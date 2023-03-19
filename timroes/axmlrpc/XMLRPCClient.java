package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes3.dex */
public class XMLRPCClient {
    static final String CONTENT_LENGTH = "Content-Length";
    static final String CONTENT_TYPE = "Content-Type";
    private static final String DEFAULT_USER_AGENT = "aXMLRPC";
    static final String FAULT = "fault";
    public static final int FLAGS_8BYTE_INT = 2;
    public static final int FLAGS_ACCEPT_NULL_DATES = 16384;
    public static final int FLAGS_APACHE_WS = 776;
    public static final int FLAGS_DEBUG = 8192;
    public static final int FLAGS_DEFAULT_TYPE_STRING = 256;
    public static final int FLAGS_ENABLE_COOKIES = 4;
    public static final int FLAGS_FORWARD = 32;
    public static final int FLAGS_IGNORE_NAMESPACES = 512;
    public static final int FLAGS_IGNORE_STATUSCODE = 16;
    public static final int FLAGS_NIL = 8;
    public static final int FLAGS_NONE = 0;
    public static final int FLAGS_NO_STRING_DECODE = 2048;
    public static final int FLAGS_NO_STRING_ENCODE = 4096;
    public static final int FLAGS_SSL_IGNORE_ERRORS = 192;
    public static final int FLAGS_SSL_IGNORE_INVALID_CERT = 128;
    public static final int FLAGS_SSL_IGNORE_INVALID_HOST = 64;
    public static final int FLAGS_STRICT = 1;
    public static final int FLAGS_USE_SYSTEM_PROXY = 1024;
    static final String HOST = "Host";
    static final String HTTP_POST = "POST";
    static final String METHOD_CALL = "methodCall";
    static final String METHOD_NAME = "methodName";
    static final String METHOD_RESPONSE = "methodResponse";
    static final String PARAM = "param";
    static final String PARAMS = "params";
    static final String STRUCT_MEMBER = "member";
    static final String TYPE_XML = "text/xml; charset=utf-8";
    static final String USER_AGENT = "User-Agent";
    public static final String VALUE = "value";
    private AuthenticationManager authManager;
    private Map<Long, Caller> backgroundCalls;
    private CookieManager cookieManager;
    private final int flags;
    private Map<String, String> httpParameters;
    private KeyManager[] keyManagers;
    private Proxy proxy;
    private ResponseParser responseParser;
    private final SerializerHandler serializerHandler;
    private int timeout;
    private TrustManager[] trustManagers;
    private URL url;

    public XMLRPCClient(URL url, String str, int i) {
        this.httpParameters = new ConcurrentHashMap();
        this.backgroundCalls = new ConcurrentHashMap();
        this.serializerHandler = new SerializerHandler(i);
        this.url = url;
        this.flags = i;
        this.responseParser = new ResponseParser();
        this.cookieManager = new CookieManager(i);
        this.authManager = new AuthenticationManager();
        this.httpParameters.put("Content-Type", TYPE_XML);
        this.httpParameters.put("User-Agent", str);
        if (isFlagSet(128)) {
            this.trustManagers = new TrustManager[]{new X509TrustManager() { // from class: com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
        }
        if (isFlagSet(1024)) {
            Properties properties = System.getProperties();
            String property = properties.getProperty("http.proxyHost");
            int parseInt = Integer.parseInt(properties.getProperty("http.proxyPort", "0"));
            if (parseInt <= 0 || property.length() <= 0 || property.equals("null")) {
                return;
            }
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, parseInt));
        }
    }

    public XMLRPCClient(URL url, int i) {
        this(url, DEFAULT_USER_AGENT, i);
    }

    public XMLRPCClient(URL url, String str) {
        this(url, str, 0);
    }

    public XMLRPCClient(URL url) {
        this(url, DEFAULT_USER_AGENT, 0);
    }

