package com.miaoshasha.admin.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * todo: 需要加入服务下线消息提醒
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-30
 * Time：12:12
 * ================================
 */
@EnableAdminServer
@SpringBootApplication
@EnableEurekaClient
public class MonitorApplication {

    public static void main(String[] args){
        SpringApplication.run(MonitorApplication.class,args);
    }
}
