package com.hand.utils;

import com.hand.constant.CookieConstant;
import com.hand.constant.RedisConstant;
import com.hand.entity.BuyerInfo;
import com.hand.exception.BuyerAuthorizeException;
import com.hand.service.BuyerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/5
 */
@Slf4j
public class BuyerUtil {

    public static BuyerInfo getBuyerInfo(HttpServletRequest request,BuyerInfoService buyerInfoService,StringRedisTemplate redisTemplate){
        //获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】 Cookie中查不到token值");
            throw new BuyerAuthorizeException();
        }
        //查询redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_USER_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】 Redis中查不到token值");
            throw new BuyerAuthorizeException();
        }
        return buyerInfoService.findBuyerByUsername(tokenValue);
    }
}
