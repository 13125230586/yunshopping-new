package com.yunshen.yunshoppingbackend.model.dto.member;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询会员请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberQueryRequest extends PageRequest implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 等级ID
     */
    private Long levelId;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}