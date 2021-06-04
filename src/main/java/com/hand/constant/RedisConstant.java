package com.hand.constant;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description Redis常量
 * @date 2019/4/15
 */
public interface RedisConstant
{
    Integer EXPIRE = 7200;//2小时

    Integer CART_EXPIRE = 3600 * 24 * 7; //7天

    String TOKEN_PREFIX = "token_%s";

    String TOKEN_USER_PREFIX = "token_user_%s";
}
