package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Description:管理员实体类
 * User: xiaoxuwen
 * Date: 2018-06-15 15:23
 * Version: V1.0
 */
@Data
@Table(name = "t_admin")
public class Admin extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 最后一次登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 最后一次登录IP
     */
    private String lastLoginIp;
    /**
     * 登录次数
     */
    private Integer loginCount;
}
