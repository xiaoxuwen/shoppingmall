package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.model.Pager;

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
     * 根据商铺id和订单状态获取订单
     * @param shopId 商铺id
     * @param status 订单状态
     * @return
     */
    public List<Order> listByStatusOrId(Integer shopId ,Integer status);

    /**
     * 获取产品核销统计
     * @param shopId 店铺id
     * @return 统计结果
     */
    public List<Map<String,Object>> listProductStatistics(Integer shopId);

    /**
     * 获取会员消费统计
     * @param shopId 店铺id
     * @return 统计结果
     */
    public List<Map<String,Object>> listMemberStatistics(Integer shopId);

}
