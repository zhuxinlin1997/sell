package com.hand.controller;

import com.hand.config.ProjectUrlConfig;
import com.hand.constant.CookieConstant;
import com.hand.constant.RedisConstant;
import com.hand.dto.OrderDTO;
import com.hand.entity.*;
import com.hand.enums.ResultEnum;
import com.hand.exception.BuyerAuthorizeException;
import com.hand.exception.SellAuthorizeException;
import com.hand.service.*;
import com.hand.utils.BuyerUtil;
import com.hand.utils.CookieUtil;
import com.hand.utils.KeyUtil;
import com.hand.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@Controller
@Slf4j
@RequestMapping("/buyer")
public class BuyerInfoController {
    @Autowired
    private BuyerInfoService buyerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name="redisTemplate")
    private RedisTemplate<String,Object> objectRedisTemplate;
    @Autowired
    private BuyerAddressService buyerAddressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse servletResponse,
                              Map<String,Object> map){
        //1. 根据username 和 password 去和数据库里的匹配
        BuyerInfo buyerInfo = buyerInfoService.findBuyerByUsername(username);
        String md5Password = null;
        try {
            md5Password = MD5Util.getMD5Code(password);
        }catch (Exception e){
            log.error("【密码加密失败】 e={}",e.getMessage());
            map.put("msg",ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
            map.put("url","/sell/buyer/product/web/list");
            return new ModelAndView("common/error",map);
        }
        if (buyerInfo == null || !buyerInfo.getPassword().equals(md5Password)) {
            log.error("【用户不存在或密码错误】");
            map.put("msg",ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
            map.put("url","/sell/buyer/product/web/list");
            return new ModelAndView("common/error",map);
        }
        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_USER_PREFIX, token),username,expire, TimeUnit.SECONDS);
        //3. 设置token至cookie
        CookieUtil.set(servletResponse, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/buyer/product/web/list");
    }
    @GetMapping("/toLogin")
    public ModelAndView toLogin(){
        return new ModelAndView("buyer/login");
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
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_USER_PREFIX,cookie.getValue()));
        }
        map.put("msg",ResultEnum.LOGOUT_SUCEESS.getMessage());
        map.put("url","/sell/buyer/product/web/list");
        return new ModelAndView("common/success",map);
    }

    @PostMapping("/reg")
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("relQuestion") String relQuestion,
                                 @RequestParam("relPassword") String relPassword,
                                 @RequestParam("userRelName") String userRelName,
                                 Map<String,Object> map){
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setBuyerId(KeyUtil.genUniqueKey());
        try {
            buyerInfo.setPassword(MD5Util.getMD5Code(password));
        }catch (Exception e){
            log.error("【密码加密失败】 e={}",e.getMessage());
            map.put("msg",ResultEnum.PASSWORD_ERROR.getMessage());
            map.put("url","/sell/buyer/toLogin");
            return new ModelAndView("common/error",map);
        }
        buyerInfo.setUsername(username);
        buyerInfo.setUserRelName(userRelName);
        buyerInfo.setRelQuestion(relQuestion);
        buyerInfo.setRelPassword(relPassword);
        buyerInfoService.saveBuyer(buyerInfo);
        return new ModelAndView("buyer/login");
    }
    @GetMapping("/manage")
    public ModelAndView manage(HttpServletRequest request,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "10") Integer size,
                               Map<String,Object> map){
        BuyerInfo buyer = BuyerUtil.getBuyerInfo(request, buyerInfoService, redisTemplate);
        List<BuyerAddress> buyerAddressList = buyerAddressService.findByBuyerId(buyer.getBuyerId());
        Page<OrderDTO> orderDTOPage = orderService.findList(buyer.getBuyerId(), new PageRequest(page-1, size));
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) objectRedisTemplate.opsForValue().get(buyer.getBuyerId());
        if (shoppingCartList == null) {
            shoppingCartList = new ArrayList<>();
        }
        List<ProductInfo> productInfoList = productService.findByProductIdIn(shoppingCartList.stream().map(e -> e.getProductId()).collect(Collectors.toList()));
        map.put("buyerInfo",buyer);
        map.put("buyerAddressList",buyerAddressList);
        map.put("orderDTOPage",orderDTOPage);
        map.put("shoppingCartList",shoppingCartList);
        map.put("productInfoList",productInfoList);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("buyer/manage");
    }
}
