package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Category;
import com.etn.shoppingmall.core.mapper.CategoryMapper;
import com.etn.shoppingmall.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 10:21
 * Version: V1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Category load(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Category category) {
        return categoryMapper.insertSelective(category) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Category category) {
        category.setDeleted(true);
        return categoryMapper.updateByPrimaryKeySelective(category) > 0;
    }

    /**
     * 不分页获取类目
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> list() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        return categoryMapper.selectByExample(example);
    }

}
