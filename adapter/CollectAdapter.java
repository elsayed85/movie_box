package com.movieboxpro.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.module.UpFetchModule;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.GetPlanPathLiveData;
import com.movieboxpro.android.livedata.GoMoreWatchingLiveData;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.model.WatchingItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.widget.CustomProgressView;
import io.reactivex.Observable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: CollectAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u00122\u00020\u00012\u00020\u00022\u00020\u0003:\u0007\u0012\u0013\u0014\u0015\u0016\u0017\u0018B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0014¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter;", "Lcom/chad/library/adapter/base/BaseNodeAdapter;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "Lcom/chad/library/adapter/base/module/UpFetchModule;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;)V", "getItemType", "", "data", "", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "position", "isFixedViewType", "", IjkMediaMeta.IJKM_KEY_TYPE, "Companion", "FavoriteEpisodeProvider", "FavoriteMovieProvider", "FavoriteProvider", "FavoriteSeasonProvider", "FavoriteTitleProvider", "FavoriteWatchingProvider", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CollectAdapter extends BaseNodeAdapter implements LoadMoreModule, UpFetchModule {
    public static final Companion Companion = new Companion(null);
    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectAdapter(LifecycleOwner lifecycleOwner, LoadView loadView) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(loadView, "loadView");
        addFullSpanNodeProvider(new FavoriteTitleProvider(5, R.layout.adapter_favorite_title_item));
        addFullSpanNodeProvider(new FavoriteWatchingProvider(lifecycleOwner, loadView, 6, R.layout.watch_plan_top_layout));
        addNodeProvider(new FavoriteProvider(lifecycleOwner, loadView, 1, R.layout.adapter_favorite_waiting_item));
        addNodeProvider(new FavoriteSeasonProvider(loadView, lifecycleOwner, 2, R.layout.adapter_favorite_season_item));
        addNodeProvider(new FavoriteEpisodeProvider(3, R.layout.adapter_favorite_episode_item));
        addNodeProvider(new FavoriteMovieProvider(4, R.layout.adapter_favorite_waiting_movie_item));
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$Companion;", "", "()V", "EXPAND_COLLAPSE_PAYLOAD", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseNodeAdapter, com.chad.library.adapter.base.BaseQuickAdapter
    public boolean isFixedViewType(int i) {
        return super.isFixedViewType(i) || i == 5 || i == 6;
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends BaseNode> data, int i) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseNode baseNode = data.get(i);
        if (baseNode instanceof FavoriteItem) {
            FavoriteItem favoriteItem = (FavoriteItem) baseNode;
            if (favoriteItem.getFavoriteTitle() != null) {
                return 5;
            }
            if (favoriteItem.getWatchingItem() != null) {
                return 6;
            }
            return favoriteItem.getBox_type() == 1 ? 4 : 1;
        } else if (baseNode instanceof FavoriteSeasonItem) {
            return 2;
        } else {
            return baseNode instanceof FavoriteEpisodeItem ? 3 : 1;
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J:\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J2\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J*\u0010\u001f\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteWatchingProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "itemViewType", "", "layoutId", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "markTv", "id", "", "season", "episode", DownloadInfo.DOWNLOAD_OVER, "position", "watchingAdapter", "Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteWatchingProvider$WatchingAdapter;", "markWatched", "boxType", "watched", "removeFavorite", "WatchingAdapter", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteWatchingProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;
        private final LifecycleOwner lifecycleOwner;
        private final LoadView loadView;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteWatchingProvider(LifecycleOwner lifecycleOwner, LoadView loadView, int i, int i2) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(loadView, "loadView");
            this.lifecycleOwner = lifecycleOwner;
            this.loadView = loadView;
            this.itemViewType = i;
            this.layoutId = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markWatched(int i, WatchingAdapter watchingAdapter, String str, int i2, int i3) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", Integer.valueOf(i2)).param("mid", str).param("watched", Integer.valueOf(i3)).asRequest(), this.lifecycleOwner), new CollectAdapter$FavoriteWatchingProvider$markWatched$1(this), null, new CollectAdapter$FavoriteWatchingProvider$markWatched$2(this), null, new CollectAdapter$FavoriteWatchingProvider$markWatched$3(this, watchingAdapter, i), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markTv(String str, String str2, String str3, int i, int i2, WatchingAdapter watchingAdapter) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this.lifecycleOwner), new CollectAdapter$FavoriteWatchingProvider$markTv$1(this), null, new CollectAdapter$FavoriteWatchingProvider$markTv$2(this), null, new CollectAdapter$FavoriteWatchingProvider$markTv$3(this, watchingAdapter, i2), 10, null);
        }

        private final void removeFavorite(String str, int i, int i2, WatchingAdapter watchingAdapter) {
            RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this.lifecycleOwner, "User_watch_plan_del").param("mid", str).param("box_type", Integer.valueOf(i)).asMsg(), new CollectAdapter$FavoriteWatchingProvider$removeFavorite$1(this), null, new CollectAdapter$FavoriteWatchingProvider$removeFavorite$2(this), null, new CollectAdapter$FavoriteWatchingProvider$removeFavorite$3(this, watchingAdapter, i2), 10, null);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, BaseNode item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            final WatchingItem watchingItem = ((FavoriteItem) item).getWatchingItem();
            RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
            LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llWatching);
            LinearLayout linearLayout2 = (LinearLayout) helper.getView(R.id.llWaiting);
            TextView textView = (TextView) helper.getView(R.id.tvWatchingCount);
            TextView textView2 = (TextView) helper.getView(R.id.tvWaitingCount);
            boolean z = false;
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new LinearItemDecoration(0, 8, false));
                watchingItem.setAdapter(new WatchingAdapter(new ArrayList()));
                watchingItem.getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$CollectAdapter$FavoriteWatchingProvider$EjZDj_vtiOuiUhcCisO1rwzW_OM
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        CollectAdapter.FavoriteWatchingProvider.m56convert$lambda0(WatchingItem.this, baseQuickAdapter, view, i);
                    }
                });
                watchingItem.getAdapter().addChildClickViewIds(R.id.ivDetail, R.id.ivMark, R.id.ivMore);
                watchingItem.getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$CollectAdapter$FavoriteWatchingProvider$yX2msQgrzV45_5ee7lRW2FEvFUo
                    @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                    public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        CollectAdapter.FavoriteWatchingProvider.m57convert$lambda3(WatchingItem.this, this, baseQuickAdapter, view, i);
                    }
                });
                recyclerView.setTag("added");
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            recyclerView.setAdapter(watchingItem.getAdapter());
            WatchingAdapter adapter = watchingItem.getAdapter();
            ArrayList<FavoriteItem> list = watchingItem.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            adapter.setNewInstance(list);
            ArrayList<FavoriteItem> list2 = watchingItem.getList();
            if ((list2 == null || list2.isEmpty()) ? true : true) {
                CommonExtKt.gone(linearLayout);
                CommonExtKt.gone(recyclerView);
            } else {
                CommonExtKt.visible(linearLayout);
                CommonExtKt.visible(recyclerView);
            }
            if (watchingItem.isHaveWaiting()) {
                CommonExtKt.visible(linearLayout2);
            } else {
                CommonExtKt.gone(linearLayout2);
            }
            if (watchingItem.getWaitingNum() == 0) {
                textView2.setText("");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append((char) 65288);
                sb.append(watchingItem.getWaitingNum());
                sb.append((char) 65289);
                textView2.setText(sb.toString());
            }
            if (watchingItem.getWatchingNum() == 0) {
                textView.setText("");
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append((char) 65288);
            sb2.append(watchingItem.getWatchingNum());
            sb2.append((char) 65289);
            textView.setText(sb2.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: convert$lambda-0  reason: not valid java name */
        public static final void m56convert$lambda0(WatchingItem watchingItem, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            if (i == 10) {
                GoMoreWatchingLiveData.Companion.get().setValue(true);
                return;
            }
            FavoriteItem item = watchingItem.getAdapter().getItem(i);
            GetPlanPathLiveData.Companion.get().setValue(MapsKt.hashMapOf(TuplesKt.to("id", item.getId()), TuplesKt.to("box_type", Integer.valueOf(item.getBox_type())), TuplesKt.to("season", Integer.valueOf((item.getBox_type() != 2 || item.getLast_episode() == null) ? 1 : item.getLast_episode().getSeason())), TuplesKt.to("episode", Integer.valueOf((item.getBox_type() != 2 || item.getLast_episode() == null) ? 1 : item.getLast_episode().getEpisode()))));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: convert$lambda-3  reason: not valid java name */
        public static final void m57convert$lambda3(final WatchingItem watchingItem, final FavoriteWatchingProvider this$0, BaseQuickAdapter noName_0, View v, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(v, "v");
            final FavoriteItem item = watchingItem.getAdapter().getItem(i);
            switch (v.getId()) {
                case R.id.ivDetail /* 2131296946 */:
                    if (item.getBox_type() == 1) {
                        MovieDetailActivity.start(this$0.getContext(), item.getId(), item.getPoster());
                        return;
                    } else {
                        TvDetailActivity.start(this$0.getContext(), item.getId());
                        return;
                    }
                case R.id.ivMark /* 2131296969 */:
                    if (item.getLast_episode() == null) {
                        WatchingAdapter adapter = watchingItem.getAdapter();
                        Intrinsics.checkNotNullExpressionValue(adapter, "watchingItem.adapter");
                        this$0.markWatched(i, adapter, item.getId(), item.getBox_type(), 1);
                        return;
                    }
                    String id = item.getId();
                    String valueOf = String.valueOf(item.getLast_episode().getSeason());
                    String valueOf2 = String.valueOf(item.getLast_episode().getEpisode());
                    WatchingAdapter adapter2 = watchingItem.getAdapter();
                    Intrinsics.checkNotNullExpressionValue(adapter2, "watchingItem.adapter");
                    this$0.markTv(id, valueOf, valueOf2, 1, i, adapter2);
                    return;
                case R.id.ivMore /* 2131296970 */:
                    ArrayList<Pair> arrayList = new ArrayList();
                    if (item.getBox_type() == 1) {
                        arrayList.add(new Pair("ADD THIS MOVIE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteWatchingProvider$convert$2$1
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                CollectAdapter.FavoriteWatchingProvider favoriteWatchingProvider = CollectAdapter.FavoriteWatchingProvider.this;
                                int i3 = i;
                                CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter3 = watchingItem.getAdapter();
                                Intrinsics.checkNotNullExpressionValue(adapter3, "watchingItem.adapter");
                                favoriteWatchingProvider.markWatched(i3, adapter3, item.getId(), 1, 1);
                            }
                        }));
                        arrayList.add(new Pair("ADD THIS MOVIE TO WAITING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteWatchingProvider$convert$2$2
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                CollectAdapter.FavoriteWatchingProvider favoriteWatchingProvider = CollectAdapter.FavoriteWatchingProvider.this;
                                int i3 = i;
                                CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter3 = watchingItem.getAdapter();
                                Intrinsics.checkNotNullExpressionValue(adapter3, "watchingItem.adapter");
                                favoriteWatchingProvider.markWatched(i3, adapter3, item.getId(), 1, 0);
                            }
                        }));
                    } else {
                        if (item.getLast_episode() != null) {
                            arrayList.add(new Pair("ADD CURRENT EPISODE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteWatchingProvider$convert$2$3
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i2) {
                                    CollectAdapter.FavoriteWatchingProvider favoriteWatchingProvider = CollectAdapter.FavoriteWatchingProvider.this;
                                    String id2 = item.getId();
                                    String valueOf3 = String.valueOf(item.getLast_episode().getSeason());
                                    String valueOf4 = String.valueOf(item.getLast_episode().getEpisode());
                                    int i3 = i;
                                    CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter3 = watchingItem.getAdapter();
                                    Intrinsics.checkNotNullExpressionValue(adapter3, "watchingItem.adapter");
                                    favoriteWatchingProvider.markTv(id2, valueOf3, valueOf4, 1, i3, adapter3);
                                }
                            }));
                            arrayList.add(new Pair("ADD CURRENT SEASON TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteWatchingProvider$convert$2$4
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i2) {
                                    CollectAdapter.FavoriteWatchingProvider favoriteWatchingProvider = CollectAdapter.FavoriteWatchingProvider.this;
                                    String id2 = item.getId();
                                    String valueOf3 = String.valueOf(item.getLast_episode().getSeason());
                                    int i3 = i;
                                    CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter3 = watchingItem.getAdapter();
                                    Intrinsics.checkNotNullExpressionValue(adapter3, "watchingItem.adapter");
                                    favoriteWatchingProvider.markTv(id2, valueOf3, "", 1, i3, adapter3);
                                }
                            }));
                        }
                        arrayList.add(new Pair("ADD THIS TV SHOW TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteWatchingProvider$convert$2$5
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                CollectAdapter.FavoriteWatchingProvider favoriteWatchingProvider = CollectAdapter.FavoriteWatchingProvider.this;
                                int i3 = i;
                                CollectAdapter.FavoriteWatchingProvider.WatchingAdapter adapter3 = watchingItem.getAdapter();
                                Intrinsics.checkNotNullExpressionValue(adapter3, "watchingItem.adapter");
                                favoriteWatchingProvider.markWatched(i3, adapter3, item.getId(), 2, 1);
                            }
                        }));
                    }
                    ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                    for (Pair pair : arrayList) {
                        canceledOnTouchOutside.addSheetItem((String) pair.getFirst(), ActionSheetDialog.SheetItemColor.White, (ActionSheetDialog.OnSheetItemClickListener) pair.getSecond());
                    }
                    canceledOnTouchOutside.addSheetItem(item.getBox_type() == 1 ? "REMOVE THIS MOVIE FROM WATCHING" : "REMOVE THIS TV SHOW FROM WATCHING", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.adapter.-$$Lambda$CollectAdapter$FavoriteWatchingProvider$0wZ9zD1lJB6BQFYw8_qK8FaUzWU
                        @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                        public final void onClick(int i2) {
                            CollectAdapter.FavoriteWatchingProvider.m58convert$lambda3$lambda2(CollectAdapter.FavoriteWatchingProvider.this, item, i, watchingItem, i2);
                        }
                    }).show();
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: convert$lambda-3$lambda-2  reason: not valid java name */
        public static final void m58convert$lambda3$lambda2(FavoriteWatchingProvider this$0, FavoriteItem item, int i, WatchingItem watchingItem, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(item, "$item");
            String id = item.getId();
            int box_type = item.getBox_type();
            WatchingAdapter adapter = watchingItem.getAdapter();
            Intrinsics.checkNotNullExpressionValue(adapter, "watchingItem.adapter");
            this$0.removeFavorite(id, box_type, i, adapter);
        }

        /* compiled from: CollectAdapter.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0005j\b\u0012\u0004\u0012\u00020\u0002`\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteWatchingProvider$WatchingAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "convert", "", "holder", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class WatchingAdapter extends BaseQuickAdapter<FavoriteItem, BaseViewHolder> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public WatchingAdapter(ArrayList<FavoriteItem> list) {
                super(R.layout.adapter_watching_item, list);
                Intrinsics.checkNotNullParameter(list, "list");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, FavoriteItem item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ImageView imageView = (ImageView) holder.getView(R.id.ivPoster);
                ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressBar);
                ImageView imageView2 = (ImageView) holder.getView(R.id.ivContinue);
                TextView textView = (TextView) holder.getView(R.id.tvSeasonEpisode);
                ImageView imageView3 = (ImageView) holder.getView(R.id.ivDetail);
                ImageView imageView4 = (ImageView) holder.getView(R.id.ivMark);
                ImageView imageView5 = (ImageView) holder.getView(R.id.ivMore);
                if (holder.getAdapterPosition() == 10) {
                    GlideUtils.loadWithCorner(getContext(), (int) R.drawable.ic_movie_more, imageView, CommonExtKt.dp2Px(8));
                    CommonExtKt.gone(progressBar);
                    CommonExtKt.gone(imageView2);
                    CommonExtKt.invisible(textView);
                    CommonExtKt.gone(imageView3);
                    CommonExtKt.gone(imageView4);
                    CommonExtKt.gone(imageView5);
                    return;
                }
                CommonExtKt.visible(imageView4);
                CommonExtKt.visible(imageView5);
                CommonExtKt.visible(imageView2);
                GlideUtils.loadCornerPortraitGifHolder(getContext(), item.getPoster(), imageView, 8);
                CommonExtKt.visible(progressBar);
                CommonExtKt.visible(imageView3);
                if (item.getBox_type() == 2) {
                    if (item.getLast_episode() != null) {
                        progressBar.setMax(item.getLast_episode().getRuntime() * 60);
                        progressBar.setProgress(item.getLast_episode().getSeconds());
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("S%s E%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getLast_episode().getSeason()), Integer.valueOf(item.getLast_episode().getEpisode())}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        CommonExtKt.textShadow$default(textView, format, 0, 0, 6, null);
                        CommonExtKt.visible(textView);
                        return;
                    }
                    CommonExtKt.invisible(textView);
                    progressBar.setMax(1);
                    progressBar.setProgress(0);
                    return;
                }
                CommonExtKt.invisible(textView);
                progressBar.setMax(item.getRuntime() * 60);
                progressBar.setProgress(item.getSeconds());
            }
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J(\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteMovieProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "itemViewType", "", "layoutId", "(II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "onClick", "view", "Landroid/view/View;", "data", "position", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteMovieProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteMovieProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, BaseNode item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            FavoriteItem favoriteItem = (FavoriteItem) item;
            helper.setText(R.id.tvName, favoriteItem.getTitle());
            helper.setText(R.id.tvTime, favoriteItem.getReleased());
            helper.setText(R.id.tvTag, favoriteItem.getYear());
            GlideUtils.loadWithCorner(getContext(), favoriteItem.getPoster(), (ImageView) helper.getView(R.id.ivPoster), CommonExtKt.dp2Px(4));
            helper.setImageResource(R.id.ivQuality, CommonUtils.getMovieTag(favoriteItem.getQuality_tag_new()));
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            if (!Intrinsics.areEqual(favoriteItem.getYear(), "0")) {
                String year = favoriteItem.getYear();
                if (!(year == null || StringsKt.isBlank(year))) {
                    sb.append(favoriteItem.getYear());
                }
            }
            String cats = favoriteItem.getCats();
            if (!(cats == null || StringsKt.isBlank(cats))) {
                sb.append(" | ");
                sb.append(favoriteItem.getCats());
            }
            if (favoriteItem.getRuntime() != 0) {
                sb.append(" | ");
                sb.append(favoriteItem.getRuntime() + "min");
            }
            helper.setText(R.id.tvTag, sb.toString());
            TextView textView = (TextView) helper.getView(R.id.tvImdbRating);
            String imdb_rating = favoriteItem.getImdb_rating();
            if ((imdb_rating == null || imdb_rating.length() == 0) || Intrinsics.areEqual(favoriteItem.getImdb_rating(), "0")) {
                CommonExtKt.textShadow$default(textView, "-.-", 0, 0, 6, null);
            } else {
                String imdb_rating2 = favoriteItem.getImdb_rating();
                Intrinsics.checkNotNullExpressionValue(imdb_rating2, "item.imdb_rating");
                CommonExtKt.textShadow$default(textView, imdb_rating2, 0, 0, 6, null);
            }
            TextView textView2 = (TextView) helper.getView(R.id.tvRefresh);
            String tomato_meter = favoriteItem.getTomato_meter();
            if (((tomato_meter == null || tomato_meter.length() == 0) ? true : true) || Intrinsics.areEqual(favoriteItem.getTomato_meter(), "0")) {
                CommonExtKt.textShadow$default(textView2, "-.-%", 0, 0, 6, null);
            } else {
                CommonExtKt.textShadow$default(textView2, Intrinsics.stringPlus(favoriteItem.getTomato_meter(), "%"), 0, 0, 6, null);
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.clLike);
            ImageView imageView = (ImageView) helper.getView(R.id.ivRight);
            TextView textView3 = (TextView) helper.getView(R.id.tvWatchTime);
            ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
            TextView textView4 = (TextView) helper.getView(R.id.tvProgress);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivView);
            ImageView imageView3 = (ImageView) helper.getView(R.id.ivPlay);
            if (favoriteItem.isWatched()) {
                CommonExtKt.gone(imageView3);
                CommonExtKt.visible(imageView2);
                CommonExtKt.visible(constraintLayout);
                imageView.setImageResource(R.mipmap.ic_blue_duihao);
                if (favoriteItem.getLast_play_time() == 0) {
                    textView3.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getMark_time() * 1000));
                } else {
                    textView3.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getLast_play_time() * 1000));
                }
                CommonExtKt.visible(textView3);
                CommonExtKt.gone(progressBar);
                CommonExtKt.gone(textView4);
                ImageView imageView4 = (ImageView) helper.getView(R.id.ivLike);
                TextView textView5 = (TextView) helper.getView(R.id.tvLike);
                ImageView imageView5 = (ImageView) helper.getView(R.id.ivDislike);
                TextView textView6 = (TextView) helper.getView(R.id.tvDislike);
                if (favoriteItem.getLike_status() == 0) {
                    imageView4.setImageResource(R.mipmap.ic_like);
                    imageView5.setImageResource(R.mipmap.ic_yellow_dislike);
                    textView5.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                    if (favoriteItem.getLike_total() == 0) {
                        textView5.setText("LIKE");
                    } else {
                        textView5.setText(String.valueOf(favoriteItem.getLike_total()));
                    }
                    textView6.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                    if (favoriteItem.getDislike_total() == 0) {
                        textView6.setText("DISLIKE");
                        return;
                    } else {
                        textView6.setText(String.valueOf(favoriteItem.getDislike_total()));
                        return;
                    }
                } else if (favoriteItem.getLike_status() == 1) {
                    imageView4.setImageResource(R.mipmap.ic_yellow_liked);
                    imageView5.setImageResource(R.mipmap.ic_yellow_dislike);
                    textView5.setTextColor(Color.parseColor("#FFEFC001"));
                    textView5.setText(String.valueOf(favoriteItem.getLike_total()));
                    textView6.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                    if (favoriteItem.getDislike_total() == 0) {
                        textView6.setText("DISLIKE");
                        return;
                    } else {
                        textView6.setText(String.valueOf(favoriteItem.getDislike_total()));
                        return;
                    }
                } else if (favoriteItem.getLike_status() == 2) {
                    imageView4.setImageResource(R.mipmap.ic_like);
                    imageView5.setImageResource(R.mipmap.ic_yellow_disliked);
                    textView6.setTextColor(Color.parseColor("#FFEFC001"));
                    textView6.setText(String.valueOf(favoriteItem.getDislike_total()));
                    textView5.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                    if (favoriteItem.getLike_total() == 0) {
                        textView5.setText("LIKE");
                        return;
                    } else {
                        textView5.setText(String.valueOf(favoriteItem.getLike_total()));
                        return;
                    }
                } else {
                    return;
                }
            }
            CommonExtKt.visible(imageView3);
            CommonExtKt.gone(constraintLayout);
            imageView.setImageResource(R.mipmap.ic_gray_right);
            ProgressBar progressBar2 = progressBar;
            CommonExtKt.visible(progressBar2);
            TextView textView7 = textView4;
            CommonExtKt.visible(textView7);
            if (favoriteItem.getWaiting() == 1) {
                CommonExtKt.gone(imageView2);
                CommonExtKt.gone(textView3);
                CommonExtKt.gone(progressBar2);
                CommonExtKt.gone(textView7);
                return;
            }
            CommonExtKt.visible(imageView2);
            CommonExtKt.visible(textView3);
            CommonExtKt.visible(progressBar2);
            CommonExtKt.visible(textView7);
            if (favoriteItem.getLast_play_time() == 0) {
                textView3.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getMark_time() * 1000));
            } else {
                textView3.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getLast_play_time() * 1000));
            }
            textView4.setText(TimeUtils.getTime(favoriteItem.getSeconds()) + '/' + TimeUtils.getTime(favoriteItem.getRuntime() * 60));
            progressBar.setMax(favoriteItem.getRuntime() * 60);
            progressBar.setProgress(favoriteItem.getSeconds());
        }

        /* JADX WARN: Type inference failed for: r2v3, types: [com.chad.library.adapter.base.BaseNodeAdapter, java.lang.Object] */
        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onClick(BaseViewHolder helper, View view, BaseNode data, int i) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(data, "data");
            ?? adapter = getAdapter2();
            Intrinsics.checkNotNull(adapter);
            adapter.expandOrCollapse(i, true, true, 110);
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J,\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J \u0010!\u001a\u0004\u0018\u00010\u00142\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\u0006\u0010#\u001a\u00020\u0007H\u0002J\"\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'H\u0002J@\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020-2\u0006\u00101\u001a\u00020/H\u0002JB\u00102\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\b\u00104\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u0007H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "itemViewType", "", "layoutId", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;II)V", "getItemViewType", "()I", "getLayoutId", "calPercent", "", "diliverNum", "queryMailNum", "checkIncludeRange", "", "startEpisodeItem", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "endEpisodeItem", "season", "episode", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "payloads", "", "", "findLeftRangePoint", "episodes", "position", "getLastEpisode", "tid", "favoriteItem", "Lcom/movieboxpro/android/model/FavoriteItem;", "initLikeStatus", NotificationCompat.CATEGORY_STATUS, "likeNum", "dislikeNum", "ivLike", "Landroid/widget/ImageView;", "tvLike", "Landroid/widget/TextView;", "ivDislike", "tvDislike", "markWatched", DownloadInfo.DOWNLOAD_OVER, "id", "tvPos", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;
        private final LifecycleOwner lifecycleOwner;
        private final LoadView loadView;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public /* bridge */ /* synthetic */ void convert(BaseViewHolder baseViewHolder, BaseNode baseNode, List list) {
            convert2(baseViewHolder, baseNode, (List<? extends Object>) list);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteProvider(LifecycleOwner lifecycleOwner, LoadView loadView, int i, int i2) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(loadView, "loadView");
            this.lifecycleOwner = lifecycleOwner;
            this.loadView = loadView;
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(final BaseViewHolder helper, final BaseNode item) {
            Object obj;
            char c;
            int i;
            int i2;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            FavoriteItem favoriteItem = (FavoriteItem) item;
            TextView textView = (TextView) helper.getView(R.id.tvUpdateCount);
            if (favoriteItem.getUpdateCount() == 0) {
                CommonExtKt.gone(textView);
            } else {
                CommonExtKt.visible(textView);
                textView.setText(String.valueOf(favoriteItem.getUpdateCount()));
            }
            TextView textView2 = (TextView) helper.getView(R.id.tvStarNum);
            ImageView imageView = (ImageView) helper.getView(R.id.ivStar);
            TextView textView3 = (TextView) helper.getView(R.id.tvTimeProgress);
            ProgressBar progressBar = (ProgressBar) helper.getView(R.id.pbCurr);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivView);
            ImageView imageView3 = (ImageView) helper.getView(R.id.ivPlay);
            final TextView textView4 = (TextView) helper.getView(R.id.tvTvName);
            boolean z = true;
            if (favoriteItem.getLast_episode() == null) {
                if (favoriteItem.isWatched()) {
                    CommonExtKt.gone(imageView3);
                } else {
                    CommonExtKt.visible(imageView3);
                }
                CommonExtKt.gone(imageView);
                CommonExtKt.gone(textView2);
                textView4.setText(favoriteItem.getTitle());
                StringBuilder sb = new StringBuilder();
                if (!Intrinsics.areEqual(favoriteItem.getYear(), "0")) {
                    String year = favoriteItem.getYear();
                    if (!(year == null || StringsKt.isBlank(year))) {
                        sb.append(favoriteItem.getYear());
                    }
                }
                String cats = favoriteItem.getCats();
                if (!(cats == null || StringsKt.isBlank(cats))) {
                    sb.append(" | ");
                    sb.append(favoriteItem.getCats());
                }
                if (favoriteItem.getRuntime() != 0) {
                    sb.append(" | ");
                    sb.append(favoriteItem.getRuntime() + "min");
                }
                CommonExtKt.gone(imageView2);
                CommonExtKt.gone(textView3);
                CommonExtKt.gone(progressBar);
                helper.setText(R.id.tvTag, sb.toString());
                GlideUtils.loadWithCorner(getContext(), favoriteItem.getBanner_mini(), (ImageView) helper.getView(R.id.ivPoster), CommonExtKt.dp2Px(9));
                obj = "0";
                c = '/';
                i2 = 0;
            } else {
                CommonExtKt.visible(imageView2);
                CommonExtKt.visible(textView3);
                CommonExtKt.visible(imageView);
                CommonExtKt.visible(textView2);
                if (favoriteItem.isWatched()) {
                    CommonExtKt.gone(imageView3);
                    CommonExtKt.gone(progressBar);
                    textView3.setText(TimeUtils.getTime(favoriteItem.getLast_episode().getRuntime() * 60));
                } else {
                    CommonExtKt.visible(imageView3);
                    CommonExtKt.visible(progressBar);
                    textView3.setText(TimeUtils.getTime(favoriteItem.getLast_episode().getSeconds()) + '/' + TimeUtils.getTime(favoriteItem.getLast_episode().getRuntime() * 60));
                }
                progressBar.setMax(favoriteItem.getLast_episode().getRuntime() * 60);
                progressBar.setProgress(favoriteItem.getLast_episode().getSeconds());
                String imdb_rating = favoriteItem.getLast_episode().getImdb_rating();
                if ((imdb_rating == null || imdb_rating.length() == 0) || Intrinsics.areEqual(favoriteItem.getLast_episode().getImdb_rating(), "0")) {
                    obj = "0";
                    c = '/';
                    i = R.id.tvTag;
                    i2 = 0;
                    CommonExtKt.textShadow$default(textView2, "-.-", 0, 0, 6, null);
                } else {
                    String imdb_rating2 = favoriteItem.getLast_episode().getImdb_rating();
                    Intrinsics.checkNotNullExpressionValue(imdb_rating2, "item.last_episode.imdb_rating");
                    i2 = 0;
                    obj = "0";
                    c = '/';
                    i = R.id.tvTag;
                    CommonExtKt.textShadow$default(textView2, imdb_rating2, 0, 0, 6, null);
                }
                textView4.setText('S' + CommonExtKt.format2(favoriteItem.getLast_episode().getSeason()) + 'E' + CommonExtKt.format2(favoriteItem.getLast_episode().getEpisode()) + " - " + ((Object) favoriteItem.getLast_episode().getTitle()));
                helper.setText(i, favoriteItem.getTitle());
                Context context = getContext();
                String thumbs = favoriteItem.getLast_episode().getThumbs();
                GlideUtils.loadWithCorner(context, thumbs == null || thumbs.length() == 0 ? favoriteItem.getBanner_mini() : favoriteItem.getLast_episode().getThumbs(), (ImageView) helper.getView(R.id.ivPoster), CommonExtKt.dp2Px(8));
            }
            GlideUtils.loadWithCorner(getContext(), favoriteItem.getPoster(), (ImageView) helper.getView(R.id.ivTvPoster), CommonExtKt.dp2Px(4));
            TextView textView5 = (TextView) helper.getView(R.id.tvSeasonEpisode);
            if (favoriteItem.getLast_episode() == null) {
                CommonExtKt.visible(textView5);
                textView5.setText(favoriteItem.getSeason_episode());
            } else {
                CommonExtKt.gone(textView5);
            }
            TextView textView6 = (TextView) helper.getView(R.id.tvImdbRating);
            String imdb_rating3 = favoriteItem.getImdb_rating();
            if ((imdb_rating3 == null || imdb_rating3.length() == 0) || Intrinsics.areEqual(favoriteItem.getImdb_rating(), obj)) {
                CommonExtKt.textShadow$default(textView6, "-.-", 0, 0, 6, null);
            } else {
                String imdb_rating4 = favoriteItem.getImdb_rating();
                Intrinsics.checkNotNullExpressionValue(imdb_rating4, "item.imdb_rating");
                CommonExtKt.textShadow$default(textView6, imdb_rating4, 0, 0, 6, null);
            }
            TextView textView7 = (TextView) helper.getView(R.id.tvRefresh);
            String tomato_meter = favoriteItem.getTomato_meter();
            if ((tomato_meter == null || tomato_meter.length() == 0) || Intrinsics.areEqual(favoriteItem.getTomato_meter(), obj)) {
                CommonExtKt.textShadow$default(textView7, "-.-%", 0, 0, 6, null);
            } else {
                CommonExtKt.textShadow$default(textView7, Intrinsics.stringPlus(favoriteItem.getTomato_meter(), "%"), 0, 0, 6, null);
            }
            final TextView textView8 = (TextView) helper.getView(R.id.tvTotalPercent);
            textView8.setText(Intrinsics.stringPlus(calPercent(favoriteItem.getWatchedEpisodeCount(), favoriteItem.getTotalEpisode()), "% WATCHED"));
            final TextView textView9 = (TextView) helper.getView(R.id.tvTotalSeason);
            textView9.setText(favoriteItem.getWatchedEpisodeCount() + c + favoriteItem.getTotalEpisode() + " episodes");
            TextView textView10 = (TextView) helper.getView(R.id.tvTvTime);
            String update_title = favoriteItem.getUpdate_title();
            if (update_title != null && update_title.length() != 0) {
                z = false;
            }
            if (z) {
                textView10.setText("This Show Has ended");
            } else {
                textView10.setText(favoriteItem.getUpdate_title());
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.clLike);
            ImageView imageView4 = (ImageView) helper.getView(R.id.ivRight);
            TextView textView11 = (TextView) helper.getView(R.id.tvWatchTime);
            if (favoriteItem.isWatched()) {
                CommonExtKt.visible(constraintLayout);
                imageView4.setImageResource(R.mipmap.ic_blue_duihao);
                if (favoriteItem.getLast_play_time() == 0) {
                    textView11.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getMark_time() * 1000));
                } else {
                    textView11.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getLast_play_time() * 1000));
                }
                CommonExtKt.visible(textView11);
                ImageView imageView5 = (ImageView) helper.getView(R.id.ivLike);
                TextView textView12 = (TextView) helper.getView(R.id.tvLike);
                ImageView imageView6 = (ImageView) helper.getView(R.id.ivDislike);
                TextView textView13 = (TextView) helper.getView(R.id.tvDislike);
                if (favoriteItem.getLast_episode() == null) {
                    initLikeStatus(favoriteItem.getLike_status(), favoriteItem.getLike_total(), favoriteItem.getDislike_total(), imageView5, textView12, imageView6, textView13);
                } else {
                    initLikeStatus(favoriteItem.getLast_episode().getLike_status(), favoriteItem.getLast_episode().getLike_total(), favoriteItem.getLast_episode().getDislike_total(), imageView5, textView12, imageView6, textView13);
                }
            } else {
                CommonExtKt.gone(constraintLayout);
                imageView4.setImageResource(R.mipmap.ic_gray_right);
                if (favoriteItem.getLast_episode() == null) {
                    CommonExtKt.gone(textView11);
                } else {
                    CommonExtKt.visible(textView11);
                    if (favoriteItem.getLast_play_time() == 0) {
                        textView11.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getMark_time() * 1000));
                    } else {
                        textView11.setText(TimeUtils.INSTANCE.formatPlayTime(favoriteItem.getLast_play_time() * 1000));
                    }
                }
            }
            ImageView imageView7 = (ImageView) helper.getView(R.id.ivExpand);
            if (favoriteItem.isExpanded()) {
                imageView7.setImageResource(R.mipmap.ic_unexpand);
            } else {
                imageView7.setImageResource(R.mipmap.ic_expand);
            }
            LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.linearLayout);
            LinearLayout linearLayout2 = (LinearLayout) helper.getView(R.id.llContainer);
            if (favoriteItem.isExpanded()) {
                linearLayout.setBackgroundResource(R.drawable.favorite_top8corner_bg_shape);
                DensityUtils.setMarginPx(linearLayout2, CommonExtKt.dp2Px(12), i2, CommonExtKt.dp2Px(12), i2);
            } else {
                linearLayout.setBackgroundResource(R.drawable.favorite_8corner_bg_shape);
                DensityUtils.setMarginPx(linearLayout2, CommonExtKt.dp2Px(12), i2, CommonExtKt.dp2Px(12), CommonExtKt.dp2Px(16));
            }
            CustomProgressView customProgressView = (CustomProgressView) helper.getView(R.id.progress);
            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) helper.getView(R.id.seekBar);
            appCompatSeekBar.setMax(favoriteItem.getTotalEpisode());
            appCompatSeekBar.setProgress(favoriteItem.getLastWatchedCount());
            appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteProvider$convert$1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i3, boolean z2) {
                    String calPercent;
                    String calPercent2;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    if (z2) {
                        int i4 = 0;
                        if (i3 != 0) {
                            if (((FavoriteItem) BaseNode.this).getLastWatchedItem() != null) {
                                int i5 = i3 - 1;
                                if (i5 >= ((FavoriteItem) BaseNode.this).getAllEpisodes().size()) {
                                    return;
                                }
                                FavoriteEpisodeItem favoriteEpisodeItem = ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i5);
                                ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(0);
                                if (favoriteEpisodeItem.getSeason() > ((FavoriteItem) BaseNode.this).getLastWatchedItem().getSeason() || (favoriteEpisodeItem.getSeason() == ((FavoriteItem) BaseNode.this).getLastWatchedItem().getSeason() && favoriteEpisodeItem.getEpisode() >= ((FavoriteItem) BaseNode.this).getLastWatchedItem().getEpisode())) {
                                    while (i4 < i3) {
                                        int i6 = i4 + 1;
                                        if (i4 <= ((FavoriteItem) BaseNode.this).getLastWatchedIndex()) {
                                            if (((FavoriteItem) BaseNode.this).getAllEpisodes().get(i4).getOver() == 1) {
                                                FavoriteItem favoriteItem2 = (FavoriteItem) BaseNode.this;
                                                favoriteItem2.setWatchedEpisodeCount(favoriteItem2.getWatchedEpisodeCount() + 1);
                                            }
                                        } else {
                                            FavoriteItem favoriteItem3 = (FavoriteItem) BaseNode.this;
                                            favoriteItem3.setWatchedEpisodeCount(favoriteItem3.getWatchedEpisodeCount() + 1);
                                        }
                                        i4 = i6;
                                    }
                                } else {
                                    while (i4 < i3) {
                                        int i7 = i4 + 1;
                                        if (((FavoriteItem) BaseNode.this).getAllEpisodes().get(i4).getOver() == 1) {
                                            FavoriteItem favoriteItem4 = (FavoriteItem) BaseNode.this;
                                            favoriteItem4.setWatchedEpisodeCount(favoriteItem4.getWatchedEpisodeCount() + 1);
                                        }
                                        i4 = i7;
                                    }
                                }
                                TextView textView14 = textView8;
                                calPercent2 = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                                textView14.setText(Intrinsics.stringPlus(calPercent2, "%"));
                                if (i3 == 0) {
                                    textView9.setText("UNWATCHED");
                                    textView4.setText("S01E01");
                                    FavoriteItem.LastEpisode last_episode = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode != null) {
                                        last_episode.setSeason(1);
                                    }
                                    FavoriteItem.LastEpisode last_episode2 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode2 != null) {
                                        last_episode2.setEpisode(1);
                                    }
                                    FavoriteItem.LastEpisode last_episode3 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode3 == null) {
                                        return;
                                    }
                                    last_episode3.setTitle("");
                                    return;
                                }
                                TextView textView15 = textView9;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append('S');
                                sb2.append(CommonExtKt.format2(favoriteEpisodeItem.getSeason()));
                                sb2.append('E');
                                sb2.append(CommonExtKt.format2(favoriteEpisodeItem.getEpisode()));
                                sb2.append("/S");
                                List<FavoriteEpisodeItem> allEpisodes = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes, "item.allEpisodes");
                                FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes);
                                sb2.append((Object) (favoriteEpisodeItem2 == null ? null : CommonExtKt.format2(favoriteEpisodeItem2.getSeason())));
                                sb2.append('E');
                                List<FavoriteEpisodeItem> allEpisodes2 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes2, "item.allEpisodes");
                                FavoriteEpisodeItem favoriteEpisodeItem3 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes2);
                                sb2.append((Object) (favoriteEpisodeItem3 == null ? null : CommonExtKt.format2(favoriteEpisodeItem3.getEpisode())));
                                textView15.setText(sb2.toString());
                                if (i3 <= ((FavoriteItem) BaseNode.this).getAllEpisodes().size() - 1) {
                                    textView4.setText('S' + CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getSeason()) + 'E' + CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getEpisode()));
                                    FavoriteItem.LastEpisode last_episode4 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode4 != null) {
                                        last_episode4.setSeason(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getSeason());
                                    }
                                    FavoriteItem.LastEpisode last_episode5 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode5 != null) {
                                        last_episode5.setEpisode(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getEpisode());
                                    }
                                    FavoriteItem.LastEpisode last_episode6 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                    if (last_episode6 == null) {
                                        return;
                                    }
                                    last_episode6.setTitle("");
                                    return;
                                }
                                TextView textView16 = textView4;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append('S');
                                List<FavoriteEpisodeItem> allEpisodes3 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes3, "item.allEpisodes");
                                sb3.append(CommonExtKt.format2(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes3)).getSeason()));
                                sb3.append('E');
                                List<FavoriteEpisodeItem> allEpisodes4 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes4, "item.allEpisodes");
                                sb3.append(CommonExtKt.format2(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes4)).getEpisode()));
                                textView16.setText(sb3.toString());
                                FavoriteItem.LastEpisode last_episode7 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode7 != null) {
                                    List<FavoriteEpisodeItem> allEpisodes5 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                    Intrinsics.checkNotNullExpressionValue(allEpisodes5, "item.allEpisodes");
                                    last_episode7.setSeason(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes5)).getSeason());
                                }
                                FavoriteItem.LastEpisode last_episode8 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode8 != null) {
                                    List<FavoriteEpisodeItem> allEpisodes6 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                    Intrinsics.checkNotNullExpressionValue(allEpisodes6, "item.allEpisodes");
                                    last_episode8.setEpisode(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes6)).getEpisode());
                                }
                                FavoriteItem.LastEpisode last_episode9 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode9 == null) {
                                    return;
                                }
                                last_episode9.setTitle("");
                                return;
                            }
                            ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(i3);
                            TextView textView17 = textView8;
                            calPercent = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                            textView17.setText(Intrinsics.stringPlus(calPercent, "%"));
                            if (i3 == 0) {
                                textView9.setText("UNWATCHED");
                                textView4.setText("S01E01");
                                FavoriteItem.LastEpisode last_episode10 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode10 != null) {
                                    last_episode10.setSeason(1);
                                }
                                FavoriteItem.LastEpisode last_episode11 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode11 != null) {
                                    last_episode11.setEpisode(1);
                                }
                                FavoriteItem.LastEpisode last_episode12 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode12 == null) {
                                    return;
                                }
                                last_episode12.setTitle("");
                                return;
                            }
                            TextView textView18 = textView9;
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append('S');
                            int i8 = i3 - 1;
                            sb4.append(CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i8).getSeason()));
                            sb4.append('E');
                            sb4.append(CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i8).getEpisode()));
                            sb4.append("/S");
                            List<FavoriteEpisodeItem> allEpisodes7 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes7, "item.allEpisodes");
                            FavoriteEpisodeItem favoriteEpisodeItem4 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes7);
                            sb4.append((Object) (favoriteEpisodeItem4 == null ? null : CommonExtKt.format2(favoriteEpisodeItem4.getSeason())));
                            sb4.append('E');
                            List<FavoriteEpisodeItem> allEpisodes8 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes8, "item.allEpisodes");
                            FavoriteEpisodeItem favoriteEpisodeItem5 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes8);
                            sb4.append((Object) (favoriteEpisodeItem5 == null ? null : CommonExtKt.format2(favoriteEpisodeItem5.getEpisode())));
                            textView18.setText(sb4.toString());
                            if (i3 <= ((FavoriteItem) BaseNode.this).getAllEpisodes().size() - 1) {
                                textView4.setText('S' + CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getSeason()) + 'E' + CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getEpisode()));
                                FavoriteItem.LastEpisode last_episode13 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode13 != null) {
                                    last_episode13.setSeason(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getSeason());
                                }
                                FavoriteItem.LastEpisode last_episode14 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode14 != null) {
                                    last_episode14.setEpisode(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getEpisode());
                                }
                                FavoriteItem.LastEpisode last_episode15 = ((FavoriteItem) BaseNode.this).getLast_episode();
                                if (last_episode15 == null) {
                                    return;
                                }
                                last_episode15.setTitle("");
                                return;
                            }
                            TextView textView19 = textView4;
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append('S');
                            List<FavoriteEpisodeItem> allEpisodes9 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes9, "item.allEpisodes");
                            sb5.append(CommonExtKt.format2(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes9)).getSeason()));
                            sb5.append('E');
                            List<FavoriteEpisodeItem> allEpisodes10 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes10, "item.allEpisodes");
                            sb5.append(CommonExtKt.format2(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes10)).getEpisode()));
                            textView19.setText(sb5.toString());
                            FavoriteItem.LastEpisode last_episode16 = ((FavoriteItem) BaseNode.this).getLast_episode();
                            if (last_episode16 != null) {
                                List<FavoriteEpisodeItem> allEpisodes11 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes11, "item.allEpisodes");
                                last_episode16.setSeason(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes11)).getSeason());
                            }
                            FavoriteItem.LastEpisode last_episode17 = ((FavoriteItem) BaseNode.this).getLast_episode();
                            if (last_episode17 != null) {
                                List<FavoriteEpisodeItem> allEpisodes12 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes12, "item.allEpisodes");
                                last_episode17.setEpisode(((FavoriteEpisodeItem) CollectionsKt.last((List<? extends Object>) allEpisodes12)).getEpisode());
                            }
                            FavoriteItem.LastEpisode last_episode18 = ((FavoriteItem) BaseNode.this).getLast_episode();
                            if (last_episode18 == null) {
                                return;
                            }
                            last_episode18.setTitle("");
                            return;
                        }
                        ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(0);
                        textView9.setText("UNWATCHED");
                        textView8.setText("0%");
                        textView4.setText("S01E01");
                        FavoriteItem.LastEpisode last_episode19 = ((FavoriteItem) BaseNode.this).getLast_episode();
                        if (last_episode19 != null) {
                            last_episode19.setSeason(1);
                        }
                        FavoriteItem.LastEpisode last_episode20 = ((FavoriteItem) BaseNode.this).getLast_episode();
                        if (last_episode20 == null) {
                            return;
                        }
                        last_episode20.setEpisode(1);
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    String calPercent;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    int progress = seekBar.getProgress();
                    if (progress != 0) {
                        TextView textView14 = textView8;
                        calPercent = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                        textView14.setText(Intrinsics.stringPlus(calPercent, "% WATCHED"));
                        TextView textView15 = textView9;
                        textView15.setText(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount() + '/' + ((FavoriteItem) BaseNode.this).getTotalEpisode() + " episodes");
                        CollectAdapter.FavoriteProvider favoriteProvider = this;
                        BaseNode baseNode = BaseNode.this;
                        int i3 = progress + (-1);
                        favoriteProvider.markWatched(1, (FavoriteItem) baseNode, ((FavoriteItem) baseNode).getId(), ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getSeason(), ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i3).getEpisode(), i3, helper.getAdapterPosition());
                        return;
                    }
                    textView9.setText("UNWATCHED");
                    textView8.setText("0%");
                    CollectAdapter.FavoriteProvider favoriteProvider2 = this;
                    BaseNode baseNode2 = BaseNode.this;
                    FavoriteItem favoriteItem2 = (FavoriteItem) baseNode2;
                    String id = ((FavoriteItem) baseNode2).getId();
                    List<FavoriteEpisodeItem> allEpisodes = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                    Intrinsics.checkNotNullExpressionValue(allEpisodes, "item.allEpisodes");
                    FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes);
                    int season = favoriteEpisodeItem == null ? 1 : favoriteEpisodeItem.getSeason();
                    List<FavoriteEpisodeItem> allEpisodes2 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                    Intrinsics.checkNotNullExpressionValue(allEpisodes2, "item.allEpisodes");
                    FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes2);
                    favoriteProvider2.markWatched(0, favoriteItem2, id, season, favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getEpisode(), 0, helper.getAdapterPosition());
                }
            });
            ArrayList<Integer> episodes = favoriteItem.getEpisodes();
            if (episodes == null) {
                episodes = new ArrayList<>();
            }
            customProgressView.setEpisodes(episodes);
        }

        private final boolean checkIncludeRange(FavoriteEpisodeItem favoriteEpisodeItem, FavoriteEpisodeItem favoriteEpisodeItem2, int i, int i2) {
            if (favoriteEpisodeItem != null && favoriteEpisodeItem2 != null) {
                if (i < favoriteEpisodeItem2.getSeason()) {
                    if (i > favoriteEpisodeItem.getSeason()) {
                        return true;
                    }
                    return i == favoriteEpisodeItem.getSeason() && i2 >= favoriteEpisodeItem.getEpisode();
                } else if (i == favoriteEpisodeItem2.getSeason() && i2 <= favoriteEpisodeItem2.getEpisode()) {
                    return true;
                }
            }
            return false;
        }

        private final FavoriteEpisodeItem findLeftRangePoint(List<? extends FavoriteEpisodeItem> list, int i) {
            int i2 = i - 1;
            FavoriteEpisodeItem favoriteEpisodeItem = null;
            if (i2 > 0 && i2 >= 0) {
                while (true) {
                    int i3 = i2 - 1;
                    FavoriteEpisodeItem favoriteEpisodeItem2 = list.get(i2);
                    if (favoriteEpisodeItem2.getOver() != 1) {
                        break;
                    }
                    favoriteEpisodeItem = favoriteEpisodeItem2;
                    if (i3 < 0) {
                        break;
                    }
                    i2 = i3;
                }
            }
            return favoriteEpisodeItem;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0055, code lost:
            if (r27 < r24.getLastWatchedItem().getEpisode()) goto L11;
         */
        /* JADX WARN: Type inference failed for: r0v19, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v22, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v28, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Integer] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void markWatched(int r23, com.movieboxpro.android.model.FavoriteItem r24, java.lang.String r25, int r26, int r27, int r28, int r29) {
            /*
                Method dump skipped, instructions count: 299
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.CollectAdapter.FavoriteProvider.markWatched(int, com.movieboxpro.android.model.FavoriteItem, java.lang.String, int, int, int, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getLastEpisode(String str, int i, FavoriteItem favoriteItem) {
            Observable<String> asRequest = HttpRequest.Companion.post("User_watched_plan_episode_list").param("tid", str).asRequest();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, lifecycleOwner), null, null, null, null, new CollectAdapter$FavoriteProvider$getLastEpisode$1(favoriteItem, this, i), 15, null);
        }

        private final void initLikeStatus(int i, int i2, int i3, ImageView imageView, TextView textView, ImageView imageView2, TextView textView2) {
            if (i == 0) {
                imageView.setImageResource(R.mipmap.ic_like);
                imageView2.setImageResource(R.mipmap.ic_yellow_dislike);
                textView.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                if (i2 == 0) {
                    textView.setText("LIKE");
                } else {
                    textView.setText(String.valueOf(i2));
                }
                textView2.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                if (i3 == 0) {
                    textView2.setText("DISLIKE");
                } else {
                    textView2.setText(String.valueOf(i3));
                }
            } else if (i == 1) {
                imageView.setImageResource(R.mipmap.ic_yellow_liked);
                imageView2.setImageResource(R.mipmap.ic_yellow_dislike);
                textView.setTextColor(Color.parseColor("#FFEFC001"));
                textView.setText(String.valueOf(i2));
                textView2.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                if (i3 == 0) {
                    textView2.setText("DISLIKE");
                } else {
                    textView2.setText(String.valueOf(i3));
                }
            } else if (i != 2) {
            } else {
                imageView.setImageResource(R.mipmap.ic_like);
                imageView2.setImageResource(R.mipmap.ic_yellow_disliked);
                textView2.setTextColor(Color.parseColor("#FFEFC001"));
                textView2.setText(String.valueOf(i3));
                textView.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
                if (i2 == 0) {
                    textView.setText("LIKE");
                } else {
                    textView.setText(String.valueOf(i2));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String calPercent(int i, int i2) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            String format = numberFormat.format((i / i2) * 100);
            Intrinsics.checkNotNullExpressionValue(format, "numberFormat.format((dil…loat() * 100).toDouble())");
            return format;
        }

        /* renamed from: convert  reason: avoid collision after fix types in other method */
        public void convert2(BaseViewHolder helper, BaseNode item, List<? extends Object> payloads) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(payloads, "payloads");
            for (Object obj : payloads) {
                if ((obj instanceof Integer) && Intrinsics.areEqual(obj, (Object) 110)) {
                    ImageView imageView = (ImageView) helper.getView(R.id.ivExpand);
                    if (((FavoriteItem) item).isExpanded()) {
                        imageView.setImageResource(R.mipmap.ic_unexpand);
                    } else {
                        imageView.setImageResource(R.mipmap.ic_expand);
                    }
                }
            }
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J \u0010\u001a\u001a\u0004\u0018\u00010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J \u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!H\u0002J@\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteSeasonProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "itemViewType", "", "layoutId", "(Lcom/movieboxpro/android/base/LoadView;Landroidx/lifecycle/LifecycleOwner;II)V", "getItemViewType", "()I", "getLayoutId", "calPercent", "", "diliverNum", "queryMailNum", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "payloads", "", "", "findLeftRangePoint", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "episodes", "position", "getLastEpisode", "id", "favoriteItem", "Lcom/movieboxpro/android/model/FavoriteItem;", "markSeason", DownloadInfo.DOWNLOAD_OVER, "season", "episode", "seasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "seasonPos", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteSeasonProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;
        private final LifecycleOwner lifecycleOwner;
        private final LoadView loadView;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public /* bridge */ /* synthetic */ void convert(BaseViewHolder baseViewHolder, BaseNode baseNode, List list) {
            convert2(baseViewHolder, baseNode, (List<? extends Object>) list);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteSeasonProvider(LoadView loadView, LifecycleOwner lifecycleOwner, int i, int i2) {
            Intrinsics.checkNotNullParameter(loadView, "loadView");
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            this.loadView = loadView;
            this.lifecycleOwner = lifecycleOwner;
            this.itemViewType = i;
            this.layoutId = i2;
        }

        /* JADX WARN: Type inference failed for: r2v22, types: [com.chad.library.adapter.base.BaseNodeAdapter] */
        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(final BaseViewHolder helper, final BaseNode item) {
            int findParentNode;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
            ((CustomProgressView) helper.getView(R.id.progressBar)).setEpisodes(new ArrayList<>(favoriteSeasonItem.getWatchList()));
            helper.setText(R.id.tvSeason, "Season " + favoriteSeasonItem.getSeason() + " · " + calPercent(favoriteSeasonItem.getWatchedCount(), favoriteSeasonItem.getEpisode_list().size()) + '%');
            StringBuilder sb = new StringBuilder();
            sb.append(favoriteSeasonItem.getWatchedCount());
            sb.append('/');
            sb.append(favoriteSeasonItem.getEpisode_list().size());
            sb.append(" episodes watched");
            helper.setText(R.id.tvEpisodeWatched, sb.toString());
            ImageView imageView = (ImageView) helper.getView(R.id.ivExpand);
            if (favoriteSeasonItem.isExpanded()) {
                imageView.setImageResource(R.mipmap.ic_unexpand);
            } else {
                imageView.setImageResource(R.mipmap.ic_expand);
            }
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivMark);
            if (favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size()) {
                imageView2.setImageResource(R.mipmap.ic_blue_duihao);
            } else {
                imageView2.setImageResource(R.mipmap.ic_gray_duihao);
            }
            if (favoriteSeasonItem.isExpanded()) {
                CommonExtKt.invisible(imageView2);
            } else {
                CommonExtKt.visible(imageView2);
            }
            View view = helper.getView(R.id.view);
            if (favoriteSeasonItem.isExpanded()) {
                DensityUtils.setMarginPx(view, CommonExtKt.dp2Px(68), CommonExtKt.dp2Px(12), 0, 0);
            } else {
                DensityUtils.setMarginPx(view, CommonExtKt.dp2Px(28), CommonExtKt.dp2Px(12), 0, 0);
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.clContainer);
            ?? adapter = getAdapter2();
            if (adapter != 0 && (findParentNode = adapter.findParentNode(helper.getAdapterPosition())) != -1) {
                List<FavoriteSeasonItem> episode_progress_list = ((FavoriteItem) ((BaseNode) adapter.getItem(findParentNode))).getEpisode_progress_list();
                Intrinsics.checkNotNullExpressionValue(episode_progress_list, "item1.episode_progress_list");
                if (((FavoriteSeasonItem) CollectionsKt.last((List<? extends Object>) episode_progress_list)).getSeason() == favoriteSeasonItem.getSeason()) {
                    if (favoriteSeasonItem.isExpanded()) {
                        constraintLayout.setBackgroundResource(R.drawable.favorite_bg_shape);
                        DensityUtils.setMarginPx(constraintLayout, CommonExtKt.dp2Px(12), 0, CommonExtKt.dp2Px(12), 0);
                    } else {
                        constraintLayout.setBackgroundResource(R.drawable.favorite_bottom8corner_bg_shape);
                        DensityUtils.setMarginPx(constraintLayout, CommonExtKt.dp2Px(12), 0, CommonExtKt.dp2Px(12), CommonExtKt.dp2Px(16));
                    }
                } else {
                    constraintLayout.setBackgroundResource(R.drawable.favorite_bg_shape);
                    DensityUtils.setMarginPx(constraintLayout, CommonExtKt.dp2Px(12), 0, CommonExtKt.dp2Px(12), 0);
                }
            }
            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) helper.getView(R.id.seekBar);
            appCompatSeekBar.setMax(favoriteSeasonItem.getEpisode_list().size());
            if (favoriteSeasonItem.getLastWatchedItem() == null) {
                appCompatSeekBar.setProgress(0);
            } else {
                appCompatSeekBar.setProgress(favoriteSeasonItem.getLastWatchedIndex() + 1);
            }
            appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.adapter.CollectAdapter$FavoriteSeasonProvider$convert$1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    String calPercent;
                    String calPercent2;
                    String calPercent3;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    if (z) {
                        int i2 = 0;
                        if (i != 0) {
                            if (((FavoriteSeasonItem) BaseNode.this).getLastWatchedItem() != null) {
                                FavoriteEpisodeItem favoriteEpisodeItem = ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().get(i - 1);
                                ((FavoriteSeasonItem) BaseNode.this).setWatchedCount(0);
                                if (favoriteEpisodeItem.getSeason() > ((FavoriteSeasonItem) BaseNode.this).getLastWatchedItem().getSeason() || (favoriteEpisodeItem.getSeason() == ((FavoriteSeasonItem) BaseNode.this).getLastWatchedItem().getSeason() && favoriteEpisodeItem.getEpisode() >= ((FavoriteSeasonItem) BaseNode.this).getLastWatchedItem().getEpisode())) {
                                    while (i2 < i) {
                                        int i3 = i2 + 1;
                                        if (i2 <= ((FavoriteSeasonItem) BaseNode.this).getLastWatchedIndex()) {
                                            if (((FavoriteSeasonItem) BaseNode.this).getEpisode_list().get(i2).getOver() == 1) {
                                                FavoriteSeasonItem favoriteSeasonItem2 = (FavoriteSeasonItem) BaseNode.this;
                                                favoriteSeasonItem2.setWatchedCount(favoriteSeasonItem2.getWatchedCount() + 1);
                                            }
                                        } else {
                                            FavoriteSeasonItem favoriteSeasonItem3 = (FavoriteSeasonItem) BaseNode.this;
                                            favoriteSeasonItem3.setWatchedCount(favoriteSeasonItem3.getWatchedCount() + 1);
                                        }
                                        i2 = i3;
                                    }
                                } else {
                                    while (i2 < i) {
                                        int i4 = i2 + 1;
                                        if (((FavoriteSeasonItem) BaseNode.this).getEpisode_list().get(i2).getOver() == 1) {
                                            FavoriteSeasonItem favoriteSeasonItem4 = (FavoriteSeasonItem) BaseNode.this;
                                            favoriteSeasonItem4.setWatchedCount(favoriteSeasonItem4.getWatchedCount() + 1);
                                        }
                                        i2 = i4;
                                    }
                                }
                                BaseViewHolder baseViewHolder = helper;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Season ");
                                sb2.append(((FavoriteSeasonItem) BaseNode.this).getSeason());
                                sb2.append(" · ");
                                calPercent3 = this.calPercent(((FavoriteSeasonItem) BaseNode.this).getWatchedCount(), ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size());
                                sb2.append(calPercent3);
                                sb2.append('%');
                                baseViewHolder.setText(R.id.tvSeason, sb2.toString());
                                BaseViewHolder baseViewHolder2 = helper;
                                baseViewHolder2.setText(R.id.tvEpisodeWatched, ((FavoriteSeasonItem) BaseNode.this).getWatchedCount() + '/' + ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size() + " episodes watched");
                                return;
                            }
                            ((FavoriteSeasonItem) BaseNode.this).setWatchedCount(i);
                            BaseViewHolder baseViewHolder3 = helper;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Season ");
                            sb3.append(((FavoriteSeasonItem) BaseNode.this).getSeason());
                            sb3.append(" · ");
                            calPercent2 = this.calPercent(((FavoriteSeasonItem) BaseNode.this).getWatchedCount(), ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size());
                            sb3.append(calPercent2);
                            sb3.append('%');
                            baseViewHolder3.setText(R.id.tvSeason, sb3.toString());
                            BaseViewHolder baseViewHolder4 = helper;
                            baseViewHolder4.setText(R.id.tvEpisodeWatched, ((FavoriteSeasonItem) BaseNode.this).getWatchedCount() + '/' + ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size() + " episodes watched");
                            return;
                        }
                        ((FavoriteSeasonItem) BaseNode.this).setWatchedCount(0);
                        BaseViewHolder baseViewHolder5 = helper;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Season ");
                        sb4.append(((FavoriteSeasonItem) BaseNode.this).getSeason());
                        sb4.append(" · ");
                        calPercent = this.calPercent(((FavoriteSeasonItem) BaseNode.this).getWatchedCount(), ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size());
                        sb4.append(calPercent);
                        sb4.append('%');
                        baseViewHolder5.setText(R.id.tvSeason, sb4.toString());
                        BaseViewHolder baseViewHolder6 = helper;
                        baseViewHolder6.setText(R.id.tvEpisodeWatched, ((FavoriteSeasonItem) BaseNode.this).getWatchedCount() + '/' + ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().size() + " episodes watched");
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    LifecycleOwner lifecycleOwner;
                    LifecycleOwner lifecycleOwner2;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    if (seekBar.getProgress() != 0) {
                        FavoriteEpisodeItem favoriteEpisodeItem = ((FavoriteSeasonItem) BaseNode.this).getEpisode_list().get(seekBar.getProgress() - 1);
                        int progress = seekBar.getProgress() - 1;
                        int adapterPosition = helper.getAdapterPosition();
                        lifecycleOwner2 = this.lifecycleOwner;
                        this.markSeason(1, favoriteEpisodeItem.getSeason(), favoriteEpisodeItem.getEpisode(), (FavoriteSeasonItem) BaseNode.this, progress, adapterPosition, lifecycleOwner2);
                        return;
                    }
                    List<FavoriteEpisodeItem> episode_list = ((FavoriteSeasonItem) BaseNode.this).getEpisode_list();
                    Intrinsics.checkNotNullExpressionValue(episode_list, "item.episode_list");
                    FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) episode_list);
                    CollectAdapter.FavoriteSeasonProvider favoriteSeasonProvider = this;
                    int season = favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getSeason();
                    int episode = favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getEpisode();
                    int progress2 = seekBar.getProgress() - 1;
                    int adapterPosition2 = helper.getAdapterPosition();
                    lifecycleOwner = this.lifecycleOwner;
                    favoriteSeasonProvider.markSeason(0, season, episode, (FavoriteSeasonItem) BaseNode.this, progress2, adapterPosition2, lifecycleOwner);
                }
            });
        }

        private final FavoriteEpisodeItem findLeftRangePoint(List<? extends FavoriteEpisodeItem> list, int i) {
            int i2 = i - 1;
            FavoriteEpisodeItem favoriteEpisodeItem = null;
            if (i2 > 0 && i2 >= 0) {
                while (true) {
                    int i3 = i2 - 1;
                    FavoriteEpisodeItem favoriteEpisodeItem2 = list.get(i2);
                    if (favoriteEpisodeItem2.getOver() != 1) {
                        break;
                    }
                    favoriteEpisodeItem = favoriteEpisodeItem2;
                    if (i3 < 0) {
                        break;
                    }
                    i2 = i3;
                }
            }
            return favoriteEpisodeItem;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getLastEpisode(String str, int i, FavoriteItem favoriteItem) {
            Observable<String> asRequest = HttpRequest.Companion.post("User_watched_plan_episode_list").param("tid", str).asRequest();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, lifecycleOwner), null, null, null, null, new CollectAdapter$FavoriteSeasonProvider$getLastEpisode$1(favoriteItem, this, i), 15, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x005a, code lost:
            if (r24 < r25.getLastWatchedItem().getEpisode()) goto L11;
         */
        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v21, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v24, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v30, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r2v14, types: [T, java.lang.Integer] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void markSeason(int r22, int r23, int r24, com.movieboxpro.android.model.FavoriteSeasonItem r25, int r26, int r27, androidx.lifecycle.LifecycleOwner r28) {
            /*
                Method dump skipped, instructions count: 315
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.CollectAdapter.FavoriteSeasonProvider.markSeason(int, int, int, com.movieboxpro.android.model.FavoriteSeasonItem, int, int, androidx.lifecycle.LifecycleOwner):void");
        }

        /* renamed from: convert  reason: avoid collision after fix types in other method */
        public void convert2(BaseViewHolder helper, BaseNode item, List<? extends Object> payloads) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(payloads, "payloads");
            for (Object obj : payloads) {
                if ((obj instanceof Integer) && Intrinsics.areEqual(obj, (Object) 110)) {
                    ImageView imageView = (ImageView) helper.getView(R.id.ivExpand);
                    if (((FavoriteSeasonItem) item).isExpanded()) {
                        imageView.setImageResource(R.mipmap.ic_unexpand);
                    } else {
                        imageView.setImageResource(R.mipmap.ic_expand);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String calPercent(int i, int i2) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            String format = numberFormat.format((i / i2) * 100);
            Intrinsics.checkNotNullExpressionValue(format, "numberFormat.format((dil…loat() * 100).toDouble())");
            return format;
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteEpisodeProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "itemViewType", "", "layoutId", "(II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteEpisodeProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteEpisodeProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, BaseNode item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
            ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
            TextView textView = (TextView) helper.getView(R.id.tvProgress);
            if (favoriteEpisodeItem.getOver() == 1) {
                helper.setImageResource(R.id.ivMark, R.mipmap.ic_blue_duihao);
                CommonExtKt.gone(textView);
                CommonExtKt.gone(progressBar);
            } else {
                helper.setImageResource(R.id.ivMark, R.mipmap.ic_gray_duihao);
                if (favoriteEpisodeItem.getSeconds() == 0) {
                    CommonExtKt.gone(textView);
                    CommonExtKt.gone(progressBar);
                } else {
                    CommonExtKt.visible(textView);
                    CommonExtKt.visible(progressBar);
                    textView.setText(PropertyUtils.MAPPED_DELIM + TimeUtils.getTime(favoriteEpisodeItem.getSeconds()) + '/' + TimeUtils.getTime(favoriteEpisodeItem.getRuntime() * 60) + PropertyUtils.MAPPED_DELIM2);
                    progressBar.setProgress(favoriteEpisodeItem.getSeconds());
                    progressBar.setMax(favoriteEpisodeItem.getRuntime() * 60);
                }
            }
            helper.setText(R.id.tvSeasonEpisode, 'S' + CommonExtKt.format2(favoriteEpisodeItem.getSeason()) + 'E' + CommonExtKt.format2(favoriteEpisodeItem.getEpisode()) + " - " + ((Object) favoriteEpisodeItem.getTitle()));
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.clContainer);
            if (favoriteEpisodeItem.isLastSeason() && favoriteEpisodeItem.isLastItem()) {
                constraintLayout.setBackgroundResource(R.drawable.favorite_bottom8corner_bg_shape);
                DensityUtils.setMarginPx(constraintLayout, CommonExtKt.dp2Px(12), 0, CommonExtKt.dp2Px(12), CommonExtKt.dp2Px(16));
                return;
            }
            constraintLayout.setBackgroundResource(R.drawable.favorite_bg_shape);
            DensityUtils.setMarginPx(constraintLayout, CommonExtKt.dp2Px(12), 0, CommonExtKt.dp2Px(12), 0);
        }
    }

    /* compiled from: CollectAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/adapter/CollectAdapter$FavoriteTitleProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "itemViewType", "", "layoutId", "(II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class FavoriteTitleProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public FavoriteTitleProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, BaseNode item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tvName);
            helper.getView(R.id.view);
            if (((FavoriteItem) item).getFavoriteTitle().isWaiting()) {
                textView.setText("WAITING TO WATCH");
                textView.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white_40alpha));
                return;
            }
            textView.setText("WATCHING");
            textView.setTextColor(CommonExtKt.colorInt(this, (int) R.color.white));
        }
    }
}
