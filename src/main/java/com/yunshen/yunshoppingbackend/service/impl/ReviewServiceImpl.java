package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.ReviewMapper;
import com.yunshen.yunshoppingbackend.mapper.UserMapper;
import com.yunshen.yunshoppingbackend.model.entity.Review;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ReviewVO;
import com.yunshen.yunshoppingbackend.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品评价服务实现
 */
@Service
@Slf4j
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long addReview(Review review, User loginUser) {
        ThrowUtils.throwIf(review == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        review.setUserId(loginUser.getId());

        boolean result = this.save(review);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("添加评价 userId:{} productId:{} reviewId:{}", loginUser.getId(), review.getProductId(), review.getId());
        return review.getId();
    }

    @Override
    public Boolean deleteReview(Long reviewId, User loginUser) {
        ThrowUtils.throwIf(reviewId == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        Review review = this.getById(reviewId);
        ThrowUtils.throwIf(review == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!review.getUserId().equals(loginUser.getId()), ErrorCode.NO_AUTH_ERROR);

        boolean result = this.removeById(reviewId);
        log.info("删除评价 reviewId:{} userId:{}", reviewId, loginUser.getId());
        return result;
    }

    @Override
    public Page<ReviewVO> listReviewByProductId(Long productId, int current, int pageSize) {
        ThrowUtils.throwIf(productId == null, ErrorCode.PARAMS_ERROR);

        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productId", productId);
        queryWrapper.orderByDesc("createTime");

        Page<Review> reviewPage = this.page(new Page<>(current, pageSize), queryWrapper);
        Page<ReviewVO> reviewVOPage = new Page<>(current, pageSize, reviewPage.getTotal());

        List<ReviewVO> reviewVOList = reviewPage.getRecords().stream()
                .map(this::getReviewVO)
                .collect(Collectors.toList());
        reviewVOPage.setRecords(reviewVOList);

        return reviewVOPage;
    }

    @Override
    public ReviewVO getReviewVO(Review review) {
        if (review == null) {
            return null;
        }
        ReviewVO reviewVO = new ReviewVO();
        BeanUtil.copyProperties(review, reviewVO);

        User user = userMapper.selectById(review.getUserId());
        if (user != null) {
            reviewVO.setUserName(user.getUserName());
            reviewVO.setUserAvatar(user.getUserAvatar());
        }

        return reviewVO;
    }
}