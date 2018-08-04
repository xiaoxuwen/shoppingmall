package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Description:店铺Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:55
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping
    public String index() {
        return "system/shop.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/shop_form.html";
    }

    @GetMapping("/add")
    public String add() {
        return "system/shop_add.html";
    }

    @GetMapping("/detail")
    public String detail() {
        return "system/shop_detail.html";
    }

    @RequestMapping("/shopApply")
    public String apply() {
        return "system/shop_apply.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Shop> list(String name) {
        return shopService.find(FinalValue.SHOP_STATUS_SUCCESS, name);
    }

    @ResponseBody
    @GetMapping("/apply")
    public Pager<Shop> apply(String name) {
        return shopService.find(null, name);
    }

    /**
     * 添加店铺
     **/
    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Shop shop) {
        shop.setStatus(FinalValue.SHOP_STATUS_SUCCESS);
        shop.setDeleted(false);
        shop.setAddTime(LocalDateTime.now());

        if (shopService.add(shop)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改店铺
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Shop shop) {
        if (shopService.update(shop)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除店铺
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer shopId) {
        if (shopService.delete(shopId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

    /**
     * 修改店铺状态
     *
     * @param shopId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer shopId, Integer status) {
        Shop shop = new Shop();
        shop.setId(shopId);
        shop.setStatus(status);
        if (shopService.update(shop)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

}
