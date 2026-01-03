package com.yunshen.yunshoppingbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.vo.DistributionDataVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品数据库操作
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 商品分类分布统计
     */
    @Select("SELECT p.categoryId as status, c.categoryName as name, COUNT(p.id) as count " +
            "FROM product p " +
            "LEFT JOIN category c ON p.categoryId = c.id " +
            "WHERE p.status = 1 AND p.reviewStatus = 1 AND p.isDelete = 0 " +
            "GROUP BY p.categoryId, c.categoryName " +
            "ORDER BY count DESC " +
            "LIMIT 10")
    List<DistributionDataVO> getProductCategoryDistribution();
}