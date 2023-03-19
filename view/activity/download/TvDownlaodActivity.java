package com.movieboxpro.android.view.activity.download;

import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.SubtitleDownloadEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.utils.tool.ThreadUtils;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class TvDownlaodActivity extends BaseActivity {
    private String banner;
    private String id;
    private MyLinearLayoutManager layoutManager;
    private TvDownlaodAdapter mAdapter;
    @BindView(R.id.fragmen_download_delete)
    TextView mDelete;
    @BindView(R.id.text_empty)
    TextView mEmptyText;
    @BindView(R.id.activity_tvdownload_more)
    TextView mMore;
    @BindView(R.id.activity_tvdownload_recycler)
    MyRecyclerView mRecycler;
    @BindView(R.id.fragmen_download_select)
    TextView mSelect;
    @BindView(R.id.fragment_download_tabs)
    LinearLayout mTabs;
    private String poster;
    List<DownloadModel> list = new ArrayList();
    private int Flag = 0;
    private boolean isFull = false;
    OnItemClickListener onItemClickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            CastSession castSession;
            if (!X86HintUtils.checkX86(TvDownlaodActivity.this) && i >= 0 && i <= TvDownlaodActivity.this.mAdapter.getItemCount() - 1) {
                DownloadModel model = TvDownlaodActivity.this.mAdapter.getModel(i);
                if (TvDownlaodActivity.this.Flag == 0) {
                    model.path = Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(model.path, model.getTitle(), Constant.DIR_DOWNLOAD);
                    if (!new File(model.path).exists()) {
                        if (!model.path.startsWith("/storage/emulated/0")) {
                            ToastUtils.showShort("File not exist,please check the sd card");
                            return;
                        } else {
                            ToastUtils.showShort("File not exist,please re-download");
                            return;
                        }
                    }
                    TvDetail tvDetail = new TvDetail(model);
                    try {
                        castSession = CastContext.getSharedInstance(TvDownlaodActivity.this.context).getSessionManager().getCurrentCastSession();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        castSession = null;
                    }
                    if (castSession != null && castSession.isConnected()) {
                        TvDownlaodActivity.this.showToast("Local video cannot use cast");
                        return;
                    }
                    TvDownlaodActivity.this.route(TvPlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                    return;
                }
                if (model.isChecked == 0) {
                    if (!TvDownlaodActivity.this.choosed.containsKey(model.privateid)) {
                        TvDownlaodActivity.this.choosed.put(model.privateid, model);
                        TvDownlaodActivity.this.list.get(i).isChecked = 1;
                    }
                } else if (TvDownlaodActivity.this.choosed.containsKey(model.privateid)) {
                    TvDownlaodActivity.this.choosed.remove(model.privateid);
                    TvDownlaodActivity.this.list.get(i).isChecked = 0;
                }
                if (!TvDownlaodActivity.this.choosed.isEmpty() && TvDownlaodActivity.this.choosed.size() > 0) {
                    TextView textView = TvDownlaodActivity.this.mDelete;
                    textView.setText("Delete(" + TvDownlaodActivity.this.choosed.size() + ")");
                    TvDownlaodActivity.this.mDelete.setTextColor(TvDownlaodActivity.this.getResources().getColor(R.color.white));
                    if (TvDownlaodActivity.this.choosed.size() == TvDownlaodActivity.this.list.size()) {
                        TvDownlaodActivity.this.isFull = true;
                        TvDownlaodActivity.this.mSelect.setText("Deselect All");
                        TvDownlaodActivity.this.mSelect.setTextColor(TvDownlaodActivity.this.getResources().getColor(R.color.color_text));
                    } else {
                        TvDownlaodActivity.this.isFull = false;
                        TvDownlaodActivity.this.mSelect.setText("Select All");
                        TvDownlaodActivity.this.mSelect.setTextColor(TvDownlaodActivity.this.getResources().getColor(R.color.white));
                    }
                } else {
                    TvDownlaodActivity.this.mDelete.setTextColor(TvDownlaodActivity.this.getResources().getColor(R.color.color_text));
                    TvDownlaodActivity.this.mDelete.setText("Delete");
                }
                TvDownlaodActivity.this.mAdapter.notifyItemChanged(i);
            }
        }
    };
    public HashMap<String, DownloadModel> choosed = new HashMap<>();

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    /* loaded from: classes3.dex */
    public class Item2ViewHolder_ViewBinding implements Unbinder {
        private Item2ViewHolder target;

        public Item2ViewHolder_ViewBinding(Item2ViewHolder item2ViewHolder, View view) {
            this.target = item2ViewHolder;
            item2ViewHolder.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_downlaod_header, "field 'mTitle'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item2ViewHolder item2ViewHolder = this.target;
            if (item2ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item2ViewHolder.mTitle = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_download_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_download_name, "field 'mName'", TextView.class);
            item1ViewHolder.record = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_download_season, "field 'record'", TextView.class);
            item1ViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_download_speed, "field 'mSpeed'", TextView.class);
            item1ViewHolder.mRuntime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_download_runtime, "field 'mRuntime'", TextView.class);
            item1ViewHolder.mChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_download_checked, "field 'mChecked'", ImageView.class);
            item1ViewHolder.mQuality = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_download_quality, "field 'mQuality'", ImageView.class);
            item1ViewHolder.ivDownloadSubtitle = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_Download_Subtitle, "field 'ivDownloadSubtitle'", ImageView.class);
            item1ViewHolder.tvSubtitleNum = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSubtitleNum, "field 'tvSubtitleNum'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.record = null;
            item1ViewHolder.mSpeed = null;
            item1ViewHolder.mRuntime = null;
            item1ViewHolder.mChecked = null;
            item1ViewHolder.mQuality = null;
            item1ViewHolder.ivDownloadSubtitle = null;
            item1ViewHolder.tvSubtitleNum = null;
        }
    }

    @OnClick({R.id.activity_tvdownload_more})
    public void onClicked(View view) {
        route(TvDetailActivity.class, ParamsUtils.newBuilder().addParam(TvDetailActivity.TV_TID, this.id).addParam("tv_poster", this.poster).addParam(TvDetailActivity.TV_BANNER, this.banner).build());
    }

    @OnClick({R.id.fragmen_download_select, R.id.fragmen_download_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmen_download_delete /* 2131296784 */:
                for (Map.Entry<String, DownloadModel> entry : this.choosed.entrySet()) {
                    this.list.remove(entry.getValue());
                    entry.getValue().startService(Constant.ACTION.MOVIE_DELETE, this.activity);
                }
                this.choosed.clear();
                this.mDelete.setText("Delete");
                this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
                this.mAdapter.notifyDataSetChanged();
                return;
            case R.id.fragmen_download_select /* 2131296785 */:
                this.choosed.clear();
                if (!this.isFull) {
                    this.choosed.clear();
                    for (DownloadModel downloadModel : this.list) {
                        downloadModel.isChecked = 1;
                        this.choosed.put(downloadModel.privateid, downloadModel);
                    }
                    this.isFull = true;
                    TextView textView = this.mDelete;
                    textView.setText("Delete(" + this.choosed.size() + ")");
                    this.mSelect.setText("Deselect All");
                    this.mDelete.setTextColor(getResources().getColor(R.color.white));
                    this.mSelect.setTextColor(getResources().getColor(R.color.white));
                } else {
                    for (DownloadModel downloadModel2 : this.list) {
                        downloadModel2.isChecked = 0;
                    }
                    this.choosed.clear();
                    this.isFull = false;
                    this.mSelect.setText("Select All");
                    this.mDelete.setText("Delete");
                    this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
                    this.mSelect.setTextColor(getResources().getColor(R.color.color_text));
                }
                this.mAdapter.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.activity.download.TvDownlaodActivity$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ TvDetail val$tv;

        AnonymousClass2(TvDetail tvDetail) {
            this.val$tv = tvDetail;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((ObservableSubscribeProxy) Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", this.val$tv.id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).flatMap(new Function<TvDetail, Observable<TvDetail>>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.2.2
                @Override // io.reactivex.functions.Function
                public Observable<TvDetail> apply(final TvDetail tvDetail) throws Exception {
                    String str;
                    String str2;
                    String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                    if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
                        str2 = "";
                        str = str2;
                    } else {
                        str = string;
                        str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                    }
                    return Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", AnonymousClass2.this.val$tv.id, AnonymousClass2.this.val$tv.seasonDetail.season + "", AnonymousClass2.this.val$tv.seasonDetail.episode + "", str2, str).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function<BaseMediaModel, TvDetail>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.2.2.1
                        @Override // io.reactivex.functions.Function
                        public TvDetail apply(BaseMediaModel baseMediaModel) throws Exception {
                            ArrayList arrayList = new ArrayList();
                            if (AnonymousClass2.this.val$tv.list.size() > 0) {
                                arrayList.addAll(AnonymousClass2.this.val$tv.list);
                            }
                            arrayList.addAll(baseMediaModel.list);
                            tvDetail.list = arrayList;
                            return tvDetail;
                        }
                    });
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(TvDownlaodActivity.this))).subscribe(new Observer<TvDetail>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.2.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    TvDownlaodActivity.this.showLoading();
                }

                @Override // io.reactivex.Observer
                public void onNext(TvDetail tvDetail) {
                    TvDownlaodActivity.this.hideLoading();
                    TvDownlaodActivity.this.route(TvPlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, AnonymousClass2.this.val$tv.seasonDetail.season).addParam("videoplayer_episode", AnonymousClass2.this.val$tv.seasonDetail.episode).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    TvDownlaodActivity.this.hideLoading();
                    TvDownlaodActivity.this.route(TvPlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", AnonymousClass2.this.val$tv).addParam(VideoPlayerActivity.VIDEO_ID, AnonymousClass2.this.val$tv.seasonDetail.season).addParam("videoplayer_episode", AnonymousClass2.this.val$tv.seasonDetail.episode).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPlayUrl(TvDetail tvDetail) {
        ThreadUtils.runOnUiThread(new AnonymousClass2(tvDetail));
    }

    private void checkOnlineDevice(final TvDetail tvDetail) {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, tvDetail.id, SystemUtils.getUniqueId(App.getContext()), 1, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, Build.BRAND, Build.MODEL).map(new Function<String, Object>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.4
            @Override // io.reactivex.functions.Function
            public Object apply(String str) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() != -88) {
                    TvDownlaodActivity.this.getPlayUrl(tvDetail);
                    return Observable.just("");
                }
                ScreenManageDialog newInstance = ScreenManageDialog.newInstance(new ArrayList(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), baseResponse.getMsg());
                newInstance.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.4.1
                    @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                    public void onStopDevice() {
                        TvDownlaodActivity.this.getPlayUrl(tvDetail);
                    }
                });
                newInstance.show(TvDownlaodActivity.this.getSupportFragmentManager(), ScreenManageDialog.class.getSimpleName());
                return Observable.empty();
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Object>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.3
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                TvDownlaodActivity.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Object obj) {
                TvDownlaodActivity.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                TvDownlaodActivity.this.hideLoading();
                TvDownlaodActivity.this.getPlayUrl(tvDetail);
            }
        });
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_tv_download, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        if (this.mAdapter == null) {
            this.mAdapter = new TvDownlaodAdapter(this.list);
            this.layoutManager = new MyLinearLayoutManager(this.context);
            this.mRecycler.setEmptyView(this.mEmptyText);
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onItemClickListener);
            RecyclerView.ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
        setTitleRightText("Edit", new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i = TvDownlaodActivity.this.Flag;
                if (i == 0) {
                    TvDownlaodActivity.this.Flag = 1;
                    TvDownlaodActivity.this.mTabs.setVisibility(0);
                    TvDownlaodActivity.this.mAdapter.notifyDataSetChanged();
                    TvDownlaodActivity.this.mTitleRightText.setText("Done");
                } else if (i != 1) {
                } else {
                    TvDownlaodActivity.this.Flag = 0;
                    TvDownlaodActivity.this.choosed.clear();
                    TvDownlaodActivity.this.mTabs.setVisibility(8);
                    TvDownlaodActivity.this.mTitleRightText.setText("Edit");
                    TvDownlaodActivity.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        setTitleBack(true);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        this.id = getStringParam(TvDetailActivity.TV_TID, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        this.banner = getStringParam(TvDetailActivity.TV_BANNER, "");
        this.poster = getStringParam("tv_poster", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubtitleDownload(SubtitleDownloadEvent subtitleDownloadEvent) {
        loadData();
    }

    private void loadData() {
        this.list.clear();
        List<Download> findByTid = App.getDB().downloadDao().findByTid(this.id, 2, 2);
        Collections.sort(findByTid, new Comparator<Download>() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.6
            @Override // java.util.Comparator
            public int compare(Download download, Download download2) {
                if (download.getSeason() > download2.getSeason()) {
                    return 1;
                }
                return download.getSeason() == download2.getSeason() ? 0 : -1;
            }
        });
        int i = -1;
        for (Download download : findByTid) {
            String str = this.TAG;
            MLog.d(str, "下载状态3 ：" + download.getSeason());
            setTitle(download.getTitle());
            if (i != download.getSeason()) {
                DownloadModel downloadModel = new DownloadModel();
                downloadModel.title = UploadErrorInfoService.SEASON + download.getSeason();
                downloadModel.box_type = 1;
                this.list.add(downloadModel);
                i = download.getSeason();
            }
            this.list.add(new DownloadModel(download));
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* loaded from: classes3.dex */
    public class TvDownlaodAdapter extends BaseAdapter<DownloadModel> {
        public TvDownlaodAdapter(List<DownloadModel> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            BaseViewHolder item2ViewHolder;
            if (i == 1) {
                item2ViewHolder = new Item2ViewHolder(layoutInflater.inflate(R.layout.layout_download_tvtitle, viewGroup, false), onItemClickListener);
            } else if (i != 2) {
                return null;
            } else {
                item2ViewHolder = new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_download_tvitem, viewGroup, false), onItemClickListener);
            }
            return item2ViewHolder;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            final DownloadModel model = getModel(i);
            int i2 = model.box_type;
            if (i2 == 1) {
                ((Item2ViewHolder) baseViewHolder).mTitle.setText(model.title);
            } else if (i2 == 2) {
                Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
                if (TvDownlaodActivity.this.activity != null && !TvDownlaodActivity.this.activity.isFinishing()) {
                    GlideUtils.loadLandGifHolder(TvDownlaodActivity.this.context, model.seasonthumbs, item1ViewHolder.mCover);
                    GlideApp.with(TvDownlaodActivity.this.context).load(model.seasonthumbs).centerCrop().into(item1ViewHolder.mCover);
                }
                item1ViewHolder.ivDownloadSubtitle.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.TvDownlaodActivity.TvDownlaodAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DownloadSubtitleActivity.Companion.starter(TvDownlaodActivity.this, model.title, model.movieId, String.valueOf(model.fid), model.season, model.episode);
                    }
                });
                int i3 = 0;
                item1ViewHolder.mName.setText(String.format("%s %s", Integer.valueOf(model.episode), model.seasontitle));
                item1ViewHolder.mQuality.setImageResource(CommonUtils.getMovieQualityTag(model.quality));
                item1ViewHolder.mSpeed.setText(model.size);
                TextView textView = item1ViewHolder.mRuntime;
                textView.setText(model.runtime + " MIN");
                if (TvDownlaodActivity.this.Flag == 1) {
                    item1ViewHolder.mChecked.setVisibility(0);
                } else {
                    item1ViewHolder.mChecked.setVisibility(8);
                }
                if (model.isChecked == 0) {
                    item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox);
                } else {
                    item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox_checked);
                }
                PlayRecode findByEpisode = App.getDB().playRecodeDao().findByEpisode(2, model.movieId, model.season, model.episode);
                if (findByEpisode != null) {
                    item1ViewHolder.record.setTextColor(ContextCompat.getColor(App.getContext(), R.color.white_70alpha));
                    item1ViewHolder.record.setText(String.format("RESUME %s", TimeUtils.getTime(findByEpisode.getStart_time() / 1000)));
                } else {
                    item1ViewHolder.record.setTextColor(ContextCompat.getColor(App.getContext(), R.color.color_main_blue));
                    item1ViewHolder.record.setText("Not watched");
                }
                List<File> listFilesInDir = FileUtils.listFilesInDir(new File(Constant.DIR_UPLOAD_TV_SUBTITLE + File.separator + TvDownlaodActivity.this.id + File.separator + model.title + File.separator + "Season " + model.season + File.separator + "Episode " + model.episode));
                if (listFilesInDir != null) {
                    for (File file : listFilesInDir) {
                        List<File> listFilesInDir2 = FileUtils.listFilesInDir(file);
                        if (listFilesInDir2 != null) {
                            i3 += listFilesInDir2.size();
                        }
                    }
                }
                if (i3 > 0) {
                    item1ViewHolder.ivDownloadSubtitle.setImageResource(R.mipmap.ic_download_subtitle_num);
                    item1ViewHolder.tvSubtitleNum.setText(String.valueOf(i3));
                    return;
                }
                item1ViewHolder.ivDownloadSubtitle.setImageResource(R.mipmap.ic_download_subtitle);
                item1ViewHolder.tvSubtitleNum.setText("");
            }
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return super.getType(i);
            }
            return getModel(i).box_type;
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_Download_Subtitle)
        ImageView ivDownloadSubtitle;
        @BindView(R.id.tv_download_checked)
        ImageView mChecked;
        @BindView(R.id.tv_download_poster)
        ImageView mCover;
        @BindView(R.id.tv_download_name)
        TextView mName;
        @BindView(R.id.tv_download_quality)
        ImageView mQuality;
        @BindView(R.id.tv_download_runtime)
        TextView mRuntime;
        @BindView(R.id.tv_download_speed)
        TextView mSpeed;
        @BindView(R.id.tv_download_season)
        TextView record;
        @BindView(R.id.tvSubtitleNum)
        TextView tvSubtitleNum;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* loaded from: classes3.dex */
    static class Item2ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_downlaod_header)
        TextView mTitle;

        public Item2ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
