package com.etn.shoppingmall.admin.web.interceptor;

import com.etn.shoppingmall.core.entity.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:登录拦截器
 * User: xiaoxuwen
 * Date: 2018-05-30 14:12
 * Version: V1.0
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return super.preHandle(request, response, handler);
    }
}
