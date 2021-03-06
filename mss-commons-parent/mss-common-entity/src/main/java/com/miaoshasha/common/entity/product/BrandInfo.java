package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table brand_info
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class BrandInfo extends AbstractBaseEntity{
    /** */
    private Long brandId;

    /** 品牌名称*/
    private String brandName;

    /** 品牌图标的URL*/
    private String brandLogo;

    /** 品牌官方网址*/
    private String brandUrl;

    /** 状态，0-禁用，1-启用*/
    private Integer state;

    /** 品牌描述*/
    private String brandDesc;


    @Override
    public Long getId() {
        return this.brandId;
    }
}