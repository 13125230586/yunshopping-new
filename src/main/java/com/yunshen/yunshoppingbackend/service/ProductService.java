package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ProductVO;

/**
 * 商品服务
 */
public interface ProductService extends IService<Product> {

    /**
     * 添加商品
     */
    Long addProduct(ProductAddRequest productAddRequest, User loginUser);

    /**
     * 商品审核
     */
    Boolean reviewProduct(ProductReviewRequest reviewRequest, User loginUser);

    /**
     * 分页查询商品
     */
    Page<ProductVO> listProductVOByPage(ProductQueryRequest productQueryRequest);

    /**
     * 获取商品VO
     */
    ProductVO getProductVO(Product product);

    /**
     * 扣减库存
     */
    Boolean deductStock(Long productId, Integer quantity);

    /**
     * 恢复库存
     */
    Boolean restoreStock(Long productId, Integer quantity);
}