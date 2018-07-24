package com.etn.shoppingmall.admin.config.web;

import com.etn.shoppingmall.admin.web.interceptor.AdminLoginInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置默认Sevlet
     * 对静态资源默认servlet配置，加入 对.js .gif .img等资源的处理， 允许使用"/"进行整体映射
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//		InterceptorRegistration loginInterceptor =registry.addInterceptor(new AdminLoginInterceptor());
//		String shopLoginInterceptPath = "/index";
//
//		//登录权限限定
//		loginInterceptor.addPathPatterns(shopLoginInterceptPath);
//
//
//		InterceptorRegistration permissionIR = registry.addInterceptor(new ShopPermissionInterceptor());
//		// 配置拦截的路径
//		permissionIR.addPathPatterns(shopLoginInterceptPath);
//		// 配置不拦截的路径
//		/** shoplist page **/
//		permissionIR.excludePathPatterns("/shopadmin/shoplist");
//		permissionIR.excludePathPatterns("/shopadmin/getshoplist");
//		/** shopregister page **/
//		permissionIR.excludePathPatterns("/shopadmin/getshopinitinfo");
//		permissionIR.excludePathPatterns("/shopadmin/registershop");
//		permissionIR.excludePathPatterns("/shopadmin/shopoperation");
//		/** shopmanage page **/
//		permissionIR.excludePathPatterns("/shopadmin/shopmanagement");
//		permissionIR.excludePathPatterns("/shopadmin/getmanagementinfo");
//		/** shopauthmanagement page **/
//		permissionIR.excludePathPatterns("/shopadmin/addshopauthmap");
//		/** scan **/
//		permissionIR.excludePathPatterns("/shopadmin/adduserproductmap");
//		permissionIR.excludePathPatterns("/shopadmin/exchangeaward");
    }

}
