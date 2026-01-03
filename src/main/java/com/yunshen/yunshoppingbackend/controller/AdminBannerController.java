package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.banner.*;
import com.yunshen.yunshoppingbackend.model.vo.BannerVO;
import com.yunshen.yunshoppingbackend.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 轮播图管理接口
 */
@Api(tags = "轮播图管理接口")
@RestController
@RequestMapping("/admin/banner")
@Slf4j
public class AdminBannerController {

    @Resource
    private BannerService bannerService;

    /**
     * 分页查询轮播图
     */
    @ApiOperation("分页查询轮播图")
    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<BannerVO>> listBannerByPage(@RequestBody BannerQueryRequest bannerQueryRequest) {
        ThrowUtils.throwIf(bannerQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Page<BannerVO> bannerVOPage = bannerService.listBannerVOByPage(bannerQueryRequest);
        return ResultUtils.success(bannerVOPage);
    }

    /**
     * 新增轮播图
     */
    @ApiOperation("新增轮播图")
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addBanner(@RequestBody BannerAddRequest bannerAddRequest) {
        ThrowUtils.throwIf(bannerAddRequest == null, ErrorCode.PARAMS_ERROR);
        Long bannerId = bannerService.addBanner(bannerAddRequest);
        return ResultUtils.success(bannerId);
    }

    /**
     * 修改轮播图
     */
    @ApiOperation("修改轮播图")
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateBanner(@RequestBody BannerUpdateRequest bannerUpdateRequest) {
        ThrowUtils.throwIf(bannerUpdateRequest == null || bannerUpdateRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        Boolean result = bannerService.updateBanner(bannerUpdateRequest);
        return ResultUtils.success(result);
    }

    /**
     * 删除轮播图
     */
    @ApiOperation("删除轮播图")
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteBanner(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        Boolean result = bannerService.deleteBanner(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * 修改轮播图状态
     */
    @ApiOperation("修改轮播图状态")
    @PostMapping("/status")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateBannerStatus(@RequestBody BannerStatusRequest bannerStatusRequest) {
        ThrowUtils.throwIf(bannerStatusRequest == null || bannerStatusRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        Boolean result = bannerService.updateBannerStatus(bannerStatusRequest.getId(), bannerStatusRequest.getStatus());
        return ResultUtils.success(result);
    }

    /**
     * 批量修改排序
     */
    @ApiOperation("批量修改排序")
    @PostMapping("/sort")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> batchUpdateSort(@RequestBody BannerSortRequest bannerSortRequest) {
        ThrowUtils.throwIf(bannerSortRequest == null || bannerSortRequest.getBanners() == null, ErrorCode.PARAMS_ERROR);
        Boolean result = bannerService.batchUpdateSort(bannerSortRequest);
        return ResultUtils.success(result);
    }
}