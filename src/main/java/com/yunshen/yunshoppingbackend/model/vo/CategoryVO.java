package com.yunshen.yunshoppingbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分类视图对象
 */
@Data
public class CategoryVO implements Serializable {

    private Long id;

    private String categoryName;

    private Long parentId;

    private Integer level;

    private Integer sortOrder;

    private String icon;

    private List<CategoryVO> children;

    private static final long serialVersionUID = 1L;
}