package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductReviewRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ProductVO;
import com.yunshen.yunshoppingbackend.service.ProductService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 商品接口
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    /**
     * 添加商品
     */
    @PostMapping("/add")
    public BaseResponse<Long> addProduct(@RequestBody ProductAddRequest productAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long productId = productService.addProduct(productAddRequest, loginUser);
        return ResultUtils.success(productId);
    }

    /**
     * 商品审核
     */
    @PostMapping("/review")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> reviewProduct(@RequestBody ProductReviewRequest reviewRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = productService.reviewProduct(reviewRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 删除商品
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProduct(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = productService.removeById(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * 分页查询商品
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ProductVO>> listProductByPage(@RequestBody ProductQueryRequest productQueryRequest) {
        Page<ProductVO> productVOPage = productService.listProductVOByPage(productQueryRequest);
        return ResultUtils.success(productVOPage);
    }

    /**
     * 根据ID获取商品
     */
    @GetMapping("/get")
    public BaseResponse<ProductVO> getProductById(Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        ProductVO productVO = productService.getProductVO(productService.getById(id));
        return ResultUtils.success(productVO);
    }
}