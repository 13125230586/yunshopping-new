package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户优惠券视图
 */
@Data
public class UserCouponVO implements Serializable {

    /**
     * 用户优惠券ID
     */
    private Long id;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 优惠券类型
     */
    private Integer couponType;

    /**
     * 优惠券类型描述
     */
    private String couponTypeText;

    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;

    /**
     * 最低使用金额
     */
    private BigDecimal minAmount;

    /**
     * 优惠券状态
     */
    private Integer status;

    /**
     * 优惠券状态描述
     */
    private String statusText;

    /**
     * 是否可用
     */
    private Boolean canUse;

    /**
     * 不可用原因
     */
    private String unavailableReason;

    /**
     * 领取时间
     */
    private Date receiveTime;

    /**
     * 使用时间
     */
    private Date useTime;

    /**
     * 优惠券开始时间
     */
    private Date startTime;

    /**
     * 优惠券结束时间
     */
    private Date endTime;

    private static final long serialVersionUID = 1L;
}
