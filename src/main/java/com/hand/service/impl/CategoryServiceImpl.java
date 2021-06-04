package com.hand.service.impl;

import com.hand.dao.ProductCategoryDAO;
import com.hand.entity.ProductCategory;
import com.hand.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/22
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;
    @Override
    public ProductCategory findOne(Integer catetoryId) {
        return productCategoryDAO.findOne(catetoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDAO.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDAO.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }
}
