package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description:砍价实体类
 * User: xiaoxuwen
 * Date: 2018/8/6  21:51
 * Modified By:
 */
@Data
@Table(name = "t_bargain")
public class Bargain extends BaseEntity {
    /**
     * 产品所属店铺ID
     */
    private Integer shopId;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品简介
     */
    private String brief;
    /**
     * 使用须知
     */
    private String info;
    /**
     * 份数
     */
    private Integer fen;
    /**
     * 产品详细介绍，是富文本格式
     */
    private String content;
    /**
     * 产品页面产品图片
     */
    private String picUrl;
    /**
     * 产品宣传图片列表（保留）
     */
    private String[] gallery;
    /**
     * 产品关键字，采用逗号间隔
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
     * 价格
     */
    private BigDecimal price;
    /**
     * 砍价成功价格
     */
    private BigDecimal lowPrice;
    /**
     * 现价
     */
    private BigDecimal nowPrice;
    /**
     * 砍价人数设置
     */
    private Integer people;
    /**
     * 已参与人数
     */
    private Integer joinPeople;
    /**
     * 活动时间，1.永久 2.自定义
     */
    private Integer during;
    /**
     * 活动开始时间
     */
    private LocalDateTime startDate;
    /**
     * 活动结束时间
     */
    private LocalDateTime endDate;
    /**
     * 有效时间
     */
    private String validDate;
}