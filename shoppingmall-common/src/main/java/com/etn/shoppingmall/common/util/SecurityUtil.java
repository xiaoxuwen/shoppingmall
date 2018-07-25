package com.etn.shoppingmall.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class SecurityUtil {
    private static final Log logger = LogFactory.getLog(SecurityUtil.class);

    public static String md5(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("密码加密发生异常！" + e);
        }
        md.update(password.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static String md5(String username, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("密码加密发生异常！" + e);
        }
        md.update(username.getBytes());
        md.update(password.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static void main(String[] args) {
        System.out.println(SecurityUtil.md5("admin", "admin"));
        System.out.println("f6fdffe48c908deb0f4c3bd36c032e72");
    }

}
