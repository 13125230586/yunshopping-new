package com.yunshen.yunshoppingbackend.constant;

/**
 * 支付常量
 */
public interface PaymentConstant {

    /**
     * 支付方式 支付宝
     */
    String PAYMENT_METHOD_ALIPAY = "alipay";

    /**
     * 支付方式 微信支付
     */
    String PAYMENT_METHOD_WXPAY = "wxpay";

    /**
     * 支付状态 待支付
     */
    int PAYMENT_STATUS_WAIT = 0;

    /**
     * 支付状态 已支付
     */
    int PAYMENT_STATUS_PAID = 1;

    /**
     * 支付状态 已退款
     */
    int PAYMENT_STATUS_REFUNDED = 2;
}