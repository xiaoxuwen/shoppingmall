package com.etn.shoppingmall.wx.config;

import com.etn.shoppingmall.wx.annotation.support.LoginShopHandlerMethodArgumentResolver;
import com.etn.shoppingmall.wx.annotation.support.LoginUserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class UseConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
        argumentResolvers.add(new LoginShopHandlerMethodArgumentResolver());
    }
}
