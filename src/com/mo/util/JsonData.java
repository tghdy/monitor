package com.mo.util;

import net.sf.json.JSONObject;

public class JsonData {
    private Integer flag = 0;
    private String msg;
    private Object data;

    //成功标记
    final public static Integer SUCCESS = 1;
    //失败标记
    final public static Integer FAILED = 0;

    public JsonData(Integer flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public JsonData(Integer flag, String msg) {

        this.flag = flag;
        this.msg = msg;
    }

    public Integer getFlag() {
        return flag;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("flag", flag);
        json.put("msg", msg);
        json.put("data", data);
        return json.toString();
    }
}
