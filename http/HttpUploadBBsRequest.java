package com.movieboxpro.android.http;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: HttpUploadBBsRequest.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005¢\u0006\u0002\u0010\u0006J,\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u0007J\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0001J*\u0010\u0014\u001a\u00020\u00002\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\tJ\b\u0010\u0018\u001a\u00020\u0004H\u0002J\u001a\u0010\u0019\u001a\u00020\u00002\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001bH\u0007J\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001dJ\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001dR*\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/http/HttpUploadBBsRequest;", "", "data", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "formName", "mLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "mediaType", "uploadFile", "Ljava/io/File;", "addBaseParams", "module", IjkMediaMeta.IJKM_KEY_TYPE, IDataSource.SCHEME_FILE_TAG, "addParam", "key", "value", "addParams", "param", "bindLifecycle", "lifecycleOwner", "buildData", "executeUpload", "callback", "Lcom/movieboxpro/android/http/ResponseCallback;", "executeUploadObservable", "Lio/reactivex/Observable;", "executeUploadObservable2", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HttpUploadBBsRequest {
    private HashMap<String, Object> data;
    private String formName;
    private LifecycleOwner mLifecycleOwner;
    private String mediaType;
    private File uploadFile;

    public HttpUploadBBsRequest() {
        this(null, 1, null);
    }

    public final HttpUploadBBsRequest addBaseParams(String module, File file) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(file, "file");
        return addBaseParams$default(this, module, null, file, null, 10, null);
    }

    public final HttpUploadBBsRequest addBaseParams(String module, String type, File file) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(file, "file");
        return addBaseParams$default(this, module, type, file, null, 8, null);
    }

    public final HttpUploadBBsRequest executeUpload() {
        return executeUpload$default(this, null, 1, null);
    }

    public HttpUploadBBsRequest(HashMap<String, Object> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.mediaType = "";
        this.formName = "";
    }

    public /* synthetic */ HttpUploadBBsRequest(HashMap hashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HashMap() : hashMap);
    }

    public final HttpUploadBBsRequest addParams(HashMap<String, Object> param) {
        Intrinsics.checkNotNullParameter(param, "param");
        this.data = param;
        return this;
    }

    public final HttpUploadBBsRequest addParam(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.data.put(key, value);
        return this;
    }

    public static /* synthetic */ HttpUploadBBsRequest addBaseParams$default(HttpUploadBBsRequest httpUploadBBsRequest, String str, String str2, File file, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "text/plain";
        }
        if ((i & 8) != 0) {
            str3 = IDataSource.SCHEME_FILE_TAG;
        }
        return httpUploadBBsRequest.addBaseParams(str, str2, file, str3);
    }

    public final HttpUploadBBsRequest addBaseParams(String module, String type, File file, String formName) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(formName, "formName");
        this.data.put("module", module);
        this.mediaType = type;
        this.uploadFile = file;
        this.formName = formName;
        return this;
    }

    public final HttpUploadBBsRequest bindLifecycle(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.mLifecycleOwner = lifecycleOwner;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpUploadBBsRequest executeUpload$default(HttpUploadBBsRequest httpUploadBBsRequest, ResponseCallback responseCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            responseCallback = null;
        }
        return httpUploadBBsRequest.executeUpload(responseCallback);
    }

    public final HttpUploadBBsRequest executeUpload(final ResponseCallback<String> responseCallback) {
        if (this.mLifecycleOwner == null) {
            throw new RuntimeException("not bind LifecycleOwner");
        }
        MultipartBody.Part part = null;
        MediaType parse = MediaType.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody create = RequestBody.create(parse, file);
        try {
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            part = MultipartBody.Part.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), create);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ((ObservableSubscribeProxy) Http.getService().Upload_Request(API.BBS_URL, MultipartBody.Part.createFormData("data", buildData()), MultipartBody.Part.createFormData("appid", "27"), part).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.http.HttpUploadBBsRequest$executeUpload$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                ResponseCallback<String> responseCallback2 = responseCallback;
                if (responseCallback2 == null) {
                    return;
                }
                responseCallback2.onStart();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                super.onNext((HttpUploadBBsRequest$executeUpload$1) s);
                ResponseCallback<String> responseCallback2 = responseCallback;
                if (responseCallback2 == null) {
                    return;
                }
                responseCallback2.onSuccess(s);
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e2) {
                Intrinsics.checkNotNullParameter(e2, "e");
                ResponseCallback<String> responseCallback2 = responseCallback;
                if (responseCallback2 == null) {
                    return;
                }
                responseCallback2.onFailure(e2.getCode(), e2.getMessage());
            }
        });
        return this;
    }

    public final Observable<String> executeUploadObservable() {
        MultipartBody.Part part;
        if (this.mLifecycleOwner == null) {
            throw new RuntimeException("not bind LifecycleOwner");
        }
        MediaType parse = MediaType.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody create = RequestBody.create(parse, file);
        try {
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            part = MultipartBody.Part.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), create);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            part = null;
        }
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("data", buildData());
        MultipartBody.Part createFormData2 = MultipartBody.Part.createFormData("appid", "27");
        if (part != null) {
            return Http.getService().Upload_Request(API.BBS_URL, createFormData, createFormData2, part).compose(RxUtils.rxTranslate2Bean(String.class));
        }
        return null;
    }

    public final Observable<String> executeUploadObservable2() {
        MultipartBody.Part part;
        MediaType parse = MediaType.parse(this.mediaType);
        File file = this.uploadFile;
        Intrinsics.checkNotNull(file);
        RequestBody create = RequestBody.create(parse, file);
        try {
            String str = this.formName;
            File file2 = this.uploadFile;
            Intrinsics.checkNotNull(file2);
            part = MultipartBody.Part.createFormData(str, URLEncoder.encode(file2.getName(), "UTF-8"), create);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            part = null;
        }
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("data", buildData());
        MultipartBody.Part createFormData2 = MultipartBody.Part.createFormData("appid", "27");
        if (part != null) {
            return Http.getService().Upload_Request(API.BBS_URL, createFormData, createFormData2, part);
        }
        return null;
    }

    private final String buildData() {
        String versionName = App.versionName;
        Intrinsics.checkNotNullExpressionValue(versionName, "versionName");
        this.data.put("app_version", versionName);
        String bbs_uid = App.getBBsInfo().getBbs_uid();
        Intrinsics.checkNotNullExpressionValue(bbs_uid, "getBBsInfo().bbs_uid");
        this.data.put("uid", bbs_uid);
        String deviceToken = App.deviceToken;
        Intrinsics.checkNotNullExpressionValue(deviceToken, "deviceToken");
        this.data.put("token", deviceToken);
        this.data.put("platform", "android");
        String deviceLang = App.deviceLang;
        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
        this.data.put("lang", deviceLang);
        String channel = App.channel;
        Intrinsics.checkNotNullExpressionValue(channel, "channel");
        this.data.put("medium", channel);
        this.data.put("expired_date", Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        String encodeBody = HttpUtils.encodeBody(JSONObject.toJSONString(this.data));
        Intrinsics.checkNotNullExpressionValue(encodeBody, "encodeBody(JSONObject.toJSONString(data))");
        return encodeBody;
    }
}
