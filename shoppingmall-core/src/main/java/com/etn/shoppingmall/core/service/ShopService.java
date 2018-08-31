package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;
import java.util.Map;

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

    boolean addMapper(Shop shop);

    boolean update(Shop shop);

    /**
     * 店铺
     *
     * @return
     */
    List<Shop> listShop(String name);

    /**
     * 人气最旺的店铺(根据行业属性筛选)
     * @param name
     * @param categoryId
     * @return
     */
    List<Shop> listShop(String name,Integer categoryId);

    /**
     * 店铺登录
     * @param phone
     * @return
     */
    Shop login(String phone,String password);

    /**
     * 获取入驻申请
     * @param openid
     * @return
     */
    List<Shop> openidByShop(String openid);

    /**
     * 店铺推荐
     * @return
     */
    List<Map<String,Object>> recommendShop();
}
