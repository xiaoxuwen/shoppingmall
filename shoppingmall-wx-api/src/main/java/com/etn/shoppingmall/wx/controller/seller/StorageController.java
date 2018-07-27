package com.etn.shoppingmall.wx.controller.seller;

import com.etn.shoppingmall.core.service.StorageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 顾客端文件存储接口
 * User: lihu
 * Date: 2018/7/26 11:44
 * Version: V1.0
 */
@Api(value = "文件存储业务的接口", tags = {"文件存储业务的controller"})
@RestController
@RequestMapping("/wx/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;
}
