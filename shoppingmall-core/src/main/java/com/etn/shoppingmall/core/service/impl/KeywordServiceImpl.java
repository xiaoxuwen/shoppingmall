package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Keyword;
import com.etn.shoppingmall.core.mapper.KeywordMapper;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.KeywordService;
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
 * Date: 2018-07-24 14:59
 * Version: V1.0
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Keyword load(Integer id) {
        return keywordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Keyword keyword) {
        return keywordMapper.insertSelective(keyword) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Keyword keyword) {
        return keywordMapper.updateByPrimaryKeySelective(keyword) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        Keyword keyword = new Keyword();
        keyword.setId(id);
        keyword.setDeleted(true);
        return keywordMapper.updateByPrimaryKeySelective(keyword) > 0;
    }

    /**
     * 不分页获取问题
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Keyword> list() {
        Example example = new Example(Keyword.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        return keywordMapper.selectByExample(example);
    }
}
