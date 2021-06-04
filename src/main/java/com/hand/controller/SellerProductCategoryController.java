package com.hand.controller;

import com.hand.entity.ProductCategory;
import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description 商品类目控制器
 * @date 2019/3/20
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerProductCategoryController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 订单列表
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("/category/index");
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                             @RequestParam(value = "categoryName") String categoryName,
                             @RequestParam(value = "categoryType") Integer categoryType,
                             Map<String,Object> map){
        //判断是新增还是修改
        ProductCategory productCategory = new ProductCategory();
        try {
            if (!StringUtils.isEmpty(categoryId)){
               productCategory = categoryService.findOne(categoryId);
            }
            productCategory.setCategoryName(categoryName);
            productCategory.setCategoryType(categoryType);
            categoryService.save(productCategory);
        } catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        } catch (Exception e){
            map.put("msg",ResultEnum.UNKNOWN_ERROR.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.CAREGORY_SAVE_SUCCESS.getMessage());
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
