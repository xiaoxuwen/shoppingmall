package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:订单Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:30
 * Version: V1.0
 */
public interface OrderMapper extends MyMapper<Order> {
    /**
     * 分页获取订单
     *
     * @param phone 用户手机号
     * @param sn    订单编号
     * @return
     */
    List<Order> find(@Param("realName") String realName, @Param("phone") String phone, @Param("sn") String sn);
}
