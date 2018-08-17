package com.etn.shoppingmall.wx.annotation.support;

import com.etn.shoppingmall.wx.annotation.LoginShop;
import com.etn.shoppingmall.wx.model.ShopTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/17 10:08
 * Version: V1.0
 */
public class LoginShopHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String LOGIN_TOKEN_KEY = "X-Shoppingmall-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginShop.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            return null;
        }
        return ShopTokenManager.getShopId(token);
    }
}
