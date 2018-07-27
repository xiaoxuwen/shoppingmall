package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.model.Pager;

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
}
