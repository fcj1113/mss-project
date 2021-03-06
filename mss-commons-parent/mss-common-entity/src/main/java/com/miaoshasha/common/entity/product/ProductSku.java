package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table product_sku
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class ProductSku extends AbstractBaseEntity {
    /** 库存单元ID*/
    private Long skuId;

    /** 库存单元编码*/
    private String skuCode;

    /** 库存单元名称*/
    private String skuName;

    /** 商品ID*/
    private Long productId;

    /** 打包属性ID组合，英文逗号隔开*/
    private String skuItems;

    /** sale名称*/
    private String itemName;

    /** 市场价，单位分*/
    private Long marketPrice;

    /** 销售价格，单位分*/
    private Long sellPrice;

    /** 会员价，单位分*/
    private Long memberPrice;

    /** 成本价格，单位分*/
    private Long costPrice;

    /** 积分最大使用比例0-100*/
    private Integer percentPoint;

    /** 邮费，免邮费用为0*/
    private Integer postage;

    /** 库存数量*/
    private Integer stockNum;

    /** 自定义已销售的数量，默认与真实一致*/
    private Integer saleNum;

    /** 真实销售数量*/
    private Integer realSaleNum;

    /** 二维码,图片url*/
    private String qrCode;

    /** 条码*/
    private String barCode;

    /** 归属店铺*/
    private Long belongStore;

    /** 状态*/
    private String state;


    @Override
    public Long getId() {
        return this.skuId;
    }
}