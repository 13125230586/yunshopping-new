package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品视图对象
 */
@Data
public class ProductVO implements Serializable {

    private Long id;

    private String productName;

    private String productTitle;

    private String productDescription;

    private Long categoryId;

    private Long shopId;

    private String shopName;

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

    private Integer viewCount;

    private Integer favoriteCount;

    private Long userId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}