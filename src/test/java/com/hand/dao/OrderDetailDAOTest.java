package com.hand.dao;

import com.hand.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

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
public class OrderDetailDAOTest {
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDAO.findByOrderId("123456");
        System.out.println(orderDetailList);
    }
    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("112313");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("口味虾");
        orderDetail.setProductPrice(new BigDecimal("16.7"));
        orderDetail.setProductQuantity(3);
        OrderDetail result = orderDetailDAO.save(orderDetail);

        System.out.println(result);
    }

}