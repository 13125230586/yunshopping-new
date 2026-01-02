package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 店铺视图对象
 */
@Data
public class ShopVO implements Serializable {

    private Long id;

    private String shopName;

    private String shopLogo;

    private String shopBanner;

    private String shopDescription;

    private Integer shopLevel;

    private Integer shopStatus;

    private Long userId;

    private Long totalSales;

    private BigDecimal totalRevenue;

    private BigDecimal rating;

    private Integer reviewCount;

    private String province;

    private String city;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}