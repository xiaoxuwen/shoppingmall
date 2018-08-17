package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CategoryService;
import com.etn.shoppingmall.core.service.CollageService;
import com.etn.shoppingmall.core.service.ShopService;
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
@RequestMapping(value = "/sys/collage")
public class CollageController {
    @Autowired
    private CollageService collageService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String index() {
        return "system/collage.html";
    }

    @RequestMapping("/editForm")
    public String editForm(Model model) {
        List<Shop> shops = shopService.listShop(null);
        model.addAttribute("shops", shops);
        return "system/collage_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Collage> list(String name) {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return collageService.find(name);
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Collage collage) {
        String[] str = collage.getValidDate().split(" ~ ");
        collage.setStartDate(LocalDateTime.parse(str[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        collage.setEndDate(LocalDateTime.parse(str[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        collage.setDeleted(false);
        collage.setAddTime(LocalDateTime.now());
        if (collageService.add(collage)) {
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
    public ResponseUtil update(Collage collage) {
        if (collageService.update(collage)) {
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
    public ResponseUtil delete(Integer collageId) {
        if (collageService.delete(collageId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

    /**
     * 上下架
     *
     * @param collageId 产品id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer collageId, Boolean status) {
        Collage collage = new Collage();
        collage.setId(collageId);
        collage.setIsOnSale(status);
        if (collageService.update(collage)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

}
