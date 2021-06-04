package com.hand.service.impl;

import com.hand.dto.OrderDTO;
import com.hand.entity.OrderDetail;
import com.hand.enums.OrderPayEnum;
import com.hand.enums.OrderStatusEnum;
import com.hand.service.OrderService;
import com.hand.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    private final static String BUYER_OPENID="zhuxinlin123";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("花桥卫生院");
        orderDTO.setBuyerName("小吴2");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("13476606593");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123457");
        orderDetail.setProductQuantity(1);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123458");
        orderDetail2.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1551188177857860146");
        System.out.println(orderDTO);
    }

    @Test
    public void findList() {
        Pageable page = new PageRequest(1,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, page);
        System.out.println(orderDTOPage.getTotalElements()+","+orderDTOPage.getContent());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1551230989595684562");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        OrderDTO result = orderService.cancel(orderDTO);
        System.out.println(result);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1551187915258309201");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1551186593880685674100000");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(OrderPayEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findList1() {
        Pageable page = new PageRequest(1,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(page);
        System.out.println(orderDTOPage.getTotalElements()+","+ JsonUtil.toJson(orderDTOPage.getContent())+","+orderDTOPage.getTotalPages());
    }
}