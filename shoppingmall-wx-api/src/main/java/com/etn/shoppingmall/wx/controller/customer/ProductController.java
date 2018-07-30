package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 顾客端产品接口
 * User: lihu
 * Date: 2018/7/26 11:51
 * Version: V1.0
 */
@Api(value = "产品业务的接口", tags = {"产品业务的controller"})
@RestController
@RequestMapping("/wx/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "产品ID检索产品",notes = "产品ID检索产品的接口")
    @ApiImplicitParam(name = "producdtId",value = "产品ID",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/producById")
    public Object productById(@RequestParam Integer producdtId){
        return ResponseUtil.ok("producdtId="+producdtId);
    }

    @ApiOperation(value = "关键字检索产品",notes = "关键字检索产品的接口")
    @ApiImplicitParam(name = "keyword",value = "关键字",required = true,dataType = "String",paramType = "query")
    @GetMapping("/productByKeywordList")
    public Object productByKeywordList(@RequestParam String keyword){
        return ResponseUtil.ok("keyword="+keyword);
    }

    @ApiOperation(value = "不分页获取折扣最低产品",notes = "不分页获取折扣最低产品的接口")
    @GetMapping("/discountList")
    public Object discountList(){
        return ResponseUtil.ok();
    }

    @ApiOperation(value = "不分页获取距离最近产品",notes = "不分页获取距离最近产品的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude",value = "经度",required = true,dataType = "Float",paramType = "query"),
            @ApiImplicitParam(name = "latitude",value = "纬度",required = true,dataType = "Float",paramType = "query")
    })
    @PostMapping("/distanceList")
    public Object distanceList(@RequestParam Float longitude,@RequestParam Float latitude){
        return ResponseUtil.ok("经度:"+longitude+"纬度:"+latitude);
    }

}
