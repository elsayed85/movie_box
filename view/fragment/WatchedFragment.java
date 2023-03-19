package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.WatchedEpisode;
import com.movieboxpro.android.model.WatchedItem;
import com.movieboxpro.android.model.WatchedResponse;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WatchedFragment.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\fH\u0014J\"\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002JK\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0012\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0014J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\"H\u0014J\b\u0010#\u001a\u00020\bH\u0014J\u0018\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0002H\u0014J\b\u0010'\u001a\u00020\bH\u0014J\u0010\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020*H\u0014J\b\u0010+\u001a\u00020\u0006H\u0014J\"\u0010,\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J2\u0010-\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010/\u001a\u000200H\u0014J\b\u00101\u001a\u000202H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/fragment/WatchedFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/WatchedItem;", "Lcom/movieboxpro/android/model/WatchedResponse;", "()V", "screenLandscape", "", "width", "", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "addToWaiting", "boxType", "id", "", "position", "changeLikeStatus", "like", "currStatus", "season", "episode", "(ZILjava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;I)V", "deleteWatched", "watchedId", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "initHolder", "helper", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isVerticalLayout", "markToWaiting", "markTv", DownloadInfo.DOWNLOAD_OVER, "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchedFragment extends BaseListFragment<WatchedItem, WatchedResponse> {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean screenLandscape;
    private int width;

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
    protected int gridLayoutSpan() {
        return 2;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_watched_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return CommonExtKt.request$default(Api.INSTANCE.getWatchedList(this.mCurrentPage, this.mPageSize), null, 1, null);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.mClass = WatchedItem.class;
        this.mPageClass = WatchedResponse.class;
        Context context = getContext();
        if (context != null) {
            this.width = (DensityUtils.getScreenWidth(context) - (DensityUtils.dp2px(12.0f) * 4)) / 2;
            this.screenLandscape = CommonUtils.isTablet();
        }
        RefreshWatchedLiveData.Companion.get().observeInFragment(this, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$wnEdCXZ42h2DgomugbpRzQr89pg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchedFragment.m1232getBundle$lambda1(WatchedFragment.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getBundle$lambda-1  reason: not valid java name */
    public static final void m1232getBundle$lambda1(WatchedFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isVerticalLayout() {
        return !CommonUtils.isTablet();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$Vpf9hGFyggdsLEe6mz6VWs-SWhw
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WatchedFragment.m1244onItemClick$lambda2(WatchedFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-2  reason: not valid java name */
    public static final void m1244onItemClick$lambda2(WatchedFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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

    private final void changeLikeStatus(boolean z, int i, String str, int i2, Integer num, Integer num2, int i3) {
        int i4;
        if (!z) {
            if (i == 0 || i == 1) {
                i4 = 2;
            }
            i4 = 0;
        } else if (i != 0) {
            if (i != 1) {
                i4 = i == 2 ? 1 : 0;
            }
            i4 = 0;
        } else {
            i4 = 1;
        }
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Movie_like").param("box_type", Integer.valueOf((i2 != 2 || (num != null && num.intValue() == 0)) ? i2 : 3)).param("mid", str).param("season", num).param("episode", num2).param(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i4)).asRequest(), this), new WatchedFragment$changeLikeStatus$1(this), null, new WatchedFragment$changeLikeStatus$2(this), null, new WatchedFragment$changeLikeStatus$3(this, i3, i2, num, i4, z), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$1vRqIOFbAEgIBUbQkNORBG5JoVw
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WatchedFragment.m1239onItemChildClick$lambda7(WatchedFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-7  reason: not valid java name */
    public static final void m1239onItemChildClick$lambda7(final WatchedFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        final WatchedItem watchedItem = (WatchedItem) this$0.mAdapter.getItem(i);
        int id = view.getId();
        if (id == R.id.ivMore) {
            if (watchedItem.getBox_type() == 1) {
                new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("ADD THIS MOVIE TO WAITING", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$4vXXh9_pJh-mFQgmscax_c1_fbY
                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                    public final void onClick(int i2) {
                        WatchedFragment.m1240onItemChildClick$lambda7$lambda3(WatchedFragment.this, watchedItem, i, i2);
                    }
                }).addSheetItem("REMOVE FROM WATCHED", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$iMByWq9U59O-jpaeaaHW3v99A4c
                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                    public final void onClick(int i2) {
                        WatchedFragment.m1241onItemChildClick$lambda7$lambda4(WatchedFragment.this, watchedItem, i, i2);
                    }
                }).show();
                return;
            } else {
                new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("ADD THIS TV SHOWS TO WAITING", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$W-iyo7gcZxEPV7X6WfNYOtyQSss
                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                    public final void onClick(int i2) {
                        WatchedFragment.m1242onItemChildClick$lambda7$lambda5(WatchedFragment.this, watchedItem, i, i2);
                    }
                }).addSheetItem("REMOVE FROM WATCHED", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$WatchedFragment$xFoDuBcaHdshHxIYaBKKX5J91gY
                    @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                    public final void onClick(int i2) {
                        WatchedFragment.m1243onItemChildClick$lambda7$lambda6(WatchedFragment.this, watchedItem, i, i2);
                    }
                }).show();
                return;
            }
        }
        if (id == R.id.llDislike) {
            if (watchedItem.getBox_type() == 1) {
                int like_status = watchedItem.getLike_status();
                String id2 = watchedItem.getId();
                int box_type = watchedItem.getBox_type();
                WatchedEpisode episode_info = watchedItem.getEpisode_info();
                Integer valueOf = Integer.valueOf(episode_info == null ? 0 : episode_info.getSeason());
                WatchedEpisode episode_info2 = watchedItem.getEpisode_info();
                this$0.changeLikeStatus(false, like_status, id2, box_type, valueOf, Integer.valueOf(episode_info2 != null ? episode_info2.getEpisode() : 0), i);
            } else if (watchedItem.getEpisode_info() != null) {
                this$0.changeLikeStatus(false, watchedItem.getLike_status(), watchedItem.getId(), watchedItem.getBox_type(), Integer.valueOf(watchedItem.getEpisode_info().getSeason()), Integer.valueOf(watchedItem.getEpisode_info().getEpisode()), i);
            } else {
                this$0.changeLikeStatus(false, watchedItem.getLike_status(), watchedItem.getId(), watchedItem.getBox_type(), 0, 0, i);
            }
        } else if (id != R.id.llLike) {
        } else {
            if (watchedItem.getBox_type() == 1) {
                int like_status2 = watchedItem.getLike_status();
                String id3 = watchedItem.getId();
                int box_type2 = watchedItem.getBox_type();
                WatchedEpisode episode_info3 = watchedItem.getEpisode_info();
                Integer valueOf2 = Integer.valueOf(episode_info3 == null ? 0 : episode_info3.getSeason());
                WatchedEpisode episode_info4 = watchedItem.getEpisode_info();
                this$0.changeLikeStatus(true, like_status2, id3, box_type2, valueOf2, Integer.valueOf(episode_info4 != null ? episode_info4.getEpisode() : 0), i);
            } else if (watchedItem.getEpisode_info() != null) {
                this$0.changeLikeStatus(true, watchedItem.getEpisode_info().getLike_status(), watchedItem.getId(), watchedItem.getBox_type(), Integer.valueOf(watchedItem.getEpisode_info().getSeason()), Integer.valueOf(watchedItem.getEpisode_info().getEpisode()), i);
            } else {
                this$0.changeLikeStatus(true, watchedItem.getLike_status(), watchedItem.getId(), watchedItem.getBox_type(), 0, 0, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-7$lambda-3  reason: not valid java name */
    public static final void m1240onItemChildClick$lambda7$lambda3(WatchedFragment this$0, WatchedItem watchedItem, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.addToWaiting(watchedItem.getBox_type(), watchedItem.getId(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-7$lambda-4  reason: not valid java name */
    public static final void m1241onItemChildClick$lambda7$lambda4(WatchedFragment this$0, WatchedItem watchedItem, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.deleteWatched(watchedItem.getWatched_id(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-7$lambda-5  reason: not valid java name */
    public static final void m1242onItemChildClick$lambda7$lambda5(WatchedFragment this$0, WatchedItem watchedItem, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.addToWaiting(watchedItem.getBox_type(), watchedItem.getId(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-7$lambda-6  reason: not valid java name */
    public static final void m1243onItemChildClick$lambda7$lambda6(WatchedFragment this$0, WatchedItem watchedItem, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.deleteWatched(watchedItem.getWatched_id(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        PowerfulStickyDecoration.Builder groupHeight = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.fragment.WatchedFragment$initRecyclerView$stickyDecoration$1
            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int i) {
                BaseQuickAdapter baseQuickAdapter;
                baseQuickAdapter = WatchedFragment.this.mAdapter;
                WatchedItem watchedItem = (WatchedItem) baseQuickAdapter.getItemOrNull(i);
                return watchedItem != null ? TimeUtils.INSTANCE.formatRecentFileTime(watchedItem.getLast_play_time() * 1000) : "";
            }

            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int i) {
                BaseQuickAdapter baseQuickAdapter;
                LayoutInflater from = LayoutInflater.from(WatchedFragment.this.getContext());
                ViewParent parent = recyclerView.getParent();
                if (parent != null) {
                    View view = from.inflate(R.layout.watched_time_title_layout, (ViewGroup) parent, false);
                    baseQuickAdapter = WatchedFragment.this.mAdapter;
                    WatchedItem watchedItem = (WatchedItem) baseQuickAdapter.getItemOrNull(i);
                    if (watchedItem != null) {
                        ((TextView) view.findViewById(R.id.textView)).setText(TimeUtils.INSTANCE.formatRecentFileTime(watchedItem.getLast_play_time() * 1000));
                    }
                    Intrinsics.checkNotNullExpressionValue(view, "view");
                    return view;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
        }).setGroupBackground(0).setSticky(false).setGroupHeight(60);
        if (CommonUtils.isTablet()) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
            }
            groupHeight.resetSpan(recyclerView, (GridLayoutManager) layoutManager);
        }
        recyclerView.addItemDecoration(groupHeight.build());
    }

    private final void deleteWatched(String str, int i) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watched_delete").param("watched_id", str).asRequest(), this), new WatchedFragment$deleteWatched$1(this), null, new WatchedFragment$deleteWatched$2(this), null, new WatchedFragment$deleteWatched$3(this, i), 10, null);
    }

    private final void markToWaiting(int i, String str, int i2) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", Integer.valueOf(i)).param("mid", str).param("watched", (Object) 0).asRequest(), this), new WatchedFragment$markToWaiting$1(this), null, new WatchedFragment$markToWaiting$2(this), null, new WatchedFragment$markToWaiting$3(this, i2), 10, null);
    }

    private final void addToWaiting(int i, String str, int i2) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_add").param("box_type", Integer.valueOf(i)).param("mid", str).param("watched", (Object) 0).asRequest(), this), new WatchedFragment$addToWaiting$1(this), null, new WatchedFragment$addToWaiting$2(this), null, new WatchedFragment$addToWaiting$3(this, i, i2), 10, null);
    }

    private final void markTv(String str, String str2, String str3, int i, int i2) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this), new WatchedFragment$markTv$1(this), null, new WatchedFragment$markTv$2(this), null, new WatchedFragment$markTv$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<WatchedItem, BaseViewHolder> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        adapter.addChildClickViewIds(R.id.ivMore, R.id.llLike, R.id.llDislike);
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:118:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0322  */
    @Override // com.movieboxpro.android.base.BaseListFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder r19, com.movieboxpro.android.model.WatchedItem r20) {
        /*
            Method dump skipped, instructions count: 1346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.fragment.WatchedFragment.initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.WatchedItem):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initHolder$lambda-10  reason: not valid java name */
    public static final void m1233initHolder$lambda10(ConstraintLayout clContainer) {
        Intrinsics.checkNotNullParameter(clContainer, "$clContainer");
        Log.d("initHolder", String.valueOf(clContainer.getWidth()));
    }
}
