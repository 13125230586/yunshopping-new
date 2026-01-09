package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderCreateRequest;
import com.yunshen.yunshoppingbackend.model.dto.order.OrderQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.OrderVO;
import com.yunshen.yunshoppingbackend.service.OrderService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public BaseResponse<Long> createOrder(@RequestBody OrderCreateRequest orderCreateRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long orderId = orderService.createOrder(orderCreateRequest, loginUser);
        return ResultUtils.success(orderId);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public BaseResponse<Boolean> cancelOrder(@RequestParam Long orderId, HttpServletRequest request) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = orderService.cancelOrder(orderId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 确认收货
     */
    @PostMapping("/confirm")
    public BaseResponse<Boolean> confirmReceipt(@RequestParam Long orderId, HttpServletRequest request) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = orderService.confirmReceipt(orderId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 删除订单
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteOrder(@RequestParam Long orderId, HttpServletRequest request) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = orderService.deleteOrder(orderId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 分页查询订单
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<OrderVO>> listOrderByPage(@RequestBody OrderQueryRequest orderQueryRequest,
                                                        HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Page<OrderVO> orderVOPage = orderService.listOrderVOByPage(orderQueryRequest, loginUser);
        return ResultUtils.success(orderVOPage);
    }

    /**
     * 获取订单详情
     */
    @GetMapping("/get")
    public BaseResponse<OrderVO> getOrderDetail(@RequestParam Long orderId, HttpServletRequest request) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        OrderVO orderVO = orderService.getOrderDetail(orderId, loginUser);
        return ResultUtils.success(orderVO);
    }

    /**
     * 卖家分页查询订单
     */
    @PostMapping("/seller/list/page")
    public BaseResponse<Page<OrderVO>> listSellerOrderByPage(@RequestBody OrderQueryRequest orderQueryRequest,
                                                              HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Page<OrderVO> orderVOPage = orderService.listSellerOrderVOByPage(orderQueryRequest, loginUser);
        return ResultUtils.success(orderVOPage);
    }

    /**
     * 卖家获取订单详情
     */
    @GetMapping("/seller/get")
    public BaseResponse<OrderVO> getSellerOrderDetail(@RequestParam Long orderId, HttpServletRequest request) {
        ThrowUtils.throwIf(orderId == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        OrderVO orderVO = orderService.getSellerOrderDetail(orderId, loginUser);
        return ResultUtils.success(orderVO);
    }
}