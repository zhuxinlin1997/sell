/**
 * 文件名： ConfigController.java
 * 版权： Copyright 2020 CETC All Rights Reserved.
 * 描述： description
 */
package com.hand.controller;

import com.hand.config.ProjectUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试获取配置文件信息
 * @author xinlin.zhu@hand-china.com
 * @date 2020/12/15 11:02
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigController {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/get")
    public String getConfig(){
        return projectUrlConfig.getSell();
    }
}
