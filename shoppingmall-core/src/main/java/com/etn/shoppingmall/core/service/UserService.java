package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.Pager;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:53
 * Version: V1.0
 */
public interface UserService extends BaseService<User> {

    /**
     * 分页获取用户
     *
     * @param level 用户级别（普通/VIP）
     * @param phone 手机号
     * @return
     */
    Pager<User> findUser(Integer level, String phone);
}
