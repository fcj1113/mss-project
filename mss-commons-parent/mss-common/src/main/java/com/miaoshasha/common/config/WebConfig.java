package com.miaoshasha.common.config;

import com.miaoshasha.common.filter.TraceRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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


    /**
     * 启动
     * @return
     */
    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("webAccessFilter");
        registration.setOrder(1);
        return registration;
    }
}
