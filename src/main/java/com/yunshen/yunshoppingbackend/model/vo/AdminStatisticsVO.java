package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理后台数据统计VO
 */
@Data
public class AdminStatisticsVO implements Serializable {

    /**
     * 总用户数
     */
    private Long totalUsers;

    /**
     * 总订单数
     */
    private Long totalOrders;

    /**
     * 待审核商品数
     */
    private Long pendingProducts;

    /**
     * 待审核店铺数
     */
    private Long pendingShops;

    private static final long serialVersionUID = 1L;
}