package com.hand.converter;

import com.hand.dto.OrderDTO;
import com.hand.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/27
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> converter(e)).collect(Collectors.toList());
        return  orderDTOList;
    }
}
