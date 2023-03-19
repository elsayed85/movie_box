package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class BaseResponse<T> {
    private int code;
    private T data;
    private String msg;
    private String result;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
