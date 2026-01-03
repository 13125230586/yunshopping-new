package com.yunshen.yunshoppingbackend.model.dto.banner;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 轮播图查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerQueryRequest extends PageRequest implements Serializable {

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}