package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.model.Pager;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 9:23
 * Version: V1.0
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 分页获取管理员
     *
     * @return
     */
    Pager<Admin> find();
}
