package com.hand.dao;

import com.hand.entity.BuyerInfo;
import com.hand.utils.JsonUtil;
import com.hand.utils.KeyUtil;
import com.hand.utils.MD5Util;
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
 * @date 2019/5/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerInfoDAOTest {
    @Autowired
    private BuyerInfoDAO dao;
    @Test
    public void findByUsername() {
        BuyerInfo buyerInfo = dao.findByUsername("zhuxinlin");
        System.out.println(JsonUtil.toJson(buyerInfo));
    }

    @Test
    public void saveTest() throws Exception{
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setBuyerId(KeyUtil.genUniqueKey());
        buyerInfo.setPassword(MD5Util.getMD5Code("111111"));
        buyerInfo.setUsername("zhuxinlin");
        buyerInfo.setUserRelName("朱新林");
        buyerInfo.setRelQuestion("测试问题");
        buyerInfo.setRelPassword("测试密码");
        dao.save(buyerInfo);
    }
}