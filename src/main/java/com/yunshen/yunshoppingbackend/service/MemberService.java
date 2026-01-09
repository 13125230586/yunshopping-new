package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberActivateRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberGrantRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.Member;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.MemberBenefitLogVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberVO;

import java.util.List;

/**
 * 会员服务
 */
public interface MemberService extends IService<Member> {

    /**
     * 获取当前用户会员信息
     */
    MemberVO getCurrentUserMemberInfo(User loginUser);

    /**
     * 开通会员
     */
    Boolean activateMember(MemberActivateRequest memberActivateRequest, User loginUser);

    /**
     * 查询会员权益发放日志
     */
    List<MemberBenefitLogVO> listBenefitLogs(User loginUser);

    /**
     * 分页查询会员列表
     */
    Page<MemberVO> listMemberVOByPage(MemberQueryRequest memberQueryRequest);

    /**
     * 赠送会员
     */
    Boolean grantMember(MemberGrantRequest memberGrantRequest);

    /**
     * 获取会员VO
     */
    MemberVO getMemberVO(Member member);

    /**
     * 根据用户ID获取会员
     */
    Member getMemberByUserId(Long userId);

    /**
     * 更新会员成长值和等级
     */
    void updateGrowthAndLevel(Long userId, Integer addGrowth);
}