package com.hand.enums;

import lombok.Getter;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/26
 */
@Getter
public enum ResultEnum {
    UNKNOWN_ERROR(1,"系统异常，请联系管理员"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(16,"支付状态错误"),
    PARAM_ERROR(17,"参数不正确"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"订单不属于该用户"),
    ORDER_CANCEL_SUCCESS(20,"订单取消成功"),
    ORDER_FINISH_SUCCESS(21,"订单完结成功"),
    PRODUCT_STATUS_UPDATE_SUCCESS(22,"商品状态成功"),
    PRODUCT_SAVE_SUCCESS(23,"商品保存成功"),
    CAREGORY_SAVE_SUCCESS(24,"类目保存成功"),
    USERNAME_OR_PASSWORD_ERROR(25,"用户名或密码错误"),
    LOGOUT_SUCEESS(26,"登出成功"),
    ACTIVITY_ENDS(27,"活动结束"),
    PASSWORD_ERROR(28,"密码输入非法"),
    ADDRESS_SAVE_SUCCESS(29,"地址保存成功"),
    ADDRESS_DELETE_SUCCESS(30,"地址删除成功"),
    ADDRESS_OWNER_ERROR(31,"地址不属于该用户"),
    ADDRESS_UPDATE_SUCCESS(32,"地址修改成功"),
    ADDRESS_DELETE_ERROR(33,"默认地址不能删除"),
    CART_ADD_SUCCESS(34,"成功加入购物车"),
    CART_DELETE_ERROR(35,"购物车删除失败"),
    CART_DELETE_SUCCESS(36,"购物车删除成功"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
