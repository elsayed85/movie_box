package com.movieboxpro.android.view.activity.tv;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TvDownloadAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.SubtitleDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.utils.tool.ThreadUtils;
import com.movieboxpro.android.view.activity.DownloadResolutionActivity;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.tv.TvDownloadContract;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.dialog.ChooseSeasonEpisodeDialog;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.widget.CustomSwitchView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: TvDownloadActivity.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 72\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u000278B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0002H\u0014J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\tH\u0002J \u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0012H\u0002J\"\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00180\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 H\u0002J\b\u0010!\u001a\u00020\tH\u0014J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0016H\u0014J\b\u0010$\u001a\u00020\u0016H\u0014J\b\u0010%\u001a\u00020\u0016H\u0014J\b\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020\u0016H\u0014J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000fH\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J\b\u0010-\u001a\u00020\u0016H\u0014J \u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000fH\u0002J\u0010\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u000203H\u0002J\u0016\u00104\u001a\u00020\u00162\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020605H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0018\u00010\fR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082.¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082.¢\u0006\u0004\n\u0002\u0010\u0010¨\u00069"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadPresenter;", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadContract$View;", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog$OnSeasonYearChooseListener;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/TvDownloadAdapter;", "currSeason", "", "currYear", "mReceiver", "Lcom/movieboxpro/android/view/activity/tv/TvDownloadActivity$DownloadProgressReceiver;", "seasons", "", "", "[Ljava/lang/String;", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "years", "bindPresenter", "checkDownload", "", "downloadFile", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "i", "checkEnoughSpace", "checkOnlineDevice", "tv", "getAutoDownloadUrl", "Landroidx/core/util/Pair;", "list", "", "getLayoutResId", "getPlayUrl", "initData", "initListener", "initView", "isNeedLoadData", "", "onDestroy", "onSeasonYearChoose", "season", "year", "registerDownloadReceiver", "requestData", "showAddWatchedFlagDialog", "id", "episode", "showDownload", "downloadModel", "Lcom/movieboxpro/android/model/DownloadModel;", "showEpisodes", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "Companion", "DownloadProgressReceiver", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvDownloadActivity extends BaseMvpActivity<TvDownloadPresenter> implements TvDownloadContract.View, ChooseSeasonEpisodeDialog.OnSeasonYearChooseListener {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private TvDownloadAdapter adapter;
    private int currSeason;
    private int currYear;
    private DownloadProgressReceiver mReceiver;
    private String[] seasons;
    private TvDetail tvDetail;
    private String[] years;

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
    protected int getLayoutResId() {
        return R.layout.activity_tv_download2;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((CustomSwitchView) _$_findCachedViewById(R.id.switchView)).setCheckListener(new CustomSwitchView.OnCheckListener() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$initListener$1
            @Override // com.movieboxpro.android.view.widget.CustomSwitchView.OnCheckListener
            public void onCheckChangeListener(boolean z) {
                SettingManager.INSTANCE.saveAutoDownloadSelectQuality(z);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvEdit)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$qsNXB4d9aANTZeFsF0qYs4eNmI0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDownloadActivity.m845initListener$lambda0(TvDownloadActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$J8JIGmvf0CLqGaYEVxojhFVIovk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDownloadActivity.m846initListener$lambda1(TvDownloadActivity.this, view);
            }
        });
        TvDownloadAdapter tvDownloadAdapter = this.adapter;
        TvDownloadAdapter tvDownloadAdapter2 = null;
        if (tvDownloadAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tvDownloadAdapter = null;
        }
        tvDownloadAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$SKfh2O6Bb_CCrm_tqRNvJRW3bfc
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvDownloadActivity.m847initListener$lambda2(TvDownloadActivity.this, baseQuickAdapter, view, i);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llSeason)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$jTTtn6vXwmvo3ATXpP3wmcJRITI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDownloadActivity.m848initListener$lambda3(TvDownloadActivity.this, view);
            }
        });
        TvDownloadAdapter tvDownloadAdapter3 = this.adapter;
        if (tvDownloadAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            tvDownloadAdapter2 = tvDownloadAdapter3;
        }
        tvDownloadAdapter2.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$KGUL3WB-ygFHjiMrmXA6iWj8HDU
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                boolean m849initListener$lambda4;
                m849initListener$lambda4 = TvDownloadActivity.m849initListener$lambda4(TvDownloadActivity.this, baseQuickAdapter, view, i);
                return m849initListener$lambda4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m845initListener$lambda0(TvDownloadActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TvDownloadActivity tvDownloadActivity = this$0;
        tvDownloadActivity.startActivity(new Intent(tvDownloadActivity, DownloadResolutionActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m846initListener$lambda1(TvDownloadActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m847initListener$lambda2(final TvDownloadActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        TvDownloadActivity tvDownloadActivity = this$0;
        if (X86HintUtils.checkX86(tvDownloadActivity)) {
            return;
        }
        TvDownloadAdapter tvDownloadAdapter = this$0.adapter;
        if (tvDownloadAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tvDownloadAdapter = null;
        }
        final TvDetail.SeasonDetail item = tvDownloadAdapter.getItem(i);
        DownloadDao downloadDao = App.getDB().downloadDao();
        StringBuilder sb = new StringBuilder();
        sb.append(item == null ? null : item.tid);
        sb.append('_');
        sb.append((Object) (item != null ? item.id : null));
        Download findByType = downloadDao.findByType(2, sb.toString());
        if (findByType != null) {
            int statue = findByType.getStatue();
            if (statue != 1) {
                if (statue == 2) {
                    DownloadModel downloadModel = new DownloadModel(findByType);
                    downloadModel.path = Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByType.getPath(), findByType.getTitle(), Constant.DIR_DOWNLOAD));
                    if (!new File(downloadModel.path).exists()) {
                        ToastUtils.showShort("File not exist,please re-download", new Object[0]);
                        return;
                    }
                    TvDetail tvDetail = new TvDetail(downloadModel);
                    if (Network.isConnected(tvDownloadActivity)) {
                        this$0.checkOnlineDevice(tvDetail);
                        return;
                    }
                    Bundle build = ParamsUtils.newBuilder().addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).build();
                    Intent intent = new Intent(tvDownloadActivity, TvPlayerActivity.class);
                    intent.putExtras(build);
                    this$0.startActivity(intent);
                    return;
                } else if (statue != 3) {
                    return;
                }
            }
            this$0.showDownload(new DownloadModel(findByType));
            return;
        }
        if (!(item != null && item.code_file == 1)) {
            ToastUtils.showShort("Not Available", new Object[0]);
        } else if (!SettingManager.INSTANCE.isAutoDownloadSelectQuality()) {
            TvDetail tvDetail2 = this$0.tvDetail;
            if (tvDetail2 != null) {
                tvDetail2.seasonDetail = item;
            }
            PlayerStrategy playerStrategy = new PlayerStrategy();
            playerStrategy.setInstace(this$0.tvDetail);
            ChooseActivity.Companion.start(tvDownloadActivity, true, playerStrategy);
        } else {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", item.tid, String.valueOf(item.season), String.valueOf(item.episode), str2, str).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0))).subscribe(new Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$initListener$4$1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    TvDownloadActivity.this.showLoadingView();
                }

                @Override // io.reactivex.Observer
                public void onNext(TvDetail tvDetail1) {
                    TvDetail tvDetail3;
                    TvDetail tvDetail4;
                    Pair autoDownloadUrl;
                    TvDetail tvDetail5;
                    Intrinsics.checkNotNullParameter(tvDetail1, "tvDetail1");
                    try {
                        tvDetail3 = TvDownloadActivity.this.tvDetail;
                        if (tvDetail3 != null) {
                            tvDetail3.list = tvDetail1.list;
                        }
                        tvDetail4 = TvDownloadActivity.this.tvDetail;
                        if (tvDetail4 != null) {
                            tvDetail4.seasonDetail = item;
                        }
                        TvDownloadActivity tvDownloadActivity2 = TvDownloadActivity.this;
                        ArrayList arrayList = tvDetail1.list;
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        autoDownloadUrl = tvDownloadActivity2.getAutoDownloadUrl(arrayList);
                        tvDetail5 = TvDownloadActivity.this.tvDetail;
                        if (tvDetail5 == null) {
                            return;
                        }
                        TvDownloadActivity tvDownloadActivity3 = TvDownloadActivity.this;
                        S s = autoDownloadUrl.second;
                        Intrinsics.checkNotNullExpressionValue(s, "autoDownloadUrl.second");
                        F f = autoDownloadUrl.first;
                        Intrinsics.checkNotNullExpressionValue(f, "autoDownloadUrl.first");
                        tvDownloadActivity3.checkDownload((BaseMediaModel.DownloadFile) s, tvDetail5, ((Number) f).intValue());
                    } catch (Exception unused) {
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    TvDownloadActivity.this.hideLoadingView();
                    ToastUtils.showShort(Intrinsics.stringPlus("Download failed:", e.getMessage()), new Object[0]);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m848initListener$lambda3(TvDownloadActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseSeasonEpisodeDialog.Companion companion = ChooseSeasonEpisodeDialog.Companion;
        String[] strArr = this$0.seasons;
        String[] strArr2 = null;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasons");
            strArr = null;
        }
        String[] strArr3 = this$0.years;
        if (strArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("years");
        } else {
            strArr2 = strArr3;
        }
        companion.newInstance(strArr, strArr2, String.valueOf(this$0.currSeason), String.valueOf(this$0.currYear)).show(this$0.getSupportFragmentManager(), "ChooseSeasonEpisodeDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final boolean m849initListener$lambda4(TvDownloadActivity this$0, BaseQuickAdapter adapter, View view, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TvDownloadAdapter tvDownloadAdapter = this$0.adapter;
        if (tvDownloadAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tvDownloadAdapter = null;
        }
        TvDetail.SeasonDetail item = tvDownloadAdapter.getItem(i);
        if (item != null) {
            TvDetail tvDetail = this$0.tvDetail;
            String str2 = "";
            if (tvDetail != null && (str = tvDetail.id) != null) {
                str2 = str;
            }
            this$0.showAddWatchedFlagDialog(str2, String.valueOf(item.season), String.valueOf(item.episode));
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, BaseMediaModel.DownloadFile> getAutoDownloadUrl(List<? extends BaseMediaModel.DownloadFile> list) {
        Object[] array = StringsKt.split$default((CharSequence) SettingManager.INSTANCE.getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null).toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            String currDownloadRule = SettingManager.INSTANCE.getCurrDownloadRule();
            if (list.size() == 1) {
                return new Pair<>(0, list.get(0));
            }
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                i++;
                int size = list.size();
                int i2 = 0;
                while (i2 < size) {
                    int i3 = i2 + 1;
                    BaseMediaModel.DownloadFile downloadFile = list.get(i2);
                    if (StringsKt.equals("ORG", currDownloadRule, true)) {
                        if (!TextUtils.isEmpty(downloadFile.path) && downloadFile.original == 1) {
                            return new Pair<>(Integer.valueOf(i2), downloadFile);
                        }
                    } else if (!TextUtils.isEmpty(downloadFile.path) && StringsKt.equals(str, downloadFile.real_quality, true) && downloadFile.original != 1) {
                        return new Pair<>(Integer.valueOf(i2), downloadFile);
                    }
                    i2 = i3;
                }
            }
            return new Pair<>(0, list.get(0));
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkDownload(BaseMediaModel.DownloadFile downloadFile, TvDetail tvDetail, int i) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            if (!new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)).exists()) {
                ToastUtils.showShort("SD Card is invalid", new Object[0]);
                return;
            } else {
                checkEnoughSpace(downloadFile, tvDetail, i);
                return;
            }
        }
        checkEnoughSpace(downloadFile, tvDetail, i);
    }

    private final void checkEnoughSpace(BaseMediaModel.DownloadFile downloadFile, final TvDetail tvDetail, final int i) {
        ((ObservableSubscribeProxy) Observable.just(downloadFile).map($$Lambda$TvDownloadActivity$2opd90Z6e70bNj5M3jWEHLIUyBU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$checkEnoughSpace$2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                onSuccess(bool.booleanValue());
            }

            public void onSuccess(boolean z) {
                TvDownloadActivity.this.hideLoadingView();
                Intent intent = new Intent(TvDownloadActivity.this, SubtitleDownloadService.class);
                if (z) {
                    intent.putExtra("id", tvDetail.id);
                    intent.putExtra("name", tvDetail.title);
                    intent.putExtra("episode", tvDetail.seasonDetail.episode);
                    intent.putExtra("season", tvDetail.seasonDetail.season);
                    TvDownloadActivity.this.startService(intent);
                    tvDetail.saveInDao(i, TvDownloadActivity.this);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TvDownloadActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Download failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkEnoughSpace$lambda-5  reason: not valid java name */
    public static final Boolean m840checkEnoughSpace$lambda5(BaseMediaModel.DownloadFile downloadFile) {
        Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
        URLConnection openConnection = new URL(downloadFile.path).openConnection();
        if (openConnection == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.connect();
        long contentLength = httpURLConnection.getContentLength();
        if (contentLength < 0) {
            String headerField = httpURLConnection.getHeaderField("Content-Length");
            Intrinsics.checkNotNullExpressionValue(headerField, "connection.getHeaderField(\"Content-Length\")");
            contentLength = Long.parseLong(headerField);
        }
        httpURLConnection.disconnect();
        boolean z = false;
        if (FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD) > contentLength) {
            z = true;
        } else {
            ToastUtils.showShort("Not enough space to download next episode", new Object[0]);
        }
        return Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getPlayUrl(final TvDetail tvDetail) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$IhqryLLpEIl2TDJAq9i9cRAth50
            @Override // java.lang.Runnable
            public final void run() {
                TvDownloadActivity.m842getPlayUrl$lambda8(TvDetail.this, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayUrl$lambda-8  reason: not valid java name */
    public static final void m842getPlayUrl$lambda8(final TvDetail tv2, final TvDownloadActivity this$0) {
        Intrinsics.checkNotNullParameter(tv2, "$tv");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ObservableSubscribeProxy) Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", tv2.id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$qucSH6UU1X9bsNE9QSN8ZSOXzNU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m843getPlayUrl$lambda8$lambda7;
                m843getPlayUrl$lambda8$lambda7 = TvDownloadActivity.m843getPlayUrl$lambda8$lambda7(TvDetail.this, (TvDetail) obj);
                return m843getPlayUrl$lambda8$lambda7;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this$0))).subscribe(new Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$getPlayUrl$1$2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TvDownloadActivity.this.showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(TvDetail tvDetail) {
                Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
                TvDownloadActivity.this.hideLoadingView();
                Bundle build = ParamsUtils.newBuilder().addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tv2.seasonDetail.season).addParam("videoplayer_episode", tv2.seasonDetail.episode).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build();
                Intrinsics.checkNotNullExpressionValue(build, "newBuilder()\n           …                 .build()");
                Intent intent = new Intent(TvDownloadActivity.this, TvPlayerActivity.class);
                intent.putExtras(build);
                TvDownloadActivity.this.startActivity(intent);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TvDownloadActivity.this.hideLoadingView();
                Bundle build = ParamsUtils.newBuilder().addParam("videoplayer_params", tv2).addParam(VideoPlayerActivity.VIDEO_ID, tv2.seasonDetail.season).addParam("videoplayer_episode", tv2.seasonDetail.episode).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build();
                Intrinsics.checkNotNullExpressionValue(build, "newBuilder()\n           …                 .build()");
                Intent intent = new Intent(TvDownloadActivity.this, TvPlayerActivity.class);
                intent.putExtras(build);
                TvDownloadActivity.this.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayUrl$lambda-8$lambda-7  reason: not valid java name */
    public static final ObservableSource m843getPlayUrl$lambda8$lambda7(final TvDetail tv2, final TvDetail tvDetail) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(tv2, "$tv");
        Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        return Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", tv2.id, tv2.seasonDetail.season + "", tv2.seasonDetail.episode + "", str2, str).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$8FOnIWyirFDC8i_9aQis-cE-v_k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                TvDetail m844getPlayUrl$lambda8$lambda7$lambda6;
                m844getPlayUrl$lambda8$lambda7$lambda6 = TvDownloadActivity.m844getPlayUrl$lambda8$lambda7$lambda6(TvDetail.this, tvDetail, (BaseMediaModel) obj);
                return m844getPlayUrl$lambda8$lambda7$lambda6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPlayUrl$lambda-8$lambda-7$lambda-6  reason: not valid java name */
    public static final TvDetail m844getPlayUrl$lambda8$lambda7$lambda6(TvDetail tv2, TvDetail tvDetail, BaseMediaModel baseMediaModel) {
        Intrinsics.checkNotNullParameter(tv2, "$tv");
        Intrinsics.checkNotNullParameter(tvDetail, "$tvDetail");
        Intrinsics.checkNotNullParameter(baseMediaModel, "baseMediaModel");
        ArrayList arrayList = new ArrayList();
        if (tv2.list.size() > 0) {
            List<BaseMediaModel.DownloadFile> list = tv2.list;
            Intrinsics.checkNotNullExpressionValue(list, "tv.list");
            arrayList.addAll(list);
        }
        List<BaseMediaModel.DownloadFile> list2 = baseMediaModel.list;
        Intrinsics.checkNotNullExpressionValue(list2, "baseMediaModel.list");
        arrayList.addAll(list2);
        tvDetail.list = arrayList;
        return tvDetail;
    }

    private final void checkOnlineDevice(final TvDetail tvDetail) {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, tvDetail.id, SystemUtils.getUniqueId(App.getContext()), 1, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, Build.BRAND, Build.MODEL).map(new Function() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$KNDdUPKvBioVpJiejhV4ZxoamM8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Object m841checkOnlineDevice$lambda9;
                m841checkOnlineDevice$lambda9 = TvDownloadActivity.m841checkOnlineDevice$lambda9(TvDownloadActivity.this, tvDetail, (String) obj);
                return m841checkOnlineDevice$lambda9;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Object>() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$checkOnlineDevice$2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TvDownloadActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Object model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TvDownloadActivity.this.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TvDownloadActivity.this.hideLoadingView();
                TvDownloadActivity.this.getPlayUrl(tvDetail);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkOnlineDevice$lambda-9  reason: not valid java name */
    public static final Object m841checkOnlineDevice$lambda9(final TvDownloadActivity this$0, final TvDetail tv2, String s) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tv2, "$tv");
        Intrinsics.checkNotNullParameter(s, "s");
        BaseResponse baseResponse = (BaseResponse) JSON.parseObject(s, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        if (baseResponse.getCode() == -88) {
            ScreenManageDialog newInstance$default = ScreenManageDialog.Companion.newInstance$default(ScreenManageDialog.Companion, new ArrayList(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), baseResponse.getMsg(), false, 4, null);
            newInstance$default.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.tv.TvDownloadActivity$checkOnlineDevice$1$1
                @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                public void onStopDevice() {
                    TvDownloadActivity.this.getPlayUrl(tv2);
                }
            });
            newInstance$default.show(this$0.getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
            return Observable.empty();
        }
        this$0.getPlayUrl(tv2);
        return Observable.just("");
    }

    private final void showAddWatchedFlagDialog(final String str, final String str2, final String str3) {
        String[] stringArray = getResources().getStringArray(R.array.AddWatchedFlagArray);
        new ActionSheetDialog(this).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItems(Arrays.asList(Arrays.copyOf(stringArray, stringArray.length)), new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$OGaH2rcx9TU_L7fj6KvqWW9-IVw
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public final void onClick(int i) {
                TvDownloadActivity.m854showAddWatchedFlagDialog$lambda10(TvDownloadActivity.this, str2, str3, str, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAddWatchedFlagDialog$lambda-10  reason: not valid java name */
    public static final void m854showAddWatchedFlagDialog$lambda10(TvDownloadActivity this$0, String season, String episode, String id, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(season, "$season");
        Intrinsics.checkNotNullParameter(episode, "$episode");
        Intrinsics.checkNotNullParameter(id, "$id");
        if (!App.isLogin()) {
            Login2Activity.start(this$0);
            return;
        }
        int i2 = 1;
        int i3 = i - 1;
        if (i3 != 0) {
            if (i3 == 1) {
                episode = "";
            } else if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        season = "";
                        episode = season;
                    } else {
                        episode = "";
                    }
                }
                i2 = 0;
            } else {
                season = "";
                episode = season;
            }
        }
        ((TvDownloadPresenter) this$0.mPresenter).markWatched(id, i2, season, episode);
    }

    private final void showDownload(final DownloadModel downloadModel) {
        new ActionSheetDialog(this).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Cancel Download", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$pvNJImJ3ErRrw48Hv2PSs13JRPE
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public final void onClick(int i) {
                TvDownloadActivity.m855showDownload$lambda12(DownloadModel.this, this, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDownload$lambda-12  reason: not valid java name */
    public static final void m855showDownload$lambda12(DownloadModel downloadModel, final TvDownloadActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(downloadModel, "$downloadModel");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        downloadModel.startService(Constant.ACTION.MOVIE_DELETE, (Activity) this$0);
        ((RecyclerView) this$0._$_findCachedViewById(R.id.recyclerView)).postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.tv.-$$Lambda$TvDownloadActivity$6YQgPG7o2LhKl22-ibj8oWeg-4E
            @Override // java.lang.Runnable
            public final void run() {
                TvDownloadActivity.m856showDownload$lambda12$lambda11(TvDownloadActivity.this);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDownload$lambda-12$lambda-11  reason: not valid java name */
    public static final void m856showDownload$lambda12$lambda11(TvDownloadActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TvDownloadAdapter tvDownloadAdapter = this$0.adapter;
        if (tvDownloadAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tvDownloadAdapter = null;
        }
        tvDownloadAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    @Override // com.movieboxpro.android.view.dialog.ChooseSeasonEpisodeDialog.OnSeasonYearChooseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSeasonYearChoose(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "season"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "year"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 0
            int r1 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.NumberFormatException -> L44
            r5.currSeason = r1     // Catch: java.lang.NumberFormatException -> L44
            r1 = r7
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.NumberFormatException -> L44
            int r1 = r1.length()     // Catch: java.lang.NumberFormatException -> L44
            if (r1 <= 0) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = 0
        L1d:
            if (r1 == 0) goto L25
            int r1 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.NumberFormatException -> L44
            r5.currYear = r1     // Catch: java.lang.NumberFormatException -> L44
        L25:
            T extends com.movieboxpro.android.base.mvp.BaseContract$BasePresenter r1 = r5.mPresenter     // Catch: java.lang.NumberFormatException -> L44
            com.movieboxpro.android.view.activity.tv.TvDownloadPresenter r1 = (com.movieboxpro.android.view.activity.tv.TvDownloadPresenter) r1     // Catch: java.lang.NumberFormatException -> L44
            com.movieboxpro.android.model.tv.TvDetail r2 = r5.tvDetail     // Catch: java.lang.NumberFormatException -> L44
            java.lang.String r3 = ""
            if (r2 != 0) goto L31
        L2f:
            r2 = r3
            goto L36
        L31:
            java.lang.String r2 = r2.id     // Catch: java.lang.NumberFormatException -> L44
            if (r2 != 0) goto L36
            goto L2f
        L36:
            int r4 = r5.currSeason     // Catch: java.lang.NumberFormatException -> L44
            if (r4 != 0) goto L3b
            r6 = r3
        L3b:
            int r4 = r5.currYear     // Catch: java.lang.NumberFormatException -> L44
            if (r4 != 0) goto L40
            r7 = r3
        L40:
            r1.getEpisodes(r2, r6, r7)     // Catch: java.lang.NumberFormatException -> L44
            goto L4b
        L44:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r7 = "Load failed:invalid season"
            com.movieboxpro.android.utils.ToastUtils.showShort(r7, r6)
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.tv.TvDownloadActivity.onSeasonYearChoose(java.lang.String, java.lang.String):void");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ((CustomSwitchView) _$_findCachedViewById(R.id.switchView)).setChecked(SettingManager.INSTANCE.isAutoDownloadSelectQuality());
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        String str;
        this.tvDetail = (TvDetail) getIntent().getSerializableExtra("tvDetail");
        this.currSeason = getIntent().getIntExtra("currSeason", 1);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Season %s", Arrays.copyOf(new Object[]{Integer.valueOf(this.currSeason)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tvCurrSeason)).setText(format);
        Serializable serializableExtra = getIntent().getSerializableExtra("data");
        TvDownloadAdapter tvDownloadAdapter = null;
        ArrayList arrayList = serializableExtra instanceof ArrayList ? (ArrayList) serializableExtra : null;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        String[] stringArrayExtra = getIntent().getStringArrayExtra("season");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        this.seasons = stringArrayExtra;
        String[] stringArrayExtra2 = getIntent().getStringArrayExtra("year");
        if (stringArrayExtra2 == null) {
            stringArrayExtra2 = new String[0];
        }
        this.years = stringArrayExtra2;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvSeasons);
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[1];
        String[] strArr = this.seasons;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasons");
            strArr = null;
        }
        objArr[0] = Integer.valueOf(strArr.length);
        String format2 = String.format("%s seasons", Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        textView.setText(format2);
        this.adapter = new TvDownloadAdapter(arrayList);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        TvDownloadAdapter tvDownloadAdapter2 = this.adapter;
        if (tvDownloadAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            tvDownloadAdapter = tvDownloadAdapter2;
        }
        recyclerView2.setAdapter(tvDownloadAdapter);
        registerDownloadReceiver();
        if (arrayList.isEmpty()) {
            TvDownloadPresenter tvDownloadPresenter = (TvDownloadPresenter) this.mPresenter;
            TvDetail tvDetail = this.tvDetail;
            if (tvDetail == null || (str = tvDetail.id) == null) {
                str = "";
            }
            tvDownloadPresenter.getEpisodes(str, String.valueOf(this.currSeason), "");
        }
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(getIntent().getStringExtra("name"));
    }

    @Override // com.movieboxpro.android.view.activity.tv.TvDownloadContract.View
    public void showEpisodes(List<TvDetail.SeasonDetail> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        TvDownloadAdapter tvDownloadAdapter = this.adapter;
        if (tvDownloadAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tvDownloadAdapter = null;
        }
        tvDownloadAdapter.setList(list);
        if (this.currSeason == 0) {
            if (this.currYear == -1) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("Released", Arrays.copyOf(new Object[0], 0));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) _$_findCachedViewById(R.id.tvCurrSeason)).setText(format);
                return;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("Year %s", Arrays.copyOf(new Object[]{Integer.valueOf(this.currYear)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            ((TextView) _$_findCachedViewById(R.id.tvCurrSeason)).setText(format2);
            return;
        }
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String format3 = String.format("Season %s", Arrays.copyOf(new Object[]{Integer.valueOf(this.currSeason)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tvCurrSeason)).setText(format3);
    }

    private final void registerDownloadReceiver() {
        this.mReceiver = new DownloadProgressReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_FAILURE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PAUSED);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_STARTED);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_READY);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        DownloadProgressReceiver downloadProgressReceiver = this.mReceiver;
        Intrinsics.checkNotNull(downloadProgressReceiver);
        localBroadcastManager.registerReceiver(downloadProgressReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TvDownloadPresenter bindPresenter() {
        return new TvDownloadPresenter(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        DownloadProgressReceiver downloadProgressReceiver = this.mReceiver;
        if (downloadProgressReceiver == null) {
            return;
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadProgressReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TvDownloadActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadActivity$DownloadProgressReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/movieboxpro/android/view/activity/tv/TvDownloadActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class DownloadProgressReceiver extends BroadcastReceiver {
        final /* synthetic */ TvDownloadActivity this$0;

        public DownloadProgressReceiver(TvDownloadActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            TvDownloadAdapter tvDownloadAdapter = null;
            Download findByType = App.getDB().downloadDao().findByType(2, intent == null ? null : intent.getStringExtra(Constant.Download.PARAMS_KEY_MOVIE_ID));
            if (findByType != null) {
                TvDownloadActivity tvDownloadActivity = this.this$0;
                TvDownloadAdapter tvDownloadAdapter2 = tvDownloadActivity.adapter;
                if (tvDownloadAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    tvDownloadAdapter2 = null;
                }
                int i = 0;
                for (Object obj : tvDownloadAdapter2.getData()) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    TvDetail.SeasonDetail seasonDetail = (TvDetail.SeasonDetail) obj;
                    if (seasonDetail.season == findByType.getSeason() && seasonDetail.episode == findByType.getEpisode()) {
                        TvDownloadAdapter tvDownloadAdapter3 = tvDownloadActivity.adapter;
                        if (tvDownloadAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            tvDownloadAdapter = tvDownloadAdapter3;
                        }
                        tvDownloadAdapter.notifyItemChanged(i);
                        return;
                    }
                    i = i2;
                }
            }
        }
    }

    /* compiled from: TvDownloadActivity.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JU\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/activity/tv/TvDownloadActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "list", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "year", "", "", "season", "currSeason", "", "name", "(Landroid/content/Context;Lcom/movieboxpro/android/model/tv/TvDetail;Ljava/util/List;[Ljava/lang/String;[Ljava/lang/String;ILjava/lang/String;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, TvDetail tvDetail, List<? extends TvDetail.SeasonDetail> list, String[] year, String[] season, int i, String name) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(year, "year");
            Intrinsics.checkNotNullParameter(season, "season");
            Intrinsics.checkNotNullParameter(name, "name");
            Intent intent = new Intent(context, TvDownloadActivity.class);
            intent.putExtra("data", new ArrayList(list));
            intent.putExtra("tvDetail", tvDetail);
            intent.putExtra("year", year);
            intent.putExtra("season", season);
            intent.putExtra("name", name);
            intent.putExtra("currSeason", i);
            context.startActivity(intent);
        }
    }
}
