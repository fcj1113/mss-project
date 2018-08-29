package com.miaoshasha.common.entity.member;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Member extends AbstractBaseEntity {
    /** */
    private Long memberId;

    /** */
    private Long userId;

    /** 会员姓名*/
    private String memberName;

    /** 会员手机号码*/
    private String memberPhone;

    /** 昵称*/
    private String nickname;

    /** 会员身份证号*/
    private String idcardNo;

    /** 性别，1-男，2-女*/
    private Integer sex;

    /** 生日*/
    private Date birthday;

    /** 级别*/
    private Integer leval;

    /** 头像图片URL*/
    private String avatar;

    /** 状态*/
    private Integer state;

    /** 来源*/
    private Integer source;

    /** */
    private String notes;


    @Override
    public Long getId() {
        return this.memberId;
    }
}