package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 顾客端类目接口
 * User: lihu
 * Date: 2018/7/26 11:58
 * Version: V1.0
 */
@Api(value = "类目业务的接口",tags = {"类目业务的Controller"})
@RestController
@RequestMapping("/wx/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "不分页获取类目",tags = "不分页获取类目的接口")
    @GetMapping("/list")
    public Object list(){
        return ResponseUtil.ok();
    }
}
