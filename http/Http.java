package com.movieboxpro.android.http;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.converter.FastJsonConverterFactory;
import com.movieboxpro.android.http.converter.ToStringConverterFactory;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SSLUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
/* loaded from: classes.dex */
public class Http {
    public static final String API_REQUEST = "API_REQUEST";
    private static final String TAG = "Http";
    private static OkHttpClient client;
    private static OkHttpClient client2;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static APIService service;
    private static APIService2 service2;

    public static APIService2 getService2() {
        if (client2 == null) {
            client2 = new OkHttpClient.Builder().build();
        }
        if (service2 == null) {
            service2 = (APIService2) new Retrofit.Builder().client(client).baseUrl(API.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(FastJsonConverterFactory.create()).build().create(APIService2.class);
        }
        return service2;
    }

    public static APIService getService() {
        if (client == null) {
            initClient();
        }
        if (service == null) {
            createService(API.BASE_URL);
        }
        return service;
    }

    public static OkHttpClient getOkHttpClient() {
        if (client == null) {
            initClient();
        }
        return client;
    }

    public static void baseUrlChanged() {
        createService(API.BASE_URL);
    }

    private static void createService(String str) {
        if (client != null) {
            service = (APIService) new Retrofit.Builder().client(client).baseUrl(str).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(FastJsonConverterFactory.create()).build().create(APIService.class);
        }
    }

    private static void initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        SSLUtil sSLUtil = new SSLUtil();
        sSLUtil.addCertificate();
        builder.sslSocketFactory(sSLUtil.getSSLSocketFactory(), sSLUtil.getMX509TrustManager());
        builder.dns(new HttpDns());
        setCookieJar(builder);
        setHeaderInterceptor(builder);
        setParamsInterceptor(builder);
        setCacheDirectory(builder);
        setTimeout(builder);
        builder.addInterceptor(new DynamicTimeoutInterceptor());
        client = builder.build();
    }

    private static void setLoggingInterceptor(OkHttpClient.Builder builder) {
        if (builder != null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.addInterceptor(httpLoggingInterceptor);
        }
    }

