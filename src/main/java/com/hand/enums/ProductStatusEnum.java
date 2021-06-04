package com.hand.enums;

import lombok.Getter;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description 商品状态
 * @date 2019/2/23
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"上架"),
    DOWN(1,"下架"),
    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
