package com.etn.shoppingmall.wx.controller.customer;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.RegexUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.UserService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.UserInfo;
import com.etn.shoppingmall.wx.model.UserToken;
import com.etn.shoppingmall.wx.model.WxLoginInfo;
import com.etn.shoppingmall.wx.model.UserTokenManager;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/customer")
public class WxAuthController {
    private final Log logger = LogFactory.getLog(WxAuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private WxMaService wxService;

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @return 登录结果
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data:
     * {
     * token: xxx,
     * tokenExpire: xxx,
     * userInfo: xxx
     * }
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @RequestMapping("/loginByWx")
    public Object loginByWx(@RequestBody WxLoginInfo wxLoginInfo) {
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if (code == null || userInfo == null) {
            return ResponseUtil.badArgument();
        }

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        if (sessionKey == null || openId == null) {
            return ResponseUtil.fail();
        }

        User user = userService.queryByOpenid(openId);
        if (user == null) {
            user = new User();
            user.setOpenid(openId);
            user.setNickName(userInfo.getNickName());
            user.setAvatarUrl(userInfo.getAvatarUrl());
            user.setGender(userInfo.getGender());
            user.setUserLevel(FinalValue.USER_LEVEL_COMMON);
            user.setStatus(FinalValue.USER_STATUS_TRUE);
            user.setAddTime(LocalDateTime.now());
            user.setDeleted(false);

            userService.add(user);
        }

        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", user);
        return ResponseUtil.ok(result);
    }

    /**
     * 账号注册
     *
     * @param body    请求内容
     *                {
     *                username: xxx,
     *                password: xxx,
     *                mobile: xxx
     *                code: xxx
     *                }
     *                其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data:
     * {
     * token: xxx,
     * tokenExpire: xxx,
     * userInfo: xxx
     * }
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("/register")
    public Object register(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request, HttpSession session) {
        String realName = JacksonUtil.parseString(body, "realName");
        String phone = JacksonUtil.parseString(body, "phone");
        String code = JacksonUtil.parseString(body, "code");

        if (realName == null  || phone == null || code == null) {
            return ResponseUtil.badArgument();
        }

        List<User> userList = userService.queryByPhone(phone);
        if (userList.size() > 0) {
            return ResponseUtil.fail(403, "手机号已注册");
        }
        if (!RegexUtil.isMobileExact(phone)) {
            return ResponseUtil.fail(403, "手机号格式不正确");
        }
        User user = userService.load(userId);
        user.setRealName(realName);
        user.setPhone(phone);


        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", user);
        return ResponseUtil.ok(result);
    }


}
