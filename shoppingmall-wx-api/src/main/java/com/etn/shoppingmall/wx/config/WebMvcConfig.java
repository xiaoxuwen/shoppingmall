package com.etn.shoppingmall.wx.config;

import com.etn.shoppingmall.wx.interceptor.LoginUserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LoginUserInterceptor loginUserInterceptor() {
        return new LoginUserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor())
                .addPathPatterns("/bgm/**")
                .excludePathPatterns("/user/queryPublisher");
        super.addInterceptors(registry);
    }

}
