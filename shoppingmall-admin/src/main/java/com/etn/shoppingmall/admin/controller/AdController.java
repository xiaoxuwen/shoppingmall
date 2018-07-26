package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Description:广告Controller
 * User: xiaoxuwen
 * Date: 2018-07-26 8:51
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @RequestMapping
    public String index() {
        return "system/ad.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/ad_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Ad> list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return adService.list();
    }


    /**
     * 添加广告
     **/
    @ResponseBody
    @RequestMapping("/add")
    public ResponseUtil add(Ad ad) {
        ad.setEnabled(true);
        ad.setAddTime(LocalDateTime.now());
        ad.setDeleted(false);
        ad.setPosition(1);
        if (adService.add(ad)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改广告
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Ad ad) {
        if (adService.update(ad)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除广告
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer adId) {
        if (adService.delete(adId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

    /**
     * 修改广告状态
     *
     * @param adId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer adId, Boolean status) {
        Ad ad = new Ad();
        ad.setId(adId);
        ad.setEnabled(status);
        if (adService.update(ad)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }
}
