package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
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

    @RequestMapping("/userList")
    public String userList() {
        return "system/user.html";
    }

    @RequestMapping("/userVip")
    public String userVip() {
        return "system/user_vip.html";
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
        return userService.findUser(null, phone);
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
        return userService.findUser(FinalValue.USER_LEVEL_VIP, phone);
    }

    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param status 用户状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer userId, Integer status) {
        User user = new User();
        user.setId(userId);
        user.setStatus(status);
        if (userService.update(user)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }
}
