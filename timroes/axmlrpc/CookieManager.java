package com.movieboxpro.android.timroes.axmlrpc;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CookieManager {
    private static final String COOKIE = "Cookie";
    private static final String SET_COOKIE = "Set-Cookie";
    private Map<String, String> cookies = new ConcurrentHashMap();
    private int flags;

    public CookieManager(int i) {
        this.flags = i;
    }

    public void clearCookies() {
        this.cookies.clear();
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public void readCookies(HttpURLConnection httpURLConnection) {
        if ((this.flags & 4) == 0) {
            return;
        }
        for (int i = 0; i < httpURLConnection.getHeaderFields().size(); i++) {
            String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
            if (headerFieldKey != null && "Set-Cookie".equalsIgnoreCase(headerFieldKey.toLowerCase())) {
                String[] split = httpURLConnection.getHeaderField(i).split(";")[0].split("=");
                if (split.length >= 2) {
                    this.cookies.put(split[0], split[1]);
                }
            }
        }
    }

    public void setCookies(HttpURLConnection httpURLConnection) {
        if ((this.flags & 4) == 0) {
            return;
        }
        String str = "";
        for (Map.Entry<String, String> entry : this.cookies.entrySet()) {
            str = str + entry.getKey() + "=" + entry.getValue() + "; ";
        }
        httpURLConnection.setRequestProperty("Cookie", str);
    }
}
