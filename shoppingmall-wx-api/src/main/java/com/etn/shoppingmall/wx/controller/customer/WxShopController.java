package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:商家入驻
 * User: xiaoxuwen
 * Date: 2018/8/7  9:49
 * Modified By:
 */
public class WxShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 1、商家入驻
     * @param body
     * {
     *     openid:"openid",
     *     real_name:"真实姓名",
     *     phone:"手机号",
     *     name:"店名",
     *     address:"地址"
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
        if (openid == null && real_name == null && phone == null && name == null && address == null ){
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
}
