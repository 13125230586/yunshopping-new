package com.yunshen.yunshoppingbackend.model.dto.cart;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车添加请求
 */
@Data
public class CartAddRequest implements Serializable {

    private Long productId;

    private Integer quantity;

    private String specification;

    private static final long serialVersionUID = 1L;
}