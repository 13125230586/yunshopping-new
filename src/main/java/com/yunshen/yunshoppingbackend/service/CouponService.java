package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.entity.Coupon;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.CouponVO;
import com.yunshen.yunshoppingbackend.model.vo.UserCouponVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 优惠券服务
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 获取用户可用的优惠券列表
     */
    List<UserCouponVO> getAvailableUserCoupons(BigDecimal orderAmount, User loginUser);

    /**
     * 获取所有优惠券列表
     */
    List<CouponVO> listAllCoupons();

    /**
     * 获取优惠券VO
     */
    CouponVO getCouponVO(Coupon coupon);
}
