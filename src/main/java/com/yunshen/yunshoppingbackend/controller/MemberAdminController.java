package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberGrantRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelAddRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberLevelUpdateRequest;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberQueryRequest;
import com.yunshen.yunshoppingbackend.model.vo.MemberLevelVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberVO;
import com.yunshen.yunshoppingbackend.service.MemberLevelService;
import com.yunshen.yunshoppingbackend.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员管理接口（管理员端）
 */
@RestController
@RequestMapping("/admin/member")
@Slf4j
public class MemberAdminController {

    @Resource
    private MemberLevelService memberLevelService;

    @Resource
    private MemberService memberService;

    /**
     * 新增会员等级
     */
    @PostMapping("/level/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addMemberLevel(@RequestBody MemberLevelAddRequest memberLevelAddRequest) {
        ThrowUtils.throwIf(memberLevelAddRequest == null, ErrorCode.PARAMS_ERROR);
        Long levelId = memberLevelService.addMemberLevel(memberLevelAddRequest);
        return ResultUtils.success(levelId);
    }

    /**
     * 修改会员等级
     */
    @PostMapping("/level/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateMemberLevel(@RequestBody MemberLevelUpdateRequest memberLevelUpdateRequest) {
        ThrowUtils.throwIf(memberLevelUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Boolean result = memberLevelService.updateMemberLevel(memberLevelUpdateRequest);
        return ResultUtils.success(result);
    }

    /**
     * 删除会员等级
     */
    @PostMapping("/level/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteMemberLevel(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        Boolean result = memberLevelService.deleteMemberLevel(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * 获取所有会员等级列表
     */
    @GetMapping("/level/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<MemberLevelVO>> listAllLevels() {
        List<MemberLevelVO> levelList = memberLevelService.listAllLevels();
        return ResultUtils.success(levelList);
    }

    /**
     * 分页查询会员列表
     */
    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<MemberVO>> listMemberByPage(@RequestBody MemberQueryRequest memberQueryRequest) {
        ThrowUtils.throwIf(memberQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Page<MemberVO> memberPage = memberService.listMemberVOByPage(memberQueryRequest);
        return ResultUtils.success(memberPage);
    }

    /**
     * 赠送会员
     */
    @PostMapping("/grant")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> grantMember(@RequestBody MemberGrantRequest memberGrantRequest) {
        ThrowUtils.throwIf(memberGrantRequest == null, ErrorCode.PARAMS_ERROR);
        Boolean result = memberService.grantMember(memberGrantRequest);
        return ResultUtils.success(result);
    }
}