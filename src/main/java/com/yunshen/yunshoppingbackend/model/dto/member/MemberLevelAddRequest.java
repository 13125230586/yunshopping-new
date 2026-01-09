package com.yunshen.yunshoppingbackend.model.dto.member;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 新增会员等级请求
 */
@Data
public class MemberLevelAddRequest implements Serializable {

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 等级代码
     */
    private String levelCode;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;

    /**
     * 升级所需成长值
     */
    private Integer requiredGrowth;

    /**
     * 开通赠送的优惠券ID
     */
    private Long welcomeCouponId;

    /**
     * 生日月赠送优惠券ID
     */
    private Long birthdayCouponId;

    /**
     * 等级图标URL
     */
    private String icon;

    /**
     * 排序序号
     */
    private Integer sortOrder;

    /**
     * 等级描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}