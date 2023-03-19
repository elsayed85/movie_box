package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.SearchEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.search.SrearchResultModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SearchResultFragment.kt */
@Metadata(d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\r\u0018\u0000 .2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0006H\u0014J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aH\u0014J\u0006\u0010\u001b\u001a\u00020\u0006J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0002H\u0014J\b\u0010 \u001a\u00020!H\u0014J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$H\u0014J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020\u0014H\u0016J\b\u0010'\u001a\u00020(H\u0014J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0007J6\u0010,\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/view/fragment/SearchResultFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/search/SrearchResultModel;", "", "()V", "allDefault", "", "country", "gener", "keyword", "quality", "rating", "scrollListener", "com/movieboxpro/android/view/fragment/SearchResultFragment$scrollListener$1", "Lcom/movieboxpro/android/view/fragment/SearchResultFragment$scrollListener$1;", "sort", IjkMediaMeta.IJKM_KEY_TYPE, "year", "enableEventBus", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getFilterData", "Ljava/util/HashMap;", "getServiceData", "Lio/reactivex/Observable;", "haveFilter", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onDestroyView", "onFragmentResume", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onSearch", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/SearchEvent;", "setFilter", "genre", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SearchResultFragment extends BaseListFragment<SrearchResultModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String keyword = "";
    private String type = "";
    private String sort = "";
    private String gener = "0";
    private String year = "";
    private String rating = "";
    private String country = "";
    private String quality = "";
    private boolean allDefault = true;
    private final SearchResultFragment$scrollListener$1 scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.SearchResultFragment$scrollListener$1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Context context;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                Context context2 = SearchResultFragment.this.getContext();
                if (context2 == null) {
                    return;
                }
                Glide.with(context2).resumeRequests();
            } else if (i != 1) {
                if (i == 2 && (context = SearchResultFragment.this.getContext()) != null) {
                    Glide.with(context).pauseRequests();
                }
            } else {
                Context context3 = SearchResultFragment.this.getContext();
                if (context3 == null) {
                    return;
                }
                Glide.with(context3).resumeRequests();
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
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.layout_search_movie_item;
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

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return Http.getService().SearchFilter(API.BASE_URL, API.Search.SEARCH4, this.type, this.keyword, App.getUserData().uid_v2, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), this.sort, this.year, this.rating, this.quality, this.gener, this.country, PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchResultFragment$IsQN0GnO_AUp1nUik8sAghHcxtU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchResultFragment.m1220onItemClick$lambda0(SearchResultFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-0  reason: not valid java name */
    public static final void m1220onItemClick$lambda0(SearchResultFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        String string;
        String string2;
        String string3;
        if (bundle == null || (string = bundle.getString("keyword")) == null) {
            string = "";
        }
        this.keyword = string;
        String str = TtmlNode.COMBINE_ALL;
        if (bundle != null && (string3 = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE, TtmlNode.COMBINE_ALL)) != null) {
            str = string3;
        }
        this.type = str;
        this.mClass = SrearchResultModel.class;
        this.mPageSize = 8;
        this.mPreLoadNum = 4;
        if (Intrinsics.areEqual(this.type, "movie")) {
            string2 = PrefsUtils.getInstance().getString(Constant.Prefs.SEARCH_FILTER_MOVIE);
        } else {
            string2 = PrefsUtils.getInstance().getString(Constant.Prefs.SEARCH_FILTER_TV);
        }
        String str2 = string2;
        boolean z = false;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            Object parseObject = JSONObject.parseObject(string2, HashMap.class);
            if (parseObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
            }
            HashMap hashMap = (HashMap) parseObject;
            String str3 = (String) hashMap.get("year");
            if (str3 == null) {
                str3 = "0";
            }
            this.year = str3;
            String str4 = (String) hashMap.get("gener");
            if (str4 == null) {
                str4 = "0";
            }
            this.gener = str4;
            String str5 = (String) hashMap.get("sort");
            if (str5 == null) {
                str5 = "";
            }
            this.sort = str5;
            String str6 = (String) hashMap.get("rating");
            if (str6 == null) {
                str6 = "";
            }
            this.rating = str6;
            String str7 = (String) hashMap.get("quality");
            if (str7 == null) {
                str7 = "";
            }
            this.quality = str7;
            String str8 = (String) hashMap.get("country");
            if (str8 == null) {
                str8 = "";
            }
            this.country = str8;
        }
        if (Intrinsics.areEqual(this.year, "0") && Intrinsics.areEqual(this.gener, "0") && Intrinsics.areEqual(this.sort, "")) {
            String str9 = this.country;
            if (str9 == null || StringsKt.isBlank(str9)) {
                z = true;
            }
        }
        this.allDefault = z;
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        if (Intrinsics.areEqual(this.type, "tv")) {
            Context context = getContext();
            if (context != null) {
                CommonExtKt.onMobEvent(context, "TVSearch");
            }
            EventUtils.event("电视搜索");
        } else if (Intrinsics.areEqual(this.type, "movie")) {
            Context context2 = getContext();
            if (context2 != null) {
                CommonExtKt.onMobEvent(context2, "MovieSearch");
            }
            EventUtils.event("电影搜索");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSearch(SearchEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String str = event.keyWord;
        Intrinsics.checkNotNullExpressionValue(str, "event.keyWord");
        this.keyword = str;
        startRefresh();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, SrearchResultModel item) {
        String upperCase;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        GlideUtils.loadCornerPortraitGifHolder(getContext(), item.poster, (ImageView) helper.getView(R.id.search_movie_avatar), 8);
        ImageView imageView = (ImageView) helper.getView(R.id.search_movie_desc);
        String str = item.quality_tag_new;
        if (str == null || str.length() == 0) {
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

    /* compiled from: SearchResultFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/SearchResultFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/SearchResultFragment;", "keyword", "", IjkMediaMeta.IJKM_KEY_TYPE, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchResultFragment newInstance(String str, String type) {
            Intrinsics.checkNotNullParameter(type, "type");
            SearchResultFragment searchResultFragment = new SearchResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, type);
            searchResultFragment.setArguments(bundle);
            return searchResultFragment;
        }
    }
}
