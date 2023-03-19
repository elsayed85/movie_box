package com.movieboxpro.android.view.activity.review;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.PlayListItemAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.view.activity.movielist.MoreCompilationsActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListMoreActivity;
import com.movieboxpro.android.view.fragment.movielist.CompilationsListActivity;
import io.reactivex.Observable;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class AltMovieListAllFragment extends BaseListFragment<MovieListModel, String> {
    private int orientation = 0;
    private String type;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_movie_list_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenRefresh() {
        return true;
    }

    public static AltMovieListAllFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        AltMovieListAllFragment altMovieListAllFragment = new AltMovieListAllFragment();
        altMovieListAllFragment.setArguments(bundle);
        return altMovieListAllFragment;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        if (getContext() != null) {
            if (CommonUtils.isTablet()) {
                this.orientation = 1;
            } else {
                this.orientation = 0;
            }
        }
        this.mClass = MovieListModel.class;
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, App.isLogin() ? App.getUserData().uid_v2 : "", this.type, this.mCurrentPage, this.mPageSize, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltMovieListAllFragment$Mqb_caCux7fBFoWnMdn-BT5PoI4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AltMovieListAllFragment.this.lambda$onItemChildClick$0$AltMovieListAllFragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemChildClick$0$AltMovieListAllFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel movieListModel = (MovieListModel) this.mAdapter.getItem(i);
        if (movieListModel != null) {
            String type = movieListModel.getType();
            char c = 65535;
            if (type.hashCode() == 44612368 && type.equals("compilations")) {
                c = 0;
            }
            if (c == 0) {
                MoreCompilationsActivity.start(getContext(), movieListModel.getType(), movieListModel.getName(), true);
            } else {
                MovieListMoreActivity.start(getContext(), movieListModel.getType(), movieListModel.getName(), true);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2) {
            this.orientation = 1;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            this.orientation = 0;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.type = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE);
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        EventUtils.event("进入Movielist");
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<MovieListModel, BaseViewHolder> baseQuickAdapter) {
        baseQuickAdapter.addChildClickViewIds(R.id.ll_more);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder baseViewHolder, MovieListModel movieListModel) {
        View view = baseViewHolder.getView(R.id.ll_more);
        int i = 1;
        if (movieListModel.getIsmore() == 1) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
        baseViewHolder.setText(R.id.tv_title, movieListModel.getName());
        RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.recyclerView);
        List<MovieListModel.MovieListItem> list = movieListModel.getList();
        String type = movieListModel.getType();
        char c = 65535;
        int hashCode = type.hashCode();
        if (hashCode != -2008465223) {
            if (hashCode != 44612368) {
                if (hashCode == 92645877 && type.equals("actor")) {
                    c = 1;
                }
            } else if (type.equals("compilations")) {
                c = 0;
            }
        } else if (type.equals("special")) {
            c = 2;
        }
        if (c == 0) {
            i = 2;
        } else if (c == 1) {
            i = 3;
        } else if (c == 2) {
            i = 4;
        }
        for (MovieListModel.MovieListItem movieListItem : list) {
            movieListItem.setItemType(i);
        }
        final PlayListItemAdapter playListItemAdapter = new PlayListItemAdapter(list);
        playListItemAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltMovieListAllFragment$iDqwNJ7Qo0d0w64RqOy8Si3jSoo
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i2) {
                AltMovieListAllFragment.this.lambda$initHolder$1$AltMovieListAllFragment(playListItemAdapter, baseQuickAdapter, view2, i2);
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setAdapter(playListItemAdapter);
    }

    public /* synthetic */ void lambda$initHolder$1$AltMovieListAllFragment(PlayListItemAdapter playListItemAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem item = playListItemAdapter.getItem(i);
        if (item != null) {
            int itemType = item.getItemType();
            if (itemType == 2) {
                CompilationsListActivity.Companion.start(getContext(), item.getId(), item.getName(), false, true);
            } else if (itemType != 3) {
                EventBus.getDefault().post(new MovieListSelectedEvent(item));
            }
        }
    }
}
