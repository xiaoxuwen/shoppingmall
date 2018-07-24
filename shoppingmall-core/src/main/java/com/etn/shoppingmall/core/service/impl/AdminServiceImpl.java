package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.mapper.AdminMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdminService;
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
 * Date: 2018-07-24 9:25
 * Version: V1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin load(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Admin admin) {
        return adminMapper.insertSelective(admin) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Admin admin) {
        admin.setDeleted(true);
        return adminMapper.updateByPrimaryKeySelective(admin) > 0;
    }

    /**
     * 分页获取管理员
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Pager<Admin> find() {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }

        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Admin> list = adminMapper.selectByExample(example);
        PageInfo<Admin> pageList = new PageInfo<>(list);

        return new Pager<>(SystemContext.getPageOffset(), SystemContext.getPageSize(), pageList.getTotal(), list);
    }
}
