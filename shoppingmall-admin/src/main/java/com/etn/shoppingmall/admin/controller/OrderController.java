package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:订单Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:51
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping
    public String index() {
        return "system/order.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/order_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Order> list(String realName, String phone, String sn) {
        return orderService.find(realName,phone, sn);
    }
}
