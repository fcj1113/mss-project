package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table product_detail
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class ProductDetail extends AbstractBaseEntity {
    /** */
    private Long pid;

    /** */
    private Long productId;

    /** */
    private String productImages;

    /** */
    private String productUrl;

    /** 商品介绍*/
    private String intro;

    @Override
    public Long getId() {
        return this.pid;
    }
}