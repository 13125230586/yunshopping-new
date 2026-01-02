package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评价视图对象
 */
@Data
public class ReviewVO implements Serializable {

    private Long id;

    private Long userId;

    private String userName;

    private String userAvatar;

    private Long productId;

    private Integer rating;

    private String content;

    private String images;

    private Integer isAnonymous;

    private Integer likeCount;

    private String replyContent;

    private Date replyTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}