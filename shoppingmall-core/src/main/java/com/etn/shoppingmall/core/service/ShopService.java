package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:53
 * Version: V1.0
 */
public interface ShopService extends BaseService<Shop> {
    /**
     * 分页获取店铺
     *
     * @return
     */
    Pager<Shop> find(Integer status, String name);

    boolean add(Shop shop);

    boolean update(Shop shop);
}
