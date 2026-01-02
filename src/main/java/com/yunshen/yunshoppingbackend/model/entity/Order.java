package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体
 */
@TableName(value = "orders")
@Data
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNo;

    private Long userId;

    private Long shopId;

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

    private Date cancelTime;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}