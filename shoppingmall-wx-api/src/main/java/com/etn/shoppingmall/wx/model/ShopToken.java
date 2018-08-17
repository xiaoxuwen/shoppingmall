package com.etn.shoppingmall.wx.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: 商家端token
 * User: lihu
 * Date: 2018/8/17 10:05
 * Version: V1.0
 */
@Data
public class ShopToken {

    private Integer shopId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;
}
