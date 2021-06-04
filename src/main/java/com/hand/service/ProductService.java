package com.hand.service;

import com.hand.dto.CartDTO;
import com.hand.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/23
 */
@Service
public interface ProductService {
    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    //查询所有上架
    List<ProductInfo> findAllUp();

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
    //上下架商品
    ProductInfo onOffProduct(String productId);

    //根据类目查询商品
    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);

    //根据商品编号查询商品
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    //根据类目查询上架的商品
    List<ProductInfo> findByCategoryType(Integer categoryType);
}
