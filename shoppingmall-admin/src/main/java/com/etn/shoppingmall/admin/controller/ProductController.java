package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CategoryService;
import com.etn.shoppingmall.core.service.ProductService;
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
@RequestMapping(value = "/sys/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String index() {
        return "system/product.html";
    }

    @RequestMapping("/editForm")
    public String editForm(Model model) {
        List<Shop> shops = shopService.listShop(null);
        model.addAttribute("shops", shops);
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
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

    @ResponseBody
    @PostMapping("/add")
    public ResponseUtil add(Product product) {
        String[] str = product.getValidDate().split(" ~ ");
        product.setStartDate(LocalDateTime.parse(str[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        product.setEndDate(LocalDateTime.parse(str[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        product.setDeleted(false);
        product.setAddTime(LocalDateTime.now());
        if (productService.add(product)) {
            return ResponseUtil.ok(1, "添加成功");
        } else {
            return ResponseUtil.fail(0, "添加失败");
        }
    }

    /**
     * 修改店铺
     **/
    @ResponseBody
    @RequestMapping("/update")
    public ResponseUtil update(Product product) {
        if (productService.update(product)) {
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
    public ResponseUtil delete(Integer productId) {
        if (productService.delete(productId)) {
            return ResponseUtil.ok(1, "删除成功");
        } else {
            return ResponseUtil.fail(0, "删除失败");
        }
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
