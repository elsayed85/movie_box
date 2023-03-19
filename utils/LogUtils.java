package com.movieboxpro.android.utils;

import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.orhanobut.logger.Logger;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: LogUtils.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0001J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u000f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0012\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0013\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0014\u001a\u00020\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J0\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/utils/LogUtils;", "", "()V", LogUtils.INTERFACE_JSON_TAG, "", LogUtils.INTERFACE_PARAM_JSON_TAG, "isLog", "", "loadInterfaceParamData", "", "s", "logCollections", "any", "logD", "tag", "logE", "logInterfaceJson", "json", "logInterfaceParamJson", "logJson", "logXml", "xml", "uploadError2", "isVlc", "boxType", "", "season", "episode", "id", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LogUtils {
    public static final LogUtils INSTANCE = new LogUtils();
    private static final String INTERFACE_JSON_TAG = "INTERFACE_JSON_TAG";
    private static final String INTERFACE_PARAM_JSON_TAG = "INTERFACE_PARAM_JSON_TAG";

    private final boolean isLog() {
        return false;
    }

    private LogUtils() {
    }

    public final void uploadError2(final boolean z, final int i, final int i2, final int i3, final String str) {
        Observable compose = Observable.just("").map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$LogUtils$_H82VnSy6CQ0tkJMrwbWM_kld38
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m99uploadError2$lambda1;
                m99uploadError2$lambda1 = LogUtils.m99uploadError2$lambda1(z, (String) obj);
                return m99uploadError2$lambda1;
            }
        }).flatMap(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$LogUtils$PrfzqgMms0DbD9TD5kuqx3OCbxE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m100uploadError2$lambda2;
                m100uploadError2$lambda2 = LogUtils.m100uploadError2$lambda2(i, str, i2, i3, (String) obj);
                return m100uploadError2$lambda2;
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "just(\"\")\n               …tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose, null, null, null, LogUtils$uploadError2$3.INSTANCE, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadError2$lambda-1  reason: not valid java name */
    public static final String m99uploadError2$lambda1(boolean z, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
        StringBuilder sb = new StringBuilder();
        String str = z ? "vlc" : "IJKMEDIA";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (readLine != null && StringsKt.contains$default((CharSequence) readLine, (CharSequence) str, false, 2, (Object) null)) {
                    sb.append(readLine);
                    sb.append(ShellUtil.COMMAND_LINE_END);
                }
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uploadError2$lambda-2  reason: not valid java name */
    public static final ObservableSource m100uploadError2$lambda2(int i, String str, int i2, int i3, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        APIService service = Http.getService();
        String str2 = API.BASE_URL;
        String str3 = App.isLogin() ? App.getUserData().uid_v2 : "";
        return service.Movie_feedback2(str2, API.Common.MOVIE_FEEDBACK, str3, i, str, -1, "", "14", SystemUtils.getMsg() + '\n' + it, i2, i3, "").compose(RxUtils.rxTranslateMsg());
    }

    public final void logJson(String str) {
        if (isLog()) {
            Logger.json(str);
        }
    }

    public final void logInterfaceJson(String str) {
        if (isLog()) {
            Logger.t(INTERFACE_JSON_TAG).json(str);
        }
    }

    public final void logInterfaceParamJson(String str) {
        if (isLog()) {
            Logger.t(INTERFACE_PARAM_JSON_TAG).json(str);
        }
    }

    public final void loadInterfaceParamData(String str) {
        if (isLog()) {
            Logger.t(INTERFACE_PARAM_JSON_TAG).d(str);
        }
    }

    public final void logXml(String str) {
        if (isLog()) {
            Logger.xml(str);
        }
    }

    public final void logCollections(Object any) {
        Intrinsics.checkNotNullParameter(any, "any");
        if (isLog()) {
            Logger.d(any);
        }
    }

    public final void logD(String str) {
        if (isLog()) {
            Logger.d(str);
        }
    }

    public final void logD(String tag, String str) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (isLog()) {
            Logger.t(tag).d(str);
        }
    }

    public final void logE(String str) {
        if (isLog()) {
            if (str == null) {
                str = "";
            }
            Logger.e(str, new Object[0]);
        }
    }
}
