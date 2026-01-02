package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付记录视图对象
 */
@Data
public class PaymentVO implements Serializable {

    private Long id;

    private String paymentNo;

    private Long orderId;

    private String paymentMethod;

    private BigDecimal payAmount;

    private Integer payStatus;

    private String transactionId;

    private Date payTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}