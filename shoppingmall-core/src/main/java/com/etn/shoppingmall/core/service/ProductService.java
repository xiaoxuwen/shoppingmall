package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:41
 * Version: V1.0
 */
public interface ProductService extends BaseService<Product> {
    /**
     * 分页获取产品
     *
     * @return
     */
    Pager<Product> find(String name);

    /**
     * 折扣最低的产品
     *
     * @return
     */
    List<Product> listDiscountProduct(String name);

    /**
     * 距离最近的产品
     *
     * @return
     */
    List<Product> listDistanceProduct(String name);

    /**
     * 根据行业获取产品
     *
     * @param categoryId 行业id
     * @return
     */
    List<Product> listProduct(Integer categoryId);
}
