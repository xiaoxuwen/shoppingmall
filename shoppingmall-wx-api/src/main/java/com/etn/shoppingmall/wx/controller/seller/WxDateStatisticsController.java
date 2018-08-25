package com.etn.shoppingmall.wx.controller.seller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.ShopUser;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.core.service.ShopUserService;
import com.etn.shoppingmall.wx.annotation.LoginShop;
import com.etn.shoppingmall.wx.model.UserToken;
import com.etn.shoppingmall.wx.model.UserTokenManager;
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

    private static Log  log= LogFactory.getLog(WxDateStatisticsController.class);
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
    public ResponseUtil statistics(@LoginShop Integer shopId , @RequestBody String body){
        if (shopId == null){
            log.info("shopid="+shopId+"   body="+body);
            return ResponseUtil.badArgumentValue();
        }

        String beforeTime = JacksonUtil.parseString(body, "beforeTime");
        String endTime = JacksonUtil.parseString(body, "endTime");
        if(StringUtil.isBlank(beforeTime) || StringUtil.isBlank(endTime)){
            return ResponseUtil.badArgumentValue();
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newBeforeTime= LocalDateTime.parse(beforeTime,df);
        LocalDateTime newEndTime= LocalDateTime.parse(endTime,df);
        Map<String,Object> statistics = orderService.listProductStatistics(shopId,beforeTime,endTime);
        List<ShopUser> shopUserList = shopUserService.countShopUser(shopId,newBeforeTime,newEndTime);
        statistics.put("memberCount",shopUserList.size());
        return ResponseUtil.ok(statistics);
    }

    @GetMapping("/token")
    private ResponseUtil token(Integer id){
        UserToken userToken =UserTokenManager.generateToken(id);
        return ResponseUtil.ok(userToken);
    }

}
