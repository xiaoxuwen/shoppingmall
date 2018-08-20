package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/wx/user")
public class WxIndexController {
    @Autowired
    private AdService adService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ActService actService;

    /**
     * 1、首页信息(广告数据、行业属性列表、折扣最低的产品)
     *
     * @return
     */
    @GetMapping("/index")
    public ResponseUtil index() {
        Map<String, Object> data = new HashMap<>();
        data.put("ads", adService.listAd());
        data.put("categorys", categoryService.listCategory());
        data.put("products", productService.listDiscountProduct(null));
        return ResponseUtil.ok(data);
    }

    /**
     * 2、折扣最低的产品
     *
     * @param name 产品名称
     * @return
     */
    @PostMapping("/discountProduct")
    public ResponseUtil discountProduct(String name) {
        return ResponseUtil.ok(productService.listDiscountProduct(name));
    }

    /**
     * 3、距离最近的产品(用户与店铺的距离)
     *
     * @param name 产品名称
     * @return
     */
    @PostMapping("/distanceProduct")
    public ResponseUtil distanceProduct(String name,Double latitude,Double longitude) {
        if (latitude == null || longitude == null){
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(productService.listDistanceProduct(name,latitude,longitude));
    }

    /**
     * 4、人气最旺的店铺(店铺的订单数量降序)
     *
     * @param name 产品名称
     * @return
     */
    @PostMapping("/hotShop")
    public ResponseUtil hotShop(String name) {
        SystemContext.setSort("order_num");
        SystemContext.setOrder("desc");
        return ResponseUtil.ok(shopService.listShop(name));
    }

    /**
     * 5、广告链接-活动专场
     *
     * @return
     */
    @GetMapping("/act")
    public ResponseUtil act() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return ResponseUtil.ok(actService.listAct());
    }

    /**
     * 6、广告链接-新店展示
     *
     * @return
     */
    @GetMapping("/newShop")
    public ResponseUtil newShop() {
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        return ResponseUtil.ok(shopService.listShop(null));
    }

    /**
     * 7、产品筛选（行业属性筛选）
     *
     * @param categoryId 行业属性id
     * @return
     */
    @PostMapping("/listProduct")
    public ResponseUtil listProduct(Integer categoryId) {
        return ResponseUtil.ok(productService.listProduct(categoryId));
    }
}
