package com.etn.shoppingmall.wx.model;

import com.etn.shoppingmall.common.util.CharUtil;
import com.etn.shoppingmall.common.util.SecurityUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 编号生成
 * User: lihu
 * Date: 2018/8/22 14:21
 * Version: V1.0
 */
public class NumberManager {


    public static String getAf(){
        String time = locatDateTimeToString(LocalDateTime.now());
        String random = CharUtil.getRandomString(5);
        return random+time;
    }

    public static String getSn(){
        String time = locatDateTimeToString(LocalDateTime.now());
        String random = CharUtil.getRandomString(5);
        return time+random;
    }

    public static String locatDateTimeToString(LocalDateTime localDateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dtf.format(localDateTime);
    }

    public static void main(String[] args) {
        System.out.println(getSn());
    }

}
