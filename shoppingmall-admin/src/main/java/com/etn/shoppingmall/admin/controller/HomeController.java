package com.etn.shoppingmall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller()
@RequestMapping("/home")
public class HomeController {

    /**
     * 控制台
     */
    @RequestMapping("/console")
    public String console() {
        return "home/console.html";
    }

    /**
     * 消息弹窗
     */
    @RequestMapping("/message")
    public String message() {
        return "tpl/message.html";
    }

    /**
     * 修改密码弹窗
     */
    @RequestMapping("/password")
    public String password() {
        return "tpl/password.html";
    }

    /**
     * 主题设置弹窗
     */
    @RequestMapping("/theme")
    public String theme() {
        return "tpl/theme.html";
    }

    /**
     * 设置主题
     */
    @RequestMapping("/setTheme")
    public String setTheme(String themeName, HttpSession session) {
        if (null == themeName) {
            session.removeAttribute("theme");
        } else {
            session.setAttribute("theme", themeName);
        }
        return "redirect:/index";
    }
}
