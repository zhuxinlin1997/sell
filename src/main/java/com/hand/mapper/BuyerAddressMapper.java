package com.hand.mapper;

import com.hand.entity.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/17
 */
public interface BuyerAddressMapper {

    @Delete("update buyer_address ba set ba.default_flag = 'N' where ba.buyer_id = #{buyerId,jdbcType=VARCHAR}")
    public Integer updateBuyerAddressByDefaultFlag(String buyerId);

}
