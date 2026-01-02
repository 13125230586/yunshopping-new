package com.yunshen.yunshoppingbackend.model.dto.product;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品审核请求
 */
@Data
public class ProductReviewRequest implements Serializable {

    private Long id;

    private Integer reviewStatus;

    private String reviewMessage;

    private static final long serialVersionUID = 1L;
}