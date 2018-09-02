package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.mapper.OrderMapper;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.OrderStatusJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018/9/2  21:19
 * Modified By:
 */
@Service(value = "orderStatusJobService")
public class OrderStatusJobServiceImpl implements OrderStatusJobService {
    private static final Logger log = LoggerFactory.getLogger(OrderStatusJobServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void dealOrder() {
        log.info("Quartz Running Time : " + new Date());
//        Example example = new Example(Order.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("deleted", false);
//        criteria.andEqualTo("status", FinalValue.ORDER_STATUS_UNTAPPED);
//        List<Order> orders = orderMapper.selectByExample(example);
//        for (Order order : orders) {
//        }
    }
}
