package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车视图对象
 */
@Data
public class CartVO implements Serializable {

    private Long id;

    private Long productId;

    private String productName;

    private String productImage;

    private BigDecimal price;

    private Integer quantity;

    private String specification;

    private Integer isChecked;

    private Integer stock;

    private Long shopId;

    private String shopName;

    private static final long serialVersionUID = 1L;
}