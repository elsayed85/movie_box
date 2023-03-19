package com.movieboxpro.android.http;
/* loaded from: classes.dex */
public abstract class CipherKeys {
    private String appKey;
    private String iv;
    private String key;

    /* JADX INFO: Access modifiers changed from: protected */
    public CipherKeys(String str, String str2, String str3) {
        this.appKey = str;
        this.key = str2;
        this.iv = str3;
    }

    public static CipherKeys getDefaultKeys() {
        return getCiperKeys();
    }

    public static CipherKeys getCiperKeys() {
        return new StoneCipherKeys();
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getKey() {
        return this.key;
    }

    public String getIv() {
        return this.iv;
    }
}
