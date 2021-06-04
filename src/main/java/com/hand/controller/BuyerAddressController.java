package com.hand.controller;

import com.hand.entity.BuyerAddress;
import com.hand.entity.BuyerInfo;
import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.form.BuyerAddressForm;
import com.hand.service.BuyerAddressService;
import com.hand.service.BuyerInfoService;
import com.hand.utils.BuyerUtil;
import com.hand.utils.JsonUtil;
import com.hand.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
@Controller
@Slf4j
@RequestMapping("/buyer/address")
public class BuyerAddressController {
    @Autowired
    private BuyerAddressService buyerAddressService;
    @Autowired
    private BuyerInfoService buyerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @PostMapping("/save")
    public ModelAndView save(@Valid BuyerAddressForm buyerAddressForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            log.error("【保存用户地址】 参数不正确，addressForm={}", JsonUtil.toJson(buyerAddressForm));
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }
        buyerAddressService.updateDefaultFlag(buyerAddressForm.getBuyerId());
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAddressId(KeyUtil.genUniqueKey());
        buyerAddress.setBuyerId(buyerAddressForm.getBuyerId());
        buyerAddress.setReceiptAddress(buyerAddressForm.getReceiptAddress());
        buyerAddress.setReceiptPhone(buyerAddressForm.getReceiptPhone());
        buyerAddress.setReceiver(buyerAddressForm.getReceiver());
        buyerAddressService.save(buyerAddress);
        map.put("msg", ResultEnum.ADDRESS_SAVE_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "addressId") String addressId,
                               HttpServletRequest request,
                               Map<String,Object> map){
        //处理越权访问
        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, redisTemplate);
        BuyerAddress buyerAddress = buyerAddressService.findOne(addressId);
        if (!buyerAddress.getBuyerId().equals(buyerInfo.getBuyerId())){
            log.error("【删除地址】 用户越权访问，address={},buyerInfo={}", buyerAddress,buyerInfo);
            map.put("msg",ResultEnum.ADDRESS_OWNER_ERROR.getMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }
        //判断是否是默认地址
        if (buyerAddress.getDefaultFlag().equals("Y")){
            log.error("【删除地址】 默认地址不能删除，address={}", buyerAddress);
            map.put("msg",ResultEnum.ADDRESS_DELETE_ERROR.getMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }
        buyerAddressService.deleteByAddressId(addressId);
        map.put("msg", ResultEnum.ADDRESS_DELETE_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/setDefaultAddress")
    public ModelAndView setDefaultAddress(@RequestParam(value = "addressId") String addressId,
                                          HttpServletRequest request,
                                          Map<String,Object> map){
        //处理越权访问
        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, redisTemplate);
        BuyerAddress buyerAddress = buyerAddressService.findOne(addressId);
        if (!buyerAddress.getBuyerId().equals(buyerInfo.getBuyerId())){
            log.error("【设置默认地址】 用户越权访问，address={},buyerInfo={}", buyerAddress,buyerInfo);
            map.put("msg",ResultEnum.ADDRESS_OWNER_ERROR.getMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }
        buyerAddressService.updateDefaultFlag(buyerInfo.getBuyerId());
        buyerAddress.setDefaultFlag("Y");
        buyerAddressService.save(buyerAddress);
        map.put("msg", ResultEnum.ADDRESS_UPDATE_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }
}
