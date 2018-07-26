package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

/**
 * Description:广告Service
 * User: xiaoxuwen
 * Date: 2018-07-23 11:46
 * Version: V1.0
 */
public interface AdService extends BaseService<Ad> {
    /**
     * 分页获取广告
     *
     * @return
     */
    Pager<Ad> find();

    /**
     * 不分页获取广告
     *
     * @return
     */
    Pager<Ad> list();
}
