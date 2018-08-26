package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.model.Pager;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:41
 * Version: V1.0
 */
public interface BargainService extends BaseService<Bargain> {
    /**
     * 分页获取产品
     *
     * @return
     */
    Pager<Bargain> find(String name);

    /**
     * 不分页获取产品
     * @return
     */
    List<Bargain> list();

    /**
     * 获取砍价参与者用户列表
     * @param bid
     * @return
     */
    List<BargainUser> listBargainUser(Integer bid,String af);

    BargainUser loadBargainUser(Integer id);

    boolean addBargainUser(BargainUser bargainUser);

    boolean updateBargainUser(BargainUser bargainUser);

    boolean deleteBargainUser(Integer id);
    /**
     * 根据砍价产品id和用户id获取参与者
     * @param userId
     * @param bid
     * @return
     */
    List<BargainUser> bidAndUserIdByBargainUser(Integer userId,Integer flag,Integer bid,String af);

    /**
     * 验证参与砍价者今天是否达到参与次数
     * @param userId
     * @return
     */
    boolean todaysBargainUserByUserId(Integer userId);

    /**
     * 根据af和flag获取
     * @param af
     * @param flag
     * @return
     */
    BargainUser queryByAfAndFlag(String af,Integer flag);

    /**
     * 根据店铺id获取砍价产品列表
     * @param shopId
     * @return
     */
    List<Bargain> listBargainByShopId(Integer shopId);

    /**
     * 删除
     * @param af
     * @return
     */
    Integer deleteByAf(String af);
}
