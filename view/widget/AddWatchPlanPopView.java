package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.lxj.xpopup.core.AttachPopupView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AddWatchPlanPopView.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0005H\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/widget/AddWatchPlanPopView;", "Lcom/lxj/xpopup/core/AttachPopupView;", "context", "Landroid/content/Context;", "watched", "", "time", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "(Landroid/content/Context;IJLcom/movieboxpro/android/listener/AddWatchPlanListener;)V", "getTime", "()J", "getWatched", "()I", "getImplLayoutId", "onCreate", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddWatchPlanPopView extends AttachPopupView {
    public Map<Integer, View> _$_findViewCache;
    private final AddWatchPlanListener listener;
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
        return R.layout.add_watch_plan_pop_layout;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddWatchPlanPopView(Context context, int i, long j, AddWatchPlanListener listener) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this._$_findViewCache = new LinkedHashMap();
        this.watched = i;
        this.time = j;
        this.listener = listener;
    }

    public final long getTime() {
        return this.time;
    }

    public final int getWatched() {
        return this.watched;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public void onCreate() {
        super.onCreate();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llWaiting);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.llWatched);
        TextView tvWaitingTime = (TextView) findViewById(R.id.tvWaitingTime);
        TextView tvWatchedTime = (TextView) findViewById(R.id.tvWatchedTime);
        TextView tvWaitingAdded = (TextView) findViewById(R.id.tvWaitingAdded);
        TextView tvWatchedAdded = (TextView) findViewById(R.id.tvWatchedAdded);
        int i = this.watched;
        if (i == 1) {
            Intrinsics.checkNotNullExpressionValue(tvWatchedAdded, "tvWatchedAdded");
            CommonExtKt.visible(tvWatchedAdded);
            Intrinsics.checkNotNullExpressionValue(tvWatchedTime, "tvWatchedTime");
            CommonExtKt.visible(tvWatchedTime);
            tvWatchedTime.setText(TimeUtils.INSTANCE.formatPlayTime(this.time * 1000));
        } else if (i == 0) {
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.visible(tvWaitingAdded);
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.visible(tvWaitingTime);
            tvWaitingTime.setText(TimeUtils.INSTANCE.formatPlayTime(this.time * 1000));
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddWatchPlanPopView$4bQb9xEmLU3yHhftGfkBTwO0D_g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddWatchPlanPopView.m1424onCreate$lambda0(AddWatchPlanPopView.this, view);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddWatchPlanPopView$iPGWJDAkbyjKdQgMhsLqaGmGeuU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddWatchPlanPopView.m1425onCreate$lambda1(AddWatchPlanPopView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m1424onCreate$lambda0(AddWatchPlanPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onWatchPlan(0);
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m1425onCreate$lambda1(AddWatchPlanPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onWatchPlan(1);
        this$0.dismiss();
    }
}
