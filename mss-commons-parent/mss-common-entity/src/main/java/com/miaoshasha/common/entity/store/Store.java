package com.miaoshasha.common.entity.store;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class Store  extends AbstractBaseEntity {
    /** */
    private Long storeId;

    /** */
    private String storeName;

    /** 店铺归属客户*/
    private Long custId;

    /** 联系人*/
    private String contacts;

    /** 联系人电话，可是手机号码也可以是固定电话*/
    private String contactsPhone;

    /** 省份*/
    private Integer province;

    /** 城市*/
    private Integer city;

    /** 区县*/
    private Integer county;

    /** */
    private String address;

    /** */
    private String allAddress;

    /** 经度*/
    private Double longitude;

    /** 维度*/
    private Double latitude;

    /** 商店门头照*/
    private String headImage;

    /** 商店图片,最多5张*/
    private String storeImage;

    /** 起始营业时间*/
    private Date openBeginTime;

    /** 结束营业时间*/
    private Date openEndTime;

    /** */
    private Integer seqNo;

    /** 状态，0-关闭，1-启用*/
    private Integer state;

    /** 店铺资质 0没有资质 1有资质*/
    private Integer storeAptitude;

    /** 审核状态，1-未审核，2-审核通过，3-审核不通过*/
    private Integer checkState;

    /** 店主身份证号*/
    private String idcardNo;

    /** 身份证号码照片*/
    private String idcardImage;

    /** 营业执照号码*/
    private String storeLicense;

    /** 营业执照照片*/
    private String licenseImage;

    /** 店铺图文简介,html编辑器完成*/
    private String storeDesc;


    @Override
    public Long getId() {
        return this.storeId;
    }
}