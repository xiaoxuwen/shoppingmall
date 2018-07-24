package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Category;

import java.util.List;

/**
 * Description:类目Service
 * User: xiaoxuwen
 * Date: 2018-07-24 10:19
 * Version: V1.0
 */
public interface CategoryService extends BaseService<Category> {
    /**
     * 不分页获取类目
     *
     * @return
     */
    List<Category> list();
}
