package com.hand.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/5/4
 */
@Data
public class BuyerAddressForm {
    @NotEmpty(message = "收货人必填")
    private String receiver;
    @NotEmpty(message = "手机号必填")
    @Pattern(regexp="^1[3456789]\\d{9}$",message="手机号非法")
    private String receiptPhone;
    @NotEmpty(message = "收获地址必填")
    private String receiptAddress;

    private String buyerId;
}
