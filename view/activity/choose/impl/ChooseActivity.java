package com.movieboxpro.android.view.activity.choose.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.livedata.DownloadPathLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.MediaFunction;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.presenter.ChromeCastPresenter;
import com.movieboxpro.android.presenter.activity.PsChoose;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.service.SubtitleDownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.MemoryUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.choose.IChoose;
import com.movieboxpro.android.view.activity.download.DownloadingActivity;
import com.movieboxpro.android.view.activity.movie.MovieChooseActivity;
import com.movieboxpro.android.view.dialog.ChooseDownloadPathDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.PlayAddToCastDialog;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 f2\u00020\u00012\u00020\u0002:\u0003efgB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010H\u0002J\u0010\u0010@\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010H\u0002J\u0016\u0010A\u001a\u00020\u00182\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0010\u0010B\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010H\u0002J&\u0010C\u001a\u00020>2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u00052\u0006\u0010F\u001a\u00020G2\u0006\u0010?\u001a\u00020\u0010H\u0016J\b\u0010H\u001a\u00020\u0010H\u0014J\b\u0010I\u001a\u00020>H\u0016J\b\u0010J\u001a\u00020>H\u0002J\b\u0010K\u001a\u00020>H\u0016J\b\u0010L\u001a\u00020>H\u0002J\b\u0010M\u001a\u00020>H\u0016J\b\u0010N\u001a\u00020>H\u0016J\u0018\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0016J\"\u0010U\u001a\u00020>2\u0006\u0010V\u001a\u00020\u00102\u0006\u0010W\u001a\u00020\u00102\b\u0010X\u001a\u0004\u0018\u00010YH\u0014J\b\u0010Z\u001a\u00020>H\u0014J\u0010\u0010[\u001a\u00020>2\u0006\u0010\\\u001a\u00020PH\u0007J\u0010\u0010]\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010H\u0003J\u0010\u0010^\u001a\u00020>2\u0006\u0010_\u001a\u00020`H\u0016J\u001a\u0010a\u001a\u00020>2\b\u0010\\\u001a\u0004\u0018\u00010P2\u0006\u0010b\u001a\u00020\u0018H\u0002J\u000e\u0010c\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0010J\b\u0010d\u001a\u00020>H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR,\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\b\"\u0004\b \u0010\nR \u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\nR\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0014\u0010*\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060-X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u0004\u0018\u0001058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lcom/movieboxpro/android/view/activity/choose/impl/ChooseActivity;", "Lcom/movieboxpro/android/base/BaseActivity;", "Lcom/movieboxpro/android/view/activity/choose/IChoose;", "()V", "H264", "", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "getH264", "()Ljava/util/List;", "setH264", "(Ljava/util/List;)V", "H265", "getH265", "setH265", DownloadInfo.DOWNLOAD, "Ljava/util/LinkedHashMap;", "", "getDownload", "()Ljava/util/LinkedHashMap;", "setDownload", "(Ljava/util/LinkedHashMap;)V", "flShowOrg", "Landroid/widget/FrameLayout;", ChooseActivity.ISDOWNLOAD, "", "ivEdit", "Landroidx/appcompat/widget/AppCompatImageView;", "ivMoreDefinition", "Landroid/widget/ImageView;", "list", "", "getList", "setList", "list2", "getList2", "setList2", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getListener", "()Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "setListener", "(Lcom/chad/library/adapter/base/listener/OnItemClickListener;)V", "llPath", "Landroid/widget/LinearLayout;", "mAdapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "mBackRound", "mClose", "Landroid/widget/TextView;", "mDowload", "mPresenter", "Lcom/movieboxpro/android/presenter/activity/PsChoose;", "mRecycler", "Landroidx/recyclerview/widget/RecyclerView;", "mSize", "player", "Lcom/movieboxpro/android/model/PlayerStrategy;", "showOrg", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "tvPath", "checkDownloadPath", "", "position", "checkEnoughSpace", "checkHaveOrg", "doClick", "getSrt", "srtLists", "Lcom/movieboxpro/android/model/common/Srt;", FileDownloadService.PARAMS_KEY_PATH, "", "getStatusTintColor", "hideSwipeView", "initAdapter", "initData", "initDownloadPath", "initPresenter", "initView", "loadView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onViewClicked", "view", "requestPermission", "setPath", "downloadFile", "Lcom/movieboxpro/android/model/BaseMediaModel;", "setViewVisibility", "visible", "shouldLoad", "showSwipeView", "ChooseAdapter", "Companion", "Item1ViewHolder", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseActivity extends BaseActivity implements IChoose {
    public static final Companion Companion = new Companion(null);
    public static final String ISDOWNLOAD = "isDownload";
    public static final int REQUEST_PERMISSION_SETTING = 100;
    @BindView(R.id.flShowOrg)
    public FrameLayout flShowOrg;
    private boolean isDownload;
    @BindView(R.id.ivEdit)
    public AppCompatImageView ivEdit;
    @BindView(R.id.ivMoreDefinition)
    public ImageView ivMoreDefinition;
    @BindView(R.id.llPath)
    public LinearLayout llPath;
    private CommMultiBaseAdapter<BaseMediaModel.DownloadFile> mAdapter;
    @BindView(R.id.choose_backround)
    public LinearLayout mBackRound;
    @BindView(R.id.choose_close)
    public TextView mClose;
    @BindView(R.id.choose_download)
    public TextView mDowload;
    private PsChoose mPresenter;
    @BindView(R.id.recycler_normal)
    public RecyclerView mRecycler;
    @BindView(R.id.choose_size)
    public TextView mSize;
    private PlayerStrategy player;
    private boolean showOrg;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvPath)
    public TextView tvPath;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private List<BaseMediaModel.DownloadFile> list = new ArrayList();
    private List<? extends BaseMediaModel.DownloadFile> list2 = new ArrayList();
    private List<? extends BaseMediaModel.DownloadFile> H265 = new ArrayList();
    private List<? extends BaseMediaModel.DownloadFile> H264 = new ArrayList();
    private LinkedHashMap<Integer, List<BaseMediaModel.DownloadFile>> download = new LinkedHashMap<>();
    private OnItemClickListener listener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$OuV03ajoV77vvNCLT1Id_jXoit8
        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
        public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            ChooseActivity.m332listener$lambda0(ChooseActivity.this, baseQuickAdapter, view, i);
        }
    };

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

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    /* loaded from: classes3.dex */
    public final class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mTheme = (ImageView) Utils.findOptionalViewAsType(view, R.id.layout_choose_theme, "field 'mTheme'", ImageView.class);
            item1ViewHolder.mFormat = (ImageView) Utils.findOptionalViewAsType(view, R.id.layout_choose_format, "field 'mFormat'", ImageView.class);
            item1ViewHolder.mFormatDark = (ImageView) Utils.findOptionalViewAsType(view, R.id.layout_choose_format_dark, "field 'mFormatDark'", ImageView.class);
            item1ViewHolder.mSize = (TextView) Utils.findOptionalViewAsType(view, R.id.layout_choose_size, "field 'mSize'", TextView.class);
            item1ViewHolder.mTime = (TextView) Utils.findOptionalViewAsType(view, R.id.layout_choose_time, "field 'mTime'", TextView.class);
            item1ViewHolder.mName = (TextView) Utils.findOptionalViewAsType(view, R.id.layout_choose_name, "field 'mName'", TextView.class);
            item1ViewHolder.mDownlaod = (ImageView) Utils.findOptionalViewAsType(view, R.id.layout_choose_download, "field 'mDownlaod'", ImageView.class);
            item1ViewHolder.mSelect = (TextView) Utils.findOptionalViewAsType(view, R.id.layout_choose_select, "field 'mSelect'", TextView.class);
            item1ViewHolder.mVip = (ImageView) Utils.findOptionalViewAsType(view, R.id.layout_choose_vip, "field 'mVip'", ImageView.class);
            item1ViewHolder.tvRate = (TextView) Utils.findOptionalViewAsType(view, R.id.tv_rate, "field 'tvRate'", TextView.class);
            item1ViewHolder.ivOrigin = (ImageView) Utils.findOptionalViewAsType(view, R.id.ivOrigin, "field 'ivOrigin'", ImageView.class);
            item1ViewHolder.ivHdr = (ImageView) Utils.findOptionalViewAsType(view, R.id.ivHdr, "field 'ivHdr'", ImageView.class);
            item1ViewHolder.ivColorBit = (ImageView) Utils.findOptionalViewAsType(view, R.id.ivColorBit, "field 'ivColorBit'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mTheme = null;
            item1ViewHolder.mFormat = null;
            item1ViewHolder.mFormatDark = null;
            item1ViewHolder.mSize = null;
            item1ViewHolder.mTime = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.mDownlaod = null;
            item1ViewHolder.mSelect = null;
            item1ViewHolder.mVip = null;
            item1ViewHolder.tvRate = null;
            item1ViewHolder.ivOrigin = null;
            item1ViewHolder.ivHdr = null;
            item1ViewHolder.ivColorBit = null;
        }
    }

    @OnClick({R.id.choose_download, R.id.choose_close, R.id.flShowOrg, R.id.ivEdit})
    public final void onViewClicked(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = null;
        switch (view.getId()) {
            case R.id.choose_close /* 2131296521 */:
                finish();
                this.showOrg = true;
                CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter2 = this.mAdapter;
                if (commMultiBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                } else {
                    commMultiBaseAdapter = commMultiBaseAdapter2;
                }
                commMultiBaseAdapter.notifyDataSetChanged();
                FrameLayout frameLayout = this.flShowOrg;
                Intrinsics.checkNotNull(frameLayout);
                frameLayout.setVisibility(8);
                SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
                Intrinsics.checkNotNull(swipeRefreshLayout);
                swipeRefreshLayout.setRefreshing(false);
                return;
            case R.id.choose_download /* 2131296522 */:
                route(DownloadingActivity.class);
                return;
            case R.id.flShowOrg /* 2131296757 */:
                this.showOrg = true;
                CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter3 = this.mAdapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                } else {
                    commMultiBaseAdapter = commMultiBaseAdapter3;
                }
                commMultiBaseAdapter.notifyDataSetChanged();
                FrameLayout frameLayout2 = this.flShowOrg;
                Intrinsics.checkNotNull(frameLayout2);
                frameLayout2.setVisibility(8);
                SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
                Intrinsics.checkNotNull(swipeRefreshLayout2);
                swipeRefreshLayout2.setRefreshing(false);
                return;
            case R.id.ivEdit /* 2131296951 */:
                ChooseDownloadPathDialog chooseDownloadPathDialog = new ChooseDownloadPathDialog();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                chooseDownloadPathDialog.show(supportFragmentManager, ChooseDownloadPathDialog.class.getSimpleName());
                return;
            default:
                return;
        }
    }

    public final List<BaseMediaModel.DownloadFile> getList() {
        return this.list;
    }

    public final void setList(List<BaseMediaModel.DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public final List<BaseMediaModel.DownloadFile> getList2() {
        return this.list2;
    }

    public final void setList2(List<? extends BaseMediaModel.DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list2 = list;
    }

    public final List<BaseMediaModel.DownloadFile> getH265() {
        return this.H265;
    }

    public final void setH265(List<? extends BaseMediaModel.DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.H265 = list;
    }

    public final List<BaseMediaModel.DownloadFile> getH264() {
        return this.H264;
    }

    public final void setH264(List<? extends BaseMediaModel.DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.H264 = list;
    }

    public final LinkedHashMap<Integer, List<BaseMediaModel.DownloadFile>> getDownload() {
        return this.download;
    }

    public final void setDownload(LinkedHashMap<Integer, List<BaseMediaModel.DownloadFile>> linkedHashMap) {
        Intrinsics.checkNotNullParameter(linkedHashMap, "<set-?>");
        this.download = linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: listener$lambda-0  reason: not valid java name */
    public static final void m332listener$lambda0(ChooseActivity this$0, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        this$0.doClick(i);
    }

    public final OnItemClickListener getListener() {
        return this.listener;
    }

    public final void setListener(OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "<set-?>");
        this.listener = onItemClickListener;
    }

    private final void requestPermission(final int i) {
        new RxPermissions(this).requestEachCombined("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$fRo_W4gSjA7o68r955-HwUTcNDI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ChooseActivity.m333requestPermission$lambda2(ChooseActivity.this, i, (Permission) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-2  reason: not valid java name */
    public static final void m333requestPermission$lambda2(final ChooseActivity this$0, final int i, Permission permission) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (permission.granted) {
            this$0.doClick(i);
        } else if (permission.shouldShowRequestPermissionRationale) {
            new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("We need write and read storage to download?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$suHVpNNl-Q4geh0IVmIVj21D6e4
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    ChooseActivity.m334requestPermission$lambda2$lambda1(ChooseActivity.this, i);
                }
            }).create().show();
        } else {
            new MsgHintDialog.Builder(this$0).setTitle("Note").setContent("We need write and read storage to download,you have denied permission,go setting?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity$requestPermission$1$2
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    try {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", ChooseActivity.this.getPackageName(), null));
                        ChooseActivity.this.startActivityForResult(intent, 100);
                    } catch (Exception unused) {
                        ToastUtils.showShort("Please grant write and read permissions manually", new Object[0]);
                    }
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-2$lambda-1  reason: not valid java name */
    public static final void m334requestPermission$lambda2$lambda1(ChooseActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestPermission(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void doClick(final int r8) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity.doClick(int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doClick$lambda-3  reason: not valid java name */
    public static final void m324doClick$lambda3(ChooseActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkDownloadPath(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doClick$lambda-4  reason: not valid java name */
    public static final void m325doClick$lambda4(ChooseActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkDownloadPath(i);
    }

    private final void checkEnoughSpace(final int i) {
        ((ObservableSubscribeProxy) Observable.just(Integer.valueOf(i)).map(new Function() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$hwu_DqCIUlvMIMva8O35zl0H3Xs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Boolean m323checkEnoughSpace$lambda5;
                m323checkEnoughSpace$lambda5 = ChooseActivity.m323checkEnoughSpace$lambda5(ChooseActivity.this, i, (Integer) obj);
                return m323checkEnoughSpace$lambda5;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity$checkEnoughSpace$2
            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Boolean bool) {
                onNext(bool.booleanValue());
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ChooseActivity.this.showLoading();
            }

            public void onNext(boolean z) {
                PlayerStrategy playerStrategy;
                PlayerStrategy playerStrategy2;
                PlayerStrategy playerStrategy3;
                PlayerStrategy playerStrategy4;
                PlayerStrategy playerStrategy5;
                Intent intent = new Intent(ChooseActivity.this, SubtitleDownloadService.class);
                if (z) {
                    playerStrategy = ChooseActivity.this.player;
                    PlayerStrategy playerStrategy6 = null;
                    if (playerStrategy == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("player");
                        playerStrategy = null;
                    }
                    if (playerStrategy.getInstace() instanceof TvDetail) {
                        playerStrategy3 = ChooseActivity.this.player;
                        if (playerStrategy3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("player");
                            playerStrategy3 = null;
                        }
                        MediaFunction instace = playerStrategy3.getInstace();
                        if (instace == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetail");
                        }
                        TvDetail tvDetail = (TvDetail) instace;
                        intent.putExtra("id", tvDetail.id);
                        intent.putExtra("name", tvDetail.title);
                        playerStrategy4 = ChooseActivity.this.player;
                        if (playerStrategy4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("player");
                            playerStrategy4 = null;
                        }
                        intent.putExtra("episode", playerStrategy4.getEpisode());
                        playerStrategy5 = ChooseActivity.this.player;
                        if (playerStrategy5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("player");
                        } else {
                            playerStrategy6 = playerStrategy5;
                        }
                        intent.putExtra("season", playerStrategy6.getSeason());
                    } else {
                        playerStrategy2 = ChooseActivity.this.player;
                        if (playerStrategy2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("player");
                        } else {
                            playerStrategy6 = playerStrategy2;
                        }
                        MediaFunction instace2 = playerStrategy6.getInstace();
                        if (instace2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieDetail");
                        }
                        MovieDetail movieDetail = (MovieDetail) instace2;
                        intent.putExtra("id", movieDetail.id);
                        intent.putExtra("name", movieDetail.title);
                    }
                    ChooseActivity.this.startService(intent);
                    ChooseActivity.this.shouldLoad(i);
                    ChooseActivity.this.finish();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ChooseActivity.this.hideLoading();
                ToastUtils.showShort(Intrinsics.stringPlus("Download failed:", e.getMessage()), new Object[0]);
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                ChooseActivity.this.hideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d0  */
    /* renamed from: checkEnoughSpace$lambda-5  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Boolean m323checkEnoughSpace$lambda5(com.movieboxpro.android.view.activity.choose.impl.ChooseActivity r4, int r5, java.lang.Integer r6) {
        /*
            java.lang.String r6 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r6)
            com.movieboxpro.android.model.PlayerStrategy r6 = r4.player
            r0 = 0
            java.lang.String r1 = "player"
            if (r6 != 0) goto L10
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r0
        L10:
            com.movieboxpro.android.db.entity.Download r6 = r6.getDownload()
            r2 = 0
            if (r6 != 0) goto Ld8
            if (r5 >= 0) goto L25
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "Path is Empty"
            com.movieboxpro.android.utils.ToastUtils.showShort(r5, r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            return r4
        L25:
            com.movieboxpro.android.model.PlayerStrategy r6 = r4.player
            if (r6 != 0) goto L2c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L2c:
            com.movieboxpro.android.model.PlayerStrategy r6 = r4.player
            if (r6 != 0) goto L34
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r0
        L34:
            int r6 = r6.getSeason()
            if (r6 <= 0) goto L56
            com.movieboxpro.android.model.PlayerStrategy r6 = r4.player
            if (r6 != 0) goto L42
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r0
        L42:
            int r6 = r6.getEpisode()
            if (r6 <= 0) goto L56
            r6 = r4
            android.content.Context r6 = (android.content.Context) r6
            java.lang.String r1 = "DownloadTV"
            com.movieboxpro.android.utils.CommonExtKt.onMobEvent(r6, r1)
            java.lang.String r6 = "下载电视"
            com.movieboxpro.android.utils.EventUtils.event(r6)
            goto L63
        L56:
            r6 = r4
            android.content.Context r6 = (android.content.Context) r6
            java.lang.String r1 = "DownloadMovie"
            com.movieboxpro.android.utils.CommonExtKt.onMobEvent(r6, r1)
            java.lang.String r6 = "下载电影"
            com.movieboxpro.android.utils.EventUtils.event(r6)
        L63:
            com.movieboxpro.android.base.CommMultiBaseAdapter<com.movieboxpro.android.model.BaseMediaModel$DownloadFile> r4 = r4.mAdapter
            if (r4 != 0) goto L6d
            java.lang.String r4 = "mAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L6e
        L6d:
            r0 = r4
        L6e:
            java.lang.Object r4 = r0.getItem(r5)
            com.movieboxpro.android.model.BaseMediaModel$DownloadFile r4 = (com.movieboxpro.android.model.BaseMediaModel.DownloadFile) r4
            java.net.URL r5 = new java.net.URL
            java.lang.String r4 = r4.path
            r5.<init>(r4)
            java.net.URLConnection r4 = r5.openConnection()
            if (r4 == 0) goto Ld0
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            java.lang.String r5 = "GET"
            r4.setRequestMethod(r5)
            r5 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r5)
            java.lang.String r5 = "Accept-Encoding"
            java.lang.String r6 = "identity"
            r4.setRequestProperty(r5, r6)
            r4.connect()
            int r5 = r4.getContentLength()
            long r5 = (long) r5
            r0 = 0
            int r3 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r3 >= 0) goto Lb1
            java.lang.String r5 = "Content-Length"
            java.lang.String r5 = r4.getHeaderField(r5)
            java.lang.String r6 = "connection.getHeaderField(\"Content-Length\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            long r5 = java.lang.Long.parseLong(r5)
        Lb1:
            r4.disconnect()
            java.lang.String r4 = com.movieboxpro.android.app.Constant.DIR_DOWNLOAD
            long r0 = com.movieboxpro.android.utils.FileUtils.getFsAvailableSize(r4)
            int r4 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lc4
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            return r4
        Lc4:
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "Not enough space"
            com.movieboxpro.android.utils.ToastUtils.showShort(r5, r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            return r4
        Ld0:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r4.<init>(r5)
            throw r4
        Ld8:
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "Already Download"
            com.movieboxpro.android.utils.ToastUtils.showShort(r5, r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.choose.impl.ChooseActivity.m323checkEnoughSpace$lambda5(com.movieboxpro.android.view.activity.choose.impl.ChooseActivity, int, java.lang.Integer):java.lang.Boolean");
    }

    private final void checkDownloadPath(final int i) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            if (!new File(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)).exists()) {
                new MsgHintDialog.Builder(this).setContent("SD Card is invalid,Switch to Internal Storage?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$2de8NGYSYBNV8qFrortaqZodeT0
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        ChooseActivity.m322checkDownloadPath$lambda6(ChooseActivity.this, i);
                    }
                }).create().show();
                return;
            } else {
                checkEnoughSpace(i);
                return;
            }
        }
        checkEnoughSpace(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkDownloadPath$lambda-6  reason: not valid java name */
    public static final void m322checkDownloadPath$lambda6(ChooseActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.INTERNAL_STORAGE, true);
        PrefsUtils prefsUtils = PrefsUtils.getInstance();
        prefsUtils.putString(Constant.Prefs.DOWNLOAD_DIR, Constant.DIR + ((Object) File.separator) + DownloadInfo.DOWNLOAD);
        Constant.DIR_DOWNLOAD = Constant.DIR + ((Object) File.separator) + DownloadInfo.DOWNLOAD;
        this$0.checkEnoughSpace(i);
    }

    public final void shouldLoad(int i) {
        boolean areEqual;
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = this.mAdapter;
        PlayerStrategy playerStrategy = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter = null;
        }
        BaseMediaModel.DownloadFile item = commMultiBaseAdapter.getItem(i);
        int i2 = 0;
        Iterator<BaseMediaModel.DownloadFile> it = this.list.iterator();
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            BaseMediaModel.DownloadFile next = it.next();
            if (item.mmfid != null) {
                areEqual = Intrinsics.areEqual(item.mmfid, next.mmfid);
            } else {
                areEqual = Intrinsics.areEqual(item.tmfid, next.tmfid);
            }
            if (areEqual) {
                break;
            }
            i2++;
        }
        PlayerStrategy playerStrategy2 = this.player;
        if (playerStrategy2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy2 = null;
        }
        if (playerStrategy2.getDownload() != null) {
            showToast("Already Download");
        } else if (i < 0) {
            showToast("Path is Empty");
        } else {
            PlayerStrategy playerStrategy3 = this.player;
            if (playerStrategy3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
            } else {
                playerStrategy = playerStrategy3;
            }
            playerStrategy.SaveInDao(i2, this.activity);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        View inflate = inflater.inflate(R.layout.activity_choosemovie, container, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…emovie, container, false)");
        return inflate;
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        this.mPresenter = new PsChoose(this.activity, this);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.setEnabled(false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        initAdapter();
        CommonUtils.initSwipeColor(this.swipeRefreshLayout);
        Serializable serializable = getSerializable(ISDOWNLOAD, (Serializable) false);
        Intrinsics.checkNotNullExpressionValue(serializable, "getSerializable(ISDOWNLOAD, false)");
        this.isDownload = ((Boolean) serializable).booleanValue();
        Serializable serializable2 = getSerializable(MovieChooseActivity.CHOOSEMOVIE, new PlayerStrategy());
        Intrinsics.checkNotNullExpressionValue(serializable2, "getSerializable(MovieCho…EMOVIE, PlayerStrategy())");
        this.player = (PlayerStrategy) serializable2;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("SSSS : ");
        PlayerStrategy playerStrategy = this.player;
        PlayerStrategy playerStrategy2 = null;
        if (playerStrategy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy = null;
        }
        sb.append((Object) playerStrategy.getId());
        sb.append(" : ");
        PlayerStrategy playerStrategy3 = this.player;
        if (playerStrategy3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy3 = null;
        }
        sb.append(playerStrategy3.getSeason());
        sb.append(" : ");
        PlayerStrategy playerStrategy4 = this.player;
        if (playerStrategy4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy4 = null;
        }
        sb.append(playerStrategy4.getEpisode());
        MLog.d(str, sb.toString());
        PsChoose psChoose = this.mPresenter;
        Intrinsics.checkNotNull(psChoose);
        PlayerStrategy playerStrategy5 = this.player;
        if (playerStrategy5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy5 = null;
        }
        psChoose.setFactory(playerStrategy5.switchBean());
        PsChoose psChoose2 = this.mPresenter;
        Intrinsics.checkNotNull(psChoose2);
        PlayerStrategy playerStrategy6 = this.player;
        if (playerStrategy6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy6 = null;
        }
        String id = playerStrategy6.getId();
        PlayerStrategy playerStrategy7 = this.player;
        if (playerStrategy7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy7 = null;
        }
        int season = playerStrategy7.getSeason();
        PlayerStrategy playerStrategy8 = this.player;
        if (playerStrategy8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
        } else {
            playerStrategy2 = playerStrategy8;
        }
        psChoose2.getPath(id, season, playerStrategy2.getEpisode());
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        Intrinsics.checkNotNull(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$CK1NXroqpyFo4zkbT_hzKUIhwkk
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ChooseActivity.m327initData$lambda8(ChooseActivity.this);
            }
        });
        if (this.isDownload) {
            LinearLayout linearLayout = this.llPath;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(0);
            initDownloadPath();
            DownloadPathLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$LRIV_QMHyQnT-rqZW4HOGOqQY80
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ChooseActivity.m328initData$lambda9(ChooseActivity.this, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-8  reason: not valid java name */
    public static final void m327initData$lambda8(ChooseActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = null;
        PlayerStrategy playerStrategy = null;
        if (this$0.showOrg) {
            PsChoose psChoose = this$0.mPresenter;
            Intrinsics.checkNotNull(psChoose);
            PlayerStrategy playerStrategy2 = this$0.player;
            if (playerStrategy2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                playerStrategy2 = null;
            }
            String id = playerStrategy2.getId();
            PlayerStrategy playerStrategy3 = this$0.player;
            if (playerStrategy3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                playerStrategy3 = null;
            }
            int season = playerStrategy3.getSeason();
            PlayerStrategy playerStrategy4 = this$0.player;
            if (playerStrategy4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
            } else {
                playerStrategy = playerStrategy4;
            }
            psChoose.getPath(id, season, playerStrategy.getEpisode());
            return;
        }
        this$0.showOrg = true;
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter2 = this$0.mAdapter;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            commMultiBaseAdapter = commMultiBaseAdapter2;
        }
        commMultiBaseAdapter.notifyDataSetChanged();
        FrameLayout frameLayout = this$0.flShowOrg;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.setVisibility(8);
        SwipeRefreshLayout swipeRefreshLayout = this$0.swipeRefreshLayout;
        Intrinsics.checkNotNull(swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-9  reason: not valid java name */
    public static final void m328initData$lambda9(ChooseActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initDownloadPath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewVisibility(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view == null ? null : view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (z) {
            layoutParams2.height = -2;
            layoutParams2.width = -1;
            CommonExtKt.visible(view);
        } else {
            CommonExtKt.gone(view);
            layoutParams2.height = 0;
            layoutParams2.width = 0;
        }
        view.setLayoutParams(layoutParams2);
    }

    private final void initAdapter() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(1, Integer.valueOf((int) R.layout.adapter_quality_title)));
        arrayList.add(new Pair(2, Integer.valueOf((int) R.layout.adapter_quality_item)));
        arrayList.add(new Pair(3, Integer.valueOf((int) R.layout.adapter_quality_line)));
        this.mAdapter = new CommMultiBaseAdapter<>(new ChooseActivity$initAdapter$1(this), ChooseActivity$initAdapter$2.INSTANCE, new ArrayList(arrayList));
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
        RecyclerView recyclerView = this.mRecycler;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        RecyclerView recyclerView2 = this.mRecycler;
        Intrinsics.checkNotNull(recyclerView2);
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = this.mAdapter;
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter = null;
        }
        recyclerView2.setAdapter(commMultiBaseAdapter);
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter3 = this.mAdapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.setOnItemClickListener(this.listener);
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter4 = this.mAdapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter4 = null;
        }
        commMultiBaseAdapter4.addChildClickViewIds(R.id.ivMore);
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter5 = this.mAdapter;
        if (commMultiBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter5;
        }
        commMultiBaseAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.choose.impl.-$$Lambda$ChooseActivity$nPGXfnVhDtBghgQFadZ0uTPes6w
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseActivity.m326initAdapter$lambda11(ChooseActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-11  reason: not valid java name */
    public static final void m326initAdapter$lambda11(ChooseActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = this$0.mAdapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter = null;
        }
        BaseMediaModel.DownloadFile item = commMultiBaseAdapter.getItem(i);
        item.originShow = true;
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter2 = this$0.mAdapter;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter2 = null;
        }
        commMultiBaseAdapter2.notifyItemChanged(i);
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter3 = this$0.mAdapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            commMultiBaseAdapter3 = null;
        }
        int i2 = 0;
        for (Object obj : commMultiBaseAdapter3.getData()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) obj;
            if (downloadFile.viewType == 2 && downloadFile.fid == item.fid) {
                downloadFile.originShow = true;
                CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter4 = this$0.mAdapter;
                if (commMultiBaseAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    commMultiBaseAdapter4 = null;
                }
                commMultiBaseAdapter4.notifyItemChanged(i2);
            }
            i2 = i3;
        }
    }

    private final void initDownloadPath() {
        String byte2FitMemorySize;
        String str;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(MemoryUtils.getAvailSpaceSize(this.context));
            Intrinsics.checkNotNullExpressionValue(byte2FitMemorySize, "byte2FitMemorySize(Memor…tAvailSpaceSize(context))");
            str = "Internal Storage";
        } else {
            byte2FitMemorySize = FileUtils.byte2FitMemorySize(FileUtils.getFsTotalSize(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DIR)));
            Intrinsics.checkNotNullExpressionValue(byte2FitMemorySize, "byte2FitMemorySize(\n    …          )\n            )");
            str = "SD Card";
        }
        TextView textView = this.tvPath;
        Intrinsics.checkNotNull(textView);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Download to:%s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        textView.setText(format);
        TextView textView2 = this.mSize;
        Intrinsics.checkNotNull(textView2);
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("Free:%s", Arrays.copyOf(new Object[]{byte2FitMemorySize}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        textView2.setText(format2);
    }

    @Override // com.movieboxpro.android.view.activity.choose.IChoose
    public void setPath(BaseMediaModel downloadFile) {
        List<BaseMediaModel.DownloadFile> list;
        Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
        MLog.d(this.TAG, Intrinsics.stringPlus("!!!", Boolean.valueOf(downloadFile.list != null && downloadFile.list.size() > 0)));
        PlayerStrategy playerStrategy = this.player;
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter = null;
        if (playerStrategy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy = null;
        }
        playerStrategy.addDonwload(downloadFile);
        this.list.clear();
        List<BaseMediaModel.DownloadFile> list2 = this.list;
        List<BaseMediaModel.DownloadFile> list3 = downloadFile.list;
        Intrinsics.checkNotNullExpressionValue(list3, "downloadFile.list");
        list2.addAll(list3);
        try {
            CastSession currentCastSession = CastContext.getSharedInstance(this.context).getSessionManager().getCurrentCastSession();
            if (currentCastSession != null) {
                currentCastSession.isConnected();
            }
        } catch (Exception unused) {
        }
        ArrayList arrayList = new ArrayList();
        Intrinsics.checkNotNullExpressionValue(downloadFile.list, "downloadFile.list");
        if (!list.isEmpty()) {
            List<BaseMediaModel.DownloadFile> list4 = downloadFile.list;
            Intrinsics.checkNotNullExpressionValue(list4, "downloadFile.list");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : list4) {
                String str = ((BaseMediaModel.DownloadFile) obj).filename;
                Object obj2 = linkedHashMap.get(str);
                if (obj2 == null) {
                    obj2 = (List) new ArrayList();
                    linkedHashMap.put(str, obj2);
                }
                ((List) obj2).add(obj);
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                BaseMediaModel.DownloadFile downloadFile2 = new BaseMediaModel.DownloadFile();
                downloadFile2.filename = (String) entry.getKey();
                downloadFile2.viewType = 1;
                BaseMediaModel.DownloadFile downloadFile3 = (BaseMediaModel.DownloadFile) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                downloadFile2.fid = downloadFile3 == null ? 0 : downloadFile3.fid;
                BaseMediaModel.DownloadFile downloadFile4 = (BaseMediaModel.DownloadFile) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                downloadFile2.dateline = downloadFile4 == null ? 0L : downloadFile4.dateline;
                if (checkHaveOrg((List) entry.getValue())) {
                    downloadFile2.hasOrg = true;
                }
                arrayList.add(downloadFile2);
                for (BaseMediaModel.DownloadFile downloadFile5 : (Iterable) entry.getValue()) {
                    downloadFile5.viewType = 2;
                }
                BaseMediaModel.DownloadFile downloadFile6 = (BaseMediaModel.DownloadFile) CollectionsKt.lastOrNull((List<? extends Object>) entry.getValue());
                if (downloadFile6 != null) {
                    downloadFile6.lastItem = true;
                }
                arrayList.addAll((Collection) entry.getValue());
                BaseMediaModel.DownloadFile downloadFile7 = new BaseMediaModel.DownloadFile();
                downloadFile7.viewType = 3;
                arrayList.add(downloadFile7);
            }
            arrayList.remove(arrayList.size() - 1);
        }
        CommMultiBaseAdapter<BaseMediaModel.DownloadFile> commMultiBaseAdapter2 = this.mAdapter;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            commMultiBaseAdapter = commMultiBaseAdapter2;
        }
        commMultiBaseAdapter.setList(arrayList);
    }

    private final boolean checkHaveOrg(List<? extends BaseMediaModel.DownloadFile> list) {
        for (BaseMediaModel.DownloadFile downloadFile : list) {
            if (StringsKt.equals("org", downloadFile.quality, true)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.movieboxpro.android.view.activity.choose.IChoose
    public void getSrt(List<? extends Srt> srtLists, String path, int i) {
        Intrinsics.checkNotNullParameter(srtLists, "srtLists");
        Intrinsics.checkNotNullParameter(path, "path");
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        for (Srt srt : srtLists) {
            String stringPlus = Intrinsics.stringPlus("https://www.movieboxpro.app/api/srttovtt/index?srt_url=", HttpUtils.toURLEncoded(srt.file_path));
            String str = srt.file_path;
            Intrinsics.checkNotNullExpressionValue(str, "srt.file_path");
            String str2 = srt.file_path;
            Intrinsics.checkNotNullExpressionValue(str2, "srt.file_path");
            String substring = str.substring(StringsKt.lastIndexOf$default((CharSequence) str2, "/", 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            MediaTrack buildTrack = ChromeCastPresenter.buildTrack(i2, "text", MediaTrack.ROLE_SUBTITLE, stringPlus, substring, srt.lang);
            Intrinsics.checkNotNullExpressionValue(buildTrack, "buildTrack(\n            …ang\n                    )");
            arrayList.add(buildTrack);
            i2++;
        }
        MediaMetadata mediaMetadata = new MediaMetadata(1);
        PlayerStrategy playerStrategy = this.player;
        PlayerStrategy playerStrategy2 = null;
        if (playerStrategy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy = null;
        }
        MediaFunction instace = playerStrategy.getInstace();
        if (instace == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.model.BaseMediaModel");
        }
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, ((BaseMediaModel) instace).title);
        PlayerStrategy playerStrategy3 = this.player;
        if (playerStrategy3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            playerStrategy3 = null;
        }
        if (playerStrategy3.getSeason() != 0) {
            PlayerStrategy playerStrategy4 = this.player;
            if (playerStrategy4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                playerStrategy4 = null;
            }
            if (playerStrategy4.getEpisode() != 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[2];
                PlayerStrategy playerStrategy5 = this.player;
                if (playerStrategy5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("player");
                    playerStrategy5 = null;
                }
                objArr[0] = CommonUtils.formatInteger(playerStrategy5.getSeason());
                PlayerStrategy playerStrategy6 = this.player;
                if (playerStrategy6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("player");
                    playerStrategy6 = null;
                }
                objArr[1] = CommonUtils.formatInteger(playerStrategy6.getEpisode());
                String format = String.format("S%s E%s", Arrays.copyOf(objArr, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, format);
            }
        }
        PlayerStrategy playerStrategy7 = this.player;
        if (playerStrategy7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
        } else {
            playerStrategy2 = playerStrategy7;
        }
        MediaFunction instace2 = playerStrategy2.getInstace();
        if (instace2 != null) {
            mediaMetadata.addImage(new WebImage(Uri.parse(((BaseMediaModel) instace2).poster_min)));
            MediaInfo build = new MediaInfo.Builder(path).setStreamType(1).setContentType("videos/mp4").setMetadata(mediaMetadata).setMediaTracks(arrayList).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(path)\n          …cks)\n            .build()");
            CastSession currentCastSession = CastContext.getSharedInstance(this.activity).getSessionManager().getCurrentCastSession();
            if (currentCastSession == null || !currentCastSession.isConnected()) {
                Log.w(this.TAG, "showQueuePopup(): not connected to a cast device");
                return;
            }
            RemoteMediaClient remoteMediaClient = currentCastSession.getRemoteMediaClient();
            if (remoteMediaClient == null) {
                Log.w(this.TAG, "showQueuePopup(): null RemoteMediaClient");
                return;
            }
            MediaQueueItem build2 = new MediaQueueItem.Builder(build).setAutoplay(true).setStartTime(i).setPreloadTime(20.0d).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(mSelectedMedia).…PreloadTime(20.0).build()");
            PlayAddToCastDialog playAddToCastDialog = new PlayAddToCastDialog(this, new ChooseActivity$getSrt$dialog$1(remoteMediaClient, build2, this));
            if (isFinishing()) {
                return;
            }
            playAddToCastDialog.show();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.model.BaseMediaModel");
    }

    @Override // com.movieboxpro.android.view.activity.choose.IChoose
    public void showSwipeView() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        Intrinsics.checkNotNull(swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override // com.movieboxpro.android.view.activity.choose.IChoose
    public void hideSwipeView() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        Intrinsics.checkNotNull(swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(this.TAG);
        super.onDestroy();
    }

    /* compiled from: ChooseActivity.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006J\u0018\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/activity/choose/impl/ChooseActivity$ChooseAdapter;", "Lcom/movieboxpro/android/base/BaseAdapter;", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "list", "", "isCastConnect", "", "(Lcom/movieboxpro/android/view/activity/choose/impl/ChooseActivity;Ljava/util/List;Z)V", "getHolder", "Lcom/movieboxpro/android/base/BaseViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/base/OnItemClickListener;", "setCastConnect", "", "castConnect", "setView", "holder", "position", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class ChooseAdapter extends BaseAdapter<BaseMediaModel.DownloadFile> {
        private boolean isCastConnect;
        final /* synthetic */ ChooseActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChooseAdapter(ChooseActivity this$0, List<? extends BaseMediaModel.DownloadFile> list, boolean z) {
            super(list);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(list, "list");
            this.this$0 = this$0;
            this.isCastConnect = z;
        }

        public final void setCastConnect(boolean z) {
            this.isCastConnect = z;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater inflater, ViewGroup parent, int i, com.movieboxpro.android.base.OnItemClickListener listener) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(listener, "listener");
            return new Item1ViewHolder(inflater.inflate(R.layout.layout_choose_item, parent, false), listener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder holder, int i) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            BaseMediaModel.DownloadFile model = getModel(i);
            Intrinsics.checkNotNullExpressionValue(model, "getModel(position)");
            BaseMediaModel.DownloadFile downloadFile = model;
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) holder;
            if (StringsKt.equals("org", downloadFile.quality, true)) {
                if (this.this$0.showOrg) {
                    ViewGroup.LayoutParams layoutParams = item1ViewHolder.itemView.getLayoutParams();
                    if (layoutParams == null) {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
                    }
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    layoutParams2.width = -1;
                    layoutParams2.height = -2;
                    item1ViewHolder.itemView.setVisibility(0);
                    item1ViewHolder.itemView.setLayoutParams(layoutParams2);
                } else {
                    ViewGroup.LayoutParams layoutParams3 = item1ViewHolder.itemView.getLayoutParams();
                    if (layoutParams3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
                    }
                    RecyclerView.LayoutParams layoutParams4 = (RecyclerView.LayoutParams) layoutParams3;
                    layoutParams4.width = 0;
                    layoutParams4.height = 0;
                    item1ViewHolder.itemView.setVisibility(8);
                    item1ViewHolder.itemView.setLayoutParams(layoutParams4);
                }
            } else {
                ViewGroup.LayoutParams layoutParams5 = item1ViewHolder.itemView.getLayoutParams();
                if (layoutParams5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
                }
                RecyclerView.LayoutParams layoutParams6 = (RecyclerView.LayoutParams) layoutParams5;
                layoutParams6.width = -1;
                layoutParams6.height = -2;
                item1ViewHolder.itemView.setVisibility(0);
                item1ViewHolder.itemView.setLayoutParams(layoutParams6);
            }
            if (this.this$0.activity != null && !this.this$0.activity.isFinishing()) {
                if (downloadFile.vip_only == 1) {
                    ImageView imageView = item1ViewHolder.mVip;
                    Intrinsics.checkNotNull(imageView);
                    imageView.setVisibility(0);
                } else {
                    ImageView imageView2 = item1ViewHolder.mVip;
                    Intrinsics.checkNotNull(imageView2);
                    imageView2.setVisibility(8);
                }
                if (TextUtils.equals(downloadFile.real_quality, "360p")) {
                    RequestBuilder<Drawable> load = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.drawable.ic_choose_sd));
                    ImageView imageView3 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView3);
                    load.into(imageView3);
                } else if (TextUtils.equals(downloadFile.quality, "720p")) {
                    RequestBuilder<Drawable> load2 = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.drawable.ic_choose_hd));
                    ImageView imageView4 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView4);
                    load2.into(imageView4);
                } else if (TextUtils.equals(downloadFile.quality, "1080p")) {
                    RequestBuilder<Drawable> load3 = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.drawable.ic_choose_fullhd));
                    ImageView imageView5 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView5);
                    load3.into(imageView5);
                } else if (TextUtils.equals(downloadFile.quality, "4K")) {
                    RequestBuilder<Drawable> load4 = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.drawable.ic_choose_4k));
                    ImageView imageView6 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView6);
                    load4.into(imageView6);
                } else if (TextUtils.equals(downloadFile.quality, "8K")) {
                    RequestBuilder<Drawable> load5 = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.mipmap.ic_choose_8k));
                    ImageView imageView7 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView7);
                    load5.into(imageView7);
                } else if (TextUtils.equals(downloadFile.quality, "org")) {
                    RequestBuilder<Drawable> load6 = Glide.with(this.this$0.activity).load(Integer.valueOf((int) R.mipmap.ic_origin_rate));
                    ImageView imageView8 = item1ViewHolder.mTheme;
                    Intrinsics.checkNotNull(imageView8);
                    load6.into(imageView8);
                }
            }
            if (downloadFile.original == 1) {
                ImageView imageView9 = item1ViewHolder.ivOrigin;
                Intrinsics.checkNotNull(imageView9);
                imageView9.setVisibility(0);
            } else {
                ImageView imageView10 = item1ViewHolder.ivOrigin;
                Intrinsics.checkNotNull(imageView10);
                imageView10.setVisibility(8);
            }
            if (downloadFile.hdr == 1) {
                ImageView imageView11 = item1ViewHolder.ivHdr;
                Intrinsics.checkNotNull(imageView11);
                imageView11.setVisibility(0);
            } else {
                ImageView imageView12 = item1ViewHolder.ivHdr;
                Intrinsics.checkNotNull(imageView12);
                imageView12.setVisibility(8);
            }
            if (downloadFile.colorbit > 8) {
                ImageView imageView13 = item1ViewHolder.ivColorBit;
                Intrinsics.checkNotNull(imageView13);
                imageView13.setVisibility(0);
                if (downloadFile.colorbit == 10) {
                    ImageView imageView14 = item1ViewHolder.ivColorBit;
                    Intrinsics.checkNotNull(imageView14);
                    imageView14.setImageResource(R.mipmap.ic_color_bit_10);
                } else if (downloadFile.colorbit == 12) {
                    ImageView imageView15 = item1ViewHolder.ivColorBit;
                    Intrinsics.checkNotNull(imageView15);
                    imageView15.setImageResource(R.mipmap.ic_color_bit_12);
                }
            } else {
                ImageView imageView16 = item1ViewHolder.ivColorBit;
                Intrinsics.checkNotNull(imageView16);
                imageView16.setVisibility(8);
            }
            if (TextUtils.isEmpty(downloadFile.bitstream)) {
                TextView textView = item1ViewHolder.tvRate;
                Intrinsics.checkNotNull(textView);
                textView.setVisibility(8);
            } else {
                TextView textView2 = item1ViewHolder.tvRate;
                Intrinsics.checkNotNull(textView2);
                textView2.setVisibility(0);
                TextView textView3 = item1ViewHolder.tvRate;
                Intrinsics.checkNotNull(textView3);
                textView3.setText(downloadFile.bitstream);
            }
            ImageView imageView17 = item1ViewHolder.mFormatDark;
            Intrinsics.checkNotNull(imageView17);
            imageView17.setImageResource(downloadFile.h265 == 1 ? R.drawable.ic_choose_fromat : R.mipmap.ic_choose_fromat2);
            ImageView imageView18 = item1ViewHolder.mFormat;
            Intrinsics.checkNotNull(imageView18);
            imageView18.setVisibility(8);
            TextView textView4 = item1ViewHolder.mSize;
            Intrinsics.checkNotNull(textView4);
            textView4.setText(downloadFile.size);
            TextView textView5 = item1ViewHolder.mTime;
            Intrinsics.checkNotNull(textView5);
            textView5.setText(TimeUtils.formatTime3(downloadFile.dateline * 1000));
            TextView textView6 = item1ViewHolder.mName;
            Intrinsics.checkNotNull(textView6);
            textView6.setText(downloadFile.filename);
            TextView textView7 = item1ViewHolder.mSelect;
            Intrinsics.checkNotNull(textView7);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s Selecet", Arrays.copyOf(new Object[]{downloadFile.count}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView7.setText(format);
            TextView textView8 = item1ViewHolder.mSelect;
            Intrinsics.checkNotNull(textView8);
            textView8.setVisibility(8);
            if (this.isCastConnect) {
                ImageView imageView19 = item1ViewHolder.mDownlaod;
                Intrinsics.checkNotNull(imageView19);
                imageView19.setVisibility(8);
                return;
            }
            ImageView imageView20 = item1ViewHolder.mDownlaod;
            Intrinsics.checkNotNull(imageView20);
            imageView20.setVisibility(0);
        }
    }

    /* compiled from: ChooseActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/activity/choose/impl/ChooseActivity$Item1ViewHolder;", "Lcom/movieboxpro/android/base/BaseViewHolder;", "itView", "Landroid/view/View;", "l", "Lcom/movieboxpro/android/base/OnItemClickListener;", "(Landroid/view/View;Lcom/movieboxpro/android/base/OnItemClickListener;)V", "ivColorBit", "Landroid/widget/ImageView;", "ivHdr", "ivOrigin", "mDownlaod", "mFormat", "mFormatDark", "mName", "Landroid/widget/TextView;", "mSelect", "mSize", "mTheme", "mTime", "mVip", "tvRate", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.ivColorBit)
        public ImageView ivColorBit;
        @BindView(R.id.ivHdr)
        public ImageView ivHdr;
        @BindView(R.id.ivOrigin)
        public ImageView ivOrigin;
        @BindView(R.id.layout_choose_download)
        public ImageView mDownlaod;
        @BindView(R.id.layout_choose_format)
        public ImageView mFormat;
        @BindView(R.id.layout_choose_format_dark)
        public ImageView mFormatDark;
        @BindView(R.id.layout_choose_name)
        public TextView mName;
        @BindView(R.id.layout_choose_select)
        public TextView mSelect;
        @BindView(R.id.layout_choose_size)
        public TextView mSize;
        @BindView(R.id.layout_choose_theme)
        public ImageView mTheme;
        @BindView(R.id.layout_choose_time)
        public TextView mTime;
        @BindView(R.id.layout_choose_vip)
        public ImageView mVip;
        @BindView(R.id.tv_rate)
        public TextView tvRate;

        public Item1ViewHolder(View view, com.movieboxpro.android.base.OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* compiled from: ChooseActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/choose/impl/ChooseActivity$Companion;", "", "()V", "ISDOWNLOAD", "", "REQUEST_PERMISSION_SETTING", "", TtmlNode.START, "", "context", "Landroid/content/Context;", ChooseActivity.ISDOWNLOAD, "", "player", "Lcom/movieboxpro/android/model/PlayerStrategy;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, boolean z, PlayerStrategy playerStrategy) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, ChooseActivity.class);
            intent.putExtra(ChooseActivity.ISDOWNLOAD, z);
            intent.putExtra(MovieChooseActivity.CHOOSEMOVIE, playerStrategy);
            context.startActivity(intent);
        }
    }
}
