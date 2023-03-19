package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.alibaba.fastjson.JSONObject;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.databinding.ActivityAccount2Binding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.settings.ChangeEmilActivity;
import com.movieboxpro.android.view.activity.settings.ModifyEmail;
import com.movieboxpro.android.view.activity.settings.OrderActivity;
import com.movieboxpro.android.view.activity.settings.VipPlanActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.yalantis.ucrop.UCrop;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: AccountActivity.kt */
@Metadata(d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u000e\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0016H\u0014J\b\u0010\"\u001a\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/activity/AccountActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "agreementModel", "Lcom/movieboxpro/android/model/UserAgreementModel;", "binding", "Lcom/movieboxpro/android/databinding/ActivityAccount2Binding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityAccount2Binding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "mGoogleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "observer", "com/movieboxpro/android/view/activity/AccountActivity$observer$1", "Lcom/movieboxpro/android/view/activity/AccountActivity$observer$1;", "outUri", "Landroid/net/Uri;", "buildData", "", "buildFamilyData", "getSub", "", "initData", "initListener", "initUserInfo", "initView", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "uploadImage", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AccountActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(AccountActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityAccount2Binding;", 0))};
    public static final Companion Companion = new Companion(null);
    private static final int REQUEST_CODE_CHOOSE = 1;
    private static final int REQUEST_MODIFT_USERNAME = 100;
    private UserAgreementModel agreementModel;
    private GoogleSignInClient mGoogleSignInClient;
    private Uri outUri;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityAccount2Binding.class, this);
    private final AccountActivity$observer$1 observer = new Observer() { // from class: com.movieboxpro.android.view.activity.AccountActivity$observer$1
        @Override // java.util.Observer
        public void update(Observable o, Object obj) {
            Intrinsics.checkNotNullParameter(o, "o");
            AccountActivity.this.initUserInfo();
        }
    };

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
    public final ActivityAccount2Binding getBinding() {
        return (ActivityAccount2Binding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    /* compiled from: AccountActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/activity/AccountActivity$Companion;", "", "()V", "REQUEST_CODE_CHOOSE", "", "REQUEST_MODIFT_USERNAME", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initUserInfo() {
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        GlideUtils.load((Activity) this, userData.avatar, (ImageView) getBinding().ivAvatar);
        getBinding().tvUsername.setText(userData.username);
        getBinding().tvUid.setText(userData.getUid());
        getBinding().tvEmail.setText(userData.email);
        if (userData.isvip == 1) {
            LinearLayout linearLayout = getBinding().llSubscriptions;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llSubscriptions");
            CommonExtKt.visible(linearLayout);
            TextView textView = getBinding().tvVipExpire;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime3(userData.dead_time * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView.setText(format);
            return;
        }
        LinearLayout linearLayout2 = getBinding().llSubscriptions;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llSubscriptions");
        CommonExtKt.gone(linearLayout2);
    }

    private final String buildFamilyData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        HttpUtils.decodeBody("eyJhcHBfa2V5IjoiNjQwNWMyZTYwNDVmNjU5YjdmZGNkOTU3ZmU1MmZlOWEiLCJlbmNyeXB0X2RhdGEiOiJWQUtzQ1JJY0k4NGFBQWgzV3UrXC9kcU1hY2lUNGMxNHFaUnAza0F4RzNVRTJlVDcwTFB4QUZ4ZE9KejhGMGRaSiIsInZlcmlmeSI6ImIzYTJmZWE2ZGRmYzBhYzgzMTQ3OTc3Njg1MGFmN2ZkIn0=", CipherKeys.getCiperKeys());
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().llFamilyPlan.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$yJ204nroxj1IHo_mPtwXiqnGK9I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m119initListener$lambda0(AccountActivity.this, view);
            }
        });
        getBinding().tvSignOut.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$PYrB6XiSeAS6PWHJ45ebcCtxITk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m122initListener$lambda2(AccountActivity.this, view);
            }
        });
        getBinding().llMyOrder.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$p8RYD9KXsyVw8USAmEU1_2odwPQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m124initListener$lambda3(AccountActivity.this, view);
            }
        });
        getBinding().llBlockList.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$-huaq6r1g6fqgC-pqPTfZ5PYNVA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m125initListener$lambda4(AccountActivity.this, view);
            }
        });
        getBinding().llEmail.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$HOEjXhzSYoq00oMJi_-ErcdkouU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m126initListener$lambda5(AccountActivity.this, view);
            }
        });
        getBinding().llName.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$4Bkbgy3w-XUjRBJiJHiaooe5s2o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m127initListener$lambda6(AccountActivity.this, view);
            }
        });
        getBinding().llAvatar.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$6kyWbgee60Hmahp6NgUvTA4RDrg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m128initListener$lambda7(AccountActivity.this, view);
            }
        });
        getBinding().llSubscriptions.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$T3y_uwk1VwGvyp68io0LkgRL9Ss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m129initListener$lambda8(AccountActivity.this, view);
            }
        });
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$t1oQ6TFRse4nOfbA1i35pkYzLB0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m130initListener$lambda9(AccountActivity.this, view);
            }
        });
        getBinding().llAccountSecurity.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$1yvVD3JLGfB5pdy1REchiKsTyvg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m120initListener$lambda10(AccountActivity.this, view);
            }
        });
        getBinding().llClearWatched.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$TdbVPQXPA5tidYMgjeTwxr3NP2w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountActivity.m121initListener$lambda11(AccountActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m119initListener$lambda0(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            SystemUtils.startBrowser((Activity) this$0, Intrinsics.stringPlus("https://www.movieboxpro.app/index/family?auth=", this$0.buildFamilyData()));
        } else {
            Login2Activity.start(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m122initListener$lambda2(final AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("Are you sure to sign out?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AccountActivity$jeITLtbXufyjuCk7eu4nVPfVnVE
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                AccountActivity.m123initListener$lambda2$lambda1(AccountActivity.this);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2$lambda-1  reason: not valid java name */
    public static final void m123initListener$lambda2$lambda1(AccountActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ObservableSubscribeProxy) Http.getService().login_out(API.BASE_URL, API.USER.LOGIN_OUT, SystemUtils.getUniqueId(App.getContext()), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0))).subscribe(new AccountActivity$initListener$2$1$1(this$0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m124initListener$lambda3(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OrderActivity.start(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m125initListener$lambda4(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AccountActivity accountActivity = this$0;
        accountActivity.startActivity(new Intent(accountActivity, MyBlockListActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m126initListener$lambda5(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChangeEmilActivity.Companion.start(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m127initListener$lambda6(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            ModifyEmail.start(100, this$0, "", App.getUserData().username);
        } else {
            Login2Activity.start(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m128initListener$lambda7(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            this$0.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Choose"), 1);
        } else {
            Login2Activity.start(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m129initListener$lambda8(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.agreementModel != null) {
            VipPlanActivity.Companion.start(this$0, this$0.agreementModel);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m130initListener$lambda9(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-10  reason: not valid java name */
    public static final void m120initListener$lambda10(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AccountActivity accountActivity = this$0;
        accountActivity.startActivity(new Intent(accountActivity, AccountSecurityActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-11  reason: not valid java name */
    public static final void m121initListener$lambda11(AccountActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object as = Http.getService().MovieRecord(API.BASE_URL, API.SETTING.MOVIERECORD, App.getUserData().uid_v2, "delall", "", "", "").compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0));
        Intrinsics.checkNotNullExpressionValue(as, "getService().MovieRecord…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new AccountActivity$initListener$11$1(this$0), null, new AccountActivity$initListener$11$2(this$0), null, new AccountActivity$initListener$11$3(this$0), 10, null);
    }

    private final void getSub() {
        io.reactivex.Observable<R> compose = Http.getService().User_agreement(API.BASE_URL, "User_agreement", App.getUserData().uid_v2).compose(RxUtils.rxTranslate2Bean(UserAgreementModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.AccountActivity$getSub$$inlined$subscribeToBean$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                ActivityAccount2Binding binding;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                binding = AccountActivity.this.getBinding();
                AppCompatImageView appCompatImageView = binding.ivVipArrow;
                Intrinsics.checkNotNullExpressionValue(appCompatImageView, "binding.ivVipArrow");
                CommonExtKt.visible(appCompatImageView);
                AccountActivity.this.agreementModel = (UserAgreementModel) it;
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.AccountActivity$getSub$$inlined$subscribeToBean$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(it)");
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.AccountActivity$getSub$$inlined$subscribeToBean$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.AccountActivity$getSub$$inlined$subscribeToBean$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        App.getInstance().addObserver(this.observer);
        initUserInfo();
        getSub();
        GoogleSignInOptions build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(GoogleSignInOpti…il()\n            .build()");
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, build);
        if (App.getUserData().getFamily() != 0) {
            UserModel.UserData userData = App.getUserData();
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
            if (App.getUserData().getFamily_member_count() <= 1) {
                TextView textView = getBinding().tvFamilyNum;
                textView.setText(((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " member)");
                return;
            }
            TextView textView2 = getBinding().tvFamilyNum;
            textView2.setText(((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " members)");
            return;
        }
        getBinding().tvFamilyNum.setText("");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseBindingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        App.getInstance().delObserver(this.observer);
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("My Account");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i != 1) {
                if (i != 69) {
                    return;
                }
                uploadImage();
                return;
            }
            Uri data = intent == null ? null : intent.getData();
            this.outUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
            Intrinsics.checkNotNull(data);
            Uri uri = this.outUri;
            Intrinsics.checkNotNull(uri);
            UCrop.of(data, uri).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1000, 1000).start(this);
        }
    }

    private final void uploadImage() {
        Uri uri = this.outUri;
        File file = new File(uri == null ? null : uri.getPath());
        if (file.exists()) {
            ((ObservableSubscribeProxy) Http.getService().uploadAvatar2(API.BASE_URL, buildData(), BuildConfig.APPLICATION_ID, App.versionCode, MultipartBody.Part.createFormData("imgupload", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file))).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.activity.AccountActivity$uploadImage$1
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    AccountActivity.this.showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(UserModel.UserData model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    AccountActivity.this.hideLoadingView();
                    model.avatar += '?' + TimeUtils.getCurrentTime();
                    App.updateUser(model.avatar);
                    GlideUtils.load((Activity) AccountActivity.this, model.avatar, (ImageView) ((CircleImageView) AccountActivity.this._$_findCachedViewById(R.id.ivAvatar)), (int) R.drawable.ic_default_avatar);
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    AccountActivity.this.hideLoadingView();
                }
            });
        }
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
}
