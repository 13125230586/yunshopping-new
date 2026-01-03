package com.yunshen.yunshoppingbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.vo.DistributionDataVO;
import com.yunshen.yunshoppingbackend.model.vo.TrendDataVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单数据库操作
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单趋势统计
     */
    @Select("SELECT DATE(createTime) as `date`, COUNT(*) as count " +
            "FROM orders " +
            "WHERE createTime >= DATE_SUB(NOW(), INTERVAL #{days} DAY) " +
            "GROUP BY DATE(createTime) " +
            "ORDER BY `date` ASC")
    List<TrendDataVO> getOrderTrend(@Param("days") Integer days);

    /**
     * 订单状态分布统计
     */
    @Select("SELECT orderStatus as status, COUNT(*) as count " +
            "FROM orders " +
            "GROUP BY orderStatus " +
            "ORDER BY orderStatus ASC")
    List<DistributionDataVO> getOrderStatusDistribution();

    /**
     * 销售额趋势统计
     */
    @Select("SELECT DATE(createTime) as `date`, IFNULL(SUM(totalAmount), 0) as amount " +
            "FROM orders " +
            "WHERE createTime >= DATE_SUB(NOW(), INTERVAL #{days} DAY) " +
            "AND orderStatus IN (3, 4) " +
            "GROUP BY DATE(createTime) " +
            "ORDER BY `date` ASC")
    List<TrendDataVO> getSalesTrend(@Param("days") Integer days);
}