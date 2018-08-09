package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.core.service.ProductService;
import com.etn.shoppingmall.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Description: 商家端数据统计api
 * User: lihu
 * Date: 2018/8/6 10:21
 * Version: V1.0
 */
@Api(tags = {"商家端，数据统计"},description = "数据统计的接口")
@RestController
@RequestMapping("wx/seller")
public class WxDateStatisticsController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /**
     * 获取产品核销统计结果
     * @param shopId 店铺id
     * @return {
     *              "prc": 5,           产品剩余数量
     *              "pc": 6,            产品总数
     *              "pName": "String",  产品名称
     *              "pId": 1,           产品id
     *              "prvc": 0,          产品未核销数量
     *              "pvc": 1            产品核销数量
     *          }
     */
    @ApiOperation(value = "产品核销统计",notes = "产品核销统计的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/pvs")
    public Object productVerificationStatistics(@RequestParam("shopId") Integer shopId){
        if (shopId == null){
            return ResponseUtil.badArgumentValue();
        }
        List<Map<String,Object>> statistics = orderService.listProductStatistics(shopId);
        if (statistics == null){
            return ResponseUtil.ok(0,"暂无统计信息");
        }
        return ResponseUtil.ok(statistics);
    }


    /**
     *  获取会员消费统计结果
     * @param shopId
     * @return
     *  {
     *       "memberAvatarUrl": "http://127.0.0.1:8082/os/storage/fetch/ta4pmytf7ed5vhs0qss6.gif",  会员头像链接
     *       "expenseCount": 50,      会员消费的总金额
     *       "phone": "123456",       会员电话
     *       "memberName": "String",  会员名
     *       "memberId": 1            会员id
     *     }
     */
    @ApiOperation(value = "会员消费统计",notes = "会员消费统计的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/mes")
    public Object memberExpenseStatistics(@RequestParam("shopId") Integer shopId){
        if (shopId == null){
            return ResponseUtil.badArgumentValue();
        }
        List<Map<String,Object>> statistics = orderService.listMemberStatistics(shopId);
        if (statistics == null){
            return ResponseUtil.ok(0,"暂无统计信息");
        }
        return ResponseUtil.ok(statistics);
    }


}
