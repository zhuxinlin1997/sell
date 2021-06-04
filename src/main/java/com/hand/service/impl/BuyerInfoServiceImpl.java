package com.hand.service.impl;

import com.hand.dao.BuyerInfoDAO;
import com.hand.entity.BuyerInfo;
import com.hand.service.BuyerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
@Service
public class BuyerInfoServiceImpl implements BuyerInfoService {
    @Autowired
    private BuyerInfoDAO buyerInfoDAO;
    @Override
    public BuyerInfo findBuyerByUsername(String username) {
        return buyerInfoDAO.findByUsername(username);
    }

    @Override
    public BuyerInfo saveBuyer(BuyerInfo buyerInfo) {
        return buyerInfoDAO.save(buyerInfo);
    }

    @Override
    public List<BuyerInfo> findBuyerAll() {
        return buyerInfoDAO.findAll();
    }
}
