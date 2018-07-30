package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.Seller;

/**
 * Description:商家Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:30
 * Version: V1.0
 */
public interface SellerMapper extends MyMapper<Seller> {
    int add(Seller seller);
}
