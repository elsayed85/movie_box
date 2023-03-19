package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.mediarouter.media.MediaControlIntent;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.CommBaseAdapter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ChromeCastDialogFragment.kt */
@Metadata(d1 = {"\u0000c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\r\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0016\u0010\u001f\u001a\u00020\u00152\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050!H\u0002J\b\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0015H\u0002J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0010J\u0016\u0010&\u001a\u00020\u00152\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050'H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChromeCastDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Landroidx/mediarouter/media/MediaRouter$RouteInfo;", "mHandler", "Landroid/os/Handler;", "mLastUpdateTime", "", "mediaRouter", "Landroidx/mediarouter/media/MediaRouter;", "mediaRouterCallback", "com/movieboxpro/android/view/dialog/ChromeCastDialogFragment$mediaRouterCallback$1", "Lcom/movieboxpro/android/view/dialog/ChromeCastDialogFragment$mediaRouterCallback$1;", "otherClick", "Landroid/view/View$OnClickListener;", "selector", "Landroidx/mediarouter/media/MediaRouteSelector;", "kotlin.jvm.PlatformType", "initData", "", "initLayoutId", "", "initListener", "initView", "isDefaultOrBluetooth", "", "route", "isSystemMediaRouteProvider", "onFilterRoute", "onFilterRoutes", "routes", "", "onStop", "refreshRoutes", "setOtherClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updateRoutes", "Ljava/util/ArrayList;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChromeCastDialogFragment extends BaseCenterDialogFragment {
    public static final Companion Companion = new Companion(null);
    public static final int MSG_UPDATE_ROUTES = 1;
    private CommBaseAdapter<MediaRouter.RouteInfo> adapter;
    private long mLastUpdateTime;
    private MediaRouter mediaRouter;
    private View.OnClickListener otherClick;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private MediaRouteSelector selector = MediaRouteSelector.EMPTY;
    private final Handler mHandler = new Handler() { // from class: com.movieboxpro.android.view.dialog.ChromeCastDialogFragment$mHandler$1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Intrinsics.checkNotNullParameter(message, "message");
            if (message.what == 1) {
                ChromeCastDialogFragment chromeCastDialogFragment = ChromeCastDialogFragment.this;
                Object obj = message.obj;
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<androidx.mediarouter.media.MediaRouter.RouteInfo>");
                }
                chromeCastDialogFragment.updateRoutes((ArrayList) obj);
            }
        }
    };
    private final ChromeCastDialogFragment$mediaRouterCallback$1 mediaRouterCallback = new MediaRouter.Callback() { // from class: com.movieboxpro.android.view.dialog.ChromeCastDialogFragment$mediaRouterCallback$1
        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo route) {
            Intrinsics.checkNotNullParameter(router, "router");
            Intrinsics.checkNotNullParameter(route, "route");
            ChromeCastDialogFragment.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo route) {
            Intrinsics.checkNotNullParameter(router, "router");
            Intrinsics.checkNotNullParameter(route, "route");
            ChromeCastDialogFragment.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo route) {
            Intrinsics.checkNotNullParameter(router, "router");
            Intrinsics.checkNotNullParameter(route, "route");
            ChromeCastDialogFragment.this.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo route) {
            Intrinsics.checkNotNullParameter(router, "router");
            Intrinsics.checkNotNullParameter(route, "route");
            ChromeCastDialogFragment.this.dismissAllowingStateLoss();
        }
    };

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public int initLayoutId() {
        return R.layout.fragment_chrome_cast;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* compiled from: ChromeCastDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChromeCastDialogFragment$Companion;", "", "()V", "MSG_UPDATE_ROUTES", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void setOtherClickListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.otherClick = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initListener() {
        CommBaseAdapter<MediaRouter.RouteInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChromeCastDialogFragment$z0Yh0r0NsY_qWPvY3jIYuPqPksc
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChromeCastDialogFragment.m964initListener$lambda0(ChromeCastDialogFragment.this, baseQuickAdapter, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvVlcCast)).setOnClickListener(this.otherClick);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m964initListener$lambda0(ChromeCastDialogFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommBaseAdapter<MediaRouter.RouteInfo> commBaseAdapter = this$0.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        MediaRouter.RouteInfo item = commBaseAdapter.getItem(i);
        if (item.isEnabled()) {
            item.select();
            this$0.dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshRoutes() {
        List<MediaRouter.RouteInfo> list;
        MediaRouter mediaRouter = this.mediaRouter;
        List<MediaRouter.RouteInfo> routes = mediaRouter == null ? null : mediaRouter.getRoutes();
        if (routes != null) {
            list = routes;
        } else {
            list = new ArrayList();
        }
        ArrayList<MediaRouter.RouteInfo> arrayList = new ArrayList<>(list);
        ArrayList<MediaRouter.RouteInfo> arrayList2 = arrayList;
        onFilterRoutes(arrayList2);
        CollectionsKt.sortWith(arrayList2, $$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY.INSTANCE);
        if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= 300) {
            updateRoutes(arrayList);
            return;
        }
        this.mHandler.removeMessages(1);
        Handler handler = this.mHandler;
        handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshRoutes$lambda-1  reason: not valid java name */
    public static final int m965refreshRoutes$lambda1(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2) {
        String name = routeInfo.getName();
        Intrinsics.checkNotNullExpressionValue(name, "o1.name");
        String name2 = routeInfo2.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "o2.name");
        return StringsKt.compareTo(name, name2, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRoutes(ArrayList<MediaRouter.RouteInfo> arrayList) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        CommBaseAdapter<MediaRouter.RouteInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(arrayList);
    }

    private final void onFilterRoutes(List<MediaRouter.RouteInfo> list) {
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            if (!onFilterRoute(list.get(i))) {
                list.remove(i);
            }
            size = i;
        }
    }

    private final boolean onFilterRoute(MediaRouter.RouteInfo routeInfo) {
        return !isDefaultOrBluetooth(routeInfo) && routeInfo.isEnabled();
    }

    private final boolean isDefaultOrBluetooth(MediaRouter.RouteInfo routeInfo) {
        if (routeInfo.isDefault() || routeInfo.getDeviceType() == 3) {
            return true;
        }
        return isSystemMediaRouteProvider(routeInfo) && routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
    }

    private final boolean isSystemMediaRouteProvider(MediaRouter.RouteInfo routeInfo) {
        return TextUtils.equals(routeInfo.getProvider().getProviderInstance().getMetadata().getPackageName(), "android");
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialogFragment
    public void initData() {
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_chrome_cast_item, ChromeCastDialogFragment$initData$1.INSTANCE, null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<MediaRouter.RouteInfo> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView.setAdapter(commBaseAdapter);
        Context context = getContext();
        this.mediaRouter = context != null ? MediaRouter.getInstance(context) : null;
        MediaRouteSelector build = new MediaRouteSelector.Builder().addControlCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK).build();
        this.selector = build;
        MediaRouter mediaRouter = this.mediaRouter;
        if (mediaRouter != null) {
            mediaRouter.addCallback(build, this.mediaRouterCallback, 1);
        }
        refreshRoutes();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        MediaRouter mediaRouter = this.mediaRouter;
        if (mediaRouter != null) {
            mediaRouter.removeCallback(this.mediaRouterCallback);
        }
        this.mHandler.removeMessages(1);
        super.onStop();
    }
}
