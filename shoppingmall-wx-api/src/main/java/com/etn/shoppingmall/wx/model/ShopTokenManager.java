package com.etn.shoppingmall.wx.model;

import com.etn.shoppingmall.common.util.CharUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/17 10:11
 * Version: V1.0
 */
public class ShopTokenManager {

    private static Map<String, ShopToken> tokenMap = new HashMap<>();
    private static Map<Integer, ShopToken> idMap = new HashMap<>();

    public static Integer getShopId(String token) {
        ShopToken shopToken = tokenMap.get(token);
        if (shopToken == null) {
            return null;
        }

        if (shopToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(shopToken.getShopId());
            return null;
        }

        return shopToken.getShopId();
    }


    public static ShopToken generateToken(Integer id) {
        ShopToken shopToken = null;

        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        shopToken = new ShopToken();
        shopToken.setToken(token);
        shopToken.setUpdateTime(update);
        shopToken.setExpireTime(expire);
        shopToken.setShopId(id);
        tokenMap.put(token, shopToken);
        idMap.put(id, shopToken);

        return shopToken;
    }
}
