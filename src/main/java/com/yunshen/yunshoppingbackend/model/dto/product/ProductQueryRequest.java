package com.yunshen.yunshoppingbackend.model.dto.product;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQueryRequest extends PageRequest implements Serializable {

    private Long id;

    private String productName;

    private Long categoryId;

    private Long shopId;

    private String brandName;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer status;

    private Integer reviewStatus;

    private Long userId;

    private String searchText;

    private static final long serialVersionUID = 1L;
}