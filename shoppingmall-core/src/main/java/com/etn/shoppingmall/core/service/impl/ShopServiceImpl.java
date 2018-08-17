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
    public boolean add(Shop shop) {
        return shopMapper.add(shop);
    }

    @Override
    public boolean update(Shop shop) {
        return shopMapper.update(shop);
    }

    /**
     * 人气最旺的店铺
     *
     * @return
     */
    @Override
    public List<Shop> listShop(String name) {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(name)) criteria.andLike("name", "%" + name + "%");

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        return shopMapper.selectByExample(example);
    }

    /**
     * 商家登录
     * @param phone
     * @param password
     * @return
     */
    @Override
    public Shop login(String phone,String password){
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",phone);
        criteria.andEqualTo("password",password);
        criteria.andEqualTo("deleted", false);
        return shopMapper.selectOneByExample(example);
    }

    @Override
    public List<Shop> openidByShop(String openid){
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid",openid);
        criteria.andEqualTo("deleted", false);
        return shopMapper.selectByExample(example);
    }


}
