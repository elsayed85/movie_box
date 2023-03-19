package com.movieboxpro.android.http;

import java.util.List;
/* loaded from: classes3.dex */
public class BaseResponse<T> {
    public int code;
    public T data;
    public List<T> list;
    public String msg;
    public int success = 0;
}
