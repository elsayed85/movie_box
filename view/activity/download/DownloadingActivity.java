package com.movieboxpro.android.view.activity.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.ares.downloader.jarvis.Jarvis;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.ChangeToPauseEvent;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class DownloadingActivity extends BaseActivity {
    private static final String lock = "";
    @BindView(R.id.clTotal)
    ConstraintLayout clTotal;
    private MyLinearLayoutManager layoutManager;
    private DownloadAdapter mAdapter;
    @BindView(R.id.donwmload_cache_size)
    TextView mCacheSize;
    @BindView(R.id.activity_download_delete)
    TextView mDelete;
    @BindView(R.id.activity_download_memory)
    TextView mMemory;
    @BindView(R.id.download_recycler_normal)
    RecyclerView mRecycler;
    @BindView(R.id.activity_download_select)
    TextView mSelect;
    @BindView(R.id.activity_download_start)
    TextView mStart;
    @BindView(R.id.activity_download_tabs)
    LinearLayout mTabs;
    private BookDownloadReceiver receiver;
    @BindView(R.id.sizeProgressBar)
    ProgressBar sizeProgressBar;
    @BindView(R.id.tvSize)
    TextView tvSize;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    public List<DownloadModel> list = new ArrayList();
    public HashMap<String, DownloadModel> choosed = new HashMap<>();
    private int Flag = 0;
    private boolean isFull = false;
    OnClickListener onClickListener = new OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
        }

        @Override // com.movieboxpro.android.view.activity.download.DownloadingActivity.OnClickListener
        public void onMovieClicked(int i, ImageView imageView) {
            if (i < 0 || i > DownloadingActivity.this.mAdapter.getItemCount() - 1) {
                return;
            }
            DownloadModel downloadModel = DownloadingActivity.this.list.get(i);
            if (DownloadingActivity.this.Flag == 0) {
                DownloadingActivity.this.Controller(downloadModel);
                return;
            }
            if (downloadModel.isChecked == 0) {
                if (!DownloadingActivity.this.choosed.containsKey(downloadModel.privateid)) {
                    DownloadingActivity.this.choosed.put(downloadModel.privateid, downloadModel);
                    DownloadingActivity.this.list.get(i).isChecked = 1;
                }
            } else if (DownloadingActivity.this.choosed.containsKey(downloadModel.privateid)) {
                DownloadingActivity.this.choosed.remove(downloadModel.privateid);
                DownloadingActivity.this.list.get(i).isChecked = 0;
            }
            if (!DownloadingActivity.this.choosed.isEmpty() && DownloadingActivity.this.choosed.size() > 0) {
                TextView textView = DownloadingActivity.this.mDelete;
                textView.setText("Delete(" + DownloadingActivity.this.choosed.size() + ")");
                DownloadingActivity.this.mDelete.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                if (DownloadingActivity.this.choosed.size() == DownloadingActivity.this.list.size()) {
                    DownloadingActivity.this.isFull = true;
                    DownloadingActivity.this.mSelect.setText("Deselect All");
                    DownloadingActivity.this.mSelect.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_text));
                } else {
                    DownloadingActivity.this.isFull = false;
                    DownloadingActivity.this.mSelect.setText("Select All");
                    DownloadingActivity.this.mSelect.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                }
            } else {
                DownloadingActivity.this.mDelete.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_text));
                DownloadingActivity.this.mDelete.setText("Delete");
            }
            DownloadingActivity.this.mAdapter.notifyItemChanged(i);
        }

        @Override // com.movieboxpro.android.view.activity.download.DownloadingActivity.OnClickListener
        public void onTvClicked(int i, ImageView imageView) {
            if (i < 0 || i > DownloadingActivity.this.mAdapter.getItemCount() - 1) {
                return;
            }
            DownloadModel downloadModel = DownloadingActivity.this.list.get(i);
            if (DownloadingActivity.this.Flag == 0) {
                DownloadingActivity.this.Controller(downloadModel);
                return;
            }
            if (downloadModel.isChecked == 0) {
                if (!DownloadingActivity.this.choosed.containsKey(downloadModel.privateid)) {
                    DownloadingActivity.this.choosed.put(downloadModel.privateid, downloadModel);
                    DownloadingActivity.this.list.get(i).isChecked = 1;
                }
            } else if (DownloadingActivity.this.choosed.containsKey(downloadModel.privateid)) {
                DownloadingActivity.this.choosed.remove(downloadModel.privateid);
                DownloadingActivity.this.list.get(i).isChecked = 0;
            }
            if (!DownloadingActivity.this.choosed.isEmpty() && DownloadingActivity.this.choosed.size() > 0) {
                TextView textView = DownloadingActivity.this.mDelete;
                textView.setText("Delete(" + DownloadingActivity.this.choosed.size() + ")");
                DownloadingActivity.this.mDelete.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                if (DownloadingActivity.this.choosed.size() == DownloadingActivity.this.list.size()) {
                    DownloadingActivity.this.isFull = true;
                    DownloadingActivity.this.mSelect.setText("Deselect All");
                    DownloadingActivity.this.mSelect.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_text));
                } else {
                    DownloadingActivity.this.isFull = false;
                    DownloadingActivity.this.mSelect.setText("Select All");
                    DownloadingActivity.this.mSelect.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                }
            } else {
                DownloadingActivity.this.mDelete.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_text));
                DownloadingActivity.this.mDelete.setText("Delete");
            }
            DownloadingActivity.this.mAdapter.notifyItemChanged(i);
        }
    };
    private long lastShowSpeedTime = 0;

    /* loaded from: classes3.dex */
    interface OnClickListener extends OnItemClickListener {
        void onMovieClicked(int i, ImageView imageView);

        void onTvClicked(int i, ImageView imageView);
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected int getStatusTintColor() {
        return R.color.color_main;
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.move_download_progress, "field 'mProgress'", ProgressBar.class);
            item1ViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_speed, "field 'mSpeed'", TextView.class);
            item1ViewHolder.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_size, "field 'mSize'", TextView.class);
            item1ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_name, "field 'mName'", TextView.class);
            item1ViewHolder.mChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_checked, "field 'mChecked'", ImageView.class);
            item1ViewHolder.mShaow = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.move_download_shadow, "field 'mShaow'", FrameLayout.class);
            item1ViewHolder.mQuality = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_quality, "field 'mQuality'", ImageView.class);
            item1ViewHolder.mSeason = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_seasons, "field 'mSeason'", TextView.class);
            item1ViewHolder.tvSeasonEpisode = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSeasonEpisode, "field 'tvSeasonEpisode'", TextView.class);
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
            item1ViewHolder.mSpeed = null;
            item1ViewHolder.mSize = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.mChecked = null;
            item1ViewHolder.mShaow = null;
            item1ViewHolder.mQuality = null;
            item1ViewHolder.mSeason = null;
            item1ViewHolder.tvSeasonEpisode = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item2ViewHolder_ViewBinding implements Unbinder {
        private Item2ViewHolder target;

        public Item2ViewHolder_ViewBinding(Item2ViewHolder item2ViewHolder, View view) {
            this.target = item2ViewHolder;
            item2ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_poster, "field 'mCover'", ImageView.class);
            item2ViewHolder.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.move_download_progress, "field 'mProgress'", ProgressBar.class);
            item2ViewHolder.mSeason = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_seasons, "field 'mSeason'", TextView.class);
            item2ViewHolder.mSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_speed, "field 'mSpeed'", TextView.class);
            item2ViewHolder.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_size, "field 'mSize'", TextView.class);
            item2ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.move_download_name, "field 'mName'", TextView.class);
            item2ViewHolder.mChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_checked, "field 'mChecked'", ImageView.class);
            item2ViewHolder.mShaow = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.move_download_shadow, "field 'mShaow'", FrameLayout.class);
            item2ViewHolder.mQuality = (ImageView) Utils.findRequiredViewAsType(view, R.id.move_download_quality, "field 'mQuality'", ImageView.class);
            item2ViewHolder.tvSeasonEpisode = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSeasonEpisode, "field 'tvSeasonEpisode'", TextView.class);
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
            item2ViewHolder.mSeason = null;
            item2ViewHolder.mSpeed = null;
            item2ViewHolder.mSize = null;
            item2ViewHolder.mName = null;
            item2ViewHolder.mChecked = null;
            item2ViewHolder.mShaow = null;
            item2ViewHolder.mQuality = null;
            item2ViewHolder.tvSeasonEpisode = null;
        }
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, DownloadingActivity.class));
    }

    public static void startWithFlags(Context context) {
        Intent intent = new Intent(context, DownloadingActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @OnClick({R.id.activity_download_start, R.id.donwmload_cache_size})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.activity_download_start) {
            if (id != R.id.donwmload_cache_size) {
                return;
            }
            showAddOptions();
        } else if (this.mStart.getTag().equals(0)) {
            this.mStart.setTag(1);
            this.mStart.setText("Suspend All");
            Intent intent = new Intent(this.context, DownloadService.class);
            intent.setAction(Constant.ACTION.MOVIE_STARTALL);
            this.activity.startService(intent);
        } else {
            this.mStart.setTag(0);
            this.mStart.setText("Start All");
            Intent intent2 = new Intent(this.context, DownloadService.class);
            intent2.setAction(Constant.ACTION.MOVIE_PAUSEDALL);
            this.activity.startService(intent2);
        }
    }

    @OnClick({R.id.activity_download_select, R.id.activity_download_delete})
    public void onSelectClicked(View view) {
        int id = view.getId();
        if (id == R.id.activity_download_delete) {
            for (Map.Entry<String, DownloadModel> entry : this.choosed.entrySet()) {
                this.list.remove(entry.getValue());
                entry.getValue().startService(Constant.ACTION.MOVIE_DELETE, this.activity);
            }
            this.choosed.clear();
            this.mDelete.setText("Delete");
            this.mDelete.setTextColor(getResources().getColor(R.color.color_text));
            this.mAdapter.notifyDataSetChanged();
        } else if (id == R.id.activity_download_select) {
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
        }
    }

    public void Controller(final DownloadModel downloadModel) {
        if (downloadModel.statue == 3) {
            if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false) && !Network.isWifiConnected(this)) {
                new MsgHintDialog.Builder(this).setTitle("Note").setContent("You are using celluar,continue to download?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.download.-$$Lambda$DownloadingActivity$dfNRJxk4NXuS_FPZnW9R3oK8HxU
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        DownloadingActivity.this.lambda$Controller$0$DownloadingActivity(downloadModel);
                    }
                }).create().show();
            } else {
                downloadModel.startService(Constant.ACTION.MOVIE_DOWNLOAD, this.activity);
            }
        } else if (downloadModel.statue == 4) {
            if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.USER_CELLUAR_DOWNLOAD, false) && !Network.isWifiConnected(this)) {
                new MsgHintDialog.Builder(this).setTitle("Note").setContent("You are using celluar,continue to download?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.download.-$$Lambda$DownloadingActivity$YY9oaDk4FYqo7TOVO_YXuskl5sA
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        DownloadingActivity.this.lambda$Controller$1$DownloadingActivity(downloadModel);
                    }
                }).create().show();
            } else {
                downloadModel.startService(Constant.ACTION.MOVIE_ERROR, this.activity);
            }
        } else if (downloadModel.statue == 0) {
            Download findByType = App.getDB().downloadDao().findByType(downloadModel.box_type, downloadModel.privateid);
            findByType.setStatue(3);
            App.getDB().downloadDao().update(findByType);
            onDownloadPaused(downloadModel.privateid, downloadModel.box_type);
        } else {
            downloadModel.startService(Constant.ACTION.MOVIE_PAUSED, this.activity);
        }
    }

    public /* synthetic */ void lambda$Controller$0$DownloadingActivity(DownloadModel downloadModel) {
        downloadModel.startService(Constant.ACTION.MOVIE_DOWNLOAD, this.activity);
    }

    public /* synthetic */ void lambda$Controller$1$DownloadingActivity(DownloadModel downloadModel) {
        downloadModel.startService(Constant.ACTION.MOVIE_ERROR, this.activity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeToPause(ChangeToPauseEvent changeToPauseEvent) {
        Download findByType = App.getDB().downloadDao().findByType(changeToPauseEvent.getBoxType(), changeToPauseEvent.getPrivateId());
        findByType.setStatue(3);
        App.getDB().downloadDao().update(findByType);
        onDownloadPaused(changeToPauseEvent.getPrivateId(), changeToPauseEvent.getBoxType());
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_downloading, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        initSpaceSize();
        this.mStart.setTag(1);
        setTitleRightText("Edit", new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i = DownloadingActivity.this.Flag;
                if (i == 0) {
                    DownloadingActivity.this.Flag = 1;
                    DownloadingActivity.this.mTabs.setVisibility(0);
                    DownloadingActivity.this.mAdapter.notifyDataSetChanged();
                    DownloadingActivity.this.mTitleRightText.setText("Done");
                } else if (i != 1) {
                } else {
                    DownloadingActivity.this.Flag = 0;
                    DownloadingActivity.this.choosed.clear();
                    DownloadingActivity.this.mTabs.setVisibility(8);
                    DownloadingActivity.this.mTitleRightText.setText("Edit");
                    DownloadingActivity.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        if (this.mAdapter == null) {
            this.mAdapter = new DownloadAdapter(this.list);
            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
            this.layoutManager = myLinearLayoutManager;
            this.mRecycler.setLayoutManager(myLinearLayoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onClickListener);
            RecyclerView.ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
        if (this.receiver == null) {
            this.receiver = new BookDownloadReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_FAILURE);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_PAUSED);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_STARTED);
            intentFilter.addAction(Constant.ACTION.DOWNLOAD_MOVIE_READY);
            LocalBroadcastManager.getInstance(this.context).registerReceiver(this.receiver, intentFilter);
        }
    }

    private void initSpaceSize() {
        ProgressBar progressBar = this.sizeProgressBar;
        if (progressBar == null || this.mMemory == null) {
            return;
        }
        progressBar.setMax((int) FileUtils.getFsTotalSize(Constant.DIR_DOWNLOAD));
        this.sizeProgressBar.setProgress((int) (FileUtils.getFsTotalSize(Constant.DIR_DOWNLOAD) - FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD)));
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            this.mMemory.setText(String.format("Internal Storage,Free:%s", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD))));
        } else {
            this.mMemory.setText(String.format("SD Card,Free:%s", FileUtils.byte2FitMemorySize(FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD))));
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        setTitle("DOWNLOADING");
        this.mCacheSize.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.3
            @Override // java.lang.Runnable
            public void run() {
                DownloadingActivity.this.updateAllStatus();
            }
        }, 500L);
        this.mCacheSize.setText(String.format("Max Concurrent %s", Integer.valueOf(PrefsUtils.getInstance().getInt(Constant.MAX_DOWNLOAD_COUNT, 1))));
        List<Download> all = App.getDB().downloadDao().getAll();
        String str = this.TAG;
        MLog.d(str, "下载+++ ：" + all.size());
        this.list.clear();
        for (Download download : all) {
            if (download.getStatue() == 0 || download.getStatue() == 1 || download.getStatue() == 3 || download.getStatue() == 4) {
                this.list.add(new DownloadModel(download));
            }
        }
        this.mAdapter.notifyDataSetChanged();
        if (this.list.size() != 0) {
            this.clTotal.setVisibility(0);
            if (this.list.size() == 1) {
                this.tvTotal.setText("1 Video");
            } else {
                this.tvTotal.setText(String.format("%s Videos", Integer.valueOf(this.list.size())));
            }
        }
        this.clTotal.setKeepScreenOn(true);
        setTotalSizeSpeed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.clTotal.setKeepScreenOn(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CallManager.cancelAll(getClass().getSimpleName());
        RxManager.remove(this.TAG);
        if (this.receiver != null) {
            LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.receiver);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDownloadCount(int i) {
        PrefsUtils.getInstance().putInt(Constant.MAX_DOWNLOAD_COUNT, i);
        Intent intent = new Intent(this.activity, DownloadService.class);
        intent.setAction(Constant.ACTION.MOVIE_RESET_MAX_COUNT);
        this.activity.startService(intent);
    }

    private void showAddOptions() {
        new ActionSheetDialog(this.context).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("(VIP)  3", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.6
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                if (App.isLogin()) {
                    if (App.getUserData().isvip == 1) {
                        Jarvis.getInstance().initThreadPoolLength(3);
                        DownloadingActivity.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                DownloadingActivity.this.mCacheSize.setText("Max Concurrent 3");
                                DownloadingActivity.this.setDownloadCount(3);
                            }
                        });
                        return;
                    }
                    DownloadingActivity.this.route(VipActivity.class);
                    return;
                }
                DownloadingActivity.this.route(Login2Activity.class);
            }
        }).addSheetItem("(VIP)  2", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.5
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                if (App.isLogin()) {
                    if (App.getUserData().isvip == 1) {
                        Jarvis.getInstance().initThreadPoolLength(2);
                        DownloadingActivity.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                DownloadingActivity.this.mCacheSize.setText("Max Concurrent 2");
                                DownloadingActivity.this.setDownloadCount(2);
                            }
                        });
                        return;
                    }
                    DownloadingActivity.this.route(VipActivity.class);
                    return;
                }
                DownloadingActivity.this.route(Login2Activity.class);
            }
        }).addSheetItem(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.4
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i) {
                Jarvis.getInstance().initThreadPoolLength(1);
                DownloadingActivity.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadingActivity.this.mCacheSize.setText("Max Concurrent 1");
                        DownloadingActivity.this.setDownloadCount(1);
                    }
                });
            }
        }).show();
    }

    private boolean getChoose(String str, int i, DownloadModel downloadModel) {
        return i == downloadModel.box_type && str.equals(downloadModel.privateid);
    }

    /* loaded from: classes3.dex */
    private class BookDownloadReceiver extends BroadcastReceiver {
        private BookDownloadReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MLog.d(DownloadingActivity.this.TAG, "onReceive");
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(Constant.Download.PARAMS_KEY_MOVIE_ID);
            int intExtra = intent.getIntExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, 1);
            if (stringExtra != null) {
                if (Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE.equals(action)) {
                    DownloadingActivity.this.onDownloadSuccess(stringExtra, intExtra);
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_FAILURE.equals(action)) {
                    DownloadingActivity.this.onDownloadFailure(stringExtra, intent.getStringExtra(Constant.Download.PARAMS_KEY_REASON), intExtra);
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS.equals(action)) {
                    DownloadingActivity.this.onDownloadProgress(stringExtra, intExtra, intent.getIntExtra(Constant.Download.PARAMS_KEY_PROGRESS, 0), intent.getLongExtra(Constant.Download.PARAMS_KEY_SIZE, 0L));
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_PAUSED.equals(action)) {
                    DownloadingActivity.this.onDownloadPaused(stringExtra, intExtra);
                } else if (Constant.ACTION.DOWNLOAD_MOVIE_READY.equals(action)) {
                    DownloadingActivity.this.onDownloadReady(stringExtra, intExtra);
                } else {
                    DownloadingActivity.this.onDownloadStarted(stringExtra, intExtra);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllStatus() {
        for (DownloadModel downloadModel : this.list) {
            if (downloadModel.statue == 1) {
                this.mStart.setTag(1);
                this.mStart.setText("Suspend All");
                return;
            }
        }
        this.mStart.setTag(0);
        this.mStart.setText("Start All");
    }

    private void setTotalSizeSpeed() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (DownloadModel downloadModel : this.list) {
            if (downloadModel.statue == 1 || downloadModel.statue == 0) {
                j += downloadModel.speed;
                j3 += getFileSize(downloadModel.size);
                j2 += downloadModel.fileLength;
            }
        }
        if (j == 0) {
            this.tvSize.setText("");
            return;
        }
        SpanUtils.with(this.tvSize).append(FileUtils.byte2FitMemorySize(j) + "/S").setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.color_main_blue)).append(String.format(" · %s · %s", FileUtils.byte2FitMemorySize(j2) + "/" + FileUtils.byte2FitMemorySize(j3), TimeUtils.getTimeFull((j3 - j2) / j))).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(this, R.color.white_70alpha)).create();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadProgress(String str, int i, int i2, long j) {
        synchronized ("") {
            if (!TextUtils.isEmpty(str)) {
                int i3 = 0;
                while (true) {
                    if (i3 >= this.list.size()) {
                        break;
                    }
                    DownloadModel downloadModel = this.list.get(i3);
                    if (getChoose(str, i, this.list.get(i3))) {
                        long j2 = j - downloadModel.fileLength;
                        if (j2 < 0) {
                            j2 = 0;
                        }
                        downloadModel.progress = i2;
                        downloadModel.fileLength = j;
                        downloadModel.speed = j2;
                        this.mAdapter.notifyItemChanged(i3);
                        if (System.currentTimeMillis() - this.lastShowSpeedTime >= 1000) {
                            this.lastShowSpeedTime = System.currentTimeMillis();
                            setTotalSizeSpeed();
                        }
                    } else {
                        i3++;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadStarted(String str, int i) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgressStart: " + str);
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (getChoose(str, i, this.list.get(i2))) {
                    this.list.get(i2).speed = 0L;
                    this.list.get(i2).statue = 1;
                    this.mAdapter.notifyItemChanged(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
        updateAllStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadPaused(String str, int i) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgressPaused: " + str);
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (getChoose(str, i, this.list.get(i2))) {
                    this.list.get(i2).speed = 0L;
                    this.list.get(i2).statue = 3;
                    this.mAdapter.notifyItemChanged(i2);
                    setTotalSizeSpeed();
                    break;
                } else {
                    i2++;
                }
            }
        }
        updateAllStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadReady(String str, int i) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgressReady: " + str);
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (getChoose(str, i, this.list.get(i2))) {
                    this.list.get(i2).speed = 0L;
                    this.list.get(i2).statue = 0;
                    this.mAdapter.notifyItemChanged(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
        updateAllStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadSuccess(String str, int i) {
        String str2 = this.TAG;
        MLog.d(str2, "onDownloadProgressSuccess: " + str);
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (getChoose(str, i, this.list.get(i2))) {
                    this.list.remove(i2);
                    this.mAdapter.notifyItemChanged(i2);
                    setTotalSizeSpeed();
                    break;
                } else {
                    i2++;
                }
            }
        }
        updateAllStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadFailure(String str, String str2, int i) {
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.list.size()) {
                    break;
                } else if (getChoose(str, i, this.list.get(i2))) {
                    this.list.get(i2).speed = 0L;
                    this.list.get(i2).statue = 4;
                    this.list.get(i2).setFailReason(str2);
                    this.mAdapter.notifyItemChanged(i2);
                    setTotalSizeSpeed();
                    break;
                } else {
                    i2++;
                }
            }
        }
        updateAllStatus();
    }

    /* loaded from: classes3.dex */
    public class DownloadAdapter extends BaseAdapter<DownloadModel> {
        public DownloadAdapter(List<DownloadModel> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            BaseViewHolder item1ViewHolder;
            if (i == 0) {
                item1ViewHolder = new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_downloading_item, viewGroup, false), onItemClickListener);
            } else if (i == 1) {
                item1ViewHolder = new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_downloading_item, viewGroup, false), onItemClickListener);
            } else if (i != 2) {
                return null;
            } else {
                item1ViewHolder = new Item2ViewHolder(layoutInflater.inflate(R.layout.layout_downloading_item, viewGroup, false), onItemClickListener);
            }
            return item1ViewHolder;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            DownloadModel model = getModel(i);
            if (model.box_type == 1 || model.box_type == 0) {
                setMovieType(model, (Item1ViewHolder) baseViewHolder);
            }
            if (model.box_type == 2) {
                setTvType(model, (Item2ViewHolder) baseViewHolder);
            }
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return super.getType(i);
            }
            return getModel(i).box_type;
        }

        private void setMovieType(DownloadModel downloadModel, Item1ViewHolder item1ViewHolder) {
            if (DownloadingActivity.this.activity != null && !DownloadingActivity.this.activity.isFinishing()) {
                GlideUtils.load(DownloadingActivity.this.context, downloadModel.poster, item1ViewHolder.mCover, (int) R.drawable.ic_default);
            }
            if (downloadModel.statue == 3) {
                item1ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item1ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item1ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                item1ViewHolder.mSpeed.setText("Suspended");
            } else if (downloadModel.statue == 4) {
                item1ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                item1ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                item1ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                if (downloadModel.getFailReason() != null) {
                    TextView textView = item1ViewHolder.mSpeed;
                    textView.setText("Download failed:" + downloadModel.getFailReason());
                } else {
                    item1ViewHolder.mSpeed.setText("Download failed");
                }
            } else if (downloadModel.statue == 0) {
                item1ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item1ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_main_blue));
                item1ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                item1ViewHolder.mSpeed.setText("Readying...");
            } else {
                item1ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item1ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_main_blue));
                item1ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                TextView textView2 = item1ViewHolder.mSpeed;
                textView2.setText(FileUtils.byte2FitMemorySize(downloadModel.speed) + "/S");
            }
            if (DownloadingActivity.this.Flag == 1) {
                item1ViewHolder.mChecked.setVisibility(0);
            } else {
                item1ViewHolder.mChecked.setVisibility(8);
            }
            if (downloadModel.isChecked == 0) {
                item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox);
            } else {
                item1ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox_checked);
            }
            item1ViewHolder.tvSeasonEpisode.setVisibility(4);
            if (downloadModel.speed != 0) {
                TextView textView3 = item1ViewHolder.mSize;
                textView3.setText(String.format("%s · %s", FileUtils.byte2FitMemorySize(downloadModel.fileLength) + "/" + downloadModel.size, TimeUtils.getTimeFull((getFileSize(downloadModel.size) - downloadModel.fileLength) / downloadModel.speed)));
            } else {
                TextView textView4 = item1ViewHolder.mSize;
                textView4.setText(FileUtils.byte2FitMemorySize(downloadModel.fileLength) + "/" + downloadModel.size);
            }
            item1ViewHolder.mName.setText(downloadModel.title);
            item1ViewHolder.mProgress.setProgress(downloadModel.progress);
            item1ViewHolder.mQuality.setImageResource(CommonUtils.getMovieQualityTag(downloadModel.quality));
            item1ViewHolder.mShaow.setVisibility(8);
            item1ViewHolder.mSeason.setVisibility(8);
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

        private void setTvType(DownloadModel downloadModel, Item2ViewHolder item2ViewHolder) {
            if (DownloadingActivity.this.activity != null && !DownloadingActivity.this.activity.isFinishing()) {
                GlideUtils.load(DownloadingActivity.this.context, downloadModel.seasonthumbs, item2ViewHolder.mCover, (int) R.drawable.ic_default);
            }
            if (downloadModel.statue == 3) {
                item2ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item2ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item2ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                item2ViewHolder.mSpeed.setText("Suspended");
            } else if (downloadModel.statue == 4) {
                item2ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                item2ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                item2ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.error_text));
                item2ViewHolder.mSpeed.setText("FAIL");
            } else if (downloadModel.statue == 0) {
                item2ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item2ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_main_blue));
                item2ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item2ViewHolder.mSpeed.setText("Readying...");
            } else {
                item2ViewHolder.mSize.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white_70alpha));
                item2ViewHolder.mSpeed.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.color_main_blue));
                item2ViewHolder.mName.setTextColor(DownloadingActivity.this.getResources().getColor(R.color.white));
                TextView textView = item2ViewHolder.mSpeed;
                textView.setText(FileUtils.byte2FitMemorySize(downloadModel.speed) + "/S");
            }
            if (DownloadingActivity.this.Flag == 1) {
                item2ViewHolder.mChecked.setVisibility(0);
            } else {
                item2ViewHolder.mChecked.setVisibility(8);
            }
            if (downloadModel.isChecked == 0) {
                item2ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox);
            } else {
                item2ViewHolder.mChecked.setImageResource(R.drawable.ic_checkbox_checked);
            }
            if (downloadModel.speed != 0) {
                TextView textView2 = item2ViewHolder.mSize;
                textView2.setText(String.format("%s · %s", FileUtils.byte2FitMemorySize(downloadModel.fileLength) + "/" + downloadModel.size, TimeUtils.getTimeFull((getFileSize(downloadModel.size) - downloadModel.fileLength) / downloadModel.speed)));
            } else {
                TextView textView3 = item2ViewHolder.mSize;
                textView3.setText(FileUtils.byte2FitMemorySize(downloadModel.fileLength) + "/" + downloadModel.size);
            }
            item2ViewHolder.tvSeasonEpisode.setVisibility(0);
            item2ViewHolder.tvSeasonEpisode.setText(String.format(" (S%s/E%s)", Integer.valueOf(downloadModel.season), Integer.valueOf(downloadModel.episode)));
            SpanUtils.with(item2ViewHolder.mName).append(downloadModel.title).setForegroundColor(DownloadingActivity.this.getResources().getColor(R.color.white)).create();
            item2ViewHolder.mProgress.setProgress(downloadModel.progress);
            item2ViewHolder.mQuality.setImageResource(CommonUtils.getMovieQualityTag(downloadModel.quality));
            item2ViewHolder.mShaow.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Item1ViewHolder extends BaseViewHolder {
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
        @BindView(R.id.move_download_seasons)
        TextView mSeason;
        @BindView(R.id.move_download_shadow)
        FrameLayout mShaow;
        @BindView(R.id.move_download_size)
        TextView mSize;
        @BindView(R.id.move_download_speed)
        TextView mSpeed;
        @BindView(R.id.tvSeasonEpisode)
        TextView tvSeasonEpisode;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.Item1ViewHolder.1
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
        @BindView(R.id.move_download_seasons)
        TextView mSeason;
        @BindView(R.id.move_download_shadow)
        FrameLayout mShaow;
        @BindView(R.id.move_download_size)
        TextView mSize;
        @BindView(R.id.move_download_speed)
        TextView mSpeed;
        @BindView(R.id.tvSeasonEpisode)
        TextView tvSeasonEpisode;

        Item2ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.download.DownloadingActivity.Item2ViewHolder.1
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
    }
}
