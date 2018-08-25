package com.etn.shoppingmall.core.model;

import com.etn.shoppingmall.core.entity.CollageUser;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * User: lihu
 * Date: 2018/8/23 9:04
 * Version: V1.0
 */
@Data
public class CollageUsers {

    /**
     * 拼团发起者
     */
    private CollageUser collageUser;
    /**
     * 拼团参与者
     */
    private List<CollageUser> collageUserList;
}
