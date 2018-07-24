package com.etn.shoppingmall.core.service;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:24
 * Version: V1.0
 */
public interface BaseService<T> {
    T load(Integer id);

    boolean add(T t);

    boolean update(T t);

    boolean delete(T t);
}
