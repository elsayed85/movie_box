package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.lxj.xpopup.core.AttachPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AddTvWatchPlanPopView.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010\u001b\u001a\u00020\u000bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/widget/AddTvWatchPlanPopView;", "Lcom/lxj/xpopup/core/AttachPopupView;", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "id", "", "watched", "", "time", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Lcom/movieboxpro/android/base/LoadView;Ljava/lang/String;IJLcom/movieboxpro/android/listener/AddWatchPlanListener;)V", "getId", "()Ljava/lang/String;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getLoadView", "()Lcom/movieboxpro/android/base/LoadView;", "getTime", "()J", "getWatched", "()I", "getImplLayoutId", "onCreate", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddTvWatchPlanPopView extends AttachPopupView {
    public Map<Integer, View> _$_findViewCache;
    private final String id;
    private final LifecycleOwner lifecycleOwner;
    private final AddWatchPlanListener listener;
    private final LoadView loadView;
    private final long time;
    private final int watched;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public int getImplLayoutId() {
        return R.layout.add_tv_watch_plan_pop_layout;
    }

    @Override // android.view.View
    public final String getId() {
        return this.id;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final LoadView getLoadView() {
        return this.loadView;
    }

    public final long getTime() {
        return this.time;
    }

    public final int getWatched() {
        return this.watched;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddTvWatchPlanPopView(Context context, LifecycleOwner lifecycleOwner, LoadView loadView, String str, int i, long j, AddWatchPlanListener listener) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(loadView, "loadView");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this._$_findViewCache = new LinkedHashMap();
        this.lifecycleOwner = lifecycleOwner;
        this.loadView = loadView;
        this.id = str;
        this.watched = i;
        this.time = j;
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public void onCreate() {
        super.onCreate();
        TextView tvWaitingTime = (TextView) findViewById(R.id.tvWaitingTime);
        TextView tvWaitingAdded = (TextView) findViewById(R.id.tvWaitingAdded);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ((LinearLayout) findViewById(R.id.llWaiting)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddTvWatchPlanPopView$_P0a6pKknGtJx0F0gh8d6LSSqlo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddTvWatchPlanPopView.m1423onCreate$lambda0(AddTvWatchPlanPopView.this, view);
            }
        });
        if (this.watched == 0) {
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.visible(tvWaitingAdded);
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.visible(tvWaitingTime);
            tvWaitingTime.setText(TimeUtils.INSTANCE.formatPlayTime(this.time));
        } else {
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.gone(tvWaitingAdded);
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.gone(tvWaitingTime);
        }
        LoadingStatusView loadingView = (LoadingStatusView) findViewById(R.id.loadingView);
        if (this.watched != -1) {
            Intrinsics.checkNotNullExpressionValue(loadingView, "loadingView");
            CommonExtKt.visible(loadingView);
            Observable<String> asRequest = HttpRequest.Companion.post("User_watched_plan_episode_list").param("tid", this.id).asRequest();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
            Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, lifecycleOwner), new AddTvWatchPlanPopView$onCreate$2(this), null, new AddTvWatchPlanPopView$onCreate$3(this), null, new AddTvWatchPlanPopView$onCreate$4(this, recyclerView), 10, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m1423onCreate$lambda0(AddTvWatchPlanPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onWatchPlan(0);
        this$0.dismiss();
    }
}
