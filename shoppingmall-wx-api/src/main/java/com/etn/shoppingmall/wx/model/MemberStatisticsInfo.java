package com.etn.shoppingmall.wx.model;

import com.etn.shoppingmall.core.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/6 16:30
 * Version: V1.0
 */

@Data
public class MemberStatisticsInfo {

    private Integer memberId;    //会员id
    private String memberName;    //会员名
    private String memberAvatarUrl;    //会员头像链接
    private String phone;    //手机号
    private BigDecimal expenseCount;    //消费总量

}
