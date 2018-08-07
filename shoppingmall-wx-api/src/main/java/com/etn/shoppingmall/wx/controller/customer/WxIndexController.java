package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.AdService;
import com.etn.shoppingmall.core.service.CategoryService;
import com.etn.shoppingmall.core.service.ProductService;
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
    @Autowired
    private ProductService productService;

    /**
     * 首页信息(广告数据、行业属性列表、折扣最低的产品)
     *
     * @return
     */
    @GetMapping("/index")
    public ResponseUtil index() {
        Map<String, Object> data = new HashMap<>();
        data.put("ads", adService.listAd());
        data.put("categorys", categoryService.listCategory());
        data.put("products", "");
        return ResponseUtil.ok(data);
    }
}
