package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.service.AdService;
import com.etn.shoppingmall.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-31 9:42
 * Version: V1.0
 */
@RestController
@RequestMapping("/wx/customer")
public class WxIndexController {
    @Autowired
    private AdService adService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/index")
    public ResponseUtil index() {
        Map<String, Object> data = new HashMap<>();
        data.put("ads",adService.listAd());
        data.put("categorys",categoryService.listCategory());
        return ResponseUtil.ok(data);
    }
}
