package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.BaseLoadMoreAdapter;
import com.movieboxpro.android.adapter.VideoDiffCallback;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListDragFragment;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.FavoriteAllFilterLiveData;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
/* compiled from: FavoriteFragment.kt */
@Metadata(d1 = {"\u0000\u0099\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0013\u0018\u0000 Q2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002QRB\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0006H\u0014J\u0006\u0010\u001c\u001a\u00020\u0016J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J&\u0010 \u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030!j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003`\"J\u0006\u0010#\u001a\u00020\u0016J\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030&0%J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0014J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020*J\b\u0010+\u001a\u00020\u0016H\u0014J\u0006\u0010,\u001a\u00020\u0006J\u0018\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0002H\u0015J\b\u00101\u001a\u00020\u0016H\u0014J\u0012\u00102\u001a\u00020\u001a2\b\u00103\u001a\u0004\u0018\u000104H\u0014J\u0006\u00105\u001a\u00020\u0006J\b\u00106\u001a\u00020\u0006H\u0014J\b\u00107\u001a\u00020\u0006H\u0014J\b\u00108\u001a\u00020\u0006H\u0014J\b\u00109\u001a\u00020\u0006H\u0014J\u0010\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\u001aH\u0016J\b\u0010>\u001a\u00020\u001aH\u0016J\b\u0010?\u001a\u00020@H\u0014J\b\u0010A\u001a\u00020\u001aH\u0014J\u0010\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020DH\u0007J\u0010\u0010E\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020FH\u0007J\u0006\u0010G\u001a\u00020\u001aJ\u0006\u0010H\u001a\u00020\u001aJ\u000e\u0010I\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u000fJ\u000e\u0010J\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u0006J\u000e\u0010L\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\rJ>\u0010M\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\u000e\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment;", "Lcom/movieboxpro/android/base/BaseListDragFragment;", "Lcom/movieboxpro/android/model/common/Collectlist;", "", "()V", "allDefault", "", "boxType", "country", "editMode", "gener", "isAll", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/FavoriteFragment$OnFavoriteListener;", "onItemDragListener", "Lcom/chad/library/adapter/base/listener/OnItemDragListener;", "quality", "rating", "scrollListener", "com/movieboxpro/android/view/fragment/FavoriteFragment$scrollListener$1", "Lcom/movieboxpro/android/view/fragment/FavoriteFragment$scrollListener$1;", "selectCount", "", "sort", "year", "beginRefresh", "", "enableEventBus", "getAllCount", "getBundle", "arguments", "Landroid/os/Bundle;", "getFilterData", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getSelectCount", "getSelectIds", "Landroid/util/SparseArray;", "", "getServiceData", "Lio/reactivex/Observable;", "getVideos", "", "gridLayoutSpan", "haveFilter", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isEditMode", "isNeedLogin", "isOpenLoadMore", "isUseDiffUtil", "isVerticalLayout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDestroyView", "onFragmentResumeNoFirst", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onLoadComplete", "onLowMemory", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LowMemoryEvent;", "onRefresh", "Lcom/movieboxpro/android/event/RefreshFavoriteEvent;", "refreshSelectStatus", "selectAll", "setDragListener", "setEditMode", "edit", "setFavoriteListener", "setFilter", "genre", "setRefreshEnable", "enable", "Companion", "OnFavoriteListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FavoriteFragment extends BaseListDragFragment<Collectlist, String> {
    public static final Companion Companion = new Companion(null);
    private boolean editMode;
    private boolean isAll;
    private OnFavoriteListener listener;
    private OnItemDragListener onItemDragListener;
    private int selectCount;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String boxType = "0";
    private String sort = "";
    private String gener = "0";
    private String year = "0";
    private String rating = "";
    private String country = "";
    private String quality = "";
    private boolean allDefault = true;
    private final FavoriteFragment$scrollListener$1 scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment$scrollListener$1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Context context;
            Context context2;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                FragmentActivity activity = FavoriteFragment.this.getActivity();
                if (!((activity == null || activity.isDestroyed()) ? false : true) || (context = FavoriteFragment.this.getContext()) == null) {
                    return;
                }
                Glide.with(context).resumeRequests();
            } else if (i != 1) {
                if (i == 2 && (context2 = FavoriteFragment.this.getContext()) != null) {
                    Glide.with(context2).pauseRequests();
                }
            } else {
                Context context3 = FavoriteFragment.this.getContext();
                if (context3 == null) {
                    return;
                }
                Glide.with(context3).resumeRequests();
            }
        }
    };

    /* compiled from: FavoriteFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment$OnFavoriteListener;", "", "onSelect", "", "allCount", "", "selectCount", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnFavoriteListener {
        void onSelect(int i, int i2);
    }

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

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected int initItemLayout() {
        return R.layout.adapter_favorite_item;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected boolean isNeedLogin() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected boolean isUseDiffUtil() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected boolean isVerticalLayout() {
        return false;
    }

    public final void setFavoriteListener(OnFavoriteListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    public final void setFilter(String boxType, String year, String genre, String sort, String rating, String quality, String country) {
        Intrinsics.checkNotNullParameter(boxType, "boxType");
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        boolean z = true;
        this.mCurrentPage = 1;
        this.boxType = boxType;
        this.year = year;
        this.gener = genre;
        this.sort = sort;
        this.rating = rating;
        this.quality = quality;
        this.country = country;
        this.allDefault = (Intrinsics.areEqual(year, "0") && Intrinsics.areEqual(genre, "0") && Intrinsics.areEqual(sort, "") && StringsKt.isBlank(country) && Intrinsics.areEqual(boxType, "0")) ? false : false;
        if (this.isAll) {
            this.mAdapter.getDraggableModule().setDragEnabled(this.allDefault);
        }
        startRefresh();
    }

    public final boolean haveFilter() {
        if (Intrinsics.areEqual(this.year, "0") && Intrinsics.areEqual(this.gener, "0") && Intrinsics.areEqual(this.sort, "")) {
            String str = this.country;
            if (str == null || StringsKt.isBlank(str)) {
                String str2 = this.rating;
                if (str2 == null || StringsKt.isBlank(str2)) {
                    String str3 = this.quality;
                    if ((str3 == null || StringsKt.isBlank(str3)) && Intrinsics.areEqual(this.boxType, "0")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final HashMap<String, String> getFilterData() {
        return MapsKt.hashMapOf(TuplesKt.to("year", this.year), TuplesKt.to("gener", this.gener), TuplesKt.to("sort", this.sort), TuplesKt.to("rating", this.rating), TuplesKt.to("quality", this.quality), TuplesKt.to("country", this.country), TuplesKt.to("boxType", this.boxType));
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected void onLoadComplete() {
        if (this.isAll) {
            this.mAdapter.getLoadMoreModule().setEnableLoadMore(this.allDefault);
        } else {
            this.mAdapter.getLoadMoreModule().setEnableLoadMore(false);
        }
        if (this.mCurrentPage == 1) {
            this.selectCount = 0;
            OnFavoriteListener onFavoriteListener = this.listener;
            if (onFavoriteListener == null) {
                return;
            }
            onFavoriteListener.onSelect(this.mAdapter.getData().size(), this.selectCount);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected Observable<String> getServiceData() {
        if (this.isAll) {
            if (this.allDefault) {
                return HttpRequest.Companion.post(API.Movie.MOVE_COLLECT).param(IjkMediaMeta.IJKM_KEY_TYPE, "get").param("box_type", this.boxType).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).param("sort", this.sort).param("year", this.year).param("cat_id", this.gener).param("country", this.country).param("content_rating", this.rating).param("quality", this.quality).asRequest();
            }
            return HttpRequest.Companion.post("Movie_collect_list").param("box_type", this.boxType).param("sort", this.sort).param("year", this.year).param("cat_id", this.gener).param("country", this.country).param("content_rating", this.rating).param("quality", this.quality).asRequest();
        }
        return HttpRequest.Companion.post("Movie_collect_list").param("box_type", this.boxType).param("sort", this.sort).param("year", this.year).param("cat_id", this.gener).param("country", this.country).param("content_rating", this.rating).param("quality", this.quality).asRequest();
    }

    public final void setRefreshEnable(boolean z) {
        this.swipeRefreshLayout.setEnabled(z);
    }

    public final List<Collectlist> getVideos() {
        BaseLoadMoreAdapter baseLoadMoreAdapter = this.mAdapter;
        List<Collectlist> data = baseLoadMoreAdapter == null ? null : baseLoadMoreAdapter.getData();
        return data == null ? new ArrayList() : data;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected void getBundle(Bundle bundle) {
        String string;
        this.mClass = Collectlist.class;
        boolean z = false;
        this.isAll = bundle == null ? false : bundle.getBoolean(TtmlNode.COMBINE_ALL, false);
        if (bundle == null || (string = bundle.getString("boxType")) == null) {
            string = "";
        }
        this.boxType = string;
        String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_COLLECT);
        String str = string2;
        if (!(str == null || StringsKt.isBlank(str))) {
            Object parseObject = JSONObject.parseObject(string2, HashMap.class);
            if (parseObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.String> }");
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
            String str8 = (String) hashMap.get("boxType");
            if (str8 == null) {
                str8 = "";
            }
            this.boxType = str8;
        }
        if (Intrinsics.areEqual(this.year, "0") && Intrinsics.areEqual(this.gener, "0") && Intrinsics.areEqual(this.sort, "")) {
            String str9 = this.country;
            if ((str9 == null || StringsKt.isBlank(str9)) && Intrinsics.areEqual(this.boxType, "0")) {
                z = true;
            }
        }
        this.allDefault = z;
        if (this.isAll) {
            FavoriteAllFilterLiveData.Companion.get().setValue(Boolean.valueOf(!this.allDefault));
        }
    }

    public final int getSelectCount() {
        return this.selectCount;
    }

    public final int getAllCount() {
        if (this.mAdapter != null) {
            return this.mAdapter.getData().size();
        }
        return 0;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment$rcpH3prt_aeDpRo-hrkqcOVvax4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FavoriteFragment.m1157onItemClick$lambda0(FavoriteFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-0  reason: not valid java name */
    public static final void m1157onItemClick$lambda0(FavoriteFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        Collectlist collectlist = (Collectlist) this$0.mAdapter.getItem(i);
        if (collectlist != null) {
            if (this$0.editMode) {
                collectlist.setSelected(!collectlist.isSelected());
                if (collectlist.isSelected()) {
                    this$0.selectCount++;
                } else {
                    this$0.selectCount--;
                }
                OnFavoriteListener onFavoriteListener = this$0.listener;
                if (onFavoriteListener != null) {
                    onFavoriteListener.onSelect(this$0.mAdapter.getData().size(), this$0.selectCount);
                }
                this$0.mAdapter.notifyItemChanged(i);
            } else if (collectlist.box_type == 1) {
                MovieDetailActivity.start(this$0.getContext(), collectlist.id, collectlist.poster);
            } else {
                TvDetailActivity.start(this$0.getContext(), collectlist.id, collectlist.banner_mini, collectlist.poster);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListDragFragment
    public void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        this.mAdapter.setDiffCallback(new VideoDiffCallback());
        this.mAdapter.getDraggableModule().setDragEnabled(this.isAll);
        this.mAdapter.getDraggableModule().setOnItemDragListener(this.onItemDragListener);
        this.mRecyclerView.addOnScrollListener(this.scrollListener);
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mRecyclerView.removeOnScrollListener(this.scrollListener);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setDragListener(OnItemDragListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onItemDragListener = listener;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRefresh(RefreshFavoriteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        beginRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResumeNoFirst() {
        super.onFragmentResumeNoFirst();
        if (this.mAdapter == null || !this.mAdapter.getData().isEmpty() || this.swipeRefreshLayout.isRefreshing()) {
            return;
        }
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLowMemory(LowMemoryEvent event) {
        BaseLoadMoreAdapter baseLoadMoreAdapter;
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.isVisibleToUser || (baseLoadMoreAdapter = this.mAdapter) == null) {
            return;
        }
        baseLoadMoreAdapter.setList(new ArrayList());
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected boolean isOpenLoadMore() {
        return this.isAll;
    }

    @Override // com.movieboxpro.android.base.BaseListDragFragment
    protected int gridLayoutSpan() {
        boolean z = false;
        if (getContext() != null && CommonUtils.isTablet()) {
            z = true;
        }
        return z ? 5 : 3;
    }

    public final boolean isEditMode() {
        return this.editMode;
    }

    public final void setEditMode(boolean z) {
        this.editMode = z;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public final void selectAll() {
        if (this.mAdapter != null) {
            if (this.selectCount == this.mAdapter.getData().size()) {
                for (Collectlist collectlist : this.mAdapter.getData()) {
                    collectlist.setSelected(false);
                    this.selectCount = 0;
                }
            } else {
                for (Collectlist collectlist2 : this.mAdapter.getData()) {
                    collectlist2.setSelected(true);
                    this.selectCount = this.mAdapter.getData().size();
                }
            }
            OnFavoriteListener onFavoriteListener = this.listener;
            if (onFavoriteListener != null) {
                onFavoriteListener.onSelect(this.mAdapter.getData().size(), this.selectCount);
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public final void beginRefresh() {
        startRefresh();
    }

    public final void refreshSelectStatus() {
        if (this.mAdapter != null) {
            this.editMode = false;
            this.selectCount = 0;
            OnFavoriteListener onFavoriteListener = this.listener;
            if (onFavoriteListener != null) {
                onFavoriteListener.onSelect(this.mAdapter.getData().size(), this.selectCount);
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public final SparseArray<List<String>> getSelectIds() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SparseArray<List<String>> sparseArray = new SparseArray<>();
        if (this.mAdapter != null) {
            for (Collectlist collectlist : this.mAdapter.getData()) {
                if (collectlist.isSelected()) {
                    if (collectlist.box_type == 1) {
                        arrayList.add(collectlist.id);
                    } else {
                        arrayList2.add(collectlist.id);
                    }
                }
            }
        }
        sparseArray.put(1, arrayList);
        sparseArray.put(2, arrayList2);
        return sparseArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListDragFragment
    public void initHolder(BaseViewHolder helper, Collectlist item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
        SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.tvTimeUpdate);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivCheckable);
        TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
        TextView textView2 = (TextView) helper.getView(R.id.tvUpdateCount);
        GlideUtils.loadPortraitGifHolder(getContext(), item.poster, (ImageView) helper.getView(R.id.ivPoster));
        boolean z = false;
        if (item.box_type == 1) {
            CommonExtKt.gone(textView);
            CommonExtKt.gone(slantedTextView);
            String str = item.quality_tag_new;
            if (str == null || str.length() == 0) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
                imageView.setImageResource(CommonUtils.getMovieTag(item.quality_tag_new));
            }
        } else {
            CommonExtKt.gone(imageView);
            String str2 = item.season_episode;
            if (str2 == null || str2.length() == 0) {
                CommonExtKt.gone(textView);
            } else {
                CommonExtKt.visible(textView);
                textView.setText(item.season_episode);
            }
            String str3 = item.update_title;
            if (str3 == null || str3.length() == 0) {
                CommonExtKt.gone(slantedTextView);
            } else {
                CommonExtKt.visible(slantedTextView);
                slantedTextView.setText(item.update_title);
            }
        }
        if (this.editMode) {
            CommonExtKt.visible(imageView2);
            if (item.isSelected()) {
                imageView2.setImageResource(R.drawable.ic_checkbox_checked);
            } else {
                imageView2.setImageResource(R.drawable.ic_checkbox);
            }
        } else {
            CommonExtKt.gone(imageView2);
            if (item.isSelected()) {
                imageView2.setImageResource(R.drawable.ic_checkbox_checked);
            } else {
                imageView2.setImageResource(R.drawable.ic_checkbox);
            }
        }
        if (item.updateCount > 0) {
            CommonExtKt.visible(textView2);
            if (item.updateCount > 99) {
                textView2.setText("99+");
            } else {
                textView2.setText(String.valueOf(item.updateCount));
            }
        } else {
            CommonExtKt.gone(textView2);
        }
        TextView textView3 = (TextView) helper.getView(R.id.tvImdbRating);
        String imdb_rating = item.getImdb_rating();
        if ((imdb_rating == null || imdb_rating.length() == 0) ? true : true) {
            CommonExtKt.textShadow$default(textView3, "-.-", 0, 0, 6, null);
        } else {
            String imdb_rating2 = item.getImdb_rating();
            if (imdb_rating2 == null) {
                imdb_rating2 = "";
            }
            CommonExtKt.textShadow$default(textView3, imdb_rating2, 0, 0, 6, null);
        }
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivTomato);
        TextView textView4 = (TextView) helper.getView(R.id.tvTomatoMeter);
        imageView3.setImageResource(CommonUtils.getTomatoImg(item.tomato_meter));
        if (item.tomato_meter == 0) {
            CommonExtKt.gone(textView4);
            CommonExtKt.gone(imageView3);
            return;
        }
        CommonExtKt.visible(textView4);
        CommonExtKt.visible(imageView3);
        StringBuilder sb = new StringBuilder();
        sb.append(item.tomato_meter);
        sb.append('%');
        CommonExtKt.textShadow$default(textView4, sb.toString(), 0, 0, 6, null);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            RecyclerView mRecyclerView = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 5);
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (getResources().getConfiguration().orientation == 1) {
            RecyclerView mRecyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 3);
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    /* compiled from: FavoriteFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/FavoriteFragment;", "boxType", "", TtmlNode.COMBINE_ALL, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FavoriteFragment newInstance(int i, boolean z) {
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            favoriteFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("boxType", Integer.valueOf(i)), TuplesKt.to(TtmlNode.COMBINE_ALL, Boolean.valueOf(z))));
            return favoriteFragment;
        }
    }
}
