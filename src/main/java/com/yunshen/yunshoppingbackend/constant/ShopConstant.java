package com.yunshen.yunshoppingbackend.constant;

/**
 * 店铺常量
 */
public interface ShopConstant {

    /**
     * 店铺等级 普通店
     */
    int SHOP_LEVEL_NORMAL = 0;

    /**
     * 店铺等级 品牌店
     */
    int SHOP_LEVEL_BRAND = 1;

    /**
     * 店铺等级 旗舰店
     */
    int SHOP_LEVEL_FLAGSHIP = 2;

    /**
     * 店铺状态 待审核
     */
    int SHOP_STATUS_PENDING = 0;

    /**
     * 店铺状态 营业中
     */
    int SHOP_STATUS_OPEN = 1;

    /**
     * 店铺状态 已关闭
     */
    int SHOP_STATUS_CLOSED = 2;

    /**
     * 店铺状态 审核拒绝
     */
    int SHOP_STATUS_REJECTED = 3;

    /**
     * 审核状态 待审核
     */
    int REVIEW_STATUS_PENDING = 0;

    /**
     * 审核状态 通过
     */
    int REVIEW_STATUS_PASS = 1;

    /**
     * 审核状态 拒绝
     */
    int REVIEW_STATUS_REJECT = 2;
}