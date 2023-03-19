package com.movieboxpro.android.view.fragment.home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseListEmptyFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.ChangeTabPosEvent;
import com.movieboxpro.android.event.OpenFilterEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.CountryResponse;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.model.tv.TvDetailModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import com.movieboxpro.android.view.fragment.home.SpecialFilterFragment;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.android.view.widget.SpecialFilterView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: TvShowsFragment.kt */
@Metadata(d1 = {"\u0000§\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0017\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 2\u0006\u0010\"\u001a\u00020#H\u0014J\b\u0010$\u001a\u00020%H\u0014J\b\u0010&\u001a\u00020'H\u0014J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020.H\u0002J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000300H\u0014J\b\u00101\u001a\u00020\u000eH\u0014J\b\u00102\u001a\u00020)H\u0002J\u0018\u00103\u001a\u00020)2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0002H\u0014J\b\u00107\u001a\u00020\u000eH\u0014J\u0010\u00108\u001a\u00020)2\u0006\u0010\"\u001a\u00020#H\u0014J\b\u00109\u001a\u00020'H\u0014J\b\u0010:\u001a\u00020'H\u0014J\u0010\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020)H\u0016J\u0018\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020\u0003H\u0016J\b\u0010B\u001a\u00020CH\u0014J\u0010\u0010D\u001a\u00020)2\u0006\u0010-\u001a\u00020.H\u0007J(\u0010E\u001a\u00020)2\u0006\u0010-\u001a\u00020.2\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\bH\u0002J\b\u0010F\u001a\u00020'H\u0014J\b\u0010G\u001a\u00020)H\u0002J\b\u0010H\u001a\u00020)H\u0002R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0007j\b\u0012\u0004\u0012\u00020\u000e`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/TvShowsFragment;", "Lcom/movieboxpro/android/base/BaseListEmptyFragment;", "Lcom/movieboxpro/android/model/tv/TvDetailModel;", "", "Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment$OnTopFilterClickListener;", "()V", "country", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "dialog", "Lcom/movieboxpro/android/view/dialog/FilterVideoDialog;", "genre", "", "genres", "", "Lcom/movieboxpro/android/model/common/Gener;", "imdbRating", "loadingPopupView", "Lcom/lxj/xpopup/impl/LoadingPopupView;", "rating", "scrollListener", "com/movieboxpro/android/view/fragment/home/TvShowsFragment$scrollListener$1", "Lcom/movieboxpro/android/view/fragment/home/TvShowsFragment$scrollListener$1;", "sort", "tomatoMeter", "topFilterId", "topFilterView", "Lcom/movieboxpro/android/view/widget/SpecialFilterView;", "year", "addHeaderView", "", "Landroid/view/View;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "addItemDecoration", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "enableEventBus", "", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getCountry", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OpenFilterEvent;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "hideLoadingView", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "isOpenLoadMore", "isVerticalLayout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDestroyView", "onFilterClicked", "selected", "id", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onOpenFilter", "openFilter", "openLowMemoryEmpty", "requestTopFilter", "showLoadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TvShowsFragment extends BaseListEmptyFragment<TvDetailModel, String> implements SpecialFilterFragment.OnTopFilterClickListener {
    private ArrayList<FilterCountry> countryList;
    private FilterVideoDialog dialog;
    private List<? extends Gener> genres;
    private LoadingPopupView loadingPopupView;
    private SpecialFilterView topFilterView;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String year = "";
    private ArrayList<Integer> genre = new ArrayList<>();
    private String sort = FilterVideoDialog.TV_DEFAULT_SORT;
    private String rating = "";
    private String imdbRating = "";
    private String tomatoMeter = "";
    private String topFilterId = "";
    private ArrayList<String> country = new ArrayList<>();
    private final TvShowsFragment$scrollListener$1 scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.home.TvShowsFragment$scrollListener$1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Context context;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                Context context2 = TvShowsFragment.this.getContext();
                if (context2 == null) {
                    return;
                }
                FragmentActivity activity = TvShowsFragment.this.getActivity();
                if ((activity == null || activity.isDestroyed()) ? false : true) {
                    Glide.with(context2).resumeRequests();
                }
            } else if (i != 1) {
                if (i == 2 && (context = TvShowsFragment.this.getContext()) != null) {
                    FragmentActivity activity2 = TvShowsFragment.this.getActivity();
                    if ((activity2 == null || activity2.isDestroyed()) ? false : true) {
                        Glide.with(context).pauseRequests();
                    }
                }
            } else {
                Context context3 = TvShowsFragment.this.getContext();
                if (context3 == null) {
                    return;
                }
                FragmentActivity activity3 = TvShowsFragment.this.getActivity();
                if ((activity3 == null || activity3.isDestroyed()) ? false : true) {
                    Glide.with(context3).resumeRequests();
                }
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

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected int initItemLayout() {
        return R.layout.adapter_tv_shows_item;
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected boolean isVerticalLayout() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected boolean openLowMemoryEmpty() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected Observable<String> getServiceData() {
        if (this.topFilterId.length() > 0) {
            return Http.getService().Top_list_movie(API.BASE_URL, API.Tv.TOP_LIST_TV, this.topFilterId, this.mCurrentPage, this.mPageSize);
        }
        return HttpRequest.Companion.post(API.Tv.TV_LIST_V2).param("year", this.year).param("cid", this.genre).param("orderby", this.sort).param("rating", this.rating).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).param("country", this.country).param("imdb_rating_section", this.imdbRating).param("tomato_meter_section", this.tomatoMeter).asRequest();
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected void getBundle(Bundle bundle) {
        this.mClass = TvDetailModel.class;
        this.mPageSize = 8;
        this.mPreLoadNum = 6;
        StringBuilder sb = new StringBuilder();
        sb.append((int) SettingManager.INSTANCE.getTVStartYear());
        sb.append('-');
        sb.append((int) SettingManager.INSTANCE.getTVEndYear());
        String sb2 = sb.toString();
        if (Intrinsics.areEqual(sb2, Intrinsics.stringPlus("1900-", Integer.valueOf(Calendar.getInstance().get(1))))) {
            this.year = "";
        } else {
            this.year = sb2;
        }
        this.genre = SettingManager.INSTANCE.getTVGener();
        this.sort = SettingManager.INSTANCE.getTVSort();
        this.rating = SettingManager.INSTANCE.getTVRating();
        this.country = SettingManager.INSTANCE.getTVCountry();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(SettingManager.INSTANCE.getTvImdbStart());
        sb3.append('-');
        sb3.append(SettingManager.INSTANCE.getTvImdbEnd());
        String sb4 = sb3.toString();
        this.imdbRating = sb4;
        if (Intrinsics.areEqual(sb4, "0.0-10.0")) {
            this.imdbRating = "";
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(SettingManager.INSTANCE.getTvTomatoStart());
        sb5.append('-');
        sb5.append(SettingManager.INSTANCE.getTvTomatoEnd());
        String sb6 = sb5.toString();
        this.tomatoMeter = sb6;
        if (Intrinsics.areEqual(sb6, "0.0-100.0")) {
            this.tomatoMeter = "";
        }
        Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, new TvShowsFragment$getBundle$1(this), 15, null);
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected List<View> addHeaderView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Context context = getContext();
        SpecialFilterView specialFilterView = context == null ? null : new SpecialFilterView(context, null, 0, 6, null);
        this.topFilterView = specialFilterView;
        if (specialFilterView != null) {
            specialFilterView.setTopFilterClickListener(this);
        }
        requestTopFilter();
        View[] viewArr = new View[1];
        SpecialFilterView specialFilterView2 = this.topFilterView;
        if (specialFilterView2 != null) {
            viewArr[0] = specialFilterView2;
            return CollectionsKt.arrayListOf(viewArr);
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.View");
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected void initRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.addOnScrollListener(this.scrollListener);
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mRecyclerView.removeOnScrollListener(this.scrollListener);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    private final void requestTopFilter() {
        ((ObservableSubscribeProxy) Http.getService().Top_list(API.BASE_URL, "Top_list", 2).compose(RxUtils.rxTranslate2List(SpecialFilterModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<SpecialFilterModel>>() { // from class: com.movieboxpro.android.view.fragment.home.TvShowsFragment$requestTopFilter$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<SpecialFilterModel> model) {
                SpecialFilterView specialFilterView;
                Intrinsics.checkNotNullParameter(model, "model");
                specialFilterView = TvShowsFragment.this.topFilterView;
                if (specialFilterView == null) {
                    return;
                }
                specialFilterView.setFilterData(model);
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected RecyclerView.ItemDecoration addItemDecoration() {
        return new GridInsetDecoration(10, 1, true);
    }

    private final void getCountry(OpenFilterEvent openFilterEvent) {
        String string = PrefsUtils.getInstance().getString("movie_country_list");
        if (string != null) {
            if (this.countryList == null) {
                Observable<R> compose = HttpRequest.Companion.post("Movie_country_list").param("box_type", (Object) 2).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                Object as = compose.map($$Lambda$TvShowsFragment$PGo3KraS8WPY5jWMrHkccstr8aE.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Movie_…bindLifecycleOwner(this))");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, TvShowsFragment$getCountry$6.INSTANCE, 15, null);
            }
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new TvShowsFragment$getCountry$7(this, string, openFilterEvent, null), 2, null);
            return;
        }
        Observable<R> compose2 = HttpRequest.Companion.post("Movie_country_list").param("box_type", (Object) 2).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(compose2, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        Object as2 = compose2.map($$Lambda$TvShowsFragment$FipRcmzaoa1618z2Tsr9xgiQ.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as2, "HttpRequest.post(\"Movie_…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as2, new TvShowsFragment$getCountry$2(this), null, new TvShowsFragment$getCountry$3(this), null, new TvShowsFragment$getCountry$4(this, openFilterEvent), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCountry$lambda-1  reason: not valid java name */
    public static final CountryResponse m1254getCountry$lambda1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        PrefsUtils.getInstance().putString("movie_country_list", it);
        return (CountryResponse) JSONObject.parseObject(it, CountryResponse.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCountry$lambda-2  reason: not valid java name */
    public static final PrefsUtils m1255getCountry$lambda2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrefsUtils.getInstance().putString("movie_country_list", it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openFilter(OpenFilterEvent openFilterEvent, ArrayList<FilterCountry> arrayList) {
        if (openFilterEvent.getType() == 2) {
            this.genres = openFilterEvent.getGeners();
            if (this.dialog == null) {
                FilterVideoDialog.Companion companion = FilterVideoDialog.Companion;
                List<Gener> geners = openFilterEvent.getGeners();
                Intrinsics.checkNotNullExpressionValue(geners, "event.geners");
                FilterVideoDialog newInstance$default = FilterVideoDialog.Companion.newInstance$default(companion, geners, false, 0, 4, null);
                this.dialog = newInstance$default;
                Intrinsics.checkNotNull(newInstance$default);
                newInstance$default.setFilterListener(arrayList, new FilterVideoDialog.OnFilterListener() { // from class: com.movieboxpro.android.view.fragment.home.TvShowsFragment$openFilter$1
                    @Override // com.movieboxpro.android.view.dialog.FilterVideoDialog.OnFilterListener
                    public void onFilter(String year, ArrayList<Integer> genre, String sort, String rating, String quality, ArrayList<String> country, String imdbRating, String tomatoMeter) {
                        SpecialFilterView specialFilterView;
                        Intrinsics.checkNotNullParameter(year, "year");
                        Intrinsics.checkNotNullParameter(genre, "genre");
                        Intrinsics.checkNotNullParameter(sort, "sort");
                        Intrinsics.checkNotNullParameter(rating, "rating");
                        Intrinsics.checkNotNullParameter(quality, "quality");
                        Intrinsics.checkNotNullParameter(country, "country");
                        Intrinsics.checkNotNullParameter(imdbRating, "imdbRating");
                        Intrinsics.checkNotNullParameter(tomatoMeter, "tomatoMeter");
                        specialFilterView = TvShowsFragment.this.topFilterView;
                        if (specialFilterView != null) {
                            specialFilterView.resetFilter();
                        }
                        TvShowsFragment.this.topFilterId = "";
                        TvShowsFragment.this.year = year;
                        TvShowsFragment.this.sort = sort;
                        TvShowsFragment.this.rating = rating;
                        TvShowsFragment.this.genre = genre;
                        TvShowsFragment.this.country = country;
                        TvShowsFragment.this.imdbRating = imdbRating;
                        TvShowsFragment.this.tomatoMeter = tomatoMeter;
                        TvShowsFragment.this.startRefresh();
                    }

                    @Override // com.movieboxpro.android.view.dialog.FilterVideoDialog.OnFilterListener
                    public void typeSelect(int i) {
                        EventBus.getDefault().post(new ChangeTabPosEvent(i));
                    }
                });
            }
            FilterVideoDialog filterVideoDialog = this.dialog;
            boolean z = false;
            if (filterVideoDialog != null && !filterVideoDialog.isAdded()) {
                z = true;
            }
            if (z) {
                FilterVideoDialog filterVideoDialog2 = this.dialog;
                Intrinsics.checkNotNull(filterVideoDialog2);
                filterVideoDialog2.show(getChildFragmentManager(), "FilterVideoDialog");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onOpenFilter(OpenFilterEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 2) {
            ArrayList<FilterCountry> arrayList = this.countryList;
            if (arrayList == null) {
                getCountry(event);
                return;
            }
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            openFilter(event, arrayList);
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.SpecialFilterFragment.OnTopFilterClickListener
    public void onFilterClicked(boolean z, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (!z) {
            id = "";
        }
        this.topFilterId = id;
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.home.-$$Lambda$TvShowsFragment$5AiuwcjSzOZbByVHQ2GKMDgUDEo
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvShowsFragment.m1257onItemClick$lambda3(TvShowsFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-3  reason: not valid java name */
    public static final void m1257onItemClick$lambda3(TvShowsFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        TvDetailModel tvDetailModel = (TvDetailModel) this$0.mAdapter.getItem(i);
        TvDetailActivity.start(this$0.getContext(), tvDetailModel == null ? null : tvDetailModel.id, tvDetailModel == null ? null : tvDetailModel.banner_mini, tvDetailModel != null ? tvDetailModel.poster : null);
    }

    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 4 : 3;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        boolean z = false;
        if (getResources().getConfiguration().orientation == 2) {
            RecyclerView mRecyclerView = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 4);
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            FilterVideoDialog filterVideoDialog = this.dialog;
            if (filterVideoDialog != null && filterVideoDialog.isVisible()) {
                z = true;
            }
            if (!z || this.genres == null) {
                return;
            }
            FilterVideoDialog filterVideoDialog2 = this.dialog;
            if (filterVideoDialog2 != null) {
                filterVideoDialog2.dismiss();
            }
            FilterVideoDialog.Companion companion = FilterVideoDialog.Companion;
            List<? extends Gener> list = this.genres;
            Intrinsics.checkNotNull(list);
            FilterVideoDialog newInstance$default = FilterVideoDialog.Companion.newInstance$default(companion, list, false, 0, 4, null);
            this.dialog = newInstance$default;
            if (newInstance$default == null) {
                return;
            }
            newInstance$default.show(getChildFragmentManager(), "FilterVideoDialog");
        } else if (getResources().getConfiguration().orientation == 1) {
            RecyclerView mRecyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 2);
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            FilterVideoDialog filterVideoDialog3 = this.dialog;
            if (filterVideoDialog3 != null && filterVideoDialog3.isVisible()) {
                z = true;
            }
            if (!z || this.genres == null) {
                return;
            }
            FilterVideoDialog filterVideoDialog4 = this.dialog;
            if (filterVideoDialog4 != null) {
                filterVideoDialog4.dismiss();
            }
            FilterVideoDialog.Companion companion2 = FilterVideoDialog.Companion;
            List<? extends Gener> list2 = this.genres;
            Intrinsics.checkNotNull(list2);
            FilterVideoDialog newInstance$default2 = FilterVideoDialog.Companion.newInstance$default(companion2, list2, false, 0, 4, null);
            this.dialog = newInstance$default2;
            if (newInstance$default2 == null) {
                return;
            }
            newInstance$default2.show(getChildFragmentManager(), "FilterVideoDialog");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            Intrinsics.checkNotNull(loadingPopupView);
            if (loadingPopupView.isShow()) {
                return;
            }
        }
        LoadingPopupView loadingPopupView2 = this.loadingPopupView;
        if (loadingPopupView2 != null) {
            Intrinsics.checkNotNull(loadingPopupView2);
            if (loadingPopupView2.isShow()) {
                return;
            }
        }
        if (this.loadingPopupView == null) {
            this.loadingPopupView = new XPopup.Builder(getContext()).dismissOnBackPressed(true).dismissOnTouchOutside(false).hasShadowBg(true).asLoading("");
        }
        LoadingPopupView loadingPopupView3 = this.loadingPopupView;
        Intrinsics.checkNotNull(loadingPopupView3);
        loadingPopupView3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoadingView() {
        LoadingPopupView loadingPopupView = this.loadingPopupView;
        if (loadingPopupView != null) {
            Intrinsics.checkNotNull(loadingPopupView);
            loadingPopupView.smartDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListEmptyFragment
    public void initHolder(BaseViewHolder helper, TvDetailModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        GlideUtils.loadPortraitGifHolder(getContext(), item.poster, (ImageView) helper.getView(R.id.tv_item_poster));
        TextView textView = (TextView) helper.getView(R.id.tv_item_season);
        SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.slv_right_text);
        String str = item.season_episode;
        boolean z = false;
        if (str == null || str.length() == 0) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            textView.setText(item.season_episode);
        }
        String str2 = item.update_title;
        if (str2 == null || str2.length() == 0) {
            CommonExtKt.gone(slantedTextView);
        } else {
            CommonExtKt.visible(slantedTextView);
            slantedTextView.setText(item.update_title);
        }
        TextView textView2 = (TextView) helper.getView(R.id.tvImdbRating);
        String str3 = item.imdb_rating;
        if ((str3 == null || str3.length() == 0) ? true : true) {
            CommonExtKt.textShadow$default(textView2, "-.-", 0, 0, 6, null);
        } else {
            String str4 = item.imdb_rating;
            if (str4 == null) {
                str4 = "";
            }
            CommonExtKt.textShadow$default(textView2, str4, 0, 0, 6, null);
        }
        ImageView imageView = (ImageView) helper.getView(R.id.ivTomato);
        TextView textView3 = (TextView) helper.getView(R.id.tvTomatoMeter);
        imageView.setImageResource(CommonUtils.getTomatoImg(item.tomato_meter));
        if (item.tomato_meter == 0) {
            CommonExtKt.gone(textView3);
            CommonExtKt.gone(imageView);
            return;
        }
        CommonExtKt.visible(textView3);
        CommonExtKt.visible(imageView);
        StringBuilder sb = new StringBuilder();
        sb.append(item.tomato_meter);
        sb.append('%');
        CommonExtKt.textShadow$default(textView3, sb.toString(), 0, 0, 6, null);
    }
}
