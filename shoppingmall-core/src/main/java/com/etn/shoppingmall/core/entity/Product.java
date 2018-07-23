package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description:产品基本信息实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 14:39
 * Version: V1.0
 */
@Data
@Table(name = "t_product")
public class Product {
    /**
     * 商品编号
     */
    private String sn;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品简介
     */
    private String brief;
    /**
     * 商品详细介绍，是富文本格式
     */
    private String content;
    /**
     * 商品页面商品图片
     */
    private String picUrl;
    /**
     * 商品宣传图片列表
     */
    private String[] gallery;
    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;
    /**
     * 是否上架
     */
    private Boolean isOnSale;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 零售价格
     */
    private BigDecimal retail_price;
    /**
     * 会员价格
     */
    private BigDecimal counter_price;
    /**
     * 优惠类型，满减/打折/自定义价格
     */
    private Integer discountType;
    /**
     * 优惠价格 满减/打折/自定义优惠价格
     */
    private String discountMoney;
    /**
     * 使用期限，1.永久 2.自定义
     */
    private Integer during;
    /**
     * 商品开始使用时间
     */
    private LocalDateTime startDate;
    /**
     * 商品过期时间
     */
    private LocalDateTime endDate;
    /**
     * 商品所属类目ID
     */
    private Integer categoryId;
    /**
     * 商品所属店铺ID
     */
    private Integer shopId;
}
