package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.entity.Seller;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:商家Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:54
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping
    public String index() {
        return "system/seller.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/seller_form.html";
    }

}
