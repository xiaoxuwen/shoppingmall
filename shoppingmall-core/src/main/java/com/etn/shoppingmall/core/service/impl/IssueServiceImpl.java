package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Issue;
import com.etn.shoppingmall.core.mapper.IssueMapper;
import com.etn.shoppingmall.core.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 14:54
 * Version: V1.0
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueMapper issueMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Issue load(Integer id) {
        return issueMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Issue issue) {
        return issueMapper.insertSelective(issue) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Issue issue) {
        return issueMapper.updateByPrimaryKeySelective(issue) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setDeleted(true);
        return issueMapper.updateByPrimaryKeySelective(issue) > 0;
    }

    /**
     * 不分页获取问题
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Issue> list() {
        Example example = new Example(Issue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        return issueMapper.selectByExample(example);
    }
}
