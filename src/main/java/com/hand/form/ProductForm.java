package com.hand.form;

import com.hand.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/3/20
 */
@Data
public class ProductForm {
    /** 商品编号. */
    private String productId;

    /** 商品名称. */
    @NotEmpty(message = "商品名称必填")
    private String productName;

    /** 单价. */
    @NotNull(message = "单价必填")
    private BigDecimal productPrice;

    /** 库存. */
    @NotNull(message = "库存必填")
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    @NotEmpty(message = "小图必填")
    private String productIcon;

    /** 商品状态.0上架1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    @NotNull(message = "类目必填")
    private Integer categoryType;
}
