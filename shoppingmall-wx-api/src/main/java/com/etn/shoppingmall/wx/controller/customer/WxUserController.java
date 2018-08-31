package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.*;
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
    @Autowired
    private BargainService bargainService;
    @Autowired
    private CollageUserService collageUserService;

    /**
     * 我的信息- 个人二维码
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
     * 5、店面推荐(4-6家精准推荐)
     */
    @GetMapping("/recommendShop")
    public ResponseUtil recommendShop(@LoginUser Integer userId) {
        return ResponseUtil.ok(shopService.recommendShop());
    }

    /**
     * 5、我的砍价列表
     * @param userId
     * @return
     */
    @GetMapping("/myBargainList")
    public ResponseUtil myBargainList(@LoginUser Integer userId){
        if (userId == null){
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(bargainService.listBargainByUserId(userId));
    }

    /**
     * 6、我的拼团列表
     * @param userId
     * @return
     */
    @GetMapping("/myCollageList")
    public ResponseUtil myCollageList(@LoginUser Integer userId){
        if (userId == null){
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(collageUserService.listCollageByUserId(userId));
    }

}
