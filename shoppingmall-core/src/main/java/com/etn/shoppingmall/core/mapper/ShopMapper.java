package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:店铺Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:31
 * Version: V1.0
 */
public interface ShopMapper extends MyMapper<Shop> {
    List<Shop> find(@Param("status") Integer status, @Param("name") String name);

    boolean add(Shop shop);

    boolean update(Shop shop);
}
