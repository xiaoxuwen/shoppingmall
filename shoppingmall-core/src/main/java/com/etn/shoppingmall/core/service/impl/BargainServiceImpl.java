package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.core.entity.Bargain;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.mapper.BargainMapper;
import com.etn.shoppingmall.core.mapper.BargainUserMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.BargainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
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
 * Date: 2018/8/6  22:49
 * Modified By:
 */
@Service
public class BargainServiceImpl implements BargainService {

    @Autowired
    private BargainMapper bargainMapper;
    @Autowired
    private BargainUserMapper bargainUserMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Bargain load(Integer id) {
        return bargainMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean add(Bargain bargain) {
        return bargainMapper.insertSelective(bargain) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(Bargain bargain) {
        return bargainMapper.updateByPrimaryKey(bargain) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean delete(Integer id) {
        Bargain bargain = new Bargain();
        bargain.setId(id);
        bargain.setDeleted(true);
        return bargainMapper.updateByPrimaryKeySelective(bargain) > 0;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public BargainUser loadBargainUser(Integer id) {
        return bargainUserMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addBargainUser(BargainUser bargainUser) {
        return bargainUserMapper.addBargainUser(bargainUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateBargainUser(BargainUser bargainUser) {
        return bargainUserMapper.updateByPrimaryKey(bargainUser) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteBargainUser(Integer id) {
        BargainUser bargainUser = new BargainUser();
        bargainUser.setId(id);
        bargainUser.setDeleted(true);
        return bargainUserMapper.updateByPrimaryKeySelective(bargainUser) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteByAf(String af){
        if (StringUtil.isBlank(af)){
            return 0;
        }
        return bargainUserMapper.deleteByAf(af);
    }

    /**
     * 分页获取产品
     *
     * @param name
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Pager<Bargain> find(String name) {
        Example example = new Example(Bargain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(name)) criteria.andLike("name", "%" + name + "%");

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Bargain> list = bargainMapper.selectByExample(example);
        PageInfo<Bargain> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }

    /**
     * 不分页获取产品
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Bargain> list() {
        Example example = new Example(Bargain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        List<Bargain> list = bargainMapper.selectByExample(example);
        return list;
    }

    /**
     * 获取砍价用户列表
     * @param bid
     * @return
     */
    @Override
    public List<BargainUser> listBargainUser(Integer bid,String af) {
        return bargainUserMapper.listBargainUser(bid,null,null,af);
    }

    /**
     * 根据砍价产品id和用户id获取参与者
     * @param userId
     * @param bid
     * @return
     */
    @Override
    public List<BargainUser> bidAndUserIdByBargainUser(Integer userId,Integer flag,Integer bid,String af){
        return bargainUserMapper.listBargainUser(bid,flag,userId,af);
    }

    /**
     * 验证参与砍价者今天是否达到参与次数
     * @param userId
     * @return
     */
    @Override
    public boolean todaysBargainUserByUserId(Integer userId){
        return bargainUserMapper.todaysBargainUserByUserId(userId) >= 3;
    }

    /**
     *
     * @param af
     * @param flag
     * @return
     */
    @Override
    public BargainUser queryByAfAndFlag(String af,Integer flag){
        Example example = new Example(BargainUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        if (!StringUtils.isEmpty(flag)) criteria.andEqualTo("flag",flag);
        if (!StringUtils.isEmpty(af)) {
            criteria.andEqualTo("af",af);
        }
        return bargainUserMapper.selectOneByExample(example);
    }

    /**
     * 根据店铺id获取砍价产品列表
     * @param shopId
     * @return
     */
    @Override
    public List<Bargain> listBargainByShopId(Integer shopId){
        Example example = new Example(Bargain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        if (shopId != null)criteria.andEqualTo("shopId", shopId);
        return bargainMapper.selectByExample(example);
    }
}
