package com.hand.service;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/17
 */
public interface SeckillService {
    //查询秒杀的商品信息
    String querySeckillProductInfo(String productId);
    //秒杀商品
    void orderProductMockDiffUser(String productId);
}
