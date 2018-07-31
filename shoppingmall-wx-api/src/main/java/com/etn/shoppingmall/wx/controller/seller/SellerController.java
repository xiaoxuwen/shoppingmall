package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.core.service.SellerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 商家端商家信息接口
 * User: lihu
 * Date: 2018/7/26 11:49
 * Version: V1.0
 */
@Api(value = "商家业务的接口", tags = {"商家业务的controller"})
@RestController
@RequestMapping("/wx/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;
}
