package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Act;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Description:活动专场
 * User: xiaoxuwen
 * Date: 2018/7/31  22:09
 * Modified By:
 */
@Controller
@RequestMapping(value = "/sys/act")
public class ActController {
    @Autowired
    private ActService actService;

    @RequestMapping
    public String index() {
        return "system/act.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/act_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Act> list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return actService.find();
    }

    /**
     * 添加活动
     **/
    @ResponseBody
    @RequestMapping("/add")
    public ResponseUtil add(Act act) {
        act.setIsOnSale(true);
        act.setAddTime(LocalDateTime.now());
        act.setDeleted(false);
        if (actService.add(act)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改活动
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Act act) {
        if (actService.update(act)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除活动
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer actId) {
        if (actService.delete(actId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

    /**
     * 修改活动状态
     *
     * @param actId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer actId, Boolean status) {
        Act act = new Act();
        act.setId(actId);
        act.setIsOnSale(status);
        if (actService.update(act)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }
}
