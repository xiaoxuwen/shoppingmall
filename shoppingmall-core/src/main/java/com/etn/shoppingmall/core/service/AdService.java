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
public interface AdService {
    Ad load(Integer id);

    boolean add(Ad ad);

    boolean update(Ad ad);

    boolean delete(Ad ad);

    /**
     * 分页获取广告
     *
     * @return
     */
    Pager<Ad> findAd();

    /**
     * 不分页获取广告
     *
     * @return
     */
    List<Ad> listAd();
}
