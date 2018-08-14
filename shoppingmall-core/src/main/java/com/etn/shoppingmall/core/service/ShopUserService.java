package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.ShopUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 店铺验证会员Service
 * User: lihu
 * Date: 2018-08-13 11:15
 * Version: V1.0
 */
public interface ShopUserService extends BaseService<ShopUser> {

    /**
     * 不分页获取店铺验证会员
     * @param shopId 店铺id
     * @return
     */
    List<ShopUser> countShopUser(Integer shopId, LocalDateTime beforeTime,LocalDateTime endTime);

}
