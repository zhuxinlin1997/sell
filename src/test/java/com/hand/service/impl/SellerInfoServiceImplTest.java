package com.hand.service.impl;

import com.hand.entity.SellerInfo;
import com.hand.service.SellerInfoService;
import com.hand.utils.JsonUtil;
import com.hand.utils.KeyUtil;
import com.hand.utils.MD5Util;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Test
    public void findSellerByUsername() {
        SellerInfo sellerInfo = sellerInfoService.findSellerByUsername("15084911046");
        System.out.println(sellerInfo);
    }

    @Test
    public void saveSeller() {
        try {
            SellerInfo sellerInfo = new SellerInfo();
            sellerInfo.setSellerId(KeyUtil.genUniqueKey());
            sellerInfo.setNote("测试");
            sellerInfo.setUsername("admin");
            sellerInfo.setPassword(MD5Util.getMD5Code("admin"));
            sellerInfoService.saveSeller(sellerInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findSellerAll() {
        List<SellerInfo> sellerInfoList = sellerInfoService.findSellerAll();
        System.out.println(JsonUtil.toJson(sellerInfoList));
    }
}