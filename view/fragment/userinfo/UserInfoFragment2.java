package com.movieboxpro.android.view.fragment.userinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleFragment;
import com.movieboxpro.android.databinding.FragmentUserInfo2Binding;
import com.movieboxpro.android.event.OnNoticeCountEvent;
import com.movieboxpro.android.event.OnScanResultEvent;
import com.movieboxpro.android.model.NoticeCountModel;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.databinding.FragmentBindingDelegate;
import com.movieboxpro.android.utils.databinding.ReflectExtKt;
import com.movieboxpro.android.utils.resultcontract.ChooseImageResultContract;
import com.movieboxpro.android.view.activity.PermissionRequestActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.QrCodeActivity;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.viewmodel.UserInfoViewModel;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: UserInfoFragment2.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0016J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\"\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$H\u0007J\b\u0010%\u001a\u00020\u000bH\u0016J\b\u0010&\u001a\u00020\u000bH\u0016J\b\u0010'\u001a\u00020\u000bH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000e0\u000e0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, d2 = {"Lcom/movieboxpro/android/view/fragment/userinfo/UserInfoFragment2;", "Lcom/movieboxpro/android/base/BaseSimpleFragment;", "()V", ReflectExtKt.BIND_NAME, "Lcom/movieboxpro/android/databinding/FragmentUserInfo2Binding;", "getBind", "()Lcom/movieboxpro/android/databinding/FragmentUserInfo2Binding;", "bind$delegate", "Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", "imageResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "scanResultLauncher", "Landroid/content/Intent;", "uploadUri", "Landroid/net/Uri;", "viewModel", "Lcom/movieboxpro/android/viewmodel/UserInfoViewModel;", "getViewModel", "()Lcom/movieboxpro/android/viewmodel/UserInfoViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableEventBus", "", "hideLoadingPage", "initData", "initListener", "initView", "onActivityResult", "requestCode", "", "resultCode", "data", "onScanResult", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnScanResultEvent;", "showErrorPage", "showLoadingPage", "startScanActivity", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UserInfoFragment2 extends BaseSimpleFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(UserInfoFragment2.class, ReflectExtKt.BIND_NAME, "getBind()Lcom/movieboxpro/android/databinding/FragmentUserInfo2Binding;", 0))};
    public Map<Integer, View> _$_findViewCache;
    private final FragmentBindingDelegate bind$delegate;
    private final ActivityResultLauncher<Unit> imageResultLauncher;
    private final ActivityResultLauncher<Intent> scanResultLauncher;
    private Uri uploadUri;
    private final Lazy viewModel$delegate;

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
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

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment, com.movieboxpro.android.base.LoadView
    public void hideLoadingPage() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment, com.movieboxpro.android.base.LoadView
    public void showErrorPage() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment, com.movieboxpro.android.base.LoadView
    public void showLoadingPage() {
    }

    public UserInfoFragment2() {
        super(R.layout.fragment_user_info2);
        this._$_findViewCache = new LinkedHashMap();
        UserInfoFragment2 userInfoFragment2 = this;
        this.bind$delegate = new FragmentBindingDelegate(FragmentUserInfo2Binding.class, userInfoFragment2);
        UserInfoFragment2$special$$inlined$viewModels$default$1 userInfoFragment2$special$$inlined$viewModels$default$1 = new UserInfoFragment2$special$$inlined$viewModels$default$1(userInfoFragment2);
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(userInfoFragment2, Reflection.getOrCreateKotlinClass(UserInfoViewModel.class), new UserInfoFragment2$special$$inlined$viewModels$default$2(userInfoFragment2$special$$inlined$viewModels$default$1), new UserInfoFragment2$special$$inlined$viewModels$default$3(userInfoFragment2$special$$inlined$viewModels$default$1, userInfoFragment2));
        ActivityResultLauncher<Unit> registerForActivityResult = registerForActivityResult(new ChooseImageResultContract(), new ActivityResultCallback() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$84SczOnwoflId7uqjJQDfDYmQKU
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                UserInfoFragment2.m1305imageResultLauncher$lambda1(UserInfoFragment2.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…        }\n        }\n    }");
        this.imageResultLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$gaW6PPHVW4OqnRvrOEQs-1PMKk4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                UserInfoFragment2.m1313scanResultLauncher$lambda2(UserInfoFragment2.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…ctivity()\n        }\n    }");
        this.scanResultLauncher = registerForActivityResult2;
    }

    private final FragmentUserInfo2Binding getBind() {
        return (FragmentUserInfo2Binding) this.bind$delegate.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    private final UserInfoViewModel getViewModel() {
        return (UserInfoViewModel) this.viewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: imageResultLauncher$lambda-1  reason: not valid java name */
    public static final void m1305imageResultLauncher$lambda1(UserInfoFragment2 this$0, Uri uri) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.uploadUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
        if (uri == null || (context = this$0.getContext()) == null) {
            return;
        }
        Uri uri2 = this$0.uploadUri;
        Intrinsics.checkNotNull(uri2);
        UCrop.of(uri, uri2).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1000, 1000).start(context, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: scanResultLauncher$lambda-2  reason: not valid java name */
    public static final void m1313scanResultLauncher$lambda2(UserInfoFragment2 this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.startScanActivity();
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initView() {
        UserInfoFragment2 userInfoFragment2 = this;
        getViewModel().getNoticeCountModel().observe(userInfoFragment2, new Observer() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$i8xzwg89JUVKe2vcH2_ozOWqUog
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserInfoFragment2.m1309initView$lambda3(UserInfoFragment2.this, (NoticeCountModel) obj);
            }
        });
        getViewModel().getUserAgreementModel().observe(userInfoFragment2, new Observer() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$uqa1emKzF5MwN_jDFyyEOLi1MX4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                UserInfoFragment2.m1310initView$lambda4(UserInfoFragment2.this, (UserAgreementModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3  reason: not valid java name */
    public static final void m1309initView$lambda3(UserInfoFragment2 this$0, NoticeCountModel noticeCountModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (noticeCountModel.getCount() > 0) {
            TextView textView = this$0.getBind().tabCount;
            Intrinsics.checkNotNullExpressionValue(textView, "bind.tabCount");
            CommonExtKt.visible(textView);
            if (noticeCountModel.getCount() <= 99) {
                this$0.getBind().tabCount.setText(String.valueOf(noticeCountModel.getCount()));
            } else {
                this$0.getBind().tabCount.setText("99+");
            }
        } else {
            TextView textView2 = this$0.getBind().tabCount;
            Intrinsics.checkNotNullExpressionValue(textView2, "bind.tabCount");
            CommonExtKt.gone(textView2);
        }
        EventBus.getDefault().post(new OnNoticeCountEvent(noticeCountModel.getCount()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-4  reason: not valid java name */
    public static final void m1310initView$lambda4(UserInfoFragment2 this$0, UserAgreementModel userAgreementModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SpanUtils.with(this$0.getBind().tvNextBill).append("Next Bill : ").setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white_30alpha)).setFontSize(10, true).append(userAgreementModel.getNext_billing_date()).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.color_main_yellow)).setFontSize(10, true).setUnderline().create();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initData() {
        getViewModel().setLoadingView(this);
        getBind().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.movieboxpro.android.view.fragment.userinfo.UserInfoFragment2$initData$1
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
            }
        });
        getBind().setVm(getViewModel());
        getViewModel().getData();
    }

    private final void startScanActivity() {
        new IntentIntegrator(getActivity()).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setCameraId(0).setBeepEnabled(true).setCaptureActivity(QrCodeActivity.class).initiateScan();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onScanResult(OnScanResultEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        UserInfoViewModel viewModel = getViewModel();
        String result = event.getResult();
        Intrinsics.checkNotNullExpressionValue(result, "event.result");
        viewModel.doTvLogin(result);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initListener() {
        getBind().ivScan.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$u7wJGwNcyXDFGfvy2w5aziCm-gc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment2.m1306initListener$lambda7(UserInfoFragment2.this, view);
            }
        });
        getBind().ivAvatar.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$mTDgfqm69Xy8NB7I9HJ_bet9BQQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserInfoFragment2.m1308initListener$lambda9(UserInfoFragment2.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final void m1306initListener$lambda7(final UserInfoFragment2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this$0.getContext());
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment2$2unN6pr8gH_Ut4udZOmmqedj1Tk
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    UserInfoFragment2.m1307initListener$lambda7$lambda6(UserInfoFragment2.this);
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
            this$0.startScanActivity();
        } else {
            this$0.scanResultLauncher.launch(PermissionRequestActivity.Companion.startIntent(this$0, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7$lambda-6  reason: not valid java name */
    public static final void m1307initListener$lambda7$lambda6(UserInfoFragment2 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        InputChildModePasswordActivity.Companion.start(activity, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m1308initListener$lambda9(UserInfoFragment2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            this$0.imageResultLauncher.launch(null);
            return;
        }
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        Login2Activity.start(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        String path;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 69) {
            UserInfoViewModel viewModel = getViewModel();
            Uri uri = this.uploadUri;
            String str = "";
            if (uri != null && (path = uri.getPath()) != null) {
                str = path;
            }
            viewModel.updateAvatar(new File(str));
        }
    }
}
