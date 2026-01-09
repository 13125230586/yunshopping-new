package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.MemberLevel;
import com.yunshen.yunshoppingbackend.model.vo.MemberLevelVO;

import java.util.List;

/**
 * 会员等级服务
 */
public interface MemberLevelService extends IService<MemberLevel> {

    /**
     * 获取所有会员等级列表
     */
    List<MemberLevelVO> listAllLevels();

    /**
     * 新增会员等级
     */
    Long addMemberLevel(MemberLevelAddRequest memberLevelAddRequest);

    /**
     * 修改会员等级
     */
    Boolean updateMemberLevel(MemberLevelUpdateRequest memberLevelUpdateRequest);

    /**
     * 删除会员等级
     */
    Boolean deleteMemberLevel(Long id);

    /**
     * 获取会员等级VO
     */
    MemberLevelVO getMemberLevelVO(MemberLevel memberLevel);

    /**
     * 根据等级代码获取等级
     */
    MemberLevel getLevelByCode(String levelCode);
}