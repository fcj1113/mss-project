package com.miaoshasha.base.mapper.user;

import com.miaoshasha.common.base.BaseMapper;
import com.miaoshasha.common.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     *  根据手机号码查询登录用户信息
     * @param loginNo
     * @param regChannel
     * @param loginType
     * @return
     */
    public User getLoginUser(@Param("loginNo") String loginNo, @Param("regChannel") Integer regChannel, @Param("loginType") Integer loginType);


    /**
     * 根据手机号码查询用户列表
     * @param userPhone
     * @return
     */
    public List<User> findByUserPhone(@Param("userPhone") String userPhone);

    /**
     * 更新锁的状态
     *
     * @param userId
     * @return
     */
    public boolean updateLockState(@Param("userId") Integer userId, @Param("lockState") Integer lockState) ;

    /**
     * 更改状态
     * @param userId
     * @param state
     * @return
     */
    public boolean updateState(@Param("userId") Integer userId, @Param("state") Integer state);

}