    public URL getURL() {
        return this.url;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void setUserAgentString(String str) {
        this.httpParameters.put("User-Agent", str);
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setCustomHttpHeader(String str, String str2) {
        if ("Content-Type".equals(str) || "Host".equals(str) || "Content-Length".equals(str)) {
            throw new XMLRPCRuntimeException("You cannot modify the Host, Content-Type or Content-Length header.");
        }
        this.httpParameters.put(str, str2);
    }

    public void setLoginData(String str, String str2) {
        this.authManager.setAuthData(str, str2);
    }

    public void clearLoginData() {
        this.authManager.clearAuthData();
    }

    public Map<String, String> getCookies() {
        return this.cookieManager.getCookies();
    }

    public void clearCookies() {
        this.cookieManager.clearCookies();
    }

    public void installCustomTrustManager(TrustManager trustManager) {
        if (isFlagSet(128)) {
            return;
        }
        this.trustManagers = new TrustManager[]{trustManager};
    }

    public void installCustomTrustManagers(TrustManager[] trustManagerArr) {
        if (isFlagSet(128)) {
            return;
        }
        this.trustManagers = (TrustManager[]) trustManagerArr.clone();
    }

    public void installCustomKeyManager(KeyManager keyManager) {
        if (isFlagSet(128)) {
            return;
        }
        this.keyManagers = new KeyManager[]{keyManager};
    }

    public void installCustomKeyManagers(KeyManager[] keyManagerArr) {
        if (isFlagSet(128)) {
            return;
        }
        this.keyManagers = (KeyManager[]) keyManagerArr.clone();
    }

    public Object call(String str, Object... objArr) throws XMLRPCException {
        return new Caller().call(str, objArr);
    }

    public long callAsync(XMLRPCCallback xMLRPCCallback, String str, Object... objArr) {
        long currentTimeMillis = System.currentTimeMillis();
        new Caller(xMLRPCCallback, currentTimeMillis, str, objArr).start();
        return currentTimeMillis;
    }

    public void cancel(long j) {
        Caller caller = this.backgroundCalls.get(Long.valueOf(j));
        if (caller == null) {
            return;
        }
        caller.cancel();
        try {
            caller.join();
        } catch (InterruptedException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Call createCall(String str, Object[] objArr) {
        if (isFlagSet(1) && !str.matches("^[A-Za-z0-9\\._:/]*$")) {
            throw new XMLRPCRuntimeException("Method name must only contain A-Z a-z . : _ / ");
        }
        return new Call(this.serializerHandler, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFlagSet(int i) {
        return (i & this.flags) != 0;
    }

    /* loaded from: classes3.dex */
    private class Caller extends Thread {
        private volatile boolean canceled;
        private HttpURLConnection http;
        private XMLRPCCallback listener;
        private String methodName;
        private Object[] params;
        private long threadId;

        public Caller(XMLRPCCallback xMLRPCCallback, long j, String str, Object[] objArr) {
            this.listener = xMLRPCCallback;
            this.threadId = j;
            this.methodName = str;
            this.params = objArr;
        }

        public Caller() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (this.listener == null) {
                    return;
                }
                try {
                    try {
                        XMLRPCClient.this.backgroundCalls.put(Long.valueOf(this.threadId), this);
                        this.listener.onResponse(this.threadId, call(this.methodName, this.params));
                    } catch (XMLRPCServerException e) {
                        this.listener.onServerError(this.threadId, e);
                    }
                } catch (CancelException unused) {
                } catch (XMLRPCException e2) {
                    this.listener.onError(this.threadId, e2);
                }
            } finally {
                XMLRPCClient.this.backgroundCalls.remove(Long.valueOf(this.threadId));
            }
        }

        public void cancel() {
            this.canceled = true;
            HttpURLConnection httpURLConnection = this.http;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x015f  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x019b A[Catch: IOException -> 0x01bf, SocketTimeoutException -> 0x01dc, TryCatch #3 {SocketTimeoutException -> 0x01dc, IOException -> 0x01bf, blocks: (B:2:0x0000, B:4:0x000e, B:6:0x0029, B:8:0x004d, B:9:0x0067, B:10:0x0075, B:12:0x007b, B:13:0x0093, B:22:0x00e1, B:30:0x00ff, B:35:0x010c, B:36:0x0113, B:37:0x0114, B:39:0x011c, B:42:0x012b, B:43:0x0132, B:44:0x0133, B:46:0x0155, B:50:0x0162, B:52:0x016c, B:55:0x017a, B:57:0x0195, B:54:0x0172, B:59:0x019b, B:60:0x01a2, B:23:0x00e8, B:25:0x00f0, B:61:0x01a3, B:62:0x01be, B:16:0x00d0, B:5:0x001f), top: B:78:0x0000 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object call(java.lang.String r9, java.lang.Object[] r10) throws com.movieboxpro.android.timroes.axmlrpc.XMLRPCException {
            /*
                Method dump skipped, instructions count: 486
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient.Caller.call(java.lang.String, java.lang.Object[]):java.lang.Object");
        }

        private HttpURLConnection verifyConnection(URLConnection uRLConnection) throws XMLRPCException {
            if (!(uRLConnection instanceof HttpURLConnection)) {
                throw new IllegalArgumentException("The URL is not valid for a http connection.");
            }
            if (uRLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
                if (XMLRPCClient.this.isFlagSet(64)) {
                    httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient.Caller.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str, SSLSession sSLSession) {
                            return true;
                        }
                    });
                }
                if (XMLRPCClient.this.trustManagers != null) {
                    try {
                        String[] strArr = {"TLS", "SSL"};
                        for (int i = 0; i < 2; i++) {
                            SSLContext sSLContext = SSLContext.getInstance(strArr[i]);
                            sSLContext.init(XMLRPCClient.this.keyManagers, XMLRPCClient.this.trustManagers, new SecureRandom());
                            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                        }
                    } catch (Exception e) {
                        throw new XMLRPCException(e);
                    }
                }
                return httpsURLConnection;
            }
            return (HttpURLConnection) uRLConnection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class CancelException extends RuntimeException {
        private CancelException() {
        }
    }
}
