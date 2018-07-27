package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.mapper.OrderMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
    public boolean delete(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setDeleted(true);
        return orderMapper.updateByPrimaryKeySelective(order) > 0;
    }

    /**
     * 分页获取订单
     *
     * @return
     */
    @Override
    public Pager<Order> find() {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Order> list = orderMapper.selectByExample(example);
        PageInfo<Order> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }
}
