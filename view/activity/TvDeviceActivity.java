package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivityTvDeviceBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.settings.DeviceManagerActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
/* compiled from: TvDeviceActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/TvDeviceActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityTvDeviceBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityTvDeviceBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "getDevices", "", "initData", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvDeviceActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(TvDeviceActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityTvDeviceBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityTvDeviceBinding.class, this);

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
    public final ActivityTvDeviceBinding getBinding() {
        return (ActivityTvDeviceBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$TvDeviceActivity$KEWi5E6uM4vXpQsIbiJvTy-6u-c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDeviceActivity.m305initListener$lambda0(TvDeviceActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m305initListener$lambda0(TvDeviceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        getDevices();
        getBinding().loadingView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$TvDeviceActivity$vfAzTVMtlNM6dzJwgJ5LWoMbJGA
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                TvDeviceActivity.m304initData$lambda1(TvDeviceActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m304initData$lambda1(TvDeviceActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDevices();
    }

    private final void getDevices() {
        Observable<R> compose = HttpRequest.Companion.post(API.Common.DEVICELIST).asRequest().compose(RxUtils.rxTranslate2List(Device.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        Observable map = compose.map($$Lambda$TvDeviceActivity$ySrUSOpHGpzVzI6uKw9QxlD2Qe0.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(map, "HttpRequest.post(API.Com…       list\n            }");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(map, this), new TvDeviceActivity$getDevices$2(this), null, new TvDeviceActivity$getDevices$3(this), null, new TvDeviceActivity$getDevices$4(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDevices$lambda-3  reason: not valid java name */
    public static final ArrayList m303getDevices$lambda3(List it) {
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
        getBinding().toolBar.tvTitle.setText("TV Device");
        SpanUtils with = SpanUtils.with(getBinding().tvManager);
        Intrinsics.checkNotNullExpressionValue(with, "with(binding.tvManager)");
        SpanUtils addText = CommonExtKt.addText(with, "Manage", 14, R.color.color_main_blue);
        final int colorInt = CommonExtKt.colorInt((Context) this, (int) R.color.color_main_blue);
        addText.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.activity.TvDeviceActivity$initView$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                TvDeviceActivity tvDeviceActivity = TvDeviceActivity.this;
                tvDeviceActivity.startActivity(new Intent(tvDeviceActivity, DeviceManagerActivity.class));
            }
        }).append(" Devices").setForegroundColor(Color.parseColor("#66FFFFFF")).create();
    }
}
