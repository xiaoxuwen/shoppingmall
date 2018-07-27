package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:类目Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:50
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String index() {
        return "system/category.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/category_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Category> list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Category> list = categoryService.list();
        return new Pager<>(list.size(), list);
    }
}
