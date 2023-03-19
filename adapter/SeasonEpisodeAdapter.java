package com.movieboxpro.android.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.widget.CustomProgressView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: SeasonEpisodeAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0004\u0015\u0016\u0017\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0010H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter;", "Lcom/chad/library/adapter/base/BaseNodeAdapter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;)V", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getListener", "()Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "getLoadView", "()Lcom/movieboxpro/android/base/LoadView;", "getItemType", "", "data", "", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "position", "Companion", "EpisodeProvider", "SeasonProvider", "TotalSeasonProvider", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SeasonEpisodeAdapter extends BaseNodeAdapter {
    public static final Companion Companion = new Companion(null);
    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
    private final LifecycleOwner lifecycleOwner;
    private final DialogAction.ActionListener listener;
    private final LoadView loadView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeasonEpisodeAdapter(LifecycleOwner lifecycleOwner, LoadView loadView, DialogAction.ActionListener actionListener) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(loadView, "loadView");
        this.lifecycleOwner = lifecycleOwner;
        this.loadView = loadView;
        this.listener = actionListener;
        addNodeProvider(new TotalSeasonProvider(lifecycleOwner, loadView, actionListener, 1, R.layout.adapter_total_season));
        addNodeProvider(new SeasonProvider(this.loadView, this.lifecycleOwner, 2, R.layout.adapter_favorite_season_item));
        addNodeProvider(new EpisodeProvider(3, R.layout.adapter_favorite_episode_item));
    }

    public /* synthetic */ SeasonEpisodeAdapter(LifecycleOwner lifecycleOwner, LoadView loadView, DialogAction.ActionListener actionListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, loadView, (i & 4) != 0 ? null : actionListener);
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final DialogAction.ActionListener getListener() {
        return this.listener;
    }

    public final LoadView getLoadView() {
        return this.loadView;
    }

    /* compiled from: SeasonEpisodeAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter$Companion;", "", "()V", "EXPAND_COLLAPSE_PAYLOAD", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: SeasonEpisodeAdapter.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J \u0010\u001a\u001a\u0004\u0018\u00010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J@\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter$SeasonProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "itemViewType", "", "layoutId", "(Lcom/movieboxpro/android/base/LoadView;Landroidx/lifecycle/LifecycleOwner;II)V", "getItemViewType", "()I", "getLayoutId", "calPercent", "", "diliverNum", "queryMailNum", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "payloads", "", "", "findLeftRangePoint", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "episodes", "position", "markSeason", DownloadInfo.DOWNLOAD_OVER, "season", "episode", "seasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "seasonPos", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class SeasonProvider extends BaseNodeProvider {
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

        public SeasonProvider(LoadView loadView, LifecycleOwner lifecycleOwner, int i, int i2) {
            Intrinsics.checkNotNullParameter(loadView, "loadView");
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            this.loadView = loadView;
            this.lifecycleOwner = lifecycleOwner;
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(final BaseViewHolder helper, final BaseNode item) {
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
            View view = helper.getView(R.id.view);
            if (favoriteSeasonItem.isExpanded()) {
                DensityUtils.setMarginPx(view, CommonExtKt.dp2Px(68), CommonExtKt.dp2Px(12), 0, 0);
            } else {
                DensityUtils.setMarginPx(view, CommonExtKt.dp2Px(28), CommonExtKt.dp2Px(12), 0, 0);
            }
            ((ConstraintLayout) helper.getView(R.id.clContainer)).setBackgroundColor(0);
            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) helper.getView(R.id.seekBar);
            appCompatSeekBar.setMax(favoriteSeasonItem.getEpisode_list().size());
            if (favoriteSeasonItem.getLastWatchedItem() == null) {
                appCompatSeekBar.setProgress(0);
            } else {
                appCompatSeekBar.setProgress(favoriteSeasonItem.getLastWatchedIndex() + 1);
            }
            appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.adapter.SeasonEpisodeAdapter$SeasonProvider$convert$1
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
                    SeasonEpisodeAdapter.SeasonProvider seasonProvider = this;
                    int season = favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getSeason();
                    int episode = favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getEpisode();
                    int progress2 = seekBar.getProgress() - 1;
                    int adapterPosition2 = helper.getAdapterPosition();
                    lifecycleOwner = this.lifecycleOwner;
                    seasonProvider.markSeason(0, season, episode, (FavoriteSeasonItem) BaseNode.this, progress2, adapterPosition2, lifecycleOwner);
                }
            });
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
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.SeasonEpisodeAdapter.SeasonProvider.markSeason(int, int, int, com.movieboxpro.android.model.FavoriteSeasonItem, int, int, androidx.lifecycle.LifecycleOwner):void");
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

    /* compiled from: SeasonEpisodeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter$EpisodeProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "itemViewType", "", "layoutId", "(II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class EpisodeProvider extends BaseNodeProvider {
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

        public EpisodeProvider(int i, int i2) {
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
            ((ConstraintLayout) helper.getView(R.id.clContainer)).setBackgroundColor(0);
        }
    }

    /* compiled from: SeasonEpisodeAdapter.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J \u0010\u001f\u001a\u0004\u0018\u00010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\"2\u0006\u0010#\u001a\u00020\tH\u0002JB\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u00162\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0002R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter$TotalSeasonProvider;", "Lcom/chad/library/adapter/base/provider/BaseNodeProvider;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "itemViewType", "", "layoutId", "(Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;II)V", "getItemViewType", "()I", "getLayoutId", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getListener", "()Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "getLoadView", "()Lcom/movieboxpro/android/base/LoadView;", "calPercent", "", "diliverNum", "queryMailNum", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/chad/library/adapter/base/entity/node/BaseNode;", "findLeftRangePoint", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "episodes", "", "position", "markWatched", DownloadInfo.DOWNLOAD_OVER, "favoriteItem", "Lcom/movieboxpro/android/model/FavoriteItem;", "id", "season", "episode", "tvPos", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class TotalSeasonProvider extends BaseNodeProvider {
        private final int itemViewType;
        private final int layoutId;
        private final LifecycleOwner lifecycleOwner;
        private final DialogAction.ActionListener listener;
        private final LoadView loadView;

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final DialogAction.ActionListener getListener() {
            return this.listener;
        }

        public final LoadView getLoadView() {
            return this.loadView;
        }

        public TotalSeasonProvider(LifecycleOwner lifecycleOwner, LoadView loadView, DialogAction.ActionListener actionListener, int i, int i2) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(loadView, "loadView");
            this.lifecycleOwner = lifecycleOwner;
            this.loadView = loadView;
            this.listener = actionListener;
            this.itemViewType = i;
            this.layoutId = i2;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(final BaseViewHolder helper, final BaseNode item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            FavoriteItem favoriteItem = (FavoriteItem) item;
            final TextView textView = (TextView) helper.getView(R.id.tvTotalPercent);
            textView.setText(Intrinsics.stringPlus(calPercent(favoriteItem.getWatchedEpisodeCount(), favoriteItem.getTotalEpisode()), "% WATCHED"));
            final TextView textView2 = (TextView) helper.getView(R.id.tvTotalSeason);
            textView2.setText(favoriteItem.getWatchedEpisodeCount() + '/' + favoriteItem.getTotalEpisode() + " episodes");
            ImageView imageView = (ImageView) helper.getView(R.id.ivExpand);
            if (favoriteItem.isExpanded()) {
                imageView.setImageResource(R.mipmap.ic_unexpand);
            } else {
                imageView.setImageResource(R.mipmap.ic_expand);
            }
            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) helper.getView(R.id.seekBar);
            appCompatSeekBar.setMax(favoriteItem.getTotalEpisode());
            appCompatSeekBar.setProgress(favoriteItem.getLastWatchedCount());
            appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.adapter.SeasonEpisodeAdapter$TotalSeasonProvider$convert$1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    String calPercent;
                    String calPercent2;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    if (z) {
                        int i2 = 0;
                        if (i != 0) {
                            if (((FavoriteItem) BaseNode.this).getLastWatchedItem() != null) {
                                FavoriteEpisodeItem favoriteEpisodeItem = ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i - 1);
                                ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(0);
                                if (favoriteEpisodeItem.getSeason() > ((FavoriteItem) BaseNode.this).getLastWatchedItem().getSeason() || (favoriteEpisodeItem.getSeason() == ((FavoriteItem) BaseNode.this).getLastWatchedItem().getSeason() && favoriteEpisodeItem.getEpisode() >= ((FavoriteItem) BaseNode.this).getLastWatchedItem().getEpisode())) {
                                    while (i2 < i) {
                                        int i3 = i2 + 1;
                                        if (i2 <= ((FavoriteItem) BaseNode.this).getLastWatchedIndex()) {
                                            if (((FavoriteItem) BaseNode.this).getAllEpisodes().get(i2).getOver() == 1) {
                                                FavoriteItem favoriteItem2 = (FavoriteItem) BaseNode.this;
                                                favoriteItem2.setWatchedEpisodeCount(favoriteItem2.getWatchedEpisodeCount() + 1);
                                            }
                                        } else {
                                            FavoriteItem favoriteItem3 = (FavoriteItem) BaseNode.this;
                                            favoriteItem3.setWatchedEpisodeCount(favoriteItem3.getWatchedEpisodeCount() + 1);
                                        }
                                        i2 = i3;
                                    }
                                } else {
                                    while (i2 < i) {
                                        int i4 = i2 + 1;
                                        if (((FavoriteItem) BaseNode.this).getAllEpisodes().get(i2).getOver() == 1) {
                                            FavoriteItem favoriteItem4 = (FavoriteItem) BaseNode.this;
                                            favoriteItem4.setWatchedEpisodeCount(favoriteItem4.getWatchedEpisodeCount() + 1);
                                        }
                                        i2 = i4;
                                    }
                                }
                                TextView textView3 = textView;
                                calPercent2 = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                                textView3.setText(Intrinsics.stringPlus(calPercent2, "%"));
                                if (i == 0) {
                                    textView2.setText("UNWATCHED");
                                    return;
                                }
                                TextView textView4 = textView2;
                                StringBuilder sb = new StringBuilder();
                                sb.append('S');
                                sb.append(CommonExtKt.format2(favoriteEpisodeItem.getSeason()));
                                sb.append('E');
                                sb.append(CommonExtKt.format2(favoriteEpisodeItem.getEpisode()));
                                sb.append("/S");
                                List<FavoriteEpisodeItem> allEpisodes = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes, "item.allEpisodes");
                                FavoriteEpisodeItem favoriteEpisodeItem2 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes);
                                sb.append((Object) (favoriteEpisodeItem2 == null ? null : CommonExtKt.format2(favoriteEpisodeItem2.getSeason())));
                                sb.append('E');
                                List<FavoriteEpisodeItem> allEpisodes2 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                                Intrinsics.checkNotNullExpressionValue(allEpisodes2, "item.allEpisodes");
                                FavoriteEpisodeItem favoriteEpisodeItem3 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes2);
                                sb.append((Object) (favoriteEpisodeItem3 != null ? CommonExtKt.format2(favoriteEpisodeItem3.getEpisode()) : null));
                                textView4.setText(sb.toString());
                                return;
                            }
                            ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(i);
                            TextView textView5 = textView;
                            calPercent = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                            textView5.setText(Intrinsics.stringPlus(calPercent, "%"));
                            if (i == 0) {
                                textView2.setText("UNWATCHED");
                                return;
                            }
                            TextView textView6 = textView2;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append('S');
                            int i5 = i - 1;
                            sb2.append(CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i5).getSeason()));
                            sb2.append('E');
                            sb2.append(CommonExtKt.format2(((FavoriteItem) BaseNode.this).getAllEpisodes().get(i5).getEpisode()));
                            sb2.append("/S");
                            List<FavoriteEpisodeItem> allEpisodes3 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes3, "item.allEpisodes");
                            FavoriteEpisodeItem favoriteEpisodeItem4 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes3);
                            sb2.append((Object) (favoriteEpisodeItem4 == null ? null : CommonExtKt.format2(favoriteEpisodeItem4.getSeason())));
                            sb2.append('E');
                            List<FavoriteEpisodeItem> allEpisodes4 = ((FavoriteItem) BaseNode.this).getAllEpisodes();
                            Intrinsics.checkNotNullExpressionValue(allEpisodes4, "item.allEpisodes");
                            FavoriteEpisodeItem favoriteEpisodeItem5 = (FavoriteEpisodeItem) CollectionsKt.lastOrNull((List<? extends Object>) allEpisodes4);
                            sb2.append((Object) (favoriteEpisodeItem5 != null ? CommonExtKt.format2(favoriteEpisodeItem5.getEpisode()) : null));
                            textView6.setText(sb2.toString());
                            return;
                        }
                        ((FavoriteItem) BaseNode.this).setWatchedEpisodeCount(0);
                        textView2.setText("UNWATCHED");
                        textView.setText("0%");
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    String calPercent;
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    int progress = seekBar.getProgress();
                    if (progress != 0) {
                        TextView textView3 = textView;
                        calPercent = this.calPercent(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount(), ((FavoriteItem) BaseNode.this).getTotalEpisode());
                        textView3.setText(Intrinsics.stringPlus(calPercent, "% WATCHED"));
                        TextView textView4 = textView2;
                        textView4.setText(((FavoriteItem) BaseNode.this).getWatchedEpisodeCount() + '/' + ((FavoriteItem) BaseNode.this).getTotalEpisode() + " episodes");
                        SeasonEpisodeAdapter.TotalSeasonProvider totalSeasonProvider = this;
                        BaseNode baseNode = BaseNode.this;
                        int i = progress + (-1);
                        totalSeasonProvider.markWatched(1, (FavoriteItem) baseNode, ((FavoriteItem) baseNode).getId(), ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i).getSeason(), ((FavoriteItem) BaseNode.this).getAllEpisodes().get(i).getEpisode(), i, helper.getAdapterPosition());
                        return;
                    }
                    textView2.setText("UNWATCHED");
                    textView.setText("0%");
                    SeasonEpisodeAdapter.TotalSeasonProvider totalSeasonProvider2 = this;
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
                    totalSeasonProvider2.markWatched(0, favoriteItem2, id, season, favoriteEpisodeItem2 == null ? 1 : favoriteEpisodeItem2.getEpisode(), 0, helper.getAdapterPosition());
                }
            });
            ArrayList<Integer> episodes = favoriteItem.getEpisodes();
            Intrinsics.checkNotNullExpressionValue(episodes, "item.episodes");
            ((CustomProgressView) helper.getView(R.id.progress)).setEpisodes(episodes);
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
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.adapter.SeasonEpisodeAdapter.TotalSeasonProvider.markWatched(int, com.movieboxpro.android.model.FavoriteItem, java.lang.String, int, int, int, int):void");
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

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends BaseNode> data, int i) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseNode baseNode = data.get(i);
        if (baseNode instanceof FavoriteSeasonItem) {
            return 2;
        }
        return baseNode instanceof FavoriteEpisodeItem ? 3 : 1;
    }
}
