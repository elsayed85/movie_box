package com.movieboxpro.android.view.fragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class MovieListList2Fragment extends BaseListFragment<MovieListModel.MovieListItem, MovieListModel> {
    private boolean fromAdd;
    private String mType;
    private boolean showPublic = false;
    private String uid;

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

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean openLowMemoryEmpty() {
        return true;
    }

    public static MovieListList2Fragment newInstance(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        bundle.putBoolean("fromAdd", z);
        MovieListList2Fragment movieListList2Fragment = new MovieListList2Fragment();
        movieListList2Fragment.setArguments(bundle);
        return movieListList2Fragment;
    }

    public static MovieListList2Fragment newInstance(String str, String str2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        bundle.putString("uid", str2);
        bundle.putBoolean("showPublic", z);
        MovieListList2Fragment movieListList2Fragment = new MovieListList2Fragment();
        movieListList2Fragment.setArguments(bundle);
        return movieListList2Fragment;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        this.mClass = MovieListModel.MovieListItem.class;
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, this.uid, this.mType, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$MovieListList2Fragment$-Sdk0S0F6FV2mT3hL1i-SlKqgPY
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieListList2Fragment.this.lambda$onItemClick$0$MovieListList2Fragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$MovieListList2Fragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this.mAdapter.getItem(i);
        if (movieListItem != null) {
            MovieListDetailActivity.start(getContext(), movieListItem.getLid(), movieListItem.getUsername(), (movieListItem.getImgArr() == null || movieListItem.getImgArr().size() < 1) ? "" : movieListItem.getImgArr().get(0), this.fromAdd);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<MovieListModel.MovieListItem> list) {
        if (this.showPublic) {
            ArrayList arrayList = new ArrayList();
            for (MovieListModel.MovieListItem movieListItem : list) {
                if (movieListItem.getStatus() == 1) {
                    arrayList.add(movieListItem);
                }
            }
            list.clear();
            list.addAll(arrayList);
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
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new GridInsetDecoration(9, 0, true));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 2 : 1;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.mType = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE);
        String string = bundle.getString("uid");
        this.uid = string;
        if (TextUtils.isEmpty(string)) {
            this.uid = App.isLogin() ? App.getUserData().uid_v2 : "";
        }
        this.fromAdd = bundle.getBoolean("fromAdd", false);
        this.showPublic = bundle.getBoolean("showPublic", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder baseViewHolder, MovieListModel.MovieListItem movieListItem) {
        SpanUtils.with((TextView) baseViewHolder.getView(R.id.tv_name)).append(String.valueOf(movieListItem.getName())).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
        SpanUtils.with((TextView) baseViewHolder.getView(R.id.tv_num)).append(String.valueOf(movieListItem.getCount())).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
        List<String> imgArr = movieListItem.getImgArr();
        if (imgArr == null || imgArr.size() <= 0) {
            return;
        }
        GlideUtils.loadPost(getContext(), imgArr.get(0), (ImageView) baseViewHolder.getView(R.id.imageView), 4);
    }
}
