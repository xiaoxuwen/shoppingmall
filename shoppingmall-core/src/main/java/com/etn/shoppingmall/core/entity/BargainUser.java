package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Description:参与砍价的用户实体类
 * User: xiaoxuwen
 * Date: 2018/8/6  22:03
 * Modified By:
 */
@Data
@Table(name = "t_bargain_user")
public class BargainUser extends BaseEntity {
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 砍价价格
     */
    private BigDecimal price;
}