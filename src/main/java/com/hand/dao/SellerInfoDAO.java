package com.hand.dao;

import com.hand.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
public interface SellerInfoDAO extends JpaRepository<SellerInfo,String> {

    SellerInfo findByUsername(String usernmae);

}
