package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Seller;
import com.etn.shoppingmall.core.mapper.SellerMapper;
import com.etn.shoppingmall.core.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:36
 * Version: V1.0
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Seller load(Integer id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Seller seller) {
        return sellerMapper.insertSelective(seller) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Seller seller) {
        return sellerMapper.updateByPrimaryKeySelective(seller) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Seller seller) {
        seller.setDeleted(true);
        return sellerMapper.updateByPrimaryKeySelective(seller) > 0;
    }
}
