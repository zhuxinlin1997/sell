package com.hand.enums;

import lombok.Getter;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/25
 */
@Getter
public enum OrderPayEnum implements CodeEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;

    private String msg;

    OrderPayEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
