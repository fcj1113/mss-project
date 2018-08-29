package com.miaoshasha.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fengchaojun on 2018/5/16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    LoginRequiredHandler localInterceptor() {
//        return new LoginRequiredHandler();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
//    }
}
