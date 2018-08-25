package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.model.Pager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:40
 * Version: V1.0
 */
public interface OrderService extends BaseService<Order> {
    /**
     * 分页获取订单
     *
     * @return
     */
    Pager<Order> find(String realName, String phone, String sn);

    /**
     * 不分页获取订单
     * @param shopId     店铺id
     * @param status     订单状态
     * @param beforeTime 开始时间
     * @param endTime    结束时间
     * @return
     */
    public List<Order> list(Integer shopId , Integer status, String beforeTime,String endTime);

    /**
     * 获取产品核销统计
     * @param shopId 店铺id
     * @param beforeTime 开始时间
     * @param endTime    结束时间
     * @return 统计结果
     */
    public Map<String,Object> listProductStatistics(Integer shopId,String beforeTime,String endTime);

    /**
     * 我的订单
     * @param userId 用户id
     * @param status 订单状态
     * @param orderType  订单类型
     * @return
     */
    public List<Order> myOrder(Integer userId,Integer status,Integer orderType);

    /**
     * 验证订单
     * @param userId
     * @param productId
     * @param status
     * @param orderType
     * @return
     */
    List<Order> verificationCoupon(Integer userId,Integer productId,Integer status,Integer orderType);

}
