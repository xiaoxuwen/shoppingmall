package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:41
 * Version: V1.0
 */
public interface CollageService extends BaseService<Collage> {
    /**
     * 分页获取产品
     *
     * @return
     */
    Pager<Collage> find(String name);

    /**
     * 不分页获取产品
     * @param name
     * @return
     */
    List<Collage> list(String name);
}
