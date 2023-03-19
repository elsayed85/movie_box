package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.ActorAdapter;
import com.movieboxpro.android.adapter.MovieListItemAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.SearchEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.SearchAllFilterLiveData;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.search.SrearchResultModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.MoreSearchMovieListActivity;
import com.movieboxpro.android.view.activity.actor.ActorDetailActivity;
import com.movieboxpro.android.view.activity.actor.MoreActorsActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: SearchResultAllFragment.kt */
@Metadata(d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0010\u0018\u0000 >2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0015H\u0014J\b\u0010\u001c\u001a\u00020\bH\u0014J\b\u0010\u001d\u001a\u00020\bH\u0014J\u0012\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\"J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030$H\u0014J\u0006\u0010%\u001a\u00020\bJ\u0018\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0002H\u0014J\b\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u0002H\u0014J\u0010\u0010.\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010/\u001a\u00020\u001aH\u0016J\b\u00100\u001a\u00020\u001aH\u0016J\b\u00101\u001a\u000202H\u0014J\b\u00103\u001a\u00020\u001aH\u0014J\u0010\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u000206H\u0007J\u0016\u00107\u001a\u00020\u001a2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000209H\u0014J\b\u0010:\u001a\u00020\u001aH\u0002J\b\u0010;\u001a\u00020\u001aH\u0002J6\u0010<\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/movieboxpro/android/view/fragment/SearchResultAllFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/search/SrearchResultModel;", "", "()V", "actorDisposable", "Lio/reactivex/disposables/Disposable;", "allDefault", "", "country", "gener", "keyword", "playListDisposable", "quality", "rating", "scrollListener", "com/movieboxpro/android/view/fragment/SearchResultAllFragment$scrollListener$1", "Lcom/movieboxpro/android/view/fragment/SearchResultAllFragment$scrollListener$1;", "sort", "year", "addHeaderView", "", "Landroid/view/View;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "doSomethingWithData", "", "list", "enableEventBus", "enableMultiAdapter", "getBundle", "arguments", "Landroid/os/Bundle;", "getFilterData", "Ljava/util/HashMap;", "getServiceData", "Lio/reactivex/Observable;", "haveFilter", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "initItemType", "t", "initRecyclerView", "onDestroyView", "onFragmentResume", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onLoadComplete", "onSearch", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/SearchEvent;", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "searchActors", "searchPlayList", "setFilter", "genre", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SearchResultAllFragment extends BaseListFragment<SrearchResultModel, String> {
    public static final Companion Companion = new Companion(null);
    private Disposable actorDisposable;
    private Disposable playListDisposable;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String keyword = "";
    private String sort = "";
    private String gener = "0";
    private String year = "0";
    private String rating = "";
    private String country = "";
    private String quality = "";
    private boolean allDefault = true;
    private final SearchResultAllFragment$scrollListener$1 scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.SearchResultAllFragment$scrollListener$1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Context context;
            Context context2;
            Context context3;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                FragmentActivity activity = SearchResultAllFragment.this.getActivity();
                if (!((activity == null || activity.isDestroyed()) ? false : true) || (context = SearchResultAllFragment.this.getContext()) == null) {
                    return;
                }
                Glide.with(context).resumeRequests();
            } else if (i == 1) {
                FragmentActivity activity2 = SearchResultAllFragment.this.getActivity();
                if (!((activity2 == null || activity2.isDestroyed()) ? false : true) || (context2 = SearchResultAllFragment.this.getContext()) == null) {
                    return;
                }
                Glide.with(context2).resumeRequests();
            } else if (i != 2) {
            } else {
                FragmentActivity activity3 = SearchResultAllFragment.this.getActivity();
                if (!((activity3 == null || activity3.isDestroyed()) ? false : true) || (context3 = SearchResultAllFragment.this.getContext()) == null) {
                    return;
                }
                Glide.with(context3).pauseRequests();
            }
        }
    };

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected List<View> addHeaderView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        return null;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableMultiAdapter() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.layout_search_movie_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return Http.getService().SearchFilter(API.BASE_URL, API.Search.SEARCH4, TtmlNode.COMBINE_ALL, this.keyword, App.getUserData().uid_v2, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), this.sort, this.year, this.rating, this.quality, this.gener, this.country, PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0);
    }

    public final void setFilter(String year, String genre, String sort, String rating, String quality, String country) {
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        boolean z = true;
        this.mCurrentPage = 1;
        this.year = year;
        this.gener = genre;
        this.sort = sort;
        this.rating = rating;
        this.quality = quality;
        this.country = country;
        this.allDefault = (Intrinsics.areEqual(year, "0") && Intrinsics.areEqual(genre, "0") && Intrinsics.areEqual(sort, "") && StringsKt.isBlank(country)) ? false : false;
        startRefresh();
    }

    public final boolean haveFilter() {
        if (Intrinsics.areEqual(this.year, "0") && Intrinsics.areEqual(this.gener, "0") && Intrinsics.areEqual(this.sort, "")) {
            String str = this.country;
            if (str == null || StringsKt.isBlank(str)) {
                String str2 = this.rating;
                if (str2 == null || StringsKt.isBlank(str2)) {
                    String str3 = this.quality;
                    if (str3 == null || StringsKt.isBlank(str3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final HashMap<String, String> getFilterData() {
        return MapsKt.hashMapOf(TuplesKt.to("year", this.year), TuplesKt.to("gener", this.gener), TuplesKt.to("sort", this.sort), TuplesKt.to("rating", this.rating), TuplesKt.to("quality", this.quality), TuplesKt.to("country", this.country));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSearch(SearchEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String str = event.keyWord;
        Intrinsics.checkNotNullExpressionValue(str, "event.keyWord");
        this.keyword = str;
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        Context context = getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "CompositeSearch");
        }
        EventUtils.event("综合搜索");
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<SrearchResultModel> list) {
        if (list != null) {
            for (SrearchResultModel srearchResultModel : list) {
                srearchResultModel.setViewType(1);
            }
        }
        if (this.mCurrentPage == 1) {
            Disposable disposable = this.actorDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
                searchActors();
            }
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) || this.mCurrentPage != 1) {
            return;
        }
        Disposable disposable2 = this.playListDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            searchPlayList();
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchResultAllFragment$j9cu2WUmV-YAJb22H5fOxqpoLhI
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchResultAllFragment.m1219onItemClick$lambda1(SearchResultAllFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m1219onItemClick$lambda1(SearchResultAllFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        SrearchResultModel srearchResultModel = (SrearchResultModel) this$0.mAdapter.getItem(i);
        boolean z = false;
        if (srearchResultModel != null && srearchResultModel.box_type == 1) {
            z = true;
        }
        if (z) {
            MovieDetailActivity.start(this$0.getContext(), srearchResultModel.id, srearchResultModel.poster);
        } else {
            TvDetailActivity.start(this$0.getContext(), srearchResultModel == null ? null : srearchResultModel.id, srearchResultModel == null ? null : srearchResultModel.banner_mini, srearchResultModel != null ? srearchResultModel.poster : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public int initItemType(SrearchResultModel t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return t.getViewType();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void registerItemType(BaseMultiTypeDelegate<SrearchResultModel> multiTypeDelegate) {
        Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
        multiTypeDelegate.addItemType(1, R.layout.layout_search_movie_item);
        multiTypeDelegate.addItemType(2, R.layout.recycler_view_layout);
        multiTypeDelegate.addItemType(3, R.layout.recycler_view_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.addOnScrollListener(this.scrollListener);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mRecyclerView.removeOnScrollListener(this.scrollListener);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        String string;
        if (bundle == null || (string = bundle.getString("keyword")) == null) {
            string = "";
        }
        this.keyword = string;
        this.mClass = SrearchResultModel.class;
        this.mPageSize = 8;
        this.mPreLoadNum = 4;
        String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.SEARCH_FILTER_ALL);
        String str = string2;
        boolean z = false;
        if (!(str == null || StringsKt.isBlank(str))) {
            Object parseObject = JSONObject.parseObject(string2, HashMap.class);
            if (parseObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
            }
            HashMap hashMap = (HashMap) parseObject;
            String str2 = (String) hashMap.get("year");
            if (str2 == null) {
                str2 = "0";
            }
            this.year = str2;
            String str3 = (String) hashMap.get("gener");
            if (str3 == null) {
                str3 = "0";
            }
            this.gener = str3;
            String str4 = (String) hashMap.get("sort");
            if (str4 == null) {
                str4 = "";
            }
            this.sort = str4;
            String str5 = (String) hashMap.get("rating");
            if (str5 == null) {
                str5 = "";
            }
            this.rating = str5;
            String str6 = (String) hashMap.get("quality");
            if (str6 == null) {
                str6 = "";
            }
            this.quality = str6;
            String str7 = (String) hashMap.get("country");
            if (str7 == null) {
                str7 = "";
            }
            this.country = str7;
        }
        if (Intrinsics.areEqual(this.year, "0") && Intrinsics.areEqual(this.gener, "0") && Intrinsics.areEqual(this.sort, "")) {
            String str8 = this.country;
            if (str8 == null || StringsKt.isBlank(str8)) {
                z = true;
            }
        }
        this.allDefault = z;
        SearchAllFilterLiveData.Companion.get().setValue(Boolean.valueOf(!this.allDefault));
    }

    private final void searchPlayList() {
        Observable<R> compose = Http.getService().Search(API.BASE_URL, API.Search.SEARCH4, "playlists", this.keyword, App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", Integer.valueOf(PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0)).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Observer<List<? extends MovieListModel.MovieListItem>>() { // from class: com.movieboxpro.android.view.fragment.SearchResultAllFragment$searchPlayList$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                SearchResultAllFragment.this.playListDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(List<? extends MovieListModel.MovieListItem> t) {
                BaseQuickAdapter baseQuickAdapter;
                BaseQuickAdapter baseQuickAdapter2;
                BaseQuickAdapter baseQuickAdapter3;
                Intrinsics.checkNotNullParameter(t, "t");
                if (t.isEmpty()) {
                    return;
                }
                SrearchResultModel srearchResultModel = new SrearchResultModel();
                srearchResultModel.setViewType(3);
                srearchResultModel.setPlayList(t);
                if (t.size() == 10) {
                    MovieListModel.MovieListItem movieListItem = new MovieListModel.MovieListItem();
                    movieListItem.setMore(true);
                    srearchResultModel.getPlayList().add(movieListItem);
                }
                baseQuickAdapter = SearchResultAllFragment.this.mAdapter;
                if (baseQuickAdapter.getData().size() >= 2) {
                    baseQuickAdapter3 = SearchResultAllFragment.this.mAdapter;
                    baseQuickAdapter3.addData(2, (int) srearchResultModel);
                } else {
                    baseQuickAdapter2 = SearchResultAllFragment.this.mAdapter;
                    baseQuickAdapter2.addData((BaseQuickAdapter) srearchResultModel);
                }
                SearchResultAllFragment.this.hideEmptyView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onLoadComplete() {
        List data = this.mAdapter.getData();
        if (data == null || data.isEmpty()) {
            return;
        }
        hideEmptyView();
    }

    private final void searchActors() {
        Observable<R> compose = Http.getService().Search(API.BASE_URL, API.Search.SEARCH4, "actor", this.keyword, App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", Integer.valueOf(PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0)).compose(RxUtils.rxTranslate2List(ActorModel.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2List(T::class.java))");
        RxSubscribersKt.transform(compose, this).subscribe(new Observer<List<? extends ActorModel>>() { // from class: com.movieboxpro.android.view.fragment.SearchResultAllFragment$searchActors$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                SearchResultAllFragment.this.actorDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(List<? extends ActorModel> model) {
                BaseQuickAdapter baseQuickAdapter;
                BaseQuickAdapter baseQuickAdapter2;
                BaseQuickAdapter baseQuickAdapter3;
                Intrinsics.checkNotNullParameter(model, "model");
                if (model.isEmpty()) {
                    return;
                }
                SrearchResultModel srearchResultModel = new SrearchResultModel();
                srearchResultModel.setViewType(2);
                srearchResultModel.setActorModels(model);
                if (model.size() == 10) {
                    ActorModel actorModel = new ActorModel();
                    actorModel.setMore(true);
                    srearchResultModel.getActorModels().add(actorModel);
                }
                baseQuickAdapter = SearchResultAllFragment.this.mAdapter;
                if (baseQuickAdapter.getData().size() >= 4) {
                    baseQuickAdapter3 = SearchResultAllFragment.this.mAdapter;
                    baseQuickAdapter3.addData(4, (int) srearchResultModel);
                } else {
                    baseQuickAdapter2 = SearchResultAllFragment.this.mAdapter;
                    baseQuickAdapter2.addData((BaseQuickAdapter) srearchResultModel);
                }
                SearchResultAllFragment.this.hideEmptyView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, SrearchResultModel item) {
        String upperCase;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        int viewType = item.getViewType();
        boolean z = false;
        if (viewType != 1) {
            if (viewType == 2) {
                RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
                final ActorAdapter actorAdapter = new ActorAdapter(item.getActorModels());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
                if (recyclerView.getTag() == null) {
                    recyclerView.addItemDecoration(new LayoutManagerItemDecoration(0, 10));
                    recyclerView.setTag("tag");
                }
                recyclerView.setAdapter(actorAdapter);
                actorAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchResultAllFragment$ozY2gYL9EXw3crHRzU93jh7ZgGI
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        SearchResultAllFragment.m1217initHolder$lambda8(ActorAdapter.this, this, baseQuickAdapter, view, i);
                    }
                });
                return;
            } else if (viewType != 3) {
                return;
            } else {
                RecyclerView recyclerView2 = (RecyclerView) helper.getView(R.id.recyclerView);
                final MovieListItemAdapter movieListItemAdapter = new MovieListItemAdapter(item.getPlayList());
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
                if (recyclerView2.getTag() == null) {
                    recyclerView2.addItemDecoration(new LayoutManagerItemDecoration(0, 10));
                    recyclerView2.setTag("tag");
                }
                recyclerView2.setAdapter(movieListItemAdapter);
                movieListItemAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchResultAllFragment$yVOFPUx5W_bpbFjyRpLHM5ZmnWQ
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        SearchResultAllFragment.m1216initHolder$lambda12(MovieListItemAdapter.this, this, baseQuickAdapter, view, i);
                    }
                });
                return;
            }
        }
        GlideUtils.loadCornerPortraitGifHolder(getContext(), item.poster, (ImageView) helper.getView(R.id.search_movie_avatar), 8);
        ImageView imageView = (ImageView) helper.getView(R.id.search_movie_desc);
        String str = item.quality_tag_new;
        if ((str == null || str.length() == 0) ? true : true) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
            imageView.setImageResource(CommonUtils.getMovieTag(item.quality_tag_new));
        }
        SpanUtils with = SpanUtils.with((TextView) helper.getView(R.id.search_movie_name));
        String str2 = item.title;
        if (str2 != null) {
            SpanUtils bold = with.append(str2).setFontSize(14, true).setBold();
            Context context = getContext();
            SpanUtils fontSize = bold.setForegroundColor(context == null ? -1 : CommonExtKt.colorInt(context, (int) R.color.white)).append(" (").setFontSize(12, true);
            Context context2 = getContext();
            fontSize.setForegroundColor(context2 == null ? -1 : CommonExtKt.colorInt(context2, (int) R.color.white_30alpha));
        }
        String str3 = item.year;
        if (str3 != null) {
            SpanUtils fontSize2 = with.append(str3).setFontSize(12, true);
            Context context3 = getContext();
            SpanUtils fontSize3 = fontSize2.setForegroundColor(context3 == null ? -1 : CommonExtKt.colorInt(context3, (int) R.color.white_30alpha)).append(")").setFontSize(12, true);
            Context context4 = getContext();
            fontSize3.setForegroundColor(context4 != null ? CommonExtKt.colorInt(context4, (int) R.color.white_30alpha) : -1);
        }
        with.create();
        SpanUtils timeSpan = SpanUtils.with((TextView) helper.getView(R.id.search_movie_time));
        String str4 = item.runtime;
        if (str4 != null) {
            Intrinsics.checkNotNullExpressionValue(timeSpan, "timeSpan");
            SpanUtils bold2 = CommonExtKt.addText(timeSpan, str4, 14, R.color.white_30alpha).setBold();
            Intrinsics.checkNotNullExpressionValue(bold2, "timeSpan.addText(it, 14,….white_30alpha).setBold()");
            CommonExtKt.addText(bold2, " min  ", 14, R.color.white_30alpha).setBold();
        }
        String str5 = item.cats;
        if (str5 != null) {
            Intrinsics.checkNotNullExpressionValue(timeSpan, "timeSpan");
            CommonExtKt.addText(timeSpan, str5, 14, R.color.white_30alpha).setBold();
        }
        timeSpan.create();
        TextView textView = (TextView) helper.getView(R.id.search_movie_actors);
        textView.setText(item.imdb_rating);
        ScreenUtils.setDrawableLeft(textView, R.drawable.ic_rating);
        helper.setText(R.id.search_movie_describ, item.description);
        String str6 = item.lang;
        if (str6 == null) {
            upperCase = null;
        } else {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            upperCase = str6.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        }
        helper.setText(R.id.search_movie_star, upperCase);
        ImageView imageView2 = (ImageView) helper.getView(R.id.search_movie_play);
        if (item.box_type == 1) {
            imageView2.setImageResource(R.drawable.ic_play);
        } else {
            imageView2.setImageResource(R.drawable.ic_play_tv);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initHolder$lambda-8  reason: not valid java name */
    public static final void m1217initHolder$lambda8(ActorAdapter adapter, SearchResultAllFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String actor_id;
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ActorModel item = adapter.getItem(i);
        if (item.isMore()) {
            Context context = this$0.getContext();
            if (context == null) {
                return;
            }
            MoreActorsActivity.Companion companion = MoreActorsActivity.Companion;
            String str = this$0.keyword;
            companion.start(context, "", FirebaseAnalytics.Event.SEARCH, str, str);
            return;
        }
        Context context2 = this$0.getContext();
        if (context2 == null) {
            return;
        }
        ActorDetailActivity.Companion companion2 = ActorDetailActivity.Companion;
        String str2 = "";
        if (item != null && (actor_id = item.getActor_id()) != null) {
            str2 = actor_id;
        }
        companion2.start(context2, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initHolder$lambda-12  reason: not valid java name */
    public static final void m1216initHolder$lambda12(MovieListItemAdapter adapter, SearchResultAllFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String str;
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        MovieListModel.MovieListItem item = adapter.getItem(i);
        if (item.isMore()) {
            Context context = this$0.getContext();
            if (context == null) {
                return;
            }
            MoreSearchMovieListActivity.Companion.start(context, this$0.keyword);
            return;
        }
        Context context2 = this$0.getContext();
        if (context2 == null || item == null) {
            return;
        }
        List<String> imgArr = item.getImgArr();
        Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
        if (!imgArr.isEmpty()) {
            String str2 = item.getImgArr().get(0);
            Intrinsics.checkNotNullExpressionValue(str2, "item.imgArr[0]");
            str = str2;
        } else {
            str = "";
        }
        String lid = item.getLid();
        MovieListDetailActivity.start(context2, lid != null ? lid : "", item.getUsername(), str);
    }

    /* compiled from: SearchResultAllFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/SearchResultAllFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/SearchResultAllFragment;", "keyword", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchResultAllFragment newInstance(String str) {
            SearchResultAllFragment searchResultAllFragment = new SearchResultAllFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            searchResultAllFragment.setArguments(bundle);
            return searchResultAllFragment;
        }
    }
}
