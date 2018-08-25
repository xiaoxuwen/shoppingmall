package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.CollageUser;
import com.etn.shoppingmall.core.mapper.CollageUserMapper;
import com.etn.shoppingmall.core.model.CollageUsers;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.CollageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/22 17:01
 * Version: V1.0
 */
@Service
public class CollageUserServiceImpl implements CollageUserService {

    @Autowired
    private CollageUserMapper collageUserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CollageUser load(Integer id) {
        return collageUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(CollageUser collageUser) {
        return collageUserMapper.addCollageUser(collageUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(CollageUser collageUser) {
        return collageUserMapper.updateByPrimaryKey(collageUser) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        CollageUser collageUser = new CollageUser();
        collageUser.setId(id);
        collageUser.setDeleted(true);
        return collageUserMapper.updateByPrimaryKeySelective(collageUser) > 0;
    }

    @Override
    public List<CollageUsers> listCollageUsers(Integer prductId) {
        List<CollageUsers> collageUsersList = new ArrayList<CollageUsers>();
        //发起者
        List<CollageUser> sponsor = collageUserMapper.listCollageUser(prductId,1,null);
        for (CollageUser collageUser:sponsor) {
            CollageUsers collageUsers = new CollageUsers();
            collageUsers.setCollageUser(collageUser);
            //参与者
            List<CollageUser> participant = collageUserMapper.listCollageUser(prductId,2,collageUser.getAf());
            collageUsers.setCollageUserList(participant);
            collageUsersList.add(collageUsers);
        }
        return collageUsersList;
    }

    @Override
    public List<CollageUser> listByAf(String af){
        return collageUserMapper.listCollageUser(null,null,af);
    }

    @Override
    public CollageUser querySponsor(String af){
        List<CollageUser> collageUserList = collageUserMapper.listCollageUser(null,FinalValue.SPONSOR,af);
        if (collageUserList.size() >0){
           return collageUserList.get(0);
        }
        return null;
    }

}
