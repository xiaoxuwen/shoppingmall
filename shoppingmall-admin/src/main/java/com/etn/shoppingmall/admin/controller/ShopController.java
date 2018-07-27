package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:店铺Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:55
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index() {
        return "system/shop.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/shop_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Shop> list() {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        return shopService.find();
    }

}
