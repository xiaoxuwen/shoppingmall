package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.mapper.AdMapper;
import com.etn.shoppingmall.core.model.Pager;
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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Ad load(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Ad ad) {
        return adMapper.insertSelective(ad) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Ad ad) {
        return adMapper.updateByPrimaryKeySelective(ad) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setDeleted(true);
        return adMapper.updateByPrimaryKeySelective(ad) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pager<Ad> find() {
        Example example = new Example(Ad.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Ad> list = adMapper.selectByExample(example);
        PageInfo<Ad> pageList = new PageInfo<>(list);

        return new Pager<>(pageList.getTotal(), list);
    }

    /**
     * 不分页获取广告
     *
     * @return
     */
    @Override
    public List<Ad> list() {
        Example example = new Example(Ad.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        return adMapper.selectByExample(example);
    }

    @Override
    public List<Ad> listAd() {
        Example example = new Example(Ad.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        criteria.andEqualTo("enabled", true);
        example.setOrderByClause("priority" + " " + "desc");
        return adMapper.selectByExample(example);
    }
}
