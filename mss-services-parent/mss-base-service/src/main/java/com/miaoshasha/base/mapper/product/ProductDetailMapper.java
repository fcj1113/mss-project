package com.miaoshasha.base.mapper.product;

import com.miaoshasha.common.entity.product.ProductDetail;

public interface ProductDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int insert(ProductDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int insertSelective(ProductDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    ProductDetail selectByPrimaryKey(Long pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ProductDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductDetail record);
}