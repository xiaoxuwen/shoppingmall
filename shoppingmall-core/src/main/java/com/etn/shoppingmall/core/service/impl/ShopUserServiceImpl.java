package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.ShopUser;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.mapper.ShopUserMapper;
import com.etn.shoppingmall.core.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/13 11:32
 * Version: V1.0
 */
@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ShopUser load(Integer id) {
        return shopUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(ShopUser shopUser) {
        return shopUserMapper.insertSelective(shopUser) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(ShopUser shopUser) {
        return shopUserMapper.updateByPrimaryKey(shopUser) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        ShopUser shopUser = new ShopUser();
        shopUser.setId(id);
        shopUser.setDeleted(true);
        return shopUserMapper.updateByPrimaryKeySelective(shopUser) > 0;
    }

    @Override
    public List<ShopUser> countShopUser(Integer shopId, LocalDateTime beforeTime, LocalDateTime endTime){
        Example example = new Example(ShopUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("shopId",shopId);
        criteria.andBetween("addTime",beforeTime,endTime);
        criteria.andEqualTo("deleted", false);
        return shopUserMapper.selectByExample(example);
    }
}
