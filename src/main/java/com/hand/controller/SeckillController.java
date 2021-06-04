package com.hand.controller;

import com.hand.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/17
 */
@RestController
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return seckillService.querySeckillProductInfo(productId);
    }
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request productId+"+productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySeckillProductInfo(productId);
    }
}
