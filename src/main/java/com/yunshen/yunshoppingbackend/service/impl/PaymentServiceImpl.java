package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.constant.OrderConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.OrderMapper;
import com.yunshen.yunshoppingbackend.mapper.PaymentMapper;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.entity.Payment;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.PaymentVO;
import com.yunshen.yunshoppingbackend.service.PaymentService;
import com.yunshen.yunshoppingbackend.utils.OrderNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 支付服务实现
 */
@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Long createPayment(Long orderId, Integer paymentMethod, User loginUser) {
        ThrowUtils.throwIf(orderId == null || paymentMethod == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Order order = orderMapper.selectById(orderId);
        ThrowUtils.throwIf(order == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!order.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);
        ThrowUtils.throwIf(order.getOrderStatus() != OrderConstant.ORDER_STATUS_WAIT_PAY,
                ErrorCode.OPERATION_ERROR, "订单状态不允许支付");

        Payment payment = new Payment();
        payment.setPaymentNo(OrderNumberUtil.generateOrderNo());
        payment.setOrderId(orderId);
        payment.setUserId(loginUser.getId());
        payment.setPaymentMethod(String.valueOf(paymentMethod));
        payment.setPayAmount(order.getPayAmount());
        payment.setPayStatus(0);

        boolean result = this.save(payment);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("创建支付记录 userId:{} orderId:{} paymentNo:{}", loginUser.getId(), orderId, payment.getPaymentNo());
        return payment.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean paymentSuccess(String paymentNo, String tradeNo) {
        ThrowUtils.throwIf(paymentNo == null, ErrorCode.PARAMS_ERROR);

        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paymentNo", paymentNo);
        Payment payment = this.getOne(queryWrapper);
        ThrowUtils.throwIf(payment == null, ErrorCode.NOT_FOUND_ERROR);

        if (payment.getPayStatus() == 1) {
            log.warn("重复支付回调 paymentNo:{}", paymentNo);
            return true;
        }

        payment.setPayStatus(1);
        payment.setTransactionId(tradeNo);
        payment.setPayTime(new Date());
        boolean result = this.updateById(payment);

        Order order = orderMapper.selectById(payment.getOrderId());
        if (order != null) {
            order.setPaymentStatus(1);
            order.setOrderStatus(OrderConstant.ORDER_STATUS_WAIT_SEND);
            orderMapper.updateById(order);
        }

        log.info("支付成功 paymentNo:{} tradeNo:{}", paymentNo, tradeNo);
        return result;
    }

    @Override
    public Boolean paymentFailed(String paymentNo) {
        ThrowUtils.throwIf(paymentNo == null, ErrorCode.PARAMS_ERROR);

        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paymentNo", paymentNo);
        Payment payment = this.getOne(queryWrapper);
        ThrowUtils.throwIf(payment == null, ErrorCode.NOT_FOUND_ERROR);

        payment.setPayStatus(2);
        boolean result = this.updateById(payment);

        log.info("支付失败 paymentNo:{}", paymentNo);
        return result;
    }

    @Override
    public PaymentVO getPaymentByOrderId(Long orderId) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);

        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", orderId);
        Payment payment = this.getOne(queryWrapper);

        return getPaymentVO(payment);
    }

    @Override
    public PaymentVO getPaymentVO(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentVO paymentVO = new PaymentVO();
        BeanUtil.copyProperties(payment, paymentVO);
        return paymentVO;
    }
}