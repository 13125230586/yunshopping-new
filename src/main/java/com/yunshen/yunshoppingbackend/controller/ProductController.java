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
import com.yunshen.yunshoppingbackend.model.dto.product.ProductStatusRequest;
import com.yunshen.yunshoppingbackend.model.dto.product.ProductUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.Product;
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

        Product product = productService.getById(deleteRequest.getId());
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");

        // 只有商品所有者或管理员可以删除
        boolean isAdmin = UserConstant.ADMIN_ROLE.equals(loginUser.getUserRole());
        boolean isOwner = product.getUserId().equals(loginUser.getId());
        ThrowUtils.throwIf(!isAdmin && !isOwner, ErrorCode.NO_AUTH_ERROR, "无权限删除该商品");

        boolean result = productService.removeById(deleteRequest.getId());
        log.info("商品删除 productId:{} userId:{} isAdmin:{}", deleteRequest.getId(), loginUser.getId(), isAdmin);
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

    /**
     * 更新商品信息
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(productUpdateRequest == null || productUpdateRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        Product product = productService.getById(productUpdateRequest.getId());
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");

        // 只有商品所有者或管理员可以更新
        boolean isAdmin = UserConstant.ADMIN_ROLE.equals(loginUser.getUserRole());
        boolean isOwner = product.getUserId().equals(loginUser.getId());
        ThrowUtils.throwIf(!isAdmin && !isOwner, ErrorCode.NO_AUTH_ERROR, "无权限编辑该商品");

        Boolean result = productService.updateProduct(productUpdateRequest);
        log.info("商品更新 productId:{} userId:{} isAdmin:{}", productUpdateRequest.getId(), loginUser.getId(), isAdmin);
        return ResultUtils.success(result);
    }

    /**
     * 修改商品上下架状态
     */
    @PostMapping("/status")
    public BaseResponse<Boolean> updateProductStatus(@RequestBody ProductStatusRequest productStatusRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(productStatusRequest == null || productStatusRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        Product product = productService.getById(productStatusRequest.getId());
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");

        // 只有商品所有者或管理员可以修改状态
        boolean isAdmin = UserConstant.ADMIN_ROLE.equals(loginUser.getUserRole());
        boolean isOwner = product.getUserId().equals(loginUser.getId());
        ThrowUtils.throwIf(!isAdmin && !isOwner, ErrorCode.NO_AUTH_ERROR, "无权限修改该商品状态");

        Boolean result = productService.updateProductStatus(productStatusRequest);
        log.info("商品状态修改 productId:{} status:{} userId:{} isAdmin:{}", productStatusRequest.getId(), productStatusRequest.getStatus(), loginUser.getId(), isAdmin);
        return ResultUtils.success(result);
    }

    /**
     * 卖家分页查询自己的商品
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<ProductVO>> listMyProductByPage(@RequestBody ProductQueryRequest productQueryRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        productQueryRequest.setUserId(loginUser.getId());

        Page<ProductVO> productVOPage = productService.listProductVOByPage(productQueryRequest);
        log.info("卖家查询自己的商品 userId:{} total:{} current:{} pageSize:{}",
                loginUser.getId(), productVOPage.getTotal(), productQueryRequest.getCurrent(), productQueryRequest.getPageSize());
        return ResultUtils.success(productVOPage);
    }
}