package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.service.KeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 顾客端关键字接口
 * User: lihu
 * Date: 2018/7/26 11:55
 * Version: V1.0
 */
@Api(value = "关键字业务的接口", tags = {"关键字业务的Controller"})
@RestController
@RequestMapping("/wx/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @ApiOperation(value = "不分页获取关键字",notes = "不分页获取关键字的接口")
    @GetMapping("/list")
    public Object list(){
        return ResponseUtil.ok();
    }
}
