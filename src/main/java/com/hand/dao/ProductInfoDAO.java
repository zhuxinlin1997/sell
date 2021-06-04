package com.hand.dao;

import com.hand.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/22
 */
public interface ProductInfoDAO extends JpaRepository<ProductInfo,String> {
    //根据商品类目查询商品
    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //查询上架商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
    //根据商品id查询商品集合
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    List<ProductInfo> findByCategoryTypeAndAndProductStatus(Integer categoryType,Integer productStatus);
}
