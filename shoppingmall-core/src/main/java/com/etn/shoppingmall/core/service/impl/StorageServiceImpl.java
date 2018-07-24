package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Storage;
import com.etn.shoppingmall.core.mapper.StorageMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.StorageService;
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

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByKey(String ikey) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ikey", ikey);
        storageMapper.deleteByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Storage storage) {
        storageMapper.insertSelective(storage);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Storage queryByName(String filename) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", filename);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Storage queryByKey(String ikey) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ikey", ikey);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Storage storage) {
        storageMapper.updateByPrimaryKeySelective(storage);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Storage queryById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pager<Storage> listSelective(String ikey, String name) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(ikey)) {
            criteria.andEqualTo("ikey", ikey);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Storage> list = storageMapper.selectByExample(example);
        PageInfo<Storage> pageList = new PageInfo<>(list);

        return new Pager<>(SystemContext.getPageOffset(), SystemContext.getPageSize(), pageList.getTotal(), list);
    }

}
