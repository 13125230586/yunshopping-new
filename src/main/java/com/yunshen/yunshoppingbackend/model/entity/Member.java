package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息实体
 */
@TableName(value = "member")
@Data
public class Member implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long levelId;

    private Integer growthValue;

    private Date expireTime;

    private Date activateTime;

    private Integer status;

    private BigDecimal totalConsumeAmount;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}