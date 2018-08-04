package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:产品Controller
 * User: xiaoxuwen
 * Date: 2018-07-27 8:52
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "/sys/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping
    public String index() {
        return "system/product.html";
    }

    @RequestMapping("/editForm")
    public String editForm() {
        return "system/product_form.html";
    }

    /**
     * 列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Pager<Product> list(String name) {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return productService.find(name);
    }

    /**
     * 上下架
     *
     * @param productId 产品id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseUtil updateStatus(Integer productId, Boolean status) {
        Product product = new Product();
        product.setId(productId);
        product.setIsOnSale(status);
        if (productService.update(product)) {
            return ResponseUtil.ok(1, "操作成功");
        } else {
            return ResponseUtil.fail(0, "修改失败");
        }
    }
}
