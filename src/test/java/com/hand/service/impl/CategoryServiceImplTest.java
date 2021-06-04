package com.hand.service.impl;

import com.hand.entity.ProductCategory;
import com.hand.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(123);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertEquals(3,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertNotNull(result);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("测试",3);
        productCategory = categoryService.save(productCategory);
        System.out.println(productCategory.getCategoryId());
    }
}