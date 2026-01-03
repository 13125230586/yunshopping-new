package com.yunshen.yunshoppingbackend.model.dto.banner;

import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图新增请求
 */
@Data
public class BannerAddRequest implements Serializable {

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 轮播图图片地址
     */
    private String imageUrl;

    /**
     * 链接类型: category、product、shop、url
     */
    private String linkType;

    /**
     * 链接值
     */
    private String linkValue;

    /**
     * 排序字段
     */
    private Integer sortOrder;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}