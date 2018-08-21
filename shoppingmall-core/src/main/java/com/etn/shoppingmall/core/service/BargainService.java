package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.model.Pager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    List<BargainUser> listBargainUser(Integer bid);

    BargainUser loadBargainUser(Integer id);

    boolean addBargainUser(BargainUser bargainUser);

    boolean updateBargainUser(BargainUser bargainUser);

    boolean deleteBargainUser(Integer id);
}
