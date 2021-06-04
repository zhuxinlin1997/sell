package com.hand.controller;

import com.hand.config.ProjectUrlConfig;
import com.hand.constant.CookieConstant;
import com.hand.constant.RedisConstant;
import com.hand.entity.SellerInfo;
import com.hand.enums.ResultEnum;
import com.hand.service.SellerInfoService;
import com.hand.utils.CookieUtil;
import com.hand.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerInfoController {
    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse servletResponse,
                              Map<String,Object> map){
        //1. 根据username 和 password 去和数据库里的匹配
        SellerInfo sellerInfo = sellerInfoService.findSellerByUsername(username);
        String md5Password = null;
        try {
            md5Password = MD5Util.getMD5Code(password);
        }catch (Exception e){
            log.error("【密码加密失败】 e={}",e.getMessage());
            map.put("msg",ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        if (sellerInfo == null || !sellerInfo.getPassword().equals(md5Password)) {
            log.error("【用户不存在或密码错误】");
            map.put("msg",ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token),username,expire, TimeUnit.SECONDS);
        //3. 设置token至cookie
        CookieUtil.set(servletResponse, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                        HttpServletResponse response,
                        Map<String,Object> map){
        //获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        }
        map.put("msg",ResultEnum.LOGOUT_SUCEESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
