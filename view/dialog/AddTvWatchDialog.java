package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.databinding.AddTvWatchPlanPopLayoutBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.livedata.AddToWaitingLiveData;
import com.movieboxpro.android.livedata.WaitingLiveData;
import com.movieboxpro.android.livedata.WatchedLiveData;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.widget.LoadingStatusView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: AddTvWatchDialog.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J \u0010\"\u001a\u00020#2\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00190%j\b\u0012\u0004\u0012\u00020\u0019`&H\u0002J\b\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0016J\b\u0010)\u001a\u00020!H\u0016J\b\u0010*\u001a\u00020!H\u0016JJ\u0010+\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010\n2\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00192\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0002J\u0010\u00105\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J$\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u000e\u0010>\u001a\u00020!2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u001a\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\u00198B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006@"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddTvWatchDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter;", "binding", "Lcom/movieboxpro/android/databinding/AddTvWatchPlanPopLayoutBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "<set-?>", "", "tid", "getTid", "()Ljava/lang/String;", "setTid", "(Ljava/lang/String;)V", "tid$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "time", "getTime", "()J", "setTime", "(J)V", "time$delegate", "", "watched", "getWatched", "()I", "setWatched", "(I)V", "watched$delegate", "changeWatched", "", "checkShowProgress", "", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getData", "initData", "initListener", "initView", "markTv", "id", "season", "episode", DownloadInfo.DOWNLOAD_OVER, "position", "favoriteSeasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "favoriteEpisodeItem", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "markWatched", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddTvWatchDialog extends BaseBindingCenterDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddTvWatchDialog.class, "watched", "getWatched()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddTvWatchDialog.class, "time", "getTime()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddTvWatchDialog.class, "tid", "getTid()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private SeasonEpisodeAdapter adapter;
    private AddTvWatchPlanPopLayoutBinding binding;
    private AddWatchPlanListener listener;
    private final ReadWriteProperty tid$delegate;
    private final ReadWriteProperty time$delegate;
    private final ReadWriteProperty watched$delegate;

    @JvmStatic
    public static final AddTvWatchDialog newInstance(int i, long j, String str) {
        return Companion.newInstance(i, j, str);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public AddTvWatchDialog() {
        AddTvWatchDialog addTvWatchDialog = this;
        this.watched$delegate = FragmentArgsKt.arg(addTvWatchDialog);
        this.time$delegate = FragmentArgsKt.arg(addTvWatchDialog);
        this.tid$delegate = FragmentArgsKt.arg(addTvWatchDialog);
    }

    private final int getWatched() {
        return ((Number) this.watched$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWatched(int i) {
        this.watched$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getTime() {
        return ((Number) this.time$delegate.getValue(this, $$delegatedProperties[1])).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTime(long j) {
        this.time$delegate.setValue(this, $$delegatedProperties[1], Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTid() {
        return (String) this.tid$delegate.getValue(this, $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTid(String str) {
        this.tid$delegate.setValue(this, $$delegatedProperties[2], str);
    }

    /* compiled from: AddTvWatchDialog.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddTvWatchDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AddTvWatchDialog;", "watched", "", "time", "", "tid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AddTvWatchDialog newInstance(int i, long j, String str) {
            AddTvWatchDialog addTvWatchDialog = new AddTvWatchDialog();
            addTvWatchDialog.setWatched(i);
            addTvWatchDialog.setTime(j);
            if (str == null) {
                str = "";
            }
            addTvWatchDialog.setTid(str);
            return addTvWatchDialog;
        }
    }

    public final void setListener(AddWatchPlanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void changeWatched(int i) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", (Object) 2).param("mid", getTid()).param("watched", Integer.valueOf(i)).asRequest(), this), new AddTvWatchDialog$changeWatched$1(this), null, new AddTvWatchDialog$changeWatched$2(this), null, new AddTvWatchDialog$changeWatched$3(this, i), 10, null);
    }

    private final void markWatched(int i) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_add").param("box_type", (Object) 2).param("mid", getTid()).param("watched", Integer.valueOf(i)).asRequest(), this), new AddTvWatchDialog$markWatched$1(this), null, new AddTvWatchDialog$markWatched$2(this), null, new AddTvWatchDialog$markWatched$3(this, i), 10, null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.add_tv_watch_plan_pop_layout, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…layout, container, false)");
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding = (AddTvWatchPlanPopLayoutBinding) inflate;
        this.binding = addTvWatchPlanPopLayoutBinding;
        if (addTvWatchPlanPopLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding = null;
        }
        View root = addTvWatchPlanPopLayoutBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        AddTvWatchDialog addTvWatchDialog = this;
        WatchedLiveData.Companion.get().observeInFragment(addTvWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$r6JDMknA2FAI5VyuuJ_DD7LgoPI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddTvWatchDialog.m922initData$lambda0(AddTvWatchDialog.this, (Boolean) obj);
            }
        });
        WaitingLiveData.Companion.get().observeInFragment(addTvWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$uKRH_bZY56DEwKhL7eRPvjnzzCs
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddTvWatchDialog.m923initData$lambda1(AddTvWatchDialog.this, (Boolean) obj);
            }
        });
        AddToWaitingLiveData.Companion.get().observeInFragment(addTvWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$ilr0eGnPQDMy4qf2DNTuOJURObM
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddTvWatchDialog.m924initData$lambda2(AddTvWatchDialog.this, (Boolean) obj);
            }
        });
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding = this.binding;
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding2 = null;
        if (addTvWatchPlanPopLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding = null;
        }
        addTvWatchPlanPopLayoutBinding.loadingView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$tPU9ll9B-aqACMV9bARylA_ln-A
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                AddTvWatchDialog.m925initData$lambda3(AddTvWatchDialog.this);
            }
        });
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding3 = this.binding;
        if (addTvWatchPlanPopLayoutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            addTvWatchPlanPopLayoutBinding2 = addTvWatchPlanPopLayoutBinding3;
        }
        addTvWatchPlanPopLayoutBinding2.llWaiting.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$m_d0RrK5QNBOd-T1VTEFBazTAjQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddTvWatchDialog.m926initData$lambda6$lambda4(AddTvWatchDialog.this, view);
            }
        });
        addTvWatchPlanPopLayoutBinding2.llWatched.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddTvWatchDialog$XPtDOor2ihw8ggaT8ShKXKYAavw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddTvWatchDialog.m927initData$lambda6$lambda5(AddTvWatchDialog.this, view);
            }
        });
        if (getWatched() == 1) {
            TextView tvWatchedAdded = addTvWatchPlanPopLayoutBinding2.tvWatchedAdded;
            Intrinsics.checkNotNullExpressionValue(tvWatchedAdded, "tvWatchedAdded");
            CommonExtKt.visible(tvWatchedAdded);
            TextView tvWatchedTime = addTvWatchPlanPopLayoutBinding2.tvWatchedTime;
            Intrinsics.checkNotNullExpressionValue(tvWatchedTime, "tvWatchedTime");
            CommonExtKt.visible(tvWatchedTime);
            addTvWatchPlanPopLayoutBinding2.tvWatchedTime.setText(TimeUtils.INSTANCE.formatPlayTime(getTime() * 1000));
        } else if (getWatched() == 0) {
            TextView tvWaitingAdded = addTvWatchPlanPopLayoutBinding2.tvWaitingAdded;
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.visible(tvWaitingAdded);
            TextView tvWaitingTime = addTvWatchPlanPopLayoutBinding2.tvWaitingTime;
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.visible(tvWaitingTime);
            addTvWatchPlanPopLayoutBinding2.tvWaitingTime.setText(TimeUtils.INSTANCE.formatPlayTime(getTime() * 1000));
        }
        LoadingStatusView loadingView = addTvWatchPlanPopLayoutBinding2.loadingView;
        Intrinsics.checkNotNullExpressionValue(loadingView, "loadingView");
        CommonExtKt.visible(loadingView);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0  reason: not valid java name */
    public static final void m922initData$lambda0(AddTvWatchDialog this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getWatched() != 1) {
            if (this$0.getWatched() == 0) {
                this$0.changeWatched(1);
            } else {
                this$0.markWatched(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m923initData$lambda1(AddTvWatchDialog this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getWatched() != 0) {
            if (this$0.getWatched() == 1) {
                this$0.changeWatched(0);
            } else {
                this$0.markWatched(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-2  reason: not valid java name */
    public static final void m924initData$lambda2(AddTvWatchDialog this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getWatched() == -1) {
            this$0.setTime(System.currentTimeMillis() / 1000);
            this$0.markWatched(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-3  reason: not valid java name */
    public static final void m925initData$lambda3(AddTvWatchDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-6$lambda-4  reason: not valid java name */
    public static final void m926initData$lambda6$lambda4(AddTvWatchDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddWatchPlanListener addWatchPlanListener = this$0.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchPlan(0);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-6$lambda-5  reason: not valid java name */
    public static final void m927initData$lambda6$lambda5(AddTvWatchDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddWatchPlanListener addWatchPlanListener = this$0.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchPlan(1);
        }
        this$0.dismiss();
    }

    private final void getData() {
        Observable<R> compose = HttpRequest.Companion.post("User_watched_plan_episode_list").param("tid", getTid()).asRequest().compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, this), new AddTvWatchDialog$getData$1(this), null, new AddTvWatchDialog$getData$2(this), null, new AddTvWatchDialog$getData$3(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markTv(String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this), new AddTvWatchDialog$markTv$1(this), null, new AddTvWatchDialog$markTv$2(this), null, new AddTvWatchDialog$markTv$3(this, favoriteSeasonItem, str3, favoriteEpisodeItem, str2, i2, i), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkShowProgress(ArrayList<Integer> arrayList) {
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int intValue = ((Number) obj).intValue();
            if (i == 0 && intValue == 0) {
                return false;
            }
            Integer num = (Integer) CollectionsKt.getOrNull(arrayList, i - 1);
            if (num != null && intValue == 1 && num.intValue() == 0) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
