package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;
import java.util.Map;

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
     * 不分页获取产品(行业属性二级筛选)(附带距离)(距离排序)(折扣排序)
     * @param name
     * @param latitude
     * @param longitude
     * @param categoryId
     * @param sort
     * @param order
     * @return
     */
    List<Map<String,Object>> listProduct(String name, Double latitude, Double longitude,Integer categoryId,String sort,String order);

    /**
     * 获取折扣最低产品
     *
     * @return
     */
    List<Product> listDiscountProduct(String name);

    /**
     * 获取折扣最低产品(附带距离)
     *
     * @return
     */
    List<Map<String,Object>> listDiscountProduct(String name, Double latitude, Double longitude);


    /**
     * 根据行业获取折扣最低产品(附带距离)
     *
     * @param categoryId 行业id
     * @return
     */
    List<Map<String,Object>> listDiscountProductByCategory(Integer categoryId, Double latitude, Double longitude);

    /**
     * 获取距离最近的产品
     * @param name
     * @param latitude
     * @param longitude
     * @return
     */
    List<Map<String,Object>> listDistanceProduct(String name,Double latitude, Double longitude);

    /**
     * 根据行业属性获取距离最近的产品
     * @param categoryId
     * @param latitude
     * @param longitude
     * @return
     */
    public List<Map<String,Object>> listDistanceProductByCategory(Integer categoryId,Double latitude, Double longitude);

    /**
     * 根据店铺id获取产品列表
     * @param shopId
     * @return
     */
    List<Product> listPrductByShopId(Integer shopId);
}
