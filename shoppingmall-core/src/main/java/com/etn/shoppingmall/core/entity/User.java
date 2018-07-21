package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Description:店铺实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 9:22
 * Version: V1.0
 */
@Data
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
     * 真实姓名
     */
    private String realName;
    /**
     * 性别：值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String gender;
    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 联系方式
     */
    private String phone;
    /**
     * 用户状态：1 可用, 0 禁用
     */
    private Integer status;
    /**
     * 用户级别：0 普通用户，1 VIP用户
     */
    private Integer userLevel;
    /**
     * 个人二维码
     */
    private String qrCodePic;
    /**
     * 个人二维码链接
     */
    private String qrCodeUrl;
}
