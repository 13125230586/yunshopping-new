package com.yunshen.yunshoppingbackend.model.dto.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品添加请求
 */
@Data
public class ProductAddRequest implements Serializable {

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

    private String unit;

    private BigDecimal weight;

    private String tags;

    private String specifications;

    private static final long serialVersionUID = 1L;
}