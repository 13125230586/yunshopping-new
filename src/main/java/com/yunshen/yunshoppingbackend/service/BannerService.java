package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerSortRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.Banner;
import com.yunshen.yunshoppingbackend.model.vo.BannerVO;

import java.util.List;

/**
 * 轮播图服务
 */
public interface BannerService extends IService<Banner> {

    /**
     * 获取启用的轮播图列表
     */
    List<BannerVO> listEnabledBanners();

    /**
     * 分页查询轮播图
     */
    Page<BannerVO> listBannerVOByPage(BannerQueryRequest bannerQueryRequest);

    /**
     * 新增轮播图
     */
    Long addBanner(BannerAddRequest bannerAddRequest);

    /**
     * 修改轮播图
     */
    Boolean updateBanner(BannerUpdateRequest bannerUpdateRequest);

    /**
     * 删除轮播图
     */
    Boolean deleteBanner(Long id);

    /**
     * 修改轮播图状态
     */
    Boolean updateBannerStatus(Long id, Integer status);

    /**
     * 批量修改排序
     */
    Boolean batchUpdateSort(BannerSortRequest bannerSortRequest);

    /**
     * 获取轮播图VO
     */
    BannerVO getBannerVO(Banner banner);
}