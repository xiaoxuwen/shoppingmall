package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-26 17:12
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public String index() {
        return "system/user.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/user_form.html";
    }

    /**
     * 普通用户列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<User> list(@RequestParam(required = false) String phone) {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        if (StringUtil.isBlank(phone))
            return userService.findUser(FinalValue.USER_LEVEL_COMMON, null);
        else return userService.findUser(FinalValue.USER_LEVEL_COMMON, phone);
    }

    /**
     * 普通用户列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/vip")
    public Pager<User> vip(@RequestParam(required = false) String phone) {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        if (StringUtil.isBlank(phone))
            return userService.findUser(FinalValue.USER_LEVEL_VIP, null);
        else return userService.findUser(FinalValue.USER_LEVEL_VIP, phone);
    }
}
