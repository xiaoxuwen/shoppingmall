package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:活动专场
 * User: xiaoxuwen
 * Date: 2018-07-31 17:56
 * Version: V1.0
 */
@Data
@Table(name = "t_act")
public class Act extends BaseEntity {
    /**
     * 商铺名称
     */
    private String name;
    /**
     * 商铺照片
     */
    private String pic;
    /**
     * 活动开始时间
     */
    private String actDate;
    /**
     * 活动内容
     */
    private String content;
    /**
     * 商铺地址
     */
    private String address;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 是否上架
     */
    private Boolean isOnSale;
}