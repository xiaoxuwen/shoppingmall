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

    @ApiOperation(value = "获取背景音乐列表", notes = "获取背景音乐列表的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true, dataType="String", paramType="query")
    @GetMapping("/list")
    public Object list(@RequestParam String userId) {
        return ResponseUtil.ok();
    }
}
