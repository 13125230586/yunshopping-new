package com.yunshen.yunshoppingbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.vo.DistributionDataVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 店铺数据库操作
 */
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 店铺等级分布统计
     */
    @Select("SELECT shopLevel as status, COUNT(*) as count " +
            "FROM shop " +
            "WHERE shopStatus = 1 AND isDelete = 0 " +
            "GROUP BY shopLevel " +
            "ORDER BY shopLevel ASC")
    List<DistributionDataVO> getShopLevelDistribution();
}