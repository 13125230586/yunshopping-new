package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券视图
 */
@Data
public class CouponVO implements Serializable {

    /**
     * 优惠券ID
     */
    private Long id;

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
     * 总发行量
     */
    private Integer totalCount;

    /**
     * 已使用数量
     */
    private Integer usedCount;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 会员等级限制
     */
    private String forMemberLevel;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusText;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
