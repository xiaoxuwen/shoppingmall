package com.etn.shoppingmall.common.util;

public class ResponseUtil {
    private int code;         //0表示失败 1表示成功
    private String msg;         //提示信息
    private Object data;        //附加对象，用来存储一些特定的返回信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        code = 1;
    }

    public ResponseUtil(int code) {
        super();
        this.code = code;
    }

    public ResponseUtil(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseUtil(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseUtil(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseUtil ok() {
        return new ResponseUtil();
    }

    public static ResponseUtil ok(int code, String msg) {
        return new ResponseUtil(code, msg);
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

    public static ResponseUtil fail(int code, String msg) {
        return new ResponseUtil(code, msg);
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

