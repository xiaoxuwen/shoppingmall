package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.BargainService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        BigDecimal nowPrice=new BigDecimal(0);
        for (BargainUser bu : bus) {
            nowPrice.subtract(bu.getPrice());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("bargain", bargain);
        map.put("nowPrice", nowPrice);
        map.put("people", bus.size());
        map.put("bus", bus);
        return ResponseUtil.ok(map);
    }

    /**
     * 3.发起砍价
     *
     * @return
     */
    @PostMapping("/launchBargain")
    public ResponseUtil launchBargain(@LoginUser Integer userId, @RequestParam("bid")Integer bid) {
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }
        Bargain bargain = bargainService.load(bid);
        BigDecimal price = bargain.getPrice().subtract(bargain.getLowPrice());
        Integer people = bargain.getPeople();
        price.divide(new BigDecimal(people));
        BargainUser bargainUser = new BargainUser();
        User user = new User();
        user.setId(userId);
        bargainUser.setUser(user);
        bargainUser.setProductId(bid);
        bargainUser.setFlag(1);
        bargainUser.setPrice(price);
        bargainUser.setAddTime(LocalDateTime.now());
        bargainUser.setDeleted(false);
        bargainService.addBargainUser(bargainUser);
        return ResponseUtil.ok(bargainUser);
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
