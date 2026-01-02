package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.entity.Address;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.AddressVO;
import com.yunshen.yunshoppingbackend.service.AddressService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收货地址接口
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Resource
    private AddressService addressService;

    @Resource
    private UserService userService;

    /**
     * 添加收货地址
     */
    @PostMapping("/add")
    public BaseResponse<Long> addAddress(@RequestBody Address address, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long addressId = addressService.addAddress(address, loginUser);
        return ResultUtils.success(addressId);
    }


    /**
     * 更新收货地址
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateAddress(@RequestBody Address address, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = addressService.updateAddress(address, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 删除收货地址
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteAddress(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = addressService.deleteAddress(deleteRequest.getId(), loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 设置默认地址
     */
    @PostMapping("/setDefault")
    public BaseResponse<Boolean> setDefaultAddress(@RequestParam Long addressId, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean result = addressService.setDefaultAddress(addressId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取用户地址列表
     */
    @GetMapping("/list")
    public BaseResponse<List<AddressVO>> getAddressList(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<AddressVO> addressList = addressService.getUserAddressList(loginUser);
        return ResultUtils.success(addressList);
    }
}