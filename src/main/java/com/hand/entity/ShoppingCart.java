package com.hand.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/6
 */
@Data
public class ShoppingCart implements Serializable{

    private static final long serialVersionUID = 8323721180911532829L;

    private String cartId;

    private String productId;

    private Integer productNumber;

    public ShoppingCart(String cartId, String productId, Integer productNumber) {
        this.cartId = cartId;
        this.productId = productId;
        this.productNumber = productNumber;
    }
}
