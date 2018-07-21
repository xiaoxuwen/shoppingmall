package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:商家实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 9:22
 * Version: V1.0
 */
@Data
@Table(name = "t_seller")
public class Seller {
    /**
     * 小程序关联id
     */
    private String openid;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 用户状态：0 可用, 1 禁用
     */
    private Integer status;
}
