package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片上传返回结果
 */
@Data
public class UploadPictureResult implements Serializable {

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 图片宽度
     */
    private Integer picWidth;

    /**
     * 图片高度
     */
    private Integer picHeight;

    /**
     * 图片宽高比
     */
    private Double picScale;

    /**
     * 图片格式
     */
    private String picFormat;

    /**
     * 图片大小（字节）
     */
    private Long picSize;

    /**
     * 图片主色调
     */
    private String picColor;

    /**
     * 图片访问URL
     */
    private String url;

    /**
     * 缩略图URL
     */
    private String thumbnailUrl;

    private static final long serialVersionUID = 1L;
}