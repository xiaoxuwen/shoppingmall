package com.etn.shoppingmall.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description:广告实体类
 * User: xiaoxuwen
 * Date: 2018-07-21 14:15
 * Version: V1.0
 */
@Data
@Table(name = "t_ad")
public class Ad extends BaseEntity {
    /**
     * 广告标题
     */
    private String name;
    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    private String link;
    /**
     * 广告宣传图片
     */
    private String url;
    /**
     * 广告位置
     */
    private Integer position;
    /**
     * 广告内容
     */
    private String content;
    /**
     * 状态,1.正常，0 停用
     */
    private Boolean enabled;

    /**
     * 排序
     */
    private Integer priority;
}
