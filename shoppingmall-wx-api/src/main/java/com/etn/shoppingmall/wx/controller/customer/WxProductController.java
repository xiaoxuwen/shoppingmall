package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.*;
import com.etn.shoppingmall.core.entity.*;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.service.*;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.NumberManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:产品详情和下单
 * User: xiaoxuwen
 * Date: 2018/8/7  9:53
 * Modified By:
 */
@RestController
@RequestMapping("/wx/user")
public class WxProductController {

    private Log log = LogFactory.getLog(WxProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectStorageService localOsService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private OrderService orderService;


    /**
     * 1.产品详情页接口
     * @param productId 产品id
     * @return
     */
    @PostMapping("/detailsProduct")
    public ResponseUtil detailsProduct(@RequestParam("productId")Integer productId){
        if (productId == null){
            return ResponseUtil.badArgument();
        }
        Product product = productService.load(productId);
        Shop shop = shopService.load(product.getShopId());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("product",product);
        map.put("shop",shop);
        return ResponseUtil.ok(map);
    }

    /**
     *2.预定产品，生成订单优惠券
     * @param userId
     * @param body
     * {
     *     productId:
     * }
     * @return
     */
    @PostMapping("/orders")
    @Transactional
    public ResponseUtil orders(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request){
        log.info("============预定产品下单业务开始！=============");
        if (userId == null || StringUtil.isBlank(body)){
            log.info("=====参数值为空！====userId="+userId+"====body="+body);
            return ResponseUtil.badArgument();
        }
        Integer productId = JacksonUtil.parseInteger(body, "productId");
        if (productId == null){
            return ResponseUtil.badArgument();
        }
        //产品
        Product product = productService.load(productId);
        //店铺
        Shop shop = shopService.load(product.getShopId());
        //判断是否是会员
        User user = userService.load(userId);
        if (user.getUserLevel() == FinalValue.USER_LEVEL_COMMON){
            return ResponseUtil.fail(2,"请充值会员！");
        }
        //判断是否领取过此店铺的优惠劵
        if(orderService.verificationCoupon(userId,productId,FinalValue.ORDER_STATUS_UNTAPPED,FinalValue.ORDER_TYPE_DIS).size()>0){
            return ResponseUtil.fail(3,"你已预定过此产品(每个产品限量一份)！");
        }

        //判断产品是否到使用时间
        if ((product.getStartDate().isAfter(LocalDateTime.now()) && product.getDuring() == 2)){
            return ResponseUtil.fail(4,"该产品还未到使用时间！");
        }
        //判断产品是否过期、下架
        if ((product.getEndDate().isBefore(LocalDateTime.now()) && product.getDuring() == 2) || product.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        //判断产品数量
        if (product.getFen() < 1){
            return ResponseUtil.fail(6,"该产品已卖完!");
        }
        //生成订单编号
        String sn = NumberManager.getSn();
        //生成二维码
        String key =generateKey("erweima.png");
        String  requestUrl= request.getScheme()+"://"+request.getServerName();
        String qrCodeUrl = requestUrl+"/wx/seller/verification?sn="+sn;
        int width2 = 300, height2 = 300;
        ZxingHandler.encode2(qrCodeUrl, width2, height2,  localOsService.getPath(key));
        String url = localOsService.generateUrl(key);
        Storage storage = new Storage();
        storage.setName("订单二维码");
        storage.setSize(1);
        storage.setType("image/png");
        storage.setAddTime(LocalDateTime.now());
        storage.setIkey(key);
        storage.setUrl(url);

        //更改产品数量
        product.setFen(product.getFen()-1);
        //更改店铺订单量
        shop.setOrderNum(shop.getOrderNum()+1);

        //生成订单
        Order order = new Order();
        order.setUser(user);
        order.setSn(sn);
        order.setStatus(FinalValue.ORDER_STATUS_UNTAPPED);
        order.setOrderType(FinalValue.ORDER_TYPE_DIS);
        order.setAddTime(LocalDateTime.now());
        order.setShop(shop);
        order.setProduct(product);
        order.setDeleted(FinalValue.NOT_DELETED);
        order.setQrCodePic(url);
        order.setQrCodeUrl(qrCodeUrl);

        try{
            storageService.add(storage);
            boolean booAdd = orderService.add(order);
            if (!booAdd){
                log.error("=======添加订单失败！");
                throw new RuntimeException("添加订单失败");
            }
            boolean booFen = productService.update(product);
            if (!booFen){
                log.error("=======更改产品数量失败！");
                throw  new RuntimeException("更改产品数量失败");
            }
            boolean booOrderNum = shopService.update(shop);
            if (!booOrderNum){
                log.error("=======更改店铺订单量失败！");
                throw new RuntimeException("更改店铺订单量失败");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseUtil.unsupport();
        }

        return ResponseUtil.ok(order);
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key;
        Storage storage;

        do {
            key = CharUtil.getRandomString(20) + suffix;
            storage = storageService.queryByKey(key);
        }
        while (storage != null);

        return key;
    }
}
