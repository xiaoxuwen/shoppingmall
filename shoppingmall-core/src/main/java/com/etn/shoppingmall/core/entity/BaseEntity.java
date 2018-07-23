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
    /**
     * 主键，自增
     */
    private Integer id;
    /**
     * 创建时间
     */
    private LocalDateTime addTime;
    /**
     * 逻辑删除 1.删除，0.未删除
     */
    private Boolean deleted;
}
