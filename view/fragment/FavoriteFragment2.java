package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseNodeListFragment;
import com.movieboxpro.android.base.BaseSimpleFragment;
import com.movieboxpro.android.databinding.FragmentFavoriteBinding;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.FavoriteAllFilterLiveData;
import com.movieboxpro.android.livedata.GetPlanPathLiveData;
import com.movieboxpro.android.livedata.GoMoreWatchingLiveData;
import com.movieboxpro.android.livedata.RefreshWaitingLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteResponse;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.WatchingItem;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.utils.databinding.FragmentBindingDelegate;
import com.movieboxpro.android.view.activity.MoreWatchingActivity;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.dialog.SeasonExpandDialog;
import com.movieboxpro.android.view.fragment.FavoriteFragment2;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: FavoriteFragment2.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment2;", "Lcom/movieboxpro/android/base/BaseSimpleFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/FragmentFavoriteBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/FragmentFavoriteBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", "initData", "", "initListener", "initView", "FavoriteListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FavoriteFragment2 extends BaseSimpleFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FavoriteFragment2.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/FragmentFavoriteBinding;", 0))};
    public Map<Integer, View> _$_findViewCache;
    private final FragmentBindingDelegate binding$delegate;

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
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

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public FavoriteFragment2() {
        super(R.layout.fragment_favorite);
        this._$_findViewCache = new LinkedHashMap();
        this.binding$delegate = new FragmentBindingDelegate(FragmentFavoriteBinding.class, this);
    }

    private final FragmentFavoriteBinding getBinding() {
        return (FragmentFavoriteBinding) this.binding$delegate.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleFragment
    public void initData() {
        ArrayList arrayListOf = CollectionsKt.arrayListOf(FavoriteListFragment.Companion.newInstance(0), FavoriteListFragment.Companion.newInstance(1));
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayListOf, new String[]{"WAITING", "WATCHED"});
        getBinding().viewPager.setOffscreenPageLimit(arrayListOf.size());
        getBinding().viewPager.setAdapter(tabLayoutPagerAdapter);
        getBinding().tabLayout.setupWithViewPager(getBinding().viewPager);
    }

    /* compiled from: FavoriteFragment2.kt */
    @Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 o2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001oB\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0014JB\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u0011H\u0002J \u0010(\u001a\u00020\u00062\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\u00110*j\b\u0012\u0004\u0012\u00020\u0011`+H\u0002J\u0016\u0010,\u001a\u00020\u001e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020-H\u0014J\b\u0010.\u001a\u00020\u0006H\u0014J\u0012\u0010/\u001a\u00020\u001e2\b\u00100\u001a\u0004\u0018\u000101H\u0014J\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\u00020-2\u0006\u00103\u001a\u00020\u0003H\u0014J&\u00104\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b05j\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b`6J\"\u00107\u001a\u00020\u001e2\b\u00108\u001a\u0004\u0018\u00010\b2\u0006\u0010'\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u0002H\u0002J(\u0010:\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020\u0011H\u0002J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0>H\u0014J\b\u0010?\u001a\u00020\u001eH\u0002J\u0010\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020EH\u0002J\u0006\u0010F\u001a\u00020\u0006J\b\u0010G\u001a\u00020 H\u0014J\u001a\u0010H\u001a\u00020\u001e2\u0006\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010L\u001a\u00020\u0011H\u0014J\u0010\u0010M\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020OH\u0014J\u0006\u0010P\u001a\u00020\u0006J\b\u0010Q\u001a\u00020\u0006H\u0014JT\u0010R\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010S\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\n\b\u0002\u0010T\u001a\u0004\u0018\u00010U2\n\b\u0002\u0010V\u001a\u0004\u0018\u00010W2\b\b\u0002\u0010X\u001a\u00020\u0006H\u0002J\"\u0010Y\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0010\u0010Z\u001a\u00020\u001e2\u0006\u0010[\u001a\u00020\\H\u0016J\b\u0010]\u001a\u00020^H\u0014J\b\u0010_\u001a\u00020`H\u0014J\u0010\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020cH\u0007J,\u0010d\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\b\b\u0002\u0010X\u001a\u00020\u0006H\u0002J\u000e\u0010e\u001a\u00020\u001e2\u0006\u0010f\u001a\u00020\u0006J\b\u0010g\u001a\u00020\u001eH\u0014J>\u0010h\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010i\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJL\u0010j\u001a\u00020\u001e2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020k0*j\b\u0012\u0004\u0012\u00020k`+2\b\u0010l\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020\u0011H\u0002JJ\u0010m\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010S\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\n\b\u0002\u0010T\u001a\u0004\u0018\u00010U2\n\b\u0002\u0010V\u001a\u0004\u0018\u00010WH\u0002J@\u0010n\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010S\u001a\u00020\u00112\n\b\u0002\u0010T\u001a\u0004\u0018\u00010U2\n\b\u0002\u0010V\u001a\u0004\u0018\u00010WH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment2$FavoriteListFragment;", "Lcom/movieboxpro/android/base/BaseNodeListFragment;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/movieboxpro/android/model/FavoriteResponse;", "()V", "allDefault", "", "boxType", "", "country", "editMode", "gener", "quality", "rating", "sort", "waitingAdded", "<set-?>", "", "watched", "getWatched", "()I", "setWatched", "(I)V", "watched$delegate", "Lkotlin/properties/ReadWriteProperty;", "watchingAdded", "watchingItem", "Lcom/movieboxpro/android/model/WatchingItem;", "year", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseNodeAdapter;", "changeLikeStatus", "like", "currStatus", "id", "season", "episode", "position", "checkShowProgress", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "doSomethingWithData", "", "enableEventBus", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", TtmlNode.TAG_P, "getFilterData", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getLastEpisode", "tid", "favoriteItem", "getPlayPath", "currSeason", "currEpisode", "getServiceData", "Lio/reactivex/Observable;", "getWatchingList", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "haveFilter", "initAdapter", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isEditMode", "isNeedLogin", "markTv", DownloadInfo.DOWNLOAD_OVER, "favoriteSeasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "favoriteEpisodeItem", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "watching", "markWatched", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onRefreshFavorite", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/RefreshFavoriteEvent;", "removeFavorite", "setEditMode", "edit", "setEmptyView", "setFilter", "genre", "showScreenManage", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", NotificationCompat.CATEGORY_MESSAGE, "updateMarkStatus", "updateSeasonStatus", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class FavoriteListFragment extends BaseNodeListFragment<FavoriteItem, FavoriteResponse> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(FavoriteListFragment.class, "watched", "getWatched()I", 0))};
        public static final Companion Companion = new Companion(null);
        private boolean editMode;
        private boolean waitingAdded;
        private boolean watchingAdded;
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private String boxType = "0";
        private final ReadWriteProperty watched$delegate = FragmentArgsKt.arg(this);
        private String sort = "";
        private String gener = "0";
        private String year = "0";
        private String rating = "";
        private String country = "";
        private String quality = "";
        private boolean allDefault = true;
        private final WatchingItem watchingItem = new WatchingItem();

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

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected boolean enableEventBus() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        public void initHolder(BaseViewHolder helper, FavoriteItem favoriteItem) {
            Intrinsics.checkNotNullParameter(helper, "helper");
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected int initItemLayout() {
            return -1;
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected boolean isNeedLogin() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected void setEmptyView() {
        }

        private final int getWatched() {
            return ((Number) this.watched$delegate.getValue(this, $$delegatedProperties[0])).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setWatched(int i) {
            this.watched$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
        }

        /* compiled from: FavoriteFragment2.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FavoriteFragment2$FavoriteListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/FavoriteFragment2$FavoriteListFragment;", "watched", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final FavoriteListFragment newInstance(int i) {
                FavoriteListFragment favoriteListFragment = new FavoriteListFragment();
                favoriteListFragment.setWatched(i);
                return favoriteListFragment;
            }
        }

        public final void setEditMode(boolean z) {
            this.editMode = z;
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }

        public final boolean isEditMode() {
            return this.editMode;
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

        /* JADX INFO: Access modifiers changed from: private */
        public final void getWatchingList() {
            ObservableSource compose = HttpRequest.Companion.post("User_watching_update_count").asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            Observable compose2 = CommonExtKt.request$default(Api.INSTANCE.getCollectList(this.boxType, this.quality, this.sort, this.country, this.year, this.rating, this.gener, 1, 11, 0), null, 1, null).compose(RxUtils.rxTranslate2Bean(FavoriteResponse.class));
            Intrinsics.checkNotNullExpressionValue(compose2, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            Observable zip = Observable.zip(compose, compose2, new BiFunction() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$xFuSLGcrCMBEqq3YzZGxfW6M8vg
                @Override // io.reactivex.functions.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    String m1168getWatchingList$lambda0;
                    m1168getWatchingList$lambda0 = FavoriteFragment2.FavoriteListFragment.m1168getWatchingList$lambda0(FavoriteFragment2.FavoriteListFragment.this, (HashMap) obj, (FavoriteResponse) obj2);
                    return m1168getWatchingList$lambda0;
                }
            });
            Intrinsics.checkNotNullExpressionValue(zip, "zip(\n                upd…         \"\"\n            }");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(zip, this), FavoriteFragment2$FavoriteListFragment$getWatchingList$2.INSTANCE, null, FavoriteFragment2$FavoriteListFragment$getWatchingList$3.INSTANCE, null, new FavoriteFragment2$FavoriteListFragment$getWatchingList$4(this), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getWatchingList$lambda-0  reason: not valid java name */
        public static final String m1168getWatchingList$lambda0(FavoriteListFragment this$0, HashMap t1, FavoriteResponse t2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(t1, "t1");
            Intrinsics.checkNotNullParameter(t2, "t2");
            WatchingItem watchingItem = this$0.watchingItem;
            ArrayList<FavoriteItem> list = t2.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            watchingItem.setList(list);
            Integer num = (Integer) t1.get("watching_upload_count");
            Integer num2 = (Integer) t1.get("waiting_update_count");
            this$0.watchingItem.setWatchingNum(num == null ? 0 : num.intValue());
            this$0.watchingItem.setWaitingNum(num2 != null ? num2.intValue() : 0);
            return "";
        }

        public final HashMap<String, String> getFilterData() {
            return MapsKt.hashMapOf(TuplesKt.to("year", this.year), TuplesKt.to("gener", this.gener), TuplesKt.to("sort", this.sort), TuplesKt.to("rating", this.rating), TuplesKt.to("quality", this.quality), TuplesKt.to("country", this.country), TuplesKt.to("boxType", this.boxType));
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
            startRefresh();
        }

        static /* synthetic */ void removeFavorite$default(FavoriteListFragment favoriteListFragment, String str, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 8) != 0) {
                z = false;
            }
            favoriteListFragment.removeFavorite(str, i, i2, z);
        }

        private final void removeFavorite(String str, int i, int i2, boolean z) {
            RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "User_watch_plan_del").param("mid", str).param("box_type", Integer.valueOf(i)).asMsg(), new FavoriteFragment2$FavoriteListFragment$removeFavorite$1(this), null, new FavoriteFragment2$FavoriteListFragment$removeFavorite$2(this), null, new FavoriteFragment2$FavoriteListFragment$removeFavorite$3(this, z, i2), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected void addOnItemClickViews(BaseNodeAdapter adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.ivMore, R.id.llLike, R.id.llDislike, R.id.llSeason, R.id.clSeason, R.id.ivRight, R.id.ivMark, R.id.ivPoster, R.id.ivExpand);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void markTv$default(FavoriteListFragment favoriteListFragment, String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem, boolean z, int i3, Object obj) {
            favoriteListFragment.markTv(str, str2, str3, i, i2, (i3 & 32) != 0 ? null : favoriteSeasonItem, (i3 & 64) != 0 ? null : favoriteEpisodeItem, (i3 & 128) != 0 ? false : z);
        }

        private final void markTv(String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem, boolean z) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this), new FavoriteFragment2$FavoriteListFragment$markTv$1(this), null, new FavoriteFragment2$FavoriteListFragment$markTv$2(this), null, new FavoriteFragment2$FavoriteListFragment$markTv$3(this, z, str, str2, str3, i, i2, favoriteSeasonItem, favoriteEpisodeItem), 10, null);
        }

        private final void getLastEpisode(String str, int i, FavoriteItem favoriteItem) {
            Observable<R> compose = HttpRequest.Companion.post("User_watched_plan_episode_list").param("tid", str).asRequest().compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, this), null, null, null, null, new FavoriteFragment2$FavoriteListFragment$getLastEpisode$1(favoriteItem, this, i), 15, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateMarkStatus(String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
            int i3;
            int i4;
            int i5;
            boolean z;
            boolean z2;
            int i6;
            boolean z3;
            int i7;
            boolean z4;
            if (favoriteSeasonItem != null) {
                favoriteSeasonItem.getWatchList().clear();
                List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list, "it.episode_list");
                for (FavoriteEpisodeItem favoriteEpisodeItem2 : episode_list) {
                    favoriteSeasonItem.getWatchList().add(Integer.valueOf(i));
                }
                this.mAdapter.notifyItemChanged(i2);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            }
            if (favoriteEpisodeItem == null && favoriteSeasonItem == null) {
                if (i2 == this.mAdapter.getData().size() - 1) {
                    this.mAdapter.getData().remove(i2);
                    this.mAdapter.notifyDataSetChanged();
                    return;
                }
                this.mAdapter.removeAt(i2);
                return;
            }
            if ((!StringsKt.isBlank(str3)) && favoriteEpisodeItem != null) {
                favoriteEpisodeItem.setOver(i);
                this.mAdapter.notifyItemChanged(i2);
                Iterator<BaseNode> it = this.mAdapter.getData().iterator();
                int i8 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i8 = -1;
                        break;
                    }
                    BaseNode next = it.next();
                    if ((next instanceof FavoriteSeasonItem) && ((FavoriteSeasonItem) next).getSeason() == favoriteEpisodeItem.getSeason()) {
                        break;
                    }
                    i8++;
                }
                if (i8 != -1) {
                    FavoriteSeasonItem favoriteSeasonItem2 = (FavoriteSeasonItem) this.mAdapter.getItem(i8);
                    if (i == 1) {
                        favoriteSeasonItem2.setWatchedCount(favoriteSeasonItem2.getWatchedCount() + 1);
                    } else {
                        favoriteSeasonItem2.setWatchedCount(favoriteSeasonItem2.getWatchedCount() - 1);
                    }
                    favoriteSeasonItem2.getWatchList().set(Integer.parseInt(str3) - 1, Integer.valueOf(i));
                    List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem2.getEpisode_list();
                    Intrinsics.checkNotNullExpressionValue(episode_list2, "seasonItem.episode_list");
                    ListIterator<FavoriteEpisodeItem> listIterator = episode_list2.listIterator(episode_list2.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            i7 = -1;
                            break;
                        }
                        if (listIterator.previous().getOver() == 1) {
                            z4 = true;
                            continue;
                        } else {
                            z4 = false;
                            continue;
                        }
                        if (z4) {
                            i7 = listIterator.nextIndex();
                            break;
                        }
                    }
                    if (i7 != -1) {
                        FavoriteEpisodeItem favoriteEpisodeItem3 = favoriteSeasonItem2.getEpisode_list().get(i7);
                        favoriteSeasonItem2.setLastStartWatchedItem(favoriteEpisodeItem3);
                        favoriteSeasonItem2.setLastWatchedItem(favoriteEpisodeItem3);
                        favoriteSeasonItem2.setLastWatchedIndex(i7);
                        if (i7 >= 0) {
                            while (true) {
                                int i9 = i7 - 1;
                                if (favoriteSeasonItem2.getEpisode_list().get(i7).getOver() != 1) {
                                    break;
                                }
                                favoriteSeasonItem2.setLastStartWatchedItem(favoriteSeasonItem2.getEpisode_list().get(i7));
                                if (i9 < 0) {
                                    break;
                                }
                                i7 = i9;
                            }
                        }
                    } else {
                        favoriteSeasonItem2.setLastWatchedItem(null);
                        favoriteSeasonItem2.setLastStartWatchedItem(null);
                        favoriteSeasonItem2.setLastWatchedIndex(0);
                    }
                    this.mAdapter.notifyItemChanged(i8);
                }
                Iterator<BaseNode> it2 = this.mAdapter.getData().iterator();
                int i10 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i10 = -1;
                        break;
                    }
                    BaseNode next2 = it2.next();
                    if (next2 instanceof FavoriteItem ? Intrinsics.areEqual(((FavoriteItem) next2).getId(), favoriteEpisodeItem.getId()) : false) {
                        break;
                    }
                    i10++;
                }
                if (i10 != -1) {
                    FavoriteItem favoriteItem = (FavoriteItem) this.mAdapter.getItem(i10);
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    favoriteItem.setWatchedEpisodeCount(0);
                    List<FavoriteSeasonItem> episode_progress_list = favoriteItem.getEpisode_progress_list();
                    Intrinsics.checkNotNullExpressionValue(episode_progress_list, "item.episode_progress_list");
                    for (FavoriteSeasonItem favoriteSeasonItem3 : episode_progress_list) {
                        List<FavoriteEpisodeItem> episode_list3 = favoriteSeasonItem3.getEpisode_list();
                        Intrinsics.checkNotNullExpressionValue(episode_list3, "it.episode_list");
                        for (FavoriteEpisodeItem favoriteEpisodeItem4 : episode_list3) {
                            if (favoriteEpisodeItem4.getOver() == 1) {
                                favoriteItem.setWatchedEpisodeCount(favoriteItem.getWatchedEpisodeCount() + 1);
                                arrayList.add(1);
                            } else {
                                arrayList.add(0);
                            }
                        }
                    }
                    favoriteItem.setEpisodes(arrayList);
                    List<FavoriteEpisodeItem> allEpisodes = favoriteItem.getAllEpisodes();
                    Intrinsics.checkNotNullExpressionValue(allEpisodes, "item.allEpisodes");
                    ListIterator<FavoriteEpisodeItem> listIterator2 = allEpisodes.listIterator(allEpisodes.size());
                    while (true) {
                        if (!listIterator2.hasPrevious()) {
                            i6 = -1;
                            break;
                        }
                        if (listIterator2.previous().getOver() == 1) {
                            z3 = true;
                            continue;
                        } else {
                            z3 = false;
                            continue;
                        }
                        if (z3) {
                            i6 = listIterator2.nextIndex();
                            break;
                        }
                    }
                    if (i6 != -1) {
                        favoriteItem.setLastWatchedCount(0);
                        FavoriteEpisodeItem favoriteEpisodeItem5 = favoriteItem.getAllEpisodes().get(i6);
                        favoriteItem.setLastStartWatchedItem(favoriteEpisodeItem5);
                        favoriteItem.setLastWatchedItem(favoriteEpisodeItem5);
                        favoriteItem.setLastWatchedIndex(i6);
                        List<FavoriteEpisodeItem> allEpisodes2 = favoriteItem.getAllEpisodes();
                        Intrinsics.checkNotNullExpressionValue(allEpisodes2, "item.allEpisodes");
                        for (FavoriteEpisodeItem favoriteEpisodeItem6 : allEpisodes2) {
                            if (favoriteEpisodeItem6.getSeason() < favoriteEpisodeItem5.getSeason()) {
                                favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                            } else if (favoriteEpisodeItem6.getSeason() == favoriteEpisodeItem5.getSeason() && favoriteEpisodeItem6.getEpisode() <= favoriteEpisodeItem5.getEpisode()) {
                                favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                            }
                        }
                        if (i6 >= 0) {
                            while (true) {
                                int i11 = i6 - 1;
                                if (favoriteItem.getAllEpisodes().get(i6).getOver() != 1) {
                                    break;
                                }
                                favoriteItem.setLastStartWatchedItem(favoriteItem.getAllEpisodes().get(i6));
                                if (i11 < 0) {
                                    break;
                                }
                                i6 = i11;
                            }
                        }
                    } else {
                        favoriteItem.setWatchedEpisodeCount(0);
                        favoriteItem.setLastWatchedItem(null);
                        favoriteItem.setLastStartWatchedItem(null);
                        favoriteItem.setLastWatchedCount(0);
                    }
                    this.mAdapter.notifyItemChanged(i10);
                    getLastEpisode(favoriteItem.getId(), i10, favoriteItem);
                }
                Unit unit3 = Unit.INSTANCE;
                Unit unit4 = Unit.INSTANCE;
            }
            if (!(!StringsKt.isBlank(str2)) || favoriteSeasonItem == null) {
                return;
            }
            if (i == 1) {
                favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getEpisode_list().size());
            } else {
                favoriteSeasonItem.setWatchedCount(0);
            }
            List<FavoriteEpisodeItem> episode_list4 = favoriteSeasonItem.getEpisode_list();
            if (episode_list4 != null) {
                for (FavoriteEpisodeItem favoriteEpisodeItem7 : episode_list4) {
                    favoriteEpisodeItem7.setOver(i);
                }
                Unit unit5 = Unit.INSTANCE;
            }
            List<FavoriteEpisodeItem> episode_list5 = favoriteSeasonItem.getEpisode_list();
            Intrinsics.checkNotNullExpressionValue(episode_list5, "it.episode_list");
            ListIterator<FavoriteEpisodeItem> listIterator3 = episode_list5.listIterator(episode_list5.size());
            while (true) {
                if (!listIterator3.hasPrevious()) {
                    i3 = -1;
                    break;
                }
                if (listIterator3.previous().getOver() == 1) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    i3 = listIterator3.nextIndex();
                    break;
                }
            }
            if (i3 != -1) {
                FavoriteEpisodeItem favoriteEpisodeItem8 = favoriteSeasonItem.getEpisode_list().get(i3);
                favoriteSeasonItem.setLastStartWatchedItem(favoriteEpisodeItem8);
                favoriteSeasonItem.setLastWatchedItem(favoriteEpisodeItem8);
                favoriteSeasonItem.setLastWatchedIndex(i3);
                if (i3 >= 0) {
                    while (true) {
                        int i12 = i3 - 1;
                        if (favoriteSeasonItem.getEpisode_list().get(i3).getOver() != 1) {
                            break;
                        }
                        favoriteSeasonItem.setLastStartWatchedItem(favoriteSeasonItem.getEpisode_list().get(i3));
                        if (i12 < 0) {
                            break;
                        }
                        i3 = i12;
                    }
                }
            } else {
                favoriteSeasonItem.setLastWatchedItem(null);
                favoriteSeasonItem.setLastStartWatchedItem(null);
                favoriteSeasonItem.setLastWatchedIndex(0);
            }
            int i13 = 0;
            for (Object obj : this.mAdapter.getData()) {
                int i14 = i13 + 1;
                if (i13 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                BaseNode baseNode = (BaseNode) obj;
                if (baseNode instanceof FavoriteEpisodeItem) {
                    FavoriteEpisodeItem favoriteEpisodeItem9 = (FavoriteEpisodeItem) baseNode;
                    if (Intrinsics.areEqual(favoriteEpisodeItem9.getId(), favoriteSeasonItem.getId()) && favoriteEpisodeItem9.getSeason() == favoriteSeasonItem.getSeason()) {
                        favoriteEpisodeItem9.setOver(i);
                        this.mAdapter.notifyItemChanged(i13);
                    }
                }
                i13 = i14;
            }
            int findParentNode = this.mAdapter.findParentNode(i2);
            FavoriteItem favoriteItem2 = (FavoriteItem) this.mAdapter.getItem(findParentNode);
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            favoriteItem2.setWatchedEpisodeCount(0);
            List<FavoriteSeasonItem> episode_progress_list2 = favoriteItem2.getEpisode_progress_list();
            Intrinsics.checkNotNullExpressionValue(episode_progress_list2, "item.episode_progress_list");
            for (FavoriteSeasonItem favoriteSeasonItem4 : episode_progress_list2) {
                List<FavoriteEpisodeItem> episode_list6 = favoriteSeasonItem4.getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list6, "it.episode_list");
                for (FavoriteEpisodeItem favoriteEpisodeItem10 : episode_list6) {
                    if (favoriteEpisodeItem10.getOver() == 1) {
                        favoriteItem2.setWatchedEpisodeCount(favoriteItem2.getWatchedEpisodeCount() + 1);
                        arrayList2.add(1);
                    } else {
                        arrayList2.add(0);
                    }
                }
            }
            List<FavoriteEpisodeItem> allEpisodes3 = favoriteItem2.getAllEpisodes();
            Intrinsics.checkNotNullExpressionValue(allEpisodes3, "item.allEpisodes");
            ListIterator<FavoriteEpisodeItem> listIterator4 = allEpisodes3.listIterator(allEpisodes3.size());
            while (true) {
                if (!listIterator4.hasPrevious()) {
                    i4 = -1;
                    i5 = -1;
                    break;
                }
                if (listIterator4.previous().getOver() == 1) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    i5 = listIterator4.nextIndex();
                    i4 = -1;
                    break;
                }
            }
            if (i5 != i4) {
                favoriteItem2.setLastWatchedCount(0);
                FavoriteEpisodeItem favoriteEpisodeItem11 = favoriteItem2.getAllEpisodes().get(i5);
                favoriteItem2.setLastStartWatchedItem(favoriteEpisodeItem11);
                favoriteItem2.setLastWatchedItem(favoriteEpisodeItem11);
                favoriteItem2.setLastWatchedIndex(i5);
                List<FavoriteEpisodeItem> allEpisodes4 = favoriteItem2.getAllEpisodes();
                Intrinsics.checkNotNullExpressionValue(allEpisodes4, "item.allEpisodes");
                for (FavoriteEpisodeItem favoriteEpisodeItem12 : allEpisodes4) {
                    if (favoriteEpisodeItem12.getSeason() < favoriteEpisodeItem11.getSeason()) {
                        favoriteItem2.setLastWatchedCount(favoriteItem2.getLastWatchedCount() + 1);
                    } else if (favoriteEpisodeItem12.getSeason() == favoriteEpisodeItem11.getSeason() && favoriteEpisodeItem12.getEpisode() <= favoriteEpisodeItem11.getEpisode()) {
                        favoriteItem2.setLastWatchedCount(favoriteItem2.getLastWatchedCount() + 1);
                    }
                }
                if (i5 >= 0) {
                    while (true) {
                        int i15 = i5 - 1;
                        if (favoriteItem2.getAllEpisodes().get(i5).getOver() != 1) {
                            break;
                        }
                        favoriteItem2.setLastStartWatchedItem(favoriteItem2.getAllEpisodes().get(i5));
                        if (i15 < 0) {
                            break;
                        }
                        i5 = i15;
                    }
                }
            } else {
                favoriteItem2.setWatchedEpisodeCount(0);
                favoriteItem2.setLastWatchedItem(null);
                favoriteItem2.setLastStartWatchedItem(null);
                favoriteItem2.setLastWatchedCount(0);
            }
            favoriteItem2.setEpisodes(arrayList2);
            this.mAdapter.notifyItemChanged(findParentNode);
            this.mAdapter.notifyItemChanged(i2);
            getLastEpisode(favoriteItem2.getId(), findParentNode, favoriteItem2);
            Unit unit6 = Unit.INSTANCE;
            Unit unit7 = Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markWatched(String str, int i, int i2) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", Integer.valueOf(i)).param("mid", str).param("watched", Integer.valueOf(i2)).asRequest(), this), new FavoriteFragment2$FavoriteListFragment$markWatched$1(this), null, new FavoriteFragment2$FavoriteListFragment$markWatched$2(this), null, new FavoriteFragment2$FavoriteListFragment$markWatched$3(this), 10, null);
        }

        private final void changeLikeStatus(boolean z, int i, String str, int i2, int i3, int i4, int i5) {
            int i6;
            if (!z) {
                if (i == 0 || i == 1) {
                    i6 = 2;
                }
                i6 = 0;
            } else if (i != 0) {
                if (i != 1) {
                    i6 = i == 2 ? 1 : 0;
                }
                i6 = 0;
            } else {
                i6 = 1;
            }
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Movie_like").param("box_type", Integer.valueOf((i2 != 2 || i3 == 0) ? i2 : 3)).param("mid", str).param("season", Integer.valueOf(i3)).param("episode", Integer.valueOf(i4)).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i6)).asRequest(), this), new FavoriteFragment2$FavoriteListFragment$changeLikeStatus$1(this), null, new FavoriteFragment2$FavoriteListFragment$changeLikeStatus$2(this), null, new FavoriteFragment2$FavoriteListFragment$changeLikeStatus$3(this, i5, i2, i3, i6, z), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$yyw35J1yprRJouQe5aHgBQx0K_c
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    FavoriteFragment2.FavoriteListFragment.m1174onItemClick$lambda19(FavoriteFragment2.FavoriteListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-19  reason: not valid java name */
        public static final void m1174onItemClick$lambda19(FavoriteListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            BaseNode item = this$0.mAdapter.getItem(i);
            if (item instanceof FavoriteSeasonItem) {
                FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
                markTv$default(this$0, favoriteSeasonItem.getId(), String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, favoriteSeasonItem, null, false, 192, null);
            } else if (item instanceof FavoriteEpisodeItem) {
                FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
                markTv$default(this$0, favoriteEpisodeItem.getId(), String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, null, favoriteEpisodeItem, false, Opcodes.IF_ICMPNE, null);
            } else if (item instanceof FavoriteItem) {
                FavoriteItem favoriteItem = (FavoriteItem) item;
                if (favoriteItem.getBox_type() == 1) {
                    MovieDetailActivity.start(this$0.getContext(), favoriteItem.getId(), favoriteItem.getPoster());
                } else {
                    TvDetailActivity.start(this$0.getContext(), favoriteItem.getId(), favoriteItem.getBanner_mini(), favoriteItem.getPoster());
                }
            }
        }

        private final void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, final String str2, final int i, final int i2, final int i3) {
            ScreenManageDialog newInstance$default = ScreenManageDialog.Companion.newInstance$default(ScreenManageDialog.Companion, arrayList, str, false, 4, null);
            newInstance$default.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$showScreenManage$1
                @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                public void onStopDevice() {
                    FavoriteFragment2.FavoriteListFragment favoriteListFragment = FavoriteFragment2.FavoriteListFragment.this;
                    String str3 = str2;
                    if (str3 == null) {
                        str3 = "";
                    }
                    favoriteListFragment.getPlayPath(str3, i, i2, i3);
                }
            });
            newInstance$default.show(getChildFragmentManager(), ScreenManageDialog.class.getSimpleName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getPlayPath(final String str, final int i, final int i2, final int i3) {
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 1;
            Observable flatMap = Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), i, i2, i3, Build.BRAND, Build.MODEL).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$6DTe0HekU4cRlS8LusgPiShPcvA
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m1162getPlayPath$lambda20;
                    m1162getPlayPath$lambda20 = FavoriteFragment2.FavoriteListFragment.m1162getPlayPath$lambda20(FavoriteFragment2.FavoriteListFragment.this, str, i, i2, i3, (String) obj);
                    return m1162getPlayPath$lambda20;
                }
            }).onErrorResumeNext($$Lambda$FavoriteFragment2$FavoriteListFragment$cfic4nplIBHuTcvPsOewGUBj_e4.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$zdlDEKCFTWk84M9nbSISchiHU0s
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m1164getPlayPath$lambda25;
                    m1164getPlayPath$lambda25 = FavoriteFragment2.FavoriteListFragment.m1164getPlayPath$lambda25(i, str, intRef, i3, i2, (String) obj);
                    return m1164getPlayPath$lambda25;
                }
            });
            Intrinsics.checkNotNullExpressionValue(flatMap, "getService().playingFeed…      }\n                }");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(flatMap, this), new FavoriteFragment2$FavoriteListFragment$getPlayPath$4(this), null, new FavoriteFragment2$FavoriteListFragment$getPlayPath$5(this), FavoriteFragment2$FavoriteListFragment$getPlayPath$6.INSTANCE, new FavoriteFragment2$FavoriteListFragment$getPlayPath$7(this, intRef), 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-20  reason: not valid java name */
        public static final ObservableSource m1162getPlayPath$lambda20(FavoriteListFragment this$0, String id, int i, int i2, int i3, String it) {
            Observable just;
            List<DeviceModelResponse.DeviceModel> list;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(it, "it");
            Object parseObject = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
            Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(\n           …va)\n                    )");
            BaseResponse baseResponse = (BaseResponse) parseObject;
            if (baseResponse.getCode() == -88) {
                this$0.showLoadingView();
                List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
                if (device_list != null) {
                    list = device_list;
                } else {
                    list = new ArrayList();
                }
                this$0.showScreenManage(new ArrayList<>(list), baseResponse.getMsg(), id, i, i2, i3);
                just = Observable.empty();
            } else {
                just = Observable.just("");
            }
            return just;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-21  reason: not valid java name */
        public static final Observable m1163getPlayPath$lambda21(Throwable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Observable.just("");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
        /* renamed from: getPlayPath$lambda-25  reason: not valid java name */
        public static final ObservableSource m1164getPlayPath$lambda25(final int i, final String id, final Ref.IntRef position, final int i2, final int i3, String it) {
            Observable compose;
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(position, "$position");
            Intrinsics.checkNotNullParameter(it, "it");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (TextUtils.isEmpty((CharSequence) objectRef2.element)) {
                objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            } else if (StringsKt.equals("0", (String) objectRef2.element, true)) {
                objectRef.element = "";
                objectRef2.element = "";
            }
            if (i == 1) {
                compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, (String) objectRef.element, (String) objectRef2.element).compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            } else {
                compose = Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            }
            return compose.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$aEPtO28LeBFb8I5W_dQKFXOlPP8
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m1165getPlayPath$lambda25$lambda24;
                    m1165getPlayPath$lambda25$lambda24 = FavoriteFragment2.FavoriteListFragment.m1165getPlayPath$lambda25$lambda24(Ref.ObjectRef.this, objectRef2, position, i2, i3, id, i, (BaseMediaModel) obj);
                    return m1165getPlayPath$lambda25$lambda24;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-25$lambda-24  reason: not valid java name */
        public static final ObservableSource m1165getPlayPath$lambda25$lambda24(Ref.ObjectRef oss, Ref.ObjectRef groupID, Ref.IntRef position, final int i, final int i2, final String id, final int i3, final BaseMediaModel model) {
            Observable map;
            Intrinsics.checkNotNullParameter(oss, "$oss");
            Intrinsics.checkNotNullParameter(groupID, "$groupID");
            Intrinsics.checkNotNullParameter(position, "$position");
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "model");
            if (!(model instanceof MovieDetail)) {
                TvDetail tvDetail = (TvDetail) model;
                position.element = i;
                map = Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(i2), String.valueOf(i), (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$Ns5moSuoUovDovDkMqlkf7y3_Fc
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        Pair m1167getPlayPath$lambda25$lambda24$lambda23;
                        m1167getPlayPath$lambda25$lambda24$lambda23 = FavoriteFragment2.FavoriteListFragment.m1167getPlayPath$lambda25$lambda24$lambda23(id, i2, i, model, (BaseMediaModel) obj);
                        return m1167getPlayPath$lambda25$lambda24$lambda23;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "getService().TV_download…                        }");
            } else {
                Observable<R> compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, App.deviceLang, (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                map = compose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$6ACpyTNFNuTOFW7EioqdyC1k4Hw
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        Pair m1166getPlayPath$lambda25$lambda24$lambda22;
                        m1166getPlayPath$lambda25$lambda24$lambda22 = FavoriteFragment2.FavoriteListFragment.m1166getPlayPath$lambda25$lambda24$lambda22(id, i3, model, (BaseMediaModel) obj);
                        return m1166getPlayPath$lambda25$lambda24$lambda22;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "getService().Movie_detai…                        }");
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-25$lambda-24$lambda-22  reason: not valid java name */
        public static final Pair m1166getPlayPath$lambda25$lambda24$lambda22(String id, int i, BaseMediaModel model, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(it, "it");
            Download findByIdAndType = App.getDB().downloadDao().findByIdAndType(id, i, 2);
            if (findByIdAndType != null) {
                findByIdAndType.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByIdAndType.getPath(), findByIdAndType.getTitle(), Constant.DIR_DOWNLOAD)));
                if (new File(findByIdAndType.getPath()).exists()) {
                    BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByIdAndType), BaseMediaModel.DownloadFile.class);
                    downloadFile.real_quality = findByIdAndType.getQuality();
                    it.list.add(0, downloadFile);
                }
            }
            return new Pair(model, it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-25$lambda-24$lambda-23  reason: not valid java name */
        public static final Pair m1167getPlayPath$lambda25$lambda24$lambda23(String id, int i, int i2, BaseMediaModel model, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(it, "it");
            Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(id, i, i2);
            if (findByTidAndSeasonEpisode != null && findByTidAndSeasonEpisode.getStatue() == 2) {
                findByTidAndSeasonEpisode.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByTidAndSeasonEpisode.getPath(), findByTidAndSeasonEpisode.getTitle(), Constant.DIR_DOWNLOAD)));
                if (new File(findByTidAndSeasonEpisode.getPath()).exists()) {
                    BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByTidAndSeasonEpisode), BaseMediaModel.DownloadFile.class);
                    downloadFile.real_quality = findByTidAndSeasonEpisode.getQuality();
                    it.list.add(0, downloadFile);
                }
            }
            return new Pair(model, it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void goTvPlayer(TvDetail tvDetail) {
            if (X86HintUtils.checkX86(getContext())) {
                return;
            }
            Context context = getContext();
            CastSession castSession = null;
            if (context != null) {
                try {
                    castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    castSession = null;
                }
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(tvDetail);
                Context context2 = getContext();
                if (context2 == null) {
                    return;
                }
                ChooseActivity.Companion.start(context2, false, playerStrategy);
                return;
            }
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            List<TvDetail.SeasonDetail> list = tvDetail.episode;
            Intrinsics.checkNotNullExpressionValue(list, "tvDetail.episode");
            for (TvDetail.SeasonDetail seasonDetail : list) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                Long l = seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER);
                long j = 0;
                tvSeasonList.setOver(l == null ? 0L : l.longValue());
                Long l2 = seasonDetail.play_progress.get("seconds");
                if (l2 != null) {
                    j = l2.longValue();
                }
                tvSeasonList.setSeconds(j);
                tvSeasonList.setTid(seasonDetail.tid);
                tvSeasonList.setId(seasonDetail.id);
                arrayList.add(tvSeasonList);
            }
            Bundle build = ParamsUtils.newBuilder().addParam(TvDetailActivity.SEASON_TV_LIST, arrayList).addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).addParam("FeaturedFragment", false).build();
            if (PopPlayerManager.Companion.getInstance().isPopShow()) {
                PopPlayerManager.Companion.getInstance().setNewPlay(build, tvDetail);
                return;
            }
            Intent intent = new Intent(getContext(), TvPlayerActivity.class);
            intent.putExtras(build);
            startActivity(intent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void goMoviePlayer(MovieDetail movieDetail) {
            Context context = getContext();
            CastSession castSession = null;
            if (context != null) {
                try {
                    castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    castSession = null;
                }
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(movieDetail);
                Context context2 = getContext();
                if (context2 == null) {
                    return;
                }
                ChooseActivity.Companion.start(context2, false, playerStrategy);
                return;
            }
            Context context3 = getContext();
            if (PopPlayerManager.Companion.getInstance().isPopShow()) {
                PopPlayerManager.Companion.getInstance().setNewPlay(movieDetail);
            } else {
                MoviePlayerActivity.start(context3, movieDetail, movieDetail.id);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateSeasonStatus(int i, String str, String str2, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
            FavoriteItem favoriteItem = (FavoriteItem) this.mAdapter.getItem(i);
            favoriteItem.setExpanded(false);
            if (favoriteSeasonItem != null) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                favoriteItem.setWatchedEpisodeCount(0);
                List<FavoriteSeasonItem> episode_progress_list = favoriteItem.getEpisode_progress_list();
                Intrinsics.checkNotNullExpressionValue(episode_progress_list, "item.episode_progress_list");
                for (FavoriteSeasonItem favoriteSeasonItem2 : episode_progress_list) {
                    List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem2.getEpisode_list();
                    Intrinsics.checkNotNullExpressionValue(episode_list, "it.episode_list");
                    for (FavoriteEpisodeItem favoriteEpisodeItem2 : episode_list) {
                        if (favoriteEpisodeItem2.getOver() == 1) {
                            favoriteItem.setWatchedEpisodeCount(favoriteItem.getWatchedEpisodeCount() + 1);
                            arrayList.add(1);
                        } else {
                            arrayList.add(0);
                        }
                    }
                }
                favoriteItem.setEpisodes(arrayList);
            }
            if (favoriteEpisodeItem != null) {
                ArrayList arrayList2 = new ArrayList();
                favoriteItem.setWatchedEpisodeCount(0);
                List<FavoriteSeasonItem> episode_progress_list2 = favoriteItem.getEpisode_progress_list();
                Intrinsics.checkNotNullExpressionValue(episode_progress_list2, "item.episode_progress_list");
                for (FavoriteSeasonItem favoriteSeasonItem3 : episode_progress_list2) {
                    List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem3.getEpisode_list();
                    Intrinsics.checkNotNullExpressionValue(episode_list2, "it.episode_list");
                    for (FavoriteEpisodeItem favoriteEpisodeItem3 : episode_list2) {
                        if (favoriteEpisodeItem3.getOver() == 1) {
                            favoriteItem.setWatchedEpisodeCount(favoriteItem.getWatchedEpisodeCount() + 1);
                            arrayList2.add(1);
                        } else {
                            arrayList2.add(0);
                        }
                    }
                }
            }
            this.mAdapter.notifyItemChanged(i);
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$TLjyXBv0nk_C-hZ3SwKpCHfDLu4
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    FavoriteFragment2.FavoriteListFragment.m1172onItemChildClick$lambda40(FavoriteFragment2.FavoriteListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-40  reason: not valid java name */
        public static final void m1172onItemChildClick$lambda40(final FavoriteListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            String str;
            String str2;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final BaseNode itemOrNull = this$0.mAdapter.getItemOrNull(i);
            if (itemOrNull == null) {
                return;
            }
            int i2 = 1;
            if (itemOrNull instanceof FavoriteItem) {
                switch (view.getId()) {
                    case R.id.clSeason /* 2131296537 */:
                    case R.id.llSeason /* 2131297217 */:
                        if (CommonUtils.isTablet()) {
                            FavoriteItem favoriteItem = (FavoriteItem) itemOrNull;
                            SeasonExpandDialog newInstance = SeasonExpandDialog.Companion.newInstance(favoriteItem.getId());
                            newInstance.setListener(new SeasonExpandDialog.OnMarkListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$1
                                @Override // com.movieboxpro.android.view.dialog.SeasonExpandDialog.OnMarkListener
                                public void onRefresh() {
                                    BaseNodeAdapter baseNodeAdapter;
                                    baseNodeAdapter = FavoriteFragment2.FavoriteListFragment.this.mAdapter;
                                    baseNodeAdapter.notifyItemChanged(i);
                                }

                                @Override // com.movieboxpro.android.view.dialog.SeasonExpandDialog.OnMarkListener
                                public void onMark(String str3, String season, String episode, int i3, int i4, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
                                    Intrinsics.checkNotNullParameter(season, "season");
                                    Intrinsics.checkNotNullParameter(episode, "episode");
                                    FavoriteFragment2.FavoriteListFragment.this.updateSeasonStatus(i, season, episode, i3, favoriteSeasonItem, favoriteEpisodeItem);
                                }
                            });
                            newInstance.setData(favoriteItem);
                            newInstance.show(this$0.getChildFragmentManager(), SeasonExpandDialog.class.getSimpleName());
                            return;
                        }
                        this$0.mAdapter.expandOrCollapse(i, true, true, 110);
                        this$0.mAdapter.notifyItemChanged(i);
                        return;
                    case R.id.ivExpand /* 2131296954 */:
                        if (CommonUtils.isTablet()) {
                            FavoriteItem favoriteItem2 = (FavoriteItem) itemOrNull;
                            SeasonExpandDialog newInstance2 = SeasonExpandDialog.Companion.newInstance(favoriteItem2.getId());
                            newInstance2.setData(favoriteItem2);
                            newInstance2.setListener(new SeasonExpandDialog.OnMarkListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$14
                                @Override // com.movieboxpro.android.view.dialog.SeasonExpandDialog.OnMarkListener
                                public void onRefresh() {
                                    BaseNodeAdapter baseNodeAdapter;
                                    ((FavoriteItem) BaseNode.this).setExpanded(false);
                                    baseNodeAdapter = this$0.mAdapter;
                                    baseNodeAdapter.notifyItemChanged(i);
                                }

                                @Override // com.movieboxpro.android.view.dialog.SeasonExpandDialog.OnMarkListener
                                public void onMark(String str3, String season, String episode, int i3, int i4, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
                                    Intrinsics.checkNotNullParameter(season, "season");
                                    Intrinsics.checkNotNullParameter(episode, "episode");
                                    this$0.updateSeasonStatus(i, season, episode, i3, favoriteSeasonItem, favoriteEpisodeItem);
                                }
                            });
                            newInstance2.show(this$0.getChildFragmentManager(), SeasonExpandDialog.class.getSimpleName());
                            return;
                        }
                        this$0.mAdapter.expandOrCollapse(i, true, true, 110);
                        this$0.mAdapter.notifyItemChanged(i);
                        return;
                    case R.id.ivMore /* 2131296970 */:
                        ArrayList<Pair> arrayList = new ArrayList();
                        FavoriteItem favoriteItem3 = (FavoriteItem) itemOrNull;
                        if (favoriteItem3.getBox_type() == 1) {
                            if (this$0.getWatched() == 1) {
                                arrayList.add(new Pair("ADD THIS MOVIE TO WATCHING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$2
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 1, 0);
                                    }
                                }));
                            } else {
                                arrayList.add(new Pair("ADD THIS MOVIE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$3
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 1, 1);
                                    }
                                }));
                                arrayList.add(new Pair("ADD THIS MOVIE TO WAITING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$4
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 1, 0);
                                    }
                                }));
                            }
                        } else if (this$0.getWatched() == 1) {
                            if (favoriteItem3.getLast_episode() != null) {
                                arrayList.add(new Pair("ADD CURRENT EPISODE TO WAITING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$5
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.markTv$default(FavoriteFragment2.FavoriteListFragment.this, ((FavoriteItem) itemOrNull).getId(), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getSeason()), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getEpisode()), 0, i, null, null, false, 224, null);
                                    }
                                }));
                                arrayList.add(new Pair("ADD CURRENT SEASON TO WAITING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$6
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.markTv$default(FavoriteFragment2.FavoriteListFragment.this, ((FavoriteItem) itemOrNull).getId(), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getSeason()), "", 0, i, null, null, false, 224, null);
                                    }
                                }));
                            }
                            arrayList.add(new Pair("ADD THIS TV SHOW TO WATCHING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$7
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i3) {
                                    FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 2, 0);
                                }
                            }));
                            arrayList.add(new Pair("ADD THIS TV SHOW TO WATCHING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$8
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i3) {
                                    FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 2, 0);
                                }
                            }));
                        } else {
                            if (favoriteItem3.getLast_episode() != null) {
                                arrayList.add(new Pair("ADD CURRENT EPISODE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$9
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.markTv$default(FavoriteFragment2.FavoriteListFragment.this, ((FavoriteItem) itemOrNull).getId(), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getSeason()), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getEpisode()), 1, i, null, null, false, 224, null);
                                    }
                                }));
                                arrayList.add(new Pair("ADD CURRENT SEASON TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$10
                                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                    public void onClick(int i3) {
                                        FavoriteFragment2.FavoriteListFragment.markTv$default(FavoriteFragment2.FavoriteListFragment.this, ((FavoriteItem) itemOrNull).getId(), String.valueOf(((FavoriteItem) itemOrNull).getLast_episode().getSeason()), "", 1, i, null, null, false, 224, null);
                                    }
                                }));
                            }
                            arrayList.add(new Pair("ADD THIS TV SHOW TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.FavoriteFragment2$FavoriteListFragment$onItemChildClick$1$11
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i3) {
                                    FavoriteFragment2.FavoriteListFragment.this.markWatched(((FavoriteItem) itemOrNull).getId(), 2, 1);
                                }
                            }));
                        }
                        ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                        for (Pair pair : arrayList) {
                            canceledOnTouchOutside.addSheetItem((String) pair.getFirst(), ActionSheetDialog.SheetItemColor.White, (ActionSheetDialog.OnSheetItemClickListener) pair.getSecond());
                        }
                        int box_type = favoriteItem3.getBox_type();
                        if (box_type == 1) {
                            str = this$0.getWatched() != 1 ? "WAITING" : "WATCHED";
                            str2 = "REMOVE THIS MOVIE FROM ";
                        } else {
                            str = this$0.getWatched() != 1 ? "WAITING" : "WATCHED";
                            str2 = "REMOVE THIS TV SHOW FROM ";
                        }
                        canceledOnTouchOutside.addSheetItem(Intrinsics.stringPlus(str2, str), ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$jkOShQjdKQUPxCHI5qDV1WwxyH0
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public final void onClick(int i3) {
                                FavoriteFragment2.FavoriteListFragment.m1173onItemChildClick$lambda40$lambda39(FavoriteFragment2.FavoriteListFragment.this, itemOrNull, i, i3);
                            }
                        }).show();
                        return;
                    case R.id.ivPoster /* 2131296983 */:
                        if (this$0.getWatched() == 1) {
                            FavoriteItem favoriteItem4 = (FavoriteItem) itemOrNull;
                            if (favoriteItem4.getBox_type() == 1) {
                                MovieDetailActivity.start(this$0.getContext(), favoriteItem4.getId(), favoriteItem4.getPoster());
                                return;
                            } else {
                                TvDetailActivity.start(this$0.getContext(), favoriteItem4.getId(), favoriteItem4.getBanner_mini(), favoriteItem4.getPoster());
                                return;
                            }
                        }
                        FavoriteItem favoriteItem5 = (FavoriteItem) itemOrNull;
                        int season = (favoriteItem5.getBox_type() != 2 || favoriteItem5.getLast_episode() == null) ? 1 : favoriteItem5.getLast_episode().getSeason();
                        if (favoriteItem5.getBox_type() == 2 && favoriteItem5.getLast_episode() != null) {
                            i2 = favoriteItem5.getLast_episode().getEpisode();
                        }
                        String id = favoriteItem5.getId();
                        Intrinsics.checkNotNullExpressionValue(id, "item.id");
                        this$0.getPlayPath(id, favoriteItem5.getBox_type(), season, i2);
                        return;
                    case R.id.ivRight /* 2131296992 */:
                        FavoriteItem favoriteItem6 = (FavoriteItem) itemOrNull;
                        if (favoriteItem6.getLast_episode() == null) {
                            this$0.markWatched(favoriteItem6.getId(), favoriteItem6.getBox_type(), this$0.getWatched() != 1 ? 1 : 0);
                            return;
                        } else {
                            markTv$default(this$0, favoriteItem6.getId(), String.valueOf(favoriteItem6.getLast_episode().getSeason()), String.valueOf(favoriteItem6.getLast_episode().getEpisode()), 1, i, null, null, false, 128, null);
                            return;
                        }
                    case R.id.llDislike /* 2131297175 */:
                        FavoriteItem favoriteItem7 = (FavoriteItem) itemOrNull;
                        if (favoriteItem7.getBox_type() == 1) {
                            int like_status = favoriteItem7.getLike_status();
                            String id2 = favoriteItem7.getId();
                            int box_type2 = favoriteItem7.getBox_type();
                            FavoriteItem.LastEpisode last_episode = favoriteItem7.getLast_episode();
                            int season2 = last_episode == null ? 0 : last_episode.getSeason();
                            FavoriteItem.LastEpisode last_episode2 = favoriteItem7.getLast_episode();
                            this$0.changeLikeStatus(false, like_status, id2, box_type2, season2, last_episode2 == null ? 0 : last_episode2.getEpisode(), i);
                            return;
                        } else if (favoriteItem7.getLast_episode() == null) {
                            int like_status2 = favoriteItem7.getLike_status();
                            String id3 = favoriteItem7.getId();
                            int box_type3 = favoriteItem7.getBox_type();
                            FavoriteItem.LastEpisode last_episode3 = favoriteItem7.getLast_episode();
                            int season3 = last_episode3 == null ? 0 : last_episode3.getSeason();
                            FavoriteItem.LastEpisode last_episode4 = favoriteItem7.getLast_episode();
                            this$0.changeLikeStatus(false, like_status2, id3, box_type3, season3, last_episode4 == null ? 0 : last_episode4.getEpisode(), i);
                            return;
                        } else {
                            int like_status3 = favoriteItem7.getLast_episode().getLike_status();
                            String id4 = favoriteItem7.getId();
                            int box_type4 = favoriteItem7.getBox_type();
                            FavoriteItem.LastEpisode last_episode5 = favoriteItem7.getLast_episode();
                            int season4 = last_episode5 == null ? 0 : last_episode5.getSeason();
                            FavoriteItem.LastEpisode last_episode6 = favoriteItem7.getLast_episode();
                            this$0.changeLikeStatus(false, like_status3, id4, box_type4, season4, last_episode6 == null ? 0 : last_episode6.getEpisode(), i);
                            return;
                        }
                    case R.id.llLike /* 2131297190 */:
                        FavoriteItem favoriteItem8 = (FavoriteItem) itemOrNull;
                        if (favoriteItem8.getBox_type() == 1) {
                            int like_status4 = favoriteItem8.getLike_status();
                            String id5 = favoriteItem8.getId();
                            int box_type5 = favoriteItem8.getBox_type();
                            FavoriteItem.LastEpisode last_episode7 = favoriteItem8.getLast_episode();
                            int season5 = last_episode7 == null ? 0 : last_episode7.getSeason();
                            FavoriteItem.LastEpisode last_episode8 = favoriteItem8.getLast_episode();
                            this$0.changeLikeStatus(true, like_status4, id5, box_type5, season5, last_episode8 == null ? 0 : last_episode8.getEpisode(), i);
                            return;
                        } else if (favoriteItem8.getLast_episode() == null) {
                            int like_status5 = favoriteItem8.getLike_status();
                            String id6 = favoriteItem8.getId();
                            int box_type6 = favoriteItem8.getBox_type();
                            FavoriteItem.LastEpisode last_episode9 = favoriteItem8.getLast_episode();
                            int season6 = last_episode9 == null ? 0 : last_episode9.getSeason();
                            FavoriteItem.LastEpisode last_episode10 = favoriteItem8.getLast_episode();
                            this$0.changeLikeStatus(true, like_status5, id6, box_type6, season6, last_episode10 == null ? 0 : last_episode10.getEpisode(), i);
                            return;
                        } else {
                            int like_status6 = favoriteItem8.getLast_episode().getLike_status();
                            String id7 = favoriteItem8.getId();
                            int box_type7 = favoriteItem8.getBox_type();
                            FavoriteItem.LastEpisode last_episode11 = favoriteItem8.getLast_episode();
                            int season7 = last_episode11 == null ? 0 : last_episode11.getSeason();
                            FavoriteItem.LastEpisode last_episode12 = favoriteItem8.getLast_episode();
                            this$0.changeLikeStatus(true, like_status6, id7, box_type7, season7, last_episode12 == null ? 0 : last_episode12.getEpisode(), i);
                            return;
                        }
                    default:
                        return;
                }
            } else if (itemOrNull instanceof FavoriteSeasonItem) {
                if (view.getId() == R.id.ivExpand) {
                    this$0.mAdapter.expandOrCollapse(i, true, true, 110);
                    this$0.mAdapter.notifyItemChanged(i);
                } else if (view.getId() == R.id.ivMark) {
                    FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) itemOrNull;
                    markTv$default(this$0, favoriteSeasonItem.getId(), String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, favoriteSeasonItem, null, false, 192, null);
                }
            } else if (itemOrNull instanceof FavoriteEpisodeItem) {
                if (view.getId() == R.id.ivExpand) {
                    this$0.mAdapter.expandOrCollapse(i, true, true, 110);
                    this$0.mAdapter.notifyItemChanged(i);
                } else if (view.getId() == R.id.ivMark) {
                    FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) itemOrNull;
                    markTv$default(this$0, favoriteEpisodeItem.getId(), String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, null, favoriteEpisodeItem, false, Opcodes.IF_ICMPNE, null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-40$lambda-39  reason: not valid java name */
        public static final void m1173onItemChildClick$lambda40$lambda39(FavoriteListFragment this$0, BaseNode item, int i, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(item, "$item");
            FavoriteItem favoriteItem = (FavoriteItem) item;
            removeFavorite$default(this$0, favoriteItem.getId(), favoriteItem.getBox_type(), i, false, 8, null);
        }

        private final boolean checkShowProgress(ArrayList<Integer> arrayList) {
            int i = 0;
            for (Object obj : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int intValue = ((Number) obj).intValue();
                if (i == 0 && intValue == 0) {
                    return false;
                }
                Integer num = (Integer) CollectionsKt.getOrNull(arrayList, i - 1);
                if (num != null && intValue == 1 && num.intValue() == 0) {
                    return false;
                }
                i = i2;
            }
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected void doSomethingWithData(List<FavoriteItem> list) {
            int i;
            boolean z;
            int i2;
            boolean z2;
            Intrinsics.checkNotNullParameter(list, "list");
            if (list.isEmpty()) {
                return;
            }
            for (FavoriteItem favoriteItem : list) {
                favoriteItem.setWatched(getWatched() == 1);
                favoriteItem.setExpanded(false);
                if (favoriteItem.getBox_type() == 2) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    ArrayList<FavoriteEpisodeItem> arrayList2 = new ArrayList();
                    List<FavoriteSeasonItem> episode_progress_list = favoriteItem.getEpisode_progress_list();
                    int i3 = -1;
                    if (episode_progress_list != null) {
                        int i4 = 0;
                        for (Object obj : episode_progress_list) {
                            int i5 = i4 + 1;
                            if (i4 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) obj;
                            List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                            Intrinsics.checkNotNullExpressionValue(episode_list, "season.episode_list");
                            ListIterator<FavoriteEpisodeItem> listIterator = episode_list.listIterator(episode_list.size());
                            while (true) {
                                if (!listIterator.hasPrevious()) {
                                    i2 = -1;
                                    break;
                                }
                                if (listIterator.previous().getOver() == 1) {
                                    z2 = true;
                                    continue;
                                } else {
                                    z2 = false;
                                    continue;
                                }
                                if (z2) {
                                    i2 = listIterator.nextIndex();
                                    break;
                                }
                            }
                            if (i2 != i3) {
                                FavoriteEpisodeItem favoriteEpisodeItem = favoriteSeasonItem.getEpisode_list().get(i2);
                                favoriteSeasonItem.setLastStartWatchedItem(favoriteEpisodeItem);
                                favoriteSeasonItem.setLastWatchedItem(favoriteEpisodeItem);
                                favoriteSeasonItem.setLastWatchedIndex(i2);
                                if (i2 >= 0) {
                                    while (true) {
                                        int i6 = i2 - 1;
                                        if (favoriteSeasonItem.getEpisode_list().get(i2).getOver() != 1) {
                                            break;
                                        }
                                        favoriteSeasonItem.setLastStartWatchedItem(favoriteSeasonItem.getEpisode_list().get(i2));
                                        if (i6 < 0) {
                                            break;
                                        }
                                        i2 = i6;
                                    }
                                }
                            }
                            favoriteSeasonItem.setId(favoriteItem.getId());
                            favoriteSeasonItem.setExpanded(false);
                            List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem.getEpisode_list();
                            if (episode_list2 == null) {
                                episode_list2 = new ArrayList<>();
                            }
                            favoriteSeasonItem.setEpisodes(new ArrayList(episode_list2));
                            favoriteSeasonItem.setWatchList(new ArrayList());
                            List<FavoriteEpisodeItem> episode_list3 = favoriteSeasonItem.getEpisode_list();
                            if (episode_list3 == null) {
                                episode_list3 = new ArrayList<>();
                            }
                            arrayList2.addAll(episode_list3);
                            List<FavoriteEpisodeItem> episode_list4 = favoriteSeasonItem.getEpisode_list();
                            if (episode_list4 != null) {
                                int i7 = 0;
                                for (Object obj2 : episode_list4) {
                                    int i8 = i7 + 1;
                                    if (i7 < 0) {
                                        CollectionsKt.throwIndexOverflow();
                                    }
                                    FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) obj2;
                                    favoriteEpisodeItem2.setSeason(favoriteSeasonItem.getSeason());
                                    favoriteEpisodeItem2.setId(favoriteItem.getId());
                                    if (favoriteEpisodeItem2.getOver() == 1) {
                                        favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getWatchedCount() + 1);
                                        arrayList.add(1);
                                        favoriteSeasonItem.getWatchList().add(1);
                                    } else {
                                        arrayList.add(0);
                                        favoriteSeasonItem.getWatchList().add(0);
                                    }
                                    if (i4 == favoriteItem.getEpisode_progress_list().size() - 1) {
                                        favoriteEpisodeItem2.setLastSeason(true);
                                    }
                                    if (i7 == favoriteSeasonItem.getEpisode_list().size() - 1) {
                                        favoriteEpisodeItem2.setLastItem(true);
                                    }
                                    i7 = i8;
                                }
                            }
                            favoriteItem.setTotalEpisode(favoriteItem.getTotalEpisode() + favoriteSeasonItem.getEpisode_list().size());
                            favoriteItem.setWatchedEpisodeCount(favoriteItem.getWatchedEpisodeCount() + favoriteSeasonItem.getWatchedCount());
                            i4 = i5;
                            i3 = -1;
                        }
                    }
                    ArrayList arrayList3 = arrayList2;
                    ListIterator<FavoriteEpisodeItem> listIterator2 = arrayList3.listIterator(arrayList3.size());
                    while (true) {
                        if (!listIterator2.hasPrevious()) {
                            i = -1;
                            break;
                        }
                        if (listIterator2.previous().getOver() == 1) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            i = listIterator2.nextIndex();
                            break;
                        }
                    }
                    if (i != -1) {
                        Object obj3 = arrayList2.get(i);
                        Intrinsics.checkNotNullExpressionValue(obj3, "allEpisodes[lastIndex]");
                        FavoriteEpisodeItem favoriteEpisodeItem3 = (FavoriteEpisodeItem) obj3;
                        favoriteItem.setLastStartWatchedItem(favoriteEpisodeItem3);
                        favoriteItem.setLastWatchedItem(favoriteEpisodeItem3);
                        favoriteItem.setLastWatchedIndex(i);
                        for (FavoriteEpisodeItem favoriteEpisodeItem4 : arrayList2) {
                            if (favoriteEpisodeItem4.getSeason() < favoriteEpisodeItem3.getSeason()) {
                                favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                            } else if (favoriteEpisodeItem4.getSeason() == favoriteEpisodeItem3.getSeason() && favoriteEpisodeItem4.getEpisode() <= favoriteEpisodeItem3.getEpisode()) {
                                favoriteItem.setLastWatchedCount(favoriteItem.getLastWatchedCount() + 1);
                            }
                        }
                        if (i >= 0) {
                            while (true) {
                                int i9 = i - 1;
                                if (((FavoriteEpisodeItem) arrayList2.get(i)).getOver() != 1) {
                                    break;
                                }
                                favoriteItem.setLastStartWatchedItem((FavoriteEpisodeItem) arrayList2.get(i));
                                if (i9 < 0) {
                                    break;
                                }
                                i = i9;
                            }
                        }
                    }
                    favoriteItem.setEpisodes(arrayList);
                    favoriteItem.setAllEpisodes(arrayList3);
                    List<FavoriteSeasonItem> episode_progress_list2 = favoriteItem.getEpisode_progress_list();
                    if (episode_progress_list2 == null) {
                        episode_progress_list2 = new ArrayList<>();
                    }
                    favoriteItem.setSeasons(new ArrayList(episode_progress_list2));
                }
            }
            getWatched();
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected Observable<String> getServiceData() {
            if (this.mCurrentPage == 1) {
                this.waitingAdded = false;
                this.watchingAdded = false;
            }
            return CommonExtKt.request$default(Api.INSTANCE.getCollectList(this.boxType, this.quality, this.sort, this.country, this.year, this.rating, this.gener, this.mCurrentPage, this.mPageSize, 1), null, 1, null);
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected BaseNodeAdapter initAdapter() {
            return new CollectAdapter(this, this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        public List<FavoriteItem> getData(FavoriteResponse p) {
            Intrinsics.checkNotNullParameter(p, "p");
            ArrayList arrayList = new ArrayList();
            if (this.mCurrentPage == 1) {
                WatchingItem watchingItem = this.watchingItem;
                ArrayList<FavoriteItem> list = p.getList();
                watchingItem.setHaveWaiting(true ^ (list == null || list.isEmpty()));
                FavoriteItem favoriteItem = new FavoriteItem();
                favoriteItem.setWatchingItem(this.watchingItem);
                arrayList.add(favoriteItem);
                getWatchingList();
            }
            ArrayList<FavoriteItem> list2 = p.getList();
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            arrayList.addAll(list2);
            return arrayList;
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public final void onRefreshFavorite(RefreshFavoriteEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            startRefresh();
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected void getBundle(Bundle bundle) {
            String string;
            this.mClass = FavoriteItem.class;
            this.mPageClass = FavoriteResponse.class;
            this.mPageSize = 10;
            if (getWatched() == 1) {
                string = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_WATCHED);
            } else {
                string = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_WAITING);
            }
            String str = string;
            boolean z = false;
            if (!(str == null || StringsKt.isBlank(str))) {
                Object parseObject = JSONObject.parseObject(string, HashMap.class);
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
            FavoriteAllFilterLiveData.Companion.get().setValue(Boolean.valueOf(true ^ this.allDefault));
            FavoriteListFragment favoriteListFragment = this;
            RefreshWaitingLiveData.Companion.get().observeInFragment(favoriteListFragment, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$rfszT-KxwXO9fzVJeaTLfXWo5ZY
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FavoriteFragment2.FavoriteListFragment.m1159getBundle$lambda48(FavoriteFragment2.FavoriteListFragment.this, (Boolean) obj);
                }
            });
            GetPlanPathLiveData.Companion.get().observeInFragment(favoriteListFragment, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$JLmVRv2WcwVTNRJAFNXSiA655Dg
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FavoriteFragment2.FavoriteListFragment.m1160getBundle$lambda49(FavoriteFragment2.FavoriteListFragment.this, (HashMap) obj);
                }
            });
            GoMoreWatchingLiveData.Companion.get().observeInFragment(favoriteListFragment, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FavoriteFragment2$FavoriteListFragment$_Nq_jfaXMU-SYJd7gZnySZht2d4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FavoriteFragment2.FavoriteListFragment.m1161getBundle$lambda50(FavoriteFragment2.FavoriteListFragment.this, (Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getBundle$lambda-48  reason: not valid java name */
        public static final void m1159getBundle$lambda48(FavoriteListFragment this$0, Boolean bool) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.startRefresh();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getBundle$lambda-49  reason: not valid java name */
        public static final void m1160getBundle$lambda49(FavoriteListFragment this$0, HashMap hashMap) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Object obj = hashMap.get("id");
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj;
            Object obj2 = hashMap.get("box_type");
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            int intValue = ((Integer) obj2).intValue();
            Object obj3 = hashMap.get("season");
            if (obj3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            int intValue2 = ((Integer) obj3).intValue();
            Object obj4 = hashMap.get("episode");
            if (obj4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            this$0.getPlayPath(str, intValue, intValue2, ((Integer) obj4).intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getBundle$lambda-50  reason: not valid java name */
        public static final void m1161getBundle$lambda50(FavoriteListFragment this$0, Boolean bool) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            MoreWatchingActivity.Companion.start(this$0.getContext(), this$0.boxType, this$0.quality, this$0.sort, this$0.country, this$0.year, this$0.rating, this$0.gener);
        }

        @Override // com.movieboxpro.android.base.BaseNodeListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            if (CommonUtils.isTablet()) {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }

        @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
            Intrinsics.checkNotNullParameter(newConfig, "newConfig");
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == 2) {
                this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }
}
