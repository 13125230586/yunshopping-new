package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 店铺实体
 */
@TableName(value = "shop")
@Data
public class Shop implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String shopName;

    private String shopLogo;

    private String shopBanner;

    private String shopDescription;

    private Integer shopLevel;

    private Integer shopStatus;

    private Long userId;

    private Long totalSales;

    private BigDecimal totalRevenue;

    private BigDecimal rating;

    private Integer reviewCount;

    private String province;

    private String city;

    private Integer reviewStatus;

    private String reviewMessage;

    private Long reviewerId;

    private Date reviewTime;

    private Date createTime;

    private Date editTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}