package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.mapper.CollageMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CollageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018/8/6  22:55
 * Modified By:
 */
@Service
public class CollageServiceImpl implements CollageService {

    @Autowired
    private CollageMapper collageMapper;

    @Override
    public Collage load(Integer id) {
        return collageMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean add(Collage collage) {
        return collageMapper.insertSelective(collage) > 0;
    }

    @Override
    public boolean update(Collage collage) {
        return collageMapper.updateByPrimaryKey(collage) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        Collage collage = new Collage();
        collage.setId(id);
        collage.setDeleted(true);
        return collageMapper.updateByPrimaryKeySelective(collage) > 0;
    }

    /**
     * 分页获取产品
     *
     * @param name
     * @return
     */
    @Override
    public Pager<Collage> find(String name) {
        Example example = new Example(Collage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(name)) criteria.andLike("name", "%" + name + "%");

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Collage> list = collageMapper.selectByExample(example);
        PageInfo<Collage> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }
}
