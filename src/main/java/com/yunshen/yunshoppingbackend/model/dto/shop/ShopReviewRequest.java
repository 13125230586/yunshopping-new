package com.yunshen.yunshoppingbackend.model.dto.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺审核请求
 */
@Data
public class ShopReviewRequest implements Serializable {

    private Long id;

    private Integer reviewStatus;

    private String reviewMessage;

    private static final long serialVersionUID = 1L;
}