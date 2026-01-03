package com.yunshen.yunshoppingbackend.model.dto.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺更新请求
 */
@Data
public class ShopUpdateRequest implements Serializable {

    /**
     * 店铺ID
     */
    private Long id;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺Logo
     */
    private String shopLogo;

    /**
     * 店铺横幅
     */
    private String shopBanner;

    /**
     * 店铺描述
     */
    private String shopDescription;

    /**
     * 店铺等级 0普通店 1品牌店 2旗舰店
     */
    private Integer shopLevel;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    private static final long serialVersionUID = 1L;
}