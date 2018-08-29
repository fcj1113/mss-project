package com.miaoshasha.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@ComponentScan("com.miaoshasha")
@MapperScan("com.miaoshasha.base.mapper")
@SpringBootApplication
@EnableEurekaClient
public class BaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseServiceApplication.class, args);
	}
}
