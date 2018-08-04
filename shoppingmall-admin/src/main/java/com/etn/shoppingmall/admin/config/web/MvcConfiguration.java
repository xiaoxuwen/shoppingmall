package com.etn.shoppingmall.admin.config.web;

import com.etn.shoppingmall.admin.web.interceptor.AdminLoginInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration loginInterceptor =registry.addInterceptor(new AdminLoginInterceptor());
		//登录权限限定
		loginInterceptor.addPathPatterns("/");
		loginInterceptor.addPathPatterns("/index");
		loginInterceptor.addPathPatterns("/sys/**");
    }

}
