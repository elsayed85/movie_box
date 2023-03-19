package com.movieboxpro.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.body.UrlEncodedFormBody;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.LoadUploadSubtitleEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: WebUploadService.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u001c\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/service/WebUploadService;", "Landroid/app/Service;", "()V", "fileUploadHolder", "Lcom/movieboxpro/android/service/WebUploadService$FileUploadHolder;", "id", "", "mAsyncServer", "Lcom/koushikdutta/async/AsyncServer;", "saveDir", "server", "Lcom/koushikdutta/async/http/server/AsyncHttpServer;", "getContentTypeByResourceName", "resourceName", "getIndexContent", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "", "onStartCommand", "", "flags", "startId", "sendResources", "request", "Lcom/koushikdutta/async/http/server/AsyncHttpServerRequest;", "response", "Lcom/koushikdutta/async/http/server/AsyncHttpServerResponse;", "startServer", "Companion", "FileUploadHolder", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WebUploadService extends Service {
    private static final String ACTION_START_WEB_SERVICE = "com.snowtop.moviebox.action.START_WEB_SERVICE";
    private static final String ACTION_STOP_WEB_SERVICE = "com.snowtop.moviebox.action.STOP_WEB_SERVICE";
    private static final String BINARY_CONTENT_TYPE = "application/octet-stream";
    private static final String CSS_CONTENT_TYPE = "text/css;charset=utf-8";
    public static final Companion Companion = new Companion(null);
    private static final String EOT_CONTENT_TYPE = "image/vnd.ms-fontobject";
    private static final String JPG_CONTENT_TYPE = "application/jpeg";
    private static final String JS_CONTENT_TYPE = "application/javascript";
    private static final String MP3_CONTENT_TYPE = "audio/mp3";
    private static final String MP4_CONTENT_TYPE = "video/mpeg4";
    private static final String PNG_CONTENT_TYPE = "application/x-png";
    private static final String SVG_CONTENT_TYPE = "image/svg+xml";
    private static final String SWF_CONTENT_TYPE = "application/x-shockwave-flash";
    private static final String TEXT_CONTENT_TYPE = "text/html;charset=utf-8";
    private static final String TTF_CONTENT_TYPE = "application/x-font-truetype";
    private static final String WOFF_CONTENT_TYPE = "application/x-font-woff";
    private final FileUploadHolder fileUploadHolder = new FileUploadHolder();
    private final AsyncHttpServer server = new AsyncHttpServer();
    private final AsyncServer mAsyncServer = new AsyncServer();
    private String id = "";
    private String saveDir = "";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    /* compiled from: WebUploadService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/service/WebUploadService$Companion;", "", "()V", "ACTION_START_WEB_SERVICE", "", "ACTION_STOP_WEB_SERVICE", "BINARY_CONTENT_TYPE", "CSS_CONTENT_TYPE", "EOT_CONTENT_TYPE", "JPG_CONTENT_TYPE", "JS_CONTENT_TYPE", "MP3_CONTENT_TYPE", "MP4_CONTENT_TYPE", "PNG_CONTENT_TYPE", "SVG_CONTENT_TYPE", "SWF_CONTENT_TYPE", "TEXT_CONTENT_TYPE", "TTF_CONTENT_TYPE", "WOFF_CONTENT_TYPE", TtmlNode.START, "", "context", "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "stop", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, Bundle bundle) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intent intent = new Intent(context, WebUploadService.class);
            intent.setAction(WebUploadService.ACTION_START_WEB_SERVICE);
            intent.putExtras(bundle);
            context.startService(intent);
        }

        public final void stop(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, WebUploadService.class);
            intent.setAction(WebUploadService.ACTION_STOP_WEB_SERVICE);
            context.stopService(intent);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str;
        String string;
        String action = intent == null ? null : intent.getAction();
        Bundle extras = intent == null ? null : intent.getExtras();
        String str2 = "";
        if (extras != null && (string = extras.getString("id")) != null) {
            str2 = string;
        }
        this.id = str2;
        Integer valueOf = extras == null ? null : Integer.valueOf(extras.getInt("season"));
        Integer valueOf2 = extras == null ? null : Integer.valueOf(extras.getInt("episode"));
        String string2 = extras == null ? null : extras.getString("name");
        if ((valueOf == null || valueOf.intValue() != 0) && (valueOf2 == null || valueOf2.intValue() != 0)) {
            str = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + this.id + ((Object) File.separator) + ((Object) string2) + ((Object) File.separator) + "Season " + valueOf + ((Object) File.separator) + "Episode " + valueOf2 + ((Object) File.separator) + ((Object) App.deviceLang);
        } else {
            str = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + this.id + ((Object) File.separator) + ((Object) string2) + ((Object) File.separator) + ((Object) App.deviceLang);
        }
        this.saveDir = str;
        if (StringsKt.equals$default(action, ACTION_START_WEB_SERVICE, false, 2, null)) {
            startServer();
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.server.stop();
        this.mAsyncServer.stop();
    }

    private final void startServer() {
        this.server.get("/images/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$JXvygMbv3fWM8Zxkt1-2SuBru0E
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m78startServer$lambda0(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/scripts/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$nfET0S1v1Ijv24t5ljA03piTS3c
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m79startServer$lambda1(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/css/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$nvfcRtTROYxksVrmt78nUZAjMbc
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m86startServer$lambda2(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$CKkFottdqULppqMontFNPvgWv3s
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m87startServer$lambda3(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/files", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$AmB0e_i8ol4c2LEbmCRsgXTZdq8
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m88startServer$lambda5(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.post("/files/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$haVb3gconq5k99UfUR4f-1UiYls
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m89startServer$lambda6(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/files/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$FQsh1tcmx-uhoo815vQG6jd2wsY
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m90startServer$lambda7(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.post("/files", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$U3cyNyNUQjWGv34E6KSUXW6tHBA
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m80startServer$lambda12(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.get("/progress/.*", new HttpServerRequestCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$utLNlCXEorWKkYz9HhRUzay3pz8
            @Override // com.koushikdutta.async.http.server.HttpServerRequestCallback
            public final void onRequest(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
                WebUploadService.m85startServer$lambda13(WebUploadService.this, asyncHttpServerRequest, asyncHttpServerResponse);
            }
        });
        this.server.listen(this.mAsyncServer, Constant.HTTP_PORT);
        this.server.getListenCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-0  reason: not valid java name */
    public static final void m78startServer$lambda0(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendResources(asyncHttpServerRequest, asyncHttpServerResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-1  reason: not valid java name */
    public static final void m79startServer$lambda1(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendResources(asyncHttpServerRequest, asyncHttpServerResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-2  reason: not valid java name */
    public static final void m86startServer$lambda2(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendResources(asyncHttpServerRequest, asyncHttpServerResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-3  reason: not valid java name */
    public static final void m87startServer$lambda3(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        AsyncHttpServerResponse code;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (asyncHttpServerResponse == null) {
            return;
        }
        try {
            asyncHttpServerResponse.send(this$0.getIndexContent());
        } catch (IOException e) {
            e.printStackTrace();
            if (asyncHttpServerResponse == null || (code = asyncHttpServerResponse.code(500)) == null) {
                return;
            }
            code.end();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-5  reason: not valid java name */
    public static final void m88startServer$lambda5(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        String[] list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONArray jSONArray = new JSONArray();
        File file = new File(this$0.saveDir);
        if (file.exists() && file.isDirectory() && (list = file.list()) != null) {
            int i = 0;
            int length = list.length;
            while (i < length) {
                String str = list[i];
                i++;
                File file2 = new File(file, str);
                if (file2.exists() && file2.isFile()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("name", str);
                        long length2 = file2.length();
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        if (length2 > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                            jSONObject.put("size", Intrinsics.stringPlus(decimalFormat.format(((((float) length2) * 1.0f) / 1024.0f) / 1024.0f), "MB"));
                        } else if (length2 > 1024) {
                            jSONObject.put("size", Intrinsics.stringPlus(decimalFormat.format((((float) length2) * 1.0f) / 1024), "KB"));
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(length2);
                            sb.append('B');
                            jSONObject.put("size", sb.toString());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        asyncHttpServerResponse.send(jSONArray.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-6  reason: not valid java name */
    public static final void m89startServer$lambda6(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AsyncHttpRequestBody body = asyncHttpServerRequest.getBody();
        if (body == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.koushikdutta.async.http.body.UrlEncodedFormBody");
        }
        if (StringsKt.equals("delete", ((UrlEncodedFormBody) body).get().getString("_method"), true)) {
            String path = asyncHttpServerRequest.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "request.path");
            String replace$default = StringsKt.replace$default(path, "/files/", "", false, 4, (Object) null);
            try {
                String decode = URLDecoder.decode(replace$default, "utf-8");
                Intrinsics.checkNotNullExpressionValue(decode, "decode(path, \"utf-8\")");
                replace$default = decode;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            File file = new File(this$0.saveDir, replace$default);
            if (file.exists() && file.isFile()) {
                file.delete();
                EventBus.getDefault().post(new LoadUploadSubtitleEvent());
            }
        }
        asyncHttpServerResponse.end();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-7  reason: not valid java name */
    public static final void m90startServer$lambda7(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        String path;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = null;
        if (asyncHttpServerRequest != null && (path = asyncHttpServerRequest.getPath()) != null) {
            str = StringsKt.replace$default(path, "/files/", "", false, 4, (Object) null);
        }
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(this$0.saveDir, str);
        if (file.exists() && file.isFile()) {
            try {
                Headers headers = asyncHttpServerResponse.getHeaders();
                if (headers != null) {
                    headers.add("Content-Disposition", Intrinsics.stringPlus("attachment;filename=", URLEncoder.encode(file.getName(), "utf-8")));
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            asyncHttpServerResponse.sendFile(file);
            return;
        }
        asyncHttpServerResponse.code(404).send("Not found");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-12  reason: not valid java name */
    public static final void m80startServer$lambda12(final WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AsyncHttpRequestBody body = asyncHttpServerRequest.getBody();
        if (body == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.koushikdutta.async.http.body.MultipartFormDataBody");
        }
        final MultipartFormDataBody multipartFormDataBody = (MultipartFormDataBody) body;
        multipartFormDataBody.setMultipartCallback(new MultipartFormDataBody.MultipartCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$tjIbuTeESrFeJb5swre6t0deZaw
            @Override // com.koushikdutta.async.http.body.MultipartFormDataBody.MultipartCallback
            public final void onPart(Part part) {
                WebUploadService.m81startServer$lambda12$lambda10(MultipartFormDataBody.this, this$0, part);
            }
        });
        asyncHttpServerRequest.setEndCallback(new CompletedCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$zU6zfdMUKOYDCAR-PNiIhE3JTd8
            @Override // com.koushikdutta.async.callback.CompletedCallback
            public final void onCompleted(Exception exc) {
                WebUploadService.m84startServer$lambda12$lambda11(WebUploadService.this, asyncHttpServerResponse, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-12$lambda-10  reason: not valid java name */
    public static final void m81startServer$lambda12$lambda10(MultipartFormDataBody body, final WebUploadService this$0, Part part) {
        Intrinsics.checkNotNullParameter(body, "$body");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (part.isFile()) {
            body.setDataCallback(new DataCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$ss-OLfuzPn7wWl70_4rpslT_RPo
                @Override // com.koushikdutta.async.callback.DataCallback
                public final void onDataAvailable(DataEmitter dataEmitter, ByteBufferList byteBufferList) {
                    WebUploadService.m82startServer$lambda12$lambda10$lambda8(WebUploadService.this, dataEmitter, byteBufferList);
                }
            });
        } else if (body.getDataCallback() == null) {
            body.setDataCallback(new DataCallback() { // from class: com.movieboxpro.android.service.-$$Lambda$WebUploadService$0Cw0JpOLbYxAlB59HS-ABK6Nvyc
                @Override // com.koushikdutta.async.callback.DataCallback
                public final void onDataAvailable(DataEmitter dataEmitter, ByteBufferList byteBufferList) {
                    WebUploadService.m83startServer$lambda12$lambda10$lambda9(WebUploadService.this, dataEmitter, byteBufferList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-12$lambda-10$lambda-8  reason: not valid java name */
    public static final void m82startServer$lambda12$lambda10$lambda8(WebUploadService this$0, DataEmitter dataEmitter, ByteBufferList byteBufferList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FileUploadHolder fileUploadHolder = this$0.fileUploadHolder;
        byte[] allByteArray = byteBufferList.getAllByteArray();
        Intrinsics.checkNotNullExpressionValue(allByteArray, "bb.allByteArray");
        fileUploadHolder.write(allByteArray);
        byteBufferList.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-12$lambda-10$lambda-9  reason: not valid java name */
    public static final void m83startServer$lambda12$lambda10$lambda9(WebUploadService this$0, DataEmitter dataEmitter, ByteBufferList byteBufferList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            byte[] allByteArray = byteBufferList.getAllByteArray();
            Intrinsics.checkNotNullExpressionValue(allByteArray, "bb.allByteArray");
            String fileName = URLDecoder.decode(new String(allByteArray, Charsets.UTF_8), "UTF-8");
            FileUploadHolder fileUploadHolder = this$0.fileUploadHolder;
            Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
            fileUploadHolder.setFileName(fileName, this$0.saveDir);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byteBufferList.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-12$lambda-11  reason: not valid java name */
    public static final void m84startServer$lambda12$lambda11(WebUploadService this$0, AsyncHttpServerResponse asyncHttpServerResponse, Exception exc) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fileUploadHolder.reset();
        asyncHttpServerResponse.end();
        CommonExtKt.onMobEvent(this$0, "WiFiUploadSubtitles_Uploaded");
        EventUtils.event("谷歌搜索字幕下载完成");
        EventBus.getDefault().post(new LoadUploadSubtitleEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startServer$lambda-13  reason: not valid java name */
    public static final void m85startServer$lambda13(WebUploadService this$0, AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        JSONObject jSONObject = new JSONObject();
        String path = asyncHttpServerRequest.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "request.path");
        if (Intrinsics.areEqual(StringsKt.replace$default(path, "/progress/", "", false, 4, (Object) null), this$0.fileUploadHolder.getFileName())) {
            try {
                jSONObject.put("fileName", this$0.fileUploadHolder.getFileName());
                jSONObject.put("size", this$0.fileUploadHolder.getTotalSize());
                jSONObject.put("progress", this$0.fileUploadHolder.getOutPutStream() == null ? 1 : Double.valueOf(0.1d));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        asyncHttpServerResponse.send(jSONObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String getIndexContent() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            android.content.res.AssetManager r2 = r6.getAssets()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.lang.String r3 = "wifi/index.html"
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L51
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            r0.<init>()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            r2 = 10240(0x2800, float:1.4349E-41)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            int r3 = r1.read(r2)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
        L1d:
            if (r3 <= 0) goto L28
            r4 = 0
            r0.write(r2, r4, r3)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            int r3 = r1.read(r2)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            goto L1d
        L28:
            java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            byte[] r0 = r0.toByteArray()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            java.lang.String r3 = "baos.toByteArray()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            java.lang.String r3 = "utf-8"
            java.nio.charset.Charset r3 = java.nio.charset.Charset.forName(r3)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            java.lang.String r4 = "forName(\"utf-8\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            r2.<init>(r0, r3)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L59
            r1.close()     // Catch: java.io.IOException -> L45
            goto L49
        L45:
            r0 = move-exception
            r0.printStackTrace()
        L49:
            return r2
        L4a:
            r0 = move-exception
            goto L55
        L4c:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L5a
        L51:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L55:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            throw r0     // Catch: java.lang.Throwable -> L59
        L59:
            r0 = move-exception
        L5a:
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r1 = move-exception
            r1.printStackTrace()
        L64:
            goto L66
        L65:
            throw r0
        L66:
            goto L65
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.service.WebUploadService.getIndexContent():java.lang.String");
    }

    private final void sendResources(AsyncHttpServerRequest asyncHttpServerRequest, AsyncHttpServerResponse asyncHttpServerResponse) {
        String path;
        String str = null;
        if (asyncHttpServerRequest == null) {
            path = null;
        } else {
            try {
                path = asyncHttpServerRequest.getPath();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        String replace$default = path == null ? null : StringsKt.replace$default(path, "%20", " ", false, 4, (Object) null);
        boolean z = true;
        if (replace$default != null && StringsKt.startsWith$default(replace$default, "/", false, 2, (Object) null)) {
            replace$default = replace$default.substring(1);
            Intrinsics.checkNotNullExpressionValue(replace$default, "this as java.lang.String).substring(startIndex)");
        }
        if ((replace$default == null ? -1 : StringsKt.indexOf$default((CharSequence) replace$default, "?", 0, false, 6, (Object) null)) > 0) {
            if (replace$default != null) {
                str = replace$default.substring(0, StringsKt.indexOf$default((CharSequence) replace$default, "?", 0, false, 6, (Object) null));
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            replace$default = str;
        }
        String str2 = "";
        if (getContentTypeByResourceName(replace$default == null ? "" : replace$default).length() <= 0) {
            z = false;
        }
        if (z && asyncHttpServerResponse != null) {
            if (replace$default != null) {
                str2 = replace$default;
            }
            asyncHttpServerResponse.setContentType(getContentTypeByResourceName(str2));
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(getAssets().open(Intrinsics.stringPlus("wifi/", replace$default)));
        if (asyncHttpServerResponse == null) {
            return;
        }
        asyncHttpServerResponse.sendStream(bufferedInputStream, bufferedInputStream.available());
    }

    private final String getContentTypeByResourceName(String str) {
        return StringsKt.endsWith$default(str, ".css", false, 2, (Object) null) ? CSS_CONTENT_TYPE : StringsKt.endsWith$default(str, ".js", false, 2, (Object) null) ? "application/javascript" : StringsKt.endsWith$default(str, ".swf", false, 2, (Object) null) ? SWF_CONTENT_TYPE : StringsKt.endsWith$default(str, ".png", false, 2, (Object) null) ? PNG_CONTENT_TYPE : (StringsKt.endsWith$default(str, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(str, ".jpeg", false, 2, (Object) null)) ? JPG_CONTENT_TYPE : StringsKt.endsWith$default(str, ".woff", false, 2, (Object) null) ? WOFF_CONTENT_TYPE : StringsKt.endsWith$default(str, ".ttf", false, 2, (Object) null) ? TTF_CONTENT_TYPE : StringsKt.endsWith$default(str, ".svg", false, 2, (Object) null) ? SVG_CONTENT_TYPE : StringsKt.endsWith$default(str, ".eot", false, 2, (Object) null) ? EOT_CONTENT_TYPE : StringsKt.endsWith$default(str, ".mp3", false, 2, (Object) null) ? MP3_CONTENT_TYPE : StringsKt.endsWith$default(str, ".mp4", false, 2, (Object) null) ? MP4_CONTENT_TYPE : "";
    }

    /* compiled from: WebUploadService.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\b\u0010\f\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/service/WebUploadService$FileUploadHolder;", "", "()V", "fileName", "", "fileOutPutStream", "Ljava/io/BufferedOutputStream;", "reveivedFile", "Ljava/io/File;", "totalSize", "", "getFileName", "getOutPutStream", "getTotalSize", "reset", "", "setFileName", "dir", "write", "data", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FileUploadHolder {
        private String fileName;
        private BufferedOutputStream fileOutPutStream;
        private File reveivedFile;
        private long totalSize;

        public final void setFileName(String fileName, String dir) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(dir, "dir");
            this.fileName = fileName;
            this.totalSize = 0L;
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.reveivedFile = new File(file, this.fileName);
            try {
                this.fileOutPutStream = new BufferedOutputStream(new FileOutputStream(this.reveivedFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final long getTotalSize() {
            return this.totalSize;
        }

        public final BufferedOutputStream getOutPutStream() {
            return this.fileOutPutStream;
        }

        public final void reset() {
            try {
                BufferedOutputStream bufferedOutputStream = this.fileOutPutStream;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.fileOutPutStream = null;
        }

        public final void write(byte[] data) {
            Intrinsics.checkNotNullParameter(data, "data");
            try {
                BufferedOutputStream bufferedOutputStream = this.fileOutPutStream;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.write(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.totalSize += data.length;
        }
    }
}
