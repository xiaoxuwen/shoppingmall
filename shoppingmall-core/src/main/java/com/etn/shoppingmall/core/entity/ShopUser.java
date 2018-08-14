package com.etn.shoppingmall.core.entity;

import lombok.Data;
import javax.persistence.Table;

/**
 * Description: 店铺验证会员实体类
 * User: lihu
 * Date: 2018/8/13 10:37
 * Version: V1.0
 */
@Data
@Table(name = "t_shop_user")
public class ShopUser extends BaseEntity{

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 会员ID
     */
    private Integer userId;

}
