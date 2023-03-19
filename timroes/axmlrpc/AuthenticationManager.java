package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.base64.Base64;
import java.net.HttpURLConnection;
/* loaded from: classes3.dex */
public class AuthenticationManager {
    private String pass;
    private String user;

    public void clearAuthData() {
        this.user = null;
        this.pass = null;
    }

    public void setAuthData(String str, String str2) {
        this.user = str;
        this.pass = str2;
    }

    public void setAuthentication(HttpURLConnection httpURLConnection) {
        String str = this.user;
        if (str == null || this.pass == null || str.length() <= 0 || this.pass.length() <= 0) {
            return;
        }
        String encode = Base64.encode(this.user + ":" + this.pass);
        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        sb.append(encode);
        httpURLConnection.addRequestProperty("Authorization", sb.toString());
    }
}
