package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.view.View;
import com.alibaba.fastjson.JSONObject;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivityAccountSecurityBinding;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: AccountSecurityActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/AccountSecurityActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityAccountSecurityBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityAccountSecurityBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "buildFamilyData", "", "initData", "", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AccountSecurityActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(AccountSecurityActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityAccountSecurityBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityAccountSecurityBinding.class, this);

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
    public void initData() {
    }

    private final ActivityAccountSecurityBinding getBinding() {
        return (ActivityAccountSecurityBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountSecurityActivity$vuS_Qk34EB4yuiYeLzBW_ilFEsw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSecurityActivity.m136initListener$lambda0(AccountSecurityActivity.this, view);
            }
        });
        getBinding().clDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountSecurityActivity$QqQx0kGsuzQrk9Y0qd12Lcv_1FA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSecurityActivity.m137initListener$lambda1(AccountSecurityActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m136initListener$lambda0(AccountSecurityActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m137initListener$lambda1(AccountSecurityActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.startBrowser((Activity) this$0, Intrinsics.stringPlus("https://www.movieboxpro.app/index/index/confirm_logout?auth=", this$0.buildFamilyData()));
    }

    private final String buildFamilyData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Account Security");
    }
}
