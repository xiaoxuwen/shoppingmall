package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.BargainService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 1、砍价产品列表
     *
     * @return
     */
    @GetMapping("/listBargain")
    public ResponseUtil listBargain() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Bargain> list = bargainService.list();
        return ResponseUtil.ok(list);
    }

    /**
     * 2、砍价详情页
     *
     * @param bid 砍价产品id
     * @return
     */
    @GetMapping("/bargainDetail")
    public ResponseUtil bargainDetail(@RequestParam Integer bid) {
        Bargain bargain = bargainService.load(bid);
        List<BargainUser> bus = bargainService.listBargainUser(bid);
        for (BargainUser bu : bus) {

        }
        Map<String, Object> map = new HashMap<>();
        map.put("bargain", bargain);
        map.put("nowPrice", 0);
        map.put("people", bus.size());
        map.put("bus", bus);
        return ResponseUtil.ok(map);
    }

    /**
     * 3.发起砍价
     *
     * @return
     */
    @PostMapping("/bargain")
    public ResponseUtil b(@LoginUser Integer userId) {
        return null;
    }

    /**
     * 4.参与砍价
     *
     * @param bid
     * @return
     */
    @PostMapping("/addBargain")
    public ResponseUtil addBargain(@RequestParam Integer bid) {
        //需要判断砍价成功，参与人数达到指定人数
        return ResponseUtil.ok();

    }
}
