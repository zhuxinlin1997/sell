package com.hand.dao;

import com.hand.entity.SellerInfo;
import com.hand.utils.KeyUtil;
import com.hand.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class SellerInfoDAOTest {

    private static final String USERNAME = "15084911046";

    @Autowired
    private SellerInfoDAO sellerInfoDAO;
    @Test
    public void findByUsername() {
        SellerInfo sellerInfo = sellerInfoDAO.findByUsername(USERNAME);
        Assert.assertEquals(USERNAME,sellerInfo.getUsername());
    }
    @Test
    public void saveTest(){
        try {
            SellerInfo sellerInfo = new SellerInfo();
            sellerInfo.setSellerId(KeyUtil.genUniqueKey());
            sellerInfo.setNote("测试");
            sellerInfo.setUsername(USERNAME);
            sellerInfo.setPassword(MD5Util.getMD5Code("111111"));
            SellerInfo result = sellerInfoDAO.save(sellerInfo);
            Assert.assertNotNull(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}