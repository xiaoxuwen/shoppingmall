package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.common.util.Pager;
import com.etn.shoppingmall.core.entity.Ad;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-23 11:46
 * Version: V1.0
 */
public interface AdService {
    Ad load(Integer id);

    boolean add(Ad ad);

    boolean update(Ad ad);

    boolean delete(Ad ad);

    Pager<Ad> listAd(Integer page, Integer limit, String sort, String order);
}
