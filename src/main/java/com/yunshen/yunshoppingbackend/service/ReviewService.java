package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.entity.Review;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.ReviewVO;

/**
 * 商品评价服务
 */
public interface ReviewService extends IService<Review> {

    /**
     * 添加评价
     */
    Long addReview(Review review, User loginUser);

    /**
     * 删除评价
     */
    Boolean deleteReview(Long reviewId, User loginUser);

    /**
     * 分页查询商品评价
     */
    Page<ReviewVO> listReviewByProductId(Long productId, int current, int pageSize);

    /**
     * 获取评价VO
     */
    ReviewVO getReviewVO(Review review);
}