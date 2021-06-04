package com.hand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hand.enums.ProductStatusEnum;
import com.hand.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/22
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    /** 商品编号. */
    @Id
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 商品状态.0上架1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(this.productStatus,ProductStatusEnum.class);
    }
}
