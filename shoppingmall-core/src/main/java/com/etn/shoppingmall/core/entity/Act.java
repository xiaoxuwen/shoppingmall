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
    private String name;
    private String pic;
    private String actDate;
    private String content;
    private String address;
    private Integer priority;
}
