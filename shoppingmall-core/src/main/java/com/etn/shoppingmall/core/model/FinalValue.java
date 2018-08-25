package com.etn.shoppingmall.core.model;

/**
 * Description:常量池
 * User: xiaoxuwen
 * Date: 2018-07-21 14:58
 * Version: V1.0
 */
public class FinalValue {
    public static final Boolean NOT_DELETED = false;            //逻辑删除：未删除
    public static final Boolean IS_DELETED = true;              //逻辑删除：已删除

    public static final Boolean NOT_ENABLED = false;            //广告状态：禁用
    public static final Boolean IS_ENABLED = true;              //广告状态：正常

    public static final Boolean NOT_ON_SALE = false;            //商品：下架
    public static final Boolean IS_ON_SALE = true;              //商品：上架

    public static final Boolean CART_STATUS_FALSE = false;      //购物车商品状态：失效
    public static final Boolean CART_STATUS_TRUE = true;        //购物车商品状态：正常

    public static final Integer USER_STATUS_TRUE = 1;           //用户状态：启用
    public static final Integer USER_STATUS_FALSE = 0;          //用户状态：停用

    public static final Integer USER_LEVEL_COMMON = 0;          //用户等级：普通用户
    public static final Integer USER_LEVEL_VIP = 1;             //用户等级：会员用户

    public static final Integer SHOP_STATUS_FAIL = -1;          //审核不通过
    public static final Integer SHOP_STATUS_ING = 0;            //审核中
    public static final Integer SHOP_STATUS_SUCCESS = 1;        //审核通过

    public static final Integer ORDER_TYPE_DIS = 1;          //折扣订单
    public static final Integer ORDER_TYPE_COLLAGE = 2;         //拼团订单
    public static final Integer ORDER_TYPE_BARGAIN = 3;      //砍价订单

    public static final Integer ORDER_STATUS_UNTAPPED = 1;          //待使用
    public static final Integer ORDER_STATUS_FALSE = 0;         //已过期
    public static final Integer ORDER_STATUS_TRUE = 2;          //已使用

    public static final Integer SPONSOR = 1;          //发起者
    public static final Integer PARTICIPANT = 2;         //参与者

}
