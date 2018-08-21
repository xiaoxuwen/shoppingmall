package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.BargainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:砍价产品
 * User: xiaoxuwen
 * Date: 2018/8/7  9:47
 * Modified By:
 */
@RestController
@RequestMapping("wx/user")
public class WxBargainController {

    @Autowired
    private BargainService bargainService;

    /**
     * 砍价产品列表
     * @return
     */
    @GetMapping("/listBargain")
    public ResponseUtil listBargain(){
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Bargain> list = bargainService.list(null);
        return ResponseUtil.ok(list);
    }
}
