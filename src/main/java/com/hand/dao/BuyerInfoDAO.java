package com.hand.dao;

import com.hand.entity.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
public interface BuyerInfoDAO extends JpaRepository<BuyerInfo,String> {

    BuyerInfo findByUsername(String usernmae);
}
