package com.hand.controller;

import com.hand.VO.ResultVO;
import com.hand.constant.RedisConstant;
import com.hand.converter.OrderForm2OrderDTOConverter;
import com.hand.dto.OrderDTO;
import com.hand.entity.*;
import com.hand.enums.ResultEnum;
import com.hand.exception.SellException;
import com.hand.form.OrderForm;
import com.hand.service.*;
import com.hand.utils.BuyerUtil;
import com.hand.utils.JsonUtil;
import com.hand.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description 买家订单控制器
 * @date 2019/3/3
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private BuyerInfoService buyerInfoService;
    @Autowired
    private BuyerAddressService buyerAddressService;
    @Autowired
    private CategoryService categoryService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }
    //创建订单
    @PostMapping("/create1")
    public ResultVO<Map<String,String>> create1(String username,String items){
        BuyerInfo buyer = buyerInfoService.findBuyerByUsername(username);
        BuyerAddress buyerAddress = buyerAddressService.findByBuyerIdAndDefaultFlag(buyer.getBuyerId(), "Y");
        OrderForm orderForm = new OrderForm();
        orderForm.setItems(items);
        orderForm.setName(buyer.getUserRelName());
        orderForm.setAddress(buyerAddress.getReceiptAddress());
        orderForm.setPhone(buyerAddress.getReceiptPhone());
        orderForm.setOpenid(buyer.getBuyerId());
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }
    //创建订单
    @PostMapping("/create2")
    public ResultVO<Map<String,String>> create2(String username, HttpServletRequest request, String items){
        BuyerInfo buyer = buyerInfoService.findBuyerByUsername(username);
        BuyerAddress buyerAddress = buyerAddressService.findByBuyerIdAndDefaultFlag(buyer.getBuyerId(), "Y");
        OrderForm orderForm = new OrderForm();
        orderForm.setItems(items);
        orderForm.setName(buyer.getUserRelName());
        orderForm.setAddress(buyerAddress.getReceiptAddress());
        orderForm.setPhone(buyerAddress.getReceiptPhone());
        orderForm.setOpenid(buyer.getBuyerId());
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        //清除购物车

        BuyerInfo buyerInfo = BuyerUtil.getBuyerInfo(request, buyerInfoService, stringRedisTemplate);
        //从redis获取list集合
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) redisTemplate.opsForValue().get(buyerInfo.getBuyerId());
        if (shoppingCartList != null) {
            List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
            for (OrderDetail orderDetail : orderDetailList) {
                Optional<ShoppingCart> cart = shoppingCartList.stream()
                        .filter(shoppingCart -> shoppingCart.getProductId().equals(orderDetail.getProductId())).findFirst();
                if (cart.isPresent()){
                    //存在
                    ShoppingCart shoppingCart = cart.get();
                    shoppingCartList.remove(shoppingCart);
                }
            }
        }
        redisTemplate.opsForValue().set(buyerInfo.getBuyerId(),shoppingCartList, RedisConstant.CART_EXPIRE, TimeUnit.SECONDS);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【订单列表】 openid={}",openid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, new PageRequest(page, size));
        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //订单详情
    @GetMapping("/detail1")
    public ModelAndView detail1(@RequestParam("openid") String openid,
                                @RequestParam("orderId") String orderId,
                                Map<String,Object> map){
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        //查询所有的品类
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("orderDTO",orderDTO);
        map.put("buyerId",openid);
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("buyer/order_detail",map);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                            @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }

    @GetMapping("/cancel1")
    public ModelAndView cancel1(@RequestParam("openid") String openid,
                                @RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        //查询订单是否存在
        try {
            buyerService.cancelOrder(openid,orderId);
        }catch (SellException e) {
            log.error("【买家后端--取消订单，发生异常={}】", e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/buyer/manage");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/buyer/manage");
        return new ModelAndView("common/success",map);
    }
}
