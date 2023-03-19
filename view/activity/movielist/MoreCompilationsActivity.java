package com.movieboxpro.android.view.activity.movielist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.view.fragment.movielist.CompilationsListActivity;
import io.reactivex.Observable;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class MoreCompilationsActivity extends BaseListActivity<MovieListModel.MovieListItem, MovieListModel> {
    private boolean choose;
    private String mType;
    private String uid;

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int initItemLayout() {
        return R.layout.adapter_more_compilations_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isVerticalLayout() {
        return false;
    }

    public MoreCompilationsActivity() {
        this.uid = App.isLogin() ? App.getUserData().uid_v2 : "";
    }

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, MoreCompilationsActivity.class);
        intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(context, MoreCompilationsActivity.class);
        intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, str);
        intent.putExtra("title", str2);
        intent.putExtra("choose", z);
        context.startActivity(intent);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, this.uid, this.mType, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$MoreCompilationsActivity$P68ziv_ThP15gIdvXa-5phCVFcc
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoreCompilationsActivity.this.lambda$onItemClick$0$MoreCompilationsActivity(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$MoreCompilationsActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this.mAdapter.getItem(i);
        if (movieListItem != null) {
            if (this.choose) {
                CompilationsListActivity.Companion.start(this, movieListItem.getId(), movieListItem.getName(), false, true);
            } else {
                CompilationsListActivity.Companion.start(this, movieListItem.getId(), movieListItem.getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new GridInsetDecoration(16, 0, true));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        this.mType = intent.getStringExtra(IjkMediaMeta.IJKM_KEY_TYPE);
        this.mTvTitle.setText(getIntent().getStringExtra("title"));
        this.mClass = MovieListModel.MovieListItem.class;
        this.choose = getIntent().getBooleanExtra("choose", false);
        EventUtils.event("Movielist列表");
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 4 : 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder baseViewHolder, MovieListModel.MovieListItem movieListItem) {
        if (movieListItem != null) {
            GlideUtils.load((Activity) this, movieListItem.getCover(), (ImageView) baseViewHolder.getView(R.id.imageView));
        }
    }
}
