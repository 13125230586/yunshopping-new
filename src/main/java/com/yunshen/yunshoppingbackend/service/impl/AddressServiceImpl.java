package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.AddressMapper;
import com.yunshen.yunshoppingbackend.model.entity.Address;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.AddressVO;
import com.yunshen.yunshoppingbackend.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 收货地址服务实现
 */
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public Long addAddress(Address address, User loginUser) {
        ThrowUtils.throwIf(address == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        address.setUserId(loginUser.getId());

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        long count = this.count(queryWrapper);
        if (count == 0) {
            address.setIsDefault(1);
        } else if (address.getIsDefault() == null) {
            address.setIsDefault(0);
        }

        boolean result = this.save(address);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        if (address.getIsDefault() == 1 && count > 0) {
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userId", loginUser.getId());
            updateWrapper.ne("id", address.getId());
            updateWrapper.set("isDefault", 0);
            this.update(updateWrapper);
        }

        log.info("添加收货地址 userId:{} addressId:{}", loginUser.getId(), address.getId());
        return address.getId();
    }

    @Override
    public Boolean updateAddress(Address address, User loginUser) {
        ThrowUtils.throwIf(address == null || address.getId() == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Address existAddress = this.getById(address.getId());
        ThrowUtils.throwIf(existAddress == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!existAddress.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        boolean result = this.updateById(address);

        if (address.getIsDefault() == 1) {
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userId", loginUser.getId());
            updateWrapper.ne("id", address.getId());
            updateWrapper.set("isDefault", 0);
            this.update(updateWrapper);
        }

        log.info("更新收货地址 userId:{} addressId:{}", loginUser.getId(), address.getId());
        return result;
    }

    @Override
    public Boolean deleteAddress(Long addressId, User loginUser) {
        ThrowUtils.throwIf(addressId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Address address = this.getById(addressId);
        ThrowUtils.throwIf(address == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!address.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        boolean result = this.removeById(addressId);
        log.info("删除收货地址 userId:{} addressId:{}", loginUser.getId(), addressId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setDefaultAddress(Long addressId, User loginUser) {
        ThrowUtils.throwIf(addressId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Address address = this.getById(addressId);
        ThrowUtils.throwIf(address == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!address.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId", loginUser.getId());
        updateWrapper.set("isDefault", 0);
        this.update(updateWrapper);

        address.setIsDefault(1);
        boolean result = this.updateById(address);
        log.info("设置默认地址 userId:{} addressId:{}", loginUser.getId(), addressId);
        return result;
    }

    @Override
    public List<AddressVO> getUserAddressList(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.orderByDesc("isDefault", "createTime");

        List<Address> addressList = this.list(queryWrapper);
        return addressList.stream()
                .map(this::getAddressVO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressVO getAddressVO(Address address) {
        if (address == null) {
            return null;
        }
        AddressVO addressVO = new AddressVO();
        BeanUtil.copyProperties(address, addressVO);
        return addressVO;
    }
}