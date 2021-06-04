package com.hand.dao;

import com.hand.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {
    @Autowired
    private OrderMasterDAO orderMasterDAO;

    private final static String OPENID = "zhuxinlin123";
    @Test
    public void findByBuyerOpenid() {
        Pageable pageable = new PageRequest(1,1);
        Page<OrderMaster> orderMasterPage = orderMasterDAO.findByBuyerOpenid(OPENID, pageable);
        System.out.println(orderMasterPage.getTotalElements()+","+orderMasterPage.getTotalPages()+","+orderMasterPage.getContent());
    }
    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("海亮九玺");
        orderMaster.setBuyerName("新林");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("13455556666");
        orderMaster.setOrderAmount(new BigDecimal("3.2"));
        orderMaster.setOrderId("123456");
        OrderMaster result = orderMasterDAO.save(orderMaster);
    }
}