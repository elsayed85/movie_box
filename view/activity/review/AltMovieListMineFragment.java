package com.movieboxpro.android.view.activity.review;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import io.reactivex.Observable;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class AltMovieListMineFragment extends BaseListFragment<MovieListModel.MovieListItem, MovieListModel> {
    private String mType;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_movie_list_image_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedLogin() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isVerticalLayout() {
        return false;
    }

    public static AltMovieListMineFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        AltMovieListMineFragment altMovieListMineFragment = new AltMovieListMineFragment();
        altMovieListMineFragment.setArguments(bundle);
        return altMovieListMineFragment;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        this.mClass = MovieListModel.MovieListItem.class;
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, App.isLogin() ? App.getUserData().uid_v2 : "", this.mType, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltMovieListMineFragment$0hsGh1BiK-S5RWoF07PwmqTd64Y
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AltMovieListMineFragment.this.lambda$onItemClick$0$AltMovieListMineFragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$AltMovieListMineFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this.mAdapter.getItem(i);
        if (movieListItem != null) {
            EventBus.getDefault().post(new MovieListSelectedEvent(movieListItem));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(RefreshMovieListEvent refreshMovieListEvent) {
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent loginEvent) {
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSignOut(LogoutEvent logoutEvent) {
        startRefresh();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 6 : 3;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.mType = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder baseViewHolder, MovieListModel.MovieListItem movieListItem) {
        baseViewHolder.setText(R.id.tv_num, String.valueOf(movieListItem.getCount()));
        List<String> imgArr = movieListItem.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            GlideUtils.loadWithCorner(getContext(), imgArr.get(0), (ImageView) baseViewHolder.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 4.0f));
        }
        baseViewHolder.setText(R.id.tv_name, movieListItem.getName());
    }
}
