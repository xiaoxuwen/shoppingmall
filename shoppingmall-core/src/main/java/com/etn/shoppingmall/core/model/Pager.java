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
    private int code; //状态码, 0表示成功
    private String msg;  //提示信息
    private long count; // 总数量
    private List<T> data; // 当前数据

    public Pager(long count, List<T> data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }

    public Pager() {
        this.code = -1;
        this.msg = "error";
        this.count = 0;
        this.data = null;
    }

    public static int offset(int pageIndex, int pageSize) {
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
