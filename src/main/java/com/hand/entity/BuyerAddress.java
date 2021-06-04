package com.hand.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
@Entity
@DynamicUpdate
@Data
public class BuyerAddress {
    @Id
    private String addressId;
    /** 买家id */
    private String buyerId;
    /** 收货人 */
    private String receiver;
    /** 收货号码 */
    private String receiptPhone;
    /** 收获地址 */
    private String receiptAddress;
    /** 默认标志 */
    private String defaultFlag = "Y";

    private Date createTime;

    private Date updateTime;
}
