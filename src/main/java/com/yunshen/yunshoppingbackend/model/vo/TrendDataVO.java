package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 趋势数据VO
 */
@Data
public class TrendDataVO implements Serializable {

    private String date;

    private Long count;

    private BigDecimal amount;

    private static final long serialVersionUID = 1L;
}