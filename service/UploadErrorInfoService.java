package com.movieboxpro.android.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Pair;
import android.util.Size;
import android.util.SizeF;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.Utils;
import io.reactivex.Observable;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: UploadErrorInfoService.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/service/UploadErrorInfoService;", "Landroid/app/IntentService;", "()V", "onHandleIntent", "", "intent", "Landroid/content/Intent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UploadErrorInfoService extends IntentService {
    public static final String ACTION = "ACTION";
    public static final String API_NAME = "API_NAME";
    public static final String BOX_TYPE = "BOX_TYPE";
    public static final Companion Companion = new Companion(null);
    public static final String EPISODE = "EPISODE";
    public static final String ERROR_CODE = "ERROR_CODE";
    public static final String ERROR_CONTENT = "ERROR_CONTENT";
    public static final String ID = "ID";
    public static final String SEASON = "SEASON";

    public UploadErrorInfoService() {
        super("UploadErrorInfoService");
    }

    /* compiled from: UploadErrorInfoService.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J[\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/service/UploadErrorInfoService$Companion;", "", "()V", UploadErrorInfoService.ACTION, "", UploadErrorInfoService.API_NAME, UploadErrorInfoService.BOX_TYPE, UploadErrorInfoService.EPISODE, UploadErrorInfoService.ERROR_CODE, UploadErrorInfoService.ERROR_CONTENT, UploadErrorInfoService.ID, UploadErrorInfoService.SEASON, "startUploadService", "", "context", "Landroid/content/Context;", "throwable", "", "id", "apiName", "boxType", "", "action", "season", "episode", "(Landroid/content/Context;Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;II)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void startUploadService(Context context, Throwable throwable, String str, String str2, Integer num, String str3) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            startUploadService$default(this, context, throwable, str, str2, num, str3, 0, 0, 192, null);
        }

        public final void startUploadService(Context context, Throwable throwable, String str, String str2, Integer num, String str3, int i) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            startUploadService$default(this, context, throwable, str, str2, num, str3, i, 0, 128, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void startUploadService$default(Companion companion, Context context, Throwable th, String str, String str2, Integer num, String str3, int i, int i2, int i3, Object obj) {
            companion.startUploadService(context, th, str, str2, num, str3, (i3 & 64) != 0 ? 0 : i, (i3 & 128) != 0 ? 0 : i2);
        }

        public final void startUploadService(Context context, Throwable throwable, String str, String str2, Integer num, String str3, int i, int i2) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            if (context == null || !Utils.isAppForeground()) {
                return;
            }
            Pair<Integer, String> parseException = HttpResponseObserver.parseException(throwable);
            Object obj = parseException.first;
            Intrinsics.checkNotNullExpressionValue(obj, "pair.first");
            int intValue = ((Number) obj).intValue();
            int i3 = 0;
            if (500 <= intValue && intValue < 600) {
                kotlin.Pair[] pairArr = {TuplesKt.to(UploadErrorInfoService.ERROR_CODE, parseException.first), TuplesKt.to(UploadErrorInfoService.ERROR_CONTENT, parseException.second), TuplesKt.to(UploadErrorInfoService.API_NAME, str2), TuplesKt.to(UploadErrorInfoService.BOX_TYPE, num), TuplesKt.to(UploadErrorInfoService.ACTION, str3), TuplesKt.to(UploadErrorInfoService.ID, str), TuplesKt.to(UploadErrorInfoService.SEASON, Integer.valueOf(i)), TuplesKt.to(UploadErrorInfoService.EPISODE, Integer.valueOf(i2))};
                Intent intent = new Intent(context, UploadErrorInfoService.class);
                Bundle bundle = new Bundle(8);
                while (i3 < 8) {
                    kotlin.Pair pair = pairArr[i3];
                    i3++;
                    String str4 = (String) pair.component1();
                    Object component2 = pair.component2();
                    if (component2 == null) {
                        bundle.putString(str4, null);
                    } else if (component2 instanceof Boolean) {
                        bundle.putBoolean(str4, ((Boolean) component2).booleanValue());
                    } else if (component2 instanceof Byte) {
                        bundle.putByte(str4, ((Number) component2).byteValue());
                    } else if (component2 instanceof Character) {
                        bundle.putChar(str4, ((Character) component2).charValue());
                    } else if (component2 instanceof Double) {
                        bundle.putDouble(str4, ((Number) component2).doubleValue());
                    } else if (component2 instanceof Float) {
                        bundle.putFloat(str4, ((Number) component2).floatValue());
                    } else if (component2 instanceof Integer) {
                        bundle.putInt(str4, ((Number) component2).intValue());
                    } else if (component2 instanceof Long) {
                        bundle.putLong(str4, ((Number) component2).longValue());
                    } else if (component2 instanceof Short) {
                        bundle.putShort(str4, ((Number) component2).shortValue());
                    } else if (component2 instanceof Bundle) {
                        bundle.putBundle(str4, (Bundle) component2);
                    } else if (component2 instanceof CharSequence) {
                        bundle.putCharSequence(str4, (CharSequence) component2);
                    } else if (component2 instanceof Parcelable) {
                        bundle.putParcelable(str4, (Parcelable) component2);
                    } else if (component2 instanceof boolean[]) {
                        bundle.putBooleanArray(str4, (boolean[]) component2);
                    } else if (component2 instanceof byte[]) {
                        bundle.putByteArray(str4, (byte[]) component2);
                    } else if (component2 instanceof char[]) {
                        bundle.putCharArray(str4, (char[]) component2);
                    } else if (component2 instanceof double[]) {
                        bundle.putDoubleArray(str4, (double[]) component2);
                    } else if (component2 instanceof float[]) {
                        bundle.putFloatArray(str4, (float[]) component2);
                    } else if (component2 instanceof int[]) {
                        bundle.putIntArray(str4, (int[]) component2);
                    } else if (component2 instanceof long[]) {
                        bundle.putLongArray(str4, (long[]) component2);
                    } else if (component2 instanceof short[]) {
                        bundle.putShortArray(str4, (short[]) component2);
                    } else if (component2 instanceof Object[]) {
                        Class<?> componentType = component2.getClass().getComponentType();
                        Intrinsics.checkNotNull(componentType);
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            if (component2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                            }
                            bundle.putParcelableArray(str4, (Parcelable[]) component2);
                        } else if (String.class.isAssignableFrom(componentType)) {
                            if (component2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                            }
                            bundle.putStringArray(str4, (String[]) component2);
                        } else if (CharSequence.class.isAssignableFrom(componentType)) {
                            if (component2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                            }
                            bundle.putCharSequenceArray(str4, (CharSequence[]) component2);
                        } else if (Serializable.class.isAssignableFrom(componentType)) {
                            bundle.putSerializable(str4, (Serializable) component2);
                        } else {
                            String canonicalName = componentType.getCanonicalName();
                            throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str4 + Typography.quote);
                        }
                    } else if (component2 instanceof Serializable) {
                        bundle.putSerializable(str4, (Serializable) component2);
                    } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                        bundle.putBinder(str4, (IBinder) component2);
                    } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                        bundle.putSize(str4, (Size) component2);
                    } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                        bundle.putSizeF(str4, (SizeF) component2);
                    } else {
                        String canonicalName2 = component2.getClass().getCanonicalName();
                        throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str4 + Typography.quote);
                    }
                }
                intent.putExtras(bundle);
                context.startService(intent);
            }
        }
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String stringExtra;
        Integer valueOf = intent == null ? null : Integer.valueOf(intent.getIntExtra(ERROR_CODE, 0));
        String stringExtra2 = intent == null ? null : intent.getStringExtra(ERROR_CONTENT);
        String stringExtra3 = intent == null ? null : intent.getStringExtra(API_NAME);
        int intExtra = intent == null ? -1 : intent.getIntExtra(BOX_TYPE, -1);
        String stringExtra4 = intent != null ? intent.getStringExtra(ACTION) : null;
        String str = (intent == null || (stringExtra = intent.getStringExtra(ID)) == null) ? "" : stringExtra;
        int intExtra2 = intent == null ? 0 : intent.getIntExtra(SEASON, 0);
        int intExtra3 = intent == null ? 0 : intent.getIntExtra(EPISODE, 0);
        Observable compose = Http.getService().Movie_feedback2(API.BASE_URL, API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", intExtra, str, -1, "", "14", "(api)" + ((Object) stringExtra3) + ":failure->action = " + ((Object) stringExtra4) + " \n code=" + valueOf + " \n content:" + ((Object) stringExtra2) + ((Object) SystemUtils.getMsg()), intExtra2, intExtra3, "").compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "getService().Movie_feedb…tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose, null, null, null, null, 15, null);
    }
}
