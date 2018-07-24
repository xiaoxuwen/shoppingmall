package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Keyword;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:39
 * Version: V1.0
 */
public interface KeywordService extends BaseService<Keyword> {
    /**
     * 不分页获取问题
     *
     * @return
     */
    List<Keyword> list();
}
