package com.hand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hand.enums.SellerStatusEnum;
import com.hand.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@Entity
@DynamicUpdate
@Data
public class SellerInfo {
    @Id
    private String sellerId;

    /** 用户名 */
    private String username;

    /** 密码. */
    private String password;

    /** 备注. */
    private String note;

    /** 用户状态 1-可以使用,0-停用 */
    private Integer enableFlag = SellerStatusEnum.ENABLED.getCode();

    private Date createTime;

    private Date updateTime;
    @JsonIgnore
    public SellerStatusEnum getSellerStatusEnum(){
        return EnumUtil.getByCode(this.getEnableFlag(),SellerStatusEnum.class);
    }
}
