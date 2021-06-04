package com.hand.service.impl;

import com.hand.dto.CartDTO;
import com.hand.entity.ProductInfo;
import com.hand.enums.ProductStatusEnum;
import com.hand.service.ProductService;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("ds");
        System.out.println(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> page = productService.findAll(pageRequest);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void findAllUp() {
        List<ProductInfo> productInfoList = productService.findAllUp();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123458");
        productInfo.setCategoryType(0);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductDescription("好吃的红烧肉");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductName("红烧肉");
        productInfo.setProductPrice(new BigDecimal("18.5"));
        productInfo.setProductStock(100);
        ProductInfo result = productService.save(productInfo);
    }
    @Test
    public void decreaseStockTest(){
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(new CartDTO("123456",1));
        cartDTOList.add(new CartDTO("123457",2));
        productService.decreaseStock(cartDTOList);
    }
    @Test
    public void increaseStockTest(){
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(new CartDTO("123456",1));
        cartDTOList.add(new CartDTO("123457",2));
        productService.increaseStock(cartDTOList);
    }
}