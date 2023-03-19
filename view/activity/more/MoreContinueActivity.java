package com.movieboxpro.android.view.activity.more;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: MoreContinueActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0015J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\u001c\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0011\u001a\u00020\fH\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0013H\u0014J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0014¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/activity/more/MoreContinueActivity;", "Lcom/movieboxpro/android/base/BaseListActivity;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "", "()V", "getIntentData", "", "intent", "Landroid/content/Intent;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "model", "initItemLayout", "isOpenLoadMore", "", "isVerticalLayout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreContinueActivity extends BaseListActivity<Homelist.Typelist, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int initItemLayout() {
        return R.layout.adapter_continue_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isVerticalLayout() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        return Http.getService().Home_list(API.BASE_URL, API.Common.HOME_LIST_V2, App.getUserData().uid_v2, "continue", String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), 0, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        this.mClass = Homelist.Typelist.class;
        this.mPageSize = 15;
        this.mTvTitle.setText("Continue Play");
        CommonExtKt.onMobEvent(this, "EnterHomeMore");
        EventUtils.event("首页进入更多");
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.more.-$$Lambda$MoreContinueActivity$BHoitx9xpqN4td2UssN0BpzCGM4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoreContinueActivity.m379onItemClick$lambda0(MoreContinueActivity.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-0  reason: not valid java name */
    public static final void m379onItemClick$lambda0(MoreContinueActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        Homelist.Typelist typelist = (Homelist.Typelist) this$0.mAdapter.getItem(i);
        boolean z = false;
        if (typelist != null && typelist.box_type == 1) {
            z = true;
        }
        if (z) {
            MovieDetailActivity.start(this$0, typelist.id, typelist.poster);
        } else {
            TvDetailActivity.start(this$0, typelist == null ? null : typelist.id, typelist == null ? null : typelist.banner_mini, typelist != null ? typelist.poster : null);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 5 : 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder baseViewHolder, Homelist.Typelist typelist) {
        ProgressBar progressBar = baseViewHolder == null ? null : (ProgressBar) baseViewHolder.getView(R.id.progressBar);
        ImageView imageView = baseViewHolder == null ? null : (ImageView) baseViewHolder.getView(R.id.ivContinue);
        TextView textView = baseViewHolder == null ? null : (TextView) baseViewHolder.getView(R.id.tvSeasonEpisode);
        ImageView imageView2 = baseViewHolder == null ? null : (ImageView) baseViewHolder.getView(R.id.ivPoster);
        if (Intrinsics.areEqual("0", typelist == null ? null : typelist.id)) {
            if (progressBar != null) {
                CommonExtKt.gone(progressBar);
            }
            if (imageView != null) {
                CommonExtKt.gone(imageView);
            }
            if (textView == null) {
                return;
            }
            CommonExtKt.gone(textView);
            return;
        }
        if (progressBar != null) {
            CommonExtKt.visible(progressBar);
        }
        if (imageView != null) {
            CommonExtKt.visible(imageView);
        }
        GlideUtils.loadPortraitGifHolder(this, typelist != null ? typelist.poster : null, imageView2);
        if (!(typelist != null && typelist.box_type == 2)) {
            if (textView != null) {
                CommonExtKt.gone(textView);
            }
            if (progressBar != null) {
                progressBar.setMax((typelist == null ? 0 : typelist.getRuntime()) * 60);
            }
            if (progressBar == null) {
                return;
            }
            progressBar.setProgress(typelist != null ? typelist.getSeconds() : 0);
        } else if (typelist.getHistory() == null) {
            if (textView != null) {
                CommonExtKt.gone(textView);
            }
            if (progressBar != null) {
                progressBar.setMax(1);
            }
            if (progressBar == null) {
                return;
            }
            progressBar.setProgress(0);
        } else {
            if (progressBar != null) {
                progressBar.setMax(typelist.getHistory().getRuntime() * 60);
            }
            if (progressBar != null) {
                progressBar.setProgress(typelist.getHistory().getSeconds());
            }
            if (textView != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("S%s E%s", Arrays.copyOf(new Object[]{Integer.valueOf(typelist.getHistory().getSeason()), Integer.valueOf(typelist.getHistory().getEpisode())}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                CommonExtKt.textShadow$default(textView, format, 0, 0, 6, null);
            }
            if (textView == null) {
                return;
            }
            CommonExtKt.visible(textView);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
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

    /* compiled from: MoreContinueActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/more/MoreContinueActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, MoreContinueActivity.class));
        }
    }
}
