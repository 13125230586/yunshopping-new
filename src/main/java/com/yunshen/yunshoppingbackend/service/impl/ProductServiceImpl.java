package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.ProductMapper;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ProductVO;
import com.yunshen.yunshoppingbackend.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品服务实现
 */
@Service
@Slf4j
public class
ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Long addProduct(ProductAddRequest productAddRequest, User loginUser) {
        ThrowUtils.throwIf(productAddRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Product product = new Product();
        BeanUtil.copyProperties(productAddRequest, product);
        product.setUserId(loginUser.getId());
        product.setStatus(0);
        product.setReviewStatus(0);
        product.setSales(0);
        product.setViewCount(0);
        product.setFavoriteCount(0);

        boolean result = this.save(product);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("商品添加 userId:{} productId:{} productName:{}", loginUser.getId(), product.getId(), product.getProductName());
        return product.getId();
    }

    @Override
    public Boolean reviewProduct(ProductReviewRequest reviewRequest, User loginUser) {
        ThrowUtils.throwIf(reviewRequest == null || reviewRequest.getId() == null, ErrorCode.PARAMS_ERROR);

        Product product = this.getById(reviewRequest.getId());
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR);

        product.setReviewStatus(reviewRequest.getReviewStatus());
        product.setReviewMessage(reviewRequest.getReviewMessage());
        product.setReviewerId(loginUser.getId());
        product.setReviewTime(new Date());

        boolean result = this.updateById(product);
        log.info("商品审核 productId:{} reviewStatus:{} reviewerId:{}", product.getId(), reviewRequest.getReviewStatus(), loginUser.getId());
        return result;
    }

    @Override
    public Page<ProductVO> listProductVOByPage(ProductQueryRequest productQueryRequest) {
        int current = productQueryRequest.getCurrent();
        int pageSize = productQueryRequest.getPageSize();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productQueryRequest.getId() != null, "id", productQueryRequest.getId());
        queryWrapper.like(StringUtils.isNotBlank(productQueryRequest.getProductName()), "productName", productQueryRequest.getProductName());
        queryWrapper.eq(productQueryRequest.getCategoryId() != null, "categoryId", productQueryRequest.getCategoryId());
        queryWrapper.eq(productQueryRequest.getShopId() != null, "shopId", productQueryRequest.getShopId());
        queryWrapper.eq(StringUtils.isNotBlank(productQueryRequest.getBrandName()), "brandName", productQueryRequest.getBrandName());
        queryWrapper.eq(productQueryRequest.getStatus() != null, "status", productQueryRequest.getStatus());
        queryWrapper.eq(productQueryRequest.getReviewStatus() != null, "reviewStatus", productQueryRequest.getReviewStatus());
        queryWrapper.ge(productQueryRequest.getMinPrice() != null, "price", productQueryRequest.getMinPrice());
        queryWrapper.le(productQueryRequest.getMaxPrice() != null, "price", productQueryRequest.getMaxPrice());
        queryWrapper.orderByDesc("createTime");

        Page<Product> productPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<ProductVO> productVOPage = new Page<>(current, pageSize, productPage.getTotal());

        List<ProductVO> productVOList = productPage.getRecords().stream()
                .map(this::getProductVO)
                .collect(Collectors.toList());
        productVOPage.setRecords(productVOList);

        return productVOPage;
    }

    @Override
    public ProductVO getProductVO(Product product) {
        if (product == null) {
            return null;
        }
        ProductVO productVO = new ProductVO();
        BeanUtil.copyProperties(product, productVO);
        return productVO;
    }

    @Override
    public Boolean deductStock(Long productId, Integer quantity) {
        Product product = this.getById(productId);
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(product.getStock() < quantity, ErrorCode.OPERATION_ERROR, "库存不足");

        product.setStock(product.getStock() - quantity);
        boolean result = this.updateById(product);
        log.info("扣减库存 productId:{} quantity:{} remainStock:{}", productId, quantity, product.getStock());
        return result;
    }

    @Override
    public Boolean restoreStock(Long productId, Integer quantity) {
        Product product = this.getById(productId);
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR);

        product.setStock(product.getStock() + quantity);
        boolean result = this.updateById(product);
        log.info("恢复库存 productId:{} quantity:{} currentStock:{}", productId, quantity, product.getStock());
        return result;
    }
}