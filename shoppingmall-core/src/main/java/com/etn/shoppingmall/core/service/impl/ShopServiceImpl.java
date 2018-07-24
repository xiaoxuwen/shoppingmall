package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.mapper.ShopMapper;
import com.etn.shoppingmall.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:38
 * Version: V1.0
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Shop load(Integer id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Shop shop) {
        return shopMapper.insertSelective(shop) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Shop shop) {
        return shopMapper.updateByPrimaryKeySelective(shop) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Shop shop) {
        shop.setDeleted(true);
        return shopMapper.updateByPrimaryKeySelective(shop) > 0;
    }
}
