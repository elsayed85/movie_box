package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.entity.TestNetRecode;
import com.movieboxpro.android.event.LocationCompleteEvent;
import com.movieboxpro.android.model.IpInfoModel;
import com.movieboxpro.android.model.SpeedTestFeedbackModel;
import com.movieboxpro.android.model.TestSpeedModel;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.LocationUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.IpLocationFragment;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import com.movieboxpro.android.view.activity.settings.TestSpeedContract;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.dialog.FeedbackDialog;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.rxkotlin.SubscribersKt;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: TestSpeedActivity.kt */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 d2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002deB\u0005¢\u0006\u0002\u0010\u0004J \u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J(\u0010,\u001a\u0004\u0018\u00010\n2\b\u0010-\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\b\u0010.\u001a\u0004\u0018\u00010\nH\u0002J$\u0010/\u001a\u0004\u0018\u00010\n2\u0006\u0010-\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010\nH\u0002J\b\u00100\u001a\u00020\u0002H\u0014J%\u00101\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u0001032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\n05H\u0002¢\u0006\u0002\u00106J\u0010\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020\u0010H\u0002J\u0018\u00109\u001a\u00020%2\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010;H\u0002J\b\u0010<\u001a\u00020\u001bH\u0014J\b\u0010=\u001a\u00020%H\u0003J\u0010\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u001eH\u0002J\b\u0010@\u001a\u00020%H\u0003J\b\u0010A\u001a\u00020%H\u0016J\b\u0010B\u001a\u00020\u0010H\u0014J\u001e\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00102\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00070;H\u0002J\u001a\u0010F\u001a\u0004\u0018\u00010\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\nH\u0002J\b\u0010I\u001a\u00020%H\u0014J\u0018\u0010J\u001a\u00020%2\u0006\u0010K\u001a\u00020)2\u0006\u0010E\u001a\u00020\"H\u0002J\b\u0010L\u001a\u00020%H\u0015J\b\u0010M\u001a\u00020%H\u0014J\u0010\u0010N\u001a\u00020%2\u0006\u0010O\u001a\u00020PH\u0007J\"\u0010Q\u001a\u00020%2\u0006\u0010R\u001a\u00020\u00102\u0006\u0010S\u001a\u00020\u00102\b\u0010T\u001a\u0004\u0018\u00010UH\u0014J\b\u0010V\u001a\u00020%H\u0014J\b\u0010W\u001a\u00020%H\u0014J\b\u0010X\u001a\u00020%H\u0003J\u0010\u0010Y\u001a\u00020%2\u0006\u0010Z\u001a\u00020\nH\u0003J\b\u0010[\u001a\u00020%H\u0003J\u0010\u0010\\\u001a\u00020%2\u0006\u0010:\u001a\u00020]H\u0016J\u0010\u0010^\u001a\u00020%2\u0006\u0010:\u001a\u00020_H\u0016J$\u0010`\u001a\u00020%2\u0006\u0010a\u001a\u00020\u00072\u0006\u0010b\u001a\u00020\u00102\n\b\u0002\u0010c\u001a\u0004\u0018\u00010DH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0012`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082D¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedPresenter;", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedContract$View;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "Lcom/movieboxpro/android/model/common/NetTestModel;", "callMap", "Ljava/util/HashMap;", "", "Lokhttp3/Call;", "Lkotlin/collections/HashMap;", "client", "Lokhttp3/OkHttpClient;", "currPosition", "", "disposeMap", "Lio/reactivex/disposables/Disposable;", "feedbackList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/SpeedTestFeedbackModel;", "Lkotlin/collections/ArrayList;", "gpsLocation", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "ipLocation", "isAutoTesting", "", "lastPos", "latitude", "", "longitude", "maxTestCount", "speedInterval", "", "speedTime", "addEntry", "", "lineData", "Lcom/github/mikephil/charting/data/LineData;", "lineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "entry", "Lcom/github/mikephil/charting/data/Entry;", "addHeadTestSpeedInfo", "server", "speed", "addTestSpeedInfo", "bindPresenter", "checkPermission", "context", "Landroid/content/Context;", "permissions", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "deleteCacheFile", "id", "doTestSpeed", "info", "", "enableEventBus", "feedBack", "formatSpeed", "speedKbS", "getGpsLocation", "getIpFailed", "getLayoutResId", "getListener", "Lcom/movieboxpro/android/view/activity/settings/TestSpeedActivity$OnTestSpeedListener;", "size", "getSpace", "text", "length", "initData", "initLineChart", "chart", "initListener", "initView", "locationComplete", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LocationCompleteEvent;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "requestData", "requestPermission", "sendCustomTestInfo", FirebaseAnalytics.Param.CONTENT, "sendTestInfo", "showIpLocation", "Lcom/movieboxpro/android/model/IpInfoModel;", "showServerInfo", "Lcom/movieboxpro/android/model/TestSpeedModel;", "singleTest", "model", "position", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Companion", "OnTestSpeedListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TestSpeedActivity extends BaseMvpActivity<TestSpeedPresenter> implements TestSpeedContract.View {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_PERMISSION_SETTING = 100;
    private CommMultiBaseAdapter<NetTestModel> adapter;
    private OkHttpClient client;
    private int currPosition;
    private int lastPos;
    private double latitude;
    private double longitude;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final long speedTime = 8000;
    private final long speedInterval = 80;
    private String ip = "";
    private String ipLocation = "";
    private String gpsLocation = "";
    private boolean isAutoTesting = true;
    private HashMap<String, Call> callMap = new HashMap<>();
    private HashMap<String, Disposable> disposeMap = new HashMap<>();
    private final ArrayList<SpeedTestFeedbackModel> feedbackList = new ArrayList<>();
    private final int maxTestCount = 3;

    /* compiled from: TestSpeedActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedActivity$OnTestSpeedListener;", "", "onComplete", "", "position", "", "onFailed", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnTestSpeedListener {
        void onComplete(int i);

        void onFailed(int i);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.View
    public void getIpFailed() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_test_speed;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvTestWeb));
        Intrinsics.checkNotNullExpressionValue(with, "with(tvTestWeb)");
        CommonExtKt.addText(with, "Please try it on  ", 14, R.color.white_54alpha).append("https://www.nperf.com/").setFontSize(14, true).setForegroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.white_54alpha)).setUnderline().setClickSpan(new ClickableSpan() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedActivity$initView$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                SystemUtils.startBrowser((Activity) TestSpeedActivity.this, "https://www.nperf.com/");
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(CommonExtKt.colorInt(this, (int) R.color.white_54alpha));
            }
        }).create();
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("SERVER SPEED");
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$UhzRgwEbnMcv49oLjSoh0MC8vkI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestSpeedActivity.m818initView$lambda0(TestSpeedActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m818initView$lambda0(TestSpeedActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(5L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS).writeTimeout(5L, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
        this.client = build;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(1, Integer.valueOf((int) R.layout.adapter_test_speed_item)));
        arrayList.add(new Pair(2, Integer.valueOf((int) R.layout.adapter_test_speed_title_item)));
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = new CommMultiBaseAdapter<>(new TestSpeedActivity$initData$1(this), TestSpeedActivity$initData$2.INSTANCE, arrayList);
        this.adapter = commMultiBaseAdapter;
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.addChildClickViewIds(R.id.ivAction);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setNestedScrollingEnabled(false);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter3;
        }
        recyclerView2.setAdapter(commMultiBaseAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addEntry(LineData lineData, LineChart lineChart, Entry entry) {
        lineData.addEntry(entry, 0);
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.moveViewTo(entry.getX(), entry.getY(), YAxis.AxisDependency.LEFT);
        lineChart.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatSpeed(double d) {
        if (d < Utils.DOUBLE_EPSILON) {
            return "0";
        }
        if (d < 1024.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.getDefault(), "%sB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            return format;
        } else if (d < 1048576.0d) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale = Locale.getDefault();
            double d2 = 1024;
            Double.isNaN(d2);
            String format2 = String.format(locale, "%sKB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) (d / d2))}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            return format2;
        } else if (d < 1.073741824E9d) {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.getDefault();
            double d3 = 1048576;
            Double.isNaN(d3);
            String format3 = String.format(locale2, "%.1fMB/S", Arrays.copyOf(new Object[]{Double.valueOf(d / d3)}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            return format3;
        } else {
            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
            Locale locale3 = Locale.getDefault();
            double d4 = 1073741824;
            Double.isNaN(d4);
            String format4 = String.format(locale3, "%.1fGB/S", Arrays.copyOf(new Object[]{Double.valueOf(d / d4)}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            return format4;
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this.adapter;
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$-yIItLvT2gt4Z0rVN2KpFmpe7Bs
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestSpeedActivity.m814initListener$lambda2(TestSpeedActivity.this, baseQuickAdapter, view, i);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivHelp)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$ndMTuvvLolYDkeUscUIvcF-4cn0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestSpeedActivity.m815initListener$lambda3(TestSpeedActivity.this, view);
            }
        });
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter3;
        }
        commMultiBaseAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$vkVHlNEEOUdjiNgh8tV3YmjkTe4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestSpeedActivity.m816initListener$lambda8(TestSpeedActivity.this, baseQuickAdapter, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvReport)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$azDg1-gZklheFz9MbeahY3EqKy0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestSpeedActivity.m817initListener$lambda9(TestSpeedActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m814initListener$lambda2(TestSpeedActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this$0.adapter;
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        NetTestModel item = commMultiBaseAdapter.getItem(i);
        if (item.getOur() == 1 && this$0.lastPos != i) {
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter3 = this$0.adapter;
            if (commMultiBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter3 = null;
            }
            commMultiBaseAdapter3.getItem(i).isSelect = true;
            TextView textView = (TextView) this$0._$_findCachedViewById(R.id.tv_title);
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter4 = this$0.adapter;
            if (commMultiBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter4 = null;
            }
            String str = commMultiBaseAdapter4.getItem(i).country;
            if (str == null) {
                str = "";
            }
            textView.setText(Intrinsics.stringPlus(str, " selected"));
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter5 = this$0.adapter;
            if (commMultiBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter5 = null;
            }
            commMultiBaseAdapter5.notifyItemChanged(i);
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter6 = this$0.adapter;
            if (commMultiBaseAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter6 = null;
            }
            commMultiBaseAdapter6.getItem(this$0.lastPos).isSelect = false;
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter7 = this$0.adapter;
            if (commMultiBaseAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commMultiBaseAdapter2 = commMultiBaseAdapter7;
            }
            commMultiBaseAdapter2.notifyItemChanged(this$0.lastPos);
            this$0.lastPos = i;
            ((TestSpeedPresenter) this$0.mPresenter).insertDao(item);
            if (item.id != 1) {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, item.domain);
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, item.group_id);
            } else {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, "Error");
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, "");
            }
            this$0.setResult(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m815initListener$lambda3(TestSpeedActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.isLogin()) {
            IpLocationFragment.Companion companion = IpLocationFragment.Companion;
            String str = this$0.ip;
            if (str == null) {
                str = "";
            }
            companion.newInstance(str, ((TextView) this$0._$_findCachedViewById(R.id.tvLocation)).getText().toString(), this$0.ipLocation).show(this$0.getSupportFragmentManager(), "IpLocationFragment");
            return;
        }
        Login2Activity.start(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m816initListener$lambda8(TestSpeedActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this$0.adapter;
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        NetTestModel item = commMultiBaseAdapter.getItem(i);
        if (item == null) {
            return;
        }
        int i2 = 0;
        if (item.getStatus() == 1) {
            item.setStatus(3);
            if (this$0.isAutoTesting) {
                this$0.isAutoTesting = false;
                CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter3 = this$0.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter3 = null;
                }
                for (Object obj : commMultiBaseAdapter3.getData()) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    NetTestModel netTestModel = (NetTestModel) obj;
                    if (netTestModel.getStatus() == 1) {
                        netTestModel.setStatus(3);
                    }
                    CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter4 = this$0.adapter;
                    if (commMultiBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter4 = null;
                    }
                    commMultiBaseAdapter4.notifyItemChanged(i2);
                    i2 = i3;
                }
                for (Map.Entry<String, Call> entry : this$0.callMap.entrySet()) {
                    Call value = entry.getValue();
                    if (value != null) {
                        value.cancel();
                    }
                }
                for (Map.Entry<String, Disposable> entry2 : this$0.disposeMap.entrySet()) {
                    Disposable value2 = entry2.getValue();
                    if (value2 != null) {
                        value2.dispose();
                    }
                }
                return;
            }
            CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter5 = this$0.adapter;
            if (commMultiBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commMultiBaseAdapter2 = commMultiBaseAdapter5;
            }
            commMultiBaseAdapter2.notifyItemChanged(i);
            Call call = this$0.callMap.get(item.getNewUrl());
            if (call != null) {
                call.cancel();
            }
            Disposable disposable = this$0.disposeMap.get(item.getNewUrl());
            if (disposable == null) {
                return;
            }
            disposable.dispose();
            return;
        }
        item.startTime = 0L;
        item.setAddEntry(null);
        item.setCurrSize(0L);
        item.setSize(0L);
        item.setLineData(null);
        item.setInit(false);
        item.setStatus(0);
        item.setValues(null);
        item.setStatus(4);
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter6 = this$0.adapter;
        if (commMultiBaseAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter6;
        }
        commMultiBaseAdapter2.notifyItemChanged(i);
        singleTest$default(this$0, item, i, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m817initListener$lambda9(final TestSpeedActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FeedbackDialog feedbackDialog = new FeedbackDialog(this$0, "Your speed will be reported automatically, please describe your problem in detail, please include information: your country, city, network type, operator, sometimes slow or always slow, whether other video apps are normal, such as YouTube.\nThe more detailed the description, the more helpful we can help you solve the problem as soon as possible.");
        feedbackDialog.setMessageListener(new FeedbackDialog.OnMessageListener() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedActivity$initListener$4$1
            @Override // com.movieboxpro.android.view.dialog.FeedbackDialog.OnMessageListener
            public void onMessage(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                TestSpeedActivity.this.sendCustomTestInfo(message);
            }
        });
        feedbackDialog.show();
    }

    private final void doTestSpeed(List<? extends NetTestModel> list) {
        int size = list == null ? 0 : list.size();
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        int size2 = list.size();
        int i = this.maxTestCount;
        if (size2 >= i) {
            for (int i2 = 0; i2 < i; i2++) {
                singleTest(list.get(i2), i2, getListener(size, list));
                this.currPosition = i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnTestSpeedListener getListener(final int i, final List<? extends NetTestModel> list) {
        return new OnTestSpeedListener() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedActivity$getListener$1
            @Override // com.movieboxpro.android.view.activity.settings.TestSpeedActivity.OnTestSpeedListener
            public void onComplete(int i2) {
                int i3;
                int i4;
                int i5;
                int i6;
                int i7;
                int i8;
                int i9;
                TestSpeedActivity.OnTestSpeedListener listener;
                int i10;
                int i11;
                TestSpeedActivity.OnTestSpeedListener listener2;
                TestSpeedActivity testSpeedActivity = TestSpeedActivity.this;
                i3 = testSpeedActivity.currPosition;
                testSpeedActivity.currPosition = i3 + 1;
                i4 = TestSpeedActivity.this.currPosition;
                if (i4 <= i - 1) {
                    List<NetTestModel> list2 = list;
                    i5 = TestSpeedActivity.this.currPosition;
                    String newUrl = list2.get(i5).getNewUrl();
                    if (!(newUrl == null || StringsKt.isBlank(newUrl))) {
                        TestSpeedActivity testSpeedActivity2 = TestSpeedActivity.this;
                        List<NetTestModel> list3 = list;
                        i10 = testSpeedActivity2.currPosition;
                        i11 = TestSpeedActivity.this.currPosition;
                        listener2 = TestSpeedActivity.this.getListener(i, list);
                        testSpeedActivity2.singleTest(list3.get(i10), i11, listener2);
                        return;
                    }
                    TestSpeedActivity testSpeedActivity3 = TestSpeedActivity.this;
                    i6 = testSpeedActivity3.currPosition;
                    testSpeedActivity3.currPosition = i6 + 1;
                    i7 = TestSpeedActivity.this.currPosition;
                    if (i7 <= i - 1) {
                        TestSpeedActivity testSpeedActivity4 = TestSpeedActivity.this;
                        List<NetTestModel> list4 = list;
                        i8 = testSpeedActivity4.currPosition;
                        i9 = TestSpeedActivity.this.currPosition;
                        listener = TestSpeedActivity.this.getListener(i, list);
                        testSpeedActivity4.singleTest(list4.get(i8), i9, listener);
                    }
                }
            }

            @Override // com.movieboxpro.android.view.activity.settings.TestSpeedActivity.OnTestSpeedListener
            public void onFailed(int i2) {
                int i3;
                int i4;
                int i5;
                int i6;
                int i7;
                int i8;
                int i9;
                TestSpeedActivity.OnTestSpeedListener listener;
                int i10;
                int i11;
                TestSpeedActivity.OnTestSpeedListener listener2;
                TestSpeedActivity testSpeedActivity = TestSpeedActivity.this;
                i3 = testSpeedActivity.currPosition;
                testSpeedActivity.currPosition = i3 + 1;
                i4 = TestSpeedActivity.this.currPosition;
                if (i4 <= i - 1) {
                    List<NetTestModel> list2 = list;
                    i5 = TestSpeedActivity.this.currPosition;
                    String newUrl = list2.get(i5).getNewUrl();
                    if (!(newUrl == null || StringsKt.isBlank(newUrl))) {
                        TestSpeedActivity testSpeedActivity2 = TestSpeedActivity.this;
                        List<NetTestModel> list3 = list;
                        i10 = testSpeedActivity2.currPosition;
                        i11 = TestSpeedActivity.this.currPosition;
                        listener2 = TestSpeedActivity.this.getListener(i, list);
                        testSpeedActivity2.singleTest(list3.get(i10), i11, listener2);
                        return;
                    }
                    TestSpeedActivity testSpeedActivity3 = TestSpeedActivity.this;
                    i6 = testSpeedActivity3.currPosition;
                    testSpeedActivity3.currPosition = i6 + 1;
                    i7 = TestSpeedActivity.this.currPosition;
                    if (i7 <= i - 1) {
                        TestSpeedActivity testSpeedActivity4 = TestSpeedActivity.this;
                        List<NetTestModel> list4 = list;
                        i8 = testSpeedActivity4.currPosition;
                        i9 = TestSpeedActivity.this.currPosition;
                        listener = TestSpeedActivity.this.getListener(i, list);
                        testSpeedActivity4.singleTest(list4.get(i8), i9, listener);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteCacheFile(int i) {
        String str = Constant.DIR_DOWNLOAD_TEST_SPEED;
        new File(str, "testData" + i + ".dat").delete();
    }

    static /* synthetic */ void singleTest$default(TestSpeedActivity testSpeedActivity, NetTestModel netTestModel, int i, OnTestSpeedListener onTestSpeedListener, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            onTestSpeedListener = null;
        }
        testSpeedActivity.singleTest(netTestModel, i, onTestSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void singleTest(final NetTestModel netTestModel, final int i, final OnTestSpeedListener onTestSpeedListener) {
        String str = Constant.DIR_DOWNLOAD_TEST_SPEED;
        final File file = new File(str, "testData" + netTestModel.id + ".dat");
        if (file.exists()) {
            file.delete();
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.LongRef longRef = new Ref.LongRef();
        ((ObservableSubscribeProxy) Observable.just(netTestModel).map(new Function() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$ocbWAz_ETiBNqN59sd4_2lDypao
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Unit m826singleTest$lambda10;
                m826singleTest$lambda10 = TestSpeedActivity.m826singleTest$lambda10(TestSpeedActivity.this, netTestModel, i, onTestSpeedListener, booleanRef, file, (NetTestModel) obj);
                return m826singleTest$lambda10;
            }
        }).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$7XjMzVXlijlB-9Lv4HeJPskSMQo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m827singleTest$lambda12;
                m827singleTest$lambda12 = TestSpeedActivity.m827singleTest$lambda12(TestSpeedActivity.this, longRef, (Unit) obj);
                return m827singleTest$lambda12;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedActivity$singleTest$3
            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Long l) {
                onNext(l.longValue());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                CommMultiBaseAdapter commMultiBaseAdapter;
                HashMap hashMap;
                NetTestModel.this.setStatus(3);
                commMultiBaseAdapter = this.adapter;
                if (commMultiBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter = null;
                }
                commMultiBaseAdapter.notifyItemChanged(i);
                this.deleteCacheFile(NetTestModel.this.id);
                TestSpeedActivity.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                if (onTestSpeedListener2 != null) {
                    onTestSpeedListener2.onComplete(i);
                }
                hashMap = this.callMap;
                Call call = (Call) hashMap.get(NetTestModel.this.getNewUrl());
                if (call == null) {
                    return;
                }
                call.cancel();
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                HashMap hashMap;
                CommMultiBaseAdapter commMultiBaseAdapter;
                CommMultiBaseAdapter commMultiBaseAdapter2;
                Intrinsics.checkNotNullParameter(d, "d");
                hashMap = this.disposeMap;
                String newUrl = NetTestModel.this.getNewUrl();
                Intrinsics.checkNotNullExpressionValue(newUrl, "model.newUrl");
                hashMap.put(newUrl, d);
                commMultiBaseAdapter = this.adapter;
                CommMultiBaseAdapter commMultiBaseAdapter3 = null;
                if (commMultiBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter = null;
                }
                NetTestModel netTestModel2 = (NetTestModel) commMultiBaseAdapter.getItem(i);
                if (netTestModel2 != null) {
                    netTestModel2.setStatus(4);
                    commMultiBaseAdapter2 = this.adapter;
                    if (commMultiBaseAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commMultiBaseAdapter3 = commMultiBaseAdapter2;
                    }
                    commMultiBaseAdapter3.notifyItemChanged(i);
                }
            }

            public void onNext(long j) {
                CommMultiBaseAdapter commMultiBaseAdapter;
                HashMap hashMap;
                CommMultiBaseAdapter commMultiBaseAdapter2;
                if (booleanRef.element) {
                    if (longRef.element <= 3) {
                        longRef.element++;
                        return;
                    }
                    long length = file.length();
                    CommMultiBaseAdapter commMultiBaseAdapter3 = null;
                    if (length >= NetTestModel.this.getContentLength()) {
                        hashMap = this.disposeMap;
                        Disposable disposable = (Disposable) hashMap.get(NetTestModel.this.getNewUrl());
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        NetTestModel.this.setStatus(3);
                        NetTestModel.this.setCurrSize(length);
                        commMultiBaseAdapter2 = this.adapter;
                        if (commMultiBaseAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter3 = commMultiBaseAdapter2;
                        }
                        commMultiBaseAdapter3.notifyItemChanged(i);
                        TestSpeedActivity.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                        if (onTestSpeedListener2 == null) {
                            return;
                        }
                        onTestSpeedListener2.onComplete(i);
                        return;
                    }
                    NetTestModel.this.setStatus(1);
                    NetTestModel.this.setCurrSize(length);
                    String valueOf = String.valueOf(length);
                    long currentTimeMillis = System.currentTimeMillis();
                    Long l = NetTestModel.this.startTime;
                    Intrinsics.checkNotNullExpressionValue(l, "model.startTime");
                    String speed = CommonUtils.divide(valueOf, String.valueOf(currentTimeMillis - l.longValue()), 5);
                    TestSpeedActivity testSpeedActivity = this;
                    StringBuilder sb = new StringBuilder();
                    long currentTimeMillis2 = System.currentTimeMillis();
                    Long l2 = NetTestModel.this.startTime;
                    Intrinsics.checkNotNullExpressionValue(l2, "model.startTime");
                    sb.append(currentTimeMillis2 - l2.longValue());
                    sb.append(' ');
                    sb.append(length);
                    CommonExtKt.logD(testSpeedActivity, sb.toString());
                    NetTestModel netTestModel2 = NetTestModel.this;
                    Intrinsics.checkNotNullExpressionValue(speed, "speed");
                    netTestModel2.setAddEntry(new Entry((float) longRef.element, Float.parseFloat(speed)));
                    longRef.element++;
                    commMultiBaseAdapter = this.adapter;
                    if (commMultiBaseAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commMultiBaseAdapter3 = commMultiBaseAdapter;
                    }
                    commMultiBaseAdapter3.notifyItemChanged(i);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                CommMultiBaseAdapter commMultiBaseAdapter;
                Intrinsics.checkNotNullParameter(e, "e");
                ToastUtils.showShort(e.getMessage(), new Object[0]);
                NetTestModel.this.setStatus(2);
                commMultiBaseAdapter = this.adapter;
                if (commMultiBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter = null;
                }
                commMultiBaseAdapter.notifyItemChanged(i);
                TestSpeedActivity.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                if (onTestSpeedListener2 == null) {
                    return;
                }
                onTestSpeedListener2.onFailed(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: singleTest$lambda-10  reason: not valid java name */
    public static final Unit m826singleTest$lambda10(TestSpeedActivity this$0, NetTestModel model, int i, OnTestSpeedListener onTestSpeedListener, Ref.BooleanRef success, File file, NetTestModel it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(it, "it");
        Request build = new Request.Builder().url(it.getNewUrl()).build();
        OkHttpClient okHttpClient = this$0.client;
        if (okHttpClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            okHttpClient = null;
        }
        Call call = okHttpClient.newCall(build);
        String newUrl = it.getNewUrl();
        Intrinsics.checkNotNullExpressionValue(newUrl, "it.newUrl");
        Intrinsics.checkNotNullExpressionValue(call, "call");
        this$0.callMap.put(newUrl, call);
        call.enqueue(new TestSpeedActivity$singleTest$1$1(this$0, model, i, onTestSpeedListener, success, file));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: singleTest$lambda-12  reason: not valid java name */
    public static final ObservableSource m827singleTest$lambda12(final TestSpeedActivity this$0, final Ref.LongRef entryCount, Unit it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(entryCount, "$entryCount");
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.interval(100L, this$0.speedInterval, TimeUnit.MILLISECONDS).takeUntil(new Predicate() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$-BC6_TqBSbogcBJ8VPkEQ2RLOeY
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean m828singleTest$lambda12$lambda11;
                m828singleTest$lambda12$lambda11 = TestSpeedActivity.m828singleTest$lambda12$lambda11(Ref.LongRef.this, this$0, (Long) obj);
                return m828singleTest$lambda12$lambda11;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: singleTest$lambda-12$lambda-11  reason: not valid java name */
    public static final boolean m828singleTest$lambda12$lambda11(Ref.LongRef entryCount, TestSpeedActivity this$0, Long it) {
        Intrinsics.checkNotNullParameter(entryCount, "$entryCount");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return entryCount.element >= this$0.speedTime / this$0.speedInterval;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initLineChart(LineChart lineChart, long j) {
        lineChart.getDescription().setEnabled(false);
        lineChart.setViewPortOffsets(0.0f, 0.0f, 0.0f, 0.0f);
        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300.0f);
        lineChart.setNoDataText("");
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setAxisMaximum((float) (this.speedTime / this.speedInterval));
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        requestPermission();
        ((TestSpeedPresenter) this.mPresenter).getIp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        FileUtils.deleteAllInDir(new File(Constant.DIR_DOWNLOAD_TEST_SPEED));
        if (App.isLogin()) {
            sendTestInfo();
        }
        if (checkPermission(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})) {
            LocationUtils.unregister();
        }
        for (Map.Entry<String, Call> entry : this.callMap.entrySet()) {
            entry.getValue().cancel();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendCustomTestInfo(String str) {
        showLoadingView();
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        Observable compose = Observable.just(commMultiBaseAdapter.getData()).map($$Lambda$TestSpeedActivity$2k1pM4hgI3_je_rQ1mHszq1JAIc.INSTANCE).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "just(adapter.data)\n     …tils.rxSchedulerHelper())");
        SubscribersKt.subscribeBy$default(compose, new TestSpeedActivity$sendCustomTestInfo$2(this), (Function0) null, new TestSpeedActivity$sendCustomTestInfo$3(this, str), 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendCustomTestInfo$lambda-15  reason: not valid java name */
    public static final Unit m824sendCustomTestInfo$lambda15(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            NetTestModel netTestModel = (NetTestModel) it2.next();
            String str = null;
            if (netTestModel.getOur() == 1 && netTestModel.domain != null) {
                String str2 = netTestModel.domain;
                if (str2 != null) {
                    String str3 = netTestModel.domain;
                    Intrinsics.checkNotNullExpressionValue(str3, "model.domain");
                    str = str2.substring(StringsKt.lastIndexOf$default((CharSequence) str3, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                }
                netTestModel.setDomainIp(Network.getDomainAddress(str));
            } else if (netTestModel.getItemType() == 1) {
                String str4 = netTestModel.domain;
                if (str4 != null) {
                    String str5 = netTestModel.domain;
                    Intrinsics.checkNotNullExpressionValue(str5, "model.domain");
                    str = str4.substring(StringsKt.lastIndexOf$default((CharSequence) str5, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                }
                netTestModel.setDomainIp(Network.getDomainAddress(str));
            }
        }
        return Unit.INSTANCE;
    }

    private final void sendTestInfo() {
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        Observable compose = Observable.just(commMultiBaseAdapter.getData()).map($$Lambda$TestSpeedActivity$XhTZncAmIAVKC25CbOzmMNEMKv8.INSTANCE).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "just(adapter.data)\n     …tils.rxSchedulerHelper())");
        SubscribersKt.subscribeBy$default(compose, new TestSpeedActivity$sendTestInfo$2(this), (Function0) null, new TestSpeedActivity$sendTestInfo$3(this), 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendTestInfo$lambda-17  reason: not valid java name */
    public static final Unit m825sendTestInfo$lambda17(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            NetTestModel netTestModel = (NetTestModel) it2.next();
            String str = null;
            if (netTestModel.getOur() == 1 && netTestModel.domain != null) {
                String str2 = netTestModel.domain;
                if (str2 != null) {
                    String str3 = netTestModel.domain;
                    Intrinsics.checkNotNullExpressionValue(str3, "model.domain");
                    str = str2.substring(StringsKt.lastIndexOf$default((CharSequence) str3, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                }
                netTestModel.setDomainIp(Network.getDomainAddress(str));
            } else if (netTestModel.getItemType() == 1) {
                String str4 = netTestModel.domain;
                if (str4 != null) {
                    String str5 = netTestModel.domain;
                    Intrinsics.checkNotNullExpressionValue(str5, "model.domain");
                    str = str4.substring(StringsKt.lastIndexOf$default((CharSequence) str5, "/", 0, false, 6, (Object) null) + 1);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                }
                netTestModel.setDomainIp(Network.getDomainAddress(str));
            }
        }
        return Unit.INSTANCE;
    }

    private final void feedBack() {
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        for (NetTestModel netTestModel : commMultiBaseAdapter.getData()) {
            if (netTestModel.getSpeeds() != null) {
                SpeedTestFeedbackModel speedTestFeedbackModel = new SpeedTestFeedbackModel();
                List<Integer> speeds = netTestModel.getSpeeds();
                Intrinsics.checkNotNullExpressionValue(speeds, "it.speeds");
                speedTestFeedbackModel.setAverage((int) CollectionsKt.averageOfInt(speeds));
                String str = netTestModel.group_id;
                if (str == null) {
                    str = "";
                }
                speedTestFeedbackModel.setGroup(str);
                speedTestFeedbackModel.setTest_url_id(String.valueOf(netTestModel.id));
                this.feedbackList.add(speedTestFeedbackModel);
            }
        }
        ((TestSpeedPresenter) this.mPresenter).speedFeedBack(this.feedbackList, ((TextView) _$_findCachedViewById(R.id.tvLocation)).getText().toString(), this.ipLocation, this.ip);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String addHeadTestSpeedInfo(String str, String str2, String str3) {
        return str + "                                                  " + str2 + "                                                  " + str3 + ShellUtil.COMMAND_LINE_END;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String addTestSpeedInfo(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getSpace(str, "Server"));
        if (TextUtils.isEmpty(str2)) {
            sb.append("               ");
            sb.append(getSpace("145.239.252.191", "IP"));
        } else {
            sb.append(str2);
            sb.append(getSpace(str2, "IP"));
        }
        sb.append(str3);
        sb.append(ShellUtil.COMMAND_LINE_END);
        return sb.toString();
    }

    private final String getSpace(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = (str2.length() + 50) - str.length();
        int i = 0;
        while (i < length) {
            i++;
            sb.append(" ");
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TestSpeedPresenter bindPresenter() {
        return new TestSpeedPresenter(this);
    }

    /* compiled from: TestSpeedActivity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TestSpeedActivity$Companion;", "", "()V", "REQUEST_PERMISSION_SETTING", "", TtmlNode.START, "", "context", "Landroid/content/Context;", "startForResult", "Landroid/app/Activity;", "requestCode", "Landroidx/fragment/app/Fragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, TestSpeedActivity.class);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void startForResult(Activity context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivityForResult(new Intent(context, TestSpeedActivity.class), i);
        }

        public final void startForResult(Fragment context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivityForResult(new Intent(context.getContext(), TestSpeedActivity.class), i);
        }
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.View
    public void showServerInfo(TestSpeedModel info) {
        Intrinsics.checkNotNullParameter(info, "info");
        List<NetTestModel> ourInfo = info.getOur();
        Intrinsics.checkNotNullExpressionValue(ourInfo, "ourInfo");
        List<NetTestModel> list = ourInfo;
        for (NetTestModel netTestModel : list) {
            netTestModel.setItemType(1);
        }
        List<NetTestModel> third = info.getThird();
        Intrinsics.checkNotNullExpressionValue(third, "info.third");
        for (NetTestModel netTestModel2 : third) {
            netTestModel2.setItemType(1);
        }
        TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
        int i = findAll == null ? -1 : findAll.id;
        int i2 = 0;
        if (i == -1) {
            if ((!ourInfo.isEmpty()) && ourInfo.get(0).id == 1) {
                ourInfo.get(0).isSelect = true;
                ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(Intrinsics.stringPlus(ourInfo.get(0).country, " selected"));
            }
        } else {
            TestSpeedActivity testSpeedActivity = this;
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                NetTestModel netTestModel3 = (NetTestModel) next;
                if (netTestModel3.id == i) {
                    netTestModel3.isSelect = true;
                    testSpeedActivity.lastPos = i2;
                    ((TextView) testSpeedActivity._$_findCachedViewById(R.id.tv_title)).setText(Intrinsics.stringPlus(netTestModel3.country, " selected"));
                    break;
                }
                i2 = i3;
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ourInfo);
        NetTestModel netTestModel4 = new NetTestModel();
        netTestModel4.setItemType(2);
        arrayList.add(netTestModel4);
        arrayList.addAll(info.getThird());
        CommMultiBaseAdapter<NetTestModel> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setList(arrayList);
        doTestSpeed(arrayList);
    }

    @Override // com.movieboxpro.android.view.activity.settings.TestSpeedContract.View
    public void showIpLocation(IpInfoModel info) {
        List<IpInfoModel.LocationBean.SubdivisionsBean> subdivisions;
        IpInfoModel.LocationBean.ContinentBean continent;
        IpInfoModel.NamesBean names;
        String en;
        IpInfoModel.LocationBean.CountryBean country;
        IpInfoModel.NamesBean names2;
        String en2;
        Intrinsics.checkNotNullParameter(info, "info");
        String ip = info.getIp();
        if (ip == null) {
            ip = "";
        }
        this.ip = ip;
        if (info.getLocation() != null) {
            IpInfoModel.LocationBean location = info.getLocation();
            if ((location == null ? null : location.getSubdivisions()) != null) {
                IpInfoModel.LocationBean location2 = info.getLocation();
                if ((location2 == null || (subdivisions = location2.getSubdivisions()) == null || !(subdivisions.isEmpty() ^ true)) ? false : true) {
                    IpInfoModel.LocationBean.SubdivisionsBean subdivisionsBean = info.getLocation().getSubdivisions().get(0);
                    StringBuilder sb = new StringBuilder();
                    IpInfoModel.LocationBean location3 = info.getLocation();
                    if (location3 == null || (continent = location3.getContinent()) == null || (names = continent.getNames()) == null || (en = names.getEn()) == null) {
                        en = "";
                    }
                    sb.append(en);
                    sb.append(',');
                    IpInfoModel.LocationBean location4 = info.getLocation();
                    if (location4 == null || (country = location4.getCountry()) == null || (names2 = country.getNames()) == null || (en2 = names2.getEn()) == null) {
                        en2 = "";
                    }
                    sb.append(en2);
                    sb.append(',');
                    String en3 = subdivisionsBean.getNames().getEn();
                    sb.append(en3 != null ? en3 : "");
                    this.ipLocation = sb.toString();
                }
            }
        }
        ((TextView) _$_findCachedViewById(R.id.tvLocation)).setText(this.ipLocation);
        ImageView ivHelp = (ImageView) _$_findCachedViewById(R.id.ivHelp);
        Intrinsics.checkNotNullExpressionValue(ivHelp, "ivHelp");
        CommonExtKt.visible(ivHelp);
    }

    private final boolean checkPermission(Context context, String[] strArr) {
        if (context == null) {
            return false;
        }
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            i++;
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    private final void getGpsLocation() {
        LocationUtils.register(0L, 0L, new LocationUtils.OnLocationChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.TestSpeedActivity$getGpsLocation$1
            @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
            public void getLastKnownLocation(Location location) {
            }

            @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
            public void onStatusChanged(String str, int i, Bundle bundle) {
            }

            @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
            public void onLocationChanged(Location location) {
                if (location != null) {
                    try {
                        TestSpeedActivity.this.latitude = location.getLatitude();
                        TestSpeedActivity.this.longitude = location.getLongitude();
                        List<Address> fromLocation = new Geocoder(TestSpeedActivity.this, Locale.ENGLISH).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (fromLocation == null || !(!fromLocation.isEmpty())) {
                            return;
                        }
                        Address address = fromLocation.get(0);
                        TestSpeedActivity testSpeedActivity = TestSpeedActivity.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append((Object) address.getCountryName());
                        sb.append(',');
                        sb.append((Object) address.getAdminArea());
                        sb.append(',');
                        sb.append((Object) address.getSubLocality());
                        testSpeedActivity.gpsLocation = sb.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void locationComplete(LocationCompleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.gpsLocation = event.getLocation();
    }

    private final void requestPermission() {
        ((TestSpeedPresenter) this.mPresenter).getServerInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            requestPermission();
        }
    }
}
