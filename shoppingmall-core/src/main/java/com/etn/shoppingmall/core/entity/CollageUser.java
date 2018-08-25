package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:参与拼团的用户实体类
 * User: xiaoxuwen
 * Date: 2018/8/6  22:09
 * Modified By:
 */
@Data
@Table(name = "t_collage_user")
public class CollageUser extends BaseEntity {
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 用户
     */
    private User user;
    /**
     * 关联标记：关联发起者和参与者
     */
    private String af;
    /**
     * 标识 1.发起者, 2.参与者
     */
    private Integer flag;
}
