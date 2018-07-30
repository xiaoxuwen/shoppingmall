package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

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
        return categoryService.find();
    }

    /**
     * 添加类目
     **/
    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Category category) {
        category.setDeleted(false);
        category.setAddTime(LocalDateTime.now());
        if (categoryService.add(category)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改类目
     **/
    @ResponseBody
    @PostMapping("/update")
    public ResponseUtil update(Category category) {
        if (categoryService.update(category)) {
            return ResponseUtil.ok(1, "修改成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }

    /**
     * 删除类目
     **/
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseUtil delete(Integer categoryId) {
        if (categoryService.delete(categoryId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
    }

}
