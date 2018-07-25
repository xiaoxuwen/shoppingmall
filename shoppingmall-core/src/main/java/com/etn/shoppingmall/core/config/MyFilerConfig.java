package com.etn.shoppingmall.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-25 14:26
 * Version: V1.0
 */
@Configuration
public class MyFilerConfig {
    /**
     * 配置过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/*");
        registration.setName("systemContextFilter");
        return registration;
    }

    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean(name = "systemContextFilter")
    public Filter sessionFilter() {
        return new SystemContextFilter();
    }
}
