package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.model.vo.CouponVO;
import com.yunshen.yunshoppingbackend.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券管理接口（管理员端）
 */
@Api(tags = "优惠券管理接口")
@RestController
@RequestMapping("/admin/coupon")
@Slf4j
public class AdminCouponController {

    @Resource
    private CouponService couponService;

    /**
     * 获取所有优惠券列表
     */
    @ApiOperation("获取所有优惠券列表")
    @GetMapping("/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<CouponVO>> listAllCoupons() {
        List<CouponVO> couponList = couponService.listAllCoupons();
        return ResultUtils.success(couponList);
    }
}