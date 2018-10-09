package com.miaoshasha.msgcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 消息中心服务
 * @author fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-09-06
 * Time：10:16
 * ================================
 */
@EnableEurekaClient
@ComponentScan("com.miaoshasha")
@SpringBootApplication
public class MsgCenterApplication {

    public static void main(String[] args){
        SpringApplication.run(MsgCenterApplication.class,args);
    }
}
