package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Seller;
import com.etn.shoppingmall.core.entity.Shop;
import com.etn.shoppingmall.core.mapper.SellerMapper;
import com.etn.shoppingmall.core.mapper.ShopMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private SellerMapper sellerMapper;

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
    public boolean delete(Integer id) {
        Shop shop = new Shop();
        shop.setId(id);
        shop.setDeleted(true);
        return shopMapper.updateByPrimaryKeySelective(shop) > 0;
    }

    /**
     * 分页获取店铺
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pager<Shop> find(Integer status, String name) {
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Shop> list = shopMapper.find(status, name);
        PageInfo<Shop> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Shop shop, Seller seller) {
        sellerMapper.add(seller);
        shop.setOwner(seller);
        if (shopMapper.add(shop)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Shop shop, Seller seller) {
        sellerMapper.updateByPrimaryKeySelective(seller);
        if (shopMapper.update(shop)) return true;
        return false;
    }
}
