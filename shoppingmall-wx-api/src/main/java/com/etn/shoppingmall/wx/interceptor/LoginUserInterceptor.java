package com.etn.shoppingmall.wx.interceptor;

import com.etn.shoppingmall.common.util.JsonUtils;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.wx.model.UserTokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-08-23 14:06
 * Version: V1.0
 */
public class LoginUserInterceptor implements HandlerInterceptor {

    public static final String USER_LOGIN_TOKEN_KEY = "X-Shoppingmall-Token";

    /**
     * 拦截请求，在controller调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String userToken = request.getHeader(USER_LOGIN_TOKEN_KEY);

        if (StringUtils.isNotBlank(userToken)) {
            Integer uniqueToken = UserTokenManager.getUserId(userToken);
            if (uniqueToken == null) {
                returnErrorResponse(response, ResponseUtil.unlogin());
                return false;
            }
        } else {
            returnErrorResponse(response, ResponseUtil.unlogin());
            return false;
        }
        /**
         * 返回 false：请求被拦截，返回
         * 返回 true ：请求OK，可以继续执行，放行
         */
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, ResponseUtil result) throws IOException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 请求controller之后，渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {
    }

    /**
     * 请求controller之后，视图渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
    }

}

