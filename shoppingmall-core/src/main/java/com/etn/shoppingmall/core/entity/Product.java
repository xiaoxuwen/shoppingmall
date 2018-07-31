package com.etn.shoppingmall.core.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Description:产品基本信息实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 14:39
 * Version: V1.0
 */
@Data
@Table(name = "t_product")
public class Product extends BaseEntity {
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
     * 份数
     */
    private Integer fen;
    /**
     * 使用须知
     */
    private String info;
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
    private BigDecimal retailPrice;
    /**
     * 会员价格
     */
    private BigDecimal counterPrice;
    /**
     * 使用期限，1.永久 2.自定义
     */
    private Integer during;
    /**
     * 商品开始使用时间
     */
    private LocalDate startDate;
    /**
     * 商品过期时间
     */
    private LocalDate endDate;
    /**
     * 商品所属类目ID
     */
    private Integer categoryId;
    /**
     * 商品所属店铺ID
     */
    private Integer shopId;
}
