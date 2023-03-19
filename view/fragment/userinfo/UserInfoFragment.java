package com.movieboxpro.android.view.fragment.userinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.OnChangeNameEmailEvent;
import com.movieboxpro.android.event.OnNoticeCountEvent;
import com.movieboxpro.android.event.OnNoticeRefreshEvent;
import com.movieboxpro.android.event.OnScanResultEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.livedata.IncognitoModeLiveData;
import com.movieboxpro.android.model.DrawerItem;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.ClipboardUtil;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.ChildModeActivity;
import com.movieboxpro.android.view.activity.MyBlockListActivity;
import com.movieboxpro.android.view.activity.MyCommentActivity;
import com.movieboxpro.android.view.activity.NoticeMsgActivity;
import com.movieboxpro.android.view.activity.PermissionRequestActivity;
import com.movieboxpro.android.view.activity.settings.DeviceManagerActivity;
import com.movieboxpro.android.view.activity.settings.HistoryActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.settings.ModifyEmail;
import com.movieboxpro.android.view.activity.settings.OrderActivity;
import com.movieboxpro.android.view.activity.settings.ScanActivity;
import com.movieboxpro.android.view.activity.settings.SettingActivity;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import com.movieboxpro.android.view.activity.settings.VipPlanActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.QrCodeActivity;
import com.movieboxpro.android.view.activity.user.TvLoginActivity;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.TestNetDialog;
import com.movieboxpro.android.view.fragment.userinfo.UserInfoContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.yalantis.ucrop.UCrop;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: UserInfoFragment.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0006H\u0014J\b\u0010\u0017\u001a\u00020\u0002H\u0014J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001dH\u0003J\b\u0010$\u001a\u00020\u0011H\u0014J\b\u0010%\u001a\u00020\u001dH\u0014J\b\u0010&\u001a\u00020\u001dH\u0014J\b\u0010'\u001a\u00020\u001dH\u0002J\b\u0010(\u001a\u00020\u001dH\u0014J\b\u0010)\u001a\u00020\u001dH\u0014J\"\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00102\u001a\u00020\u001d2\u0006\u00100\u001a\u000203H\u0007J\u0010\u00104\u001a\u00020\u001d2\u0006\u00100\u001a\u000205H\u0007J\u0010\u00106\u001a\u00020\u001d2\u0006\u00100\u001a\u000207H\u0007J\u0010\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0006H\u0016J\u0010\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u000fH\u0016J\u0010\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\u0019H\u0002J\u0016\u0010>\u001a\u00020\u001d2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0016J\b\u0010B\u001a\u00020\u001dH\u0016J\b\u0010C\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoPresenter;", "Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoContract$View;", "()V", "REQUEST_CODE_CAMERA", "", "REQUEST_CODE_CHOOSE", "REQUEST_CODE_CROP", "REQUEST_MODIFT_USERNAME", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/DrawerItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "agreementModel", "Lcom/movieboxpro/android/model/UserAgreementModel;", "haveNewMsg", "", "mGoogleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "outUri", "Landroid/net/Uri;", "bindLayout", "bindPresenter", "buildData", "", "env", "buildFamilyData", "cropImage", "", "uri", "deviceFull", "doLogin", "userData", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "doNotLogin", "enableEventBus", "initData", "initListener", "initUserInfo", "initView", "loadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onChangeNameEmail", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnChangeNameEmailEvent;", "onLogin", "Lcom/movieboxpro/android/event/LoginEvent;", "onNoticeRefresh", "Lcom/movieboxpro/android/event/OnNoticeRefreshEvent;", "onScanResult", "Lcom/movieboxpro/android/event/OnScanResultEvent;", "setNoticeCount", "count", "showAgreement", "model", "showEmail", NotificationCompat.CATEGORY_MESSAGE, "showTestSpeedDialog", "list", "", "Lcom/movieboxpro/android/model/common/NetTestModel;", "signOutComplete", "uploadImage", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UserInfoFragment extends BaseMvpFragment<UserInfoPresenter> implements UserInfoContract.View {
    private BaseQuickAdapter<DrawerItem, BaseViewHolder> adapter;
    private UserAgreementModel agreementModel;
    private boolean haveNewMsg;
    private GoogleSignInClient mGoogleSignInClient;
    private Uri outUri;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final int REQUEST_CODE_CROP = 2;
    private final int REQUEST_CODE_CHOOSE = 1;
    private final int REQUEST_MODIFT_USERNAME = 100;
    private final int REQUEST_CODE_CAMERA = 200;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTestSpeedDialog$lambda-41  reason: not valid java name */
    public static final void m1299showTestSpeedDialog$lambda41() {
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_user_info;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public UserInfoPresenter bindPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$tOhDx8B-oUv6VbdF50ztr7ADEIM
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                UserInfoFragment.m1273initListener$lambda10(UserInfoFragment.this, baseQuickAdapter2, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvNextBill)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$WFPFQsEbWId9JqxT-tq2SHGrq28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1274initListener$lambda11(UserInfoFragment.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.clTopInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$eWsXWfXBs32xy12rgYo27EI30ts
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1275initListener$lambda13(UserInfoFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivSignOut)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$EDaDnIAuqrmRAxrtOnweJJ7pBaQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1276initListener$lambda16(UserInfoFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivScan)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$hcEFXytphMl7pSbFjEcEs1niVwA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1278initListener$lambda19(UserInfoFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvUsername)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$EPu1lblrbsJfCZ-_ABtgR6MX17A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1280initListener$lambda21(UserInfoFragment.this, view);
            }
        });
        ((CircleImageView) _$_findCachedViewById(R.id.ivAvatar)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$BtGhP6OEs_2P1o8fJQ0HUiOXIjU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1281initListener$lambda23(UserInfoFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llVip)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$LUaSj6ctLbKNO9K98wJYsTkoKq0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1282initListener$lambda24(UserInfoFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llTv)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$5GMNbx67HfkV0EzACp-CRxLnGdA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1283initListener$lambda26(UserInfoFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llNotification)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$ZGQw3qJfTibGwU-yVk-n9fbj4c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1284initListener$lambda28(UserInfoFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$RumQ_FjLen_ikjpTc2wH3qhpHgk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1285initListener$lambda31(UserInfoFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvEmail)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$RR4iPREiwJ1wqqCGFB22TY9hC6k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment.m1286initListener$lambda32(UserInfoFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-10  reason: not valid java name */
    public static final void m1273initListener$lambda10(UserInfoFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        switch (baseQuickAdapter.getItem(i).getType()) {
            case 1:
                if (App.isLogin() || (context = this$0.getContext()) == null) {
                    return;
                }
                Login2Activity.start(context);
                return;
            case 2:
                String msg = SystemUtils.getMsg(this$0.getActivity());
                Intrinsics.checkNotNullExpressionValue(msg, "getMsg(activity)");
                String buildData = this$0.buildData(msg);
                Intrinsics.checkNotNull(buildData);
                SystemUtils.startBrowser((Activity) this$0.getActivity(), Intrinsics.stringPlus("https://www.movieboxpro.app/index/invite/code?auth=", buildData));
                return;
            case 3:
                TestSpeedActivity.Companion.start(this$0.getContext());
                return;
            case 4:
                if (App.isLogin()) {
                    String msg2 = SystemUtils.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg2, "getMsg()");
                    String buildData2 = this$0.buildData(msg2);
                    Intrinsics.checkNotNull(buildData2);
                    SystemUtils.startBrowser((Activity) this$0.getActivity(), Intrinsics.stringPlus("https://www.movieboxpro.app/index/order/feed?auth=", buildData2));
                    return;
                }
                Context context2 = this$0.getContext();
                if (context2 == null) {
                    return;
                }
                Login2Activity.start(context2);
                return;
            case 5:
                if (App.isLogin()) {
                    String msg3 = SystemUtils.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg3, "getMsg()");
                    String buildData3 = this$0.buildData(msg3);
                    Intrinsics.checkNotNull(buildData3);
                    SystemUtils.startBrowser((Activity) this$0.getActivity(), Intrinsics.stringPlus("https://www.movieboxpro.app/index/order/index?auth=", buildData3));
                    return;
                }
                Context context3 = this$0.getContext();
                if (context3 == null) {
                    return;
                }
                Login2Activity.start(context3);
                return;
            case 6:
                Context context4 = this$0.getContext();
                if (context4 == null) {
                    return;
                }
                OrderActivity.start(context4);
                return;
            case 7:
                SystemUtils.startBrowser((Activity) this$0.getActivity(), "https://www.movieboxpro.app/index/index/faq");
                return;
            case 8:
                Context context5 = this$0.getContext();
                if (context5 == null) {
                    return;
                }
                SettingActivity.start(context5);
                return;
            case 9:
                this$0.showEmail("Email:support@movieboxpro.app");
                return;
            case 10:
                if (App.isLogin()) {
                    Context context6 = this$0.getContext();
                    if (context6 == null) {
                        return;
                    }
                    context6.startActivity(new Intent(context6, DeviceManagerActivity.class));
                    return;
                }
                Context context7 = this$0.getContext();
                if (context7 == null) {
                    return;
                }
                Login2Activity.start(context7);
                return;
            case 11:
                if (App.isLogin()) {
                    this$0.startActivity(new Intent(this$0.getContext(), TvLoginActivity.class));
                    return;
                }
                Context context8 = this$0.getContext();
                if (context8 == null) {
                    return;
                }
                Login2Activity.start(context8);
                return;
            case 12:
                if (App.isLogin()) {
                    SystemUtils.startBrowser((Activity) this$0.getActivity(), Intrinsics.stringPlus("https://www.movieboxpro.app/index/family?auth=", this$0.buildFamilyData()));
                    return;
                }
                Context context9 = this$0.getContext();
                if (context9 == null) {
                    return;
                }
                Login2Activity.start(context9);
                return;
            case 13:
                if (App.isLogin()) {
                    Context context10 = this$0.getContext();
                    if (context10 == null) {
                        return;
                    }
                    context10.startActivity(new Intent(context10, MyCommentActivity.class));
                    return;
                }
                Context context11 = this$0.getContext();
                if (context11 == null) {
                    return;
                }
                Login2Activity.start(context11);
                return;
            case 14:
            default:
                return;
            case 15:
                Context context12 = this$0.getContext();
                if (context12 == null) {
                    return;
                }
                context12.startActivity(new Intent(context12, ChildModeActivity.class));
                return;
            case 16:
                Context context13 = this$0.getContext();
                if (context13 == null) {
                    return;
                }
                context13.startActivity(new Intent(context13, MyBlockListActivity.class));
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-11  reason: not valid java name */
    public static final void m1274initListener$lambda11(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VipPlanActivity.Companion.start(this$0.getContext(), this$0.agreementModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-13  reason: not valid java name */
    public static final void m1275initListener$lambda13(UserInfoFragment this$0, View view) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin() || (context = this$0.getContext()) == null) {
            return;
        }
        Login2Activity.start(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-16  reason: not valid java name */
    public static final void m1276initListener$lambda16(final UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        new MsgHintDialog.Builder(context).setTitle("Note").setContent("Are you sure to sign out?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$PoZar3W8tsMA816iey-AEK0dqfg
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UserInfoFragment.m1277initListener$lambda16$lambda15$lambda14(UserInfoFragment.this);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-16$lambda-15$lambda-14  reason: not valid java name */
    public static final void m1277initListener$lambda16$lambda15$lambda14(UserInfoFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((UserInfoPresenter) this$0.mPresenter).signOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-19  reason: not valid java name */
    public static final void m1278initListener$lambda19(final UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this$0.getContext());
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$OuL_29O2R_peEe0pAj89pdQ5qhw
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    UserInfoFragment.m1279initListener$lambda19$lambda18(UserInfoFragment.this);
                }
            });
            childModeHintDialog.show();
            return;
        }
        Context context = this$0.getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "TapScanQR");
        }
        EventUtils.event("点击扫描按钮");
        if (CommonUtils.havePermissions(this$0.getContext(), new String[]{"android.permission.CAMERA"})) {
            new IntentIntegrator(this$0.getActivity()).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setCameraId(0).setBeepEnabled(true).setCaptureActivity(QrCodeActivity.class).initiateScan();
        } else {
            PermissionRequestActivity.Companion.start(this$0, 3, this$0.REQUEST_CODE_CAMERA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-19$lambda-18  reason: not valid java name */
    public static final void m1279initListener$lambda19$lambda18(UserInfoFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        InputChildModePasswordActivity.Companion.start(activity, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-21  reason: not valid java name */
    public static final void m1280initListener$lambda21(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            ModifyEmail.start(this$0.REQUEST_MODIFT_USERNAME, this$0, "", App.getUserData().username);
            return;
        }
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        Login2Activity.start(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-23  reason: not valid java name */
    public static final void m1281initListener$lambda23(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            this$0.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Choose"), this$0.REQUEST_CODE_CHOOSE);
            return;
        }
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        Login2Activity.start(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-24  reason: not valid java name */
    public static final void m1282initListener$lambda24(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            SystemUtils.startBrowser(this$0.getContext(), Intrinsics.stringPlus("https://www.movieboxpro.app/index/index/vip?auth=", this$0.buildFamilyData()));
        } else {
            Login2Activity.start(this$0.getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-26  reason: not valid java name */
    public static final void m1283initListener$lambda26(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        ScanActivity.start(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-28  reason: not valid java name */
    public static final void m1284initListener$lambda28(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            Context context = this$0.getContext();
            if (context == null) {
                return;
            }
            context.startActivity(new Intent(context, NoticeMsgActivity.class));
            return;
        }
        Context context2 = this$0.getContext();
        if (context2 == null) {
            return;
        }
        Login2Activity.start(context2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-31  reason: not valid java name */
    public static final void m1285initListener$lambda31(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            Context context = this$0.getContext();
            if (context == null) {
                return;
            }
            HistoryActivity.start(context);
            return;
        }
        Context context2 = this$0.getContext();
        if (context2 == null) {
            return;
        }
        Login2Activity.start(context2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-32  reason: not valid java name */
    public static final void m1286initListener$lambda32(UserInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            return;
        }
        Login2Activity.start(this$0.getContext());
    }

    private final String buildFamilyData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        HttpUtils.decodeBody("eyJhcHBfa2V5IjoiNjQwNWMyZTYwNDVmNjU5YjdmZGNkOTU3ZmU1MmZlOWEiLCJlbmNyeXB0X2RhdGEiOiJWQUtzQ1JJY0k4NGFBQWgzV3UrXC9kcU1hY2lUNGMxNHFaUnAza0F4RzNVRTJlVDcwTFB4QUZ4ZE9KejhGMGRaSiIsInZlcmlmeSI6ImIzYTJmZWE2ZGRmYzBhYzgzMTQ3OTc3Njg1MGFmN2ZkIn0=", CipherKeys.getCiperKeys());
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    private final void showEmail(String str) {
        new MsgHintDialog.Builder(getContext()).setTitle("Note").setContent(str).setNegativeText("Copy").setPositiveText("Send").setNegativeActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$yEV-F4NoHJajgtEM5WOAYeQE1Tw
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UserInfoFragment.m1297showEmail$lambda33(UserInfoFragment.this);
            }
        }).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$TQzpU1i3KFzC0TWSz8UcfcPUdlo
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UserInfoFragment.m1298showEmail$lambda34(UserInfoFragment.this);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showEmail$lambda-33  reason: not valid java name */
    public static final void m1297showEmail$lambda33(UserInfoFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ClipboardUtil.copyContent(this$0.getContext(), "support@movieboxpro.app");
        ToastUtils.showShort("Copy email successfully", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showEmail$lambda-34  reason: not valid java name */
    public static final void m1298showEmail$lambda34(UserInfoFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.sendEmail(this$0.getActivity());
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.View
    public void signOutComplete() {
        GoogleSignInClient googleSignInClient;
        Task<Void> signOut;
        FragmentActivity activity = getActivity();
        if (activity == null || (googleSignInClient = this.mGoogleSignInClient) == null || (signOut = googleSignInClient.signOut()) == null) {
            return;
        }
        signOut.addOnCompleteListener(activity, new OnCompleteListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$KzqE7xKxgZ7XXOb2-ijXEFuZa40
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                UserInfoFragment.m1300signOutComplete$lambda36$lambda35(UserInfoFragment.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: signOutComplete$lambda-36$lambda-35  reason: not valid java name */
    public static final void m1300signOutComplete$lambda36$lambda35(UserInfoFragment this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.hideLoading();
        App.logout();
        EventBus.getDefault().post(new LogoutEvent());
        this$0.initUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        initUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onScanResult(OnScanResultEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String result = event.getResult();
        Intrinsics.checkNotNullExpressionValue(result, "event.result");
        if (StringsKt.startsWith$default(result, "mbp_login", false, 2, (Object) null)) {
            String result2 = event.getResult();
            Intrinsics.checkNotNullExpressionValue(result2, "event.result");
            ((UserInfoPresenter) this.mPresenter).doWebLogin(result2);
            return;
        }
        String result3 = event.getResult();
        Intrinsics.checkNotNullExpressionValue(result3, "event.result");
        ((UserInfoPresenter) this.mPresenter).doTvLogin(result3);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onNoticeRefresh(OnNoticeRefreshEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (App.isLogin()) {
            ((UserInfoPresenter) this.mPresenter).getNoticeCount();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onChangeNameEmail(OnChangeNameEmailEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
        ((TextView) _$_findCachedViewById(R.id.tvUsername)).setText(userData.username);
        if (userData.isvip == 1) {
            ImageView ivVip = (ImageView) _$_findCachedViewById(R.id.ivVip);
            Intrinsics.checkNotNullExpressionValue(ivVip, "ivVip");
            CommonExtKt.visible(ivVip);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime3(userData.dead_time * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            String format2 = String.format(format, Arrays.copyOf(new Object[0], 0));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            ((TextView) _$_findCachedViewById(R.id.tvVipExpire)).setText(format2);
        } else {
            ImageView ivVip2 = (ImageView) _$_findCachedViewById(R.id.ivVip);
            Intrinsics.checkNotNullExpressionValue(ivVip2, "ivVip");
            CommonExtKt.gone(ivVip2);
        }
        ((TextView) _$_findCachedViewById(R.id.tvEmail)).setText(userData.email);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        UserModel.UserData userData;
        if (ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, 1) > 169) {
            this.haveNewMsg = true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DrawerItem("Speed Test", 3));
        arrayList.add(new DrawerItem("Devices", 10));
        if (App.getUserData().getFamily() != 0) {
            Intrinsics.checkNotNullExpressionValue(App.getUserData(), "getUserData()");
            if (App.getUserData().getFamily_member_count() <= 1) {
                arrayList.add(new DrawerItem("Family plan", 12, ((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " member)"));
            } else {
                arrayList.add(new DrawerItem("Family plan", 12, ((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " members)"));
            }
        } else {
            arrayList.add(new DrawerItem("Family plan", 12));
        }
        arrayList.add(new DrawerItem("My Comments", 13));
        arrayList.add(new DrawerItem("Code Login", 11));
        arrayList.add(new DrawerItem("Private Mode", 14));
        arrayList.add(new DrawerItem("Child Mode", 15));
        arrayList.add(new DrawerItem("Settings", 8));
        arrayList.add(new DrawerItem("My Block List", 16));
        arrayList.add(new DrawerItem("Add Ticket", 4));
        arrayList.add(new DrawerItem("My Tickets", 5));
        arrayList.add(new DrawerItem("Email Us", 9));
        arrayList.add(new DrawerItem("FAQ", 7, true));
        this.adapter = new UserInfoFragment$initData$1(arrayList, this);
        ((RecyclerView) _$_findCachedViewById(R.id.rvFunction)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvFunction);
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        GoogleSignInOptions build = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(GoogleSignInOpti…\n                .build()");
        FragmentActivity activity = getActivity();
        this.mGoogleSignInClient = activity != null ? GoogleSignIn.getClient((Activity) activity, build) : null;
        ((ConstraintLayout) _$_findCachedViewById(R.id.clTopInfo)).post(new Runnable() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$rD0IIz8dywtZqF7jRQ5WAsgKk_s
            @Override // java.lang.Runnable
            public final void run() {
                UserInfoFragment.m1271initData$lambda38(UserInfoFragment.this);
            }
        });
        IncognitoModeLiveData.Companion.get().observe(this, new Observer() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$F4D-mD0l97EEptSweZsE36xVb0E
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserInfoFragment.m1272initData$lambda39(UserInfoFragment.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-38  reason: not valid java name */
    public static final void m1271initData$lambda38(final UserInfoFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getContext() != null) {
            int width = ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clTopInfo)).getWidth();
            int height = ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clTopInfo)).getHeight();
            if (width <= 0 || height <= 0) {
                return;
            }
            final int width2 = ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clTopInfo)).getWidth();
            final int height2 = ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clTopInfo)).getHeight();
            Glide.with(this$0.requireContext()).asDrawable().load(Integer.valueOf((int) R.drawable.ic_vip_card)).dontAnimate().into((RequestBuilder) new SimpleTarget<Drawable>(width2, height2) { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoFragment$initData$3$1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    ((ConstraintLayout) UserInfoFragment.this._$_findCachedViewById(R.id.clTopInfo)).setBackground(resource);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-39  reason: not valid java name */
    public static final void m1272initData$lambda39(UserInfoFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        initUserInfo();
    }

    private final void initUserInfo() {
        if (App.isLogin()) {
            UserModel.UserData userData = App.getUserData();
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData()");
            doLogin(userData);
            return;
        }
        doNotLogin();
    }

    private final void doNotLogin() {
        ((CircleImageView) _$_findCachedViewById(R.id.ivAvatar)).setImageResource(R.mipmap.ic_no_login_avatar);
        ImageView ivVip = (ImageView) _$_findCachedViewById(R.id.ivVip);
        Intrinsics.checkNotNullExpressionValue(ivVip, "ivVip");
        CommonExtKt.gone(ivVip);
        ImageView ivSignOut = (ImageView) _$_findCachedViewById(R.id.ivSignOut);
        Intrinsics.checkNotNullExpressionValue(ivSignOut, "ivSignOut");
        CommonExtKt.gone(ivSignOut);
        ((TextView) _$_findCachedViewById(R.id.tvUsername)).setText("Sign In");
        ((TextView) _$_findCachedViewById(R.id.tvEmail)).setText("with Google account");
        ((TextView) _$_findCachedViewById(R.id.tvVipExpire)).setText("");
        ((TextView) _$_findCachedViewById(R.id.tvNextBill)).setText("");
        ImageView ivScan = (ImageView) _$_findCachedViewById(R.id.ivScan);
        Intrinsics.checkNotNullExpressionValue(ivScan, "ivScan");
        CommonExtKt.gone(ivScan);
        TextView tab_count = (TextView) _$_findCachedViewById(R.id.tab_count);
        Intrinsics.checkNotNullExpressionValue(tab_count, "tab_count");
        CommonExtKt.gone(tab_count);
        ((TextView) _$_findCachedViewById(R.id.tvUid)).setText("");
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        for (IndexedValue indexedValue : CollectionsKt.withIndex(baseQuickAdapter.getData())) {
            if (((DrawerItem) indexedValue.getValue()).getType() == 12) {
                ((DrawerItem) indexedValue.getValue()).setContent("");
                BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter3 = this.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter2.notifyItemChanged(indexedValue.getIndex());
                return;
            }
        }
    }

    private final void doLogin(UserModel.UserData userData) {
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        Iterator it = CollectionsKt.withIndex(baseQuickAdapter.getData()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IndexedValue indexedValue = (IndexedValue) it.next();
            if (((DrawerItem) indexedValue.getValue()).getType() == 12) {
                if (userData.getFamily() != 0) {
                    if (userData.getFamily_member_count() <= 1) {
                        ((DrawerItem) indexedValue.getValue()).setContent(((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " member)");
                    } else {
                        ((DrawerItem) indexedValue.getValue()).setContent(((Object) userData.getMaster_username()) + "'s family (" + userData.getFamily_member_count() + " members)");
                    }
                } else {
                    ((DrawerItem) indexedValue.getValue()).setContent("");
                }
                BaseQuickAdapter<DrawerItem, BaseViewHolder> baseQuickAdapter3 = this.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter2.notifyItemChanged(indexedValue.getIndex());
            }
        }
        GlideUtils.loadWithNoCache(getContext(), userData.avatar, (CircleImageView) _$_findCachedViewById(R.id.ivAvatar), R.drawable.ic_default_avatar);
        ((TextView) _$_findCachedViewById(R.id.tvUsername)).setText(userData.username);
        ImageView ivScan = (ImageView) _$_findCachedViewById(R.id.ivScan);
        Intrinsics.checkNotNullExpressionValue(ivScan, "ivScan");
        CommonExtKt.visible(ivScan);
        ImageView ivSignOut = (ImageView) _$_findCachedViewById(R.id.ivSignOut);
        Intrinsics.checkNotNullExpressionValue(ivSignOut, "ivSignOut");
        CommonExtKt.visible(ivSignOut);
        int i = 0;
        if (userData.isvip == 1) {
            ImageView ivVip = (ImageView) _$_findCachedViewById(R.id.ivVip);
            Intrinsics.checkNotNullExpressionValue(ivVip, "ivVip");
            CommonExtKt.visible(ivVip);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime3(userData.dead_time * 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            String format2 = String.format(format, Arrays.copyOf(new Object[0], 0));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            ((TextView) _$_findCachedViewById(R.id.tvVipExpire)).setText(format2);
        } else {
            ImageView ivVip2 = (ImageView) _$_findCachedViewById(R.id.ivVip);
            Intrinsics.checkNotNullExpressionValue(ivVip2, "ivVip");
            CommonExtKt.gone(ivVip2);
        }
        ((TextView) _$_findCachedViewById(R.id.tvEmail)).setText(userData.email);
        ((UserInfoPresenter) this.mPresenter).getNoticeCount();
        StringBuilder sb = new StringBuilder();
        if (userData.getUid() != null) {
            int length = userData.getUid().length();
            while (i < length) {
                int i2 = i + 1;
                if (i != userData.getUid().length() - 1) {
                    sb.append(String.valueOf(userData.getUid().charAt(i)));
                    sb.append("     ");
                } else {
                    sb.append(userData.getUid().charAt(i));
                }
                i = i2;
            }
        }
        ((TextView) _$_findCachedViewById(R.id.tvUid)).setText(sb);
        UserInfoPresenter userInfoPresenter = (UserInfoPresenter) this.mPresenter;
        String str = userData.uid_v2;
        userInfoPresenter.getUserAgreement(str != null ? str : "");
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.View
    public void setNoticeCount(int i) {
        if (i > 0) {
            TextView tab_count = (TextView) _$_findCachedViewById(R.id.tab_count);
            Intrinsics.checkNotNullExpressionValue(tab_count, "tab_count");
            CommonExtKt.visible(tab_count);
            if (i <= 99) {
                ((TextView) _$_findCachedViewById(R.id.tab_count)).setText(String.valueOf(i));
            } else {
                ((TextView) _$_findCachedViewById(R.id.tab_count)).setText("99+");
            }
        } else {
            TextView tab_count2 = (TextView) _$_findCachedViewById(R.id.tab_count);
            Intrinsics.checkNotNullExpressionValue(tab_count2, "tab_count");
            CommonExtKt.gone(tab_count2);
        }
        EventBus.getDefault().post(new OnNoticeCountEvent(i));
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.View
    public void showAgreement(UserAgreementModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.agreementModel = model;
        SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvNextBill)).append("Next Bill : ").setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white_30alpha)).setFontSize(10, true).append(model.getNext_billing_date()).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.color_main_yellow)).setFontSize(10, true).setUnderline().create();
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.View
    public void deviceFull() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, DeviceManagerActivity.class));
    }

    @Override // com.movieboxpro.android.view.fragment.userinfo.UserInfoContract.View
    public void showTestSpeedDialog(List<? extends NetTestModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        TestNetDialog testNetDialog = new TestNetDialog();
        testNetDialog.setNet(list).setNoOnclickListener($$Lambda$UserInfoFragment$1KLTI5kM_Js9OmySf7ZwPKzbSU.INSTANCE);
        testNetDialog.show(getChildFragmentManager(), "TestNetDialog");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 49374) {
                IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
                if (parseActivityResult.getContents() == null) {
                    ToastUtils.showShort("Scan Failed", new Object[0]);
                } else {
                    String result = parseActivityResult.getContents();
                    Intrinsics.checkNotNullExpressionValue(result, "result");
                    ((UserInfoPresenter) this.mPresenter).doTvLogin(result);
                }
            } else if (i == this.REQUEST_CODE_CHOOSE) {
                Uri data = intent == null ? null : intent.getData();
                this.outUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
                cropImage(data);
            } else if (i == 69) {
                uploadImage();
            } else if (i == this.REQUEST_CODE_CAMERA) {
                new IntentIntegrator(getActivity()).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setCameraId(0).setBeepEnabled(true).setCaptureActivity(QrCodeActivity.class).initiateScan();
            }
        }
        if (i == this.REQUEST_MODIFT_USERNAME && i2 == 1003 && intent != null) {
            ((TextView) _$_findCachedViewById(R.id.tvUsername)).setText(intent.getStringExtra("Username"));
        }
    }

    private final void uploadImage() {
        Uri uri = this.outUri;
        File file = new File(uri == null ? null : uri.getPath());
        if (file.exists()) {
            ((ObservableSubscribeProxy) Http.getService().uploadAvatar2(API.BASE_URL, buildData(), BuildConfig.APPLICATION_ID, App.versionCode, MultipartBody.Part.createFormData("imgupload", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file))).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoFragment$uploadImage$1
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    UserInfoFragment.this.showLoading();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(UserModel.UserData model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    UserInfoFragment.this.hideLoading();
                    model.avatar += '?' + TimeUtils.getCurrentTime();
                    App.updateUser(model.avatar);
                    GlideUtils.loadWithNoCache(UserInfoFragment.this.getContext(), model.avatar, (CircleImageView) UserInfoFragment.this._$_findCachedViewById(R.id.ivAvatar), R.drawable.ic_default_avatar);
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    UserInfoFragment.this.hideLoading();
                }
            });
        }
    }

    private final void cropImage(Uri uri) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        Intrinsics.checkNotNull(uri);
        Uri uri2 = this.outUri;
        Intrinsics.checkNotNull(uri2);
        UCrop.of(uri, uri2).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1000, 1000).start(context, this);
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

    private final String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "env", str);
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }
}
