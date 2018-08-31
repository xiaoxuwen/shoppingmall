package com.etn.shoppingmall.core.mapper;

import com.etn.shoppingmall.core.MyMapper;
import com.etn.shoppingmall.core.entity.CollageUser;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:参与拼团的用户Mapper
 * User: xiaoxuwen
 * Date: 2018-07-23 9:28
 * Version: V1.0
 */
public interface CollageUserMapper extends MyMapper<CollageUser> {

    /**
     * 拼团用户列表
     * @return
     */
    List<CollageUser> listCollageUser(@Param("productId")Integer prductId,@Param("flag")Integer flag,@Param("af")String af);

    /**
     * 添加
     * @param collageUser
     * @return
     */
    boolean addCollageUser(CollageUser collageUser);

    /**
     * 删除
     * @param af
     * @return
     */
    Integer deleteByAf(@Param("af")String af);

    /**
     * 根据产品id和用户id查询
     * @param bid
     * @param userId
     * @return
     */
    List<CollageUser> queryByBidAndUserId(@Param("bid")Integer bid, @Param("userId")Integer userId, @Param("flag")Integer flag,@Param("af")String af);
}
