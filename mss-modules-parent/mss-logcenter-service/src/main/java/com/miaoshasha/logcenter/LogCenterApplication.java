package com.miaoshasha.logcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * 日志中心启动类
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：17:28
 * ================================
 */
@EnableEurekaClient
@ComponentScan("com.miaoshasha")
@SpringBootApplication
public class LogCenterApplication {

    public static void main(String[] args){
        SpringApplication.run(LogCenterApplication.class);
    }
}
