package com.yunshen.yunshoppingbackend.model.dto.product;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品状态修改请求
 */
@Data
public class ProductStatusRequest implements Serializable {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品状态 0下架 1上架
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}