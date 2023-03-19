package com.movieboxpro.android;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.dueeeke.model.MediaQualityInfo;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.view.activity.videoplayer.videoview.NormalIjkVideoView;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: TestActivity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/TestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        NormalIjkVideoView normalIjkVideoView = new NormalIjkVideoView(this);
        ((FrameLayout) findViewById(R.id.frameLayout)).addView(normalIjkVideoView);
        MediaQualityInfo mediaQualityInfo = new MediaQualityInfo();
        mediaQualityInfo.setPath("https://ucdn-cn1.febbox.cn/vip/p1/movie_mp4_h264/2021/3/7/39977/movie.39977.2021.360p.H264.20211126061506.mp4?k=24e896380b74423b&t=1648722783");
        normalIjkVideoView.setDefinitionVideos(CollectionsKt.arrayListOf(mediaQualityInfo), null, 0);
        normalIjkVideoView.start();
    }

    /* compiled from: TestActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/TestActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "videoId", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            int i = 0;
            Pair[] pairArr = {TuplesKt.to("videoId", str)};
            Intent intent = new Intent(context, TestActivity.class);
            Bundle bundle = new Bundle(1);
            while (i < 1) {
                Pair pair = pairArr[i];
                i++;
                String str2 = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str2, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str2, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str2, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str2, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str2, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str2, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str2, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str2, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str2, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str2, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str2, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str2, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str2, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str2, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str2, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str2, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str2, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str2, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str2, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str2, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str2, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str2, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str2, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str2, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str2 + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str2, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str2, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str2, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str2, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str2 + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
