package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingFragment;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.databinding.FragmentRecommendBinding;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.WatchedItem;
import com.movieboxpro.android.model.WatchedResponse;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.databinding.FragmentBindingDelegate;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.fragment.RecommendFragment;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: RecommendFragment.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/fragment/RecommendFragment;", "Lcom/movieboxpro/android/base/BaseBindingFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/FragmentRecommendBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/FragmentRecommendBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", "fragment", "Lcom/movieboxpro/android/view/fragment/RecommendFragment$RecommendListFragment;", "initData", "", "initListener", "initView", "RecommendListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RecommendFragment extends BaseBindingFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(RecommendFragment.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/FragmentRecommendBinding;", 0))};
    public Map<Integer, View> _$_findViewCache;
    private final FragmentBindingDelegate binding$delegate;
    private RecommendListFragment fragment;

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
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

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public RecommendFragment() {
        super(R.layout.fragment_recommend);
        this._$_findViewCache = new LinkedHashMap();
        this.binding$delegate = new FragmentBindingDelegate(FragmentRecommendBinding.class, this);
    }

    private final FragmentRecommendBinding getBinding() {
        return (FragmentRecommendBinding) this.binding$delegate.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initListener() {
        getBinding().ivManager.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$RecommendFragment$3njvvTfDUW16vKqvPEcZRyPhn50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFragment.m1213initListener$lambda0(RecommendFragment.this, view);
            }
        });
        getBinding().ivRefresh.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$RecommendFragment$7sUMPP5XRKhzMxUjbFyR5I7w2U0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendFragment.m1214initListener$lambda1(RecommendFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1213initListener$lambda0(RecommendFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, PreferActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1214initListener$lambda1(RecommendFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_recommend_reload").asRequest(), this$0), new RecommendFragment$initListener$2$1(this$0), null, null, null, new RecommendFragment$initListener$2$2(this$0), 14, null);
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initData() {
        this.fragment = new RecommendListFragment();
        FragmentManager childFragmentManager = getChildFragmentManager();
        RecommendListFragment recommendListFragment = this.fragment;
        if (recommendListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            recommendListFragment = null;
        }
        FragmentUtils.add(childFragmentManager, recommendListFragment, (int) R.id.flContainer);
    }

    /* compiled from: RecommendFragment.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0014¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/fragment/RecommendFragment$RecommendListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/WatchedItem;", "Lcom/movieboxpro/android/model/WatchedResponse;", "()V", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "", "gridLayoutSpan", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "isVerticalLayout", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class RecommendListFragment extends BaseListFragment<WatchedItem, WatchedResponse> {
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
        protected int initItemLayout() {
            return R.layout.adapter_favorite_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isVerticalLayout() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return CommonExtKt.request$default(Api.INSTANCE.getRecommendList(this.mCurrentPage, this.mPageSize), null, 1, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            boolean z = false;
            if (getContext() != null && CommonUtils.isTablet()) {
                z = true;
            }
            return z ? 5 : 3;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$RecommendFragment$RecommendListFragment$gbXZMWREtRxc0FAPyG4EIRFNpdo
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    RecommendFragment.RecommendListFragment.m1215onItemClick$lambda1(RecommendFragment.RecommendListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-1  reason: not valid java name */
        public static final void m1215onItemClick$lambda1(RecommendListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            WatchedItem watchedItem = (WatchedItem) this$0.mAdapter.getItem(i);
            if (watchedItem.getBox_type() == 1) {
                MovieDetailActivity.start(this$0.getContext(), watchedItem.getId(), watchedItem.getPoster());
            } else {
                TvDetailActivity.start(this$0.getContext(), watchedItem.getId(), "", watchedItem.getPoster());
            }
        }

        @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
            Intrinsics.checkNotNullParameter(newConfig, "newConfig");
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == 2) {
                RecyclerView mRecyclerView = this.mRecyclerView;
                Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
                CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 5);
            } else if (getResources().getConfiguration().orientation == 1) {
                RecyclerView mRecyclerView2 = this.mRecyclerView;
                Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
                CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 3);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<WatchedItem> getData(WatchedResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<WatchedItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = WatchedItem.class;
            this.mPageClass = WatchedResponse.class;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, WatchedItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
            SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.tvTimeUpdate);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivCheckable);
            TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
            TextView textView2 = (TextView) helper.getView(R.id.tvUpdateCount);
            GlideUtils.loadPortraitGifHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivPoster));
            boolean z = false;
            if (item.getBox_type() == 1) {
                CommonExtKt.gone(textView);
                CommonExtKt.gone(slantedTextView);
                String quality_tag_new = item.getQuality_tag_new();
                if (quality_tag_new == null || quality_tag_new.length() == 0) {
                    CommonExtKt.gone(imageView);
                } else {
                    CommonExtKt.visible(imageView);
                    imageView.setImageResource(CommonUtils.getMovieTag(item.getQuality_tag_new()));
                }
            } else {
                CommonExtKt.gone(imageView);
                String season_episode = item.getSeason_episode();
                if (season_episode == null || season_episode.length() == 0) {
                    CommonExtKt.gone(textView);
                } else {
                    CommonExtKt.visible(textView);
                    textView.setText(item.getSeason_episode());
                }
                String update_title = item.getUpdate_title();
                if (update_title == null || update_title.length() == 0) {
                    CommonExtKt.gone(slantedTextView);
                } else {
                    CommonExtKt.visible(slantedTextView);
                    slantedTextView.setText(item.getUpdate_title());
                }
            }
            CommonExtKt.gone(imageView2);
            if (item.getUpdateCount() > 0) {
                CommonExtKt.visible(textView2);
                if (item.getUpdateCount() > 99) {
                    textView2.setText("99+");
                } else {
                    textView2.setText(String.valueOf(item.getUpdateCount()));
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
            imageView3.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
            if (item.getTomato_meter() == 0) {
                CommonExtKt.gone(textView4);
                CommonExtKt.gone(imageView3);
                return;
            }
            CommonExtKt.visible(textView4);
            CommonExtKt.visible(imageView3);
            StringBuilder sb = new StringBuilder();
            sb.append(item.getTomato_meter());
            sb.append('%');
            CommonExtKt.textShadow$default(textView4, sb.toString(), 0, 0, 6, null);
        }
    }
}
