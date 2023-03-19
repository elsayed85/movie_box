package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivityChildModeBinding;
import com.movieboxpro.android.event.SuperChildModeChangedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.settings.ChildModePasswordActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.settings.InputSuperChildModePasswordActivity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
/* compiled from: ChildModeActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0016J\"\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChildModeActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityChildModeBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityChildModeBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "getDevices", "", "initData", "initListener", "initStatus", "initView", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChildModeActivity extends BaseBindingActivity {
    public static final int CLOSE_CHILD_MODE = 100;
    public static final int CLOSE_SUPER_CHILD_MODE = 300;
    public static final int OPEN_CHILD_MODE = 200;
    public static final int OPEN_SUPER_CHILD_MODE = 400;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityChildModeBinding.class, this);
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(ChildModeActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityChildModeBinding;", 0))};
    public static final Companion Companion = new Companion(null);

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

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityChildModeBinding getBinding() {
        return (ActivityChildModeBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    /* compiled from: ChildModeActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChildModeActivity$Companion;", "", "()V", "CLOSE_CHILD_MODE", "", "CLOSE_SUPER_CHILD_MODE", "OPEN_CHILD_MODE", "OPEN_SUPER_CHILD_MODE", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().flTvDevice.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$UlkwfKPu6sxeeIjrEPsddnONZc4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildModeActivity.m143initListener$lambda0(ChildModeActivity.this, view);
            }
        });
        getBinding().flManage.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$8mYeAe4XDkycmiTLoCEBVy_4MWs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildModeActivity.m144initListener$lambda1(ChildModeActivity.this, view);
            }
        });
        getBinding().ivChildMode.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$6_GqeOD3HeuK-SaOezZ43H6jn_U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildModeActivity.m145initListener$lambda2(ChildModeActivity.this, view);
            }
        });
        getBinding().ivSuperChildMode.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$IB3Bm8hNZNlIqQn9Y3a7FfealGc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildModeActivity.m146initListener$lambda3(ChildModeActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m143initListener$lambda0(ChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChildModeActivity childModeActivity = this$0;
        childModeActivity.startActivity(new Intent(childModeActivity, TvDeviceActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m144initListener$lambda1(ChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChildModeActivity childModeActivity = this$0;
        childModeActivity.startActivity(new Intent(childModeActivity, SuperChildModeActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m145initListener$lambda2(ChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE)) {
            InputChildModePasswordActivity.Companion.start(this$0, 100);
        } else {
            ChildModePasswordActivity.Companion.start(this$0, 200);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m146initListener$lambda3(ChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            InputSuperChildModePasswordActivity.Companion.start(this$0, 300);
            return;
        }
        Object as = HttpRequest.Companion.post("Playlists_super_child_list").param("page", (Object) 1).param("pagelimit", (Object) 10).asRequest().compose(RxUtils.rxTranslate2Bean(MovieListModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0));
        Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Playli…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ChildModeActivity$initListener$4$1(this$0), null, new ChildModeActivity$initListener$4$2(this$0), null, new ChildModeActivity$initListener$4$3(this$0), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 100) {
                getBinding().ivChildMode.setImageResource(R.mipmap.ic_switch_unchecked);
            } else if (i == 200) {
                getBinding().ivChildMode.setImageResource(R.mipmap.ic_switch_checked);
            } else if (i == 300) {
                getBinding().ivSuperChildMode.setImageResource(R.mipmap.ic_switch_unchecked);
                EventBus.getDefault().post(new SuperChildModeChangedEvent(false));
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
                PrefsUtils.getInstance().putString(Constant.Prefs.SUPER_CHILD_MODE_PASSWORD, "");
            } else if (i == 400) {
                getBinding().ivSuperChildMode.setImageResource(R.mipmap.ic_switch_checked);
            }
            initStatus();
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        getBinding().toolBar.tvTitle.setText("Child Mode");
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$A0NjGCKVvORj47NQVzhPGmCLAXE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildModeActivity.m142initData$lambda4(ChildModeActivity.this, view);
            }
        });
        getDevices();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-4  reason: not valid java name */
    public static final void m142initData$lambda4(ChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void getDevices() {
        Observable<R> compose = HttpRequest.Companion.post(API.Common.DEVICELIST).asRequest().compose(RxUtils.rxTranslate2List(Device.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        Observable map = compose.map($$Lambda$ChildModeActivity$0OUr3eHwMNihrnh_DM5wpeNng.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "HttpRequest.post(API.Com…       list\n            }");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(map, this), null, null, null, null, new ChildModeActivity$getDevices$2(this), 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDevices$lambda-6  reason: not valid java name */
    public static final ArrayList m141getDevices$lambda6(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            Device device = (Device) it2.next();
            String str = device.platform_version;
            if (!(str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "tvOS", false, 2, (Object) null))) {
                String str2 = device.platform_version;
                if (str2 != null && StringsKt.contains$default((CharSequence) str2, (CharSequence) "android_tv", false, 2, (Object) null)) {
                }
            }
            arrayList.add(device);
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        ChildModeActivity childModeActivity = this;
        GlideUtils.load((Activity) childModeActivity, (int) R.mipmap.ic_child_mode_img, (ImageView) getBinding().imageView);
        GlideUtils.load((Activity) childModeActivity, (int) R.mipmap.ic_super_child_mode_img, (ImageView) getBinding().imageView2);
        initStatus();
    }

    private final void initStatus() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE)) {
            getBinding().ivChildMode.setImageResource(R.mipmap.ic_switch_checked);
            getBinding().llSuperChildMode.setAlpha(0.3f);
            getBinding().ivSuperChildMode.setEnabled(false);
        } else {
            getBinding().llSuperChildMode.setAlpha(1.0f);
            getBinding().ivSuperChildMode.setEnabled(true);
            getBinding().ivChildMode.setImageResource(R.mipmap.ic_switch_unchecked);
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            getBinding().ivSuperChildMode.setImageResource(R.mipmap.ic_switch_checked);
            getBinding().llChildMode.setAlpha(0.3f);
            getBinding().ivChildMode.setEnabled(false);
            return;
        }
        getBinding().ivSuperChildMode.setImageResource(R.mipmap.ic_switch_unchecked);
        getBinding().llChildMode.setAlpha(1.0f);
        getBinding().ivChildMode.setEnabled(true);
    }
}
