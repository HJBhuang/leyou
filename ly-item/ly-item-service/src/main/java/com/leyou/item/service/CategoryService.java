package com.leyou.item.service;

import com.leyou.item.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/8/0008
 * @time 13:58:11
 */
@Service
public interface CategoryService {
    List<Category> queryCategoryListByParentId(Long pid);

    int add(Category category);

    int modifyStatus(Long parentId, boolean b);

    void delete(Category category);
}
