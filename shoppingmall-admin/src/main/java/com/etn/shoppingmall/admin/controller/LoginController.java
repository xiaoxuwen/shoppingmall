package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * MainController
 */
@Controller
public class LoginController implements ErrorController {
    /**
     * 主页
     */
    @RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

    /**
     * 登录页
     */
    @GetMapping("/login")
    public String login(HttpSession session) {
//        if (getLoginUser() != null) {
//            return "redirect:index";
//        }
        return "login.html";
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping("/login")
    public Object doLogin(String username, String password, String code, HttpServletRequest request) {
        if (StringUtil.isBlank(username, password)) {
            return ResponseUtil.fail(-1, "账号密码不能为空");
        }
        CaptchaUtil captcha = new CaptchaUtil();
        if (captcha == null || !captcha.ver(code.toLowerCase(), request)) {
            return ResponseUtil.fail(-1, "验证码不正确");
        }
        try {

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

}
