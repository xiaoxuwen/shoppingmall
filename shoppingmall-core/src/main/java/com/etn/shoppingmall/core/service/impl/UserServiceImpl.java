package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.User;
import com.etn.shoppingmall.core.mapper.UserMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.UserService;
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

    /**
     * 分页获取用户
     *
     * @param level 用户级别（普通/VIP）
     * @param phone 手机号
     * @return
     */
    @Override
    public Pager<User> findUser(Integer level, String phone) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        if (level != null) criteria.andEqualTo("userLevel", level);
        if (StringUtil.isNotBlank(phone)) criteria.andLike("phone", "%" + phone + "%");

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> pageList = new PageInfo<>(list);

        return new Pager<>(pageList.getTotal(), list);
    }
}
