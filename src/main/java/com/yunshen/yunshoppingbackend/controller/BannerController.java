package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.model.vo.BannerVO;
import com.yunshen.yunshoppingbackend.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图接口
 */
@Api(tags = "轮播图接口")
@RestController
@RequestMapping("/banner")
@Slf4j
public class BannerController {

    @Resource
    private BannerService bannerService;

    /**
     * 获取启用的轮播图列表
     */
    @ApiOperation("获取启用的轮播图列表")
    @GetMapping("/list")
    public BaseResponse<List<BannerVO>> listBanner() {
        List<BannerVO> bannerList = bannerService.listEnabledBanners();
        return ResultUtils.success(bannerList);
    }
}