    private static void setTimeout(OkHttpClient.Builder builder) {
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.readTimeout(30L, TimeUnit.SECONDS);
        builder.writeTimeout(30L, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        builder.followRedirects(true);
        builder.followSslRedirects(true);
    }

    private static void setCacheDirectory(OkHttpClient.Builder builder) {
        builder.cache(new Cache(new File(App.getContext().getCacheDir().getPath(), "responses"), 10485760L));
    }

    private static void setCookieJar(OkHttpClient.Builder builder) {
        builder.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext())));
    }

    private static void setParamsInterceptor(OkHttpClient.Builder builder) {
        if (builder != null) {
            builder.addInterceptor(new Interceptor() { // from class: com.movieboxpro.android.http.Http.1
                @Override // okhttp3.Interceptor
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    MediaType contentType;
                    Request request = chain.request();
                    Headers headers = request.headers();
                    String method = request.method();
                    RequestBody body = request.body();
                    String str = null;
                    String mediaType = (body == null || (contentType = body.contentType()) == null) ? null : contentType.toString();
                    if ((method.equalsIgnoreCase("POST") && API.BASE_URL.equalsIgnoreCase(request.url().url().toString())) || API.VIDEO_THUMB_URL.equalsIgnoreCase(request.url().url().toString())) {
                        if ("application/x-www-form-urlencoded".equals(mediaType)) {
                            str = Http.addCommonParams(body);
                        } else if ("text/plain; charset=utf-8".equalsIgnoreCase(mediaType)) {
                            str = Http.processText(body);
                        }
                        LogUtils.INSTANCE.logInterfaceParamJson(str);
                        String encodeBody = HttpUtils.encodeBody(str, CipherKeys.getCiperKeys());
                        LogUtils.INSTANCE.logD(Http.API_REQUEST, str);
                        LogUtils logUtils = LogUtils.INSTANCE;
                        logUtils.logD(Http.API_REQUEST, request.url().url().toString() + "?appid=27&data=" + encodeBody + "&DEBUG=1");
                        if (encodeBody != null) {
                            body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "data=" + encodeBody + "&appid=27&platform=android&version=" + App.versionCode + "&medium=" + App.channel + "&token" + App.deviceToken);
                        }
                    }
                    return chain.proceed(request.newBuilder().headers(headers).method(method, body).url(request.url()).build());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String processText(RequestBody requestBody) throws IOException {
        JSONObject parseObject;
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        String decode = URLDecoder.decode(buffer.readUtf8(), "utf-8");
        if (TextUtils.isEmpty(decode)) {
            parseObject = new JSONObject();
        } else {
            parseObject = JSONObject.parseObject(decode);
        }
        parseObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        parseObject.put("appid", (Object) App.packageName);
        parseObject.put("app_version", (Object) App.versionName);
        parseObject.put("medium ", (Object) App.channel);
        parseObject.put("token", (Object) App.deviceToken);
        parseObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        parseObject.put("platform", (Object) "android");
        if (App.channel != null) {
            parseObject.put("channel", (Object) App.channel);
        }
        if (Build.MODEL != null) {
            parseObject.put("device_model", (Object) Build.MODEL);
        }
        if (Build.BRAND != null) {
            parseObject.put("device_name", (Object) Build.BRAND);
        }
        if (App.getUserData().uid_v2 != null) {
            parseObject.put("uid", (Object) App.getUserData().uid_v2);
        }
        UserModel.UserData userData = App.getUserData();
        if (userData != null) {
            if (userData.getMaster_uid() == null) {
                parseObject.put("master_uid", (Object) "");
            } else {
                parseObject.put("master_uid", (Object) userData.getMaster_uid());
            }
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            parseObject.put("childmode", (Object) IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            parseObject.put("childmode", (Object) "0");
        }
        return parseObject.toJSONString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String addCommonParams(RequestBody requestBody) {
        if (requestBody == null || !(requestBody instanceof FormBody)) {
            return "";
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("expired_date", String.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        builder.add("platform", "android");
        builder.add("app_version", BuildConfig.VERSION_NAME);
        builder.add("open_udid", SystemUtils.getUniqueId(App.getContext()));
        builder.add("channel", App.channel);
        if (Build.MODEL != null) {
            builder.add("device_model", Build.MODEL);
        }
        if (Build.BRAND != null) {
            builder.add("device_name", Build.BRAND);
        }
        if (App.getUserData().uid_v2 != null) {
            builder.add("uid", App.getUserData().uid_v2);
        }
        UserModel.UserData userData = App.getUserData();
        if (userData != null) {
            if (userData.getMaster_uid() == null) {
                builder.add("master_uid", "");
            } else {
                builder.add("master_uid", userData.getMaster_uid());
            }
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            builder.add("childmode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            builder.add("childmode", "0");
        }
        builder.add("lang", App.deviceLang);
        builder.add("appid", App.packageName);
        FormBody formBody = (FormBody) requestBody;
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            builder.add(formBody.name(i), formBody.value(i));
        }
        JSONObject jSONObject = new JSONObject();
        FormBody build = builder.build();
        int size2 = build.size();
        for (int i2 = 0; i2 < size2; i2++) {
            jSONObject.put(build.name(i2), (Object) build.value(i2));
        }
        return jSONObject.toJSONString();
    }

    private static String processBody(RequestBody requestBody) throws IOException {
        String str;
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        String str2 = (((URLDecoder.decode(buffer.readUtf8(), "utf-8") + "&expired_date=" + ((TimeUtils.getCurrentTime() / 1000) + 43200)) + "&platform=android") + "&app_version=" + App.versionName) + "&channel=" + App.channel;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            str = str2 + "&childmode=1";
        } else {
            str = str2 + "&childmode=0";
        }
        MLog.d(TAG, "intercept body:" + str);
        return HttpUtils.formToJson(str);
    }

    private static void setHeaderInterceptor(OkHttpClient.Builder builder) {
        if (builder != null) {
            builder.addInterceptor(new Interceptor() { // from class: com.movieboxpro.android.http.Http.2
                @Override // okhttp3.Interceptor
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request request = chain.request();
                    return chain.proceed(request.newBuilder().header("Platform", "android").header("Accept", "text/plain; charset=utf-8").method(request.method(), request.body()).build());
                }
            });
        }
    }

    public static void get(String str, Map<String, String> map, final RequestCallback<String> requestCallback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            if (requestCallback != null) {
                mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.4
                    @Override // java.lang.Runnable
                    public void run() {
                        RequestCallback.this.onFailure(-2, "url错误");
                    }
                });
                return;
            }
            return;
        }
        HttpUrl.Builder newBuilder = parse.newBuilder();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                newBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        okHttpClient.newCall(new Request.Builder().url(newBuilder.build()).cacheControl(CacheControl.FORCE_NETWORK).get().build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (RequestCallback.this != null) {
                    final String localizedMessage = iOException.getLocalizedMessage();
                    Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RequestCallback.this.onFailure(-1, localizedMessage);
                        }
                    });
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        final String string = body.string();
                        if (RequestCallback.this != null) {
                            Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    RequestCallback.this.onSuccess(string);
                                }
                            });
                            return;
                        }
                        return;
                    } else if (RequestCallback.this != null) {
                        Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                RequestCallback.this.onFailure(-1, "无响应数据");
                            }
                        });
                        return;
                    } else {
                        return;
                    }
                }
                final int code = response.code();
                final String message = response.message();
                if (RequestCallback.this != null) {
                    Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            RequestCallback.this.onFailure(code, message);
                        }
                    });
                }
            }
        });
    }

    public static void post(String str, Map<String, String> map, final RequestCallback<String> requestCallback) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient build = builder.build();
        FormBody.Builder builder2 = new FormBody.Builder();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder2.add(entry.getKey(), entry.getValue());
            }
        }
        build.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(str).post(builder2.build()).build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (RequestCallback.this != null) {
                    final String localizedMessage = iOException.getLocalizedMessage();
                    Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RequestCallback.this.onFailure(-1, localizedMessage);
                        }
                    });
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        final String string = body.string();
                        if (RequestCallback.this != null) {
                            Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.5.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    RequestCallback.this.onSuccess(string);
                                }
                            });
                        }
                    } else if (RequestCallback.this != null) {
                        Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.5.3
                            @Override // java.lang.Runnable
                            public void run() {
                                RequestCallback.this.onFailure(-1, "无响应数据");
                            }
                        });
                    }
                } else if (RequestCallback.this != null) {
                    final int code = response.code();
                    final String message = response.message();
                    if (Http.mHandler != null) {
                        Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.5.4
                            @Override // java.lang.Runnable
                            public void run() {
                                RequestCallback.this.onFailure(code, message);
                            }
                        });
                    }
                }
            }
        });
    }

    public static void uploadAsync(String str, Map<String, String> map, Map<String, String> map2, final RequestCallback<String> requestCallback) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient build = builder.build();
        MultipartBody.Builder builder2 = new MultipartBody.Builder();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder2.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (map2 == null || map2.isEmpty()) {
            if (requestCallback == null || (handler = mHandler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.8
                @Override // java.lang.Runnable
                public void run() {
                    RequestCallback.this.onFailure(-1, "文件路径未指定");
                }
            });
            return;
        }
        String str2 = map2.containsKey("filePath") ? map2.get("filePath") : null;
        if (str2 == null) {
            if (requestCallback == null || (handler2 = mHandler) == null) {
                return;
            }
            handler2.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.7
                @Override // java.lang.Runnable
                public void run() {
                    RequestCallback.this.onFailure(-1, "文件路径未指定");
                }
            });
            return;
        }
        String str3 = map2.containsKey("mimeType") ? map2.get("mimeType") : "image/*";
        File file = new File(str2);
        if (file.exists() && file.isFile()) {
            builder2.addFormDataPart("Filedata", file.getName(), MultipartBody.create(MediaType.parse(str3), file));
            build.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(str).post(builder2.build()).build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.9
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    if (RequestCallback.this != null) {
                        final String localizedMessage = iOException.getLocalizedMessage();
                        Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                RequestCallback.this.onFailure(-1, localizedMessage);
                            }
                        });
                    }
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            final String string = body.string();
                            if (RequestCallback.this != null) {
                                Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.9.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        RequestCallback.this.onSuccess(string);
                                    }
                                });
                            }
                        } else if (RequestCallback.this != null) {
                            Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.9.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    RequestCallback.this.onFailure(-1, "无响应数据");
                                }
                            });
                        }
                    } else if (RequestCallback.this != null) {
                        final int code = response.code();
                        final String message = response.message();
                        if (Http.mHandler != null) {
                            Http.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.9.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    RequestCallback.this.onFailure(code, message);
                                }
                            });
                        }
                    }
                }
            });
        } else if (requestCallback != null && (handler3 = mHandler) != null) {
            handler3.post(new Runnable() { // from class: com.movieboxpro.android.http.Http.6
                @Override // java.lang.Runnable
                public void run() {
                    RequestCallback.this.onFailure(-1, "文件不存在");
                }
            });
        }
    }

    public static Call uploadSync(String str, Map<String, String> map, Map<String, String> map2) {
        boolean z = App.isDebug;
        OkHttpClient okHttpClient = getOkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse("multipart/form-data"));
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null && !map2.isEmpty()) {
            String str2 = map2.containsKey("filePath") ? map2.get("filePath") : null;
            if (str2 != null) {
                String str3 = map2.containsKey("mimeType") ? map2.get("mimeType") : "image/*";
                File file = new File(str2);
                if (file.exists() && file.isFile()) {
                    builder.addFormDataPart("Filedata", file.getName(), MultipartBody.create(MediaType.parse(str3), file));
                    return okHttpClient.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(str).post(builder.build()).build());
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DynamicTimeoutInterceptor implements Interceptor {
        DynamicTimeoutInterceptor() {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (request.url().toString().contains("api/srttrans/index/")) {
                return chain.withConnectTimeout(60, TimeUnit.SECONDS).withReadTimeout(60, TimeUnit.SECONDS).withWriteTimeout(60, TimeUnit.SECONDS).proceed(request);
            }
            return chain.withConnectTimeout(30, TimeUnit.SECONDS).withReadTimeout(30, TimeUnit.SECONDS).withWriteTimeout(30, TimeUnit.SECONDS).proceed(request);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class HttpDns implements Dns {
        HttpDns() {
        }

        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (TextUtils.isEmpty(API.REAL_MOVIE_HOST)) {
                return Dns.SYSTEM.lookup(str);
            }
            if (API.MOVIE_HOST.contains(str)) {
                return Dns.SYSTEM.lookup(API.REAL_MOVIE_HOST);
            }
            return Dns.SYSTEM.lookup(str);
        }
    }
}
