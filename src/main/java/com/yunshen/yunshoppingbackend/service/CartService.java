package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.cart.CartAddRequest;
import com.yunshen.yunshoppingbackend.model.entity.Cart;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.CartVO;

import java.util.List;

/**
 * 购物车服务
 */
public interface CartService extends IService<Cart> {

    /**
     * 添加商品到购物车
     */
    Long addToCart(CartAddRequest cartAddRequest, User loginUser);

    /**
     * 获取用户购物车列表
     */
    List<CartVO> getUserCartList(User loginUser);

    /**
     * 更新购物车商品数量
     */
    Boolean updateQuantity(Long cartId, Integer quantity, User loginUser);

    /**
     * 删除购物车商品
     */
    Boolean deleteCartItem(Long cartId, User loginUser);

    /**
     * 清空购物车
     */
    Boolean clearCart(User loginUser);

    /**
     * 获取购物车VO
     */
    CartVO getCartVO(Cart cart);
}