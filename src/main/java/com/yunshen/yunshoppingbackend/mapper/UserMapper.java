package com.yunshen.yunshoppingbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.DistributionDataVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据库操作
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户角色分布统计
     */
    @Select("SELECT userRole as name, COUNT(*) as count " +
            "FROM user " +
            "WHERE isDelete = 0 " +
            "GROUP BY userRole " +
            "ORDER BY count DESC")
    List<DistributionDataVO> getUserRoleDistribution();
}