package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.BargainService;
import com.etn.shoppingmall.core.service.CollageService;
import com.etn.shoppingmall.core.service.ProductService;
import com.etn.shoppingmall.core.service.ShopService;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Description:店铺
 * User: xiaoxuwen
 * Date: 2018/8/7  9:49
 * Modified By:
 */
@RestController
@RequestMapping("wx/user")
public class WxShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BargainService bargainService;
    @Autowired
    private CollageService collageService;

    /**
     * 1、商家入驻
     * @param body
     * {
     *     openid:"openid",
     *     real_name:"真实姓名",
     *     phone:"手机号",
     *     name:"店名",
     *     address:"地址",
     *     code:"短信验证码"
     * }
     * @return
     */
    @PostMapping("/shopApply")
    public ResponseUtil shopApply(@RequestBody String body) {
        String openid=JacksonUtil.parseString(body,"openid");
        String real_name=JacksonUtil.parseString(body,"real_name");
        String phone=JacksonUtil.parseString(body,"phone");
        String name=JacksonUtil.parseString(body,"name");
        String address=JacksonUtil.parseString(body,"address");
        String code=JacksonUtil.parseString(body,"code");
        if (openid == null && real_name == null && phone == null && name == null && address == null || code == null){
            return ResponseUtil.badArgument();
        }
        Shop shop = new Shop();
        shop.setOpenid(openid);
        shop.setRealName(real_name);
        shop.setPhone(phone);
        shop.setName(name);
        shop.setAddress(address);
        shop.setStatus(FinalValue.SHOP_STATUS_ING);
        boolean addStuts = shopService.add(shop);
        if (!addStuts){
            return ResponseUtil.serious();
        }
        return ResponseUtil.ok();
    }

    /**
     * 2.店铺详情页
     * @param shopId
     * @return
     */
    @GetMapping("/detailsShop")
    public ResponseUtil detailsShop(@RequestParam("shopId") Integer shopId){
        if (shopId == null){
            return ResponseUtil.badArgument();
        }

        Shop shop = shopService.load(shopId);
        List<Product> products = productService.listPrductByShopId(shopId);
        List<Bargain> bargains = bargainService.listBargainByShopId(shopId);
        List<Collage> collages = collageService.listCollageByShopId(shopId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("shop",shop);
        result.put("products",new ResponseUtil(1,"折扣商品",products));
        result.put("bargains",new ResponseUtil(2,"砍价商品",bargains));
        result.put("collages",new ResponseUtil(3,"拼团商品",collages));
        return ResponseUtil.ok(result);
    }
}
