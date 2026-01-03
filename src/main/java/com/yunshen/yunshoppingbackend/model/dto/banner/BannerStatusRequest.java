package com.yunshen.yunshoppingbackend.model.dto.banner;

import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图状态修改请求
 */
@Data
public class BannerStatusRequest implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}