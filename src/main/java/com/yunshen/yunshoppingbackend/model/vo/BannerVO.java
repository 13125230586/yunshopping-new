package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图视图对象
 */
@Data
public class BannerVO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 轮播图图片地址
     */
    private String imageUrl;

    /**
     * 链接类型
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
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}