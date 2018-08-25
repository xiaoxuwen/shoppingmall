package com.etn.shoppingmall.wx.model;

import com.etn.shoppingmall.common.util.CharUtil;
import com.etn.shoppingmall.common.util.SecurityUtil;

/**
 * Description: 编号生成
 * User: lihu
 * Date: 2018/8/22 14:21
 * Version: V1.0
 */
public class NumberManager {


    public static String getAf(int bid,int userId){
        String time = String.valueOf(System.currentTimeMillis());
        String random = CharUtil.getRandomString(5);
        String bidString = String.valueOf(bid);
        String useriIdString = String.valueOf(userId);
        String md5 = SecurityUtil.md5(bidString,useriIdString);
        return time+random+md5;
    }

    public static String getSn(int userId){
        String time = String.valueOf(System.currentTimeMillis());
        String random = CharUtil.getRandomString(5);
        String useriIdString = String.valueOf(userId);
        String md5 = SecurityUtil.md5(useriIdString);
        return time+random+md5;
    }

    public static void main(String[] args) {
        System.out.println(getSn(1));
    }

}
