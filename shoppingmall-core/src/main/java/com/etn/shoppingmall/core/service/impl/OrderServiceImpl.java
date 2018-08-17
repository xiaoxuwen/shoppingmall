package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.common.util.StringUtil;
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
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
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
        List<Order> list = orderMapper.find(realName, phone, sn);
        PageInfo<Order> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }

    /**
     * 不分页获取订单
     * @param beforeTime 开始时间
     * @param endTime    结束时间
     * @param status     订单状态
     * @param shopId     店铺id
     * @return
     */
    @Override
    public List<Order> list(Integer shopId , Integer status, LocalDateTime beforeTime,LocalDateTime endTime){
        return orderMapper.list(beforeTime,endTime,status,shopId);
    }

    /**
     * 获取产品核销统计
     * @param beforeTime 开始时间
     * @param endTime    结束时间
     * @param shopId 商铺id
     * @return 统计结果
     */
    public Map<String,Object> listProductStatistics(Integer shopId,LocalDateTime beforeTime,LocalDateTime endTime){
        return orderMapper.listProductStatistics(shopId,beforeTime,endTime);
    }

    /**
     * 我的订单
     * @param userId 用户id
     * @param status 订单状态
     * @param orderType  订单类型
     * @return
     */
    public List<Order> myOrder(Integer userId,Integer status,Integer orderType){
        return orderMapper.myOrderList(userId,status,orderType);
    }
}
