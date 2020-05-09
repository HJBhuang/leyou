package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.stereotype.Service;

/**
 * @author huangjiabao
 * @data 2020/5/8/0008
 * @time 14:58:25
 */
@Service
public interface BrandService {
    PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
