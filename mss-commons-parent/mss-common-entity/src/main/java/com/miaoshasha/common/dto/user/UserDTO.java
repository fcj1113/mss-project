package com.miaoshasha.common.dto.user;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fengchaojun on 2018/5/7.
 */
@Data
public class UserDTO implements Serializable{
    private static final long serialVersionUID = 6229555536330792490L;

    // 用户ID
    private Long userId;
    // 登录类型，0-都可以，1-手机号码，2-用户名，3-邮箱
    private Integer loginType;
    //
    private String userPhone;
    //
    private String userName;
    //
    private String email;
    // 昵称
    private String nickname;
    // 密码
    private String password;

    // 生效日期
    private Date effectiveDate;
    // 失效日期
    private Date expireDate;
    // 注册渠道
    private Integer regChannel;
    // 最后登录时间
    private Date lastLogin;
    // 启停状态，0-未启用，1已启用，2已停用
    private Integer state;
    // 锁定状态，0-未锁定，1-已锁定
    private Byte lockState;
    // 创建时间
    private Date createTime;
    // 创建用户
    private Long createUser;
    //
    private Date updateTime;
    //
    private Long updateUser;
    //角色ID
    private Integer roleId ;
    //角色名称
    private String roleName ;
    //角色编码
    private String roleCode;

    private String token ;
}
