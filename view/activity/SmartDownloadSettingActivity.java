package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.lifecycle.Observer;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivitySmartDownloadSettingBinding;
import com.movieboxpro.android.livedata.DownloadRuleChangedLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.widget.CustomSwitchView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
/* compiled from: SmartDownloadSettingActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/SmartDownloadSettingActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivitySmartDownloadSettingBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivitySmartDownloadSettingBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "initData", "", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SmartDownloadSettingActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SmartDownloadSettingActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivitySmartDownloadSettingBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivitySmartDownloadSettingBinding.class, this);

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

    private final ActivitySmartDownloadSettingBinding getBinding() {
        return (ActivitySmartDownloadSettingBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().llResolution.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SmartDownloadSettingActivity$KiV0cwly9fddmlJ9lXSw-F1EqNU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadSettingActivity.m288initListener$lambda0(SmartDownloadSettingActivity.this, view);
            }
        });
        getBinding().switchDownload.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SmartDownloadSettingActivity$initListener$2
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                SettingManager.INSTANCE.saveSmartDownload(z);
                SmartDownloadChangedLiveData.Companion.get().setValue(Boolean.valueOf(z));
            }
        });
        getBinding().switchAutoDelete.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SmartDownloadSettingActivity$initListener$3
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                SettingManager.INSTANCE.saveAutoDeleteWatched(z);
            }
        });
        getBinding().switchWifiOnly.setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.SmartDownloadSettingActivity$initListener$4
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                SettingManager.INSTANCE.saveSmartDownloadWifiOnly(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m288initListener$lambda0(SmartDownloadSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SmartDownloadSettingActivity smartDownloadSettingActivity = this$0;
        smartDownloadSettingActivity.startActivity(new Intent(smartDownloadSettingActivity, DownloadResolutionActivity.class));
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        DownloadRuleChangedLiveData.Companion.get().observeInActivity(this, new Observer() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SmartDownloadSettingActivity$YD7UptlFrWTjncJTMQ-HaXQVSco
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SmartDownloadSettingActivity.m287initData$lambda2(SmartDownloadSettingActivity.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-2  reason: not valid java name */
    public static final void m287initData$lambda2(SmartDownloadSettingActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        int i = 0;
        for (Object obj : StringsKt.split$default((CharSequence) it, new String[]{","}, false, 0, 6, (Object) null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            sb.append((String) obj);
            sb.append(">");
            i = i2;
        }
        sb.deleteCharAt(sb.length() - 1);
        this$0.getBinding().tvResolution.setText(sb.toString());
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().switchDownload.setChecked(SettingManager.INSTANCE.getSmartDownload());
        getBinding().switchWifiOnly.setChecked(SettingManager.INSTANCE.getSmartDownloadWifiOnly());
        getBinding().switchAutoDelete.setChecked(SettingManager.INSTANCE.getAutoDeleteWatched());
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SmartDownloadSettingActivity$ZubV8-aDc244ypPcAyUTEo78Xjk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadSettingActivity.m289initView$lambda3(SmartDownloadSettingActivity.this, view);
            }
        });
        getBinding().toolBar.tvTitle.setText("Smart Download");
        String downloadRule = SettingManager.INSTANCE.getDownloadRule();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object obj : StringsKt.split$default((CharSequence) downloadRule, new String[]{","}, false, 0, 6, (Object) null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            sb.append((String) obj);
            sb.append(">");
            i = i2;
        }
        sb.deleteCharAt(sb.length() - 1);
        getBinding().tvResolution.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3  reason: not valid java name */
    public static final void m289initView$lambda3(SmartDownloadSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
