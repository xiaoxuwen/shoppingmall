package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param latitude
     * @param longitude
     * @return
     */
    @GetMapping("/index")
    public ResponseUtil index(Double latitude,Double longitude) {
        if (latitude == null || longitude == null){
            latitude = FinalValue.INITIAL_LATITUDE;
            longitude = FinalValue.INITIAL_LONGITUDE;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("ads", adService.listAd());
        data.put("categorys", categoryService.listCategory());
        data.put("products", productService.listDiscountProduct(null,latitude,longitude));
        return ResponseUtil.ok(data);
    }

    /**
     * 2、折扣最低的产品
     *
     * @param body 产品名称
     * {
     *      name:"",
     *     latitude:"",
     *    longitude:"",
     * }
     * @return
     */
    @PostMapping("/discountProduct")
    public ResponseUtil discountProduct(@RequestBody String body) {
        Double latitude = JacksonUtil.parseDouble(body,"latitude");
        Double longitude = JacksonUtil.parseDouble(body,"longitude");
        String name = JacksonUtil.parseString(body,"name");
        if (latitude == null || longitude == null){
            latitude = FinalValue.INITIAL_LATITUDE;
            longitude = FinalValue.INITIAL_LONGITUDE;
        }
        return ResponseUtil.ok(productService.listDiscountProduct(name,latitude,longitude));
    }

    /**
     * 3、距离最近的产品(用户与店铺的距离)
     *
     * @param body
     * {
     *     name:"",
     *     latitude:"",
     *     longitude:"",
     * }
     * @return
     */
    @PostMapping("/distanceProduct")
    public ResponseUtil distanceProduct(@RequestBody String body) {
        Double latitude = JacksonUtil.parseDouble(body,"latitude");
        Double longitude = JacksonUtil.parseDouble(body,"longitude");
        String name = JacksonUtil.parseString(body,"name");
        if (latitude == null || longitude == null){
            latitude = FinalValue.INITIAL_LATITUDE;
            longitude = FinalValue.INITIAL_LONGITUDE;
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
     * 7、折扣产品筛选（行业属性筛选）
     * @param body
     * {
     *     categoryId:"",
     *     latitude:"",
     *     longitude:"",
     * }
     * @return
     */
    @PostMapping("/listProduct")
    public ResponseUtil listProduct(@RequestBody String body) {
        Double latitude = JacksonUtil.parseDouble(body,"latitude");
        Double longitude = JacksonUtil.parseDouble(body,"longitude");
        Integer categoryId = JacksonUtil.parseInteger(body,"categoryId");
        if(categoryId == null){
            return ResponseUtil.badArgument();
        }
        if (latitude == null || longitude == null){
            latitude = FinalValue.INITIAL_LATITUDE;
            longitude = FinalValue.INITIAL_LONGITUDE;
        }
        return ResponseUtil.ok(productService.listDiscountProductByCategory(categoryId,latitude,longitude));
    }

    /**
     * 8、距离最近的产品筛选（行业属性筛选）
     *
     * @param body
     * {
     *     latitude:"Double",
     *     longitude:"Double",
     *     categoryId:"Integer"
     * }
     * @return
     */
    @PostMapping("/distanceProductByCategory")
    public ResponseUtil distanceProductByCategory(@RequestBody String body) {
        Double latitude = JacksonUtil.parseDouble(body,"latitude");
        Double longitude = JacksonUtil.parseDouble(body,"longitude");
        Integer categoryId = JacksonUtil.parseInteger(body,"categoryId");
        if(categoryId == null){
            return ResponseUtil.badArgument();
        }
        if (latitude == null || longitude == null){
            latitude = FinalValue.INITIAL_LATITUDE;
            longitude = FinalValue.INITIAL_LONGITUDE;
        }
        return ResponseUtil.ok(productService.listDistanceProductByCategory(categoryId,latitude,longitude));
    }

    /**
     * 9、人气最旺的店铺(店铺的订单数量降序)(行业属性筛选)
     *
     * @param body
     * {
     *     name:"String",  店铺名
     *     categoryId:"Integer" 行业属性id
     * }
     * @return
     */
    @PostMapping("/hotShopByCategoryId")
    public ResponseUtil hotShopByCategoryId(@RequestBody String body) {
        Integer categoryId = JacksonUtil.parseInteger(body,"categoryId");
        String name = JacksonUtil.parseString(body,"name");
        if (categoryId == null){
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(shopService.listShop(name,categoryId));
    }
}
