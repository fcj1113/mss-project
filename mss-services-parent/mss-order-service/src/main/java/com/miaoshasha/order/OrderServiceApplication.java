package com.miaoshasha.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@ComponentScan("com.miaoshasha")
@MapperScan("com.miaoshasha.order.mapper")
@EnableCaching
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {
		"com.miaoshasha.api.message"
})
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
