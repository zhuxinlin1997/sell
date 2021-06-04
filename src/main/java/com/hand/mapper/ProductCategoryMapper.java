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
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    public Integer insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    public Integer insertByObject(ProductCategory productCategory);

    @Select("select pc.category_id,pc.category_name,pc.category_type,pc.create_time from product_category pc where pc.category_type = #{categoryType,jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "create_time",property = "createTime")
    })
    public ProductCategory selectByCategoryType(Integer categoryType);

    @Select("select pc.category_id,pc.category_name,pc.category_type,pc.create_time from product_category pc where pc.category_name = #{categoryName,jdbcType=VARCHAR}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "create_time",property = "createTime")
    })
    public List<ProductCategory> selectBycategoryName(String categoryName);

    @Select("select pc.category_id,pc.category_name,pc.category_type,pc.create_time from product_category pc where pc.category_name = #{categoryName,jdbcType=VARCHAR} and pc.category_type = #{categoryType,jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "create_time",property = "createTime")
    })
    public ProductCategory selectBycategoryNameAndCategoryType(@Param("categoryName") String categoryName,
                                                               @Param("categoryType") Integer categoryType);

    @Select("select pc.category_id,pc.category_name,pc.category_type,pc.create_time from product_category pc where pc.category_name = #{categoryName,jdbcType=VARCHAR} and pc.category_type = #{categoryType,jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "create_time",property = "createTime")
    })
    public ProductCategory selectByObject(ProductCategory productCategory);

    @Update("update product_category pc set pc.category_name = #{categoryName,jdbcType=VARCHAR} where pc.category_type = #{categoryType,jdbcType=INTEGER}")
    public Integer updateByObject(ProductCategory productCategory);

    @Update("update product_category pc set pc.category_name = #{categoryName,jdbcType=VARCHAR} where pc.category_type = #{categoryType,jdbcType=INTEGER}")
    public Integer updateByMap(Map<String,Object> map);

    @Update("update product_category pc set pc.category_name = #{categoryName,jdbcType=VARCHAR} where pc.category_type = #{categoryType,jdbcType=INTEGER}")
    public Integer updateCategoryNameByCategoryType(@Param("categoryName") String categoryName,
                                                    @Param("categoryType") Integer categoryType);

    @Delete("delete pc from product_category pc where pc.category_type = #{categoryType,jdbcType=INTEGER}")
    public Integer deleteByCategoryType(Integer categoryType);

}
