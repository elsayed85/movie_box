package com.movieboxpro.android.http;

import com.alibaba.fastjson.JSONException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ConnectTimeoutException;
import retrofit2.HttpException;
/* loaded from: classes3.dex */
public class ApiException extends Exception {
    private static final int BADREQUEST = 400;
    private static final int BAD_GATEWAY = 502;
    private static final int FORBIDDEN = 403;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int UNAUTHORIZED = 401;
    private int code;
    private String message;

    /* loaded from: classes3.dex */
    public static class ERROR {
        public static final int CAST_ERROR = 1007;
        public static final int HTTP_ERROR = 1003;
        public static final int INVOKE_ERROR = 1006;
        public static final int NETWORD_ERROR = 1002;
        public static final int NULLPOINTER_EXCEPTION = 1010;
        public static final int PARSE_ERROR = 1001;
        public static final int REQUEST_CANCEL = 1008;
        public static final int SERVER_EXCEPTION = 1011;
        public static final int SSL_ERROR = 1004;
        public static final int TIMEOUT_ERROR = 1005;
        public static final int UNKNOWN = 1000;
        public static final int UNKNOWNHOST_ERROR = 1009;
    }

    public ApiException(Throwable th, int i) {
        super(th);
        this.message = th.getMessage();
        this.code = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public static ApiException handleException(Throwable th) {
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            ApiException apiException = new ApiException(httpException, httpException.code());
            apiException.message = "Network error" + httpException.code();
            return apiException;
        } else if (th instanceof ServerException) {
            ServerException serverException = (ServerException) th;
            ApiException apiException2 = new ApiException(serverException, serverException.getErrCode());
            apiException2.message = serverException.getMessage();
            return apiException2;
        } else if (th instanceof JSONException) {
            ApiException apiException3 = new ApiException(th, 1001);
            apiException3.message = "Parse error";
            return apiException3;
        } else if (th instanceof ClassCastException) {
            ApiException apiException4 = new ApiException(th, 1007);
            apiException4.message = "ClassCastException";
            return apiException4;
        } else if (th instanceof ConnectException) {
            ApiException apiException5 = new ApiException(th, 1002);
            apiException5.message = "Connect failed";
            return apiException5;
        } else if (th instanceof SSLHandshakeException) {
            ApiException apiException6 = new ApiException(th, 1004);
            apiException6.message = "Certificate validation failed";
            return apiException6;
        } else if (th instanceof ConnectTimeoutException) {
            ApiException apiException7 = new ApiException(th, 1005);
            apiException7.message = "Connection timeout";
            return apiException7;
        } else if (th instanceof SocketTimeoutException) {
            ApiException apiException8 = new ApiException(th, 1005);
            apiException8.message = "Connection timeout";
            return apiException8;
        } else if (th instanceof UnknownHostException) {
            ApiException apiException9 = new ApiException(th, 1009);
            apiException9.message = "Unable to resolve this domain name";
            return apiException9;
        } else if (th instanceof NullPointerException) {
            ApiException apiException10 = new ApiException(th, 1010);
            apiException10.message = "NullPointerException";
            return apiException10;
        } else if (th instanceof NoRouteToHostException) {
            ApiException apiException11 = new ApiException(th, 1011);
            apiException11.message = "Server connection error";
            return apiException11;
        } else {
            ApiException apiException12 = new ApiException(th, 1000);
            apiException12.message = "unknown error" + th.getMessage();
            return apiException12;
        }
    }
}
