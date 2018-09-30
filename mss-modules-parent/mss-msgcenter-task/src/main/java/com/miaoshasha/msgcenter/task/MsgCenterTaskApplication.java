package com.miaoshasha.msgcenter.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-30
 * Time：10:30
 * -----------------------------
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.miaoshasha.api.message",
        "com.miaoshasha.api.order"
})
public class MsgCenterTaskApplication {

    public static void main(String[] args){
        SpringApplication.run(MsgCenterTaskApplication.class,args);
    }
}
