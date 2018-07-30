package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 顾客端用户接口
 * User: lihu
 * Date: 2018/7/26 11:34
 * Version: V1.0
 */
@Api(value = "用户业务的接口", tags = {"用户业务的Controller"})
@RestController
@RequestMapping("wx/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录",notes = "登录的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "凭证",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "iv",value = "iv",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "encryptedDate",value = "encryptedDate",required = true,dataType = "String",paramType = "query")
    })
    @PostMapping("/login")
    public Object login(@RequestParam String code,@RequestParam String iv,@RequestParam String encryptedDate){
        return ResponseUtil.ok("code:"+code+"iv:"+iv+"encryptedDate:"+encryptedDate);
    }

    @ApiOperation(value = "openid获取用户信息",notes = "openid获取用户信息的接口")
    @ApiImplicitParam(name = "openid",value = "openid",required = true,dataType = "String",paramType = "query")
    @GetMapping("/getUser")
    public Object getUser(@RequestParam String openid){
        return ResponseUtil.ok("openid:"+openid);
    }

    @ApiOperation(value = "获取验证码",notes = "获取验证码的接口")
    @ApiImplicitParam(name = "phone",value = "手机号",required = true,dataType = "String",paramType = "query")
    @GetMapping("/getCode")
    public Object getCode(@RequestParam String phone){
        return ResponseUtil.ok("phone:"+phone);
    }

    @ApiOperation(value = "开通会员",notes = "开通会员的接口")
    @ApiImplicitParam(name = "phone",value = "phone",required = true,dataType = "String",paramType = "query")
    @PutMapping("/openMember")
    public Object openMember(@RequestParam String phone){
        return ResponseUtil.ok("phone:"+phone);
    }


}
