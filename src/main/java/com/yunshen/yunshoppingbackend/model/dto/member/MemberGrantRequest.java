package com.yunshen.yunshoppingbackend.model.dto.member;

import lombok.Data;

import java.io.Serializable;

/**
 * 赠送会员请求
 */
@Data
public class MemberGrantRequest implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 等级ID
     */
    private Long levelId;

    /**
     * 有效天数（NULL表示永久）
     */
    private Integer validDays;

    private static final long serialVersionUID = 1L;
}