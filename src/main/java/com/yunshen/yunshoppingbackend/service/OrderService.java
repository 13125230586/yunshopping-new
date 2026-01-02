package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderCreateRequest;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.OrderVO;

/**
 * 订单服务
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     */
    Long createOrder(OrderCreateRequest orderCreateRequest, User loginUser);

    /**
     * 取消订单
     */
    Boolean cancelOrder(Long orderId, User loginUser);

    /**
     * 确认收货
     */
    Boolean confirmReceipt(Long orderId, User loginUser);

    /**
     * 删除订单
     */
    Boolean deleteOrder(Long orderId, User loginUser);

    /**
     * 分页查询订单
     */
    Page<OrderVO> listOrderVOByPage(OrderQueryRequest orderQueryRequest, User loginUser);

    /**
     * 获取订单详情
     */
    OrderVO getOrderDetail(Long orderId, User loginUser);

    /**
     * 获取订单VO
     */
    OrderVO getOrderVO(Order order);
}