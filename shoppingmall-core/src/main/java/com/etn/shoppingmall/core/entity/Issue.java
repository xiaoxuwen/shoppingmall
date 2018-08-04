package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:常见问题实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 15:09
 * Version: V1.0
 */
@Data
@Table(name = "t_issue")
public class Issue extends BaseEntity {
    /**
     * 问题标题
     */
    private String question;
    /**
     * 问题答案
     */
    private String answer;
    /**
     * 排序
     */
    private Integer priority;
}
