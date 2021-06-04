package com.hand.mapper;

import com.hand.entity.ProductCategory;
import com.hand.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","新林最爱");
        map.put("categoryType",7);
        Integer result = mapper.insertByMap(map);
        Assert.assertEquals(1,result.intValue());
    }

    @Test
    public void insertByObject() {
        Integer result = mapper.insertByObject(new ProductCategory("新林最不爱", 8));
        Assert.assertEquals(1,result.intValue());
    }
    @Test
    public void selectByCategoryTypeTest(){
        ProductCategory productCategory = mapper.selectByCategoryType(4);
        System.out.println(productCategory);
    }
    @Test
    public void selectBycategoryNameTest(){
        List<ProductCategory> productCategoryList = mapper.selectBycategoryName("测试");
        System.out.println(JsonUtil.toJson(productCategoryList));
    }

    @Test
    public void selectByCategoryTypeAndCategoryNameTest(){
        ProductCategory productCategory = mapper.selectBycategoryNameAndCategoryType("测试",4);
        System.out.println(productCategory);
    }

    @Test
    public void selectByObjectTest(){
        ProductCategory productCategory = mapper.selectByObject(new ProductCategory("新林最不爱", 8));
        System.out.println(productCategory);
    }
    @Test
    public void updateByObjectTest(){
        Integer result = mapper.updateByObject(new ProductCategory("新林最不爱的分类", 8));
        System.out.println(result);
    }
    @Test
    public void updateByMapTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","新林最不爱");
        map.put("categoryType",8);
        Integer result = mapper.updateByMap(map);
        System.out.println(result);
    }
    @Test
    public void updateCategoryNameByCategoryTypeTest(){
        Integer result = mapper.updateCategoryNameByCategoryType("新林最不爱的分类", 8);
        System.out.println(result);
    }
    @Test
    public void deleteByCategoryTypeTest(){
        Integer result = mapper.deleteByCategoryType(7);
        System.out.println(result);
    }
}