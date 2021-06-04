package com.hand.service;

import com.hand.entity.BuyerInfo;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/3/3
 */
public interface BuyerInfoService {
    BuyerInfo findBuyerByUsername(String username);

    BuyerInfo saveBuyer(BuyerInfo buyerInfo);

    List<BuyerInfo> findBuyerAll();
}
