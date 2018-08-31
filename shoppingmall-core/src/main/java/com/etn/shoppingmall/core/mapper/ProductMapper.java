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
     * @param name
     * @param latitude
     * @param longitude
     * @param categoryId
     * @param sort   需要排序的字段
     * @param order  排序方式
     * @return
     */
    List<Map<String,Object>> listDistanceProduct(@Param("name") String name , @Param("latitude") Double latitude,
                                                 @Param("longitude") Double longitude,@Param("categoryId")Integer categoryId,
                                                 @Param("sort")String sort,@Param("order")String order);
}
