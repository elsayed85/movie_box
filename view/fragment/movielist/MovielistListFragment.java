package com.movieboxpro.android.view.fragment.movielist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.PlayListItemAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.view.activity.actor.ActorDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MoreCompilationsActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListMoreActivity;
import com.movieboxpro.android.view.widget.CircleIndicatorView;
import com.movieboxpro.android.view.widget.layoutmanager.ScaleLayoutManager;
import com.movieboxpro.android.view.widget.layoutmanager.ViewPagerLayoutManager;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class MovielistListFragment extends BaseListFragment<MovieListModel, String> {
    private boolean fromAdd;
    private int orientation = 0;
    private String type;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableMultiAdapter() {
        return true;
    }

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

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean openLowMemoryEmpty() {
        return true;
    }

    public static MovielistListFragment newInstance(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        bundle.putBoolean("fromAdd", z);
        MovielistListFragment movielistListFragment = new MovielistListFragment();
        movielistListFragment.setArguments(bundle);
        return movielistListFragment;
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
        if (CommonUtils.isTablet()) {
            return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, App.isLogin() ? App.getUserData().uid_v2 : "", this.type, this.mCurrentPage, 12, BuildConfig.VERSION_NAME);
        }
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, App.isLogin() ? App.getUserData().uid_v2 : "", this.type, this.mCurrentPage, 6, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$MovielistListFragment$nml72sEYJUdvfwwIrtLifKlUQ5E
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovielistListFragment.this.lambda$onItemChildClick$0$MovielistListFragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemChildClick$0$MovielistListFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel movieListModel = (MovieListModel) this.mAdapter.getItem(i);
        if (movieListModel != null) {
            String type = movieListModel.getType();
            char c = 65535;
            if (type.hashCode() == 44612368 && type.equals("compilations")) {
                c = 0;
            }
            if (c == 0) {
                MoreCompilationsActivity.start(getContext(), movieListModel.getType(), movieListModel.getName());
            } else {
                MovieListMoreActivity.start(getContext(), movieListModel.getType(), movieListModel.getName());
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
        this.fromAdd = bundle.getBoolean("fromAdd", false);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<MovieListModel, BaseViewHolder> baseQuickAdapter) {
        baseQuickAdapter.addChildClickViewIds(R.id.ll_more);
    }

    private void addLinearManager(RecyclerView recyclerView, String str) {
        String str2 = recyclerView.getTag() == null ? "" : (String) recyclerView.getTag();
        if (!str2.contains(str)) {
            recyclerView.addItemDecoration(new LinearItemDecoration(0, 8, false));
            recyclerView.setTag(str2 + str);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
    }

    private void addGridManager(RecyclerView recyclerView, int i, String str) {
        String str2 = recyclerView.getTag() == null ? "" : (String) recyclerView.getTag();
        if (!str2.contains(str)) {
            recyclerView.addItemDecoration(new GridInsetDecoration(9, 0, true));
            recyclerView.setTag(str2 + str);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<MovieListModel> list) {
        int i = 3;
        for (MovieListModel movieListModel : list) {
            if (CommonUtils.isTablet() && ("weekhot".equals(movieListModel.getType()) || "special".equals(movieListModel.getType()))) {
                MovieListModel.DoubleMovie doubleMovie = null;
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                Iterator it = new ArrayList(movieListModel.getList()).iterator();
                while (it.hasNext()) {
                    MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) it.next();
                    if (i2 % 2 == 0) {
                        doubleMovie = new MovieListModel.DoubleMovie();
                        doubleMovie.setFirst(movieListItem);
                        MovieListModel.MovieListItem movieListItem2 = new MovieListModel.MovieListItem();
                        movieListItem2.setDoubleMovie(doubleMovie);
                        arrayList.add(movieListItem2);
                    } else {
                        doubleMovie.setSecond(movieListItem);
                    }
                    i2++;
                }
                movieListModel.setList(arrayList);
            }
            if ("special".equals(movieListModel.getType())) {
                movieListModel.setViewType(2);
            } else {
                movieListModel.setViewType(i);
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public int initItemType(MovieListModel movieListModel) {
        return movieListModel.getViewType();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void registerItemType(BaseMultiTypeDelegate<MovieListModel> baseMultiTypeDelegate) {
        baseMultiTypeDelegate.addItemType(1, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(2, R.layout.adapter_movie_list2_item);
        baseMultiTypeDelegate.addItemType(3, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(4, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(5, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(6, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(7, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(8, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(9, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(10, R.layout.adapter_movie_list_item);
        baseMultiTypeDelegate.addItemType(11, R.layout.adapter_movie_list_item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(final BaseViewHolder baseViewHolder, final MovieListModel movieListModel) {
        char c;
        int i;
        View view = baseViewHolder.getView(R.id.ll_more);
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_title);
        RelativeLayout relativeLayout = (RelativeLayout) baseViewHolder.getView(R.id.rlTitle);
        if (movieListModel.getIsmore() == 1) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
        if ("collection".equals(movieListModel.getType()) || "collection2".equals(movieListModel.getType())) {
            relativeLayout.setVisibility(8);
        } else {
            relativeLayout.setVisibility(0);
        }
        textView.setText(movieListModel.getName());
        final RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.recyclerView);
        List<MovieListModel.MovieListItem> list = movieListModel.getList();
        String type = movieListModel.getType();
        switch (type.hashCode()) {
            case -2008465223:
                if (type.equals("special")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1741312354:
                if (type.equals("collection")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1338762447:
                if (type.equals("dayhot")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 108960:
                if (type.equals("new")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 44612368:
                if (type.equals("compilations")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 92645877:
                if (type.equals("actor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1226866649:
                if (type.equals("weekhot")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1853891924:
                if (type.equals("collection2")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                addLinearManager(recyclerView, movieListModel.getType());
                i = 2;
                break;
            case 1:
                addLinearManager(recyclerView, movieListModel.getType());
                i = 3;
                break;
            case 2:
                if (CommonUtils.isTablet()) {
                    addGridManager(recyclerView, 3, movieListModel.getType());
                } else {
                    addGridManager(recyclerView, 2, movieListModel.getType());
                }
                i = 4;
                break;
            case 3:
                addLinearManager(recyclerView, movieListModel.getType());
                i = 5;
                break;
            case 4:
            case 5:
                if (CommonUtils.isTablet()) {
                    addGridManager(recyclerView, 6, movieListModel.getType());
                } else {
                    addGridManager(recyclerView, 3, movieListModel.getType());
                }
                i = 6;
                break;
            case 6:
                ScaleLayoutManager scaleLayoutManager = new ScaleLayoutManager(getContext(), 0, 0);
                scaleLayoutManager.setMinScale(0.95f);
                recyclerView.setLayoutManager(scaleLayoutManager);
                if (!"weekhot".equals(recyclerView.getTag())) {
                    recyclerView.setTag("weekhot");
                    new PagerSnapHelper().attachToRecyclerView(recyclerView);
                }
                i = 7;
                break;
            case 7:
                String str = recyclerView.getTag() == null ? "" : (String) recyclerView.getTag();
                if (!str.contains("special")) {
                    recyclerView.addItemDecoration(new LinearItemDecoration(0, 8, false));
                    recyclerView.setTag(str + "special");
                }
                recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.fragment.movielist.MovielistListFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        final CircleIndicatorView circleIndicatorView = (CircleIndicatorView) baseViewHolder.getView(R.id.indicatorView);
                        circleIndicatorView.setCircleCount(movieListModel.getList().size());
                        ScaleLayoutManager scaleLayoutManager2 = new ScaleLayoutManager(MovielistListFragment.this.getContext(), 0, 0);
                        scaleLayoutManager2.setMinScale(1.0f);
                        scaleLayoutManager2.setOnPageChangeListener(new ViewPagerLayoutManager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.fragment.movielist.MovielistListFragment.1.1
                            @Override // com.movieboxpro.android.view.widget.layoutmanager.ViewPagerLayoutManager.OnPageChangeListener
                            public void onPageScrollStateChanged(int i2) {
                            }

                            @Override // com.movieboxpro.android.view.widget.layoutmanager.ViewPagerLayoutManager.OnPageChangeListener
                            public void onPageSelected(int i2) {
                                circleIndicatorView.setSelect(i2);
                            }
                        });
                        recyclerView.setLayoutManager(scaleLayoutManager2);
                    }
                });
                i = 8;
                break;
            default:
                addLinearManager(recyclerView, movieListModel.getType());
                i = 1;
                break;
        }
        for (MovieListModel.MovieListItem movieListItem : list) {
            movieListItem.setItemType(i);
        }
        final PlayListItemAdapter playListItemAdapter = new PlayListItemAdapter(list);
        playListItemAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$MovielistListFragment$88iwOhfItRG8GanULou6LapibBk
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i2) {
                MovielistListFragment.this.lambda$initHolder$1$MovielistListFragment(playListItemAdapter, baseQuickAdapter, view2, i2);
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(playListItemAdapter);
        playListItemAdapter.addChildClickViewIds(R.id.container1, R.id.container2);
        playListItemAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$MovielistListFragment$LI8pJAHuMQl8FwplCp9nOeP0R4w
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view2, int i2) {
                MovielistListFragment.this.lambda$initHolder$2$MovielistListFragment(playListItemAdapter, baseQuickAdapter, view2, i2);
            }
        });
    }

    public /* synthetic */ void lambda$initHolder$1$MovielistListFragment(PlayListItemAdapter playListItemAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem item = playListItemAdapter.getItem(i);
        if (item != null) {
            switch (item.getItemType()) {
                case 1:
                case 4:
                case 5:
                case 7:
                case 8:
                    MovieListDetailActivity.start(getContext(), item.getLid(), item.getUsername(), (item.getImgArr() == null || item.getImgArr().size() < 1) ? "" : item.getImgArr().get(0), this.fromAdd);
                    return;
                case 2:
                case 6:
                    CompilationsListActivity.Companion.start(getContext(), item.getId(), item.getName(), this.fromAdd, false);
                    return;
                case 3:
                    ActorDetailActivity.Companion.start(getContext(), item.getActor_id());
                    return;
                default:
                    return;
            }
        }
    }

    public /* synthetic */ void lambda$initHolder$2$MovielistListFragment(PlayListItemAdapter playListItemAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MovieListModel.MovieListItem item = playListItemAdapter.getItem(i);
        if (item != null) {
            String str = "";
            switch (view.getId()) {
                case R.id.container1 /* 2131296553 */:
                    MovieListModel.MovieListItem first = item.getDoubleMovie().getFirst();
                    if (first != null) {
                        if (first.getImgArr() != null && first.getImgArr().size() >= 1) {
                            str = first.getImgArr().get(0);
                        }
                        MovieListDetailActivity.start(getContext(), first.getLid(), first.getUsername(), str, this.fromAdd);
                        return;
                    }
                    return;
                case R.id.container2 /* 2131296554 */:
                    MovieListModel.MovieListItem second = item.getDoubleMovie().getSecond();
                    if (second != null) {
                        if (second.getImgArr() != null && second.getImgArr().size() >= 1) {
                            str = second.getImgArr().get(0);
                        }
                        MovieListDetailActivity.start(getContext(), second.getLid(), second.getUsername(), str, this.fromAdd);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
