package com.hand.dao;

import com.hand.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
public class ProductCategoryDAOTest {
    @Autowired
    private ProductCategoryDAO productCategoryDAO;
    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(2);
        productCategory.setCategoryName("男生最爱");
        productCategory = productCategoryDAO.save(productCategory);
        Assert.assertNotNull(productCategory);
    }
    @Test
    public void findAll(){
        List<ProductCategory> productCategoryList = productCategoryDAO.findAll();
        System.out.println(productCategoryList);
    }
    @Test
    public void findOne(){
        ProductCategory productCategory = productCategoryDAO.findOne(123);
        System.out.println(productCategory);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> categoryTypeList = Arrays.asList(0,1,3);
        List<ProductCategory> productCategoryList = productCategoryDAO.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}