package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流实体
 */
@TableName(value = "logistics")
@Data
public class Logistics implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long orderId;

    private String logisticsCompany;

    private String trackingNumber;

    private Integer logisticsStatus;

    private String currentLocation;

    private String logisticsInfo;

    private Date shipTime;

    private Date receiveTime;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}