package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.UserCouponVO;
import com.yunshen.yunshoppingbackend.service.CouponService;
import com.yunshen.yunshoppingbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 优惠券接口
 */
@Api(tags = "优惠券接口")
@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponController {

    @Resource
    private CouponService couponService;

    @Resource
    private UserService userService;

    /**
     * 获取用户可用的优惠券列表
     */
    @ApiOperation("获取用户可用的优惠券列表")
    @GetMapping("/available")
    public BaseResponse<List<UserCouponVO>> getAvailableCoupons(
            @RequestParam(required = false) BigDecimal orderAmount,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<UserCouponVO> couponList = couponService.getAvailableUserCoupons(orderAmount, loginUser);
        return ResultUtils.success(couponList);
    }

    /**
     * 获取用户的所有优惠券列表
     */
    @ApiOperation("获取用户的所有优惠券列表")
    @GetMapping("/my")
    public BaseResponse<List<UserCouponVO>> getMyCoupons(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<UserCouponVO> couponList = couponService.getAvailableUserCoupons(null, loginUser);
        return ResultUtils.success(couponList);
    }
}
