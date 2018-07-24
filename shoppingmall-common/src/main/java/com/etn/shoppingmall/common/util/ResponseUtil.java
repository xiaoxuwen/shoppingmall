package com.etn.shoppingmall.common.util;

public class ResponseUtil {
    private int result;         //0表示失败 1表示成功
    private String msg;         //提示信息
    private Object data;        //附加对象，用来存储一些特定的返回信息

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseUtil() {
        result = 1;
    }

    public ResponseUtil(int result) {
        super();
        this.result = result;
    }

    public ResponseUtil(int result, String msg) {
        super();
        this.result = result;
        this.msg = msg;
    }

    public ResponseUtil(int result, Object data) {
        this.result = result;
        this.data = data;
    }

    public ResponseUtil(int result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseUtil ok() {
        return new ResponseUtil();
    }

    public static ResponseUtil ok(Object data) {
        return new ResponseUtil(1, "成功", data);
    }

    public static ResponseUtil ok(String msg, Object data) {
        return new ResponseUtil(1, msg, data);
    }

    public static ResponseUtil fail() {
        return new ResponseUtil(-1, "错误");
    }

    public static ResponseUtil fail(int result, String msg) {
        return new ResponseUtil(result, msg);
    }


    public static ResponseUtil badArgument() {
        return fail(401, "参数不对");
    }

    public static ResponseUtil badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static ResponseUtil unlogin() {
        return fail(501, "未登录");
    }

    public static ResponseUtil serious() {
        return fail(502, "系统内部错误");
    }

    public static ResponseUtil unsupport() {
        return fail(503, "业务不支持");
    }
}

