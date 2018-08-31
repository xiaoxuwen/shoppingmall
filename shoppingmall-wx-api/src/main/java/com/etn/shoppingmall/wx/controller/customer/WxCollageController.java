package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.*;
import com.etn.shoppingmall.core.entity.*;
import com.etn.shoppingmall.core.model.CollageUsers;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.*;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.NumberManager;
import io.swagger.models.auth.In;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
     * 1.拼团产品列表
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
     * 2、拼团产品详情页
     *
     * @param bid 拼团产品产品id
     * @return
     * {
     *     isSponsor  //是否发起过此产品的 1发起过  0未发起过
     * }
     */
    @GetMapping("/collageDetail")
    public ResponseUtil collageDetail(@LoginUser Integer userId, @RequestParam Integer bid) {
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }
        Collage collage = collageService.load(bid);
        List<CollageUsers> collageUsersList = collageUserService.listCollageUsers(bid);
        Integer isSponsor = collageUserService.verify(bid,userId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSponsor",isSponsor);
        map.put("collage", collage);
        map.put("collageUsersList", collageUsersList);
        return ResponseUtil.ok(map);
    }

    /**
     * 3.开团
     * @param body
     * {
     *     bid;""
     * }
     * @return
     */
    @PostMapping("/launchCollage")
    @Transactional
    public ResponseUtil launchCollage(@LoginUser Integer userId,@RequestBody String body) {
        log.info("========拼团业务开始========");
        Integer bid = JacksonUtil.parseInteger(body,"bid");
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }
        //获取用户信息
        User userInfo = userService.load(userId);
        //拼团产品信息
        Collage collage = collageService.load(bid);
        if (collage == null){
            return ResponseUtil.badArgumentValue();
        }

        //验证会员身份
        if (userInfo.getUserLevel() != FinalValue.USER_LEVEL_VIP){
            return ResponseUtil.fail(2,"只有会员才能开团,请充值会员！");
        }
        //判断产品是否到使用时间
        if ((collage.getDuring() == 2 && collage.getStartDate().isAfter(LocalDateTime.now()))){
            return ResponseUtil.fail(4,"该产品还未到使用时间！");
        }
        //判断产品是否过期、下架
        if ((collage.getDuring() == 2 && collage.getEndDate().isBefore(LocalDateTime.now())) || collage.getDeleted()==true){
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
        collageUser.setAf(NumberManager.getAf());
        collageUser.setFlag(FinalValue.SPONSOR);
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
     * 4.参团
     * @param body
     * {
     *     bid:""    //拼团产品id
     *     af:""   //团标识
     * }
     * @return
     */
    @PostMapping("/addCollage")
    @Transactional
    public ResponseUtil addBargain(@LoginUser Integer userId,@RequestBody String body, HttpServletRequest request) {
        log.info("===========拼团业务开始===========");
        Integer bid = JacksonUtil.parseInteger(body,"bid");
        String af = JacksonUtil.parseString(body,"af");
        if (bid == null || userId == null || StringUtil.isBlank(af)){
            return ResponseUtil.badArgument();
        }
        //用户信息
        User userInfo = userService.load(userId);
        //产品信息
        Collage collage = collageService.load(bid);
        if (collage == null){
            return ResponseUtil.badArgumentValue();
        }
        //店铺信息
        Shop shop = shopService.load(collage.getShopId());
        //获取拼团发起者
        CollageUser sponsorCollageUser = collageUserService.querySponsor(af);
        //获取所以参团人
        List<CollageUser> cus = collageUserService.listByAf(af);

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
        if ((collage.getDuring() == 2 && collage.getEndDate().isBefore(LocalDateTime.now())) || collage.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        //判断产品数量
        if (collage.getFen() < 1){
            return ResponseUtil.fail(6,"该产品已卖完!");
        }

        if (cus.size() == collage.getPeople()){
            return ResponseUtil.fail(7,"此团已达到最大参与人数！");
        }

        //参团数据
        CollageUser newCollageUser  = new CollageUser();
        newCollageUser.setProductId(bid);
        newCollageUser.setUser(userInfo);
        newCollageUser.setAf(af);
        newCollageUser.setFlag(FinalValue.PARTICIPANT);
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
            //获取更新后的所以参团人
            List<CollageUser> collageUserList = collageUserService.listByAf(af);
            Order userOrder = null;
            if (collageUserList.size() == collage.getPeople()){
                for (CollageUser collageUser:collageUserList) {
                    Order order = new Order();
                    //产品id
                    Product product = new Product();
                    product.setId(collage.getId());
                    //生成订单编号
                    String sn = NumberManager.getSn();
                    //生成二维码
                    String key =generateKey("erweima.png");
                    String requestUrl= request.getScheme()+"://"+request.getServerName();
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
                Integer deleteNumber = collageUserService.deleteByAf(af);
                if (deleteNumber != collageUserList.size()){
                    throw new RuntimeException("删除拼团数据失败！");
                }
                log.info("订单业务结束！deleteNumber"+deleteNumber);
                return ResponseUtil.ok("拼团成功",userOrder);
            }
        }catch (Exception e){
            log.info("用户:"+userInfo+"========参团失败！拼团产品:collage="+collage);
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


    /**
     * 5.我的拼团详情
     * @param userId
     * @param af
     * @return
     * {
     *     code             //拼团状态  0,正在拼团  1,拼团成功  2,已过期
     *     collage          //拼团产品
     *     shortPeople      //还差几人
     *     collageUserList  //拼团人列表
     *     beginTime        //开团时间
     * }
     */
    @GetMapping("/myCollageDetail")
    public ResponseUtil myCollageDetail(@LoginUser Integer userId ,String af){
        if (StringUtil.isBlank(af) || userId == null){
            return ResponseUtil.badArgument();
        }

        List<CollageUser> collageUserList = collageUserService.listByAf(af);
        CollageUser sponsor = collageUserService.querySponsor(af);
        Collage collage = collageService.load(sponsor.getProductId());
        Integer code = 0;
        System.out.println(sponsor);
        if (sponsor.getDeleted()){
            code = 1;
        }
        if (!(sponsor.getDeleted()) && sponsor.getAddTime().plusDays(1).isBefore(LocalDateTime.now())){
            code = 2;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("collage",collage);
        map.put("shortPeople", (collage.getPeople()-collageUserList.size()));
        map.put("collageUserList", collageUserList);
        map.put("beginTime",sponsor.getAddTime());
        return ResponseUtil.ok(map);
    }

}
