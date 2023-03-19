package com.movieboxpro.android.view.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.settings.DeviceManagerActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.InvateDialog;
import com.movieboxpro.android.view.dialog.LoginCodeShowDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class Login2Activity extends BaseActivity {
    public static final int RC_SIGN_IN = 1000;
    private String authCode = "";
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @OnClick({R.id.llSignIn})
    public void onViewClicked() {
        signIn();
    }

    @OnClick({R.id.llLoginCode})
    public void onLoginCode() {
        LoginCodeShowDialog loginCodeShowDialog = new LoginCodeShowDialog();
        loginCodeShowDialog.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$6cPX7udOVNKv7X7dFQErbG2hBgI
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                Login2Activity.this.finish();
            }
        });
        loginCodeShowDialog.show(getSupportFragmentManager(), LoginCodeShowDialog.class.getSimpleName());
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, Login2Activity.class));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_login_2, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        checkTvDevice();
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestServerAuthCode("939879918880-u1qd4ku6k79501j09i65roc4a428kita.apps.googleusercontent.com").requestIdToken("939879918880-u1qd4ku6k79501j09i65roc4a428kita.apps.googleusercontent.com").build());
    }

    private void checkTvDevice() {
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature("android.software.leanback") || !(packageManager.hasSystemFeature("org.chromium.arc.device_management") || packageManager.hasSystemFeature("android.hardware.touchscreen"))) {
            findViewById(R.id.tvTvHint).setVisibility(0);
        }
    }

    private void signIn() {
        showLoading();
        startActivityForResult(this.mGoogleSignInClient.getSignInIntent(), 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(boolean z, final UserModel.UserData userData) {
        if (z) {
            showLoading();
        }
        this.service.loginthirdpart(API.BASE_URL, "Login_google", this.authCode, SystemUtils.getUniqueId(App.getContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                String str2 = Login2Activity.this.TAG;
                MLog.d(str2, "登录login_thirdpart : " + str);
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                int intValue = jSONObject.getInteger("code").intValue();
                if (intValue == 0) {
                    Login2Activity.this.CheckNeedInvate(userData);
                } else if (intValue == 2) {
                    Login2Activity.this.hideLoading();
                    Login2Activity.this.showToast(jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE));
                    String str3 = Login2Activity.this.TAG;
                    MLog.d(str3, "登录 : " + str);
                    DeviceManagerActivity.start(Login2Activity.this, jSONObject.getJSONObject("data").toJSONString());
                } else if (intValue == -88) {
                    Login2Activity.this.showInvate2(userData);
                    Login2Activity.this.hideLoading();
                } else if (intValue == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    UserModel userModel = new UserModel();
                    userModel.member = (UserModel.UserData) jSONObject2.toJavaObject((Class<Object>) UserModel.UserData.class);
                    String str4 = Login2Activity.this.TAG;
                    MLog.d(str4, "登录 : " + userModel.member.uid_v2);
                    Login2Activity.this.updateDevice(userModel, userModel.member.uid_v2);
                } else {
                    Login2Activity.this.loginout();
                    Login2Activity.this.showToast(jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE));
                    Login2Activity.this.hideLoading();
                    Login2Activity.this.findViewById(R.id.llLoginCode).setVisibility(0);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed" + th.getMessage());
                Login2Activity.this.findViewById(R.id.llLoginCode).setVisibility(0);
            }
        });
    }

    private String getBBSApiAPPID() {
        return String.format(Locale.CHINA, "%d%s", Long.valueOf(TimeUtils.getCurrentTime() / 1000), HttpUtils.md5("27"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDevice(final UserModel userModel, String str) {
        showLoading();
        APIService aPIService = this.service;
        String str2 = API.BASE_URL;
        String oSType = SystemUtils.getOSType();
        String str3 = Build.MODEL;
        String uniqueId = SystemUtils.getUniqueId(App.getContext());
        ((ObservableSubscribeProxy) aPIService.Device(str2, API.USER.DEVICE, "", str, oSType, str3, "", "", uniqueId, BuildConfig.APPLICATION_ID, (TimeUtils.getCurrentTime() / 1000) + "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                String str5 = Login2Activity.this.TAG;
                MLog.d(str5, "登录updateDevice : " + str4);
                App.login(userModel);
                EventBus.getDefault().post(new LoginEvent());
                Login2Activity.this.route(MainActivity.class);
                Login2Activity.this.finish();
                Login2Activity.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed:" + th.getMessage());
            }
        });
    }

    public void CheckNeedInvate(final UserModel.UserData userData) {
        ((ObservableSubscribeProxy) this.service.Register_NeedCode(API.BASE_URL, API.USER.REGISTER_CODE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                String str2 = Login2Activity.this.TAG;
                MLog.d(str2, "邀请码测试 : " + str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    if (jSONObject.getJSONObject("data").getBoolean("invite_code").booleanValue()) {
                        Login2Activity.this.showInvate(userData);
                        return;
                    } else {
                        Login2Activity.this.CheckUserName(userData, "");
                        return;
                    }
                }
                Login2Activity.this.loginout();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed:" + th.getMessage());
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("LoginActivity", "CheckNeedInvate" + th.getMessage());
            }
        });
    }

    public void CheckUserName(final UserModel.UserData userData, final String str) {
        ((ObservableSubscribeProxy) this.service.Recommend_username(API.BASE_URL, API.USER.RECOMMEND_USERNAME, userData.name, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str2);
                String str3 = Login2Activity.this.TAG;
                MLog.d(str3, "登录 : " + str2);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    userData.name = jSONObject2.getString("username");
                    Login2Activity.this.register(userData, str);
                    return;
                }
                Login2Activity.this.loginout();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed:" + th.getMessage());
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("LoginActivity", "CheckUserName" + th.getMessage());
                Login2Activity.this.findViewById(R.id.llLoginCode).setVisibility(0);
            }
        });
    }

    public void register(final UserModel.UserData userData, String str) {
        showLoading();
        ((ObservableSubscribeProxy) this.service.Register2(API.BASE_URL, API.USER.REGISTER2, userData.name, "123456", userData.email, str, BuildConfig.VERSION_NAME, "google", "", userData.name, userData.name, userData.id, this.authCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.5
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str2);
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                String str3 = Login2Activity.this.TAG;
                MLog.d(str3, "登录 : " + str2);
                int intValue = jSONObject.getInteger("code").intValue();
                if (intValue == 1) {
                    userData.uid_v2 = jSONObject2.getString("uid");
                    Login2Activity.this.uploadAvatar(userData);
                    Login2Activity login2Activity = Login2Activity.this;
                    login2Activity.login(userData, login2Activity.authCode);
                    return;
                }
                Login2Activity.this.loginout();
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Register failed:" + jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE) + " code:" + intValue);
                Login2Activity.this.findViewById(R.id.llLoginCode).setVisibility(0);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed:" + th.getMessage());
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("LoginActivity", "register" + th.getMessage());
            }
        });
    }

    private String buildData(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) API.USER.PROFILE);
        jSONObject.put(IjkMediaMeta.IJKM_KEY_TYPE, (Object) "upload");
        jSONObject.put("uid", (Object) str);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("upload_avatar", (Object) str2);
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadAvatar(UserModel.UserData userData) {
        if (TextUtils.isEmpty(userData.avatar) || "null".equals(userData.avatar)) {
            return;
        }
        Http.getService().uploadAvatar2(API.BASE_URL, buildData(userData.uid_v2, userData.avatar), BuildConfig.APPLICATION_ID, App.versionCode, MultipartBody.Part.createFormData("imgupload", "", RequestBody.create(MediaType.parse("image/jpg"), ""))).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.6
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData userData2) {
                LogUtils.INSTANCE.logD("LoginActivity", "");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                LogUtils.INSTANCE.logD("LoginActivity", "");
            }
        });
    }

    public void login(final UserModel.UserData userData, String str) {
        showLoading();
        ((ObservableSubscribeProxy) this.service.login(API.BASE_URL, "Login_google", userData.name, "123456", SystemUtils.getUniqueId(this.context), str, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str2);
                int intValue = jSONObject.getInteger("code").intValue();
                if (intValue == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    UserModel userModel = new UserModel();
                    userModel.member = (UserModel.UserData) jSONObject2.toJavaObject((Class<Object>) UserModel.UserData.class);
                    String str3 = Login2Activity.this.TAG;
                    MLog.d(str3, "登录 : " + userModel.member.uid_v2);
                    Login2Activity.this.updateDevice(userModel, userModel.member.uid_v2);
                } else if (intValue == -88) {
                    Login2Activity.this.showInvate2(userData);
                } else {
                    Login2Activity.this.loginout();
                    Login2Activity.this.hideLoading();
                    Login2Activity.this.showToast(jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE));
                    String str4 = Login2Activity.this.TAG;
                    MLog.d(str4, "登录 : " + str2);
                    DeviceManagerActivity.start(Login2Activity.this, jSONObject.getJSONObject("data").toJSONString());
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                ToastUtils.showShort("Login failed:" + th.getMessage());
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("LoginActivity", FirebaseAnalytics.Event.LOGIN + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInvate2(final UserModel.UserData userData) {
        InvateDialog invateDialog = new InvateDialog(this.activity);
        invateDialog.setNoOnclickListener(new InvateDialog.onNoOnclickListener() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.8
            @Override // com.movieboxpro.android.view.dialog.InvateDialog.onNoOnclickListener
            public void onNoClick(String str) {
                Login2Activity.this.Invite_active(userData, str);
            }
        });
        invateDialog.show();
    }

    public void Invite_active(final UserModel.UserData userData, String str) {
        ((ObservableSubscribeProxy) this.service.Invite_active(API.BASE_URL, API.USER.INVITE_ACTIVE, "google", userData.email, str, userData.id, this.authCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.9
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str2);
                String str3 = Login2Activity.this.TAG;
                MLog.d(str3, "登录bindthirdpart : " + str2);
                int intValue = jSONObject.getInteger("code").intValue();
                String string = jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                if (intValue == 1) {
                    Login2Activity.this.setData(false, userData);
                    return;
                }
                Login2Activity.this.loginout();
                Login2Activity.this.showToast(string);
                Login2Activity.this.hideLoading();
            }
        });
    }

    public void bindThrid2(final UserModel.UserData userData) {
        ((ObservableSubscribeProxy) this.service.bindthirdpart(API.BASE_URL, API.USER.BIND_THIRDPART, userData.uid_v2, "add", "google", "", userData.name, userData.id, userData.nickname, userData.email, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(Login2Activity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                String str2 = Login2Activity.this.TAG;
                MLog.d(str2, "登录bindthirdpart : " + str);
                if (((JSONObject) JSONObject.parse(str)).getInteger("code").intValue() == 1) {
                    Login2Activity.this.login(userData, "");
                } else {
                    Login2Activity.this.loginout();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                Login2Activity.this.hideLoading();
                LogUtils logUtils = LogUtils.INSTANCE;
                logUtils.logD("LoginActivity", "bindThrid2" + th.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        if (t instanceof GoogleSignInAccount) {
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) t;
            UserModel.UserData userData = new UserModel.UserData();
            userData.name = googleSignInAccount.getDisplayName();
            userData.email = googleSignInAccount.getEmail();
            userData.id = googleSignInAccount.getId();
            this.authCode = googleSignInAccount.getIdToken();
            userData.avatar = String.valueOf(googleSignInAccount.getPhotoUrl());
            String str = this.TAG;
            MLog.d(str, "登录 ：" + userData.name + "," + userData.id + "," + googleSignInAccount.getIdToken() + "," + userData.email + "," + userData.avatar);
            setData(true, userData);
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = this.TAG;
        MLog.d(str, "登录 ：" + i);
        if (i == 1000) {
            handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            updateView(task.getResult(com.google.android.gms.common.api.ApiException.class));
        } catch (com.google.android.gms.common.api.ApiException e) {
            String str = this.TAG;
            MLog.d(str, "signInResult:failed code=" + e.toString());
            showToast(GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode()));
            hideLoading();
            findViewById(R.id.llLoginCode).setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInvate(final UserModel.UserData userData) {
        final InvateDialog invateDialog = new InvateDialog(this.activity);
        invateDialog.setNoOnclickListener(new InvateDialog.onNoOnclickListener() { // from class: com.movieboxpro.android.view.activity.user.Login2Activity.11
            @Override // com.movieboxpro.android.view.dialog.InvateDialog.onNoOnclickListener
            public void onNoClick(String str) {
                Login2Activity.this.CheckUserName(userData, str);
                invateDialog.dismiss();
            }
        });
        invateDialog.show();
    }

    public void loginout() {
        GoogleSignInClient googleSignInClient = this.mGoogleSignInClient;
        if (googleSignInClient == null) {
            return;
        }
        googleSignInClient.signOut();
    }
}
