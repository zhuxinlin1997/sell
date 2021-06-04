package com.hand.dao;

import com.hand.entity.ProductInfo;
import com.hand.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {
    @Autowired
    private ProductInfoDAO productInfoDAO;
    @Test
    public void findByCategoryTypeIn() {
        List<ProductInfo> productInfoList = productInfoDAO.findByCategoryTypeIn(Arrays.asList(2,3));
        System.out.println(productInfoList);
    }
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234561");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("好喝的皮蛋粥");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductName("皮蛋粥1");
        productInfo.setProductPrice(new BigDecimal("3.3"));
        productInfo.setProductStock(100);
        ProductInfo result =productInfoDAO.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> productInfoList = productInfoDAO.findByProductStatus(0);
        System.out.println(productInfoList);
        Assert.assertNotNull(productInfoList);
    }
    @Test
    public void findByProductIdInTest(){
        List<ProductInfo> productInfoList = productInfoDAO.findByProductIdIn(Arrays.asList("123456","123457"));
        System.out.println(productInfoList);
    }

    @Test
    public void findByCategoryTypeAndAndProductStatusTest(){
        List<ProductInfo> result = productInfoDAO.findByCategoryTypeAndAndProductStatus(3, ProductStatusEnum.UP.getCode());
        System.out.println(result);
    }
}