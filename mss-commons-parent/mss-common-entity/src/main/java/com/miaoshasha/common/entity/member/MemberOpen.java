package com.miaoshasha.common.entity.member;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class MemberOpen  extends AbstractBaseEntity {
    /** */
    private Long pid;

    /** 会员*/
    private Long memberId;

    /** 三方OPENID*/
    private String openId;

    /** 设备id*/
    private String deviceId;

    /** 状态*/
    private Integer state;

    @Override
    public Long getId() {
        return this.pid;
    }
}