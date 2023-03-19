package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.databinding.ActivityPreferBinding;
import com.movieboxpro.android.model.LikeResponse;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.fragment.PreferActivity;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: PreferActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/fragment/PreferActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityPreferBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityPreferBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "boxType", "", "fragment", "Lcom/movieboxpro/android/view/fragment/PreferActivity$PreferLikeListFragment;", "likeStatus", "initData", "", "initListener", "initView", "PreferLikeListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreferActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(PreferActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityPreferBinding;", 0))};
    private PreferLikeListFragment fragment;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityPreferBinding.class, this);
    private int likeStatus = 1;
    private int boxType = 1;

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    private final ActivityPreferBinding getBinding() {
        return (ActivityPreferBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$PreferActivity$qI2Py8ZmVlJf3uViqjmJYsjAFbs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreferActivity.m1207initListener$lambda0(PreferActivity.this, view);
            }
        });
        getBinding().rgLike.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$PreferActivity$VttS-DXa95Rzn3Y0xn_j92AOqEY
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                PreferActivity.m1208initListener$lambda1(PreferActivity.this, radioGroup, i);
            }
        });
        getBinding().rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$PreferActivity$guPH9hHfQY_GH-TP_Nv9Y18xWhY
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                PreferActivity.m1209initListener$lambda2(PreferActivity.this, radioGroup, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1207initListener$lambda0(PreferActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1208initListener$lambda1(PreferActivity this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == R.id.rbDislikes) {
            this$0.likeStatus = 2;
        } else if (i == R.id.rbLikes) {
            this$0.likeStatus = 1;
        }
        PreferLikeListFragment preferLikeListFragment = this$0.fragment;
        if (preferLikeListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            preferLikeListFragment = null;
        }
        preferLikeListFragment.refreshData(this$0.likeStatus, this$0.boxType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1209initListener$lambda2(PreferActivity this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == R.id.rbEpisode) {
            this$0.boxType = 3;
        } else if (i == R.id.rbMovies) {
            this$0.boxType = 1;
        } else if (i == R.id.rbTv) {
            this$0.boxType = 2;
        }
        PreferLikeListFragment preferLikeListFragment = this$0.fragment;
        if (preferLikeListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            preferLikeListFragment = null;
        }
        preferLikeListFragment.refreshData(this$0.likeStatus, this$0.boxType);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        this.fragment = PreferLikeListFragment.Companion.newInstance(this.likeStatus, this.boxType);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        PreferLikeListFragment preferLikeListFragment = this.fragment;
        if (preferLikeListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            preferLikeListFragment = null;
        }
        FragmentUtils.add(supportFragmentManager, preferLikeListFragment, (int) R.id.flContainer);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Prefer Lists");
    }

    /* compiled from: PreferActivity.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0014J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0014J\b\u0010\u001c\u001a\u00020\u0006H\u0014J\u0018\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0002H\u0014J\b\u0010!\u001a\u00020\u0006H\u0014J\b\u0010\"\u001a\u00020#H\u0014J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0014J\u0016\u0010)\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000b¨\u0006+"}, d2 = {"Lcom/movieboxpro/android/view/fragment/PreferActivity$PreferLikeListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/common/Collectlist;", "Lcom/movieboxpro/android/model/LikeResponse;", "()V", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "likeStatus", "getLikeStatus", "setLikeStatus", "likeStatus$delegate", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "response", "getServiceData", "Lio/reactivex/Observable;", "", "gridLayoutSpan", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "isVerticalLayout", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "refreshData", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PreferLikeListFragment extends BaseListFragment<Collectlist, LikeResponse> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreferLikeListFragment.class, "likeStatus", "getLikeStatus()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreferLikeListFragment.class, "boxType", "getBoxType()I", 0))};
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private final ReadWriteProperty boxType$delegate;
        private final ReadWriteProperty likeStatus$delegate;

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
            return R.layout.adapter_like_item;
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

        public PreferLikeListFragment() {
            PreferLikeListFragment preferLikeListFragment = this;
            this.likeStatus$delegate = FragmentArgsKt.arg(preferLikeListFragment);
            this.boxType$delegate = FragmentArgsKt.arg(preferLikeListFragment);
        }

        private final int getLikeStatus() {
            return ((Number) this.likeStatus$delegate.getValue(this, $$delegatedProperties[0])).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setLikeStatus(int i) {
            this.likeStatus$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
        }

        private final int getBoxType() {
            return ((Number) this.boxType$delegate.getValue(this, $$delegatedProperties[1])).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setBoxType(int i) {
            this.boxType$delegate.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
        }

        /* compiled from: PreferActivity.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/PreferActivity$PreferLikeListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/PreferActivity$PreferLikeListFragment;", "likeStatus", "", "boxType", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final PreferLikeListFragment newInstance(int i, int i2) {
                PreferLikeListFragment preferLikeListFragment = new PreferLikeListFragment();
                preferLikeListFragment.setLikeStatus(i);
                preferLikeListFragment.setBoxType(i2);
                return preferLikeListFragment;
            }
        }

        public final void refreshData(int i, int i2) {
            setLikeStatus(i);
            setBoxType(i2);
            startRefresh();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            boolean z = false;
            if (getContext() != null && CommonUtils.isTablet()) {
                z = true;
            }
            return z ? 5 : 3;
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

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return CommonExtKt.request$default(Api.INSTANCE.getLikeList(getLikeStatus(), getBoxType(), this.mCurrentPage, this.mPageSize), null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<Collectlist> getData(LikeResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            ArrayList<Collectlist> list = response.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = Collectlist.class;
            this.mPageClass = LikeResponse.class;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$PreferActivity$PreferLikeListFragment$ceqV7eXv1qT7J1FGclgvfZGmqxk
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    PreferActivity.PreferLikeListFragment.m1212onItemClick$lambda1(PreferActivity.PreferLikeListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-1  reason: not valid java name */
        public static final void m1212onItemClick$lambda1(PreferLikeListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            Collectlist collectlist = (Collectlist) this$0.mAdapter.getItem(i);
            if (collectlist != null) {
                if (collectlist.box_type == 1) {
                    MovieDetailActivity.start(this$0.getContext(), collectlist.id, collectlist.poster);
                } else {
                    TvDetailActivity.start(this$0.getContext(), collectlist.id, collectlist.banner_mini, collectlist.poster);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, Collectlist item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
            SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.tvTimeUpdate);
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
                return;
            }
            String imdb_rating2 = item.getImdb_rating();
            if (imdb_rating2 == null) {
                imdb_rating2 = "";
            }
            CommonExtKt.textShadow$default(textView3, imdb_rating2, 0, 0, 6, null);
        }
    }
}
