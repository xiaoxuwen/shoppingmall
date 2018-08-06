package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:41
 * Version: V1.0
 */
public interface BargainService extends BaseService<Bargain> {
    /**
     * 分页获取产品
     *
     * @return
     */
    Pager<Bargain> find(String name);
}
