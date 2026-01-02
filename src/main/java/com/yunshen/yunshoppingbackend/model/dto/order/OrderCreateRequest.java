package com.yunshen.yunshoppingbackend.model.dto.order;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单创建请求
 */
@Data
public class OrderCreateRequest implements Serializable {

    private Long shopId;

    private List<Long> cartIds;

    private Long addressId;

    private String buyerMessage;

    private Long couponId;

    private static final long serialVersionUID = 1L;
}