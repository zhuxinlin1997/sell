package com.hand.controller;

import com.hand.constant.CookieConstant;
import com.hand.constant.RedisConstant;
import com.hand.entity.ProductCategory;
import com.hand.entity.ProductInfo;
import com.hand.service.CategoryService;
import com.hand.service.ProductService;
import com.hand.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/23
 */
@Controller
@RequestMapping("/buyer/product/web")
@Slf4j
public class BuyerProductWebController {
    @Autowired
    private ProductService productService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "productCategory",required = false) Integer productCategory,
                             HttpServletRequest request,
                             Map<String,Object> map){
        //查询所有的品类
        List<ProductCategory> productCategoryList = categoryService.findAll();
        if (productCategory == null) {
            productCategory = productCategoryList.get(0).getCategoryType();
        }
        //查询第一个品类的所有上架商品
        List<ProductInfo> productInfoList = productService.findByCategoryType(productCategory);
        getUser(request,map);
        map.put("productCategoryList",productCategoryList);
        map.put("productInfoList",productInfoList);
        return new ModelAndView("buyer/index",map);
    }

    @GetMapping("/detail")
    public ModelAndView productDetail(@RequestParam(value = "productId") String productId,
                                      HttpServletRequest request,
                                      Map<String,Object> map){
        //查询所有的品类
        List<ProductCategory> productCategoryList = categoryService.findAll();
        getUser(request,map);
        map.put("productInfo",productService.findOne(productId));
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("buyer/product_detail",map);
    }

    private void getUser(HttpServletRequest request,Map<String,Object> map){
        //查询用户信息
        //获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【用户首页】 Cookie中查不到token值，用户未登录");
        }else {
            //查询redis
            String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_USER_PREFIX, cookie.getValue()));
            if (StringUtils.isEmpty(tokenValue)){
                log.warn("【用户首页】 Redis中查不到token值");
            }
            map.put("username",tokenValue);
        }
    }
}
