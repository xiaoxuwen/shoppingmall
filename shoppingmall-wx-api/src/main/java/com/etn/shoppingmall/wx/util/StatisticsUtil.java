package com.etn.shoppingmall.wx.util;

import com.etn.shoppingmall.core.entity.Order;
import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.wx.model.ProductStatisticsInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: 产品核销统计工具类
 * User: lihu
 * Date: 2018/8/7 9:03
 * Version: V1.0
 */
public class StatisticsUtil {

    private Integer shopId;    //商铺id
    private List<Order> orderList;    //订单
    private List<Product> productList;    //产品
    private Set<Integer> idSet;    //订单筛选后产品id的集合

    /**
     * 初始化产品核销统计工具类
     * @param shopId
     * @param orderList
     * @param productList
     */
    public StatisticsUtil(Integer shopId, List<Order> orderList, List<Product> productList) {
        this.shopId = shopId;
        this.orderList = orderList;
        this.productList = productList;
        this.idSet = getIdSet();
    }

    /**
     * 筛选订单
     * @return  产品id集合
     */
    private Set<Integer> getIdSet(){
        idSet = new HashSet<Integer>();
        for (int i = orderList.size()-1 ; i >= 0 ; i--){
            Order order = orderList.get(i);
            if (order.getShop().getId() == shopId){
                idSet.add(order.getProduct().getId());
            }else {
                orderList.remove(i);
            }
        }
        return idSet;
    }

    /**
     * 获取统计数据
     * @return 统计数据
     */
    public List<ProductStatisticsInfo> getPSI(){
        List<Integer> list = new ArrayList<Integer>();
        List<ProductStatisticsInfo> listPSI = new ArrayList<ProductStatisticsInfo>();
        //迭代产品id
        for (Integer id : idSet) {
            //创建ProductStatisticsInfo统计model
            ProductStatisticsInfo PSI = new ProductStatisticsInfo();
            //降序迭代筛选后的订单
            for (int i=orderList.size()-1;i>=0;i--){
                //产品分类
                if (orderList.get(i).getProduct().getId() == id){
                    PSI.setProductName(orderList.get(i).getProduct().getName());
                    PSI.setProductRemainCount(getProductFen(orderList.get(i).getProduct().getId()));
                    if (orderList.get(i).getStatus() == 2){
                        PSI.setProductVerificationCount(PSI.getProductVerificationCount()+1);
                    }else {
                        PSI.setProductRemainVerificationCount(PSI.getProductRemainVerificationCount()+1);
                    }
                    //删除已分类的订单
                    orderList.remove(i);
                }
            }
            //添加分类
            listPSI.add(PSI);
        }
        return listPSI;
    }

    /**
     * 获取产品剩余份数
     * @param productId 产品id
     * @return 剩余份数
     */
    private Integer getProductFen(int productId){
        for (Product product:productList) {
            if(product.getId() == productId){
                return product.getFen();
            }
        }
        return 0;
    }

}
