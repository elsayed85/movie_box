package com.movieboxpro.android.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.FeaturedAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: FeaturedAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\f\rB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0014J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/adapter/FeaturedAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/common/Homelist;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/adapter/FeaturedAdapter$OnFeaturedItemClickListener;", "convert", "", "helper", "item", "setFeaturedItemClickListener", "FeaturedListAdapter", "OnFeaturedItemClickListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeaturedAdapter extends BaseQuickAdapter<Homelist, BaseViewHolder> {
    private OnFeaturedItemClickListener listener;

    /* compiled from: FeaturedAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&J4\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/adapter/FeaturedAdapter$OnFeaturedItemClickListener;", "", "onItemClicked", "", "itemType", "", "item", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "view", "Landroid/view/View;", "position", "adapter", "Lcom/movieboxpro/android/adapter/FeaturedAdapter$FeaturedListAdapter;", "onItemLongClicked", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnFeaturedItemClickListener {
        void onItemClicked(int i, Homelist.Typelist typelist, View view, int i2, FeaturedListAdapter featuredListAdapter);

        void onItemLongClicked(int i, Homelist.Typelist typelist, View view, int i2, FeaturedListAdapter featuredListAdapter);
    }

    public FeaturedAdapter() {
        super(R.layout.adapter_featured_item, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, Homelist item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvName);
        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
        String str = item.name;
        if (str == null || str.length() == 0) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            textView.setText(item.name);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setNestedScrollingEnabled(false);
        new GravitySnapHelper(8388611).attachToRecyclerView(recyclerView);
        ArrayList arrayList = item.list;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        final FeaturedListAdapter featuredListAdapter = new FeaturedListAdapter(arrayList);
        recyclerView.setAdapter(featuredListAdapter);
        CommonExtKt.disableRefreshAnimation(recyclerView);
        featuredListAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$FeaturedAdapter$kEJuUX3N5T1rG7wS6mpn2wePiww
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FeaturedAdapter.m59convert$lambda3$lambda0(FeaturedAdapter.FeaturedListAdapter.this, this, baseQuickAdapter, view, i);
            }
        });
        featuredListAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$FeaturedAdapter$afdWgBtk8WtmpNgFFnOx3Rd_CE4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FeaturedAdapter.m60convert$lambda3$lambda1(FeaturedAdapter.FeaturedListAdapter.this, this, baseQuickAdapter, view, i);
            }
        });
        featuredListAdapter.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$FeaturedAdapter$1wupFd6UEV3dtG4lVOIgK5gF-ag
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                boolean m61convert$lambda3$lambda2;
                m61convert$lambda3$lambda2 = FeaturedAdapter.m61convert$lambda3$lambda2(FeaturedAdapter.FeaturedListAdapter.this, this, baseQuickAdapter, view, i);
                return m61convert$lambda3$lambda2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-3$lambda-0  reason: not valid java name */
    public static final void m59convert$lambda3$lambda0(FeaturedListAdapter featuredAdapter, FeaturedAdapter this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(featuredAdapter, "$featuredAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        Homelist.Typelist item = featuredAdapter.getItem(i);
        View viewByPosition = featuredAdapter.getViewByPosition(i, R.id.movie_item_poster);
        OnFeaturedItemClickListener onFeaturedItemClickListener = this$0.listener;
        if (onFeaturedItemClickListener == null) {
            return;
        }
        onFeaturedItemClickListener.onItemClicked(item == null ? -1 : item.itemType, item, viewByPosition, i, featuredAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-3$lambda-1  reason: not valid java name */
    public static final void m60convert$lambda3$lambda1(FeaturedListAdapter featuredAdapter, FeaturedAdapter this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(featuredAdapter, "$featuredAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        Homelist.Typelist item = featuredAdapter.getItem(i);
        OnFeaturedItemClickListener onFeaturedItemClickListener = this$0.listener;
        if (onFeaturedItemClickListener == null) {
            return;
        }
        onFeaturedItemClickListener.onItemClicked(-100, item, view, i, featuredAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-3$lambda-2  reason: not valid java name */
    public static final boolean m61convert$lambda3$lambda2(FeaturedListAdapter featuredAdapter, FeaturedAdapter this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(featuredAdapter, "$featuredAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        Homelist.Typelist item = featuredAdapter.getItem(i);
        if (item.itemType == 2) {
            OnFeaturedItemClickListener onFeaturedItemClickListener = this$0.listener;
            if (onFeaturedItemClickListener != null) {
                onFeaturedItemClickListener.onItemLongClicked(item.itemType, item, view, i, featuredAdapter);
            }
            return true;
        }
        return false;
    }

    public final void setFeaturedItemClickListener(OnFeaturedItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* compiled from: FeaturedAdapter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0014J!\u0010\u000b\u001a\u00020\b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u00020\b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/adapter/FeaturedAdapter$FeaturedListAdapter;", "Lcom/movieboxpro/android/adapter/BaseLoadmoreDelegateMultiAdapter;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "(Ljava/util/List;)V", "convert", "", "helper", "item", "setViewGone", "views", "", "Landroid/view/View;", "([Landroid/view/View;)V", "setViewVisible", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FeaturedListAdapter extends BaseLoadmoreDelegateMultiAdapter<Homelist.Typelist, BaseViewHolder> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FeaturedListAdapter(List<Homelist.Typelist> list) {
            super(list);
            Intrinsics.checkNotNullParameter(list, "list");
            setMultiTypeDelegate(new BaseMultiTypeDelegate<Homelist.Typelist>() { // from class: com.movieboxpro.android.adapter.FeaturedAdapter.FeaturedListAdapter.1
                @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
                public int getItemType(List<? extends Homelist.Typelist> data, int i) {
                    Intrinsics.checkNotNullParameter(data, "data");
                    return data.get(i).itemType;
                }
            });
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate);
            multiTypeDelegate.addItemType(1, R.layout.layout_tvlist_item2);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate2 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate2);
            multiTypeDelegate2.addItemType(2, R.layout.layout_continue_play);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate3 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate3);
            multiTypeDelegate3.addItemType(4, R.layout.layout_tvlist_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate4 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate4);
            multiTypeDelegate4.addItemType(3, R.layout.layout_movelist_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate5 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate5);
            multiTypeDelegate5.addItemType(9, R.layout.layout_movelist_like_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate6 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate6);
            multiTypeDelegate6.addItemType(5, R.layout.adapter_home_actor_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate7 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate7);
            multiTypeDelegate7.addItemType(6, R.layout.adapter_cat_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate8 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate8);
            multiTypeDelegate8.addItemType(7, R.layout.adapter_featured_movie_list_image_item);
            BaseMultiTypeDelegate<Homelist.Typelist> multiTypeDelegate9 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate9);
            multiTypeDelegate9.addItemType(8, R.layout.adapter_home_ad_item);
            addChildClickViewIds(R.id.llDetail, R.id.ivDislike);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(BaseViewHolder helper, Homelist.Typelist item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            boolean z = false;
            switch (item.itemType) {
                case 2:
                    ImageView imageView = (ImageView) helper.getView(R.id.ivPoster);
                    ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
                    ImageView imageView2 = (ImageView) helper.getView(R.id.ivContinue);
                    TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
                    ImageView imageView3 = (ImageView) helper.getView(R.id.ivDetail);
                    String str = item.id;
                    if (str == null || str.length() == 0) {
                        GlideUtils.loadWithCorner(getContext(), (int) R.drawable.ic_movie_more, imageView, CommonExtKt.dp2Px(8));
                        setViewGone(progressBar, imageView2, textView);
                        CommonExtKt.gone(imageView3);
                        return;
                    }
                    CommonExtKt.visible(imageView2);
                    GlideUtils.loadCornerPortraitGifHolder(getContext(), item.poster, imageView, 8);
                    CommonExtKt.visible(progressBar);
                    CommonExtKt.visible(imageView3);
                    if (item.box_type == 2) {
                        if (item.getHistory() != null) {
                            progressBar.setMax(item.getHistory().getRuntime() * 60);
                            progressBar.setProgress(item.getHistory().getSeconds());
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String format = String.format("S%s E%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getHistory().getSeason()), Integer.valueOf(item.getHistory().getEpisode())}, 2));
                            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                            CommonExtKt.textShadow$default(textView, format, 0, 0, 6, null);
                            CommonExtKt.visible(textView);
                            return;
                        }
                        CommonExtKt.gone(textView);
                        progressBar.setMax(1);
                        progressBar.setProgress(0);
                        return;
                    }
                    CommonExtKt.gone(textView);
                    progressBar.setMax(item.getRuntime() * 60);
                    progressBar.setProgress(item.getSeconds());
                    return;
                case 3:
                    ImageView imageView4 = (ImageView) helper.getView(R.id.movie_item_poster);
                    ImageView imageView5 = (ImageView) helper.getView(R.id.ivStar);
                    TextView textView2 = (TextView) helper.getView(R.id.tvImdbRating);
                    ImageView imageView6 = (ImageView) helper.getView(R.id.movie_item_desc);
                    ImageView imageView7 = (ImageView) helper.getView(R.id.ivTomato);
                    TextView textView3 = (TextView) helper.getView(R.id.tvTomatoMeter);
                    String str2 = item.id;
                    if (str2 == null || str2.length() == 0) {
                        CommonExtKt.gone(textView3);
                        CommonExtKt.gone(imageView7);
                        setViewGone(imageView5, textView2, imageView6);
                        GlideUtils.loadWithCorner(getContext(), (int) R.drawable.ic_movie_more, imageView4, CommonExtKt.dp2Px(8));
                        return;
                    }
                    imageView7.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                    if (item.getTomato_meter() == 0) {
                        CommonExtKt.gone(textView3);
                        CommonExtKt.gone(imageView7);
                    } else {
                        CommonExtKt.visible(textView3);
                        CommonExtKt.visible(imageView7);
                        StringBuilder sb = new StringBuilder();
                        sb.append(item.getTomato_meter());
                        sb.append('%');
                        CommonExtKt.textShadow$default(textView3, sb.toString(), 0, 0, 6, null);
                    }
                    ImageView imageView8 = imageView6;
                    CommonExtKt.visible(imageView8);
                    String imdb_rating = item.getImdb_rating();
                    if ((imdb_rating == null || imdb_rating.length() == 0) || Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                        setViewGone(textView2, imageView5);
                    } else {
                        setViewVisible(textView2, imageView5);
                        String imdb_rating2 = item.getImdb_rating();
                        Intrinsics.checkNotNullExpressionValue(imdb_rating2, "item.imdb_rating");
                        CommonExtKt.textShadow$default(textView2, imdb_rating2, 0, 0, 6, null);
                    }
                    String str3 = item.quality_tag_new;
                    if ((str3 == null || str3.length() == 0) ? true : true) {
                        CommonExtKt.gone(imageView8);
                    } else {
                        CommonExtKt.visible(imageView8);
                        imageView6.setImageResource(CommonUtils.getMovieTag(item.quality_tag_new));
                    }
                    GlideUtils.loadCornerPortraitGifHolder(getContext(), item.poster, imageView4, 8);
                    return;
                case 4:
                    TextView textView4 = (TextView) helper.getView(R.id.tv_item_season);
                    SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.slanted_text_view);
                    TextView textView5 = (TextView) helper.getView(R.id.tvImdbRating);
                    ImageView imageView9 = (ImageView) helper.getView(R.id.ivStar);
                    ImageView imageView10 = (ImageView) helper.getView(R.id.tv_item_poster);
                    Group group = (Group) helper.getView(R.id.group);
                    ImageView imageView11 = (ImageView) helper.getView(R.id.ivTomato);
                    TextView textView6 = (TextView) helper.getView(R.id.tvTomatoMeter);
                    String str4 = item.id;
                    if (str4 == null || str4.length() == 0) {
                        setViewGone(textView4, slantedTextView, textView5, imageView9, group);
                        GlideUtils.load(getContext(), (int) R.drawable.ic_movie_more, imageView10);
                        CommonExtKt.gone(textView6);
                        CommonExtKt.gone(imageView11);
                        return;
                    }
                    imageView11.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                    if (item.getTomato_meter() == 0) {
                        CommonExtKt.gone(textView6);
                        CommonExtKt.gone(imageView11);
                    } else {
                        CommonExtKt.visible(textView6);
                        CommonExtKt.visible(imageView11);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(item.getTomato_meter());
                        sb2.append('%');
                        CommonExtKt.textShadow$default(textView6, sb2.toString(), 0, 0, 6, null);
                    }
                    String str5 = item.season_episode;
                    if (!(str5 == null || str5.length() == 0)) {
                        textView4.setText(item.season_episode);
                        CommonExtKt.visible(textView4);
                    } else {
                        CommonExtKt.gone(textView4);
                    }
                    String str6 = item.update_title;
                    if (!(str6 == null || str6.length() == 0)) {
                        slantedTextView.setText(item.update_title);
                        CommonExtKt.visible(group);
                        CommonExtKt.visible(slantedTextView);
                    } else {
                        CommonExtKt.gone(group);
                        CommonExtKt.gone(slantedTextView);
                    }
                    String imdb_rating3 = item.getImdb_rating();
                    if (((imdb_rating3 == null || imdb_rating3.length() == 0) ? true : true) || Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                        CommonExtKt.gone(textView5);
                        CommonExtKt.gone(imageView9);
                    } else {
                        CommonExtKt.visible(textView5);
                        CommonExtKt.visible(imageView9);
                        String imdb_rating4 = item.getImdb_rating();
                        Intrinsics.checkNotNullExpressionValue(imdb_rating4, "item.imdb_rating");
                        CommonExtKt.textShadow$default(textView5, imdb_rating4, 0, 0, 6, null);
                    }
                    GlideUtils.loadPortraitGifHolder(getContext(), item.poster, imageView10);
                    return;
                case 5:
                    ImageView imageView12 = (ImageView) helper.getView(R.id.ivAvatar);
                    TextView textView7 = (TextView) helper.getView(R.id.tvNameFirst);
                    TextView textView8 = (TextView) helper.getView(R.id.tvName);
                    TextView textView9 = (TextView) helper.getView(R.id.tvJob);
                    String avatar = item.getAvatar();
                    if ((avatar == null || avatar.length() == 0) ? true : true) {
                        CommonExtKt.gone(imageView12);
                        CommonExtKt.visible(textView7);
                        textView7.setText(CommonUtils.getNameFirstLetter(item.getName()));
                    } else {
                        CommonExtKt.visible(imageView12);
                        CommonExtKt.gone(textView7);
                        GlideUtils.loadWithCircle(getContext(), item.getAvatar(), imageView12, 88, (int) R.drawable.image_loading_placeholer);
                    }
                    textView8.setText(item.getName());
                    textView9.setText(item.getJob());
                    return;
                case 6:
                    GlideUtils.loadWithCorner(getContext(), item.getImg(), (ImageView) helper.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 8.0f));
                    return;
                case 7:
                    ImageView imageView13 = (ImageView) helper.getView(R.id.imageView);
                    TextView textView10 = (TextView) helper.getView(R.id.tv_num);
                    TextView textView11 = (TextView) helper.getView(R.id.tv_name);
                    if (item.getImgArr() != null) {
                        List<String> imgArr = item.getImgArr();
                        Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
                        if (!imgArr.isEmpty()) {
                            GlideUtils.loadWithCorner(getContext(), item.getImgArr().get(0), imageView13, DensityUtils.dp2px(App.getContext(), 8.0f));
                        }
                    }
                    CommonExtKt.textShadow$default(textView10, item.getCount().toString(), 0, 0, 6, null);
                    String name = item.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "item.name");
                    CommonExtKt.textBoldShadow$default(textView11, name, 0, 0, 6, null);
                    return;
                case 8:
                    GlideUtils.loadCornerLandGifHolder(getContext(), item.poster, (ImageView) helper.getView(R.id.imageView), 8);
                    return;
                case 9:
                    ImageView imageView14 = (ImageView) helper.getView(R.id.movie_item_poster);
                    ImageView imageView15 = (ImageView) helper.getView(R.id.ivStar);
                    TextView textView12 = (TextView) helper.getView(R.id.tvImdbRating);
                    ImageView imageView16 = (ImageView) helper.getView(R.id.movie_item_desc);
                    ImageView imageView17 = (ImageView) helper.getView(R.id.ivTomato);
                    TextView textView13 = (TextView) helper.getView(R.id.tvTomatoMeter);
                    String str7 = item.id;
                    if (str7 == null || str7.length() == 0) {
                        CommonExtKt.gone(textView13);
                        CommonExtKt.gone(imageView17);
                        setViewGone(imageView15, textView12, imageView16);
                        GlideUtils.loadWithCorner(getContext(), (int) R.drawable.ic_movie_more, imageView14, CommonExtKt.dp2Px(8));
                        helper.setVisible(R.id.ivDislike, false);
                        return;
                    }
                    imageView17.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                    if (item.getTomato_meter() == 0) {
                        CommonExtKt.gone(textView13);
                        CommonExtKt.gone(imageView17);
                    } else {
                        CommonExtKt.visible(textView13);
                        CommonExtKt.visible(imageView17);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(item.getTomato_meter());
                        sb3.append('%');
                        CommonExtKt.textShadow$default(textView13, sb3.toString(), 0, 0, 6, null);
                    }
                    ImageView imageView18 = imageView16;
                    CommonExtKt.visible(imageView18);
                    String imdb_rating5 = item.getImdb_rating();
                    if ((imdb_rating5 == null || imdb_rating5.length() == 0) || Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                        setViewGone(textView12, imageView15);
                    } else {
                        setViewVisible(textView12, imageView15);
                        String imdb_rating6 = item.getImdb_rating();
                        Intrinsics.checkNotNullExpressionValue(imdb_rating6, "item.imdb_rating");
                        CommonExtKt.textShadow$default(textView12, imdb_rating6, 0, 0, 6, null);
                    }
                    String str8 = item.quality_tag_new;
                    if ((str8 == null || str8.length() == 0) ? true : true) {
                        CommonExtKt.gone(imageView18);
                    } else {
                        CommonExtKt.visible(imageView18);
                        imageView16.setImageResource(CommonUtils.getMovieTag(item.quality_tag_new));
                    }
                    GlideUtils.loadCornerPortraitGifHolder(getContext(), item.poster, imageView14, 8);
                    helper.setVisible(R.id.ivDislike, true);
                    return;
                default:
                    return;
            }
        }

        private final void setViewGone(View... viewArr) {
            int length = viewArr.length;
            int i = 0;
            while (i < length) {
                View view = viewArr[i];
                i++;
                CommonExtKt.gone(view);
            }
        }

        private final void setViewVisible(View... viewArr) {
            int length = viewArr.length;
            int i = 0;
            while (i < length) {
                View view = viewArr[i];
                i++;
                CommonExtKt.visible(view);
            }
        }
    }
}
