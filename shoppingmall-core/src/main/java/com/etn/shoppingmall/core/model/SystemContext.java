package com.etn.shoppingmall.core.model;

/**
 * 用来传递列表对象的ThreadLocal数据
 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * Created by Administration on 2016/6/26.
 */
public class SystemContext {
    /**
     * 分页大小
     */
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
    /**
     * 分页的起始页
     */
    private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
    /**
     * 列表的排序字段
     */
    private static ThreadLocal<String> sort = new ThreadLocal<String>();
    /**
     * 列表的排序方式
     */
    private static ThreadLocal<String> order = new ThreadLocal<String>();

    /**
     * 获取项目路径
     */
    private static ThreadLocal<String> realPath = new ThreadLocal<String>();

    public static Integer getPageSize() {
        return pageSize.get();
    }

    public static void setPageSize(Integer _pageSize) {
        SystemContext.pageSize.set(_pageSize);
    }

    public static Integer getPageOffset() {
        return pageOffset.get();
    }

    public static void setPageOffset(Integer _pageOffset) {
        SystemContext.pageOffset.set(_pageOffset);
    }

    public static String getSort() {
        return sort.get();
    }

    public static void setSort(String _sort) {
        SystemContext.sort.set(_sort);
    }

    public static String getOrder() {
        return order.get();
    }

    public static void setOrder(String _order) {
        SystemContext.order.set(_order);
    }

    public static String getRealPath() {
        return realPath.get();
    }

    public static void setRealPath(String _realPath) {
        SystemContext.realPath.set(_realPath);
    }

    public static void removePageSize() {
        pageSize.remove();
    }

    public static void removePageOffset() {
        pageOffset.remove();
    }

    public static void removeSort() {
        sort.remove();
    }

    public static void removeOrder() {
        order.remove();
    }

    public static void removeRealPath() {
        realPath.remove();
    }

}
