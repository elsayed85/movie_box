package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.databinding.ActivityHelpSettingBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.FunctionItem;
import com.movieboxpro.android.utils.ClipboardUtil;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.AboutActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.widget.UpdateChecker;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: HelpSettingActivity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\nH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/activity/HelpSettingActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityHelpSettingBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityHelpSettingBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "buildData", "", "env", "initData", "", "initListener", "initView", "showEmail", NotificationCompat.CATEGORY_MESSAGE, "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HelpSettingActivity extends BaseBindingActivity {
    private static final int ABOUT = 6;
    private static final int ADD_TICKET = 1;
    private static final int CONTACT_US = 4;
    private static final int FAQ = 3;
    private static final int MY_TICKET = 2;
    private static final int PRIVACY_POLICY = 5;
    private static final int UPDATE = 7;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityHelpSettingBinding.class, this);
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(HelpSettingActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityHelpSettingBinding;", 0))};
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

    private final ActivityHelpSettingBinding getBinding() {
        return (ActivityHelpSettingBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    /* compiled from: HelpSettingActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/HelpSettingActivity$Companion;", "", "()V", "ABOUT", "", "ADD_TICKET", "CONTACT_US", "FAQ", "MY_TICKET", "PRIVACY_POLICY", "UPDATE", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$HelpSettingActivity$SUQ236ujGr1jmGzE5WhgoLVbF68
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpSettingActivity.m153initListener$lambda0(HelpSettingActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m153initListener$lambda0(HelpSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FunctionItem("Add Ticket", 1));
        arrayList.add(new FunctionItem("My Ticket", 2));
        arrayList.add(new FunctionItem("FAQ", 3));
        arrayList.add(new FunctionItem("Contact Us", 4));
        arrayList.add(new FunctionItem("Privacy Policy", 5));
        arrayList.add(new FunctionItem("Check For Updates", 7));
        arrayList.add(new FunctionItem("About", 6));
        final CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_function_item, HelpSettingActivity$initData$adapter$1.INSTANCE, arrayList);
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$HelpSettingActivity$BnmqiTBehPYolif9EbopjDNLk-Q
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HelpSettingActivity.m152initData$lambda1(CommBaseAdapter.this, this, baseQuickAdapter, view, i);
            }
        });
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setAdapter(commBaseAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m152initData$lambda1(CommBaseAdapter adapter, HelpSettingActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        switch (((FunctionItem) adapter.getItem(i)).getType()) {
            case 1:
                if (App.isLogin()) {
                    HelpSettingActivity helpSettingActivity = this$0;
                    String msg = SystemUtils.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "getMsg()");
                    String buildData = this$0.buildData(msg);
                    Intrinsics.checkNotNull(buildData);
                    SystemUtils.startBrowser((Activity) helpSettingActivity, Intrinsics.stringPlus("https://www.movieboxpro.app/index/order/feed?auth=", buildData));
                    return;
                }
                Login2Activity.start(this$0);
                return;
            case 2:
                if (App.isLogin()) {
                    HelpSettingActivity helpSettingActivity2 = this$0;
                    String msg2 = SystemUtils.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg2, "getMsg()");
                    String buildData2 = this$0.buildData(msg2);
                    Intrinsics.checkNotNull(buildData2);
                    SystemUtils.startBrowser((Activity) helpSettingActivity2, Intrinsics.stringPlus("https://www.movieboxpro.app/index/order/index?auth=", buildData2));
                    return;
                }
                Login2Activity.start(this$0);
                return;
            case 3:
                SystemUtils.startBrowser((Activity) this$0, "https://www.movieboxpro.app/index/index/faq");
                return;
            case 4:
                this$0.showEmail("Email:support@movieboxpro.app");
                return;
            case 5:
                SystemUtils.startBrowser((Activity) this$0, "https://www.movieboxpro.app/index/article?id=6");
                return;
            case 6:
                AboutActivity.start(this$0);
                return;
            case 7:
                if (ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, App.versionCode) <= App.versionCode) {
                    ToastUtils.showShort("MovieBoxPro is up to date.", new Object[0]);
                    return;
                }
                UpdateChecker.INSTANCE.checkWebUpdate(this$0);
                CommonExtKt.onMobEvent(this$0, "check_upgrade");
                return;
            default:
                return;
        }
    }

    private final void showEmail(String str) {
        new MsgHintDialog.Builder(this).setTitle("Note").setContent(str).setNegativeText("Copy").setPositiveText("Send").setNegativeActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$HelpSettingActivity$iwCdkRdJ8jFpvTzmeABIaKMwbpw
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                HelpSettingActivity.m155showEmail$lambda2(HelpSettingActivity.this);
            }
        }).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$HelpSettingActivity$4he0uvc4bE_DfrQe6dVu5HbSrVc
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                HelpSettingActivity.m156showEmail$lambda3(HelpSettingActivity.this);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showEmail$lambda-2  reason: not valid java name */
    public static final void m155showEmail$lambda2(HelpSettingActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ClipboardUtil.copyContent(this$0, "support@movieboxpro.app");
        ToastUtils.showShort("Copy email successfully", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showEmail$lambda-3  reason: not valid java name */
    public static final void m156showEmail$lambda3(HelpSettingActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.sendEmail(this$0);
    }

    private final String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "env", str);
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    private final String buildData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "module", API.USER.PROFILE);
        jSONObject2.put((JSONObject) IjkMediaMeta.IJKM_KEY_TYPE, "upload");
        jSONObject2.put((JSONObject) "uid", App.getUserData().uid_v2);
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "app_version", App.versionName);
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Help");
    }
}
