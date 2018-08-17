package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.ShopUser;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.core.service.ShopUserService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private ShopUserService shopUserService;

    /**
     * 获取产品核销统计结果
     * @param body
     * {
     *     "beforeTime":"2018-08-01 15:42:56",
     *     "endTime":"2018-08-01 15:42:56"
     * }
     * @param shopId 店铺id
     * @return {
     *              "count": 5,         订单总数量
     *              "combCount": 6,     待确认订单数量
     *              "memberCount": 0,   验证会员数量
     *              "successCount": 1,  已成功的订单
     *              "overdueCount": 0  已失效的订单
     *          }
     * @return
     */
    @ApiOperation(value = "产品核销统计",notes = "产品核销统计的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @PostMapping("/statistics")
    public ResponseUtil statistics(@LoginUser Integer shopId ,@RequestBody String body){
        if (shopId == null){
            return ResponseUtil.badArgumentValue();
        }
        String beforeTime = JacksonUtil.parseString(body, "beforeTime");
        String endTime = JacksonUtil.parseString(body, "endTime");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newBeforeTime= LocalDateTime.parse(beforeTime,df);
        LocalDateTime newEndTime= LocalDateTime.parse(endTime,df);
        Map<String,Object> statistics = orderService.listProductStatistics(shopId,newBeforeTime,newEndTime);
        List<ShopUser> shopUserList = shopUserService.countShopUser(shopId,newBeforeTime,newEndTime);
        statistics.put("memberCount",shopUserList.size());
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
//    @ApiOperation(value = "会员消费统计",notes = "会员消费统计的接口")
//    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
//    @PostMapping("/mes")
//    public Object memberExpenseStatistics(@LoginUser Integer shopId,@RequestBody String body){
//        if (shopId == null){
//            return ResponseUtil.badArgumentValue();
//        }
//        String beforeTime = JacksonUtil.parseString(body, "beforeTime");
//        String endTime = JacksonUtil.parseString(body, "endTime");
//
//        List<Map<String,Object>> statistics = orderService.listMemberStatistics(shopId);
//        if (statistics == null){
//            return ResponseUtil.ok(0,"暂无统计信息");
//        }
//        return ResponseUtil.ok(statistics);
//    }


}
