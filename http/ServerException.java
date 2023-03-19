package com.movieboxpro.android.http;
/* loaded from: classes3.dex */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;

    public ServerException(int i, String str) {
        super(str);
        this.errCode = i;
        this.message = str;
    }

    public int getErrCode() {
        return this.errCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
