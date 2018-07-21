package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:'文件存储表'
 * User: xiaoxuwen
 * Date: 2018-07-18 16:15
 * Version: V1.0
 */
@Data
@Table(name = "os_storage")
public class Storage extends BaseEntity {
    /**
     * 文件的唯一索引
     */
    private String ikey;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 文件访问链接
     */
    private String url;

}
