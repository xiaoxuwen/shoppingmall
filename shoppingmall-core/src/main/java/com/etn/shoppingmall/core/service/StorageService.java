package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Storage;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-23 11:15
 * Version: V1.0
 */
public interface StorageService {
    void deleteByKey(String ikey);

    void add(Storage storage);

    Storage queryByName(String filename);

    Storage queryByKey(String ikey);

    void update(Storage storage);

    Storage queryById(Integer id);

    List<Storage> listSelective(String ikey, String name, Integer page, Integer limit, String sort, String order);

    int countSelective(String ikey, String name);
}
