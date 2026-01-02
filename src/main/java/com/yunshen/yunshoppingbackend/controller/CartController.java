package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.cart.CartAddRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.CartVO;
import com.yunshen.yunshoppingbackend.service.CartService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车接口
 */
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {


    @Resource
    private CartService cartService;

    @Resource
    private UserService userService;

    /**
     * 添加到购物车
     */
    @PostMapping("/add")
    public BaseResponse<Long> addToCart(@RequestBody CartAddRequest cartAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long cartId = cartService.addToCart(cartAddRequest, loginUser);
        return ResultUtils.success(cartId);
    }

    /**
     * 获取购物车列表
     */
    @GetMapping("/list")
    public BaseResponse<List<CartVO>> getCartList(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<CartVO> cartList = cartService.getUserCartList(loginUser);
        return ResultUtils.success(cartList);
    }

    /**
     * 更新购物车商品数量
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateQuantity(@RequestParam Long cartId,
                                                 @RequestParam Integer quantity,
                                                 HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = cartService.updateQuantity(cartId, quantity, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 删除购物车商品
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCartItem(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = cartService.deleteCartItem(deleteRequest.getId(), loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 清空购物车
     */
    @PostMapping("/clear")
    public BaseResponse<Boolean> clearCart(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = cartService.clearCart(loginUser);
        return ResultUtils.success(result);
    }
}