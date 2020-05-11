package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/8/0008
 * @time 13:58:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> queryCategoryListByParentId(Long pid) {
        Category category= new Category();
        category.setParentId(pid);
        return this.categoryMapper.select(category);
    }

    @Override
    public int add(Category category) {
        this.categoryMapper.insert(category);
        return this.categoryMapper.updateCategoryStatus(category.getParentId(),true);
    }

    @Override
    public int modifyStatus(Long parentId, boolean isParent) {
        return this.categoryMapper.updateCategoryStatus(parentId,isParent);
    }
}
