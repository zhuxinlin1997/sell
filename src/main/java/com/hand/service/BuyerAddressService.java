package com.hand.service;

import com.hand.entity.BuyerAddress;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */

public interface BuyerAddressService {
    List<BuyerAddress> findByBuyerId(String buyerId);

    void save(BuyerAddress buyerAddress);

    void deleteByAddressId(String addressId);

    void updateDefaultFlag(String buyerId);

    BuyerAddress findOne(String addressId);

    BuyerAddress findByBuyerIdAndDefaultFlag(String buyerId,String defaultFlag);
}
