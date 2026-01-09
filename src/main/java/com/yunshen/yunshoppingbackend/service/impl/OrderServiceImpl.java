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
import com.yunshen.yunshoppingbackend.mapper.CouponMapper;
import com.yunshen.yunshoppingbackend.mapper.MemberMapper;
import com.yunshen.yunshoppingbackend.mapper.OrderItemMapper;
import com.yunshen.yunshoppingbackend.mapper.OrderMapper;
import com.yunshen.yunshoppingbackend.mapper.ProductMapper;
import com.yunshen.yunshoppingbackend.mapper.ShopMapper;
import com.yunshen.yunshoppingbackend.mapper.UserCouponMapper;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderCreateRequest;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.Address;
import com.yunshen.yunshoppingbackend.model.entity.Cart;
import com.yunshen.yunshoppingbackend.model.entity.Coupon;
import com.yunshen.yunshoppingbackend.model.entity.Member;
import com.yunshen.yunshoppingbackend.model.entity.MemberLevel;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.entity.OrderItem;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.entity.UserCoupon;
import com.yunshen.yunshoppingbackend.model.enums.UserRoleEnum;
import com.yunshen.yunshoppingbackend.model.vo.OrderItemVO;
import com.yunshen.yunshoppingbackend.model.vo.OrderVO;
import com.yunshen.yunshoppingbackend.service.MemberLevelService;
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
import java.math.RoundingMode;
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

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberLevelService memberLevelService;

    @Resource
    private CouponMapper couponMapper;

    @Resource
    private UserCouponMapper userCouponMapper;

    @Resource
    private ShopMapper shopMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderCreateRequest orderCreateRequest, User loginUser) {
        ThrowUtils.throwIf(orderCreateRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<Long> cartIds = orderCreateRequest.getCartIds();
        Long addressId = orderCreateRequest.getAddressId();
        Long couponId = orderCreateRequest.getCouponId();

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

        BigDecimal payAmount = totalAmount;
        BigDecimal discountAmount = BigDecimal.ZERO;

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("userId", loginUser.getId());
        Member member = memberMapper.selectOne(memberQueryWrapper);

        if (member != null && member.getStatus() == 0) {
            MemberLevel memberLevel = memberLevelService.getById(member.getLevelId());
            if (memberLevel != null && memberLevel.getDiscountRate() != null) {
                BigDecimal memberDiscount = PriceUtil.calculateDiscountPrice(totalAmount, memberLevel.getDiscountRate());
                BigDecimal memberDiscountAmount = totalAmount.subtract(memberDiscount).setScale(2, RoundingMode.HALF_UP);
                payAmount = memberDiscount;
                discountAmount = PriceUtil.add(discountAmount, memberDiscountAmount);

                log.info("应用会员折扣 userId:{} levelId:{} discountRate:{} discountAmount:{}",
                        loginUser.getId(), memberLevel.getId(), memberLevel.getDiscountRate(), memberDiscountAmount);
            }
        }

        if (couponId != null) {
            log.info("使用优惠券 userId:{} couponId:{}", loginUser.getId(), couponId);

            QueryWrapper<UserCoupon> userCouponQueryWrapper = new QueryWrapper<>();
            userCouponQueryWrapper.eq("userId", loginUser.getId());
            userCouponQueryWrapper.eq("couponId", couponId);
            userCouponQueryWrapper.eq("status", 0);
            UserCoupon userCoupon = userCouponMapper.selectOne(userCouponQueryWrapper);

            if (userCoupon == null) {
                log.warn("用户优惠券不存在或已使用 userId:{} couponId:{}", loginUser.getId(), couponId);
            }

            ThrowUtils.throwIf(userCoupon == null, ErrorCode.PARAMS_ERROR, "优惠券不可用");

            Coupon coupon = couponMapper.selectById(couponId);
            ThrowUtils.throwIf(coupon == null, ErrorCode.NOT_FOUND_ERROR, "优惠券不存在");
            ThrowUtils.throwIf(coupon.getStatus() != 1, ErrorCode.PARAMS_ERROR, "优惠券已失效");

            if (coupon.getMinAmount() != null && payAmount.compareTo(coupon.getMinAmount()) < 0) {
                ThrowUtils.throwIf(true, ErrorCode.PARAMS_ERROR, "订单金额未达到优惠券使用门槛");
            }

            BigDecimal couponDiscountAmount = BigDecimal.ZERO;
            if (coupon.getCouponType() == 1 || coupon.getCouponType() == 3) {
                couponDiscountAmount = coupon.getDiscountAmount();
            } else if (coupon.getCouponType() == 2 && coupon.getDiscountRate() != null) {
                BigDecimal discountMultiplier = BigDecimal.ONE.subtract(coupon.getDiscountRate()).setScale(2, RoundingMode.HALF_UP);
                couponDiscountAmount = payAmount.multiply(discountMultiplier).setScale(2, RoundingMode.HALF_UP);
            }

            payAmount = PriceUtil.calculateCouponPrice(payAmount, couponDiscountAmount);
            discountAmount = PriceUtil.add(discountAmount, couponDiscountAmount);

            userCoupon.setStatus(1);
            userCoupon.setUseTime(new java.util.Date());
            userCouponMapper.updateById(userCoupon);

            log.info("应用优惠券 userId:{} couponId:{} couponType:{} discountAmount:{}",
                    loginUser.getId(), couponId, coupon.getCouponType(), couponDiscountAmount);
        }

        Order order = new Order();
        order.setOrderNo(OrderNumberUtil.generateOrderNo());
        order.setUserId(loginUser.getId());
        order.setShopId(shopId);
        order.setTotalAmount(totalAmount);
        order.setPayAmount(payAmount);
        order.setDiscountAmount(discountAmount);
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

        log.info("创建订单 userId:{} orderId:{} orderNo:{} totalAmount:{} payAmount:{} discountAmount:{}",
                loginUser.getId(), order.getId(), order.getOrderNo(), totalAmount, payAmount, discountAmount);
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

    @Override
    public Page<OrderVO> listSellerOrderVOByPage(OrderQueryRequest orderQueryRequest, User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(!UserRoleEnum.SELLER.getValue().equals(loginUser.getUserRole()),
                ErrorCode.NO_AUTH_ERROR, "只有卖家可以访问");

        int current = orderQueryRequest.getCurrent();
        int pageSize = orderQueryRequest.getPageSize();

        QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
        shopQueryWrapper.eq("userId", loginUser.getId());
        List<Shop> shops = shopMapper.selectList(shopQueryWrapper);

        if (shops.isEmpty()) {
            log.info("查询卖家店铺为空 userId:{}", loginUser.getId());
            return new Page<>(current, pageSize, 0);
        }

        List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());
        log.info("查询卖家店铺 userId:{} shopIds:{}", loginUser.getId(), JSONObject.toJSONString(shopIds));

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("shopId", shopIds);

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

        log.info("卖家分页查询订单 userId:{} shopIds:{} total:{}",
                loginUser.getId(), JSONObject.toJSONString(shopIds), orderPage.getTotal());

        return orderVOPage;
    }

    @Override
    public OrderVO getSellerOrderDetail(Long orderId, User loginUser) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(!UserRoleEnum.SELLER.getValue().equals(loginUser.getUserRole()),
                ErrorCode.NO_AUTH_ERROR, "只有卖家可以访问");

        QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
        shopQueryWrapper.eq("userId", loginUser.getId());
        List<Shop> shops = shopMapper.selectList(shopQueryWrapper);

        if (shops.isEmpty()) {
            log.warn("卖家无店铺 userId:{}", loginUser.getId());
            ThrowUtils.throwIf(true, ErrorCode.NO_AUTH_ERROR, "您还没有店铺");
        }

        List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());

        Order order = this.getById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!shopIds.contains(order.getShopId()),
                ErrorCode.NO_AUTH_ERROR, "无权查看该订单");

        log.info("卖家查询订单详情 userId:{} orderId:{} shopId:{}",
                loginUser.getId(), orderId, order.getShopId());

        return getOrderVO(order);
    }
}