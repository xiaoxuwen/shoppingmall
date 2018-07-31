package com.etn.shoppingmall.wx.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserToken {
    private Integer userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

}
