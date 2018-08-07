package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Act;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

/**
 * Description:广告Service
 * User: xiaoxuwen
 * Date: 2018-07-23 11:46
 * Version: V1.0
 */
public interface ActService extends BaseService<Act> {
    /**
     * 分页获取活动
     *
     * @return
     */
    Pager<Act> find();

    List<Act> listAct();
}
