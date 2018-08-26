package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.CollageUser;
import com.etn.shoppingmall.core.model.CollageUsers;

import java.util.List;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/22 16:53
 * Version: V1.0
 */
public interface CollageUserService extends BaseService<CollageUser> {

    /**
     * 参团用户列表
     * @return
     */
    List<CollageUsers> listCollageUsers(Integer prductId);

    /**
     * 根据关联标识查询拼团成员
     * @param af
     * @return
     */
    List<CollageUser> listByAf(String af);

    /**
     * 获取砍拼团发起者
     * @param af
     * @return
     */
    CollageUser querySponsor(String af);

    /**
     * 删除
     * @param af
     * @return
     */
    Integer deleteByAf(String af);
}
