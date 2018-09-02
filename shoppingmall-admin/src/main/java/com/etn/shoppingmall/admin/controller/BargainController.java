package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.BargainService;
import com.etn.shoppingmall.core.service.CategoryService;
import com.etn.shoppingmall.core.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Description:产品Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:52
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/bargain")
public class BargainController {
    @Autowired
    private BargainService bargainService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String index() {
        return "system/bargain.html";
    }

    @RequestMapping("/editForm")
    public String editForm(Model model) {
        List<Shop> shops = shopService.listShop(null);
        model.addAttribute("shops", shops);
        return "system/bargain_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Bargain> list(String name) {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return bargainService.find(name);
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Bargain bargain) {
        if (!StringUtils.isEmpty(bargain.getValidDate())){
            String[] str = bargain.getValidDate().split(" ~ ");
            bargain.setStartDate(LocalDateTime.parse(str[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            bargain.setEndDate(LocalDateTime.parse(str[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        bargain.setDeleted(false);
        bargain.setAddTime(LocalDateTime.now());
        if (bargainService.add(bargain)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Bargain bargain) {
        if (bargainService.update(bargain)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer bargainId) {
        if (bargainService.delete(bargainId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

    /**
     * 上下架
     *
     * @param bargainId 产品id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer bargainId, Boolean status) {
        Bargain bargain = new Bargain();
        bargain.setId(bargainId);
        bargain.setIsOnSale(status);
        if (bargainService.update(bargain)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

}
