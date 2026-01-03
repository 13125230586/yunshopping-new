package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.constant.BannerConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.BannerMapper;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerSortRequest;
import com.yunshen.yunshoppingbackend.model.dto.banner.BannerUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.Banner;
import com.yunshen.yunshoppingbackend.model.entity.Category;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.vo.BannerVO;
import com.yunshen.yunshoppingbackend.service.BannerService;
import com.yunshen.yunshoppingbackend.service.CategoryService;
import com.yunshen.yunshoppingbackend.service.ProductService;
import com.yunshen.yunshoppingbackend.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 轮播图服务实现
 */
@Service
@Slf4j
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @Resource
    private ShopService shopService;

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?://)([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$"
    );

    @Override
    public List<BannerVO> listEnabledBanners() {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", BannerConstant.STATUS_ENABLED);
        queryWrapper.orderByAsc("sortOrder");

        List<Banner> bannerList = this.list(queryWrapper);
        return bannerList.stream()
                .map(this::getBannerVO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BannerVO> listBannerVOByPage(BannerQueryRequest bannerQueryRequest) {
        int current = bannerQueryRequest.getCurrent();
        int pageSize = bannerQueryRequest.getPageSize();

        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(bannerQueryRequest.getTitle()), "title", bannerQueryRequest.getTitle());
        queryWrapper.eq(bannerQueryRequest.getStatus() != null, "status", bannerQueryRequest.getStatus());
        queryWrapper.orderByAsc("sortOrder");

        Page<Banner> bannerPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<BannerVO> bannerVOPage = new Page<>(current, pageSize);
        bannerVOPage.setTotal(bannerPage.getTotal());

        List<BannerVO> bannerVOList = bannerPage.getRecords().stream()
                .map(this::getBannerVO)
                .collect(Collectors.toList());
        bannerVOPage.setRecords(bannerVOList);

        return bannerVOPage;
    }

    @Override
    public Long addBanner(BannerAddRequest bannerAddRequest) {
        ThrowUtils.throwIf(bannerAddRequest == null, ErrorCode.PARAMS_ERROR);

        String title = bannerAddRequest.getTitle();
        String imageUrl = bannerAddRequest.getImageUrl();
        String linkType = bannerAddRequest.getLinkType();
        String linkValue = bannerAddRequest.getLinkValue();
        Integer sortOrder = bannerAddRequest.getSortOrder();
        Integer status = bannerAddRequest.getStatus();

        ThrowUtils.throwIf(StringUtils.isBlank(title) || title.length() > 100, ErrorCode.PARAMS_ERROR, "标题长度需在1-100之间");
        ThrowUtils.throwIf(StringUtils.isBlank(imageUrl) || imageUrl.length() > 500, ErrorCode.PARAMS_ERROR, "图片地址长度需在1-500之间");
        ThrowUtils.throwIf(StringUtils.isBlank(linkType), ErrorCode.PARAMS_ERROR, "链接类型不能为空");
        ThrowUtils.throwIf(StringUtils.isBlank(linkValue) || linkValue.length() > 200, ErrorCode.PARAMS_ERROR, "链接值长度需在1-200之间");
        ThrowUtils.throwIf(sortOrder == null || sortOrder < 0, ErrorCode.PARAMS_ERROR, "排序值必须大于等于0");
        ThrowUtils.throwIf(status == null || (status != 0 && status != 1), ErrorCode.PARAMS_ERROR, "状态值必须为0或1");

        validateLinkValue(linkType, linkValue);

        Banner banner = new Banner();
        BeanUtil.copyProperties(bannerAddRequest, banner);

        boolean result = this.save(banner);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("新增轮播图 title:{} linkType:{} linkValue:{}", title, linkType, linkValue);
        return banner.getId();
    }

    @Override
    public Boolean updateBanner(BannerUpdateRequest bannerUpdateRequest) {
        ThrowUtils.throwIf(bannerUpdateRequest == null || bannerUpdateRequest.getId() == null, ErrorCode.PARAMS_ERROR);

        Banner banner = this.getById(bannerUpdateRequest.getId());
        ThrowUtils.throwIf(banner == null, ErrorCode.NOT_FOUND_ERROR);

        String title = bannerUpdateRequest.getTitle();
        String imageUrl = bannerUpdateRequest.getImageUrl();
        String linkType = bannerUpdateRequest.getLinkType();
        String linkValue = bannerUpdateRequest.getLinkValue();
        Integer sortOrder = bannerUpdateRequest.getSortOrder();
        Integer status = bannerUpdateRequest.getStatus();

        ThrowUtils.throwIf(StringUtils.isBlank(title) || title.length() > 100, ErrorCode.PARAMS_ERROR, "标题长度需在1-100之间");
        ThrowUtils.throwIf(StringUtils.isBlank(imageUrl) || imageUrl.length() > 500, ErrorCode.PARAMS_ERROR, "图片地址长度需在1-500之间");
        ThrowUtils.throwIf(StringUtils.isBlank(linkType), ErrorCode.PARAMS_ERROR, "链接类型不能为空");
        ThrowUtils.throwIf(StringUtils.isBlank(linkValue) || linkValue.length() > 200, ErrorCode.PARAMS_ERROR, "链接值长度需在1-200之间");
        ThrowUtils.throwIf(sortOrder == null || sortOrder < 0, ErrorCode.PARAMS_ERROR, "排序值必须大于等于0");
        ThrowUtils.throwIf(status == null || (status != 0 && status != 1), ErrorCode.PARAMS_ERROR, "状态值必须为0或1");

        validateLinkValue(linkType, linkValue);

        BeanUtil.copyProperties(bannerUpdateRequest, banner);
        boolean result = this.updateById(banner);

        log.info("修改轮播图 id:{} title:{}", banner.getId(), title);
        return result;
    }

    @Override
    public Boolean deleteBanner(Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        Banner banner = this.getById(id);
        ThrowUtils.throwIf(banner == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = this.removeById(id);
        log.info("删除轮播图 id:{}", id);
        return result;
    }

    @Override
    public Boolean updateBannerStatus(Long id, Integer status) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(status == null || (status != 0 && status != 1), ErrorCode.PARAMS_ERROR, "状态值必须为0或1");

        Banner banner = this.getById(id);
        ThrowUtils.throwIf(banner == null, ErrorCode.NOT_FOUND_ERROR);

        banner.setStatus(status);
        boolean result = this.updateById(banner);

        log.info("修改轮播图状态 id:{} status:{}", id, status);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchUpdateSort(BannerSortRequest bannerSortRequest) {
        ThrowUtils.throwIf(bannerSortRequest == null || bannerSortRequest.getBanners() == null, ErrorCode.PARAMS_ERROR);

        List<BannerSortRequest.BannerSortItem> banners = bannerSortRequest.getBanners();
        for (BannerSortRequest.BannerSortItem item : banners) {
            Banner banner = this.getById(item.getId());
            ThrowUtils.throwIf(banner == null, ErrorCode.NOT_FOUND_ERROR, "轮播图ID不存在");

            banner.setSortOrder(item.getSortOrder());
            this.updateById(banner);
        }

        log.info("批量修改轮播图排序 count:{}", banners.size());
        return true;
    }

    @Override
    public BannerVO getBannerVO(Banner banner) {
        if (banner == null) {
            return null;
        }
        BannerVO bannerVO = new BannerVO();
        BeanUtil.copyProperties(banner, bannerVO);
        return bannerVO;
    }

    /**
     * 验证链接值的有效性
     */
    private void validateLinkValue(String linkType, String linkValue) {
        switch (linkType) {
            case BannerConstant.LINK_TYPE_CATEGORY:
                Long categoryId = Long.parseLong(linkValue);
                Category category = categoryService.getById(categoryId);
                ThrowUtils.throwIf(category == null, ErrorCode.PARAMS_ERROR, "分类ID不存在");
                break;

            case BannerConstant.LINK_TYPE_PRODUCT:
                Long productId = Long.parseLong(linkValue);
                Product product = productService.getById(productId);
                ThrowUtils.throwIf(product == null, ErrorCode.PARAMS_ERROR, "商品ID不存在");
                break;

            case BannerConstant.LINK_TYPE_SHOP:
                Long shopId = Long.parseLong(linkValue);
                Shop shop = shopService.getById(shopId);
                ThrowUtils.throwIf(shop == null, ErrorCode.PARAMS_ERROR, "店铺ID不存在");
                break;

            case BannerConstant.LINK_TYPE_URL:
                ThrowUtils.throwIf(!URL_PATTERN.matcher(linkValue).matches(), ErrorCode.PARAMS_ERROR, "URL格式不正确");
                break;

            default:
                ThrowUtils.throwIf(true, ErrorCode.PARAMS_ERROR, "链接类型不正确");
        }
    }
}