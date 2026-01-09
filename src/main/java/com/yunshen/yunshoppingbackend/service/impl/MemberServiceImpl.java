package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.constant.MemberConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.MemberBenefitLogMapper;
import com.yunshen.yunshoppingbackend.mapper.MemberMapper;
import com.yunshen.yunshoppingbackend.mapper.UserCouponMapper;
import com.yunshen.yunshoppingbackend.mapper.UserMapper;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberActivateRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberGrantRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberQueryRequest;
import com.yunshen.yunshoppingbackend.model.entity.*;
import com.yunshen.yunshoppingbackend.model.enums.BenefitTypeEnum;
import com.yunshen.yunshoppingbackend.model.enums.MemberStatusEnum;
import com.yunshen.yunshoppingbackend.model.vo.MemberBenefitLogVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberLevelVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberVO;
import com.yunshen.yunshoppingbackend.service.MemberLevelService;
import com.yunshen.yunshoppingbackend.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员服务实现
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberLevelService memberLevelService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserCouponMapper userCouponMapper;

    @Resource
    private MemberBenefitLogMapper memberBenefitLogMapper;

    @Override
    public MemberVO getCurrentUserMemberInfo(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Member member = getMemberByUserId(loginUser.getId());
        if (member == null) {
            return null;
        }

        return getMemberVO(member);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean activateMember(MemberActivateRequest memberActivateRequest, User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(memberActivateRequest == null || memberActivateRequest.getLevelId() == null,
                ErrorCode.PARAMS_ERROR);

        Long levelId = memberActivateRequest.getLevelId();
        MemberLevel memberLevel = memberLevelService.getById(levelId);
        ThrowUtils.throwIf(memberLevel == null, ErrorCode.PARAMS_ERROR, "会员等级不存在");

        Member existMember = getMemberByUserId(loginUser.getId());
        if (existMember != null) {
            if (existMember.getLevelId().equals(levelId)) {
                ThrowUtils.throwIf(true, ErrorCode.OPERATION_ERROR, "已是该等级会员");
            }
            existMember.setLevelId(levelId);
            existMember.setStatus(MemberConstant.STATUS_NORMAL);
            this.updateById(existMember);

            log.info("用户升级会员 userId:{} oldLevelId:{} newLevelId:{}",
                    loginUser.getId(), existMember.getLevelId(), levelId);

            grantBenefits(loginUser.getId(), existMember.getId(), memberLevel, MemberConstant.TRIGGER_EVENT_UPGRADE);
            return true;
        }

        Member member = new Member();
        member.setUserId(loginUser.getId());
        member.setLevelId(levelId);
        member.setGrowthValue(0);
        member.setActivateTime(new Date());
        member.setStatus(MemberConstant.STATUS_NORMAL);
        member.setTotalConsumeAmount(BigDecimal.ZERO);

        boolean result = this.save(member);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        User user = userMapper.selectById(loginUser.getId());
        user.setMemberId(member.getId());
        user.setIsMember(MemberConstant.IS_MEMBER_YES);
        userMapper.updateById(user);

        log.info("开通会员 userId:{} levelId:{}", loginUser.getId(), levelId);

        grantBenefits(loginUser.getId(), member.getId(), memberLevel, MemberConstant.TRIGGER_EVENT_ACTIVATE);

        return true;
    }

    @Override
    public List<MemberBenefitLogVO> listBenefitLogs(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<MemberBenefitLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.orderByDesc("createTime");

        List<MemberBenefitLog> logList = memberBenefitLogMapper.selectList(queryWrapper);
        return logList.stream()
                .map(this::getBenefitLogVO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MemberVO> listMemberVOByPage(MemberQueryRequest memberQueryRequest) {
        int current = memberQueryRequest.getCurrent();
        int pageSize = memberQueryRequest.getPageSize();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(memberQueryRequest.getUserId() != null, "userId", memberQueryRequest.getUserId());
        queryWrapper.eq(memberQueryRequest.getLevelId() != null, "levelId", memberQueryRequest.getLevelId());
        queryWrapper.eq(memberQueryRequest.getStatus() != null, "status", memberQueryRequest.getStatus());
        queryWrapper.orderByDesc("createTime");

        Page<Member> memberPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<MemberVO> memberVOPage = new Page<>(current, pageSize);
        memberVOPage.setTotal(memberPage.getTotal());

        List<MemberVO> memberVOList = memberPage.getRecords().stream()
                .map(this::getMemberVO)
                .collect(Collectors.toList());
        memberVOPage.setRecords(memberVOList);

        return memberVOPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantMember(MemberGrantRequest memberGrantRequest) {
        ThrowUtils.throwIf(memberGrantRequest == null || memberGrantRequest.getUserId() == null
                || memberGrantRequest.getLevelId() == null, ErrorCode.PARAMS_ERROR);

        Long userId = memberGrantRequest.getUserId();
        Long levelId = memberGrantRequest.getLevelId();
        Integer validDays = memberGrantRequest.getValidDays();

        User user = userMapper.selectById(userId);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR, "用户不存在");

        MemberLevel memberLevel = memberLevelService.getById(levelId);
        ThrowUtils.throwIf(memberLevel == null, ErrorCode.PARAMS_ERROR, "会员等级不存在");

        Member existMember = getMemberByUserId(userId);
        if (existMember != null) {
            existMember.setLevelId(levelId);
            existMember.setStatus(MemberConstant.STATUS_NORMAL);
            if (validDays != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, validDays);
                existMember.setExpireTime(calendar.getTime());
            } else {
                existMember.setExpireTime(null);
            }
            this.updateById(existMember);

            log.info("赠送会员 userId:{} levelId:{} validDays:{}", userId, levelId, validDays);
            grantBenefits(userId, existMember.getId(), memberLevel, MemberConstant.TRIGGER_EVENT_ACTIVATE);
            return true;
        }

        Member member = new Member();
        member.setUserId(userId);
        member.setLevelId(levelId);
        member.setGrowthValue(0);
        member.setActivateTime(new Date());
        member.setStatus(MemberConstant.STATUS_NORMAL);
        member.setTotalConsumeAmount(BigDecimal.ZERO);

        if (validDays != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, validDays);
            member.setExpireTime(calendar.getTime());
        }

        boolean result = this.save(member);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        user.setMemberId(member.getId());
        user.setIsMember(MemberConstant.IS_MEMBER_YES);
        userMapper.updateById(user);

        log.info("赠送会员 userId:{} levelId:{} validDays:{}", userId, levelId, validDays);
        grantBenefits(userId, member.getId(), memberLevel, MemberConstant.TRIGGER_EVENT_ACTIVATE);

        return true;
    }

    @Override
    public MemberVO getMemberVO(Member member) {
        if (member == null) {
            return null;
        }
        MemberVO memberVO = new MemberVO();
        BeanUtil.copyProperties(member, memberVO);

        User user = userMapper.selectById(member.getUserId());
        if (user != null) {
            memberVO.setUserName(user.getUserName());
            memberVO.setUserAvatar(user.getUserAvatar());
        }

        MemberLevel memberLevel = memberLevelService.getById(member.getLevelId());
        if (memberLevel != null) {
            memberVO.setLevelInfo(memberLevelService.getMemberLevelVO(memberLevel));
        }

        MemberStatusEnum statusEnum = MemberStatusEnum.getEnumByValue(member.getStatus());
        if (statusEnum != null) {
            memberVO.setStatusText(statusEnum.getText());
        }

        return memberVO;
    }

    @Override
    public Member getMemberByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGrowthAndLevel(Long userId, Integer addGrowth) {
        if (userId == null || addGrowth == null || addGrowth <= 0) {
            return;
        }

        Member member = getMemberByUserId(userId);
        if (member == null) {
            return;
        }

        int newGrowth = member.getGrowthValue() + addGrowth;
        member.setGrowthValue(newGrowth);

        List<MemberLevel> allLevels = memberLevelService.list(new QueryWrapper<MemberLevel>()
                .orderByDesc("requiredGrowth"));

        for (MemberLevel level : allLevels) {
            if (newGrowth >= level.getRequiredGrowth()) {
                if (!member.getLevelId().equals(level.getId())) {
                    member.setLevelId(level.getId());
                    log.info("用户自动升级会员等级 userId:{} newLevelId:{} growthValue:{}",
                            userId, level.getId(), newGrowth);
                }
                break;
            }
        }

        this.updateById(member);
    }

    /**
     * 发放会员权益
     */
    private void grantBenefits(Long userId, Long memberId, MemberLevel memberLevel, String triggerEvent) {
        if (memberLevel.getWelcomeCouponId() != null) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUserId(userId);
            userCoupon.setCouponId(memberLevel.getWelcomeCouponId());
            userCoupon.setStatus(0);
            userCoupon.setReceiveTime(new Date());
            userCouponMapper.insert(userCoupon);

            MemberBenefitLog benefitLog = new MemberBenefitLog();
            benefitLog.setUserId(userId);
            benefitLog.setMemberId(memberId);
            benefitLog.setBenefitType(MemberConstant.BENEFIT_TYPE_COUPON);
            benefitLog.setBenefitValue(String.valueOf(memberLevel.getWelcomeCouponId()));
            benefitLog.setTriggerEvent(triggerEvent);
            benefitLog.setDescription("赠送会员专属优惠券");
            memberBenefitLogMapper.insert(benefitLog);

            log.info("发放会员优惠券 userId:{} couponId:{}", userId, memberLevel.getWelcomeCouponId());
        }
    }

    /**
     * 获取权益日志VO
     */
    private MemberBenefitLogVO getBenefitLogVO(MemberBenefitLog log) {
        if (log == null) {
            return null;
        }
        MemberBenefitLogVO logVO = new MemberBenefitLogVO();
        BeanUtil.copyProperties(log, logVO);

        BenefitTypeEnum typeEnum = BenefitTypeEnum.getEnumByValue(log.getBenefitType());
        if (typeEnum != null) {
            logVO.setBenefitTypeText(typeEnum.getText());
        }

        switch (log.getTriggerEvent()) {
            case MemberConstant.TRIGGER_EVENT_ACTIVATE:
                logVO.setTriggerEventText("开通会员");
                break;
            case MemberConstant.TRIGGER_EVENT_UPGRADE:
                logVO.setTriggerEventText("升级会员");
                break;
            case MemberConstant.TRIGGER_EVENT_BIRTHDAY:
                logVO.setTriggerEventText("生日礼包");
                break;
            default:
                logVO.setTriggerEventText(log.getTriggerEvent());
        }

        return logVO;
    }
}