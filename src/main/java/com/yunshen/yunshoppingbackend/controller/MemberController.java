package com.yunshen.yunshoppingbackend.controller;

import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.dto.member.MemberActivateRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.MemberBenefitLogVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberLevelVO;
import com.yunshen.yunshoppingbackend.model.vo.MemberVO;
import com.yunshen.yunshoppingbackend.service.MemberLevelService;
import com.yunshen.yunshoppingbackend.service.MemberService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 会员接口（用户端）
 */
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private MemberLevelService memberLevelService;

    @Resource
    private UserService userService;

    /**
     * 获取当前用户会员信息
     */
    @GetMapping("/info")
    public BaseResponse<MemberVO> getMemberInfo(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        MemberVO memberVO = memberService.getCurrentUserMemberInfo(loginUser);
        return ResultUtils.success(memberVO);
    }

    /**
     * 获取所有会员等级列表
     */
    @GetMapping("/levels")
    public BaseResponse<List<MemberLevelVO>> getAllLevels() {
        List<MemberLevelVO> levelList = memberLevelService.listAllLevels();
        return ResultUtils.success(levelList);
    }

    /**
     * 开通/升级会员
     */
    @PostMapping("/activate")
    public BaseResponse<Boolean> activateMember(@RequestBody MemberActivateRequest memberActivateRequest,
                                                  HttpServletRequest request) {
        ThrowUtils.throwIf(memberActivateRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        Boolean result = memberService.activateMember(memberActivateRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 查询会员权益领取记录
     */
    @GetMapping("/benefits/log")
    public BaseResponse<List<MemberBenefitLogVO>> getBenefitLogs(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<MemberBenefitLogVO> logList = memberService.listBenefitLogs(loginUser);
        return ResultUtils.success(logList);
    }
}