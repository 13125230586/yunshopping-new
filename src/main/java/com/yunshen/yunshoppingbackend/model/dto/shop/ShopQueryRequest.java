package com.yunshen.yunshoppingbackend.model.dto.shop;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 店铺查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShopQueryRequest extends PageRequest implements Serializable {

    private Long id;

    private String shopName;

    private Integer shopLevel;

    private Integer shopStatus;

    private Long userId;

    private Integer reviewStatus;

    private static final long serialVersionUID = 1L;
}