package com.movieboxpro.android.http;

import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: HttpRequest.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(BM\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u0004\b\u0000\u0010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0015J&\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u00170\u0012\"\u0004\b\u0000\u0010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0015J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012J\u0006\u0010\u0019\u001a\u00020\u0010J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bJ\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bH\u0002J;\u0010\u001d\u001a\u00020\u00002.\u0010\u001e\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010 0\u001f\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010 ¢\u0006\u0002\u0010!J\u0018\u0010\u001d\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u001d\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0004J.\u0010$\u001a\u00020\u00002&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040'R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR.\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/http/HttpRequest;", "", "paramMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "module", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "url", "(Ljava/util/HashMap;Ljava/lang/String;Landroidx/lifecycle/LifecycleOwner;Ljava/lang/String;)V", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getModule", "()Ljava/lang/String;", "addCommonParams", "", "asBean", "Lcom/uber/autodispose/ObservableSubscribeProxy;", ExifInterface.GPS_DIRECTION_TRUE, IjkMediaMeta.IJKM_KEY_TYPE, "Ljava/lang/Class;", "asList", "", "asMsg", "asMsgSubcribe", "asRequest", "Lio/reactivex/Observable;", "getRequest", "param", "pair", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lcom/movieboxpro/android/http/HttpRequest;", "key", "value", "params", "map", "request", "Lretrofit2/Call;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HttpRequest {
    public static final Companion Companion = new Companion(null);
    private final LifecycleOwner lifecycleOwner;
    private final String module;
    private HashMap<String, Object> paramMap;
    private final String url;

    @JvmStatic
    public static final HttpRequest post(LifecycleOwner lifecycleOwner, String str) {
        return Companion.post(lifecycleOwner, str);
    }

    @JvmStatic
    public static final HttpRequest post(String str) {
        return Companion.post(str);
    }

    @JvmStatic
    public static final HttpRequest post(String str, String str2) {
        return Companion.post(str, str2);
    }

    @JvmStatic
    public static final HttpRequest post(HashMap<String, Object> hashMap, LifecycleOwner lifecycleOwner) {
        return Companion.post(hashMap, lifecycleOwner);
    }

    public HttpRequest(HashMap<String, Object> paramMap, String module, LifecycleOwner lifecycleOwner, String url) {
        Intrinsics.checkNotNullParameter(paramMap, "paramMap");
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(url, "url");
        this.paramMap = paramMap;
        this.module = module;
        this.lifecycleOwner = lifecycleOwner;
        this.url = url;
        addCommonParams();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ HttpRequest(java.util.HashMap r1, java.lang.String r2, androidx.lifecycle.LifecycleOwner r3, java.lang.String r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r0 = this;
            r6 = r5 & 1
            if (r6 == 0) goto L9
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L9:
            r6 = r5 & 4
            if (r6 == 0) goto Le
            r3 = 0
        Le:
            r5 = r5 & 8
            if (r5 == 0) goto L19
            java.lang.String r4 = com.movieboxpro.android.http.API.BASE_URL
            java.lang.String r5 = "BASE_URL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
        L19:
            r0.<init>(r1, r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.http.HttpRequest.<init>(java.util.HashMap, java.lang.String, androidx.lifecycle.LifecycleOwner, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final String getModule() {
        return this.module;
    }

    /* compiled from: HttpRequest.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J<\u0010\u0003\u001a\u00020\u00042&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/http/HttpRequest$Companion;", "", "()V", "post", "Lcom/movieboxpro/android/http/HttpRequest;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "module", "", "url", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final HttpRequest post(LifecycleOwner lifecycleOwner, String module) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(module, "module");
            return new HttpRequest(null, module, lifecycleOwner, null, 9, null);
        }

        @JvmStatic
        public final HttpRequest post(String module) {
            Intrinsics.checkNotNullParameter(module, "module");
            return new HttpRequest(null, module, null, null, 9, null);
        }

        @JvmStatic
        public final HttpRequest post(String module, String url) {
            Intrinsics.checkNotNullParameter(module, "module");
            Intrinsics.checkNotNullParameter(url, "url");
            return new HttpRequest(null, module, null, url, 1, null);
        }

        public static /* synthetic */ HttpRequest post$default(Companion companion, HashMap hashMap, LifecycleOwner lifecycleOwner, int i, Object obj) {
            if ((i & 2) != 0) {
                lifecycleOwner = null;
            }
            return companion.post(hashMap, lifecycleOwner);
        }

        @JvmStatic
        public final HttpRequest post(HashMap<String, Object> params, LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(params, "params");
            return new HttpRequest(params, "", lifecycleOwner, null, 8, null);
        }
    }

    public final HttpRequest param(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.paramMap.put(key, str);
        return this;
    }

    public final HttpRequest param(String key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.paramMap.put(key, obj);
        return this;
    }

    public final HttpRequest params(HashMap<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.paramMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public final <T> ObservableSubscribeProxy<T> asBean(Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Object as = getRequest().compose(RxUtils.rxTranslate2Bean(type)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "getRequest().compose(RxU…cleOwner(lifecycleOwner))");
        return (ObservableSubscribeProxy) as;
    }

    public final <T> ObservableSubscribeProxy<List<T>> asList(Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Object as = getRequest().compose(RxUtils.rxTranslate2List(type)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "getRequest().compose(RxU…cleOwner(lifecycleOwner))");
        return (ObservableSubscribeProxy) as;
    }

    public final ObservableSubscribeProxy<String> asMsg() {
        Object as = getRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "getRequest().compose(RxU…cleOwner(lifecycleOwner))");
        return (ObservableSubscribeProxy) as;
    }

    public final void asMsgSubcribe() {
        ((ObservableSubscribeProxy) getRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.lifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.http.HttpRequest$asMsgSubcribe$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
            }
        });
    }

    private final Observable<String> getRequest() {
        addCommonParams();
        return Http.getService().request(this.url, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(this.paramMap)));
    }

    public final Observable<String> asRequest() {
        addCommonParams();
        return Http.getService().request(this.url, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(this.paramMap)));
    }

    public final Call<String> request() {
        addCommonParams();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(this.paramMap));
        APIService service = Http.getService();
        String str = this.url;
        Intrinsics.checkNotNullExpressionValue(requestBody, "requestBody");
        return service.callRequest(str, requestBody);
    }

    private final void addCommonParams() {
        this.paramMap.put("expired_date", String.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        this.paramMap.put("platform", "android");
        this.paramMap.put("app_version", BuildConfig.VERSION_NAME);
        this.paramMap.put("channel", App.channel);
        this.paramMap.put("device_model", Build.MODEL);
        this.paramMap.put("device_name", Build.BRAND);
        this.paramMap.put("open_udid", SystemUtils.getUniqueId(App.getContext()));
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            this.paramMap.put("childmode", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            this.paramMap.put("childmode", "0");
        }
        this.paramMap.put("lang", App.deviceLang);
        this.paramMap.put("appid", App.packageName);
        if (!StringsKt.isBlank(this.module)) {
            this.paramMap.put("module", this.module);
        }
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        HashMap<String, Object> hashMap = this.paramMap;
        String master_uid = userData.getMaster_uid();
        if (master_uid == null) {
            master_uid = "";
        }
        hashMap.put("master_uid", master_uid);
        HashMap<String, Object> hashMap2 = this.paramMap;
        String str = userData.uid_v2;
        hashMap2.put("uid", str != null ? str : "");
    }

    public final HttpRequest param(Pair<String, ? extends Object>... pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        int length = pair.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair2 = pair[i];
            i++;
            this.paramMap.put(pair2.getFirst(), pair2.getSecond());
        }
        return this;
    }
}
