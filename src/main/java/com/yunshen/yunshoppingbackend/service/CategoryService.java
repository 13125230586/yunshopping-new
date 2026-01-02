package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.entity.Category;
import com.yunshen.yunshoppingbackend.model.vo.CategoryVO;

import java.util.List;

/**
 * 商品分类服务
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取分类树
     */
    List<CategoryVO> getCategoryTree();

    /**
     * 添加分类
     */
    Long addCategory(Category category);

    /**
     * 更新分类
     */
    Boolean updateCategory(Category category);

    /**
     * 删除分类
     */
    Boolean deleteCategory(Long categoryId);

    /**
     * 获取分类VO
     */
    CategoryVO getCategoryVO(Category category);
}