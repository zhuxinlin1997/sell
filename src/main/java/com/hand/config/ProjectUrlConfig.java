package com.hand.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/15
 */
@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
@PropertySource("classpath:application.yml")
public class ProjectUrlConfig {
    /** 项目路径 */
    private String sell;
}
