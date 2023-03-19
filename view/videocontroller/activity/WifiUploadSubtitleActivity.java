package com.movieboxpro.android.view.videocontroller.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.LoadUploadSubtitleEvent;
import com.movieboxpro.android.model.WifiUploadSubtitleModel;
import com.movieboxpro.android.receiver.WifiConnectChangedReceiver;
import com.movieboxpro.android.service.WebUploadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.WifiUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: WifiUploadSubtitleActivity.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\b\u0010\u0014\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0002J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0014J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010\u001d\u001a\u00020\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020!H\u0007J\b\u0010\"\u001a\u00020\fH\u0002J\b\u0010#\u001a\u00020\fH\u0002J\b\u0010$\u001a\u00020\fH\u0002J\b\u0010%\u001a\u00020\fH\u0003J\b\u0010&\u001a\u00020\fH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/activity/WifiUploadSubtitleActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/WifiUploadSubtitleModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "dir", "", "wifiConnectChangedReceiver", "Lcom/movieboxpro/android/receiver/WifiConnectChangedReceiver;", "checkWifiState", "", "state", "Landroid/net/NetworkInfo$State;", "enableEventBus", "", "getLayoutResId", "", "getStatusColor", "initData", "initListener", "initSaveDir", "initView", "loadSubtitles", "onDestroy", "onLoadUploadSubtitles", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoadUploadSubtitleEvent;", "onWifiConnect", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "onWifiConnectChanged", "networkInfo", "Landroid/net/NetworkInfo;", "onWifiConnecting", "onWifiDisconnected", "registerWifiConnectChangedReceiver", "requestPermission", "unregisterWifiConnectChangedReceiver", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WifiUploadSubtitleActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> adapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final WifiConnectChangedReceiver wifiConnectChangedReceiver = new WifiConnectChangedReceiver();
    private String dir = "";

    /* compiled from: WifiUploadSubtitleActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NetworkInfo.State.values().length];
            iArr[NetworkInfo.State.CONNECTED.ordinal()] = 1;
            iArr[NetworkInfo.State.CONNECTING.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

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
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_wifi_upload_subtitle;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.black;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onWifiConnectChanged(NetworkInfo networkInfo) {
        Intrinsics.checkNotNullParameter(networkInfo, "networkInfo");
        NetworkInfo.State state = networkInfo.getState();
        Intrinsics.checkNotNullExpressionValue(state, "networkInfo.state");
        checkWifiState(state);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLoadUploadSubtitles(LoadUploadSubtitleEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        loadSubtitles();
    }

    private final void loadSubtitles() {
        ((ObservableSubscribeProxy) Observable.just(new File(this.dir)).map($$Lambda$WifiUploadSubtitleActivity$McSsa6Uz57ucO92bRZCuWLiwLU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.videocontroller.activity.-$$Lambda$WifiUploadSubtitleActivity$rMfticPFphcGsU-zFaSLPrT2qi0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                WifiUploadSubtitleActivity.m1354loadSubtitles$lambda2(WifiUploadSubtitleActivity.this, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadSubtitles$lambda-1  reason: not valid java name */
    public static final List m1353loadSubtitles$lambda1(File it) {
        String name;
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        if (it.exists() && it.isDirectory()) {
            File[] listFiles = it.listFiles();
            Intrinsics.checkNotNullExpressionValue(listFiles, "it.listFiles()");
            File[] fileArr = listFiles;
            int i = 0;
            int length = fileArr.length;
            while (i < length) {
                File file = fileArr[i];
                i++;
                File file2 = file;
                String name2 = file2.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "it.name");
                List split$default = StringsKt.split$default((CharSequence) name2, new String[]{"_"}, false, 0, 6, (Object) null);
                if (split$default.size() >= 2) {
                    name = (String) split$default.get(1);
                } else {
                    name = file2.getName();
                }
                String name3 = name;
                Intrinsics.checkNotNullExpressionValue(name3, "name");
                arrayList.add(new WifiUploadSubtitleModel(name3, null, null, null, 14, null));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadSubtitles$lambda-2  reason: not valid java name */
    public static final void m1354loadSubtitles$lambda2(WifiUploadSubtitleActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setNewData(list);
    }

    private final void checkWifiState(NetworkInfo.State state) {
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i == 1) {
            onWifiConnect(WifiUtils.getWifiIp());
        } else if (i == 2) {
            onWifiConnecting();
        } else {
            onWifiDisconnected();
        }
    }

    private final void onWifiDisconnected() {
        ((TextView) _$_findCachedViewById(R.id.tvIpAddress)).setText(getString(R.string.wifi_disconnecting_hint));
    }

    private final void onWifiConnecting() {
        ((TextView) _$_findCachedViewById(R.id.tvIpAddress)).setText(getString(R.string.wifi_connecting_hint));
    }

    private final void onWifiConnect(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            ((TextView) _$_findCachedViewById(R.id.tvIpAddress)).setText(getString(R.string.No_Ip));
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s:%s", Arrays.copyOf(new Object[]{str, Integer.valueOf((int) Constant.HTTP_PORT)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tvIpAddress)).setText(format);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        WebUploadService.Companion.stop(this);
        unregisterWifiConnectChangedReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1350initListener$lambda3(WifiUploadSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.activity.-$$Lambda$WifiUploadSubtitleActivity$1FDfPaFszrlvsZibFs3W6tHmCGk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiUploadSubtitleActivity.m1350initListener$lambda3(WifiUploadSubtitleActivity.this, view);
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initSaveDir();
        registerWifiConnectChangedReceiver();
        requestPermission();
        final ArrayList arrayList = new ArrayList();
        this.adapter = new BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.videocontroller.activity.WifiUploadSubtitleActivity$initData$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, WifiUploadSubtitleModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.tvSubtitleName, item.getName());
            }
        };
        WifiUploadSubtitleActivity wifiUploadSubtitleActivity = this;
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(wifiUploadSubtitleActivity));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<WifiUploadSubtitleModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        loadSubtitles();
        CommonExtKt.onMobEvent(wifiUploadSubtitleActivity, "WiFiUploadSubtitles");
        EventUtils.event("进入WiFi上传字幕");
    }

    private final void initSaveDir() {
        String str;
        String string;
        Bundle extras = getIntent().getExtras();
        String str2 = "";
        if (extras != null && (string = extras.getString("id")) != null) {
            str2 = string;
        }
        Integer valueOf = extras == null ? null : Integer.valueOf(extras.getInt("season"));
        Integer valueOf2 = extras == null ? null : Integer.valueOf(extras.getInt("episode"));
        String string2 = extras != null ? extras.getString("name") : null;
        if ((valueOf == null || valueOf.intValue() != 0) && (valueOf2 == null || valueOf2.intValue() != 0)) {
            str = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + str2 + ((Object) File.separator) + ((Object) string2) + ((Object) File.separator) + "Season " + valueOf + ((Object) File.separator) + "Episode " + valueOf2 + ((Object) File.separator) + ((Object) App.deviceLang);
        } else {
            str = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + str2 + ((Object) File.separator) + ((Object) string2) + ((Object) File.separator) + ((Object) App.deviceLang);
        }
        this.dir = str;
    }

    private final void requestPermission() {
        WebUploadService.Companion companion = WebUploadService.Companion;
        WifiUploadSubtitleActivity wifiUploadSubtitleActivity = this;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        companion.start(wifiUploadSubtitleActivity, extras);
    }

    private final void registerWifiConnectChangedReceiver() {
        registerReceiver(this.wifiConnectChangedReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
    }

    private final void unregisterWifiConnectChangedReceiver() {
        unregisterReceiver(this.wifiConnectChangedReceiver);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setNestedScrollingEnabled(false);
    }

    /* compiled from: WifiUploadSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/activity/WifiUploadSubtitleActivity$Companion;", "", "()V", "starter", "", "context", "Landroid/content/Context;", "bundle", "Landroid/os/Bundle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Context context, Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intent intent = new Intent(context, WifiUploadSubtitleActivity.class);
            intent.putExtras(bundle);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
