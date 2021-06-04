package com.hand.service.impl;

import com.hand.dao.ProductInfoDAO;
import com.hand.dto.CartDTO;
import com.hand.entity.ProductInfo;
import com.hand.enums.ProductStatusEnum;
import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/23
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDAO productInfoDAO;

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        List<String> productIdList = cartDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        for (String productId : productIdList) {
            if (productId == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
        List<ProductInfo> productInfoList = productInfoDAO.findByProductIdIn(productIdList);
        for (ProductInfo productInfo : productInfoList) {
            for (CartDTO cartDTO : cartDTOList) {
                if (cartDTO.getProductId().equals(productInfo.getProductId())){
                    productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
                    productInfoDAO.save(productInfo);
                }
            }
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        List<String> productIdList = cartDTOList.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        for (String productId : productIdList) {
            if (productId == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
        List<ProductInfo> productInfoList = productInfoDAO.findByProductIdIn(productIdList);
        for (ProductInfo productInfo : productInfoList) {
            for (CartDTO cartDTO : cartDTOList) {
                if (cartDTO.getProductId().equals(productInfo.getProductId())){
                    Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
                    if(result < 0){
                        throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                    }
                    productInfo.setProductStock(result);
                    productInfoDAO.save(productInfo);
                }
            }
        }
    }

    @Override
    public ProductInfo onOffProduct(String productId) {
        //查询商品是否存在
        ProductInfo productInfo = productInfoDAO.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //判断商品状态
        if (productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())){
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        }else if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())){
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        }
        //修改商品，持久化
        return productInfoDAO.save(productInfo);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDAO.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDAO.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findAllUp() {
        return productInfoDAO.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDAO.save(productInfo);
    }

    @Override
    public List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productInfoDAO.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductInfo> findByCategoryType(Integer categoryType) {
        return productInfoDAO.findByCategoryTypeAndAndProductStatus(categoryType,ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIdList) {
        return productInfoDAO.findByProductIdIn(productIdList);
    }
}
