package com.miaoshasha.common.entity.user;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 * Created by fengchaojun on 2018/5/7.
 */
@Data
public class UserExt  extends AbstractBaseEntity {
    private Long id;
    // 用户ID
    private Long userId;
    // 扩展字段名称
    private String fieldName;
    // 扩展字段值
    private String fieldValue;
}
