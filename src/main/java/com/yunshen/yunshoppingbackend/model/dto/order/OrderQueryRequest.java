package com.yunshen.yunshoppingbackend.model.dto.order;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryRequest extends PageRequest implements Serializable {

    private Long id;

    private String orderNo;

    private Long shopId;

    private Integer orderStatus;

    private Integer paymentStatus;

    private static final long serialVersionUID = 1L;
}