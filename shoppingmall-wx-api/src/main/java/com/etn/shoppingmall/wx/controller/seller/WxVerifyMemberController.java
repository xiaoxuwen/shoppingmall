package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.ShopUser;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.service.ShopUserService;
import com.etn.shoppingmall.core.service.UserService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.UserToken;
import com.etn.shoppingmall.wx.model.UserTokenManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 商家版会员消费api
 * User: lihu
 * Date: 2018/8/4 18:02
 * Version: V1.0
 */
@Api(tags = {"商家端，会员消费"},description = "会员消费的接口")
@RestController
@RequestMapping("wx/seller")
public class WxVerifyMemberController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopUserService shopUserService;

    /**
     *  扫码二维码验证用户会员身份
     * @param shopId 店铺id
     * @param userId 用户id
     * @return level: 1 会员  0 普通用户
     */
    @ApiOperation(value = "扫描验证会员身份",notes = "扫描验证会员身份的接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/verifyMember")
    public ResponseUtil verifyMember(@LoginUser Integer shopId, @RequestParam("userId") Integer userId){
        if (shopId ==null){
            return ResponseUtil.fail();
        }
        User user = userService.load(userId);
        if(user == null){
            return ResponseUtil.fail(-1,"用户不存在或二维码已失效。");
        }
        //添加此店铺验证次数
        ShopUser shopUser = new ShopUser();
        shopUser.setShopId(shopId);
        shopUser.setUserId(userId);
        shopUser.setAddTime(LocalDateTime.now());
        shopUser.setDeleted(false);
        boolean booShopUser = shopUserService.add(shopUser);
        if (!booShopUser){
            return ResponseUtil.badArgumentValue();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("level",user.getUserLevel());
        map.put("username",user.getRealName());
        map.put("phone",user.getPhone());
        return ResponseUtil.ok(map);
    }

}
