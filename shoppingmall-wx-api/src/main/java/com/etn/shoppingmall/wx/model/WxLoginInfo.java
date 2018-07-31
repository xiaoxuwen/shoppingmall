package com.etn.shoppingmall.wx.model;

import lombok.Data;

@Data
public class WxLoginInfo {
    private String code;
    private UserInfo userInfo;

}
