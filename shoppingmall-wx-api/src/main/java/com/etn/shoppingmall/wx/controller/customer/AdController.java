package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:顾客端广告接口
 * User: xiaoxuwen
 * Date: 2018-07-24 9:44
 * Version: V1.0
 */

@Api(value = "广告业务的接口", tags = {"广告业务的controller"})
@RestController
@RequestMapping("/wx/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @ApiOperation(value = "不分页获取广告", notes = "不分页获取广告的接口")
    @GetMapping("/list")
    public Object list() {
        return ResponseUtil.ok();
    }

    @ApiOperation(value = "分页获取广告", notes = "分页获取广告的接口")
    @GetMapping("/find")
    public Object find() {
        return ResponseUtil.ok();
    }
}
