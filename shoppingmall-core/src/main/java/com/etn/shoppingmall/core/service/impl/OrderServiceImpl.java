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

import java.util.List;
import java.util.Map;

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
    public Pager<Order> find(String realName, String phone, String sn) {
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Order> list = orderMapper.find(realName, phone, sn,null,null);
        PageInfo<Order> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }

    /**
     * 根据商铺id和订单状态获取订单
     * @param shopId 商铺id
     * @param status 订单状态
     * @return
     */
    @Override
    public List<Order> listByStatusOrId(Integer shopId ,Integer status){
        return orderMapper.find(null,null,null,shopId,status);
    }

    /**
     * 获取产品核销统计
     * @param shopId 商铺id
     * @return 统计结果
     */
    public List<Map<String,Object>> listProductStatistics(Integer shopId){
        return orderMapper.listProductStatistics(shopId);
    }

    /**
     * 获取会员消费统计
     * @param shopId 店铺id
     * @return 统计结果
     */
    public List<Map<String,Object>> listMemberStatistics(Integer shopId){
        return orderMapper.listMemberStatistics(shopId);
    }
}
