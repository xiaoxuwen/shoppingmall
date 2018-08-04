package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.entity.Issue;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-27 8:50
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @RequestMapping
    public String index() {
        return "system/issue.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/issue_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Issue> list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Issue> list = issueService.list();
        return new Pager<>(list.size(), list);
    }

    /**
     * 添加问题
     **/
    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Issue issue) {
        issue.setDeleted(false);
        issue.setAddTime(LocalDateTime.now());
        if (issueService.add(issue)) {
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
    public ResponseUtil update(Issue issue) {
        if (issueService.update(issue)) {
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
    public ResponseUtil delete(Integer issueId) {
        if (issueService.delete(issueId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

}
