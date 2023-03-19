package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnTextChanged;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.event.OnChangeNameEmailEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes.dex */
public class ModifyEmail extends BaseActivity {
    private String email;
    @BindView(R.id.modify_email_name)
    TextView hintName;
    @BindView(R.id.modify_email_text)
    EditText modifyEmailText;
    private String username;

    public static void start(int i, Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, ModifyEmail.class);
        intent.putExtra("email", str);
        intent.putExtra("username", str2);
        activity.startActivityForResult(intent, i);
    }

    public static void start(int i, Fragment fragment, String str, String str2) {
        Intent intent = new Intent(fragment.getContext(), ModifyEmail.class);
        intent.putExtra("email", str);
        intent.putExtra("username", str2);
        fragment.startActivityForResult(intent, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged({R.id.modify_email_text})
    public void onTextChanged(CharSequence charSequence) {
        if (TextUtils.isEmpty(this.email)) {
            return;
        }
        if (SystemUtils.isEmail(this.modifyEmailText.getText().toString())) {
            this.mTitleRightText.setTextColor(getResources().getColor(R.color.white));
            this.mTitleRightText.setClickable(true);
            return;
        }
        this.mTitleRightText.setTextColor(getResources().getColor(R.color.text_hint));
        this.mTitleRightText.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_modifyemail, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleRightText("Commit", new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.ModifyEmail.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(ModifyEmail.this.email)) {
                    if (SystemUtils.isEmail(ModifyEmail.this.modifyEmailText.getText().toString())) {
                        ModifyEmail.this.setData();
                        return;
                    } else {
                        ModifyEmail.this.showToast("Please checkup Your EmailAddress");
                        return;
                    }
                }
                ModifyEmail.this.setData();
            }
        });
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        this.email = getIntent().getStringExtra("email");
        this.username = getIntent().getStringExtra("username");
        if (!TextUtils.isEmpty(this.email)) {
            setTitle("Modify Email");
            this.hintName.setText("New Email");
            this.modifyEmailText.setText(this.email);
            this.modifyEmailText.setSelection(this.email.length());
            this.mTitleRightText.setTextColor(getResources().getColor(R.color.text_hint));
        } else {
            setTitle("Modify Username");
            this.hintName.setText("New Username");
            this.modifyEmailText.setHint("Enter Username");
            this.modifyEmailText.setText(this.username);
            this.modifyEmailText.setSelection(this.username.length());
        }
        this.modifyEmailText.requestFocus();
        this.modifyEmailText.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.settings.ModifyEmail.2
            @Override // java.lang.Runnable
            public void run() {
                InputMethodUtils.showSoftInput(ModifyEmail.this.modifyEmailText);
            }
        }, 100L);
    }

    public void setData() {
        Observable<String> userprofile;
        if (App.isLogin()) {
            showLoading();
            if (!TextUtils.isEmpty(this.email)) {
                userprofile = this.service.userprofile(API.BASE_URL, API.USER.PROFILE, App.getUserData().uid_v2, "", "", "", "", "", this.modifyEmailText.getText().toString(), BuildConfig.VERSION_NAME);
            } else {
                userprofile = this.service.userprofile(API.BASE_URL, API.USER.PROFILE, App.getUserData().uid_v2, "", this.modifyEmailText.getText().toString(), "", "", this.modifyEmailText.getText().toString(), "", BuildConfig.VERSION_NAME);
            }
            ((ObservableSubscribeProxy) userprofile.compose(RxUtils.rxTranslate2Bean(HashMap.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<HashMap>() { // from class: com.movieboxpro.android.view.activity.settings.ModifyEmail.3
                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    RxManager.addDisposable(ModifyEmail.this.TAG, disposable);
                }

                @Override // io.reactivex.Observer
                public void onNext(HashMap hashMap) {
                    ModifyEmail.this.showToast("Success");
                    if (!TextUtils.isEmpty(ModifyEmail.this.email)) {
                        ModifyEmail.this.setResult(1003, "Email", (String) hashMap.get("email"));
                        App.updateUser((String) hashMap.get("email"), 0);
                    } else {
                        ModifyEmail.this.setResult(1003, "Username", (String) hashMap.get("username"));
                        App.updateUsername((String) hashMap.get("username"), 0);
                    }
                    EventBus.getDefault().post(new OnChangeNameEmailEvent());
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    ModifyEmail.this.hideLoading();
                    ToastUtils.showShort(th.getMessage());
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    ModifyEmail.this.hideLoading();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        InputMethodUtils.hideSoftInput(this.modifyEmailText);
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }
}
