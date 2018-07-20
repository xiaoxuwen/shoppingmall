package com.etn.shoppingmall.core.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-20 16:03
 * Version: V1.0
 */
@Data
public class BaseEntity {
    public static final Boolean NOT_DELETED = false;
    public static final Boolean IS_DELETED = true;
    /**
     * 主键，自增
     */
    private Integer id;
    /**
     * 最后更新时间
     */
    private LocalDateTime modified;
    /**
     * 创建时间
     */
    private LocalDateTime addTime;
    /**
     * 逻辑删除
     */
    private Boolean deleted;
}
