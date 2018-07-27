package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Shop;
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
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    public Pager<Shop> find() {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Shop> list = shopMapper.selectByExample(example);
        PageInfo<Shop> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }
}
