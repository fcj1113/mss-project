package com.miaoshasha.common.base;

import lombok.Data;

import java.util.Date;

/**
 * Created by fengchaojun on 2018/7/25.
 */
@Data
public abstract class AbstractBaseEntity implements BaseEntity {

    /**
     * 创建用户ID
     */
    private Long createUser ;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户ID
     */
    private Long updateUser ;

    /**
     * 更新时间
     */
    private Date updateTime;


}
