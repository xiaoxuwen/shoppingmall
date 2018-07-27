package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:店铺实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 9:22
 * Version: V1.0
 */
@Data
@Table(name = "t_shop")
public class Shop extends BaseEntity {
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺简介
     */
    private String info;
    /**
     * 店铺地址
     */
    private String address;
    /**
     * 位置经度
     */
    private Double longitude;
    /**
     * 位置纬度
     */
    private Double latitude;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 店铺门面图片地址
     */
    private String shopImg;
    /**
     * 店铺宣传图片（画廊）
     */
    private String[] picUrls;
    /**
     * 权重，越大越排前显示
     */
    private Integer priority;
    /**
     * 状态：-1.不可用 0.审核中 1.可用
     */
    private Integer status;
    /**
     * 超级管理员给店家的提醒，包括为什么审核不通过等
     */
    private String advice;
    /**
     * 店铺是属于哪个店家的
     */
    private Seller ownerId;
}
