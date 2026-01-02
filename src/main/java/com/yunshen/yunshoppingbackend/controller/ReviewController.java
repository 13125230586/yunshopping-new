package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.model.entity.Review;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ReviewVO;
import com.yunshen.yunshoppingbackend.service.ReviewService;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 商品评价接口
 */
@RestController
@RequestMapping("/review")
@Slf4j
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    @Resource
    private UserService userService;

    /**
     * 添加评价
     */
    @PostMapping("/add")
    public BaseResponse<Long> addReview(@RequestBody Review review, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        long reviewId = reviewService.addReview(review, loginUser);
        return ResultUtils.success(reviewId);
    }

    /**
     * 删除评价
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteReview(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = reviewService.deleteReview(deleteRequest.getId(), loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 分页查询商品评价
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<ReviewVO>> listReviewByPage(@RequestParam Long productId,
                                                          @RequestParam(defaultValue = "1") int current,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        Page<ReviewVO> reviewVOPage = reviewService.listReviewByProductId(productId, current, pageSize);
        return ResultUtils.success(reviewVOPage);
    }
}