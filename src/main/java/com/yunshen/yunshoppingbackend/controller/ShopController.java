package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.shop.ShopReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ShopVO;
import com.yunshen.yunshoppingbackend.service.ShopService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 店铺接口
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    @Resource
    private ShopService shopService;

    @Resource
    private UserService userService;

    /**
     * 店铺入驻申请
     */
    @PostMapping("/add")
    public BaseResponse<Long> addShop(@RequestBody ShopAddRequest shopAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long shopId = shopService.addShop(shopAddRequest, loginUser);
        return ResultUtils.success(shopId);
    }

    /**
     * 店铺审核
     */
    @PostMapping("/review")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> reviewShop(@RequestBody ShopReviewRequest reviewRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = shopService.reviewShop(reviewRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 删除店铺
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteShop(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        boolean result = shopService.removeById(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * 分页查询店铺
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ShopVO>> listShopByPage(@RequestBody ShopQueryRequest shopQueryRequest) {
        Page<ShopVO> shopVOPage = shopService.listShopVOByPage(shopQueryRequest);
        return ResultUtils.success(shopVOPage);
    }

    /**
     * 根据ID获取店铺
     */
    @GetMapping("/get")
    public BaseResponse<ShopVO> getShopById(Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        ShopVO shopVO = shopService.getShopVO(shopService.getById(id));
        return ResultUtils.success(shopVO);
    }
}