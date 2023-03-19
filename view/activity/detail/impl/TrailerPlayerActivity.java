package com.movieboxpro.android.view.activity.detail.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.ToastUtils;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: TrailerPlayerActivity.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/detail/impl/TrailerPlayerActivity;", "Lcom/google/android/youtube/player/YouTubeBaseActivity;", "()V", "canResolveIntent", "", "intent", "Landroid/content/Intent;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "playYoutubeByStandalone", "id", "", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TrailerPlayerActivity extends YouTubeBaseActivity {
    public static final Companion Companion = new Companion(null);
    public static final int REQ_RESOLVE_SERVICE_MISSING = 200;
    public static final int REQ_START_STANDALONE_PLAYER = 100;
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
    @Override // com.google.android.youtube.player.YouTubeBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_trailer_player);
        ((YouTubePlayerView) findViewById(R.id.youtubePlayerView)).initialize(Constant.GOOGLE_YOUTUBE_KEY, new YouTubePlayer.OnInitializedListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.TrailerPlayerActivity$onCreate$1
            @Override // com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean z) {
                if (youTubePlayer == null) {
                    return;
                }
                youTubePlayer.loadVideo(TrailerPlayerActivity.this.getIntent().getStringExtra("videoId"));
            }

            @Override // com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                ToastUtils.showShort(Intrinsics.stringPlus("Player init failed:", youTubeInitializationResult == null ? null : youTubeInitializationResult.name()), new Object[0]);
                TrailerPlayerActivity trailerPlayerActivity = TrailerPlayerActivity.this;
                String stringExtra = trailerPlayerActivity.getIntent().getStringExtra("videoId");
                if (stringExtra == null) {
                    stringExtra = "";
                }
                trailerPlayerActivity.playYoutubeByStandalone(stringExtra);
            }
        });
        ((FrameLayout) findViewById(R.id.frameLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.detail.impl.-$$Lambda$TrailerPlayerActivity$aRofZddsDq_nfsZGPPZDXccxPp8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrailerPlayerActivity.m342onCreate$lambda0(TrailerPlayerActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m342onCreate$lambda0(TrailerPlayerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playYoutubeByStandalone(String str) {
        TrailerPlayerActivity trailerPlayerActivity = this;
        Intent createVideoIntent = YouTubeStandalonePlayer.createVideoIntent(trailerPlayerActivity, Constant.GOOGLE_YOUTUBE_KEY, str, 0, true, false);
        if (createVideoIntent != null) {
            if (canResolveIntent(createVideoIntent)) {
                finish();
                startActivityForResult(createVideoIntent, 100);
                return;
            }
            YouTubeInitializationResult.SERVICE_MISSING.getErrorDialog(trailerPlayerActivity, 200).show();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 100 || i2 == -1) {
            return;
        }
        YouTubeInitializationResult returnedInitializationResult = YouTubeStandalonePlayer.getReturnedInitializationResult(intent);
        if (returnedInitializationResult.isUserRecoverableError()) {
            returnedInitializationResult.getErrorDialog(this, 0).show();
        } else {
            ToastUtils.showShort("Play failed", new Object[0]);
        }
    }

    private final boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        return (queryIntentActivities == null || queryIntentActivities.isEmpty()) ? false : true;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            ImmersionBar.with(this).fullScreen(true).hideBar(BarHide.FLAG_HIDE_BAR).init();
        } else {
            ImmersionBar.with(this).reset().init();
        }
    }

    /* compiled from: TrailerPlayerActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/detail/impl/TrailerPlayerActivity$Companion;", "", "()V", "REQ_RESOLVE_SERVICE_MISSING", "", "REQ_START_STANDALONE_PLAYER", TtmlNode.START, "", "context", "Landroid/content/Context;", "videoId", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
            Intent intent = new Intent(context, TrailerPlayerActivity.class);
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
