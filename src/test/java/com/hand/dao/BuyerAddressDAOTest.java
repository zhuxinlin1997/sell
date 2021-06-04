package com.hand.dao;

import com.hand.entity.BuyerAddress;
import com.hand.utils.JsonUtil;
import com.hand.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
public class BuyerAddressDAOTest {
    @Autowired
    private BuyerAddressDAO buyerAddressDAO;
    @Test
    public void findByBuyerId() {
        List<BuyerAddress> buyerAddressList = buyerAddressDAO.findByBuyerId("1556956223138476785");
        System.out.println(JsonUtil.toJson(buyerAddressList));
    }

    @Test
    public void deleteByAddressId() {
        buyerAddressDAO.delete("1556970129826638860");
    }

    @Test
    public void saveTest(){
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAddressId(KeyUtil.genUniqueKey());
        buyerAddress.setBuyerId("1556956223138476785");
        buyerAddress.setReceiptAddress("男高327");
        buyerAddress.setReceiptPhone("2222222222");
        buyerAddress.setReceiver("曹远权1");
        buyerAddressDAO.save(buyerAddress);
    }
}