package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

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

    /**
     * @param openId 小程序标识
     * @return
     */
    User queryByOpenid(String openId);

    /**
     * @param phone 手机号
     * @return
     */
    List<User> queryByPhone(String phone);
}
