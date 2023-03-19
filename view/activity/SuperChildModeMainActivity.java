package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.CastStateListener;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.GoogleApiAvailability;
import com.gyf.immersionbar.ImmersionBar;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivitySuperChildModeMainBinding;
import com.movieboxpro.android.event.SuperChildModeChangedEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.ActivityUtils;
import com.movieboxpro.android.view.fragment.ChildModeListFragment;
import com.movieboxpro.android.view.fragment.userinfo.UserInfoFragment3;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: SuperChildModeMainActivity.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\nH\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0016J\b\u0010\"\u001a\u00020 H\u0016J\b\u0010#\u001a\u00020 H\u0016J\u001a\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\u001eH\u0002J\b\u0010(\u001a\u00020\nH\u0014J\b\u0010)\u001a\u00020 H\u0016J\b\u0010*\u001a\u00020 H\u0014J\b\u0010+\u001a\u00020 H\u0014J\u0010\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u00020 H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/movieboxpro/android/view/activity/SuperChildModeMainActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivitySuperChildModeMainBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivitySuperChildModeMainBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "gCastTested", "", "getGCastTested", "()Z", "setGCastTested", "(Z)V", "gCastable", "getGCastable", "setGCastable", "mCastContext", "Lcom/google/android/gms/cast/framework/CastContext;", "mCastSession", "Lcom/google/android/gms/cast/framework/CastSession;", "mCastStateListener", "Lcom/google/android/gms/cast/framework/CastStateListener;", "mExitTime", "", "mSessionManagerListener", "Lcom/google/android/gms/cast/framework/SessionManagerListener;", "enableEventBus", "getStatusColor", "", "initChromeCast", "", "initData", "initListener", "initView", "isCastAvailable", "act", "Landroid/app/Activity;", "resultCode", "needImmersionBar", "onBackPressed", "onDestroy", "onStart", "onSuperChildMode", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/SuperChildModeChangedEvent;", "refreshCastStatus", "MySessionManagerListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SuperChildModeMainActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SuperChildModeMainActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivitySuperChildModeMainBinding;", 0))};
    private boolean gCastTested;
    private boolean gCastable;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private CastStateListener mCastStateListener;
    private long mExitTime;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivitySuperChildModeMainBinding.class, this);
    private final SessionManagerListener<CastSession> mSessionManagerListener = new MySessionManagerListener(this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
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

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    protected boolean needImmersionBar() {
        return false;
    }

    private final ActivitySuperChildModeMainBinding getBinding() {
        return (ActivitySuperChildModeMainBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().flDrawer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeMainActivity$MO-ttznCT2NfuaPryfrcBACzzpU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModeMainActivity.m299initListener$lambda0(SuperChildModeMainActivity.this, view);
            }
        });
        getBinding().flMode.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeMainActivity$qlXCK8906T1OagP5oyHiqGQmSxM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModeMainActivity.m300initListener$lambda1(SuperChildModeMainActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m299initListener$lambda0(SuperChildModeMainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().drawerLayout.openDrawer(GravityCompat.END, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m300initListener$lambda1(SuperChildModeMainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SuperChildModeMainActivity superChildModeMainActivity = this$0;
        superChildModeMainActivity.startActivity(new Intent(superChildModeMainActivity, SuperChildModeActivity.class));
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            if (this.mCastContext != null) {
                CastContext castContext = this.mCastContext;
                Intrinsics.checkNotNull(castContext);
                castContext.getSessionManager().addSessionManagerListener(this.mSessionManagerListener, CastSession.class);
                CastContext castContext2 = this.mCastContext;
                Intrinsics.checkNotNull(castContext2);
                CastStateListener castStateListener = this.mCastStateListener;
                Intrinsics.checkNotNull(castStateListener);
                castContext2.addCastStateListener(castStateListener);
                if (this.mCastSession == null) {
                    this.mCastSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
                }
                if (this.mCastContext != null) {
                    CastContext castContext3 = this.mCastContext;
                    Intrinsics.checkNotNull(castContext3);
                    if (castContext3.getCastState() != 1) {
                        getBinding().flCast.setVisibility(0);
                    }
                }
                refreshCastStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        SuperChildModeMainActivity superChildModeMainActivity = this;
        ImmersionBar.with(superChildModeMainActivity).statusBarColor(R.color.color_main).navigationBarColor(R.color.color_main).init();
        FragmentUtils.add(getSupportFragmentManager(), new UserInfoFragment3(), (int) R.id.drawerFrameLayout);
        FragmentUtils.add(getSupportFragmentManager(), new ChildModeListFragment(), (int) R.id.flContainer);
        if (isCastAvailable(superChildModeMainActivity, 100)) {
            initChromeCast();
            return;
        }
        FrameLayout frameLayout = getBinding().flCast;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flCast");
        CommonExtKt.gone(frameLayout);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSuperChildMode(SuperChildModeChangedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.isOpen()) {
            return;
        }
        finish();
        ActivityUtils.finishAllActivities();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    private final void initChromeCast() {
        try {
            CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), getBinding().mediaRouteButton);
            getBinding().mediaRouteButton.setRemoteIndicatorDrawable(getResources().getDrawable(R.drawable.mr_button_dark));
            this.mCastStateListener = new CastStateListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeMainActivity$yI3C3DbifD7DLh_NQFN4z6AXtZ4
                @Override // com.google.android.gms.cast.framework.CastStateListener
                public final void onCastStateChanged(int i) {
                    SuperChildModeMainActivity.m298initChromeCast$lambda2(SuperChildModeMainActivity.this, i);
                }
            };
            this.mCastContext = CastContext.getSharedInstance(this);
        } catch (Exception e) {
            getBinding().flCast.setVisibility(8);
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initChromeCast$lambda-2  reason: not valid java name */
    public static final void m298initChromeCast$lambda2(SuperChildModeMainActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 1) {
            this$0.getBinding().flCast.setVisibility(0);
        } else {
            this$0.getBinding().flCast.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshCastStatus() {
        CastSession castSession = this.mCastSession;
        if (castSession != null) {
            Intrinsics.checkNotNull(castSession);
            if (castSession.isConnected()) {
                getBinding().flCast.setVisibility(0);
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mCastContext = null;
        this.mCastSession = null;
        super.onDestroy();
    }

    public final boolean getGCastable() {
        return this.gCastable;
    }

    public final void setGCastable(boolean z) {
        this.gCastable = z;
    }

    public final boolean getGCastTested() {
        return this.gCastTested;
    }

    public final void setGCastTested(boolean z) {
        this.gCastTested = z;
    }

    private final boolean isCastAvailable(Activity activity, int i) {
        if (this.gCastTested) {
            return this.gCastable;
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance()");
        Intrinsics.checkNotNull(activity);
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            this.gCastable = true;
            this.gCastTested = true;
            return true;
        } else if (isGooglePlayServicesAvailable == 9) {
            this.gCastable = false;
            this.gCastTested = true;
            return false;
        } else if (googleApiAvailability.isUserResolvableError(isGooglePlayServicesAvailable)) {
            Dialog errorDialog = googleApiAvailability.getErrorDialog(activity, isGooglePlayServicesAvailable, i, new DialogInterface.OnCancelListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeMainActivity$hacoqPqCujfdVNsxUCHmKOpWB10
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    SuperChildModeMainActivity.m301isCastAvailable$lambda3(SuperChildModeMainActivity.this, dialogInterface);
                }
            });
            Intrinsics.checkNotNull(errorDialog);
            errorDialog.show();
            return this.gCastable;
        } else {
            this.gCastTested = true;
            this.gCastable = false;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: isCastAvailable$lambda-3  reason: not valid java name */
    public static final void m301isCastAvailable$lambda3(SuperChildModeMainActivity this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.gCastable = false;
        this$0.gCastTested = false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (System.currentTimeMillis() - this.mExitTime > ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
            ToastUtils.showShort("Press again to quit", new Object[0]);
            this.mExitTime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }

    /* compiled from: SuperChildModeMainActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\bH\u0016¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/activity/SuperChildModeMainActivity$MySessionManagerListener;", "Lcom/google/android/gms/cast/framework/SessionManagerListener;", "Lcom/google/android/gms/cast/framework/CastSession;", "(Lcom/movieboxpro/android/view/activity/SuperChildModeMainActivity;)V", "onSessionEnded", "", "session", "error", "", "onSessionEnding", "onSessionResumeFailed", "onSessionResumed", "wasSuspended", "", "onSessionResuming", "sessionId", "", "onSessionStartFailed", "onSessionStarted", "onSessionStarting", "onSessionSuspended", "reason", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    private final class MySessionManagerListener implements SessionManagerListener<CastSession> {
        final /* synthetic */ SuperChildModeMainActivity this$0;

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnding(CastSession session) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumeFailed(CastSession session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResuming(CastSession session, String sessionId) {
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStartFailed(CastSession session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarting(CastSession session) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionSuspended(CastSession session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
        }

        public MySessionManagerListener(SuperChildModeMainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionEnded(CastSession session, int i) {
            Intrinsics.checkNotNullParameter(session, "session");
            if (session == this.this$0.mCastSession) {
                this.this$0.mCastSession = null;
            }
            this.this$0.refreshCastStatus();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionResumed(CastSession session, boolean z) {
            Intrinsics.checkNotNullParameter(session, "session");
            this.this$0.mCastSession = session;
            this.this$0.refreshCastStatus();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public void onSessionStarted(CastSession session, String sessionId) {
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            this.this$0.mCastSession = session;
            this.this$0.refreshCastStatus();
        }
    }
}
