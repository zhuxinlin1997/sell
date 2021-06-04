package com.hand.dao;

import com.hand.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
public interface BuyerAddressDAO extends JpaRepository<BuyerAddress,String>{
    List<BuyerAddress> findByBuyerId(String buyerId);

    BuyerAddress findByBuyerIdAndDefaultFlag(String buyerId, String defaultFlag);
}
