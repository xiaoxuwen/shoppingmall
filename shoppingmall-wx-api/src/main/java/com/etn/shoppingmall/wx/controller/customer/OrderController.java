package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 顾客端订单接口
 * User: lihu
 * Date: 2018/7/26 11:53
 * Version: V1.0
 */
@Api(value = "订单业务的接口",tags = {"订单业务的Controller"})
@RestController
@RequestMapping("/wx/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "查询订单",notes = "修改订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单id",required = true,dataType = "Integer",paramType = "query")
    })
    @PutMapping("/load")
    public Object load(@RequestParam Integer id){
        return ResponseUtil.ok("id="+id);
    }

    @ApiOperation(value = "修改订单状态",notes = "修改订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn",value = "订单编号",required = true,dataType = "String",paramType = "query")
    })
    @PutMapping("/updateStatus")
    public Object updateStatus(@RequestParam String sn){
        return ResponseUtil.ok("sn="+sn);
    }

    @ApiOperation(value = "添加订单",notes = "添加订单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "productId",value = "预定产品id",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "订单所属店铺ID",required = true,dataType = "Integer",paramType = "query"),
    })
    @PutMapping("/add")
    public Object addOrder(@RequestParam Integer userId,@RequestParam Integer productId,@RequestParam Integer shopId){
        return ResponseUtil.ok("userId="+userId+"productId="+productId+"shopId="+shopId);
    }

    @ApiOperation(value = "删除订单",notes = "删除订单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单id",required = true,dataType = "Integer",paramType = "query")
    })
    @DeleteMapping("/delete")
    public Object deleteOrder(@RequestParam Integer id){
        return ResponseUtil.ok("id="+id);
    }

}
