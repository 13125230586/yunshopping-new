package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单视图对象
 */
@Data
public class OrderVO implements Serializable {

    private Long id;

    private String orderNo;

    private Long shopId;

    private String shopName;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private BigDecimal discountAmount;

    private BigDecimal shippingFee;

    private String paymentMethod;

    private Integer orderStatus;

    private Integer paymentStatus;

    private Integer shippingStatus;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private String buyerMessage;

    private Date payTime;

    private Date shipTime;

    private Date completeTime;

    private Date createTime;

    private List<OrderItemVO> orderItems;

    private static final long serialVersionUID = 1L;
}