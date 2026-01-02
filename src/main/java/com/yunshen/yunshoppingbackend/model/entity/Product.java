package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体
 */
@TableName(value = "product")
@Data
public class Product implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String productName;

    private String productTitle;

    private String productDescription;

    private Long categoryId;

    private Long shopId;

    private String brandName;

    private String mainImageUrl;

    private String imageUrls;

    private BigDecimal price;

    private BigDecimal originalPrice;

    private Integer stock;

    private Integer sales;

    private String unit;

    private BigDecimal weight;

    private String tags;

    private String specifications;

    private Integer status;

    private Integer reviewStatus;

    private String reviewMessage;

    private Long reviewerId;

    private Date reviewTime;

    private Long userId;

    private Integer viewCount;

    private Integer favoriteCount;

    private Date createTime;

    private Date editTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}