package com.movieboxpro.android.view.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.BaseResponse;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.StringCallBack;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.settings.DeviceActivity;
import com.movieboxpro.android.view.activity.settings.ModifyEmail;
import com.movieboxpro.android.view.activity.settings.OrderActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileNotFoundException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class UserDetailActivity extends BaseActivity {
    private static final int REQUEST_CODE_CHOOSE = 1001;
    private static final int REQUEST_CODE_CROP = 1002;
    public static final int REQUEST_CODE_EMAIL = 1003;
    public static final int REQUEST_CODE_RQ = 49374;
    public static final int REQUEST_CODE_USERNAME = 1004;
    GoogleSignInClient mGoogleSignInClient;
    private Uri outUri;
    @BindView(R.id.user_detail_avatar)
    CircleImageView userDetailAvatar;
    @BindView(R.id.user_detail_describ)
    TextView userDetailDescrib;
    @BindView(R.id.user_invate_code_describ)
    TextView userDeviceInvate;
    @BindView(R.id.user_device_myEail)
    TextView userDeviceMyEail;
    @BindView(R.id.user_invate_code)
    ConstraintLayout userInvate;
    @BindView(R.id.user_detail_more)
    TextView userUserVipTime;
    @BindView(R.id.user_username_more)
    TextView userUsernameMore;
    @BindView(R.id.user_detail_vip)
    ImageView userVip;

    @OnClick({R.id.user_invate_code, R.id.user_detail_avatar, R.id.user_order, R.id.user_email, R.id.user_device, R.id.user_detail_signout, R.id.user_detail_more, R.id.user_detail_describ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_detail_avatar /* 2131298359 */:
                startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Choose"), 1001);
                return;
            case R.id.user_detail_describ /* 2131298360 */:
                ModifyEmail.start(1004, this, "", this.userDetailDescrib.getText().toString());
                return;
            case R.id.user_detail_signout /* 2131298362 */:
                if (App.isLogin()) {
                    logout(false, SystemUtils.getUniqueId(App.getContext()));
                    this.mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            UserDetailActivity.this.route(MainActivity.class);
                        }
                    });
                    return;
                }
                return;
            case R.id.user_device /* 2131298364 */:
                route(DeviceActivity.class);
                return;
            case R.id.user_email /* 2131298369 */:
                ModifyEmail.start(1003, this, this.userDeviceMyEail.getText().toString(), "");
                return;
            case R.id.user_invate_code /* 2131298372 */:
                Context context = App.getContext();
                SystemUtils.startBrowser(context, "https://www.movieboxpro.app/index/invite/code?auth=" + buildData(SystemUtils.getMsg(this.activity)));
                return;
            case R.id.user_order /* 2131298375 */:
                route(OrderActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_user_detail, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        if (App.isLogin()) {
            UserModel.UserData userData = App.getUserData();
            GlideApp.with(this.activity).load(userData.avatar).error((int) R.drawable.ic_default_avatar).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.userDetailAvatar);
            this.userDetailDescrib.setText(userData.username);
            this.userUsernameMore.setText(userData.nickname);
            this.userDeviceMyEail.setText(userData.email);
            if (userData.isvip == 1) {
                TextView textView = this.userUserVipTime;
                textView.setText("Expire Date :  " + TimeUtils.formatTime3(userData.dead_time * 1000));
                this.userVip.setVisibility(0);
            }
            loadData();
        } else {
            finish();
        }
        setTitle("User");
        setTitleRightIcon(R.drawable.ic_scan, new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new IntentIntegrator(UserDetailActivity.this.activity).setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES).setCameraId(0).setBeepEnabled(true).setCaptureActivity(QrCodeActivity.class).initiateScan();
            }
        });
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build());
    }

    private void loadData() {
        hideLoading();
        this.service.Userinfo(API.BASE_URL, API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this.context), BuildConfig.VERSION_NAME).enqueue(new StringCallBack<UserModel.UserData>(this, UserModel.UserData.class) { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.3
            @Override // com.movieboxpro.android.http.StringCallBack
            public boolean onParsed(BaseResponse<UserModel.UserData> baseResponse) {
                String str = UserDetailActivity.this.TAG;
                MLog.d(str, "登录状态 ： " + baseResponse.code + "!!!");
                if (baseResponse.code >= 1000 && baseResponse.code < 2000) {
                    UserModel.UserData userData = baseResponse.data;
                    String str2 = UserDetailActivity.this.TAG;
                    MLog.d(str2, "登录状态 ： " + userData.invite_code_count);
                    App.updateUser(userData);
                    TextView textView = UserDetailActivity.this.userDeviceInvate;
                    textView.setText(userData.invite_code_count + "");
                    return false;
                }
                App.logout();
                UserDetailActivity.this.route(Login2Activity.class);
                UserDetailActivity.this.finish();
                return false;
            }
        });
    }

    private String buildData() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) API.USER.PROFILE);
        jSONObject.put(IjkMediaMeta.IJKM_KEY_TYPE, (Object) "upload");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    private String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("env", (Object) str);
        jSONObject.put("uid", (Object) (App.isLogin() ? App.getUserData().uid_v2 : ""));
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    private void uploadAvatar() {
        if (App.isLogin() && this.service != null) {
            String path = this.outUri.getPath();
            File file = new File(path);
            String str = this.TAG;
            MLog.d(str, "SSSS" + path);
            if (file.exists()) {
                Call<String> uploadAvatar = this.service.uploadAvatar(API.BASE_URL, buildData(), BuildConfig.APPLICATION_ID, App.versionCode, MultipartBody.Part.createFormData("imgupload", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file)));
                CallManager.add(getClass().getSimpleName(), uploadAvatar);
                uploadAvatar.enqueue(new StringCallBack<UserModel.UserData>(this, UserModel.UserData.class) { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.4
                    @Override // com.movieboxpro.android.http.StringCallBack
                    public boolean onParsed(BaseResponse<UserModel.UserData> baseResponse) {
                        UserModel.UserData userData;
                        UserDetailActivity.this.showToast(baseResponse.msg);
                        if (baseResponse.code < 1000 || baseResponse.code >= 2000 || baseResponse.data == null) {
                            return false;
                        }
                        userData.avatar += "?" + TimeUtils.getCurrentTime();
                        App.updateUser(baseResponse.data.avatar);
                        return true;
                    }
                });
            }
        }
    }

    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("output", this.outUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", false);
        startActivityForResult(Intent.createChooser(intent, "Choose"), 1002);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = this.TAG;
        MLog.d(str, "收到" + i + i2);
        if (i2 != -1) {
            if (i == 1003) {
                if (App.isLogin()) {
                    this.userDeviceMyEail.setText(getStringResult(intent, "Email"));
                }
            } else if (i == 1004 && App.isLogin()) {
                this.userDetailDescrib.setText(getStringResult(intent, "Username"));
            }
        } else if (i == 1001) {
            Uri data = intent.getData();
            this.outUri = Uri.parse("file:///" + Constant.DIR_CROP + File.separator + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
            cropImage(data);
        } else if (i == 1002) {
            if (this.outUri != null) {
                try {
                    this.userDetailAvatar.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(this.outUri)));
                    uploadAvatar();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (i == 49374 && App.isLogin()) {
            IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
            String str2 = this.TAG;
            MLog.d(str2, "扫码结果：" + parseActivityResult.getContents());
            if (parseActivityResult.getContents() == null) {
                showToast("Fail");
                return;
            }
            String contents = parseActivityResult.getContents();
            String str3 = this.TAG;
            MLog.d(str3, "扫码结果：" + contents);
            scanedResult(contents);
        }
    }

    public void scanedResult(String str) {
        if (Network.isConnected(this.context)) {
            this.service.Scan_qrcode(API.BASE_URL, API.Common.SCANQRCODE, App.getUserData().uid_v2, str, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.5
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    RxManager.addDisposable(UserDetailActivity.this.TAG, disposable);
                }

                @Override // io.reactivex.Observer
                public void onNext(String str2) {
                    UserDetailActivity.this.showToast(((JSONObject) JSONObject.parse(str2)).getString(NotificationCompat.CATEGORY_MESSAGE));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }

    private void logout(boolean z, String str) {
        if (z) {
            showLoading();
        }
        this.service.login_out(API.BASE_URL, API.USER.LOGIN_OUT, str, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(UserDetailActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
                String str3 = UserDetailActivity.this.TAG;
                MLog.d(str3, "登录login_thirdpart : " + str2);
                if (((JSONObject) JSONObject.parse(str2)).getInteger("code").intValue() == 1) {
                    App.logout();
                    EventBus.getDefault().post(new LogoutEvent());
                }
            }
        });
    }
}
