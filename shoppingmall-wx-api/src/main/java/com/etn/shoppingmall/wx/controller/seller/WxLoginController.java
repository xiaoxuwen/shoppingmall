package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.SecurityUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ShopService;
import com.etn.shoppingmall.wx.model.ShopToken;
import com.etn.shoppingmall.wx.model.ShopTokenManager;
import com.etn.shoppingmall.wx.model.UserToken;
import com.etn.shoppingmall.wx.model.UserTokenManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 商家端管理员登录
 * User: lihu
 * Date: 2018/8/17 9:10
 * Version: V1.0
 */
@RestController
@RequestMapping("wx/seller")
public class WxLoginController {

    @Autowired
    private ShopService shopService;

    /**
     * 登录
     * @param body
     * {
     *     phone:"String",
     *     password:"String"
     * }
     * @return
     */
    @PostMapping("/login")
    public ResponseUtil login(@RequestBody String body){
        String phone = JacksonUtil.parseString(body,"phone");
        String password = JacksonUtil.parseString(body,"password");
        if (StringUtil.isBlank(phone,password)){
            return ResponseUtil.fail(-1, "账号密码不能为空");
        }
        Shop shop = shopService.login(phone,SecurityUtil.md5(password));
        if (shop == null){
            return ResponseUtil.fail(2, "用户名/密码错误");
        }
        if (shop.getStatus() != 1){
            return ResponseUtil.fail(3,"店铺不可用或未通过审核");
        }
        ShopToken shopToken = ShopTokenManager.generateToken(shop.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", shopToken.getToken());
        result.put("tokenExpire", shopToken.getExpireTime().toString());
        result.put("shopInfo", shop);
        return ResponseUtil.ok(result);
    }

}
