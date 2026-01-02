package com.yunshen.yunshoppingbackend.model.dto.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺入驻请求
 */
@Data
public class ShopAddRequest implements Serializable {

    private String shopName;

    private String shopLogo;

    private String shopBanner;

    private String shopDescription;

    private Integer shopLevel;

    private String province;

    private String city;

    private static final long serialVersionUID = 1L;
}