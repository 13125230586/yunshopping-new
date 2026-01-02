package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.PaymentVO;
import com.yunshen.yunshoppingbackend.service.PaymentService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 支付接口
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private UserService userService;

    /**
     * 创建支付
     */
    @PostMapping("/create")
    public BaseResponse<Long> createPayment(@RequestParam Long orderId,
                                            @RequestParam Integer paymentMethod,
                                            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long paymentId = paymentService.createPayment(orderId, paymentMethod, loginUser);
        return ResultUtils.success(paymentId);
    }

    /**
     * 支付成功回调
     */
    @PostMapping("/callback/success")
    public BaseResponse<Boolean> paymentCallback(@RequestParam String paymentNo,
                                                  @RequestParam String tradeNo) {
        boolean result = paymentService.paymentSuccess(paymentNo, tradeNo);
        return ResultUtils.success(result);
    }

    /**
     * 支付失败回调
     */
    @PostMapping("/callback/failed")
    public BaseResponse<Boolean> paymentFailed(@RequestParam String paymentNo) {
        boolean result = paymentService.paymentFailed(paymentNo);
        return ResultUtils.success(result);
    }

    /**
     * 根据订单ID获取支付记录
     */
    @GetMapping("/get")
    public BaseResponse<PaymentVO> getPaymentByOrderId(@RequestParam Long orderId) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        PaymentVO paymentVO = paymentService.getPaymentByOrderId(orderId);
        return ResultUtils.success(paymentVO);
    }
}