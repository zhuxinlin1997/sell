package com.hand.controller;

import com.hand.constant.RedisConstant;
import com.hand.entity.BuyerInfo;
import com.hand.entity.ShoppingCart;
import com.hand.enums.ResultEnum;
import com.hand.service.BuyerInfoService;
import com.hand.utils.BuyerUtil;
import com.hand.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/6
 */
@Controller
@Slf4j
@RequestMapping("/buyer/cart")
public class BuyerCartController {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private BuyerInfoService buyerInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/add")
    public ModelAndView addProductToCart(@RequestParam("productId") String productId,
                                         @RequestParam("productNumber") Integer productNumber,
                                         HttpServletRequest request,
                                         Map<String,Object> map){
        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, stringRedisTemplate);
        //从redis获取list集合
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) redisTemplate.opsForValue().get(buyerInfo.getBuyerId());
        if (shoppingCartList == null) {
            shoppingCartList = new ArrayList<>();
            shoppingCartList.add(new ShoppingCart(KeyUtil.genUniqueKey(),productId,productNumber));
        }else {
            Optional<ShoppingCart> cart = shoppingCartList.stream()
                    .filter(shoppingCart -> shoppingCart.getProductId().equals(productId)).findFirst();
            if (cart.isPresent()){
                //存在
                ShoppingCart shoppingCart = cart.get();
                shoppingCart.setProductNumber(shoppingCart.getProductNumber()+productNumber);
            }else {
                shoppingCartList.add(new ShoppingCart(KeyUtil.genUniqueKey(),productId,productNumber));
            }
        }
        redisTemplate.opsForValue().set(buyerInfo.getBuyerId(),shoppingCartList, RedisConstant.CART_EXPIRE, TimeUnit.SECONDS);
        map.put("msg", ResultEnum.CART_ADD_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/get")
    @ResponseBody
    public List<ShoppingCart> getCart(HttpServletRequest request){
        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, stringRedisTemplate);
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) redisTemplate.opsForValue().get(buyerInfo.getBuyerId());
        return shoppingCartList;
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("cartId") String cartId,
                               HttpServletRequest request,
                               Map<String,Object> map){
        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, stringRedisTemplate);
        //从redis获取list集合
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) redisTemplate.opsForValue().get(buyerInfo.getBuyerId());
        if (shoppingCartList == null) {
            map.put("msg", ResultEnum.CART_DELETE_ERROR.getMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }else {

            Optional<ShoppingCart> cart = shoppingCartList.stream()
                    .filter(shoppingCart -> shoppingCart.getCartId().equals(cartId)).findFirst();
            if (cart.isPresent()){
                //存在
                ShoppingCart shoppingCart = cart.get();
                shoppingCartList.remove(shoppingCart);
            }else {
                map.put("msg", ResultEnum.CART_DELETE_ERROR.getMessage());
                map.put("url","/sell/buyer/manage");
                return new ModelAndView("common/error",map);
            }
        }
        redisTemplate.opsForValue().set(buyerInfo.getBuyerId(),shoppingCartList, RedisConstant.CART_EXPIRE, TimeUnit.SECONDS);
        map.put("msg", ResultEnum.CART_DELETE_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }
}
