package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author bystander
 * @date 2018/9/15
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long> {
    @Update("update tb_category set is_parent=${isParent} where id=${parentId}")
    int updateCategoryStatus(@Param("parentId") Long parentId, @Param("isParent") boolean isParent);

    void deleteCategory(Category category);

    Category selectNodeInfo(Category category);

    int selectParentInfo(Category categoryInfo);
    @Update("update tb_category set name=$(name) where id=${id}")
    void updateNodeNameById(long id, String name);
}
