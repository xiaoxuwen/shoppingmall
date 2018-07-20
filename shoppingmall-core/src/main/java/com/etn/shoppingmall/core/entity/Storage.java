package com.etn.shoppingmall.core.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Description:'文件存储表'
 * User: xiaoxuwen
 * Date: 2018-07-18 16:15
 * Version: V1.0
 */
@Data
@Table(name = "os_storage")
public class Storage {
    public static final Boolean NOT_DELETED = false;
    public static final Boolean IS_DELETED = true;
    /**
     * 主键，自增
     */
    @Id
    private Integer id;
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
    /**
     * 最后更新时间
     */

    private LocalDateTime modified;
    /**
     * 创建时间
     */
    @Column(name = "add_time")
    private LocalDateTime addTime;
    /**
     * 逻辑删除
     */
    private Boolean deleted;

}
