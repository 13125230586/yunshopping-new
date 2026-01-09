package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员等级视图
 */
@Data
public class MemberLevelVO implements Serializable {

    /**
     * 等级ID
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}