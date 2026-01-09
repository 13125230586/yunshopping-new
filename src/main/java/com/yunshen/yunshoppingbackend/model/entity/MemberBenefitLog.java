package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员权益发放日志实体
 */
@TableName(value = "member_benefit_log")
@Data
public class MemberBenefitLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long memberId;

    private Integer benefitType;

    private String benefitValue;

    private String triggerEvent;

    private String description;

    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}