package com.yunshen.yunshoppingbackend.constant;

/**
 * 订单常量
 */
public interface OrderConstant {

    /**
     * 订单超时时间 15分钟
     */
    long ORDER_TIMEOUT_MINUTES = 15;

    /**
     * 自动确认收货时间 7天
     */
    long AUTO_CONFIRM_DAYS = 7;

    /**
     * 订单状态 待支付
     */
    int ORDER_STATUS_WAIT_PAY = 0;

    /**
     * 订单状态 已支付
     */
    int ORDER_STATUS_PAID = 1;

    /**
     * 订单状态 待发货
     */
    int ORDER_STATUS_WAIT_SEND = 2;

    /**
     * 订单状态 待发货(别名)
     */
    int ORDER_STATUS_WAIT_SHIP = 2;

    /**
     * 订单状态 待收货
     */
    int ORDER_STATUS_WAIT_RECEIVE = 3;

    /**
     * 订单状态 已发货(别名)
     */
    int ORDER_STATUS_SHIPPED = 3;

    /**
     * 订单状态 已完成
     */
    int ORDER_STATUS_COMPLETED = 4;

    /**
     * 订单状态 已取消
     */
    int ORDER_STATUS_CANCELLED = 5;

    /**
     * 订单状态 退款中
     */
    int ORDER_STATUS_REFUNDING = 6;

    /**
     * 订单状态 已退款
     */
    int ORDER_STATUS_REFUNDED = 7;
}