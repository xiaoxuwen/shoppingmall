package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Description:订单实体类(我的优惠券)
 * User: xiaoxuwen
 * Date: 2018-07-21 15:08
 * Version: V1.0
 */
@Data
@Table(name = "t_order")
public class Order {
    /**
     * 用户表的用户ID
     */
    private User user;
    /**
     * 订单编号
     */
    private String sn;
    /**
     * 订单状态 1.待使用 2.已使用 0.已过期
     */
    private Integer status;
    /**
     * 产品id
     */
    private Product product;
    /**
     * 店铺id
     */
    private Integer shopId;
    /**
     * 订单关闭时间
     */
    private LocalDateTime endTime;
    /**
     * 订单二维码
     */
    private String qrCodePic;
    /**
     * 订单二维码链接
     */
    private String qrCodeUrl;
}
