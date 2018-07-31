package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Act;
import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.mapper.ActMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ActService;
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
 * Date: 2018-07-31 18:01
 * Version: V1.0
 */
@Service
public class ActServiceImpl implements ActService {
    @Autowired
    private ActMapper actMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Act load(Integer id) {
        return actMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Act act) {
        return actMapper.insertSelective(act) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Act act) {
        return actMapper.updateByPrimaryKeySelective(act) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        Act act = new Act();
        act.setId(id);
        act.setDeleted(true);
        return actMapper.updateByPrimaryKeySelective(act) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pager<Act> find() {
        Example example = new Example(Ad.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Act> list = actMapper.selectByExample(example);
        PageInfo<Act> pageList = new PageInfo<>(list);

        return new Pager<>(pageList.getTotal(), list);
    }

}
