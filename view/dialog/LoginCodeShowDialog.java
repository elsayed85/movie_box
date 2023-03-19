package com.movieboxpro.android.view.dialog;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.databinding.DialogLoginCodeShowBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.ResponseKtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: LoginCodeShowDialog.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0002J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0016J\b\u0010 \u001a\u00020\u000fH\u0016J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\"\u001a\u00020\u000fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/view/dialog/LoginCodeShowDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/DialogLoginCodeShowBinding;", "code", "Landroidx/databinding/ObservableField;", "", "getCode", "()Landroidx/databinding/ObservableField;", "dispose", "Lio/reactivex/disposables/Disposable;", "loginListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "bindDevice", "", "userData", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "checkCodeLogin", "initData", "initListener", "initView", "loadCode", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "setListener", "startTimer", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginCodeShowDialog extends BaseBindingBottomDialogFragment {
    private DialogLoginCodeShowBinding binding;
    private Disposable dispose;
    private DialogAction.ActionListener loginListener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ObservableField<String> code = new ObservableField<>();

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
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

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final ObservableField<String> getCode() {
        return this.code;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_login_code_show, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…e_show, container, false)");
        DialogLoginCodeShowBinding dialogLoginCodeShowBinding = (DialogLoginCodeShowBinding) inflate;
        this.binding = dialogLoginCodeShowBinding;
        if (dialogLoginCodeShowBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogLoginCodeShowBinding = null;
        }
        View root = dialogLoginCodeShowBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    public final void setListener(DialogAction.ActionListener loginListener) {
        Intrinsics.checkNotNullParameter(loginListener, "loginListener");
        this.loginListener = loginListener;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        DialogLoginCodeShowBinding dialogLoginCodeShowBinding = this.binding;
        if (dialogLoginCodeShowBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogLoginCodeShowBinding = null;
        }
        dialogLoginCodeShowBinding.tvTry.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$LoginCodeShowDialog$U1HX4yBcySIYJnVV-vua29fsJ6U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginCodeShowDialog.m1041initListener$lambda0(LoginCodeShowDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1041initListener$lambda0(LoginCodeShowDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loadCode();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        DialogLoginCodeShowBinding dialogLoginCodeShowBinding = this.binding;
        if (dialogLoginCodeShowBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogLoginCodeShowBinding = null;
        }
        dialogLoginCodeShowBinding.setDialog(this);
        loadCode();
    }

    private final void loadCode() {
        ResponseKtKt.requestApi(LifecycleOwnerKt.getLifecycleScope(this), HashMap.class, new LoginCodeShowDialog$loadCode$1(this));
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Disposable disposable = this.dispose;
        if (disposable == null) {
            return;
        }
        disposable.dispose();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        startTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimer() {
        String str = this.code.get();
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
        Object as = Observable.interval(0L, 3L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "interval(0, 3, TimeUnit.…bindLifecycleOwner(this))");
        this.dispose = RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, new LoginCodeShowDialog$startTimer$1(this), null, new LoginCodeShowDialog$startTimer$2(this), 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkCodeLogin() {
        Object as = HttpRequest.Companion.post("Login_captcha_v2").param("openudid", SystemUtils.getUniqueId(App.getContext())).param("code", this.code.get()).asRequest().map($$Lambda$LoginCodeShowDialog$t4FgqrX6EqAbAuaQFwtpG0BdsU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Login_…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, LoginCodeShowDialog$checkCodeLogin$2.INSTANCE, null, null, null, new LoginCodeShowDialog$checkCodeLogin$3(this), 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkCodeLogin$lambda-1  reason: not valid java name */
    public static final BaseResponse m1040checkCodeLogin$lambda1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (BaseResponse) JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, String.class), new Feature[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindDevice(UserModel.UserData userData) {
        Object as = Http.getService().Device(API.BASE_URL, API.USER.DEVICE, "", userData.uid_v2, SystemUtils.getOSType(), Build.MODEL, "", "", SystemUtils.getUniqueId(App.getContext()), BuildConfig.APPLICATION_ID, String.valueOf(TimeUtils.getCurrentTime() / 1000), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService().Device(API.…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new LoginCodeShowDialog$bindDevice$1(this), null, new LoginCodeShowDialog$bindDevice$2(this), null, new LoginCodeShowDialog$bindDevice$3(this, userData), 10, null);
    }
}
