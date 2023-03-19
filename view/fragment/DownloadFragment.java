package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.DownloadChangedEvent;
import com.movieboxpro.android.event.OnDeleteDownloadEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.WriteFileToUriListener;
import com.movieboxpro.android.livedata.DownloadPathLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.MemoryUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.StringUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.ThreadUtils;
import com.movieboxpro.android.view.activity.SmartDownloadSettingActivity;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.download.DownloadingActivity;
import com.movieboxpro.android.view.activity.download.TvDownlaodActivity;
import com.movieboxpro.android.view.activity.downloadsubtitle.DownloadSubtitleActivity;
import com.movieboxpro.android.view.activity.settings.InputChildModePasswordActivity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.VideoActivityFactory;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ExportVideosProgressDialog;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.fragment.DownloadFragment;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class DownloadFragment extends BaseFragment {
    private static final String lock = "";
    private DownloadModel downloader;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    private MyLinearLayoutManager layoutManager;
    private DownloadAdapter mAdapter;
    @BindView(R.id.fragment_download_memory)
    TextView mCacheText;
    @BindView(R.id.fragmen_download_delete)
    TextView mDelete;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    @BindView(R.id.fragment_download_recycler)
    MyRecyclerView mRecycler;
    @BindView(R.id.fragmen_download_select)
    TextView mSelect;
    @BindView(R.id.fragment_download_tabs)
    LinearLayout mTabs;
    private BookDownloadReceiver receiver;
    @BindView(R.id.sizeProgressBar)
    ProgressBar sizeProgressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvExport)
    TextView tvExport;
    @BindView(R.id.tvSmartDownload)
    TextView tvSmartDownload;
    private List<DownloadModel> downloadingModels = new ArrayList();
    private Disposable disposable = null;
    private ExportVideosProgressDialog dialog = null;
    private long writeLength = 0;
    public List<DownloadModel> list = new ArrayList();
    public HashMap<String, DownloadModel> choosed = new HashMap<>();
    private int Flag = 0;
    private boolean isFull = false;
    OnClickListener onItemClickListener = new OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.4
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
        }

        @Override // com.movieboxpro.android.view.fragment.DownloadFragment.OnClickListener
        public void onMovieClicked(int i, ImageView imageView) {
            CastSession castSession;
            if (i < 0 || i > DownloadFragment.this.mAdapter.getItemCount() - 1) {
                return;
            }
            DownloadModel model = DownloadFragment.this.mAdapter.getModel(i);
            if (DownloadFragment.this.Flag == 0) {
                if (i != 0 || DownloadFragment.this.downloader == null) {
                    if (DownloadFragment.this.checkChildMode(model.content_rating)) {
                        return;
                    }
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
                    MovieDetail movieDetail = new MovieDetail(model);
                    try {
                        castSession = CastContext.getSharedInstance(DownloadFragment.this.context).getSessionManager().getCurrentCastSession();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        castSession = null;
                    }
                    if (castSession != null && castSession.isConnected()) {
                        DownloadFragment.this.showToast("Local video cannot use cast");
                        return;
                    }
                    DownloadFragment.this.route(MoviePlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", movieDetail).addParam(VideoPlayerActivity.VIDEO_ID, model.movieId).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                    return;
                }
                DownloadFragment.this.route(DownloadingActivity.class);
                return;
            }
            if (model.isChecked == 0) {
                if (!DownloadFragment.this.choosed.containsKey(model.privateid)) {
                    DownloadFragment.this.choosed.put(model.privateid, model);
                    DownloadFragment.this.list.get(i).isChecked = 1;
                }
            } else if (DownloadFragment.this.choosed.containsKey(model.privateid)) {
                DownloadFragment.this.choosed.remove(model.privateid);
                DownloadFragment.this.list.get(i).isChecked = 0;
            }
            if (!DownloadFragment.this.choosed.isEmpty() && DownloadFragment.this.choosed.size() > 0) {
                TextView textView = DownloadFragment.this.mDelete;
                textView.setText("Delete(" + DownloadFragment.this.choosed.size() + ")");
                DownloadFragment.this.mDelete.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                if (CommonUtils.canExportVideos()) {
                    DownloadFragment.this.tvExport.setEnabled(true);
                    DownloadFragment.this.tvExport.setTextColor(ContextCompat.getColor(DownloadFragment.this.getContext(), R.color.white));
                    TextView textView2 = DownloadFragment.this.tvExport;
                    textView2.setText("Export(" + DownloadFragment.this.choosed.size() + ")");
                }
                if (DownloadFragment.this.choosed.size() == DownloadFragment.this.list.size()) {
                    DownloadFragment.this.isFull = true;
                    DownloadFragment.this.mSelect.setText("Deselect All");
                    DownloadFragment.this.mSelect.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_text));
                } else {
                    DownloadFragment.this.isFull = false;
                    DownloadFragment.this.mSelect.setText("Select All");
                    DownloadFragment.this.mSelect.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                }
            } else {
                DownloadFragment.this.mDelete.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_text));
                DownloadFragment.this.mDelete.setText("Delete");
                if (CommonUtils.canExportVideos()) {
                    DownloadFragment.this.tvExport.setEnabled(false);
                    DownloadFragment.this.tvExport.setTextColor(ContextCompat.getColor(DownloadFragment.this.getContext(), R.color.color_text));
                    DownloadFragment.this.tvExport.setText("Export");
                }
            }
            DownloadFragment.this.mAdapter.notifyItemChanged(i);
        }

        @Override // com.movieboxpro.android.view.fragment.DownloadFragment.OnClickListener
        public void onTvClicked(int i, ImageView imageView) {
            if (i < 0 || i > DownloadFragment.this.mAdapter.getItemCount() - 1) {
                return;
            }
            DownloadModel model = DownloadFragment.this.mAdapter.getModel(i);
            if (DownloadFragment.this.Flag == 0) {
                if (i != 0 || DownloadFragment.this.downloader == null) {
                    if (DownloadFragment.this.checkChildMode(model.content_rating)) {
                        return;
                    }
                    model.path = Constant.DIR_DOWNLOAD + File.separator + RemoteFileUtil.getFileName(model.path, model.getTitle(), Constant.DIR_DOWNLOAD);
                    TvDetail tvDetail = new TvDetail(model);
                    DownloadFragment.this.route(TvDownlaodActivity.class, ParamsUtils.newBuilder().addParam(TvDetailActivity.TV_TID, tvDetail.id).addParam("tv_poster", tvDetail.poster).addParam(TvDetailActivity.TV_BANNER, tvDetail.banner_mini).build());
                    return;
                }
                DownloadFragment.this.route(DownloadingActivity.class);
                return;
            }
            if (model.isChecked == 0) {
                if (!DownloadFragment.this.choosed.containsKey(model.privateid)) {
                    DownloadFragment.this.choosed.put(model.privateid, model);
                    DownloadFragment.this.list.get(i).isChecked = 1;
                }
            } else if (DownloadFragment.this.choosed.containsKey(model.privateid)) {
                DownloadFragment.this.choosed.remove(model.privateid);
                DownloadFragment.this.list.get(i).isChecked = 0;
            }
            if (!DownloadFragment.this.choosed.isEmpty() && DownloadFragment.this.choosed.size() > 0) {
                TextView textView = DownloadFragment.this.mDelete;
                textView.setText("Delete(" + DownloadFragment.this.choosed.size() + ")");
                DownloadFragment.this.mDelete.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                if (CommonUtils.canExportVideos()) {
                    DownloadFragment.this.tvExport.setEnabled(true);
                    DownloadFragment.this.tvExport.setTextColor(ContextCompat.getColor(DownloadFragment.this.getContext(), R.color.white));
                    TextView textView2 = DownloadFragment.this.tvExport;
                    textView2.setText("Export(" + DownloadFragment.this.choosed.size() + ")");
                }
                if (DownloadFragment.this.choosed.size() == DownloadFragment.this.list.size()) {
                    DownloadFragment.this.isFull = true;
                    DownloadFragment.this.mSelect.setText("Deselect All");
                    DownloadFragment.this.mSelect.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_text));
                } else {
                    DownloadFragment.this.isFull = false;
                    DownloadFragment.this.mSelect.setText("Select All");
                    DownloadFragment.this.mSelect.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                }
            } else {
                DownloadFragment.this.mDelete.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_text));
                DownloadFragment.this.mDelete.setText("Delete");
                if (CommonUtils.canExportVideos()) {
                    DownloadFragment.this.tvExport.setEnabled(false);
                    DownloadFragment.this.tvExport.setTextColor(ContextCompat.getColor(DownloadFragment.this.getContext(), R.color.color_text));
                    DownloadFragment.this.tvExport.setText("Export");
                }
            }
            DownloadFragment.this.mAdapter.notifyItemChanged(i);
        }
    };
    private long lastShowSpeedTime = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OnClickListener extends OnItemClickListener {
        void onMovieClicked(int i, ImageView imageView);

        void onTvClicked(int i, ImageView imageView);
    }

    /* loaded from: classes3.dex */
    public class ItemDownloadingViewHolder_ViewBinding implements Unbinder {
        private ItemDownloadingViewHolder target;

        public ItemDownloadingViewHolder_ViewBinding(ItemDownloadingViewHolder itemDownloadingViewHolder, View view) {
            this.target = itemDownloadingViewHolder;
            itemDownloadingViewHolder.ivPoster = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_poster, "field 'ivPoster'", ImageView.class);
            itemDownloadingViewHolder.tvCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tvCount, "field 'tvCount'", TextView.class);
            itemDownloadingViewHolder.tvNames = (TextView) Utils.findRequiredViewAsType(view, R.id.tvNames, "field 'tvNames'", TextView.class);
            itemDownloadingViewHolder.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.move_download_progress, "field 'progressBar'", ProgressBar.class);
            itemDownloadingViewHolder.tvSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_speed, "field 'tvSpeed'", TextView.class);
            itemDownloadingViewHolder.tvSize = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_size, "field 'tvSize'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemDownloadingViewHolder itemDownloadingViewHolder = this.target;
            if (itemDownloadingViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemDownloadingViewHolder.ivPoster = null;
            itemDownloadingViewHolder.tvCount = null;
            itemDownloadingViewHolder.tvNames = null;
            itemDownloadingViewHolder.progressBar = null;
            itemDownloadingViewHolder.tvSpeed = null;
            itemDownloadingViewHolder.tvSize = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.move_download_progress, "field 'mProgress'", ProgressBar.class);
            item1ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_name, "field 'mName'", TextView.class);
            item1ViewHolder.mSum = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_sum, "field 'mSum'", TextView.class);
            item1ViewHolder.record = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_seasons, "field 'record'", TextView.class);
            item1ViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_speed, "field 'mSpeed'", TextView.class);
            item1ViewHolder.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_size, "field 'mSize'", TextView.class);
            item1ViewHolder.mRuntime = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_runtime, "field 'mRuntime'", TextView.class);
            item1ViewHolder.mShaow = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.move_download_shadow, "field 'mShaow'", FrameLayout.class);
            item1ViewHolder.mChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_checked, "field 'mChecked'", ImageView.class);
            item1ViewHolder.mQuality = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_quality, "field 'mQuality'", ImageView.class);
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
            item1ViewHolder.mProgress = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.mSum = null;
            item1ViewHolder.record = null;
            item1ViewHolder.mSpeed = null;
            item1ViewHolder.mSize = null;
            item1ViewHolder.mRuntime = null;
            item1ViewHolder.mShaow = null;
            item1ViewHolder.mChecked = null;
            item1ViewHolder.mQuality = null;
            item1ViewHolder.ivDownloadSubtitle = null;
            item1ViewHolder.tvSubtitleNum = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item2ViewHolder_ViewBinding implements Unbinder {
        private Item2ViewHolder target;

        public Item2ViewHolder_ViewBinding(Item2ViewHolder item2ViewHolder, View view) {
            this.target = item2ViewHolder;
            item2ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_poster, "field 'mCover'", ImageView.class);
            item2ViewHolder.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.move_download_progress, "field 'mProgress'", ProgressBar.class);
            item2ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_name, "field 'mName'", TextView.class);
            item2ViewHolder.record = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_seasons, "field 'record'", TextView.class);
            item2ViewHolder.mSum = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_sum, "field 'mSum'", TextView.class);
            item2ViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_speed, "field 'mSpeed'", TextView.class);
            item2ViewHolder.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_size, "field 'mSize'", TextView.class);
            item2ViewHolder.mRuntime = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_runtime, "field 'mRuntime'", TextView.class);
            item2ViewHolder.mShaow = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.move_download_shadow, "field 'mShaow'", FrameLayout.class);
            item2ViewHolder.mChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_checked, "field 'mChecked'", ImageView.class);
            item2ViewHolder.mQuality = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_quality, "field 'mQuality'", ImageView.class);
            item2ViewHolder.ivDownloadSubtitle = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_Download_Subtitle, "field 'ivDownloadSubtitle'", ImageView.class);
            item2ViewHolder.tvSubtitleNum = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSubtitleNum, "field 'tvSubtitleNum'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item2ViewHolder item2ViewHolder = this.target;
            if (item2ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item2ViewHolder.mCover = null;
            item2ViewHolder.mProgress = null;
            item2ViewHolder.mName = null;
            item2ViewHolder.record = null;
            item2ViewHolder.mSum = null;
            item2ViewHolder.mSpeed = null;
            item2ViewHolder.mSize = null;
            item2ViewHolder.mRuntime = null;
            item2ViewHolder.mShaow = null;
            item2ViewHolder.mChecked = null;
            item2ViewHolder.mQuality = null;
            item2ViewHolder.ivDownloadSubtitle = null;
            item2ViewHolder.tvSubtitleNum = null;
        }
    }

    static /* synthetic */ long access$614(DownloadFragment downloadFragment, long j) {
        long j2 = downloadFragment.writeLength + j;
        downloadFragment.writeLength = j2;
        return j2;
    }

    @OnClick({R.id.fragmen_download_select, R.id.fragmen_download_delete, R.id.tvExport, R.id.llSmartDownload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmen_download_delete /* 2131296784 */:
                for (Map.Entry<String, DownloadModel> entry : this.choosed.entrySet()) {
                    if (TextUtils.isEmpty(entry.getValue().localPath)) {
                        this.list.remove(entry.getValue());
                        if (entry.getValue().box_type == 1) {
                            entry.getValue().startService(Constant.ACTION.MOVIE_DELETE, this.activity, entry.getValue().box_type);
                        } else {
                            entry.getValue().deleteTvAll(this.activity);
                        }
                    } else {
                        this.list.remove(entry.getValue());
                        new File(entry.getValue().localPath).delete();
                    }
                }
                this.choosed.clear();
                this.mDelete.setText("Delete");
                this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
                if (CommonUtils.canExportVideos()) {
                    this.tvExport.setEnabled(false);
                    this.tvExport.setTextColor(ContextCompat.getColor(App.getContext(), R.color.color_text));
                    this.tvExport.setText("Export");
                }
                DownloadAdapter downloadAdapter = this.mAdapter;
                if (downloadAdapter != null) {
                    downloadAdapter.notifyDataSetChanged();
                }
                this.mDelete.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadFragment.this.initSizeProgress();
                    }
                }, 500L);
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
                    if (CommonUtils.canExportVideos()) {
                        TextView textView2 = this.tvExport;
                        textView2.setText("Export(" + this.choosed.size() + ")");
                        this.tvExport.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    }
                } else {
                    for (DownloadModel downloadModel2 : this.list) {
                        downloadModel2.isChecked = 0;
                    }
                    this.choosed.clear();
                    this.isFull = false;
                    this.mSelect.setText("Select All");
                    this.mDelete.setText("Delete");
                    if (CommonUtils.canExportVideos()) {
                        this.tvExport.setText("Export");
                        this.tvExport.setTextColor(ContextCompat.getColor(getContext(), R.color.color_text));
                    }
                    this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
                    this.mSelect.setTextColor(getResources().getColor(R.color.color_text));
                }
                DownloadAdapter downloadAdapter2 = this.mAdapter;
                if (downloadAdapter2 != null) {
                    downloadAdapter2.notifyDataSetChanged();
                    return;
                }
                return;
            case R.id.llSmartDownload /* 2131297221 */:
                startActivity(new Intent(this.context, SmartDownloadSettingActivity.class));
                return;
            case R.id.tvExport /* 2131298081 */:
                new MsgHintDialog.Builder(getContext()).setContent("The video will export to Movies folder,you can view it with file browser.").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$rCRUtIJkWdcJlOdJSuP_BvYI4uA
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        DownloadFragment.this.exportVideos();
                    }
                }).setPositiveText("Export").create().show();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exportVideos() {
        this.writeLength = 0L;
        ((ObservableSubscribeProxy) Observable.just(this.choosed).map(new AnonymousClass3()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                DownloadFragment.this.disposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    ToastUtils.showShort("Exported");
                }
                if (DownloadFragment.this.dialog != null) {
                    DownloadFragment.this.dialog.dismiss();
                }
                DownloadFragment.this.ivEdit.performClick();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ToastUtils.showShort("Export failed:" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.fragment.DownloadFragment$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass3 implements Function<HashMap<String, DownloadModel>, Boolean> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.functions.Function
        public Boolean apply(HashMap<String, DownloadModel> hashMap) throws Exception {
            File externalFilesDir;
            ArrayList<Download> arrayList = new ArrayList();
            for (Map.Entry<String, DownloadModel> entry : DownloadFragment.this.choosed.entrySet()) {
                arrayList.addAll(App.getDB().downloadDao().selectByTvId(entry.getValue().movieId, entry.getValue().box_type));
            }
            long j = 0;
            for (Download download : arrayList) {
                j += download.getFileLength();
            }
            boolean z = false;
            if (MemoryUtils.getAvailSpaceSize(DownloadFragment.this.getContext()) <= j) {
                ToastUtils.showShort("Free storage space less than " + FileUtils.byte2FitMemorySize(j));
            } else {
                if (Constant.DIR_DOWNLOAD == null && (externalFilesDir = App.getContext().getExternalFilesDir("")) != null) {
                    Constant.DIR = externalFilesDir.getParent();
                    Constant.DIR_DOWNLOAD = Constant.DIR + File.separator + DownloadInfo.DOWNLOAD;
                }
                DownloadFragment.this.activity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadFragment.this.dialog = new ExportVideosProgressDialog(DownloadFragment.this.context, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.3.1.1
                            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                            public void onClick() {
                                if (DownloadFragment.this.disposable != null) {
                                    DownloadFragment.this.disposable.dispose();
                                }
                            }
                        });
                        DownloadFragment.this.dialog.show();
                    }
                });
                boolean z2 = false;
                for (Download download2 : arrayList) {
                    Uri saveToGalleryVideoUri = download2.getBox_type() == 1 ? DownloadFragment.this.getSaveToGalleryVideoUri(String.format("%s_%s_%s", download2.getTitle(), download2.getQuality(), download2.getMovieId())) : DownloadFragment.this.getSaveToGalleryVideoUri(String.format("%s_S%sE%s_%s_%s", download2.getTitle(), Integer.valueOf(download2.getSeason()), Integer.valueOf(download2.getEpisode()), download2.getQuality(), download2.getMovieId()));
                    String fileName = RemoteFileUtil.getFileName(download2.getPath(), download2.getTitle(), Constant.DIR_DOWNLOAD);
                    final String byte2FitMemorySize = FileUtils.byte2FitMemorySize(j);
                    final int i = (int) (j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
                    FileUtils.writeFileToUri(new File(Constant.DIR_DOWNLOAD, fileName), saveToGalleryVideoUri, new WriteFileToUriListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.3.2
                        @Override // com.movieboxpro.android.listener.WriteFileToUriListener
                        public void onProgress(long j2) {
                            DownloadFragment.access$614(DownloadFragment.this, j2);
                            String format = String.format("%s/%s", FileUtils.byte2FitMemorySize(DownloadFragment.this.writeLength), byte2FitMemorySize);
                            if (DownloadFragment.this.dialog != null) {
                                DownloadFragment.this.dialog.updateProgress(format, (int) (DownloadFragment.this.writeLength / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), i);
                            }
                        }
                    });
                    z2 = true;
                }
                z = z2;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getSaveToGalleryVideoUri(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str + "_" + System.currentTimeMillis());
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000));
        return App.getContext().getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPlayUrl(final String str, final MovieDetail movieDetail) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.5
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                String str3;
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
                    str3 = "";
                    str2 = str3;
                } else {
                    str2 = string;
                    str3 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", str, "", str3, str2).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).map(new Function<MovieDetail, MovieDetail>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.5.2
                    @Override // io.reactivex.functions.Function
                    public MovieDetail apply(MovieDetail movieDetail2) throws Exception {
                        movieDetail.list.addAll(movieDetail2.list);
                        return movieDetail;
                    }
                }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(DownloadFragment.this))).subscribe(new Observer<MovieDetail>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.5.1
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        DownloadFragment.this.showLoading();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(MovieDetail movieDetail2) {
                        DownloadFragment.this.hideLoading();
                        DownloadFragment.this.route(MoviePlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", movieDetail).addParam(VideoPlayerActivity.VIDEO_ID, str).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        DownloadFragment.this.hideLoading();
                        DownloadFragment.this.route(MoviePlayerActivity.class, ParamsUtils.newBuilder().addParam("videoplayer_params", movieDetail).addParam(VideoPlayerActivity.VIDEO_ID, str).addParam(VideoActivityFactory.IS_LOCAL_FILE, true).build());
                    }
                });
            }
        });
    }

    private void checkOnlineDevice(final String str, final MovieDetail movieDetail) {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL).map(new Function<String, Object>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.7
            @Override // io.reactivex.functions.Function
            public Object apply(String str2) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str2, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() != -88) {
                    DownloadFragment.this.getPlayUrl(str, movieDetail);
                    return Observable.just("");
                }
                ScreenManageDialog newInstance = ScreenManageDialog.newInstance(new ArrayList(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), baseResponse.getMsg());
                newInstance.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.7.1
                    @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                    public void onStopDevice() {
                        DownloadFragment.this.getPlayUrl(str, movieDetail);
                    }
                });
                newInstance.show(DownloadFragment.this.getChildFragmentManager(), ScreenManageDialog.class.getSimpleName());
                return Observable.empty();
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Object>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.6
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                DownloadFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Object obj) {
                DownloadFragment.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                DownloadFragment.this.hideLoading();
                DownloadFragment.this.getPlayUrl(str, movieDetail);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkChildMode(String str) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) && CommonUtils.isChildModeVideo(str)) {
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this.context);
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$lX5e2L9aWPGEyFkyTJYi08svwZY
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    DownloadFragment.this.lambda$checkChildMode$0$DownloadFragment();
                }
            });
            childModeHintDialog.show();
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$checkChildMode$0$DownloadFragment() {
        InputChildModePasswordActivity.Companion.start(this.activity, 100);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseFragment
    public void onVisible() {
        super.onVisible();
        if (!this.loaded) {
            initData();
            this.loaded = true;
            return;
        }
        EventBus.getDefault().post(new DownloadChangedEvent());
        setData();
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        try {
            initSizeProgress();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSizeProgress() {
        ProgressBar progressBar = this.sizeProgressBar;
        if (progressBar == null || this.mCacheText == null) {
            return;
        }
        progressBar.setMax((int) FileUtils.getFsTotalSize(Constant.DIR_DOWNLOAD));
        this.sizeProgressBar.setProgress((int) (FileUtils.getFsTotalSize(Constant.DIR_DOWNLOAD) - FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD)));
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            this.mCacheText.setText(String.format("Internal Storage,Free:%s", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD))));
        } else {
            this.mCacheText.setText(String.format("SD Card,Free:%s", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD))));
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.fragment_donwload, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        CommonUtils.initSwipeColor(this.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$u_w9TPvoOoT-oJ-VcJFh3ERWao8
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DownloadFragment.this.lambda$initView$1$DownloadFragment();
            }
        });
        if (CommonUtils.canExportVideos()) {
            this.tvExport.setVisibility(0);
        }
        this.ivEdit.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$x4SjWUdj2J5zC5eYKRqeh0fiYDQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.this.lambda$initView$2$DownloadFragment(view);
            }
        });
        if (SettingManager.INSTANCE.getSmartDownload()) {
            this.tvSmartDownload.setText("ON");
        } else {
            this.tvSmartDownload.setText("OFF");
        }
        SmartDownloadChangedLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    DownloadFragment.this.tvSmartDownload.setText("ON");
                } else {
                    DownloadFragment.this.tvSmartDownload.setText("OFF");
                }
            }
        });
    }

    public /* synthetic */ void lambda$initView$1$DownloadFragment() {
        if (Network.isConnected(this.context)) {
            setData();
        } else {
            this.swipeRefreshLayout.setRefreshing(false);
        }
    }

    public /* synthetic */ void lambda$initView$2$DownloadFragment(View view) {
        int i = this.Flag;
        if (i == 0) {
            this.Flag = 1;
            this.mTabs.setVisibility(0);
            this.mAdapter.notifyDataSetChanged();
            GlideUtils.load((Activity) getActivity(), (int) R.mipmap.ic_edit_down, this.ivEdit);
        } else if (i != 1) {
        } else {
            this.Flag = 0;
            this.mTabs.setVisibility(8);
            GlideUtils.load((Activity) getActivity(), (int) R.mipmap.ic_edit, this.ivEdit);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteVideo(OnDeleteDownloadEvent onDeleteDownloadEvent) {
        initSizeProgress();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.receiver == null) {
            this.receiver = new BookDownloadReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_FAILURE);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PAUSED);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_STARTED);
            LocalBroadcastManager.getInstance(this.context).registerReceiver(this.receiver, intentFilter);
        }
        if (this.mAdapter == null) {
            this.mAdapter = new DownloadAdapter(this.list);
            this.layoutManager = new MyLinearLayoutManager(this.context);
            this.mRecycler.setEmptyView(this.mLlEmptyView);
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onItemClickListener);
            RecyclerView.ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
        setData();
        DownloadPathLiveData.Companion.get().observe(this, new androidx.lifecycle.Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$Aso6umDsg0PJmJgIgU6MKjaSxRo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DownloadFragment.this.lambda$initData$3$DownloadFragment((String) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initData$3$DownloadFragment(String str) {
        initSizeProgress();
    }

    public void setData() {
        int i = 1;
        this.swipeRefreshLayout.setRefreshing(true);
        this.list.clear();
        this.choosed.clear();
        this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
        this.mDelete.setText("Delete");
        boolean z = false;
        if (CommonUtils.canExportVideos()) {
            this.tvExport.setEnabled(false);
            this.tvExport.setTextColor(ContextCompat.getColor(getContext(), R.color.color_text));
            this.tvExport.setText("Export");
        }
        this.downloader = null;
        List<Download> findId = App.getDB().downloadDao().findId(1, 2);
        List<Download> findId2 = App.getDB().downloadDao().findId(2, 2);
        MLog.d(this.TAG, "下载状态 ：" + findId.size() + ":" + findId2.size());
        for (Download download : findId) {
            if (download.getStatue() == 2) {
                this.list.add(new DownloadModel(download));
            }
        }
        for (Download download2 : findId2) {
            MLog.d(this.TAG, "下载状态2 ：" + download2.getMovieId());
            if (download2.getStatue() == 2) {
                DownloadModel downloadModel = new DownloadModel(download2);
                List<Download> findByTid = App.getDB().downloadDao().findByTid(downloadModel.getMovieId(), 2, 2);
                float f = 0.0f;
                for (Download download3 : findByTid) {
                    try {
                        f += Float.parseFloat(download3.getSize().substring(0, download3.getSize().indexOf(" ")));
                    } catch (Exception unused) {
                    }
                }
                MLog.d(this.TAG, "下载状态2 ：" + findByTid.size());
                downloadModel.childCount = findByTid.size();
                if (f > 1024.0f) {
                    downloadModel.setSize(String.format("%.2f G", Float.valueOf(f / 1024.0f)));
                } else {
                    downloadModel.setSize(String.format("%.2f MB", Float.valueOf(f)));
                }
                this.list.add(downloadModel);
            }
        }
        List<Download> findByTypes2 = App.getDB().downloadDao().findByTypes2(2);
        this.downloadingModels.clear();
        StringBuilder sb = new StringBuilder();
        if (findByTypes2 != null && findByTypes2.size() > 0) {
            for (int i2 = 0; i2 < findByTypes2.size(); i2++) {
                Download download4 = findByTypes2.get(i2);
                if (download4.getStatue() == 1 || download4.getStatue() == 0) {
                    this.downloadingModels.add(new DownloadModel(download4));
                }
                if (i2 != findByTypes2.size() - 1) {
                    if (download4.getBox_type() == 2) {
                        sb.append(download4.getSeasontitle());
                        sb.append("/");
                    } else {
                        sb.append(download4.getTitle());
                        sb.append("/");
                    }
                } else if (download4.getBox_type() == 2) {
                    sb.append(download4.getSeasontitle());
                } else {
                    sb.append(download4.getTitle());
                }
            }
            Download download5 = findByTypes2.get(0);
            long j = 0;
            long j2 = 0;
            long j3 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < this.downloadingModels.size()) {
                DownloadModel downloadModel2 = this.downloadingModels.get(i3);
                if (downloadModel2.statue == i || downloadModel2.statue == 0) {
                    i4++;
                    j += downloadModel2.speed;
                    j3 += getFileSize(downloadModel2.size);
                    j2 += downloadModel2.fileLength;
                }
                i3++;
                i = 1;
            }
            download5.setTitle(sb.toString());
            download5.setSpeed(j);
            download5.setFileLength(j2);
            download5.setSize(FileUtils.byte2FitMemorySize(j3));
            download5.setCount(String.valueOf(i4));
            if (j3 != 0) {
                download5.setProgress(Integer.parseInt(TimeUtils.INSTANCE.calPercent(j2, j3)));
            }
            DownloadModel downloadModel3 = new DownloadModel(download5);
            this.downloader = downloadModel3;
            downloadModel3.box_type = 3;
            z = false;
            this.list.add(0, this.downloader);
        }
        this.mAdapter.notifyDataSetChanged();
        this.swipeRefreshLayout.setRefreshing(z);
    }

    private long getFileSize(String str) {
        float parseFloat;
        float f;
        if (str.contains("MB")) {
            parseFloat = Float.parseFloat(str.replace("MB", "").trim());
            f = 1048576.0f;
        } else if (str.contains("GB")) {
            parseFloat = Float.parseFloat(str.replace("GB", "").trim());
            f = 1.07374182E9f;
        } else if (!str.contains("KB")) {
            return 0L;
        } else {
            parseFloat = Float.parseFloat(str.replace("KB", "").trim());
            f = 1024.0f;
        }
        return parseFloat * f;
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        CallManager.cancelAll(getClass().getSimpleName());
        RxManager.remove(this.TAG);
        if (this.receiver != null) {
            LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.receiver);
        }
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class BookDownloadReceiver extends BroadcastReceiver {
        private BookDownloadReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MLog.d(DownloadFragment.this.TAG, "onReceive");
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(Constant.Download.PARAMS_KEY_MOVIE_ID);
            int intExtra = intent.getIntExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, 1);
            if (stringExtra != null) {
                String str = DownloadFragment.this.TAG;
                MLog.d(str, "onDownloadProgress: " + action + ", " + stringExtra);
                if (Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE.equals(action)) {
                    DownloadFragment.this.setData();
                    EventBus.getDefault().post(new DownloadChangedEvent());
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_FAILURE.equals(action)) {
                    intent.getStringExtra(Constant.Download.PARAMS_KEY_REASON);
                    EventBus.getDefault().post(new DownloadChangedEvent());
                    DownloadFragment.this.setData();
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS.equals(action)) {
                    int i = 0;
                    int intExtra2 = intent.getIntExtra(Constant.Download.PARAMS_KEY_PROGRESS, 0);
                    long j = 0;
                    long longExtra = intent.getLongExtra(Constant.Download.PARAMS_KEY_SIZE, 0L);
                    synchronized ("") {
                        if (!TextUtils.isEmpty(stringExtra)) {
                            while (true) {
                                if (i >= DownloadFragment.this.downloadingModels.size()) {
                                    break;
                                }
                                DownloadModel downloadModel = (DownloadModel) DownloadFragment.this.downloadingModels.get(i);
                                if (DownloadFragment.this.getChoose(stringExtra, intExtra, (DownloadModel) DownloadFragment.this.downloadingModels.get(i))) {
                                    long j2 = longExtra - downloadModel.fileLength;
                                    if (j2 >= 0) {
                                        j = j2;
                                    }
                                    downloadModel.progress = intExtra2;
                                    downloadModel.fileLength = longExtra;
                                    downloadModel.speed = j;
                                    if (System.currentTimeMillis() - DownloadFragment.this.lastShowSpeedTime >= 1000) {
                                        DownloadFragment.this.lastShowSpeedTime = System.currentTimeMillis();
                                        DownloadFragment.this.setTotalSizeSpeed();
                                    }
                                } else {
                                    i++;
                                }
                            }
                        }
                    }
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_PAUSED.equals(action)) {
                    DownloadFragment.this.setData();
                    EventBus.getDefault().post(new DownloadChangedEvent());
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_DELETE.equals(action)) {
                    DownloadFragment.this.onDownloadDelete(stringExtra);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalSizeSpeed() {
        if (this.list.size() == 0) {
            return;
        }
        DownloadModel model = this.mAdapter.getModel(0);
        StringBuilder sb = new StringBuilder();
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        for (int i2 = 0; i2 < this.downloadingModels.size(); i2++) {
            DownloadModel downloadModel = this.downloadingModels.get(i2);
            if (downloadModel.statue == 1 || downloadModel.statue == 0) {
                i++;
                j += downloadModel.speed;
                j3 += getFileSize(downloadModel.size);
                j2 += downloadModel.fileLength;
            }
            if (i2 != this.downloadingModels.size() - 1) {
                if (downloadModel.box_type == 2) {
                    sb.append(downloadModel.seasontitle);
                    sb.append("/");
                } else {
                    sb.append(downloadModel.title);
                    sb.append("/");
                }
            } else if (downloadModel.box_type == 2) {
                sb.append(downloadModel.seasontitle);
            } else {
                sb.append(downloadModel.title);
            }
        }
        model.setTitle(sb.toString());
        model.setSpeed(j);
        model.setFileLength(j2);
        model.setSize(FileUtils.byte2FitMemorySize(j3));
        model.setCount(String.valueOf(i));
        if (j3 != 0) {
            model.setProgress(Integer.parseInt(TimeUtils.INSTANCE.calPercent(j2, j3)));
        }
        this.mAdapter.notifyItemChanged(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getChoose(String str, int i, DownloadModel downloadModel) {
        return i == downloadModel.box_type && str.equals(downloadModel.privateid);
    }

    private void onDownloadProgress(String str, int i, long j) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgress: " + str + ", " + i + "," + j);
        List<DownloadModel> list = this.list;
        if (list == null || list.size() <= 0) {
            return;
        }
        DownloadModel downloadModel = this.list.get(0);
        if (str.equals(downloadModel.getPrivateid())) {
            String str3 = this.TAG;
            MLog.d(str3, "onDownloadProgress1: " + StringUtils.getPrintSize(j - downloadModel.getFileLength()));
            downloadModel.progress = i;
            downloadModel.fileLength = j;
            downloadModel.speed = j - downloadModel.getFileLength();
            this.mAdapter.notifyItemChanged(0);
        }
    }

    private void onDownloadPaused(String str) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgressPaused: " + str);
        synchronized ("") {
            if (!TextUtils.isEmpty(str)) {
                int i = 0;
                while (true) {
                    if (i >= this.list.size()) {
                        break;
                    } else if (str.equals(this.list.get(i).privateid)) {
                        this.mAdapter.notifyItemChanged(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadDelete(String str) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadDelete: " + str);
        synchronized ("") {
            if (!TextUtils.isEmpty(str)) {
                int i = 0;
                while (true) {
                    if (i >= this.list.size()) {
                        break;
                    } else if (str.equals(this.list.get(i).privateid)) {
                        this.list.remove(i);
                        this.mAdapter.notifyItemChanged(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public class DownloadAdapter extends BaseAdapter<DownloadModel> {
        public DownloadAdapter(List<DownloadModel> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            BaseViewHolder item1ViewHolder;
            if (i == 0 || i == 1) {
                item1ViewHolder = new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_download_item, viewGroup, false), onItemClickListener);
            } else if (i == 2) {
                item1ViewHolder = new Item2ViewHolder(layoutInflater.inflate(R.layout.layout_download_item, viewGroup, false), onItemClickListener);
            } else if (i != 3) {
                return null;
            } else {
                item1ViewHolder = new ItemDownloadingViewHolder(layoutInflater.inflate(R.layout.layout_downloading_item2, viewGroup, false), onItemClickListener);
            }
            return item1ViewHolder;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            final DownloadModel model = getModel(i);
            if (model.box_type == 1 || model.box_type == 0) {
                Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
                if (model.statue == 2) {
                    item1ViewHolder.ivDownloadSubtitle.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$DownloadFragment$DownloadAdapter$XDxiwmqQDa5rSxWPNqbuScvfpQM
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DownloadFragment.DownloadAdapter.this.lambda$setView$0$DownloadFragment$DownloadAdapter(model, view);
                        }
                    });
                }
                setMovieType(model, item1ViewHolder);
            }
            if (model.box_type == 2) {
                setTvType(model, (Item2ViewHolder) baseViewHolder);
            }
            if (model.box_type == 3) {
                ItemDownloadingViewHolder itemDownloadingViewHolder = (ItemDownloadingViewHolder) baseViewHolder;
                GlideUtils.loadPortraitGifHolder(DownloadFragment.this.context, model.poster, itemDownloadingViewHolder.ivPoster);
                itemDownloadingViewHolder.tvNames.setText(model.title);
                if (model.speed == 0) {
                    itemDownloadingViewHolder.tvSpeed.setText("");
                    itemDownloadingViewHolder.tvSize.setText("");
                    itemDownloadingViewHolder.progressBar.setVisibility(4);
                    itemDownloadingViewHolder.tvCount.setVisibility(4);
                    return;
                }
                itemDownloadingViewHolder.tvSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_main_blue));
                TextView textView = itemDownloadingViewHolder.tvSpeed;
                textView.setText(FileUtils.byte2FitMemorySize(model.speed) + "/S");
                if (model.speed != 0) {
                    TextView textView2 = itemDownloadingViewHolder.tvSize;
                    textView2.setText(String.format("%s · %s", FileUtils.byte2FitMemorySize(model.fileLength) + "/" + model.size, TimeUtils.getTimeFull((getFileSize(model.size) - model.fileLength) / model.speed)));
                } else {
                    TextView textView3 = itemDownloadingViewHolder.tvSize;
                    textView3.setText(FileUtils.byte2FitMemorySize(model.fileLength) + "/" + model.size);
                }
                itemDownloadingViewHolder.progressBar.setVisibility(0);
                itemDownloadingViewHolder.progressBar.setProgress(model.progress);
                itemDownloadingViewHolder.tvCount.setVisibility(0);
                itemDownloadingViewHolder.tvCount.setText(model.count);
            }
        }

        public /* synthetic */ void lambda$setView$0$DownloadFragment$DownloadAdapter(DownloadModel downloadModel, View view) {
            if (TextUtils.isEmpty(downloadModel.fid)) {
                ToastUtils.showShort("you need re-download movie to download subtitle");
            } else {
                DownloadSubtitleActivity.Companion.starter(DownloadFragment.this.getContext(), downloadModel.title, downloadModel.movieId, downloadModel.fid, 0, 0);
            }
        }

        private long getFileSize(String str) {
            float parseFloat;
            float f;
            if (str.contains("MB")) {
                parseFloat = Float.parseFloat(str.replace("MB", "").trim());
                f = 1048576.0f;
            } else if (str.contains("GB")) {
                parseFloat = Float.parseFloat(str.replace("GB", "").trim());
                f = 1.07374182E9f;
            } else if (!str.contains("KB")) {
                return 0L;
            } else {
                parseFloat = Float.parseFloat(str.replace("KB", "").trim());
                f = 1024.0f;
            }
            return parseFloat * f;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return super.getType(i);
            }
            return getModel(i).box_type;
        }

        private void setMovieType(DownloadModel downloadModel, Item1ViewHolder item1ViewHolder) {
            if (DownloadFragment.this.activity != null && !DownloadFragment.this.activity.isFinishing()) {
                GlideUtils.loadPortraitGifHolder(DownloadFragment.this.context, downloadModel.poster, item1ViewHolder.mCover);
            }
            int i = 0;
            if (downloadModel.statue == 2) {
                item1ViewHolder.mProgress.setVisibility(8);
                item1ViewHolder.mShaow.setVisibility(8);
                item1ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item1ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                if (DownloadFragment.this.Flag == 1) {
                    item1ViewHolder.mChecked.setVisibility(0);
                } else {
                    item1ViewHolder.mChecked.setVisibility(8);
                }
                if (downloadModel.isChecked == 0) {
                    item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox);
                } else {
                    item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox_checked);
                }
                PlayRecode findByTypeid = App.getDB().playRecodeDao().findByTypeid(1, downloadModel.movieId);
                if (findByTypeid != null) {
                    item1ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white_70alpha));
                    item1ViewHolder.mSpeed.setText(String.format("RESUME %s", TimeUtils.getTime(findByTypeid.getStart_time() / 1000)));
                } else {
                    item1ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_main_blue));
                    item1ViewHolder.mSpeed.setText("Not watched");
                }
                List<File> listFilesInDir = FileUtils.listFilesInDir(new File(Constant.DIR_UPLOAD_MOVIE_SUBTITLE + File.separator + downloadModel.movieId + File.separator + downloadModel.title));
                if (listFilesInDir != null) {
                    for (File file : listFilesInDir) {
                        List<File> listFilesInDir2 = FileUtils.listFilesInDir(file);
                        if (listFilesInDir2 != null) {
                            i += listFilesInDir2.size();
                        }
                    }
                }
                if (i > 0) {
                    item1ViewHolder.ivDownloadSubtitle.setImageResource(R.mipmap.ic_download_subtitle_num);
                    item1ViewHolder.tvSubtitleNum.setText(String.valueOf(i));
                } else {
                    item1ViewHolder.ivDownloadSubtitle.setImageResource(R.mipmap.ic_download_subtitle);
                    item1ViewHolder.tvSubtitleNum.setText("");
                }
            } else if (downloadModel.statue == 1) {
                item1ViewHolder.mProgress.setVisibility(0);
                item1ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item1ViewHolder.mShaow.setVisibility(0);
                TextView textView = item1ViewHolder.mSpeed;
                textView.setText(StringUtils.getPrintSize(downloadModel.speed) + "/S");
                item1ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_main_blue));
                TextView textView2 = item1ViewHolder.mSize;
                textView2.setText(StringUtils.getPrintSize(downloadModel.fileLength) + "/" + downloadModel.size);
                item1ViewHolder.mChecked.setVisibility(8);
                item1ViewHolder.record.setVisibility(8);
                item1ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
                item1ViewHolder.tvSubtitleNum.setText("");
            } else if (downloadModel.statue == 3) {
                item1ViewHolder.mProgress.setVisibility(0);
                item1ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.gray));
                item1ViewHolder.mSpeed.setText("Suspended");
                item1ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.gray));
                item1ViewHolder.mShaow.setVisibility(0);
                item1ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                item1ViewHolder.mChecked.setVisibility(8);
                item1ViewHolder.record.setVisibility(8);
                item1ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
                item1ViewHolder.tvSubtitleNum.setText("");
            } else if (downloadModel.statue == 4) {
                item1ViewHolder.mProgress.setVisibility(0);
                item1ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.error_text));
                item1ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.error_text));
                item1ViewHolder.mSpeed.setText("FAIL");
                item1ViewHolder.mShaow.setVisibility(0);
                item1ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                item1ViewHolder.mChecked.setVisibility(8);
                item1ViewHolder.record.setVisibility(8);
                item1ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
                item1ViewHolder.tvSubtitleNum.setText("");
            } else if (downloadModel.statue == 0) {
                item1ViewHolder.record.setVisibility(8);
            }
            item1ViewHolder.mProgress.setProgress(downloadModel.progress);
            item1ViewHolder.mName.setText(downloadModel.title);
            item1ViewHolder.mQuality.setImageResource(CommonUtils.getMovieQualityTag(downloadModel.quality));
        }

        private void setTvType(DownloadModel downloadModel, Item2ViewHolder item2ViewHolder) {
            if (DownloadFragment.this.activity != null && !DownloadFragment.this.activity.isFinishing()) {
                GlideUtils.loadPortraitGifHolder(DownloadFragment.this.context, downloadModel.poster, item2ViewHolder.mCover);
            }
            if (DownloadFragment.this.Flag == 1) {
                item2ViewHolder.mChecked.setVisibility(0);
            } else {
                item2ViewHolder.mChecked.setVisibility(8);
            }
            if (downloadModel.isChecked == 0) {
                item2ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox);
            } else {
                item2ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox_checked);
            }
            item2ViewHolder.record.setVisibility(8);
            if (downloadModel.statue == 2) {
                item2ViewHolder.mProgress.setVisibility(8);
                item2ViewHolder.mShaow.setVisibility(8);
                item2ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item2ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item2ViewHolder.mSpeed.setVisibility(8);
                List<Download> findByTid = App.getDB().downloadDao().findByTid(new TvDetail(downloadModel).id, 2, 2);
                if (findByTid != null) {
                    long j = 0;
                    for (Download download : findByTid) {
                        j += download.getFileLength();
                    }
                    item2ViewHolder.mSize.setText(StringUtils.getPrintSize(j));
                }
                SpanUtils.with(item2ViewHolder.mName).append(downloadModel.title).setForegroundColor(DownloadFragment.this.getResources().getColor(R.color.white)).setFontSize(16, true).append(String.format(" (%sP)", Integer.valueOf(downloadModel.childCount))).setForegroundColor(DownloadFragment.this.getResources().getColor(R.color.white_70alpha)).setFontSize(12, true).create();
                item2ViewHolder.ivDownloadSubtitle.setImageResource(R.mipmap.ic_right_arrow);
            } else if (downloadModel.statue == 1) {
                item2ViewHolder.mProgress.setVisibility(0);
                item2ViewHolder.mSize.setVisibility(0);
                item2ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item2ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.color_main_blue));
                item2ViewHolder.mShaow.setVisibility(0);
                TextView textView = item2ViewHolder.mSpeed;
                textView.setText(StringUtils.getPrintSize(downloadModel.speed) + "/S");
                TextView textView2 = item2ViewHolder.mSize;
                textView2.setText(StringUtils.getPrintSize(downloadModel.fileLength) + "/" + downloadModel.size);
                item2ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
                item2ViewHolder.mChecked.setVisibility(8);
                item2ViewHolder.mRuntime.setVisibility(8);
                item2ViewHolder.mName.setText(downloadModel.title);
            } else if (downloadModel.statue == 3) {
                item2ViewHolder.mProgress.setVisibility(0);
                item2ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item2ViewHolder.mSpeed.setText("Suspended");
                item2ViewHolder.mShaow.setVisibility(0);
                item2ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                item2ViewHolder.mChecked.setVisibility(8);
                item2ViewHolder.mRuntime.setVisibility(8);
                item2ViewHolder.mName.setText(downloadModel.title);
                item2ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
            } else if (downloadModel.statue == 4) {
                item2ViewHolder.mProgress.setVisibility(0);
                item2ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.error_text));
                item2ViewHolder.mSpeed.setTextColor(DownloadFragment.this.getResources().getColor(R.color.error_text));
                item2ViewHolder.mSpeed.setText("FAIL");
                item2ViewHolder.mShaow.setVisibility(0);
                item2ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                item2ViewHolder.mChecked.setVisibility(8);
                item2ViewHolder.mRuntime.setVisibility(8);
                item2ViewHolder.mName.setText(downloadModel.title);
                item2ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
            } else if (downloadModel.statue == 0) {
                item2ViewHolder.record.setVisibility(8);
                item2ViewHolder.mProgress.setVisibility(0);
                item2ViewHolder.mName.setTextColor(DownloadFragment.this.getResources().getColor(R.color.white));
                item2ViewHolder.mShaow.setVisibility(0);
                item2ViewHolder.mSize.setText(StringUtils.getPrintSize(downloadModel.fileLength));
                item2ViewHolder.mChecked.setVisibility(8);
                item2ViewHolder.mRuntime.setVisibility(8);
                item2ViewHolder.mName.setText(downloadModel.title);
                item2ViewHolder.ivDownloadSubtitle.setImageDrawable(null);
            }
            String str = DownloadFragment.this.TAG;
            MLog.d(str, "下载输出 : " + downloadModel.season);
            item2ViewHolder.mProgress.setProgress(downloadModel.progress);
            item2ViewHolder.mQuality.setImageResource(R.drawable.ic_tv_show);
        }
    }

    /* loaded from: classes3.dex */
    static class ItemDownloadingViewHolder extends BaseViewHolder {
        @BindView(R.id.move_download_poster)
        ImageView ivPoster;
        @BindView(R.id.move_download_progress)
        ProgressBar progressBar;
        @BindView(R.id.tvCount)
        TextView tvCount;
        @BindView(R.id.tvNames)
        TextView tvNames;
        @BindView(R.id.move_download_size)
        TextView tvSize;
        @BindView(R.id.move_download_speed)
        TextView tvSpeed;

        ItemDownloadingViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.ItemDownloadingViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(ItemDownloadingViewHolder.this.listener instanceof OnClickListener) || (adapterPosition = ItemDownloadingViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnClickListener) ItemDownloadingViewHolder.this.listener).onMovieClicked(adapterPosition, ItemDownloadingViewHolder.this.ivPoster);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_Download_Subtitle)
        ImageView ivDownloadSubtitle;
        @BindView(R.id.move_download_checked)
        ImageView mChecked;
        @BindView(R.id.move_download_poster)
        ImageView mCover;
        @BindView(R.id.move_download_name)
        TextView mName;
        @BindView(R.id.move_download_progress)
        ProgressBar mProgress;
        @BindView(R.id.move_download_quality)
        ImageView mQuality;
        @BindView(R.id.move_download_runtime)
        TextView mRuntime;
        @BindView(R.id.move_download_shadow)
        FrameLayout mShaow;
        @BindView(R.id.move_download_size)
        TextView mSize;
        @BindView(R.id.move_download_speed)
        TextView mSpeed;
        @BindView(R.id.move_download_sum)
        TextView mSum;
        @BindView(R.id.move_download_seasons)
        TextView record;
        @BindView(R.id.tvSubtitleNum)
        TextView tvSubtitleNum;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.Item1ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item1ViewHolder.this.listener instanceof OnClickListener) || (adapterPosition = Item1ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnClickListener) Item1ViewHolder.this.listener).onMovieClicked(adapterPosition, Item1ViewHolder.this.mChecked);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Item2ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_Download_Subtitle)
        ImageView ivDownloadSubtitle;
        @BindView(R.id.move_download_checked)
        ImageView mChecked;
        @BindView(R.id.move_download_poster)
        ImageView mCover;
        @BindView(R.id.move_download_name)
        TextView mName;
        @BindView(R.id.move_download_progress)
        ProgressBar mProgress;
        @BindView(R.id.move_download_quality)
        ImageView mQuality;
        @BindView(R.id.move_download_runtime)
        TextView mRuntime;
        @BindView(R.id.move_download_shadow)
        FrameLayout mShaow;
        @BindView(R.id.move_download_size)
        TextView mSize;
        @BindView(R.id.move_download_speed)
        TextView mSpeed;
        @BindView(R.id.move_download_sum)
        TextView mSum;
        @BindView(R.id.move_download_seasons)
        TextView record;
        @BindView(R.id.tvSubtitleNum)
        TextView tvSubtitleNum;

        Item2ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.Item2ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item2ViewHolder.this.listener instanceof OnClickListener) || (adapterPosition = Item2ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnClickListener) Item2ViewHolder.this.listener).onTvClicked(adapterPosition, Item2ViewHolder.this.mChecked);
                }
            });
        }

        public ImageView getImageView() {
            return this.mCover;
        }
    }
}
