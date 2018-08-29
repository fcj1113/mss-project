package com.miaoshasha.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@ComponentScan("com.miaoshasha")
@MapperScan("com.miaoshasha.seckill.mapper")
@SpringBootApplication
@EnableEurekaClient
public class SeckillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillServiceApplication.class, args);
	}
}
