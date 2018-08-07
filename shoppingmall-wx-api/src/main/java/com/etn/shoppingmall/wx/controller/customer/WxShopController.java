package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Description:商家入驻
 * User: xiaoxuwen
 * Date: 2018/8/7  9:49
 * Modified By:
 */
public class WxShopController {
    /**
     * 1、商家入驻
     *
     * @return
     */
    @PostMapping("/shopApply")
    public ResponseUtil shopApply() {
        return ResponseUtil.ok();
    }
}
