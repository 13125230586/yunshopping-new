package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分布数据VO
 */
@Data
public class DistributionDataVO implements Serializable {

    private Integer status;

    private String name;

    private Long count;

    private static final long serialVersionUID = 1L;
}