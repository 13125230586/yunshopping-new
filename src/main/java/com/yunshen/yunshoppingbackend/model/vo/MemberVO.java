package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息视图
 */
@Data
public class MemberVO implements Serializable {

    /**
     * 会员ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 当前等级ID
     */
    private Long levelId;

    /**
     * 等级信息
     */
    private MemberLevelVO levelInfo;

    /**
     * 成长值
     */
    private Integer growthValue;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 开通时间
     */
    private Date activateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusText;

    /**
     * 累计消费金额
     */
    private BigDecimal totalConsumeAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}