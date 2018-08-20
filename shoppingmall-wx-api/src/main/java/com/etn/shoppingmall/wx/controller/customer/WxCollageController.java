package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Collage;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.CollageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:拼团产品
 * User: xiaoxuwen
 * Date: 2018/8/7  9:48
 * Modified By:
 */
@RestController
@RequestMapping("wx/collage")
public class WxCollageController {

    @Autowired
    private CollageService collageService;

    /**
     * 拼团产品列表
     * @return
     */
    @GetMapping("/list")
    public ResponseUtil list(){
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Collage> list = collageService.list(null);
        return ResponseUtil.ok(list);
    }
}
