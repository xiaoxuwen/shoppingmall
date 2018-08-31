package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.entity.CollageUser;
import com.etn.shoppingmall.core.mapper.CollageMapper;
import com.etn.shoppingmall.core.mapper.CollageUserMapper;
import com.etn.shoppingmall.core.model.CollageUsers;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.CollageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CollageMapper collageMapper;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteByAf(String af){
        if (StringUtil.isBlank(af)){
            return 0;
        }
        return collageUserMapper.deleteByAf(af);
    }

    /**
     * 验证用户是否参与过此拼团产品
     * @param bid
     * @param userId
     * @return
     */
    @Override
    public Integer verify(Integer bid, Integer userId) {
        List<CollageUser> collageUserList = collageUserMapper.queryByBidAndUserId(bid,userId,FinalValue.SPONSOR,null);
        if (collageUserList.size() > 0){
            return 1;
        }
        return 0;
    }

    /**
     * 获取我的拼团产品列表
     * @param userId
     * @return
     * {
     *     collage  //拼团产品
     *     af   //拼团编号
     *     code   //拼团状态  0,正在拼团  1,拼团成功  2,已过期
     *     surplusTime   //剩余时间
     *     surplusPeople   //还差几人
     * }
     */
    @Override
    public List<Map<String,Object>> listCollageByUserId(Integer userId) {
        List<CollageUser> collageUserList = collageUserMapper.queryByBidAndUserId(null,userId,null,null);
        if (collageUserList.size() < 1){
            return null;
        }
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (CollageUser collageUser:collageUserList) {
            Map<String,Object> map = new HashMap<String,Object>();
            Collage collage = collageMapper.selectByPrimaryKey(collageUser.getProductId());
            CollageUser sponsor = collageUserMapper.queryByBidAndUserId(null,null,FinalValue.SPONSOR,collageUser.getAf()).get(0);
            List<CollageUser> cus = collageUserMapper.queryByBidAndUserId(null,null,null,collageUser.getAf());
            if (sponsor.getDeleted()){
                map.put("code",1);
//                map.put("surplusTime",null);
                map.put("surplusPeople",null);
            }else if (sponsor.getAddTime().plusDays(1).isAfter(LocalDateTime.now())){
//                Duration surplusTime = Duration.between(LocalDateTime.now(),sponsor.getAddTime());
                map.put("code",0);
//                map.put("surplusTime",surplusTime);
                map.put("surplusPeople",(collage.getPeople() - cus.size()));
            }else {
                map.put("code",2);
//                map.put("surplusTime",null);
                map.put("surplusPeople",null);
            }
            map.put("collage",collage);
            map.put("af",sponsor.getAf());
            list.add(map);
        }
        return list;
    }

    /**
     * 不分页获取拼团列表
     * @param prductId
     * @return
     */
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

    /**
     * 根据af获取所以拼团用户
     * @param af
     * @return
     */
    @Override
    public List<CollageUser> listByAf(String af){
        return collageUserMapper.listCollageUser(null,null,af);
    }

    /**
     * 查询拼团发起者
     * @param af
     * @return
     */
    @Override
    public CollageUser querySponsor(String af){
        List<CollageUser> collageUserList = collageUserMapper.listCollageUser(null,FinalValue.SPONSOR,af);
        if (collageUserList.size() >0){
           return collageUserList.get(0);
        }
        return null;
    }

}
