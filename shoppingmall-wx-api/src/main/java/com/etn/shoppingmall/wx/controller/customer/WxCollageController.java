package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.CharUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.common.util.StringUtil;
import com.etn.shoppingmall.common.util.ZxingHandler;
import com.etn.shoppingmall.core.entity.*;
import com.etn.shoppingmall.core.model.CollageUsers;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.*;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.NumberManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:拼团产品
 * User: xiaoxuwen
 * Date: 2018/8/7  9:48
 * Modified By:
 */
@RestController
@RequestMapping("wx/user")
public class WxCollageController {

    private Log log = LogFactory.getLog(WxCollageController.class);

    @Autowired
    private CollageService collageService;
    @Autowired
    private CollageUserService collageUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectStorageService localOsService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ShopService shopService;

    /**
     * 拼团产品列表
     * @return
     */
    @GetMapping("/listCollage")
    public ResponseUtil listCollage(){
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Collage> list = collageService.list();
        return ResponseUtil.ok(list);
    }

    /**
     * 2、拼团详情页
     *
     * @param bid 砍价产品id
     * @return
     */
    @GetMapping("/collageDetail")
    public ResponseUtil bargainDetail(@LoginUser Integer userId, @RequestParam Integer bid) {
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }
        User userInfo = userService.load(userId);
        Collage collage = collageService.load(bid);
        List<CollageUsers> collageUsersList =collageUserService.listCollageUsers(bid);
        Map<String, Object> map = new HashMap<>();
        map.put("userInfo",userInfo);
        map.put("collage", collage);
        map.put("collageUsersList", collageUsersList);
        return ResponseUtil.ok(map);
    }

    /**
     * 3.开团
     *
     * @return
     */
    @PostMapping("/launchCollage")
    @Transactional
    public ResponseUtil launchCollage(@LoginUser Integer userId, @RequestParam("bid")Integer bid) {
        log.info("========拼团业务开始========");
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }
        //获取用户信息
        User userInfo = userService.load(userId);
        //拼团产品信息
        Collage collage = collageService.load(bid);

        //验证会员身份
        if (userInfo.getUserLevel() != FinalValue.USER_LEVEL_VIP){
            return ResponseUtil.fail(2,"只有会员才能开团,请充值会员！");
        }
        //判断产品是否到使用时间
        if ((collage.getStartDate().isAfter(LocalDateTime.now()) && collage.getDuring() == 2)){
            return ResponseUtil.fail(4,"该产品还未到使用时间！");
        }
        //判断产品是否过期、下架
        if ((collage.getEndDate().isBefore(LocalDateTime.now()) && collage.getDuring() == 2) || collage.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        //判断产品数量
        if (collage.getFen() < 1){
            return ResponseUtil.fail(6,"该产品已卖完!");
        }

        //开团
        CollageUser collageUser  = new CollageUser();
        collageUser.setProductId(bid);
        collageUser.setUser(userInfo);
        collageUser.setAf(NumberManager.getAf(bid,userId));
        collageUser.setFlag(1);
        collageUser.setAddTime(LocalDateTime.now());
        collageUser.setDeleted(FinalValue.NOT_DELETED);

        //更改产品参与人数
        collage.setJoinPeople(collage.getJoinPeople()+1);

        try {
            log.info("==========开团业务开始============");
            boolean booAdd = collageUserService.add(collageUser);
            if (!booAdd){
                throw new RuntimeException("添加数据异常:开团失败");
            }
            boolean booUpdate = collageService.update(collage);
            if (!booUpdate){
                throw new RuntimeException("修改数据异常:更改产品参与人数失败");
            }
            log.info("==========开团业务结束============");
        }catch (Exception e){
            log.info("用户:"+userInfo+"========开团失败！拼团产品:collage="+collage);
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseUtil.serious();
        }
        log.info("==========拼团业务结束============");
        return ResponseUtil.ok(collageUser);
    }

    /**
     * 参团
     * @param userId
     * @param bid
     * @param af
     * @return
     */
    @PostMapping("/addCollage")
    public ResponseUtil addBargain(@LoginUser Integer userId, @RequestParam("bid") Integer bid, @RequestParam("af") String af, HttpServletRequest request) {

        if (bid == null || userId == null || StringUtil.isBlank(af)){
            return ResponseUtil.badArgument();
        }
        //用户信息
        User userInfo = userService.load(userId);
        //产品信息
        Collage collage = collageService.load(bid);
        //店铺信息
        Shop shop = shopService.load(collage.getShopId());
        //获取拼团发起者
        CollageUser sponsorCollageUser = collageUserService.querySponsor(af);

        //验证参与拼团的用户是不是发起者
        if (sponsorCollageUser.getUser().getId() == userId){
            return ResponseUtil.fail(2,"你不能和自己拼团！");
        }

        //验证会员身份
        if (userInfo.getUserLevel() != FinalValue.USER_LEVEL_VIP){
            return ResponseUtil.fail(3,"只有会员才能参团,请充值会员！");
        }
        //判断产品是否到使用时间
        if ((collage.getStartDate().isAfter(LocalDateTime.now()) && collage.getDuring() == 2)){
            return ResponseUtil.fail(4,"该产品还未到使用时间！");
        }
        //判断产品是否过期、下架
        if ((collage.getEndDate().isBefore(LocalDateTime.now()) && collage.getDuring() == 2) || collage.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        //判断产品数量
        if (collage.getFen() < 1){
            return ResponseUtil.fail(6,"该产品已卖完!");
        }

        //参团数据
        CollageUser newCollageUser  = new CollageUser();
        newCollageUser.setProductId(bid);
        newCollageUser.setUser(userInfo);
        newCollageUser.setAf(af);
        newCollageUser.setFlag(1);
        newCollageUser.setAddTime(LocalDateTime.now());
        newCollageUser.setDeleted(FinalValue.NOT_DELETED);

        try {
            //参团
            boolean booAdd = collageUserService.add(newCollageUser);
            if (!booAdd){
                throw new RuntimeException("添加数据异常:参团失败");
            }
            //更改产品参与人数
            collage.setJoinPeople(collage.getJoinPeople()+1);
            boolean booJoin = collageService.update(collage);
            if (!booJoin){
                throw new RuntimeException("修改数据异常:更改拼团产品参与人数失败");
            }

            List<CollageUser> collageUserList = collageUserService.listByAf(af);
            Order userOrder = null;
            if (collageUserList.size() == collage.getPeople()){
                for (CollageUser collageUser:collageUserList) {
                    Order order = new Order();
                    //产品id
                    Product product = new Product();
                    product.setId(collage.getId());
                    //生成订单编号
                    String sn = NumberManager.getSn(collageUser.getUser().getId());
                    //生成二维码
                    String key =generateKey("erweima.png");
                    String  requestUrl= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
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
                    storageService.add(storage);

                    //生成订单
                    order.setUser(collageUser.getUser());
                    order.setSn(sn);
                    order.setStatus(FinalValue.ORDER_STATUS_UNTAPPED);
                    order.setOrderType(FinalValue.ORDER_TYPE_COLLAGE);
                    order.setAddTime(LocalDateTime.now());
                    order.setShop(shop);
                    order.setProduct(product);
                    order.setDeleted(FinalValue.NOT_DELETED);
                    order.setQrCodePic(url);
                    order.setQrCodeUrl(qrCodeUrl);
                    boolean booAddOrder = orderService.add(order);
                    if (!booAddOrder){
                        log.error("=======添加拼团订单失败！");
                        throw new RuntimeException("添加拼团订单失败");
                    }

                    //更改拼团产品数量
                    collage.setFen(collage.getFen()-1);
                    boolean booFen = collageService.update(collage);
                    if (!booFen){
                        log.error("=======更改拼团产品数量失败！");
                        throw  new RuntimeException("更改拼团产品数量失败");
                    }
                    //更改店铺订单量
                    shop.setOrderNum(shop.getOrderNum()+1);
                    boolean booOrderNum = shopService.update(shop);
                    if (!booOrderNum){
                        log.error("=======更改店铺订单量失败！");
                        throw new RuntimeException("更改店铺订单量失败");
                    }

                    if (userId == collageUser.getUser().getId()){
                        userOrder=order;
                    }
                }
                log.info("订单业务结束！");
                return ResponseUtil.ok("拼团成功",userOrder);
            }
        }catch (Exception e){
            log.info("用户:"+userInfo+"========开团失败！拼团产品:collage="+collage);
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseUtil.serious();
        }
        log.info("=========拼团业务结束============");
        return ResponseUtil.ok("参团成功",newCollageUser);

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
