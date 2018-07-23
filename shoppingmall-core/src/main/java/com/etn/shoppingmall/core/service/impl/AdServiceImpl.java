package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.mapper.AdMapper;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-23 14:06
 * Version: V1.0
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Ad load(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean add(Ad ad) {
        return adMapper.insertSelective(ad) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(Ad ad) {
        return adMapper.updateByPrimaryKeySelective(ad) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean delete(Ad ad) {
        ad.setDeleted(true);
        return adMapper.updateByPrimaryKeySelective(ad) > 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Pager<Ad> findAd() {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        System.out.println(SystemContext.getOrder());
        System.out.println(SystemContext.getSort());
        System.out.println(SystemContext.getPageOffset());
        System.out.println(SystemContext.getPageSize());
        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Ad> list = adMapper.selectByExample(example);
        PageInfo<Ad> pageList = new PageInfo<>(list);

        return new Pager<>(SystemContext.getPageOffset(), SystemContext.getPageSize(), pageList.getTotal(), list);
    }
}
