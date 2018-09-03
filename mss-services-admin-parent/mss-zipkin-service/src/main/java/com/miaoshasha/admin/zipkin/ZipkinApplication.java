package com.miaoshasha.admin.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-31
 * Time：10:23
 * ================================
 */
@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class ZipkinApplication {

    public static void main(String[] args){
        SpringApplication.run(ZipkinApplication.class,args);
    }
}
