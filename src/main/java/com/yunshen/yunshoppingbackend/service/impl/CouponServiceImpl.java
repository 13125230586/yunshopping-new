package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.CouponMapper;
import com.yunshen.yunshoppingbackend.mapper.MemberMapper;
import com.yunshen.yunshoppingbackend.mapper.UserCouponMapper;
import com.yunshen.yunshoppingbackend.model.entity.Coupon;
import com.yunshen.yunshoppingbackend.model.entity.Member;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.entity.UserCoupon;
import com.yunshen.yunshoppingbackend.model.vo.CouponVO;
import com.yunshen.yunshoppingbackend.model.vo.UserCouponVO;
import com.yunshen.yunshoppingbackend.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 优惠券服务实现
 */
@Service
@Slf4j
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Resource
    private UserCouponMapper userCouponMapper;

    @Resource
    private MemberMapper memberMapper;

    @Override
    public List<UserCouponVO> getAvailableUserCoupons(BigDecimal orderAmount, User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<UserCoupon> userCouponQueryWrapper = new QueryWrapper<>();
        userCouponQueryWrapper.eq("userId", loginUser.getId());
        List<UserCoupon> userCoupons = userCouponMapper.selectList(userCouponQueryWrapper);

        List<UserCouponVO> result = new ArrayList<>();
        Date now = new Date();

        for (UserCoupon userCoupon : userCoupons) {
            Coupon coupon = this.getById(userCoupon.getCouponId());
            if (coupon == null) {
                continue;
            }

            UserCouponVO vo = new UserCouponVO();
            BeanUtil.copyProperties(userCoupon, vo);
            vo.setCouponId(coupon.getId());
            vo.setCouponName(coupon.getCouponName());
            vo.setCouponType(coupon.getCouponType());
            vo.setDiscountAmount(coupon.getDiscountAmount());
            vo.setDiscountRate(coupon.getDiscountRate());
            vo.setMinAmount(coupon.getMinAmount());
            vo.setStartTime(coupon.getStartTime());
            vo.setEndTime(coupon.getEndTime());

            String couponTypeText = "";
            if (coupon.getCouponType() == 1) {
                couponTypeText = "满减券";
            } else if (coupon.getCouponType() == 2) {
                couponTypeText = "折扣券";
            } else if (coupon.getCouponType() == 3) {
                couponTypeText = "新人券";
            }
            vo.setCouponTypeText(couponTypeText);

            String statusText = "";
            if (userCoupon.getStatus() == 0) {
                statusText = "未使用";
            } else if (userCoupon.getStatus() == 1) {
                statusText = "已使用";
            } else if (userCoupon.getStatus() == 2) {
                statusText = "已过期";
            }
            vo.setStatusText(statusText);

            boolean canUse = true;
            String unavailableReason = null;

            if (userCoupon.getStatus() != 0) {
                canUse = false;
                unavailableReason = statusText;
            }

            if (canUse && coupon.getStatus() != 1) {
                canUse = false;
                unavailableReason = "优惠券已失效";
            }

            if (canUse && coupon.getStartTime() != null && now.before(coupon.getStartTime())) {
                canUse = false;
                unavailableReason = "优惠券未到使用时间";
            }

            if (canUse && coupon.getEndTime() != null && now.after(coupon.getEndTime())) {
                canUse = false;
                unavailableReason = "优惠券已过期";
            }

            if (canUse && orderAmount != null && coupon.getMinAmount() != null
                    && orderAmount.compareTo(coupon.getMinAmount()) < 0) {
                canUse = false;
                unavailableReason = "订单金额未达到使用门槛";
            }

            if (canUse && StringUtils.isNotBlank(coupon.getForMemberLevel())) {
                QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
                memberQueryWrapper.eq("userId", loginUser.getId());
                Member member = memberMapper.selectOne(memberQueryWrapper);

                if (member == null) {
                    canUse = false;
                    unavailableReason = "仅限会员使用";
                } else {
                    try {
                        List<Long> allowedLevels = JSON.parseArray(coupon.getForMemberLevel(), Long.class);
                        if (allowedLevels != null && !allowedLevels.isEmpty()
                                && !allowedLevels.contains(member.getLevelId())) {
                            canUse = false;
                            unavailableReason = "不符合会员等级要求";
                        }
                    } catch (Exception e) {
                        log.warn("解析会员等级限制失败 couponId:{} forMemberLevel:{}",
                                coupon.getId(), coupon.getForMemberLevel());
                    }
                }
            }

            vo.setCanUse(canUse);
            vo.setUnavailableReason(unavailableReason);

            result.add(vo);
        }

        result.sort((a, b) -> {
            if (!a.getCanUse().equals(b.getCanUse())) {
                return a.getCanUse() ? -1 : 1;
            }
            return b.getReceiveTime().compareTo(a.getReceiveTime());
        });

        return result;
    }

    @Override
    public List<CouponVO> listAllCoupons() {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createTime");
        List<Coupon> coupons = this.list(queryWrapper);
        return coupons.stream()
                .map(this::getCouponVO)
                .collect(Collectors.toList());
    }

    @Override
    public CouponVO getCouponVO(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        CouponVO vo = new CouponVO();
        BeanUtil.copyProperties(coupon, vo);

        String couponTypeText = "";
        if (coupon.getCouponType() == 1) {
            couponTypeText = "满减券";
        } else if (coupon.getCouponType() == 2) {
            couponTypeText = "折扣券";
        } else if (coupon.getCouponType() == 3) {
            couponTypeText = "新人券";
        }
        vo.setCouponTypeText(couponTypeText);

        String statusText = "";
        if (coupon.getStatus() == 0) {
            statusText = "未启用";
        } else if (coupon.getStatus() == 1) {
            statusText = "已启用";
        } else if (coupon.getStatus() == 2) {
            statusText = "已失效";
        }
        vo.setStatusText(statusText);

        return vo;
    }
}
