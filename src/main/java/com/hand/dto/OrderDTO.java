package com.hand.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hand.entity.OrderDetail;
import com.hand.enums.OrderPayEnum;
import com.hand.enums.OrderStatusEnum;
import com.hand.utils.EnumUtil;
import com.hand.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/26
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家电话. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态,默认0新下单. */
    private Integer orderStatus;

    /** 支付状态,默认0未支付. */
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(this.orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public OrderPayEnum getOrderPayEnum(){
        return EnumUtil.getByCode(this.payStatus,OrderPayEnum.class);
    }
}
