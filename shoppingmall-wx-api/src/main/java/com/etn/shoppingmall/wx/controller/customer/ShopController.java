package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 顾客端店铺接口:
 * User: lihu
 * Date: 2018/7/26 11:47
 * Version: V1.0
 */
@Api(value = "店铺业务的接口", tags = {"店铺业务的controller"})
@RestController
@RequestMapping("/wx/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "不分页获取店铺(人气降序排列)",notes = "不分页获取店铺的接口")
    @GetMapping("/list")
    public Object popularityShopList(){
        return ResponseUtil.ok();
    }

    @ApiOperation(value = "ID获取店铺",notes = "ID获取店铺的接口")
    @ApiImplicitParam(name = "shopId",value = "店铺Id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/shopById")
    public Object shopById(@RequestParam Integer shopId){
        return ResponseUtil.ok("shopId="+shopId);
    }

    @ApiOperation(value = "不分页获取店铺(店铺创建时间降序排列)",notes = "不分页获取店铺的接口")
    @ApiImplicitParam(name = "quantity",value = "查询数量",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/newShopList")
    public Object newShopList(@RequestParam Integer quantity){
        return ResponseUtil.ok("quantity="+quantity);
    }

}
