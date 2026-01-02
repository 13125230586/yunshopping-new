package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.entity.Address;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.AddressVO;

import java.util.List;

/**
 * 收货地址服务
 */
public interface AddressService extends IService<Address> {

    /**
     * 添加收货地址
     */
    Long addAddress(Address address, User loginUser);

    /**
     * 更新收货地址
     */
    Boolean updateAddress(Address address, User loginUser);

    /**
     * 删除收货地址
     */
    Boolean deleteAddress(Long addressId, User loginUser);

    /**
     * 设置默认地址
     */
    Boolean setDefaultAddress(Long addressId, User loginUser);

    /**
     * 获取用户地址列表
     */
    List<AddressVO> getUserAddressList(User loginUser);

    /**
     * 获取地址VO
     */
    AddressVO getAddressVO(Address address);
}