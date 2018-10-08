package com.miaoshasha.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@SpringBootApplication
@ComponentScan("com.miaoshasha")
@MapperScan(basePackages = "com.miaoshasha.seckill.mapper")
@EnableEurekaClient
@EnableFeignClients(basePackages = {
		"com.miaoshasha.api.message"
})
public class SeckillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillServiceApplication.class, args);
	}
}
