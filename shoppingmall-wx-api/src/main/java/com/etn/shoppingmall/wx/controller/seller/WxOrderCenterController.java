package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.JacksonUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Description: 商家端，订单中心
 * User: lihu
 * Date: 2018/8/11 15:42
 * Version: V1.0
 */
@RestController
@RequestMapping("wx/seller")
public class WxOrderCenterController {

    @Autowired
    private OrderService orderService;

    /**
     *  获取订单
     * @param shopId
     * @param body
     * {
     *     status:1,   //订单状态;
     *     beforeTime:"yyyy-MM-dd HH:mm:ss", //开始时间
     *     endTime:"yyyy-MM-dd HH:mm:ss"     //结束时间
     * }
     * @return
     */
    @PostMapping("/list")
    public ResponseUtil list(@LoginUser Integer shopId ,@RequestBody String body){
        String status = JacksonUtil.parseString(body, "status");
        String beforeTime = JacksonUtil.parseString(body, "beforeTime");
        String endTime = JacksonUtil.parseString(body, "endTime");
        if (shopId == null && beforeTime == null && endTime == null){
            return ResponseUtil.badArgument();
        }
        Integer newStatus = null;
        if (status != null){
            newStatus=Integer.parseInt(status);
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newBeforeTime= LocalDateTime.parse(beforeTime,df);
        LocalDateTime newEndTime= LocalDateTime.parse(endTime,df);
        List<Order> orderList=orderService.list(shopId,newStatus,newBeforeTime,newEndTime);
        return ResponseUtil.ok(orderList);
    }


}