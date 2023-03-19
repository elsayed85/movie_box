package com.movieboxpro.android.view.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.LikeResponse;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
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
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: LikeFragment.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 12\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00140\u0013H\u0014J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0014J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0014J\b\u0010\u001e\u001a\u00020\bH\u0014J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0002H\u0014J\b\u0010\"\u001a\u00020\bH\u0014J\b\u0010#\u001a\u00020\u0006H\u0014J\b\u0010$\u001a\u00020\u0006H\u0014J*\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u001d2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bH\u0002J\u0010\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0014J\b\u0010/\u001a\u000200H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u00062"}, d2 = {"Lcom/movieboxpro/android/view/fragment/LikeFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/common/Collectlist;", "Lcom/movieboxpro/android/model/LikeResponse;", "()V", "editMode", "", "<set-?>", "", "likeStatus", "getLikeStatus", "()I", "setLikeStatus", "(I)V", "likeStatus$delegate", "Lkotlin/properties/ReadWriteProperty;", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "response", "getServiceData", "Lio/reactivex/Observable;", "", "gridLayoutSpan", "initHolder", "helper", "item", "initItemLayout", "isNeedLogin", "isVerticalLayout", "markStatus", "id", "boxType", "position", NotificationCompat.CATEGORY_STATUS, "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LikeFragment extends BaseListFragment<Collectlist, LikeResponse> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(LikeFragment.class, "likeStatus", "getLikeStatus()I", 0))};
    public static final Companion Companion = new Companion(null);
    private boolean editMode;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ReadWriteProperty likeStatus$delegate = FragmentArgsKt.arg(this);

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
    protected boolean isNeedLogin() {
        return true;
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

    /* JADX INFO: Access modifiers changed from: private */
    public final int getLikeStatus() {
        return ((Number) this.likeStatus$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLikeStatus(int i) {
        this.likeStatus$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* compiled from: LikeFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/LikeFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/LikeFragment;", "likeStatus", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LikeFragment newInstance(int i) {
            LikeFragment likeFragment = new LikeFragment();
            likeFragment.setLikeStatus(i);
            return likeFragment;
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return CommonExtKt.request$default(Api.INSTANCE.getLikeList(getLikeStatus(), this.mCurrentPage, this.mPageSize), null, 1, null);
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
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$LikeFragment$kvESB_xZ1LfLOP9otnHB8t207Wo
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LikeFragment.m1205onItemChildClick$lambda1(LikeFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-1  reason: not valid java name */
    public static final void m1205onItemChildClick$lambda1(final LikeFragment this$0, BaseQuickAdapter noName_0, View noName_1, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        final Collectlist collectlist = (Collectlist) this$0.mAdapter.getItem(i);
        new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem(this$0.getLikeStatus() == 1 ? "REMOVE FROM LIKE" : "REMOVE FROM DISLIKE", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.LikeFragment$onItemChildClick$1$1
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i2) {
                LikeFragment.this.markStatus(collectlist.id, collectlist.box_type, i, 0);
            }
        }).addSheetItem(this$0.getLikeStatus() == 1 ? "ADD TO DISLIKE" : "ADD TO LIKE", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.LikeFragment$onItemChildClick$1$2
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public void onClick(int i2) {
                int likeStatus;
                likeStatus = LikeFragment.this.getLikeStatus();
                if (likeStatus == 1) {
                    LikeFragment.this.markStatus(collectlist.id, collectlist.box_type, i, 2);
                } else {
                    LikeFragment.this.markStatus(collectlist.id, collectlist.box_type, i, 1);
                }
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStatus(String str, int i, int i2, int i3) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Movie_like").param("box_type", Integer.valueOf(i)).param("mid", str).param("season", (Object) 0).param("episode", (Object) 0).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i3)).asRequest(), this), new LikeFragment$markStatus$1(this), null, new LikeFragment$markStatus$2(this), null, new LikeFragment$markStatus$3(this, i2), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<Collectlist, BaseViewHolder> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        adapter.addChildClickViewIds(R.id.ivMore);
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

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$LikeFragment$7uWH8RYNBo0Oocubn4SCjt19_D0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LikeFragment.m1206onItemClick$lambda2(LikeFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-2  reason: not valid java name */
    public static final void m1206onItemClick$lambda2(LikeFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
}
