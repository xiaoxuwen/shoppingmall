package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Keyword;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:关键字
 * User: xiaoxuwen
 * Date: 2018-07-27 8:51
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/keyword")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;

    @RequestMapping
    public String index() {
        return "system/keyword.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/keyword_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Keyword> list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Keyword> list = keywordService.list();
        return new Pager<>(list.size(), list);
    }

    /**
     * 添加问题
     **/
    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Keyword keyword) {
        keyword.setDeleted(false);
        keyword.setAddTime(LocalDateTime.now());
        if (keywordService.add(keyword)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改问题
     **/
    @ResponseBody
    @PostMapping("/update")
    public ResponseUtil update(Keyword keyword) {
        if (keywordService.update(keyword)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除问题
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer keywordId) {
        if (keywordService.delete(keywordId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }
}
