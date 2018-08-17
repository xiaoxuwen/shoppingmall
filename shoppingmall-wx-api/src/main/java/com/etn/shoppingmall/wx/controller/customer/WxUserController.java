package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.core.service.ShopService;
import com.etn.shoppingmall.core.service.UserService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.UserTokenManager;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:我的账户
 * User: xiaoxuwen
 * Date: 2018/8/7  9:56
 * Modified By:
 */
@RestController
@RequestMapping("/wx/user")
public class WxUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;

    /**
     * 我的信息-个人二维码
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myInfo")
    public ResponseUtil myInfo(@LoginUser Integer userId) {
        return ResponseUtil.ok(userService.load(userId));
    }


    /**
     * 2、折扣订单
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myOrder")
    public ResponseUtil myOrder(@LoginUser Integer userId,@RequestParam("status") Integer status) {
        if (userId == null || status == null){
            return ResponseUtil.badArgumentValue();
        }
        List<Order> orderList = orderService.myOrder(userId,status,FinalValue.ORDER_TYPE_DIS);
        return ResponseUtil.ok(orderList);
    }


    /**
     * 3、砍价订单
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myBargain")
    public ResponseUtil myBargain(@LoginUser Integer userId,@RequestParam("status") Integer status) {
        if (userId == null || status == null){
            return ResponseUtil.badArgumentValue();
        }
        List<Order> orderList = orderService.myOrder(userId,status,FinalValue.ORDER_TYPE_BARGAIN);
        return ResponseUtil.ok(orderList);
    }


    /**
     * 4、拼团订单
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myCollage")
    public ResponseUtil myCollage(@LoginUser Integer userId,@RequestParam("status") Integer status) {
        if (userId == null || status == null){
            return ResponseUtil.badArgumentValue();
        }
        List<Order> orderList = orderService.myOrder(userId,status,FinalValue.ORDER_TYPE_COLLAGE);
        return ResponseUtil.ok(orderList);
    }

    /**
     * 我的商家入驻申请
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myShopApply")
    public ResponseUtil myShopApply(@LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.fail();
        }
        User user = userService.load(userId);
        List<Shop> shopList = shopService.openidByShop(user.getOpenid());
        return ResponseUtil.ok(shopList);
    }

    /**
     * 5、店面推荐(4-6家精准推荐)
     */
    @GetMapping("/recommendShop")
    public ResponseUtil recommendShop(@LoginUser Integer userId) {
        return ResponseUtil.ok();
    }

}
