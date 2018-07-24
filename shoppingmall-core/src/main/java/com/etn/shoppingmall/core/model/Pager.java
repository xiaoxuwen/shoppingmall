package com.etn.shoppingmall.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 * @author Administrator
 */
@Data
public class Pager<T> implements Serializable {
    /**
     * 分页的起始页
     */
    private int offset;
    /**
     * 分页的大小
     */
    private int size;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 分页的数据
     */
    private List<T> datas;

    public Pager(int size, int offset, long total, List<T> datas) {
        this.size = size;
        this.offset = offset;
        this.total = total;
        this.datas = datas;
    }
}
