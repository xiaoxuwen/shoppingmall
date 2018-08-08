package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.OrderService;
import com.etn.shoppingmall.core.service.ProductService;
import com.etn.shoppingmall.core.service.UserService;
import com.etn.shoppingmall.wx.model.MemberStatisticsInfo;
import com.etn.shoppingmall.wx.util.StatisticsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @ApiOperation(value = "产品核销统计",notes = "产品核销统计的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/pvs")
    public Object productVerificationStatistics(@RequestParam("shopId") Integer shopId){
        //查询全部订单
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(0);
        Pager<Order> pagerOrder = orderService.find(null,null,null);
        //查询全部产品
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(0);
        Pager<Product> productPager = productService.find("");
        if (pagerOrder.getCode() == -1 || productPager.getCode() == -1){
            return ResponseUtil.fail();
        }
        if (pagerOrder.getCount() == 0){
            return ResponseUtil.ok("暂无核销记录");
        }
        //对数据进行统计
        StatisticsUtil sUtil = new StatisticsUtil(shopId,pagerOrder.getData(),productPager.getData());
        return ResponseUtil.ok(sUtil.getPSI());
    }

    @ApiOperation(value = "会员消费统计",notes = "会员消费统计的接口")
    @ApiImplicitParam(name = "shopId",value = "商铺id",required = true,dataType = "Integer",paramType = "query")
    @GetMapping("/mes")
    public Object memberExpenseStatistics(@RequestParam("shopId") Integer shopId){
        //查询全部订单
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(0);
        Pager<Order> pagerOrder = orderService.find(null,null,null);
        List<Order> orderList = pagerOrder.getData();

        if (pagerOrder.getCode() == -1){
            return ResponseUtil.fail();
        }
        if (pagerOrder.getCount() == 0){
            return ResponseUtil.ok("暂无会员消费记录");
        }

        //清除其他店铺订单和非会员订单
        Set<Integer> userIdSet = new HashSet<Integer>();
        for (int i = orderList.size()-1; i >= 0 ; i--) {
            if (orderList.get(i).getShop().getId() != shopId || userService.load(orderList.get(i).getUser().getId()).getUserLevel() != 1){
                orderList.remove(i);
            }else {
                userIdSet.add(orderList.get(i).getUser().getId());
            }
        }

        List<MemberStatisticsInfo> listMSI = new ArrayList<MemberStatisticsInfo>();
        for (Integer id : userIdSet){
            //首次初始化
            MemberStatisticsInfo MSI = new MemberStatisticsInfo();
            //计算每个会员的消费情况
            for (int i = orderList.size()-1 ; i >= 0 ; i--) {
                if (id == orderList.get(i).getUser().getId()){
                    MSI.setMemberId(id);
                    MSI.setMemberName(orderList.get(i).getUser().getRealName());
                    MSI.setPhone(orderList.get(i).getUser().getPhone());
                    BigDecimal money = productService.load(orderList.get(i).getProduct().getId()).getCounterPrice();
                    if (MSI.getExpenseCount()!=null){
                        money.add(MSI.getExpenseCount());
                    }
                    MSI.setExpenseCount(money);
                    orderList.remove(i);
                }
            }
            listMSI.add(MSI);
        }

        return ResponseUtil.ok(listMSI);
    }


}
