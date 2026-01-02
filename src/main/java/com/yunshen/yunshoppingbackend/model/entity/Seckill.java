package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀活动实体
 */
@TableName(value = "seckill")
@Data
public class Seckill implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String activityName;

    private Long productId;

    private BigDecimal seckillPrice;

    private Integer seckillStock;

    private Integer remainStock;

    private Integer limitPerUser;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}