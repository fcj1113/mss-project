package com.miaoshasha.base.mapper.product;

import com.miaoshasha.common.entity.product.ProductAttr;

public interface ProductAttrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long attrId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    int insert(ProductAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    int insertSelective(ProductAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    ProductAttr selectByPrimaryKey(Long attrId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_attr
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductAttr record);
}