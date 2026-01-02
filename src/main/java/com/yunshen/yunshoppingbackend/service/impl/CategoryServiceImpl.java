package com.yunshen.yunshoppingbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.exception.ThrowUtils;
import com.yunshen.yunshoppingbackend.mapper.CategoryMapper;
import com.yunshen.yunshoppingbackend.model.entity.Category;
import com.yunshen.yunshoppingbackend.model.vo.CategoryVO;
import com.yunshen.yunshoppingbackend.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类服务实现
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryVO> getCategoryTree() {
        QueryWrapper<QueryWrapper> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sortOrder");
        List<Category> allCategories = this.list();

        List<CategoryVO> rootCategories = allCategories.stream()
                .filter(category -> category.getParentId() == 0)
                .map(this::getCategoryVO)
                .collect(Collectors.toList());

        for (CategoryVO root : rootCategories) {
            buildCategoryTree(root, allCategories);
        }

        return rootCategories;
    }

    private void buildCategoryTree(CategoryVO parent, List<Category> allCategories) {
        List<CategoryVO> children = allCategories.stream()
                .filter(category -> category.getParentId().equals(parent.getId()))
                .map(this::getCategoryVO)
                .collect(Collectors.toList());

        parent.setChildren(children);

        for (CategoryVO child : children) {
            buildCategoryTree(child, allCategories);
        }
    }

    @Override
    public Long addCategory(Category category) {
        ThrowUtils.throwIf(category == null, ErrorCode.PARAMS_ERROR);

        if (category.getParentId() == null) {
            category.setParentId(0L);
        }

        if (category.getSortOrder() == null) {
            category.setSortOrder(0);
        }

        boolean result = this.save(category);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        log.info("添加分类 categoryId:{} categoryName:{}", category.getId(), category.getCategoryName());
        return category.getId();
    }

    @Override
    public Boolean updateCategory(Category category) {
        ThrowUtils.throwIf(category == null || category.getId() == null, ErrorCode.PARAMS_ERROR);

        Category existCategory = this.getById(category.getId());
        ThrowUtils.throwIf(existCategory == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = this.updateById(category);
        log.info("更新分类 categoryId:{} categoryName:{}", category.getId(), category.getCategoryName());
        return result;
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        ThrowUtils.throwIf(categoryId == null, ErrorCode.PARAMS_ERROR);

        Category category = this.getById(categoryId);
        ThrowUtils.throwIf(category == null, ErrorCode.NOT_FOUND_ERROR);

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", categoryId);
        long count = this.count(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.OPERATION_ERROR, "存在子分类无法删除");

        boolean result = this.removeById(categoryId);
        log.info("删除分类 categoryId:{}", categoryId);
        return result;
    }

    @Override
    public CategoryVO getCategoryVO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryVO categoryVO = new CategoryVO();
        BeanUtil.copyProperties(category, categoryVO);
        categoryVO.setChildren(new ArrayList<>());
        return categoryVO;
    }
}