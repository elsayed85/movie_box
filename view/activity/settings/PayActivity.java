package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.android.app.GlideRequest;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.settings.VipPlanActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.uber.autodispose.ObservableSubscribeProxy;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class PayActivity extends BaseActivity {
    @BindView(R.id.ivAvatar)
    CircleImageView avatar;
    @BindView(R.id.vip_payinfo)
    ConstraintLayout constraintLayout;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.ivCardBg)
    ImageView ivCardBg;
    @BindView(R.id.ivVip)
    ImageView ivVip;
    @BindView(R.id.rvUidNum)
    RecyclerView rvUidNum;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvExpireDate)
    TextView tvExpireDate;
    @BindView(R.id.tvNextBill)
    TextView tvNextBill;
    @BindView(R.id.tvUsername)
    TextView tvUserName;
    private UserAgreementModel userAgreementModel;
    @BindView(R.id.vip_describ_purchase)
    ImageView vipPurchase;

    private void loadData() {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isDialogStyle() {
        return true;
    }

    @OnClick({R.id.tvUsername, R.id.tvEmail, R.id.ivAvatar})
    public void onLoginClick() {
        if (App.isLogin()) {
            return;
        }
        Login2Activity.start(this);
    }

    @OnClick({R.id.vip_describ_purchase})
    public void click() {
        if (!App.isLogin()) {
            route(Login2Activity.class);
            return;
        }
        CommonUtils.onEvent("ClickPurchaseButton");
        EventUtils.event("点击购买按钮");
        Activity activity = this.activity;
        SystemUtils.startBrowser(activity, "http://www.movieboxpro.app/index/pay&auth=" + buildData(""));
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, PayActivity.class));
    }

    private String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", (Object) (App.isLogin() ? App.getUserData().uid_v2 : ""));
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        String decodeBody = HttpUtils.decodeBody("eyJhcHBfa2V5IjoiNjQwNWMyZTYwNDVmNjU5YjdmZGNkOTU3ZmU1MmZlOWEiLCJlbmNyeXB0X2RhdGEiOiJWQUtzQ1JJY0k4NGFBQWgzV3UrXC9kcU1hY2lUNGMxNHFaUnAza0F4RzNVRTJlVDcwTFB4QUZ4ZE9KejhGMGRaSiIsInZlcmlmeSI6ImIzYTJmZWE2ZGRmYzBhYzgzMTQ3OTc3Njg1MGFmN2ZkIn0=", CipherKeys.getCiperKeys());
        String str2 = this.TAG;
        MLog.d(str2, "信息" + decodeBody);
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_pay, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        GlideUtils.load((Activity) this, (int) R.drawable.ic_vip_card, this.ivCardBg);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (CommonUtils.isTablet()) {
            this.frameLayout.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$PayActivity$oprQ23k0aGiARd-WoaNaJC56DeQ
                @Override // java.lang.Runnable
                public final void run() {
                    PayActivity.this.lambda$initData$0$PayActivity();
                }
            });
        } else {
            this.frameLayout.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$PayActivity$-IVdxc_9vDLjIWRfJ0WdypRBU8A
                @Override // java.lang.Runnable
                public final void run() {
                    PayActivity.this.lambda$initData$1$PayActivity();
                }
            });
        }
        this.constraintLayout.setBackgroundResource(R.drawable.bg_round_btn);
        GlideUtils.load((Activity) this, (int) R.drawable.purchase, this.vipPurchase);
        if (App.isLogin()) {
            UserModel.UserData userData = App.getUserData();
            GlideUtils.load((Activity) this, userData.avatar, (ImageView) this.avatar);
            this.tvUserName.setText(userData.username);
            this.tvEmail.setText(userData.email);
            if (userData.isvip == 1) {
                this.ivVip.setVisibility(0);
                this.tvExpireDate.setText(String.format("Expire: %s", TimeUtils.formatTime3(userData.dead_time * 1000)));
            }
            ArrayList arrayList = new ArrayList();
            if (userData.getUid_v2() != null) {
                for (int i = 0; i < userData.getUid_v2().length(); i++) {
                    arrayList.add(String.valueOf(userData.getUid_v2().charAt(i)));
                }
            }
            BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_uid_num_item, arrayList) { // from class: com.movieboxpro.android.view.activity.settings.PayActivity.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, String str) {
                    baseViewHolder.setText(R.id.textView, str);
                }
            };
            this.rvUidNum.setLayoutManager(new LinearLayoutManager(this, 0, false));
            this.rvUidNum.addItemDecoration(new LayoutManagerItemDecoration(0, 20));
            this.rvUidNum.setAdapter(baseQuickAdapter);
        } else {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_no_login_avatar, (ImageView) this.avatar);
            this.tvUserName.setText("Sign In");
            this.tvEmail.setText("with Google account");
        }
        this.tvNextBill.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VipPlanActivity.Companion companion = VipPlanActivity.Companion;
                PayActivity payActivity = PayActivity.this;
                companion.start(payActivity, payActivity.userAgreementModel);
            }
        });
        if (App.isLogin()) {
            ((ObservableSubscribeProxy) this.service.User_agreement(API.BASE_URL, "User_agreement", App.getUserData().uid_v2).compose(RxUtils.rxTranslate2Bean(UserAgreementModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserAgreementModel>() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity.5
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                    PayActivity.this.showLoading();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(UserAgreementModel userAgreementModel) {
                    PayActivity.this.userAgreementModel = userAgreementModel;
                    SpanUtils.with(PayActivity.this.tvNextBill).append("Next Bill : ").setForegroundColor(ContextCompat.getColor(PayActivity.this, R.color.white_30alpha)).setFontSize(10, true).append(userAgreementModel.getNext_billing_date()).setForegroundColor(ContextCompat.getColor(PayActivity.this, R.color.color_main_yellow)).setFontSize(10, true).setUnderline().create();
                    PayActivity.this.hideLoading();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException apiException) {
                    PayActivity.this.hideLoading();
                    PayActivity.this.tvNextBill.setVisibility(8);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initData$0$PayActivity() {
        GlideApp.with((FragmentActivity) this).asDrawable().load(Integer.valueOf((int) R.drawable.vip_reswar_land_bg)).dontAnimate().into((GlideRequest<Drawable>) new SimpleTarget<Drawable>(this.frameLayout.getWidth(), this.frameLayout.getHeight()) { // from class: com.movieboxpro.android.view.activity.settings.PayActivity.1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                PayActivity.this.frameLayout.setBackground(drawable);
            }
        });
    }

    public /* synthetic */ void lambda$initData$1$PayActivity() {
        GlideApp.with((FragmentActivity) this).asDrawable().load(Integer.valueOf((int) R.drawable.vip_reward_bg)).dontAnimate().into((GlideRequest<Drawable>) new SimpleTarget<Drawable>(this.frameLayout.getWidth(), this.frameLayout.getHeight()) { // from class: com.movieboxpro.android.view.activity.settings.PayActivity.2
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                PayActivity.this.frameLayout.setBackground(drawable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (App.isLogin()) {
            updateView(App.getUserData());
            if (Network.isConnected(this.context)) {
                loadData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }
}
