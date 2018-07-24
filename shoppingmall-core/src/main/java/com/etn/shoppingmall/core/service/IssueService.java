package com.etn.shoppingmall.core.service;

import com.etn.shoppingmall.core.entity.Issue;

import java.util.List;

/**
 * Description:常见问题Service
 * User: xiaoxuwen
 * Date: 2018-07-24 14:23
 * Version: V1.0
 */
public interface IssueService extends BaseService<Issue> {
    /**
     * 不分页获取问题
     *
     * @return
     */
    List<Issue> list();
}
