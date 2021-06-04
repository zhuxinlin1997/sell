package com.hand.enums;

import lombok.Getter;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@Getter
public enum BuyerStatusEnum implements CodeEnum{
    ENABLED(1,"正常"),
    DISABLED(0,"停用")
    ;
    private Integer code;

    private String message;

    BuyerStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
