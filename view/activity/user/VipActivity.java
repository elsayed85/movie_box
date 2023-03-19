package com.movieboxpro.android.view.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VipActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0014J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/VipActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "payUrl", "", "buildData", "env", "getLayoutResId", "", "getPayUrl", "", "getStatusColor", "initData", "initListener", "initView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VipActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String payUrl = "https://www.xuedingcongming.com/index/pay";

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
        return R.layout.activity_vip;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$VipActivity$Be25c6wONZ1w4Cunv0F02V-ol-M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipActivity.m868initListener$lambda0(VipActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivBuy)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.user.-$$Lambda$VipActivity$cfYO5tJKhro_MsB2AYyBtZOrOjs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipActivity.m869initListener$lambda1(VipActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m868initListener$lambda0(VipActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m869initListener$lambda1(VipActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.startBrowser((Activity) this$0, this$0.payUrl + "?auth=" + ((Object) this$0.buildData("")));
    }

    private final String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().getUid() : "");
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        HttpUtils.decodeBody("eyJhcHBfa2V5IjoiNjQwNWMyZTYwNDVmNjU5YjdmZGNkOTU3ZmU1MmZlOWEiLCJlbmNyeXB0X2RhdGEiOiJWQUtzQ1JJY0k4NGFBQWgzV3UrXC9kcU1hY2lUNGMxNHFaUnAza0F4RzNVRTJlVDcwTFB4QUZ4ZE9KejhGMGRaSiIsInZlcmlmeSI6ImIzYTJmZWE2ZGRmYzBhYzgzMTQ3OTc3Njg1MGFmN2ZkIn0=", CipherKeys.getCiperKeys());
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String url = ConfigUtils.readStringConfig(ConfigKey.PAY_URL);
        String str = url;
        if (!(str == null || StringsKt.isBlank(str))) {
            Intrinsics.checkNotNullExpressionValue(url, "url");
            this.payUrl = url;
        }
        if (CommonUtils.isTablet()) {
            VipActivity vipActivity = this;
            GlideUtils.load((Activity) vipActivity, (int) R.drawable.vip_page_one, (ImageView) _$_findCachedViewById(R.id.image1));
            GlideUtils.load((Activity) vipActivity, (int) R.drawable.vip_page_two, (ImageView) _$_findCachedViewById(R.id.image2));
            GlideUtils.load((Activity) vipActivity, (int) R.drawable.vip_page_three_land, (ImageView) _$_findCachedViewById(R.id.image3));
        } else {
            VipActivity vipActivity2 = this;
            GlideUtils.load((Activity) vipActivity2, (int) R.drawable.vip_page_one, (ImageView) _$_findCachedViewById(R.id.image1));
            GlideUtils.load((Activity) vipActivity2, (int) R.drawable.vip_page_two, (ImageView) _$_findCachedViewById(R.id.image2));
            GlideUtils.load((Activity) vipActivity2, (int) R.drawable.vip_page_three, (ImageView) _$_findCachedViewById(R.id.image3));
        }
        VipActivity vipActivity3 = this;
        GlideUtils.load((Activity) vipActivity3, (int) R.mipmap.ic_all_together, (ImageView) _$_findCachedViewById(R.id.iVTitle1));
        GlideUtils.load((Activity) vipActivity3, (int) R.mipmap.ic_vip_no_ads, (ImageView) _$_findCachedViewById(R.id.iVTitle2));
        GlideUtils.load((Activity) vipActivity3, (int) R.mipmap.ic_high_quality, (ImageView) _$_findCachedViewById(R.id.iVTitle3));
        GlideUtils.load((Activity) vipActivity3, (int) R.mipmap.ic_vip_buy, (ImageView) _$_findCachedViewById(R.id.ivBuy));
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            ImageView iVTitle1 = (ImageView) _$_findCachedViewById(R.id.iVTitle1);
            Intrinsics.checkNotNullExpressionValue(iVTitle1, "iVTitle1");
            CommonExtKt.visible(iVTitle1);
            ImageView image1 = (ImageView) _$_findCachedViewById(R.id.image1);
            Intrinsics.checkNotNullExpressionValue(image1, "image1");
            CommonExtKt.visible(image1);
            View _$_findCachedViewById = _$_findCachedViewById(R.id.viewLine);
            if (_$_findCachedViewById != null) {
                CommonExtKt.visible(_$_findCachedViewById);
            }
        } else {
            ImageView iVTitle12 = (ImageView) _$_findCachedViewById(R.id.iVTitle1);
            Intrinsics.checkNotNullExpressionValue(iVTitle12, "iVTitle1");
            CommonExtKt.gone(iVTitle12);
            ImageView image12 = (ImageView) _$_findCachedViewById(R.id.image1);
            Intrinsics.checkNotNullExpressionValue(image12, "image1");
            CommonExtKt.gone(image12);
            View _$_findCachedViewById2 = _$_findCachedViewById(R.id.viewLine);
            if (_$_findCachedViewById2 != null) {
                CommonExtKt.gone(_$_findCachedViewById2);
            }
        }
        getPayUrl();
    }

    private final void getPayUrl() {
        RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "Pay_url").param("uid", App.getUserData().uid_v2).asBean(HashMap.class), new VipActivity$getPayUrl$1(this), null, new VipActivity$getPayUrl$2(this), null, new VipActivity$getPayUrl$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        if (CommonUtils.isTablet()) {
            SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvInfo));
            Intrinsics.checkNotNullExpressionValue(with, "with(tvInfo)");
            SpanUtils append = CommonExtKt.addText(with, "Only versions downloaded from our official website canguarantee no ads.", 14, R.color.white_30alpha).append("\n\n");
            Intrinsics.checkNotNullExpressionValue(append, "with(tvInfo).addText(\"On…          .append(\"\\n\\n\")");
            SpanUtils append2 = CommonExtKt.addText(append, "The TV version supports all Android (4.3 or newer) andApple TV (4th generation or newer) devices.", 14, R.color.white_30alpha).append("\n\n");
            Intrinsics.checkNotNullExpressionValue(append2, "with(tvInfo).addText(\"On…          .append(\"\\n\\n\")");
            SpanUtils append3 = CommonExtKt.addText(append2, "You are free to change the device and use it for up to5 devices for fair use.", 14, R.color.white_30alpha).append("\n\n");
            Intrinsics.checkNotNullExpressionValue(append3, "with(tvInfo).addText(\"On…          .append(\"\\n\\n\")");
            SpanUtils append4 = CommonExtKt.addText(append3, "All resources come from the open Internet, and we recodeall resources in order to play smoothly on the phone. ORG refers to the source video file,the video is not re-encoded, simple processing to adapt to streaming media playback,requires fast internet speed, suitable for large screen chromecast, airplay or TV version.", 14, R.color.white_30alpha).append("\n\n");
            Intrinsics.checkNotNullExpressionValue(append4, "with(tvInfo).addText(\"On…          .append(\"\\n\\n\")");
            CommonExtKt.addText(append4, "Thank you for your support.", 14, R.color.white_30alpha).create();
            return;
        }
        SpanUtils with2 = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvInfo));
        Intrinsics.checkNotNullExpressionValue(with2, "with(tvInfo)");
        SpanUtils append5 = CommonExtKt.addText(with2, "Only versions downloaded from our official website canguarantee no ads.", 14, R.color.white_30alpha).append("\n\n\n");
        Intrinsics.checkNotNullExpressionValue(append5, "with(tvInfo).addText(\"On…        .append(\"\\n\\n\\n\")");
        SpanUtils append6 = CommonExtKt.addText(append5, "The TV version supports all Android (4.3 or newer) and Apple TV (4th generation or newer) devices.", 14, R.color.white_30alpha).append("\n\n\n");
        Intrinsics.checkNotNullExpressionValue(append6, "with(tvInfo).addText(\"On…        .append(\"\\n\\n\\n\")");
        SpanUtils append7 = CommonExtKt.addText(append6, "You are free to change the device and use it for up to 5 devices for fair use.", 14, R.color.white_30alpha).append("\n\n\n");
        Intrinsics.checkNotNullExpressionValue(append7, "with(tvInfo).addText(\"On…        .append(\"\\n\\n\\n\")");
        SpanUtils append8 = CommonExtKt.addText(append7, "All resources come from the open Internet, and we recode all resources in order to play smoothly on the phone. ORG refers to the source video file, the video is not re-encoded, simple processing to adapt to streaming media playback, requires fast internet speed, suitable for large screen chromecast, airplay or TV version.", 14, R.color.white_30alpha).append("\n\n\n");
        Intrinsics.checkNotNullExpressionValue(append8, "with(tvInfo).addText(\"On…        .append(\"\\n\\n\\n\")");
        CommonExtKt.addText(append8, "Thank you for your support.", 14, R.color.white_30alpha).create();
    }

    /* compiled from: VipActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/user/VipActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            if (context == null) {
                return;
            }
            context.startActivity(new Intent(context, VipActivity.class));
        }
    }
}
