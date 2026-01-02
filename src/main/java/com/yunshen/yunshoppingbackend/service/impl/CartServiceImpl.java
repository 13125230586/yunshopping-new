package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.CartMapper;
import com.yunshen.yunshoppingbackend.mapper.ProductMapper;
import com.yunshen.yunshoppingbackend.model.dto.cart.CartAddRequest;
import com.yunshen.yunshoppingbackend.model.entity.Cart;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.CartVO;
import com.yunshen.yunshoppingbackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车服务实现
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Long addToCart(CartAddRequest cartAddRequest, User loginUser) {
        ThrowUtils.throwIf(cartAddRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Long productId = cartAddRequest.getProductId();
        Integer quantity = cartAddRequest.getQuantity();
        ThrowUtils.throwIf(productId == null || quantity == null || quantity <= 0, ErrorCode.PARAMS_ERROR);

        Product product = productMapper.selectById(productId);
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");
        ThrowUtils.throwIf(product.getStock() < quantity, ErrorCode.OPERATION_ERROR, "库存不足");

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.eq("productId", productId);
        Cart existCart = this.getOne(queryWrapper);

        if (existCart != null) {
            existCart.setQuantity(existCart.getQuantity() + quantity);
            boolean result = this.updateById(existCart);
            ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
            log.info("更新购物车 userId:{} productId:{} quantity:{}", loginUser.getId(), productId, existCart.getQuantity());
            return existCart.getId();
        } else {
            Cart cart = new Cart();
            cart.setUserId(loginUser.getId());
            cart.setProductId(productId);
            cart.setQuantity(quantity);
            boolean result = this.save(cart);
            ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
            log.info("添加购物车 userId:{} productId:{} quantity:{}", loginUser.getId(), productId, quantity);
            return cart.getId();
        }
    }

    @Override
    public List<CartVO> getUserCartList(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.orderByDesc("createTime");

        List<Cart> cartList = this.list(queryWrapper);
        return cartList.stream()
                .map(this::getCartVO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateQuantity(Long cartId, Integer quantity, User loginUser) {
        ThrowUtils.throwIf(cartId == null || quantity == null || quantity <= 0, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Cart cart = this.getById(cartId);
        ThrowUtils.throwIf(cart == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!cart.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        Product product = productMapper.selectById(cart.getProductId());
        ThrowUtils.throwIf(product == null, ErrorCode.NOT_FOUND_ERROR, "商品不存在");
        ThrowUtils.throwIf(product.getStock() < quantity, ErrorCode.OPERATION_ERROR, "库存不足");

        cart.setQuantity(quantity);
        boolean result = this.updateById(cart);
        log.info("更新购物车数量 cartId:{} userId:{} quantity:{}", cartId, loginUser.getId(), quantity);
        return result;
    }

    @Override
    public Boolean deleteCartItem(Long cartId, User loginUser) {
        ThrowUtils.throwIf(cartId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Cart cart = this.getById(cartId);
        ThrowUtils.throwIf(cart == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!cart.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        boolean result = this.removeById(cartId);
        log.info("删除购物车商品 cartId:{} userId:{}", cartId, loginUser.getId());
        return result;
    }

    @Override
    public Boolean clearCart(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        boolean result = this.remove(queryWrapper);
        log.info("清空购物车 userId:{}", loginUser.getId());
        return result;
    }

    @Override
    public CartVO getCartVO(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartVO cartVO = new CartVO();
        BeanUtil.copyProperties(cart, cartVO);

        Product product = productMapper.selectById(cart.getProductId());
        if (product != null) {
            cartVO.setProductName(product.getProductName());
            cartVO.setProductImage(product.getMainImageUrl());
            cartVO.setPrice(product.getPrice());
            cartVO.setStock(product.getStock());
        }
        return cartVO;
    }
}