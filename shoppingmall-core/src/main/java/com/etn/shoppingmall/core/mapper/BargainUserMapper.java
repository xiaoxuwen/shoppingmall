package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.BargainUser;
import com.etn.shoppingmall.core.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:参与砍价用户的Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:28
 * Version: V1.0
 */
public interface BargainUserMapper extends MyMapper<BargainUser> {

    /**
     * 获取砍价用户
     * @param productId
     * @return
     */
    List<BargainUser> listBargainUser(@Param("productId") Integer productId,@Param("flag") Integer flag,@Param("userId")Integer userId,@Param("af")String af);

    /**
     * 根据userid获取今天的参与次数
     * @param userId
     * @return
     */
    Integer todaysBargainUserByUserId(@Param("userId") Integer userId);

    /**
     * 添加
     * @param bargainUser
     * @return
     */
    boolean addBargainUser(BargainUser bargainUser);

    /**
     * 删除
     * @param af
     * @return
     */
    Integer deleteByAf(@Param("af")String af);
}
