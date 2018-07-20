package com.etn.shoppingmall.core.entity;

import javax.persistence.Table;

/**
 * 用户信息实体类
 */
@Table(name = "t_user")
public class User extends BaseEntity {
    /**
     * 小程序关联id
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 性别：值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String gender;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 用户状态：0 可用, 1 禁用
     */
    private Integer status;
    /**
     * 用户级别：0 普通用户，1 VIP用户
     */
    private Integer userLevel;
}
