package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ShopVO;

/**
 * 店铺服务
 */
public interface ShopService extends IService<Shop> {

    /**
     * 店铺入驻申请
     */
    Long addShop(ShopAddRequest shopAddRequest, User loginUser);

    /**
     * 店铺审核
     */
    Boolean reviewShop(ShopReviewRequest reviewRequest, User loginUser);

    /**
     * 分页查询店铺
     */
    Page<ShopVO> listShopVOByPage(ShopQueryRequest shopQueryRequest);

    /**
     * 获取店铺VO
     */
    ShopVO getShopVO(Shop shop);
}