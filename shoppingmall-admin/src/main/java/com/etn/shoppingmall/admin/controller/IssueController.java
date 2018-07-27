package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Issue;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        SystemContext.setSort("add_time");
        SystemContext.setOrder("desc");
        List<Issue> list = issueService.list();
        return new Pager<>(list.size(), list);
    }
}
