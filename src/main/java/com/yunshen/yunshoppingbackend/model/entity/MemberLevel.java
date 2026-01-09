package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员等级实体
 */
@TableName(value = "member_level")
@Data
public class MemberLevel implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String levelName;

    private String levelCode;

    private BigDecimal discountRate;

    private Integer requiredGrowth;

    private Long welcomeCouponId;

    private Long birthdayCouponId;

    private String icon;

    private Integer sortOrder;

    private String description;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}