package com.etn.shoppingmall.core.service.impl;

import com.etn.shoppingmall.core.entity.Product;
import com.etn.shoppingmall.core.mapper.ProductMapper;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: xiaoxuwen
 * Date: 2018-07-24 15:22
 * Version: V1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Product load(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(Product product) {
        return productMapper.insertSelective(product) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(Product product) {
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Integer id) {
        Product product = new Product();
        product.setId(id);
        product.setDeleted(true);
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }

    /**
     * 分页获取产品
     *
     * @return
     */
    @Override
    public Pager<Product> find(String name) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(name)) criteria.andLike("name", "%" + name + "%");

        if (!StringUtils.isEmpty(SystemContext.getSort()) && !StringUtils.isEmpty(SystemContext.getOrder())) {
            example.setOrderByClause(SystemContext.getSort() + " " + SystemContext.getOrder());
        }
        PageHelper.startPage(SystemContext.getPageOffset(), SystemContext.getPageSize());
        List<Product> list = productMapper.selectByExample(example);
        PageInfo<Product> pageList = new PageInfo<>(list);
        return new Pager<>(pageList.getTotal(), list);
    }

    /**
     * 不分页获取产品(行业属性二级筛选)(附带距离)(距离排序)(折扣排序)
     * @param name
     * @param latitude
     * @param longitude
     * @param categoryId
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Map<String,Object>> listProduct(String name, Double latitude, Double longitude,
                                                Integer categoryId,String sort,String order) {
        return productMapper.listDistanceProduct(name,latitude,longitude,categoryId,SystemContext.getSort(),SystemContext.getOrder());
    }

    /**
     * 获取折扣最低产品
     * @param name
     * @return
     */
    @Override
    public List<Product> listDiscountProduct(String name) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);

        if (!StringUtils.isEmpty(name)) criteria.andLike("name", "%" + name + "%");

        example.setOrderByClause("discount" + " " + "desc");
        return productMapper.selectByExample(example);
    }

    /**
     * 获取折扣最低产品(附带距离)
     * @param name
     * @param latitude
     * @param longitude
     * @return
     */
    @Override
    public List<Map<String,Object>> listDiscountProduct(String name, Double latitude, Double longitude) {
        return productMapper.listDistanceProduct(name,latitude,longitude,null,"discount","desc");
    }


    /**
     * 根据行业获取折扣最低产品(附带距离)
     *
     * @param categoryId 行业id
     * @return
     */
    @Override
    public List<Map<String,Object>> listDiscountProductByCategory(Integer categoryId, Double latitude, Double longitude) {
        return productMapper.listDistanceProduct(null,latitude,longitude,categoryId,"discount","desc");
    }

    /**
     * 获取距离最近的产品
     * @param name
     * @param latitude
     * @param longitude
     * @return
     */
    public List<Map<String,Object>> listDistanceProduct(String name,Double latitude, Double longitude){
        return productMapper.listDistanceProduct(name,latitude,longitude,null,"distance","asc");
    }

    /**
     * 根据行业属性获取距离最近的产品
     * @param categoryId
     * @param latitude
     * @param longitude
     * @return
     */
    public List<Map<String,Object>> listDistanceProductByCategory(Integer categoryId,Double latitude, Double longitude){
        return productMapper.listDistanceProduct(null,latitude,longitude,categoryId,"distance","asc");
    }
    /**
     * 根据店铺id获取产品列表
     * @param shopId
     * @return
     */
    @Override
    public List<Product> listPrductByShopId(Integer shopId){
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", false);
        if (shopId != null){
            criteria.andEqualTo("shopId", shopId);
        }
        return productMapper.selectByExample(example);
    }
}
