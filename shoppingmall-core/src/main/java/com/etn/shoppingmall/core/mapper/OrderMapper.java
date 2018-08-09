package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description:订单Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:30
 * Version: V1.0
 */
public interface OrderMapper extends MyMapper<Order> {

    /**
     *
     * 分页获取订单
     *
     * @param realName   用户真实姓名
     * @param phone      用户手机号
     * @param sn         订单编号
     * @param shopId     商铺id
     * @param status     订单状态
     * @return           List<Order>
     */
    List<Order> find(@Param("realName") String realName, @Param("phone") String phone, @Param("sn") String sn,
                     @Param("shopId") Integer shopId, @Param("status") Integer status);

    /**
     * 不分页获取产品核销统计数据
     * @param shopId 店铺id
     * @return        统计结果
     */
    List<Map<String,Object>> listProductStatistics(@Param("shopId") Integer shopId);

    /**
     * 不分页获取会员消费统计 (消费金额降序排列)
     * @param shopId 店铺id
     * @return        统计结果
     */
    List<Map<String,Object>> listMemberStatistics(@Param("shopId") Integer shopId);
}
