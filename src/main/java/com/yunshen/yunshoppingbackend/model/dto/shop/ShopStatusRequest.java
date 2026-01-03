package com.yunshen.yunshoppingbackend.model.dto.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺状态修改请求
 */
@Data
public class ShopStatusRequest implements Serializable {

    /**
     * 店铺ID
     */
    private Long id;

    /**
     * 店铺状态 0待审核 1营业中 2已关闭 3审核拒绝
     */
    private Integer shopStatus;

    private static final long serialVersionUID = 1L;
}