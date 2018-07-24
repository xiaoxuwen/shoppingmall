package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.List;

/**
 * Description:类目实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 14:27
 * Version: V1.0
 */
@Data
@Table(name = "t_category")
public class Category extends BaseEntity{
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目说明
     */
    private String info;
    /**
     * 类目图标
     */
    private String icon_url;
    /**
     * 排序
     */
    private Integer priority;
}
