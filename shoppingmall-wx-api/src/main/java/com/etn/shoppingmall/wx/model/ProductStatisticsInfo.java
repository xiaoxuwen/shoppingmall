package com.etn.shoppingmall.wx.model;

import lombok.Data;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/6 11:43
 * Version: V1.0
 */
@Data
public class ProductStatisticsInfo {
    private Integer productID;
    private String productName;    //产品名称
    private Integer productCount;    //产品总数
    private Integer productRemainCount;    //产品剩余数量
    private Integer productVerificationCount;    //产品核销数量
    private Integer productRemainVerificationCount;    //产品未核销数量

    public ProductStatisticsInfo(){
        this.productName = "";
        this.productCount = 0;
        this.productRemainCount = 0;
        this.productVerificationCount = 0;
        this.productRemainVerificationCount = 0;
    }

    public Integer getProductCount(){
        return productRemainCount+productVerificationCount+productRemainVerificationCount;
    }

}
