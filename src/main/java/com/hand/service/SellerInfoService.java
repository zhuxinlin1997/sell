package com.hand.service;

import com.hand.entity.SellerInfo;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
public interface SellerInfoService {

    SellerInfo findSellerByUsername(String username);

    SellerInfo saveSeller(SellerInfo sellerInfo);

    List<SellerInfo> findSellerAll();
}
