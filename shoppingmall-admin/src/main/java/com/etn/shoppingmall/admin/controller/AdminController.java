package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-25 8:47
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping
    public String index() {
        return "system/admin.html";
    }

    @ResponseBody
    @GetMapping("/list")
    public Pager<Admin> list() {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        return adminService.find();
    }
}
