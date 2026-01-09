package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.MemberLevelMapper;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.MemberLevel;
import com.yunshen.yunshoppingbackend.model.vo.MemberLevelVO;
import com.yunshen.yunshoppingbackend.service.MemberLevelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员等级服务实现
 */
@Service
@Slf4j
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

    @Override
    public List<MemberLevelVO> listAllLevels() {
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sortOrder");

        List<MemberLevel> memberLevelList = this.list(queryWrapper);
        return memberLevelList.stream()
                .map(this::getMemberLevelVO)
                .collect(Collectors.toList());
    }

    @Override
    public Long addMemberLevel(MemberLevelAddRequest memberLevelAddRequest) {
        ThrowUtils.throwIf(memberLevelAddRequest == null, ErrorCode.PARAMS_ERROR);

        String levelName = memberLevelAddRequest.getLevelName();
        String levelCode = memberLevelAddRequest.getLevelCode();
        BigDecimal discountRate = memberLevelAddRequest.getDiscountRate();
        Integer requiredGrowth = memberLevelAddRequest.getRequiredGrowth();
        Integer sortOrder = memberLevelAddRequest.getSortOrder();

        ThrowUtils.throwIf(StringUtils.isBlank(levelName), ErrorCode.PARAMS_ERROR, "等级名称不能为空");
        ThrowUtils.throwIf(StringUtils.isBlank(levelCode), ErrorCode.PARAMS_ERROR, "等级代码不能为空");
        ThrowUtils.throwIf(discountRate == null || discountRate.compareTo(BigDecimal.ZERO) <= 0
                || discountRate.compareTo(BigDecimal.ONE) > 0, ErrorCode.PARAMS_ERROR, "折扣率必须在0-1之间");
        ThrowUtils.throwIf(requiredGrowth == null || requiredGrowth < 0, ErrorCode.PARAMS_ERROR, "所需成长值必须大于等于0");
        ThrowUtils.throwIf(sortOrder == null || sortOrder < 0, ErrorCode.PARAMS_ERROR, "排序值必须大于等于0");

        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("levelCode", levelCode);
        long count = this.count(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "等级代码已存在");

        MemberLevel memberLevel = new MemberLevel();
        BeanUtil.copyProperties(memberLevelAddRequest, memberLevel);

        boolean result = this.save(memberLevel);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("新增会员等级 levelName:{} levelCode:{} discountRate:{}", levelName, levelCode, discountRate);
        return memberLevel.getId();
    }

    @Override
    public Boolean updateMemberLevel(MemberLevelUpdateRequest memberLevelUpdateRequest) {
        ThrowUtils.throwIf(memberLevelUpdateRequest == null || memberLevelUpdateRequest.getId() == null,
                ErrorCode.PARAMS_ERROR);

        MemberLevel memberLevel = this.getById(memberLevelUpdateRequest.getId());
        ThrowUtils.throwIf(memberLevel == null, ErrorCode.NOT_FOUND_ERROR);

        String levelName = memberLevelUpdateRequest.getLevelName();
        String levelCode = memberLevelUpdateRequest.getLevelCode();
        BigDecimal discountRate = memberLevelUpdateRequest.getDiscountRate();
        Integer requiredGrowth = memberLevelUpdateRequest.getRequiredGrowth();
        Integer sortOrder = memberLevelUpdateRequest.getSortOrder();

        ThrowUtils.throwIf(StringUtils.isBlank(levelName), ErrorCode.PARAMS_ERROR, "等级名称不能为空");
        ThrowUtils.throwIf(StringUtils.isBlank(levelCode), ErrorCode.PARAMS_ERROR, "等级代码不能为空");
        ThrowUtils.throwIf(discountRate == null || discountRate.compareTo(BigDecimal.ZERO) <= 0
                || discountRate.compareTo(BigDecimal.ONE) > 0, ErrorCode.PARAMS_ERROR, "折扣率必须在0-1之间");
        ThrowUtils.throwIf(requiredGrowth == null || requiredGrowth < 0, ErrorCode.PARAMS_ERROR, "所需成长值必须大于等于0");
        ThrowUtils.throwIf(sortOrder == null || sortOrder < 0, ErrorCode.PARAMS_ERROR, "排序值必须大于等于0");

        if (!memberLevel.getLevelCode().equals(levelCode)) {
            QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("levelCode", levelCode);
            queryWrapper.ne("id", memberLevelUpdateRequest.getId());
            long count = this.count(queryWrapper);
            ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "等级代码已存在");
        }

        BeanUtil.copyProperties(memberLevelUpdateRequest, memberLevel);
        boolean result = this.updateById(memberLevel);

        log.info("修改会员等级 id:{} levelName:{}", memberLevel.getId(), levelName);
        return result;
    }

    @Override
    public Boolean deleteMemberLevel(Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);

        MemberLevel memberLevel = this.getById(id);
        ThrowUtils.throwIf(memberLevel == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = this.removeById(id);
        log.info("删除会员等级 id:{}", id);
        return result;
    }

    @Override
    public MemberLevelVO getMemberLevelVO(MemberLevel memberLevel) {
        if (memberLevel == null) {
            return null;
        }
        MemberLevelVO memberLevelVO = new MemberLevelVO();
        BeanUtil.copyProperties(memberLevel, memberLevelVO);
        return memberLevelVO;
    }

    @Override
    public MemberLevel getLevelByCode(String levelCode) {
        if (StringUtils.isBlank(levelCode)) {
            return null;
        }
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("levelCode", levelCode);
        return this.getOne(queryWrapper);
    }
}