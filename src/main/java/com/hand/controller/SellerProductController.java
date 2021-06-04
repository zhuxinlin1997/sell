package com.hand.controller;

import com.hand.entity.ProductCategory;
import com.hand.entity.ProductInfo;
import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.form.ProductForm;
import com.hand.service.CategoryService;
import com.hand.service.ProductService;
import com.hand.utils.JsonUtil;
import com.hand.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description 卖家端商品控制器
 * @date 2019/3/14
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 订单列表
     * @param page 第几页，从第1页开始
     * @param size 一页有多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("product/list",map);
    }

    /**
     * 商品上下架，如果已经是上架商品，就下架此商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_off_sale")
    @CacheEvict(cacheNames="product",key = "123")
    public ModelAndView onOffSale(@RequestParam(value = "productId") String productId,
                                    Map<String,Object> map){
        try {
            ProductInfo productInfo = productService.onOffProduct(productId);
        } catch (SellException e){
            log.error("【商品上下架】 异常 ,e={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.PRODUCT_STATUS_UPDATE_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("/product/index");
    }
    @PostMapping("/save")
    @CacheEvict(cacheNames="product",key = "123")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            log.error("【保存商品】 参数不正确，productForm={}", JsonUtil.toJson(productForm));
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        //判断是新增还是修改
        ProductInfo productInfo = new ProductInfo();
        try {
            if (StringUtils.isEmpty(productForm.getProductId())){
                productForm.setProductId(KeyUtil.genUniqueKey());
            }else{
                productInfo = productService.findOne(productForm.getProductId());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        } catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.PRODUCT_SAVE_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
