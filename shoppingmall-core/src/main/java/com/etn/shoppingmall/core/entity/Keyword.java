package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:搜索关键字实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 15:09
 * Version: V1.0
 */
@Data
@Table(name = "t_keyword")
public class Keyword extends BaseEntity {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 排序
     */
    private Integer priority;
}
