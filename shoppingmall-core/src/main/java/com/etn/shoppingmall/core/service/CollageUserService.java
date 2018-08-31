package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.CollageUser;
import com.etn.shoppingmall.core.model.CollageUsers;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

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

    /**
     * 验证用户是否参与过此拼团产品
     * @param bid
     * @param userId
     * @return
     */
    Integer verify(Integer bid, Integer userId);

    /**
     * 获取我的拼团产品列表
     * @param userId
     * @return
     */
    List<Map<String,Object>> listCollageByUserId(Integer userId);

}
