package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.SecurityUtil;
import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-25 8:47
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping
    public String index() {
        return "system/admin.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/admin_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Admin> list() {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        return adminService.find();
    }


    /**
     * 添加用户
     **/
    @ResponseBody
    @RequestMapping("/add")
    public ResponseUtil add(Admin admin) {
        admin.setPassword(SecurityUtil.md5("123456"));
        admin.setLoginCount(0);
        admin.setAddTime(LocalDateTime.now());
        if (adminService.add(admin)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改用户
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Admin admin) {
        Admin a = adminService.load(admin.getId());
        a.setRealName(admin.getRealName());
        a.setPhone(admin.getPhone());
        if (adminService.update(a)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除用户
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer userId) {
        Admin admin = adminService.load(userId);
        if (admin != null) {
            if (adminService.delete(admin)) {
                return ResponseUtil.ok(1, "删除成功");
            } else {
                return ResponseUtil.fail(0, "删除失败");
            }
        }
        return ResponseUtil.fail();
    }

    /**
     * 修改自己密码
     **/
    @ResponseBody
    @RequestMapping("/updatePsw")
    public ResponseUtil updatePsw(String oldPsw, String newPsw, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (!SecurityUtil.md5(oldPsw).equals(admin.getPassword())) {
            return ResponseUtil.fail(0, "原密码输入不正确");
        }
        admin.setPassword(SecurityUtil.md5(newPsw));
        if (adminService.update(admin)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail();
        }
    }
}
