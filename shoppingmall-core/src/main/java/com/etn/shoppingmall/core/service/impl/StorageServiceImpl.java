package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Storage;
import com.etn.shoppingmall.core.mapper.StorageMapper;
import com.etn.shoppingmall.core.service.StorageService;
import com.github.pagehelper.PageHelper;
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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteByKey(String ikey) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ikey", ikey);
        storageMapper.deleteByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(Storage storage) {
        storageMapper.insert(storage);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Storage queryByName(String filename) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", filename);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Storage queryByKey(String ikey) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ikey", ikey);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(Storage storage) {
        storageMapper.updateByPrimaryKeySelective(storage);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Storage queryById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Storage> listSelective(String ikey, String name, Integer page, Integer limit, String sort, String order) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(ikey)) {
            criteria.andEqualTo("ikey", ikey);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int countSelective(String ikey, String name) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(ikey)) {
            criteria.andEqualTo("ikey", ikey);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectCountByExample(example);
    }
}
