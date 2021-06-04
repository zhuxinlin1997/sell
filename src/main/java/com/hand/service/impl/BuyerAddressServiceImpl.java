package com.hand.service.impl;

import com.hand.dao.BuyerAddressDAO;
import com.hand.entity.BuyerAddress;
import com.hand.mapper.BuyerAddressMapper;
import com.hand.service.BuyerAddressService;
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
public class BuyerAddressServiceImpl implements BuyerAddressService {
    @Autowired
    private BuyerAddressDAO buyerAddressDAO;
    @Autowired
    private BuyerAddressMapper mapper;
    @Override
    public List<BuyerAddress> findByBuyerId(String buyerId) {
        return buyerAddressDAO.findByBuyerId(buyerId);
    }

    @Override
    public void save(BuyerAddress buyerAddress) {
        buyerAddressDAO.save(buyerAddress);
    }

    @Override
    public void deleteByAddressId(String addressId) {
        buyerAddressDAO.delete(addressId);
    }

    @Override
    public void updateDefaultFlag(String buyerId) {
        mapper.updateBuyerAddressByDefaultFlag(buyerId);
    }

    @Override
    public BuyerAddress findOne(String addressId) {
        return buyerAddressDAO.findOne(addressId);
    }

    @Override
    public BuyerAddress findByBuyerIdAndDefaultFlag(String buyerId, String defaultFlag) {
        return buyerAddressDAO.findByBuyerIdAndDefaultFlag(buyerId,defaultFlag);
    }
}
