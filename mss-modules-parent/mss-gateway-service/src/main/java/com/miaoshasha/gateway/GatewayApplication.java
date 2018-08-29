package com.miaoshasha.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by fengchaojun on 2018/7/10.
 */
@ComponentScan("com.miaoshasha")
@EnableZuulProxy
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.miaoshasha.api.base"
})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
