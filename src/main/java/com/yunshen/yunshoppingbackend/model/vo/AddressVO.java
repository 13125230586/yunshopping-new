package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 收货地址视图对象
 */
@Data
public class AddressVO implements Serializable {

    private Long id;

    private String receiverName;

    private String receiverPhone;

    private String province;

    private String city;

    private String district;

    private String detailAddress;

    private String postalCode;

    private Integer isDefault;

    private static final long serialVersionUID = 1L;
}