package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 商家版核销api
 * User: lihu
 * Date: 2018/8/4 10:29
 * Version: V1.0
 */
@Api(tags = {"商家端，核销"},description = "核销的接口")
@RestController
@RequestMapping("wx/seller")
public class WxVerificationController {

    private final Log logger = LogFactory.getLog(WxVerificationController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 核销api
     * @param sn  订单编号
     * @return 1 核销成功  0  核销失败
     */
    @ApiOperation(value = "核销",notes = "核销的接口")
    @ApiImplicitParam(name = "sn",value = "订单编号",required = true,dataType = "String",paramType = "query")
    @GetMapping("/verification")
    public Object verification(@RequestParam("sn") String sn){
        logger.info("调用核销接口成功，核销开始！");
        //验证参数
        if(sn.isEmpty() || sn == null){
            logger.info("参数为空！");
            return ResponseUtil.badArgumentValue();
        }
        //查询订单
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(0);
        Pager<Order> pagerOrder=orderService.find(null,null,sn);
        if (pagerOrder.getCode() == 1){
            logger.info("查询失败！");
            return ResponseUtil.fail(0,pagerOrder.getMsg());
        }else {
            logger.info("查询成功，Pager信息："+pagerOrder);
        }
        //查询数据为空
        if(pagerOrder.getCount() == 0){
            logger.info("核销失败未检测到此订单！");
            return ResponseUtil.fail(0,"核销失败,未检测到此订单！");
        }
        Order date = pagerOrder.getData().get(0);
        //检测订单是否可用
        if (date.getStatus() != 1){
            logger.info("核销失败,预定产品已使用或已过期");
            return ResponseUtil.fail(0,"核销失败,预定产品已使用或已过期");
        }
        //获取预定产品信息
        Product product = date.getProduct();
        if(product == null){
            return ResponseUtil.fail(0,"核销失败,订单错误:产品不存在");
        }
        //获取当前系统时间
        LocalDate localDate = LocalDate.now();
        //验证预定产品是否过期或未到使用期限
        if (product.getDuring() == 2){
            if(product.getStartDate().isAfter(localDate)){
                logger.info("核销失败,预定产品还未到使用时间!");
                return ResponseUtil.fail(0,"核销失败,预定产品还未到使用时间!");
            }
            if (product.getEndDate().isBefore(localDate)){
                logger.info("核销失败,预定产品已过期!");
                return ResponseUtil.fail(0,"核销失败,预定产品已过期!");
            }
        }

        /**
         * 验证订单与此店铺是否对应
         */

        //验证完毕修改订单状态
        date.setStatus(2);
        boolean state = orderService.update(date);
        if(!state){
            logger.info("系统内部错误：修改订单状态失败！");
            return ResponseUtil.serious();
        }
        return ResponseUtil.fail(1,"核销成功");
    }

    /**
     * 获取核销记录
     * @param shopId  店铺id
     * @return
     */
    @ApiOperation(value = "获取核销记录",notes = "获取核销记录的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/verificationRecord")
    public Object verificationRecord(@RequestParam("shopId") Integer shopId){
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(0);
        Pager<Order> orderPager = orderService.find(null,null,null);
        List<Order> orderList = orderPager.getData();
        List<Order> endOrderList = new ArrayList<Order>();
        //清除掉此店铺之外的订单
        for (Order order : orderList){
            Shop shop = order.getShop();
            if(shop == null){
                logger.error("订单异常:订单编号="+order.getSn()+"的商铺不存在");
                continue;
            }
            if(shop.getId() == shopId && order.getStatus() == 2){
                endOrderList.add(order);
            }
        }
        orderPager.setData(endOrderList);
        return ResponseUtil.ok(orderPager);
    }
}
