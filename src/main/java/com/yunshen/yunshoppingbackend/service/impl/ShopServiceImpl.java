package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.constant.ShopConstant;
import com.yunshen.yunshoppingbackend.exception.BusinessException;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.ShopMapper;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ShopVO;
import com.yunshen.yunshoppingbackend.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 店铺服务实现
 */
@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Override
    public Long addShop(ShopAddRequest shopAddRequest, User loginUser) {
        ThrowUtils.throwIf(shopAddRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.ne("shopStatus", ShopConstant.SHOP_STATUS_CLOSED);
        long count = this.count(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.OPERATION_ERROR, "您已有店铺无法重复申请");

        Shop shop = new Shop();
        BeanUtil.copyProperties(shopAddRequest, shop);
        shop.setUserId(loginUser.getId());
        shop.setShopStatus(ShopConstant.SHOP_STATUS_PENDING);
        shop.setReviewStatus(ShopConstant.REVIEW_STATUS_PENDING);
        shop.setRating(new java.math.BigDecimal("5.00"));

        boolean result = this.save(shop);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("店铺入驻申请 userId:{} shopId:{} shopName:{}", loginUser.getId(), shop.getId(), shop.getShopName());
        return shop.getId();
    }

    @Override
    public Boolean reviewShop(ShopReviewRequest reviewRequest, User loginUser) {
        ThrowUtils.throwIf(reviewRequest == null || reviewRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Shop shop = this.getById(reviewRequest.getId());
        ThrowUtils.throwIf(shop == null, ErrorCode.NOT_FOUND_ERROR);

        shop.setReviewStatus(reviewRequest.getReviewStatus());
        shop.setReviewMessage(reviewRequest.getReviewMessage());
        shop.setReviewerId(loginUser.getId());
        shop.setReviewTime(new Date());

        if (ShopConstant.REVIEW_STATUS_PASS == reviewRequest.getReviewStatus()) {
            shop.setShopStatus(ShopConstant.SHOP_STATUS_OPEN);
        } else if (ShopConstant.REVIEW_STATUS_REJECT == reviewRequest.getReviewStatus()) {
            shop.setShopStatus(ShopConstant.SHOP_STATUS_REJECTED);
        }

        boolean result = this.updateById(shop);
        log.info("店铺审核 shopId:{} reviewStatus:{} reviewerId:{}", shop.getId(), reviewRequest.getReviewStatus(), loginUser.getId());
        return result;
    }

    @Override
    public Page<ShopVO> listShopVOByPage(ShopQueryRequest shopQueryRequest) {
        int current = shopQueryRequest.getCurrent();
        int pageSize = shopQueryRequest.getPageSize();

        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(shopQueryRequest.getId() != null, "id", shopQueryRequest.getId());
        queryWrapper.like(StringUtils.isNotBlank(shopQueryRequest.getShopName()), "shopName", shopQueryRequest.getShopName());
        queryWrapper.eq(shopQueryRequest.getShopLevel() != null, "shopLevel", shopQueryRequest.getShopLevel());
        queryWrapper.eq(shopQueryRequest.getShopStatus() != null, "shopStatus", shopQueryRequest.getShopStatus());
        queryWrapper.eq(shopQueryRequest.getUserId() != null, "userId", shopQueryRequest.getUserId());
        queryWrapper.eq(shopQueryRequest.getReviewStatus() != null, "reviewStatus", shopQueryRequest.getReviewStatus());
        queryWrapper.orderByDesc("createTime");

        Page<Shop> shopPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<ShopVO> shopVOPage = new Page<>(current, pageSize, shopPage.getTotal());

        List<ShopVO> shopVOList = shopPage.getRecords().stream()
                .map(this::getShopVO)
                .collect(Collectors.toList());
        shopVOPage.setRecords(shopVOList);

        return shopVOPage;
    }

    @Override
    public ShopVO getShopVO(Shop shop) {
        if (shop == null) {
            return null;
        }
        ShopVO shopVO = new ShopVO();
        BeanUtil.copyProperties(shop, shopVO);
        return shopVO;
    }
}