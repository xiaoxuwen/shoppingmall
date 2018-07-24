package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Admin;
import com.etn.shoppingmall.core.mapper.AdminMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Admin load(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean add(Admin admin) {
        return adminMapper.insertSelective(admin) > 0;
    }

    @Override
    public boolean update(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin) > 0;
    }

    @Override
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
    public Pager<Admin> findAdmin() {
        return null;
    }
}
