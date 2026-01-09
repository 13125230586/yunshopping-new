package com.yunshen.yunshoppingbackend.model.dto.member;

import lombok.Data;

import java.io.Serializable;

/**
 * 开通会员请求
 */
@Data
public class MemberActivateRequest implements Serializable {

    /**
     * 目标等级ID
     */
    private Long levelId;

    /**
     * 支付方式（可选）
     */
    private String paymentMethod;

    private static final long serialVersionUID = 1L;
}