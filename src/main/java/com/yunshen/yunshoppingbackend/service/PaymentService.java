package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.entity.Payment;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.PaymentVO;

/**
 * 支付服务
 */
public interface PaymentService extends IService<Payment> {

    /**
     * 创建支付记录
     */
    Long createPayment(Long orderId, Integer paymentMethod, User loginUser);

    /**
     * 支付成功回调
     */
    Boolean paymentSuccess(String paymentNo, String tradeNo);

    /**
     * 支付失败回调
     */
    Boolean paymentFailed(String paymentNo);

    /**
     * 获取支付记录
     */
    PaymentVO getPaymentByOrderId(Long orderId);

    /**
     * 获取支付VO
     */
    PaymentVO getPaymentVO(Payment payment);
}