package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.UserService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 2、我的优惠订单（我的优惠券）
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myOrder")
    public ResponseUtil myOrder(@LoginUser Integer userId) {

        return ResponseUtil.ok();
    }


    /**
     * 3、我的砍价
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myBargain")
    public ResponseUtil myBargain(@LoginUser Integer userId) {
        return ResponseUtil.ok();
    }


    /**
     * 4、我的拼团
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/myCollage")
    public ResponseUtil myCollage(@LoginUser Integer userId) {
        return ResponseUtil.ok();
    }

    /**
     * 5、店面推荐(4-6家精准推荐)
     */
    @GetMapping("/recommendShop")
    public ResponseUtil recommendShop(@LoginUser Integer userId) {
        return ResponseUtil.ok();
    }
}
