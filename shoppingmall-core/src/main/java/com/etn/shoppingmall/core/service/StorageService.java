package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.mapper.StorageMapper;
import com.etn.shoppingmall.core.entity.Storage;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Service
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;

    public void deleteByKey(String key) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("key", key);
        storageMapper.deleteByExample(example);
    }

    public void add(Storage storage) {
        storageMapper.insert(storage);
    }

    public Storage findByName(String filename) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", filename);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    public Storage findByKey(String key) {
        Example example = new Example(Storage.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("key", key);
        criteria.andEqualTo("deleted", false);
        return storageMapper.selectOneByExample(example);
    }

    public void update(Storage storageInfo) {
        storageMapper.updateByPrimaryKeySelective(storageInfo);
    }


    public Storage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<Storage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andEqualTo("key", key);
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

    public int countSelective(String key, String name, Integer page, Integer size, String sort, String order) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andEqualTo("key", key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        criteria.andEqualTo("deleted", false);

        return storageMapper.selectCountByExample(example);
    }
}
