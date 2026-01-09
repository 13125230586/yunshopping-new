package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员权益日志视图
 */
@Data
public class MemberBenefitLogVO implements Serializable {

    /**
     * 日志ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 权益类型
     */
    private Integer benefitType;

    /**
     * 权益类型描述
     */
    private String benefitTypeText;

    /**
     * 权益值
     */
    private String benefitValue;

    /**
     * 触发事件
     */
    private String triggerEvent;

    /**
     * 触发事件描述
     */
    private String triggerEventText;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}