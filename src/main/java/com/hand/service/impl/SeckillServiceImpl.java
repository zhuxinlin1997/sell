package com.hand.service.impl;

import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.service.RedisLock;
import com.hand.service.SeckillService;
import com.hand.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/17
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private RedisLock redisLock;

    private static final Integer TIMEOUT = 10 * 1000;

    static Map<String,Integer> products;
    static Map<String,Integer> stocks;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表 商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stocks = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stocks.put("123456",100000);
    }

    public String queryMap(String productId){
        return "七夕活动，玫瑰花特价，限量份"
                +products.get(productId)
                +"，还剩："+stocks.get(productId)+"份，"
                +"该商品成功下单用户数目："
                +orders.size()+"人";
    }

    @Override
    public String querySeckillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId,Long.toString(time))){throw new SellException(101,"没排上队"); }
        //查询库存
        Integer stockNum = stocks.get(productId);
        if (stockNum == 0){throw new SellException(ResultEnum.ACTIVITY_ENDS);}
        //减库存
        stockNum -= 1;
        //模拟生成订单
        orders.put(KeyUtil.genUniqueKey(),productId);
        //模拟一些IO操作的用时
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        stocks.put(productId,stockNum);
        //解锁
        redisLock.unlock(productId,Long.toString(time));
    }
}
