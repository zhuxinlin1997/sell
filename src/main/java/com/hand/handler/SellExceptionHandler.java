package com.hand.handler;

import com.hand.VO.ResultVO;
import com.hand.config.ProjectUrlConfig;
import com.hand.exception.BuyerAuthorizeException;
import com.hand.exception.SellAuthorizeException;
import com.hand.exception.SellException;
import com.hand.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/15
 */
@ControllerAdvice
public class SellExceptionHandler {
    //拦截卖家登录异常
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("login/login");
    }
    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    //拦截买家登录异常
    @ExceptionHandler(value = BuyerAuthorizeException.class)
    public ModelAndView handlerBuyerAuthorizeException(){
        return new ModelAndView("buyer/login");
    }
}
