package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.mapper.UserMapper;
import com.etn.shoppingmall.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:40
 * Version: V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User load(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(User user) {
        return userMapper.insertSelective(user) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        User user = new User();
        user.setId(id);
        user.setDeleted(true);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }
}
