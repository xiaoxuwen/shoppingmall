package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.service.ProductService;
import com.etn.shoppingmall.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:产品详情和下单
 * User: xiaoxuwen
 * Date: 2018/8/7  9:53
 * Modified By:
 */
@RestController
@RequestMapping("/wx/user")
public class WxProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;


    /**
     * 产品详情页接口
     * @param productId 产品id
     * @return
     */
    @PostMapping("/detailsProduct")
    public ResponseUtil detailsProduct(@RequestParam("productId")Integer productId){
        Product product = productService.load(productId);
        Shop shop = shopService.load(product.getShopId());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("product",product);
        map.put("shop",shop);
        return ResponseUtil.ok(map);
    }
}
