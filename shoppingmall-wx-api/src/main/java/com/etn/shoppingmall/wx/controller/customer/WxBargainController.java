package com.etn.shoppingmall.wx.controller.customer;

import com.etn.shoppingmall.common.util.*;
import com.etn.shoppingmall.core.entity.*;
import com.etn.shoppingmall.core.model.FinalValue;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.*;
import com.etn.shoppingmall.wx.annotation.LoginUser;
import com.etn.shoppingmall.wx.model.NumberManager;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Description:砍价产品
 * User: xiaoxuwen
 * Date: 2018/8/7  9:47
 * Modified By:
 */
@RestController
@RequestMapping("wx/user")
public class WxBargainController {

    private Log log = LogFactory.getLog(WxBargainController.class);

    @Autowired
    private BargainService bargainService;
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
     * 1、砍价产品列表
     *
     * @return
     */
    @GetMapping("/listBargain")
    public ResponseUtil listBargain() {
        SystemContext.setSort("priority");
        SystemContext.setOrder("desc");
        List<Bargain> list = bargainService.list();
        return ResponseUtil.ok(list);
    }

    /**
     * 2、砍价详情页
     *
     * @param bid 砍价产品id
     * @return
     */
    @GetMapping("/bargainDetail")
    public ResponseUtil bargainDetail(@LoginUser Integer userId, @RequestParam Integer bid,@RequestParam("af")String af) {
        if (userId == null || bid == null || StringUtil.isBlank(af)){
            return ResponseUtil.badArgument();
        }
        //获取砍价产品信息
        Bargain bargain = bargainService.load(bid);
        //获取砍价发起者
        List<BargainUser> bargainUserList = bargainService.bidAndUserIdByBargainUser(null,FinalValue.SPONSOR,bid,af);
        if (bargainUserList.size() < 1){
            return ResponseUtil.fail(2,"请先发起砍价！");
        }
        BargainUser bargainUser = bargainUserList.get(0);
        //获取砍价发起者信息
        User userInfo = userService.load(bargainUser.getUser().getId());
        //根据关联编号和产品id获取参与者
        List<BargainUser> bus = bargainService.listBargainUser(bid,af);
        //获取已砍价格
        BigDecimal nowPrice=new BigDecimal("0");
        for (BargainUser bu : bus) {
            nowPrice.add(bu.getPrice());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userInfo",userInfo);
        map.put("bargain", bargain);
        map.put("nowPrice", nowPrice);
        map.put("people", bus.size());
        map.put("bus", bus);
        return ResponseUtil.ok(map);
    }

    /**
     * 3.发起砍价
     *
     * @return
     */
    @PostMapping("/launchBargain")
    @Transactional
    public ResponseUtil launchBargain(@LoginUser Integer userId, @RequestParam("bid")Integer bid) {
        log.info("===============发起砍价业务开始================");
        if (bid == null || userId == null){
            return ResponseUtil.badArgument();
        }

        //获取砍价产品
        Bargain bargain = bargainService.load(bid);
        if (bargain == null){
            return ResponseUtil.badArgumentValue();
        }
        //获取当前用户信息
        User userInfo = userService.load(userId);
        //获取砍价发起者
        List<BargainUser> bargainUserList = bargainService.bidAndUserIdByBargainUser(userId,1,bid,null);

        //验证会员身份
        if (userInfo.getUserLevel() != FinalValue.USER_LEVEL_VIP){
            return ResponseUtil.fail(2,"只有会员才能发起砍价,请充值会员！");
        }
        //对于同一个砍价品，只能发起一次砍价
        if (bargainUserList.size() > 0){
            return ResponseUtil.fail(3,"对于同一个砍价产品，你只能发起一次砍价！");
        }
        //判断产品是否到使用时间
        if ((bargain.getDuring() == 2 && bargain.getStartDate().isAfter(LocalDateTime.now()))){
            return ResponseUtil.fail(4,"该产品还未到使用时间！");
        }
        //判断产品是否过期、下架
        if ((bargain.getEndDate().isBefore(LocalDateTime.now()) && bargain.getDuring() == 2) || bargain.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        //判断产品数量
        if (bargain.getFen() < 1){
            return ResponseUtil.fail(6,"该产品已卖完!");
        }

        log.info("用户:"+userInfo+"========开始砍价！==========砍价产品:bargain="+bargain);
        //发起者所砍价格
        BigDecimal price = bargain.getPrice().subtract(bargain.getLowPrice());
        BigDecimal newPrice = RandomsUtil.num(price);
        //砍价
        BargainUser newBargainUser = new BargainUser();
        newBargainUser.setUser(userInfo);
        newBargainUser.setProductId(bid);
        newBargainUser.setAf(NumberManager.getAf(bid,userId));
        newBargainUser.setFlag(FinalValue.SPONSOR);
        newBargainUser.setPrice(newPrice);
        newBargainUser.setAddTime(LocalDateTime.now());
        newBargainUser.setDeleted(false);

        //更改产品参与人数
        bargain.setJoinPeople(bargain.getJoinPeople()+1);

        try{
            boolean booAddBargainUser = bargainService.addBargainUser(newBargainUser);
            if (!booAddBargainUser){
                throw new RuntimeException("添加数据异常:砍价失败");
            }
            boolean booUpdate = bargainService.update(bargain);
            if (!booUpdate){
                throw new RuntimeException("修改数据异常:更改产品参与人数失败");
            }
        }catch (Exception e){
            log.info("用户:"+userInfo+"========砍价失败！砍价产品:bargain="+bargain);
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseUtil.serious();
        }

        log.info("用户:"+userInfo+"========砍价成功！砍价产品:bargain="+bargain);
        log.info("==============发起砍价业务结束========");
        return ResponseUtil.ok(newBargainUser);
    }

    /**
     * 4.参与砍价
     *
     * @param bid
     * @return
     */
    @PostMapping("/addBargain")
    @Transactional
    public ResponseUtil addBargain(@LoginUser Integer userId, @RequestParam("bid") Integer bid, @RequestParam("af") String af, HttpServletRequest request) {
        log.info("=========参与砍价业务开始！=========");
        if (bid == null || userId == null || StringUtil.isBlank(af)){
            return ResponseUtil.badArgument();
        }
        //用户信息
        User userInfo = userService.load(userId);
        //产品信息
        Bargain bargain = bargainService.load(bid);
        if (bargain == null){
            return ResponseUtil.badArgumentValue();
        }
        //获取发起者
        BargainUser bargainUser = bargainService.bidAndUserIdByBargainUser(null,FinalValue.SPONSOR,null,af).get(0);
        //店铺信息
        Shop shop = shopService.load(bargain.getShopId());
        //根据关联编号和产品id获取所有砍价人
        List<BargainUser> bus = bargainService.listBargainUser(bid,af);

        //验证参与砍价的用户是不是发起者
        if (bargainUser.getUser().getId() == userId){
            return ResponseUtil.fail(2,"对于同一个砍价品，您只能砍价一次!");
        }
        //验证用户是否参与过此产品
        List<BargainUser> verify = bargainService.bidAndUserIdByBargainUser(userId,FinalValue.PARTICIPANT,bid,null);
        if (verify.size() > 0){
            return ResponseUtil.fail(2,"对于同一个砍价品，您只能帮忙砍价一次!");
        }
        //验证用户的帮助次数
        if (bargainService.todaysBargainUserByUserId(userId)){
            return ResponseUtil.fail(3,"您每天最多可以帮助3个好友砍价!");
        }
        //发起砍价24小时内砍价完成有效，逾期无效
        if (LocalDateTime.now().isAfter(bargainUser.getAddTime().plusDays(1))){
            return ResponseUtil.fail(4,"砍价已过期！");
        }
        //判断产品是否过期、下架
        if ((bargain.getDuring() == 2  && bargain.getEndDate().isBefore(LocalDateTime.now())) || bargain.getDeleted()==true){
            return ResponseUtil.fail(5,"该产品已过期/下架！");
        }
        if (bus.size() == bargain.getPeople()){
            return ResponseUtil.fail(6,"该产品已完成砍价！");
        }

        //参与者所砍价格
        BigDecimal newPrice = bargain.getPrice().subtract(bargain.getLowPrice());
        for (int i = 0; i < bus.size(); i++) {
            newPrice=newPrice.subtract(bus.get(i).getPrice());
        }
        if( bus.size()+1 != bargain.getPeople()){
            newPrice = RandomsUtil.num(newPrice);
        }

        //参与砍价
        BargainUser newBargainUser = new BargainUser();
        newBargainUser.setUser(userInfo);
        newBargainUser.setProductId(bid);
        newBargainUser.setFlag(FinalValue.PARTICIPANT);
        newBargainUser.setAf(af);
        newBargainUser.setPrice(newPrice);
        newBargainUser.setAddTime(LocalDateTime.now());
        newBargainUser.setDeleted(false);

        try {
            boolean boo = bargainService.addBargainUser(newBargainUser);
            if (!boo){
                throw new RuntimeException("添加数据异常:参与砍价失败！");
            }

            //更改产品参与人数
            bargain.setJoinPeople(bargain.getJoinPeople()+1);
            boolean booJoin = bargainService.update(bargain);
            if (!booJoin){
                log.error("=======更改砍价产品参与人数失败！");
                throw  new RuntimeException("更改砍价产品参与人数失败");
            }


            //判断砍价成功，参与人数达到指定人数,生成订单
            if(bus.size()+1 >= bargain.getPeople()){
                log.info("砍价人数达到指定人数，开始订单业务！");
                Order order = new Order();
                //产品id
                Product product = new Product();
                product.setId(bargain.getId());
                //生成订单编号
                String sn = NumberManager.getSn(bargainUser.getUser().getId());
                //生成二维码
                String key =generateKey("erweima.png");
                String  requestUrl= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                String qrCodeUrl = requestUrl+"/wx/seller/verification?sn="+sn;
                int width2 = 300, height2 = 300;
                ZxingHandler.encode2(qrCodeUrl, width2, height2,  localOsService.getPath(key));
                String url = localOsService.generateUrl(key);

                //上传二维码
                Storage storage = new Storage();
                storage.setName("订单二维码");
                storage.setSize(1);
                storage.setType("image/png");
                storage.setAddTime(LocalDateTime.now());
                storage.setIkey(key);
                storage.setUrl(url);
                storageService.add(storage);

                //生成订单
                order.setUser(bargainUser.getUser());
                order.setSn(sn);
                order.setStatus(FinalValue.ORDER_STATUS_UNTAPPED);
                order.setOrderType(FinalValue.ORDER_TYPE_BARGAIN);
                order.setAddTime(LocalDateTime.now());
                order.setShop(shop);
                order.setProduct(product);
                order.setDeleted(FinalValue.NOT_DELETED);
                order.setQrCodePic(url);
                order.setQrCodeUrl(qrCodeUrl);
                boolean booAdd = orderService.add(order);
                if (!booAdd){
                    log.error("=======添加砍价订单失败！");
                    throw new RuntimeException("添加砍价订单失败");
                }

                //更改砍价产品数量
                bargain.setFen(bargain.getFen()-1);
                boolean booFen = bargainService.update(bargain);
                if (!booFen){
                    log.error("=======更改砍价产品数量失败！");
                    throw  new RuntimeException("更改砍价产品数量失败");
                }
                //更改店铺订单量
                shop.setOrderNum(shop.getOrderNum()+1);
                boolean booOrderNum = shopService.update(shop);
                if (!booOrderNum){
                    log.error("=======更改店铺订单量失败！");
                    throw new RuntimeException("更改店铺订单量失败");
                }

                log.info("订单业务结束！");
            }

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseUtil.unsupport();
        }
        log.info("=========参与砍价业务结束！=========");
        return ResponseUtil.ok(newBargainUser);

    }

    /**
     * 图片编号查重
     * @param originalFilename
     * @return
     */
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
