package com.yunshen.yunshoppingbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图实体
 */
@TableName(value = "banner")
@Data
public class Banner implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
     * 链接类型: category(分类)、product(商品)、shop(店铺)、url(外链)
     */
    private String linkType;

    /**
     * 链接值:分类ID、商品ID、店铺ID或完整URL
     */
    private String linkValue;

    /**
     * 排序字段,数字越小越靠前
     */
    private Integer sortOrder;

    /**
     * 状态: 0-禁用 1-启用
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

    /**
     * 逻辑删除: 0-未删除 1-已删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}