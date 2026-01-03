package com.yunshen.yunshoppingbackend.model.dto.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品更新请求
 */
@Data
public class ProductUpdateRequest implements Serializable {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品标题
     */
    private String productTitle;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 主图URL
     */
    private String mainImageUrl;

    /**
     * 图片URL列表JSON
     */
    private String imageUrls;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 单位
     */
    private String unit;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 标签JSON
     */
    private String tags;

    /**
     * 规格JSON
     */
    private String specifications;

    private static final long serialVersionUID = 1L;
}