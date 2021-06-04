package com.hand.service;

import com.hand.entity.ProductCategory;
import org.hibernate.sql.Delete;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/22
 */
public interface CategoryService {
    ProductCategory findOne(Integer catetoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
