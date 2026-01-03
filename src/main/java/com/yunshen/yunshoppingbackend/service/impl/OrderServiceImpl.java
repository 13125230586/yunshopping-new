package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.constant.OrderConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.AddressMapper;
import com.yunshen.yunshoppingbackend.mapper.CartMapper;
import com.yunshen.yunshoppingbackend.mapper.OrderItemMapper;
import com.yunshen.yunshoppingbackend.mapper.OrderMapper;
import com.yunshen.yunshoppingbackend.mapper.ProductMapper;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderCreateRequest;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.Address;
import com.yunshen.yunshoppingbackend.model.entity.Cart;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.entity.OrderItem;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.OrderItemVO;
import com.yunshen.yunshoppingbackend.model.vo.OrderVO;
import com.yunshen.yunshoppingbackend.service.OrderService;
import com.yunshen.yunshoppingbackend.service.ProductService;
import com.yunshen.yunshoppingbackend.service.UserService;
import com.yunshen.yunshoppingbackend.utils.OrderNumberUtil;
import com.yunshen.yunshoppingbackend.utils.PriceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务实现
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderCreateRequest orderCreateRequest, User loginUser) {
        ThrowUtils.throwIf(orderCreateRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<Long> cartIds = orderCreateRequest.getCartIds();
        Long addressId = orderCreateRequest.getAddressId();
        ThrowUtils.throwIf(cartIds == null || cartIds.isEmpty(), ErrorCode.PARAMS_ERROR, "购物车为空");
        ThrowUtils.throwIf(addressId == null, ErrorCode.PARAMS_ERROR, "请选择收货地址");

        Address address = addressMapper.selectById(addressId);
        ThrowUtils.throwIf(address == null, ErrorCode.NOT_FOUND_ERROR, "收货地址不存在");
        ThrowUtils.throwIf(!address.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        List<Cart> cartList = cartMapper.selectBatchIds(cartIds);
        ThrowUtils.throwIf(cartList.isEmpty(), ErrorCode.NOT_FOUND_ERROR, "购物车商品不存在");

        Long shopId = null;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Cart cart : cartList) {
            ThrowUtils.throwIf(!cart.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

            Product product = productMapper.selectById(cart.getProductId());
            ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");
            ThrowUtils.throwIf(product.getStock() < cart.getQuantity(), ErrorCode.OPERATION_ERROR, "商品库存不足");

            if (shopId == null) {
                shopId = product.getShopId();
            } else {
                ThrowUtils.throwIf(!shopId.equals(product.getShopId()), ErrorCode.OPERATION_ERROR, "不支持跨店铺下单");
            }

            BigDecimal itemTotal = PriceUtil.multiply(product.getPrice(), cart.getQuantity());
            totalAmount = PriceUtil.add(totalAmount, itemTotal);
        }

        Order order = new Order();
        order.setOrderNo(OrderNumberUtil.generateOrderNo());
        order.setUserId(loginUser.getId());
        order.setShopId(shopId);
        order.setTotalAmount(totalAmount);
        order.setPayAmount(totalAmount);
        order.setOrderStatus(OrderConstant.ORDER_STATUS_WAIT_PAY);
        order.setPaymentStatus(0);
        order.setReceiverName(address.getReceiverName());
        order.setReceiverPhone(address.getReceiverPhone());
        order.setReceiverAddress(address.getProvince() + address.getCity() + address.getDistrict() + address.getDetailAddress());

        boolean result = this.save(order);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        for (Cart cart : cartList) {
            Product product = productMapper.selectById(cart.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getProductName());
            orderItem.setProductImage(product.getMainImageUrl());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setTotalAmount(PriceUtil.multiply(product.getPrice(), cart.getQuantity()));

            orderItemMapper.insert(orderItem);

            productService.deductStock(product.getId(), cart.getQuantity());
        }

        cartMapper.deleteBatchIds(cartIds);

        log.info("创建订单 userId:{} orderId:{} orderNo:{} totalAmount:{}",
                loginUser.getId(), order.getId(), order.getOrderNo(), JSONObject.toJSONString(totalAmount));
        return order.getId();
    }

    @Override
    public Boolean cancelOrder(Long orderId, User loginUser) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Order order = this.getById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!order.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);
        ThrowUtils.throwIf(order.getOrderStatus() != OrderConstant.ORDER_STATUS_WAIT_PAY,
                ErrorCode.OPERATION_ERROR, "订单状态不允许取消");

        order.setOrderStatus(OrderConstant.ORDER_STATUS_CANCELLED);
        boolean result = this.updateById(order);

        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", orderId);
        List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
        for (OrderItem item : orderItems) {
            productService.restoreStock(item.getProductId(), item.getQuantity());
        }

        log.info("取消订单 orderId:{} userId:{}", orderId, loginUser.getId());
        return result;
    }

    @Override
    public Boolean confirmReceipt(Long orderId, User loginUser) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Order order = this.getById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!order.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);
        ThrowUtils.throwIf(order.getOrderStatus() != OrderConstant.ORDER_STATUS_WAIT_RECEIVE,
                ErrorCode.OPERATION_ERROR, "订单状态不允许确认收货");

        order.setOrderStatus(OrderConstant.ORDER_STATUS_COMPLETED);
        boolean result = this.updateById(order);
        log.info("确认收货 orderId:{} userId:{}", orderId, loginUser.getId());
        return result;
    }

    @Override
    public Boolean deleteOrder(Long orderId, User loginUser) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Order order = this.getById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!order.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        boolean result = this.removeById(orderId);
        log.info("删除订单 orderId:{} userId:{}", orderId, loginUser.getId());
        return result;
    }

    @Override
    public Page<OrderVO> listOrderVOByPage(OrderQueryRequest orderQueryRequest, User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        int current = orderQueryRequest.getCurrent();
        int pageSize = orderQueryRequest.getPageSize();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        boolean isAdmin = userService.isAdmin(loginUser);
        if (!isAdmin) {
            queryWrapper.eq("userId", loginUser.getId());
        }

        queryWrapper.eq(orderQueryRequest.getId() != null, "id", orderQueryRequest.getId());
        queryWrapper.like(StringUtils.isNotBlank(orderQueryRequest.getOrderNo()), "orderNo", orderQueryRequest.getOrderNo());
        queryWrapper.eq(orderQueryRequest.getUserId() != null, "userId", orderQueryRequest.getUserId());
        queryWrapper.eq(orderQueryRequest.getShopId() != null, "shopId", orderQueryRequest.getShopId());
        queryWrapper.eq(orderQueryRequest.getOrderStatus() != null, "orderStatus", orderQueryRequest.getOrderStatus());
        queryWrapper.eq(orderQueryRequest.getPaymentStatus() != null, "paymentStatus", orderQueryRequest.getPaymentStatus());
        queryWrapper.orderByDesc("createTime");

        Page<Order> orderPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<OrderVO> orderVOPage = new Page<>(current, pageSize, orderPage.getTotal());

        List<OrderVO> orderVOList = orderPage.getRecords().stream()
                .map(this::getOrderVO)
                .collect(Collectors.toList());
        orderVOPage.setRecords(orderVOList);

        return orderVOPage;
    }

    @Override
    public OrderVO getOrderDetail(Long orderId, User loginUser) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Order order = this.getById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!order.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        return getOrderVO(order);
    }

    @Override
    public OrderVO getOrderVO(Order order) {
        if (order == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtil.copyProperties(order, orderVO);

        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", order.getId());
        List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);

        List<OrderItemVO> orderItemVOList = orderItems.stream().map(item -> {
            OrderItemVO itemVO = new OrderItemVO();
            BeanUtil.copyProperties(item, itemVO);
            return itemVO;
        }).collect(Collectors.toList());

        orderVO.setOrderItems(orderItemVOList);
        return orderVO;
    }
}