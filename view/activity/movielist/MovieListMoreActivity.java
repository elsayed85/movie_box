package com.movieboxpro.android.view.activity.movielist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.utils.SpanUtils;
import io.reactivex.Observable;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class MovieListMoreActivity extends BaseListActivity<MovieListModel.MovieListItem, MovieListModel> {
    private boolean choose;
    private String mType;
    private int orientation;
    private String uid;

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int initItemLayout() {
        return R.layout.adapter_movie_list_image_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isVerticalLayout() {
        return false;
    }

    public MovieListMoreActivity() {
        this.uid = App.isLogin() ? App.getUserData().uid_v2 : "";
        this.orientation = 0;
    }

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, MovieListMoreActivity.class);
        intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(context, MovieListMoreActivity.class);
        intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, str);
        intent.putExtra("title", str2);
        intent.putExtra("choose", z);
        context.startActivity(intent);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        this.mClass = MovieListModel.MovieListItem.class;
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, this.uid, this.mType, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MovieListMoreActivity$LFy2EotPGTeCQbd4B8lDKtcmB90
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieListMoreActivity.this.lambda$onItemClick$0$MovieListMoreActivity(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$MovieListMoreActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this.mAdapter.getItem(i);
        if (movieListItem != null) {
            String str = (movieListItem.getImgArr() == null || movieListItem.getImgArr().size() < 1) ? "" : movieListItem.getImgArr().get(0);
            if (this.choose) {
                EventBus.getDefault().post(new MovieListSelectedEvent(movieListItem));
                finish();
                return;
            }
            MovieListDetailActivity.start(this, movieListItem.getLid(), movieListItem.getUsername(), str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new GridInsetDecoration(9, 0, true));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        this.mType = intent.getStringExtra(IjkMediaMeta.IJKM_KEY_TYPE);
        this.mTvTitle.setText(getIntent().getStringExtra("title"));
        this.choose = getIntent().getBooleanExtra("choose", false);
        EventUtils.event("Movielist列表");
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder baseViewHolder, MovieListModel.MovieListItem movieListItem) {
        SpanUtils.with((TextView) baseViewHolder.getView(R.id.tv_name)).append(String.valueOf(movieListItem.getName())).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
        List<String> imgArr = movieListItem.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            GlideUtils.loadPost(this, imgArr.get(0), (ImageView) baseViewHolder.getView(R.id.imageView), 4);
        }
        SpanUtils.with((TextView) baseViewHolder.getView(R.id.tv_num)).append(String.valueOf(movieListItem.getCount())).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
    }
}
