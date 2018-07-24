package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.mapper.ProductMapper;
import com.etn.shoppingmall.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:22
 * Version: V1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Product load(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Product product) {
        return productMapper.insertSelective(product) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Product product) {
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Product product) {
        product.setDeleted(true);
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }
}
