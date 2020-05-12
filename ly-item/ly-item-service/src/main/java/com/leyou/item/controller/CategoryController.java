package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/8/0008
 * @time 13:52:50
 */
@Transactional
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据parentId查询类目
     *
     * @param pid
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        try {
            if (pid == null || pid.longValue() < 0) {
                // pid为null或者小于等于0，响应400
                return ResponseEntity.badRequest().build();
            }
            // 执行查询操作
            List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);
            if (CollectionUtils.isEmpty(categoryList)) {
                // 返回结果集为空，响应404
                return ResponseEntity.notFound().build();
            }
            // 响应200
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping("add")
    public ResponseEntity<Category> saveCategory(
            @RequestParam(value = "id", defaultValue = "0") Long pid,
            @RequestParam(value = "name" ,defaultValue="华为") String name,
            @RequestParam(value = "parentId",defaultValue = "1") Long parentId,
            @RequestParam(value = "isParent",defaultValue = "false") Boolean isParent,
            @RequestParam(value = "sort",defaultValue = "1") Integer sort) {
        Category category = new Category();
        try {
            category.setId(pid);
            category.setName(name);
            category.setParentId(parentId);
            category.setIsParent(isParent);
            category.setSort(sort);

            final int row = this.categoryService.add(category);
         //   this.categoryService.modifyStatus(parentId,true);
            if(row<1){
                //响应400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            // 响应200
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseEntity.ok(category);
    }

    @GetMapping("delete")
    public void delete(
            @RequestParam(value = "id", defaultValue = "-1") Long id) {
        Category category = new Category();
        try {
            category.setId(id);
            categoryService.delete(category);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("edit")
    public void edit(@RequestParam(value = "id") long id,
                     @RequestParam(value = "name") String name){
        categoryService.editNodeInfo(id,name);
    }
}
