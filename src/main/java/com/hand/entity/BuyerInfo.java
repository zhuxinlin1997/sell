package com.hand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hand.enums.BuyerStatusEnum;
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
 * @date 2019/5/4
 */
@Entity
@DynamicUpdate
@Data
public class BuyerInfo {
    @Id
    private String buyerId;

    /** 用户名 */
    private String username;

    /** 密码. */
    private String password;

    /** 真实姓名. */
    private String userRelName;

    /** 密保问题. */
    private String relQuestion;

    /** 密保答案. */
    private String relPassword;

    /** 用户状态 1-可以使用,0-停用 */
    private Integer enableFlag = BuyerStatusEnum.ENABLED.getCode();

    private Date createTime;

    private Date updateTime;
    @JsonIgnore
    public BuyerStatusEnum getBuyerStatusEnum(){
        return EnumUtil.getByCode(this.getEnableFlag(),BuyerStatusEnum.class);
    }
}
