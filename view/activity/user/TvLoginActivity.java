package com.movieboxpro.android.view.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.SystemUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: TvLoginActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/TvLoginActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "doTvLogin", "", "code", "", "getLayoutResId", "", "initData", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvLoginActivity extends BaseSimpleActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_tv_login;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$TvLoginActivity$w5PfzYfHTw7XG5fChja66LMSVE0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvLoginActivity.m866initListener$lambda0(TvLoginActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvLogin)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$TvLoginActivity$btqUflH1oDRE0Rnc7_p2QN4EDLo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvLoginActivity.m867initListener$lambda1(TvLoginActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m866initListener$lambda0(TvLoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils.hideSoftInput((EditText) this$0._$_findCachedViewById(R.id.etCode));
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m867initListener$lambda1(TvLoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = ((EditText) this$0._$_findCachedViewById(R.id.etCode)).getText().toString();
        if (StringsKt.isBlank(obj)) {
            return;
        }
        this$0.doTvLogin(obj);
    }

    private final void doTvLogin(String str) {
        RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "Scan_captcha_v2").param("uid", App.getUserData().uid_v2).param("openudid", SystemUtils.getUniqueId(this)).param("code", str).asMsg(), new TvLoginActivity$doTvLogin$1(this), null, new TvLoginActivity$doTvLogin$2(this), null, new TvLoginActivity$doTvLogin$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(R.id.etCode));
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Code Login");
    }
}
