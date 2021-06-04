package com.hand.service.impl;

import com.hand.dao.SellerInfoDAO;
import com.hand.entity.SellerInfo;
import com.hand.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService{
    @Autowired
    private SellerInfoDAO sellerInfoDAO;
    @Override
    public SellerInfo findSellerByUsername(String username) {
        return sellerInfoDAO.findByUsername(username);
    }

    @Override
    public SellerInfo saveSeller(SellerInfo sellerInfo) {

        return sellerInfoDAO.save(sellerInfo);
    }

    @Override
    public List<SellerInfo> findSellerAll() {
        return sellerInfoDAO.findAll();
    }
}
