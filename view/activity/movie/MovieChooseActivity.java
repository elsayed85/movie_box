package com.movieboxpro.android.view.activity.movie;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.dl7.player.model.SRTModel;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.images.WebImage;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.AppManager;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.presenter.ChromeCastPresenter;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.download.DownloadingActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes3.dex */
public class MovieChooseActivity extends BaseActivity {
    public static final String CHOOSEMOVIE = "CHOOSE_MOVIE";
    private MyLinearLayoutManager layoutManager;
    private ChooseAdapter mAdapter;
    @BindView(R.id.choose_backround)
    RelativeLayout mBackRound;
    @BindView(R.id.choose_close)
    TextView mClose;
    @BindView(R.id.choose_download)
    TextView mDowload;
    @BindView(R.id.recycler_normal)
    RecyclerView mRecycler;
    @BindView(R.id.choose_size)
    TextView mSize;
    PlayerStrategy player;
    public Srt srtLists;
    public List<BaseMediaModel.DownloadFile> list = new ArrayList();
    OnItemClickListener onItemClickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            if (i < 0 || i > MovieChooseActivity.this.mAdapter.getItemCount() - 1) {
                return;
            }
            BaseMediaModel.DownloadFile model = MovieChooseActivity.this.mAdapter.getModel(i);
            if (!TextUtils.isEmpty(model.path)) {
                CastSession currentCastSession = CastContext.getSharedInstance(MovieChooseActivity.this.context).getSessionManager().getCurrentCastSession();
                if (currentCastSession != null && currentCastSession.isConnected()) {
                    MovieChooseActivity.this.get_Srt(model);
                    return;
                } else {
                    MovieChooseActivity.this.shouldLoad(i);
                    return;
                }
            }
            MovieChooseActivity.this.finish();
            if (App.isLogin()) {
                MovieChooseActivity.this.route(VipActivity.class);
            } else {
                MovieChooseActivity.this.route(Login2Activity.class);
            }
        }
    };
    public LinkedHashMap<String, List<SRTModel.subTitles>> srtHash = new LinkedHashMap<>();

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mTheme = (ImageView) Utils.findRequiredViewAsType(view, R.id.layout_choose_theme, "field 'mTheme'", ImageView.class);
            item1ViewHolder.mFormat = (ImageView) Utils.findRequiredViewAsType(view, R.id.layout_choose_format, "field 'mFormat'", ImageView.class);
            item1ViewHolder.mSize = (TextView) Utils.findRequiredViewAsType(view, R.id.layout_choose_size, "field 'mSize'", TextView.class);
            item1ViewHolder.mTime = (TextView) Utils.findRequiredViewAsType(view, R.id.layout_choose_time, "field 'mTime'", TextView.class);
            item1ViewHolder.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.layout_choose_name, "field 'mName'", TextView.class);
            item1ViewHolder.mDownlaod = (ImageView) Utils.findRequiredViewAsType(view, R.id.layout_choose_download, "field 'mDownlaod'", ImageView.class);
            item1ViewHolder.mSelect = (TextView) Utils.findRequiredViewAsType(view, R.id.layout_choose_select, "field 'mSelect'", TextView.class);
            item1ViewHolder.mVip = (ImageView) Utils.findRequiredViewAsType(view, R.id.layout_choose_vip, "field 'mVip'", ImageView.class);
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
            item1ViewHolder.mSize = null;
            item1ViewHolder.mTime = null;
            item1ViewHolder.mName = null;
            item1ViewHolder.mDownlaod = null;
            item1ViewHolder.mSelect = null;
            item1ViewHolder.mVip = null;
        }
    }

    @OnClick({R.id.choose_download, R.id.choose_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose_close /* 2131296521 */:
                finish();
                return;
            case R.id.choose_download /* 2131296522 */:
                route(DownloadingActivity.class);
                return;
            default:
                return;
        }
    }

    public void shouldLoad(int i) {
        if (this.player.getDownload() == null) {
            if (this.player.getInstace().saveInDao(i, this.activity)) {
                donwload(this.player.getId());
                return;
            }
            return;
        }
        showToast("Already Download");
    }

    public void get_Srt(final BaseMediaModel.DownloadFile downloadFile) {
        showLoading();
        this.player.getInstace().getSrt().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.2
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MovieChooseActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    MovieChooseActivity.this.srtLists = (Srt) jSONObject2.toJavaObject((Class<Object>) Srt.class);
                }
                MovieChooseActivity.this.getChromcast(downloadFile);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                MovieChooseActivity.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                MovieChooseActivity.this.hideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getChromcast(BaseMediaModel.DownloadFile downloadFile) {
        MediaMetadata mediaMetadata = new MediaMetadata(1);
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, ((BaseMediaModel) this.player.getInstace()).title);
        mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, ((BaseMediaModel) this.player.getInstace()).description);
        mediaMetadata.addImage(new WebImage(Uri.parse(((BaseMediaModel) this.player.getInstace()).poster)));
        mediaMetadata.addImage(new WebImage(Uri.parse(((BaseMediaModel) this.player.getInstace()).poster)));
        ArrayList arrayList = new ArrayList();
        if (this.srtLists != null) {
            arrayList.add(ChromeCastPresenter.buildTrack(1L, "text", MediaTrack.ROLE_SUBTITLE, "https://www.movieboxpro.app/api/srttovtt/index?srt_url=" + HttpUtils.toURLEncoded(this.srtLists.file_path), this.srtLists.language, this.srtLists.lang));
        }
        MediaInfo build = new MediaInfo.Builder(downloadFile.path).setStreamType(1).setContentType("videos/mp4").setMetadata(mediaMetadata).setMediaTracks(arrayList).build();
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
        remoteMediaClient.queueLoad(new MediaQueueItem[]{new MediaQueueItem.Builder(build).setAutoplay(true).setPreloadTime(3.0d).build()}, 0, 0, null);
        remoteMediaClient.setActiveMediaTracks(new long[]{0, 1}).setResultCallback(new ResultCallback() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.3
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(Result result) {
                if (result.getStatus().isSuccess()) {
                    return;
                }
                String str = MovieChooseActivity.this.TAG;
                Log.e(str, "Failed with status code:" + result.getStatus().getStatusCode() + "###" + result.getStatus().getResolution());
            }
        });
        AppManager.finish(MovieChooseActivity.class);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_choosemovie, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            this.mAdapter = new ChooseAdapter(this.list);
            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
            this.layoutManager = myLinearLayoutManager;
            this.mRecycler.setLayoutManager(myLinearLayoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onItemClickListener);
        }
        TextView textView = this.mSize;
        StringBuilder sb = new StringBuilder();
        sb.append(FileUtils.getAvailMemory(this.context));
        sb.append(" Free");
        textView.setText(sb);
        this.player = (PlayerStrategy) getSerializable(CHOOSEMOVIE, new PlayerStrategy());
        getPath();
    }

    private void getPath() {
        showLoading();
        this.player.getInstace().getUrl().subscribeOn(Schedulers.io()).map(new Function<String, BaseMediaModel>() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.5
            @Override // io.reactivex.functions.Function
            public MovieDetail apply(String str) throws Exception {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    return (MovieDetail) jSONObject.getJSONObject("data").toJavaObject((Class<Object>) MovieDetail.class);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseMediaModel>() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.4
            @Override // io.reactivex.Observer
            public void onNext(BaseMediaModel baseMediaModel) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MovieChooseActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                MovieChooseActivity.this.showToast("NETWORK ERROR");
                MovieChooseActivity.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                MovieChooseActivity.this.hideLoading();
            }
        });
    }

    public void donwload(String str) {
        this.service.Movie_download(API.BASE_URL, "Movie_download", App.isLogin() ? App.getUserData().uid_v2 : "", str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movie.MovieChooseActivity.6
            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                MovieChooseActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }

    /* loaded from: classes3.dex */
    public class ChooseAdapter extends BaseAdapter<BaseMediaModel.DownloadFile> {
        public ChooseAdapter(List<BaseMediaModel.DownloadFile> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_choose_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            BaseMediaModel.DownloadFile model = getModel(i);
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
            if (MovieChooseActivity.this.activity != null && !MovieChooseActivity.this.activity.isFinishing()) {
                if (model.vip_only == 1) {
                    item1ViewHolder.mVip.setVisibility(0);
                } else {
                    item1ViewHolder.mVip.setVisibility(8);
                }
                if (TextUtils.equals(model.quality, "360p")) {
                    Glide.with(MovieChooseActivity.this.activity).load(Integer.valueOf((int) R.drawable.ic_choose_sd)).into(item1ViewHolder.mTheme);
                } else if (TextUtils.equals(model.quality, "720p")) {
                    Glide.with(MovieChooseActivity.this.activity).load(Integer.valueOf((int) R.drawable.ic_choose_hd)).into(item1ViewHolder.mTheme);
                } else if (TextUtils.equals(model.quality, "1080p")) {
                    Glide.with(MovieChooseActivity.this.activity).load(Integer.valueOf((int) R.drawable.ic_choose_fullhd)).into(item1ViewHolder.mTheme);
                }
            }
            item1ViewHolder.mSize.setText(model.size);
            item1ViewHolder.mTime.setText(TimeUtils.formatTime(model.dateline * 1000));
            item1ViewHolder.mName.setText(model.filename);
            item1ViewHolder.mSelect.setText(String.format("%s Selecet", model.count));
            item1ViewHolder.mSelect.setVisibility(8);
            CastSession currentCastSession = CastContext.getSharedInstance(MovieChooseActivity.this.context).getSessionManager().getCurrentCastSession();
            if (currentCastSession != null && currentCastSession.isConnected()) {
                item1ViewHolder.mDownlaod.setVisibility(8);
            } else {
                item1ViewHolder.mDownlaod.setVisibility(0);
            }
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.layout_choose_download)
        ImageView mDownlaod;
        @BindView(R.id.layout_choose_format)
        ImageView mFormat;
        @BindView(R.id.layout_choose_name)
        TextView mName;
        @BindView(R.id.layout_choose_select)
        TextView mSelect;
        @BindView(R.id.layout_choose_size)
        TextView mSize;
        @BindView(R.id.layout_choose_theme)
        ImageView mTheme;
        @BindView(R.id.layout_choose_time)
        TextView mTime;
        @BindView(R.id.layout_choose_vip)
        ImageView mVip;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
