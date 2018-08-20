package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description:产品Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:28
 * Version: V1.0
 */
public interface ProductMapper extends MyMapper<Product> {

    /**
     * 距离最近的产品
     * @param latitude
     * @param longitude
     * @return
     */
    List<Map<String,Object>> listDistanceProduct(@Param("name") String name , @Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
