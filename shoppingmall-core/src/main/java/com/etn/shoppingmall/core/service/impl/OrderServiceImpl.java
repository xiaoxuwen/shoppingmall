package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.mapper.OrderMapper;
import com.etn.shoppingmall.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:04
 * Version: V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Order load(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Order order) {
        return orderMapper.insertSelective(order) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Order order) {
        order.setDeleted(true);
        return orderMapper.updateByPrimaryKeySelective(order) > 0;
    }
}
