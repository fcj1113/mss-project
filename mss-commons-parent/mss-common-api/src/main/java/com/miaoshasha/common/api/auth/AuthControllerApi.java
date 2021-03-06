package com.miaoshasha.common.api.auth;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：16:10
 * -----------------------------
 */
@RequestMapping(value = "/")
public interface AuthControllerApi {
    /**
     * 登录
     * @param phoneNo 登录手机号码
     * @param password 用户密码
     * @param channel 渠道
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public DataResult<UserDTO> login(@RequestParam("phoneNo") String phoneNo,
                                     @RequestParam("password") String password,
                                     @RequestParam("channel") Integer channel);


    /**
     * 根据token获取登录信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/getUserByToken",method = RequestMethod.POST)
    public DataResult<UserDTO> getUserByToken(@RequestParam("token") String token);

    /**
     * 校验token有效性
     * @param token
     * @return
     */
    @PostMapping(value = "/verifyToken")
    public DataResult<Boolean> verifyToken(@RequestParam("token") String token);
}
