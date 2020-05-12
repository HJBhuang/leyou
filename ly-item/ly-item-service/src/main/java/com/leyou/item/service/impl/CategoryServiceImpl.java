package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/8/0008
 * @time 13:58:45
 */
@Log4j2
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

    @Override
    public void delete(Category category) {
        //TODO
        //1、根据 id先查整个节点的信息
         Category categoryInfo = categoryMapper.selectNodeInfo(category);
        log.info("categoryInfo:"+categoryInfo);


        //2、查询当前节点的父节点下有几个子节点
         int nodeCount = categoryMapper.selectParentInfo(categoryInfo);
        //3、如何该节点是最后一个节点 需要将该节点的父节点的is_parent 更新为false
        if(nodeCount ==1){
            categoryMapper.updateCategoryStatus(categoryInfo.getParentId(),false);
        }
        //4、如果不是最后一个节点 则直接删除该节点
        categoryMapper.deleteCategory(category);
    }
}
