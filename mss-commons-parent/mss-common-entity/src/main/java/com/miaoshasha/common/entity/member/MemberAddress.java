package com.miaoshasha.common.entity.member;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class MemberAddress  extends AbstractBaseEntity {
    /** */
    private Long addressId;

    /** 会员*/
    private Long memberId;

    /** 联系人*/
    private String contacts;

    /** 联系人电话*/
    private String contactsPhone;

    /** 省*/
    private Long province;

    /** 市*/
    private Long city;

    /** 区县*/
    private Long county;

    /** 街道乡镇*/
    private Long street;

    /** 详细地址*/
    private String address;

    /** 包含省市区县街道的全部地址*/
    private String allAddress;

    /** 邮编*/
    private String postcode;

    /** 是否默认,0-否，1-是*/
    private Byte isDefault;

    /** 备注*/
    private String notes;


    @Override
    public Long getId() {
        return this.addressId;
    }
}