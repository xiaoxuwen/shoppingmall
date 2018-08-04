package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.SecurityUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.common.util.UserAgentGetter;
import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.service.AdminService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * MainController
 */
@Controller
public class LoginController implements ErrorController {

    @Autowired
    private AdminService adminService;

    /**
     * 主页
     */
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "index.html";
    }

    /**
     * 登录页
     */
    @GetMapping("/login")
    public String login(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "redirect:index";
        }
        return "login.html";
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping("/login")
    public Object doLogin(String username, String password, String code, HttpServletRequest request, HttpSession session) {
        if (StringUtil.isBlank(username, password)) {
            return ResponseUtil.fail(-1, "账号密码不能为空");
        }
        CaptchaUtil captcha = new CaptchaUtil();
        if (captcha == null || !captcha.ver(code.toLowerCase(), request)) {
            return ResponseUtil.fail(-1, "验证码不正确");
        }
        try {
            Admin admin = adminService.login(username, SecurityUtil.md5(password));
            if (admin == null) return ResponseUtil.fail(2, "用户名/密码错误");
            session.setAttribute("admin", admin);

            admin.setLoginCount(admin.getLoginCount() + 1);
            admin.setLastLoginTime(LocalDateTime.now());
            UserAgentGetter agentGetter = new UserAgentGetter(request);
            admin.setLastLoginIp(agentGetter.getIpAddr());
            adminService.update(admin);
            return ResponseUtil.ok("登录成功");
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }

    /**
     * 图形验证码
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaUtil captcha = new CaptchaUtil(130, 38, 5);
        try {
            captcha.out(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * iframe页
     */
    @RequestMapping("/iframe")
    public String error(String url, Model model) {
        model.addAttribute("url", url);
        return "tpl/iframe.html";
    }

    /**
     * 错误页
     */
    @RequestMapping("/error")
    public String error(String code) {
        if ("403".equals(code)) {
            return "error/403.html";
        }
        return "error/404.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) session.invalidate();
        return "redirect:/login";
    }

}
