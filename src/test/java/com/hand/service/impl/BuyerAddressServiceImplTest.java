package com.hand.service.impl;

import com.hand.service.BuyerAddressService;
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
public class BuyerAddressServiceImplTest {
    @Autowired
    private BuyerAddressService buyerAddressService;
    @Test
    public void updateDefaultFlag() {
        buyerAddressService.updateDefaultFlag("1556956223138476785");
    }
}