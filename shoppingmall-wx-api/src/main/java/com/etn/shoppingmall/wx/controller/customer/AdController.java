package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:顾客端广告接口
 * User: xiaoxuwen
 * Date: 2018-07-24 9:44
 * Version: V1.0
 */

@Api(tags = {"用户端，首页广告"},description = "首页广告的接口")
@RestController
@RequestMapping("/wx/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @ApiOperation(value = "不分页获取广告", notes = "不分页获取广告的接口")
    @GetMapping("/list")
    public Object list() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        return ResponseUtil.ok(adService.listAd());
    }
}
