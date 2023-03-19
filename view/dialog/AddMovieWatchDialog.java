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
import com.movieboxpro.android.databinding.AddTvWatchPlanPopLayoutBinding;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.livedata.AddToWaitingLiveData;
import com.movieboxpro.android.livedata.WaitingLiveData;
import com.movieboxpro.android.livedata.WatchedLiveData;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: AddMovieWatchDialog.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010 \u001a\u00020\u001fH\u0016J\b\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u001fH\u0016J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u000f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006."}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddMovieWatchDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/AddTvWatchPlanPopLayoutBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "<set-?>", "", "tid", "getTid", "()Ljava/lang/String;", "setTid", "(Ljava/lang/String;)V", "tid$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "time", "getTime", "()J", "setTime", "(J)V", "time$delegate", "", "watched", "getWatched", "()I", "setWatched", "(I)V", "watched$delegate", "changeWatched", "", "initData", "initListener", "initView", "markWatched", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddMovieWatchDialog extends BaseBindingCenterDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddMovieWatchDialog.class, "watched", "getWatched()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddMovieWatchDialog.class, "time", "getTime()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddMovieWatchDialog.class, "tid", "getTid()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private AddTvWatchPlanPopLayoutBinding binding;
    private AddWatchPlanListener listener;
    private final ReadWriteProperty tid$delegate;
    private final ReadWriteProperty time$delegate;
    private final ReadWriteProperty watched$delegate;

    @JvmStatic
    public static final AddMovieWatchDialog newInstance(int i, long j, String str) {
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

    public AddMovieWatchDialog() {
        AddMovieWatchDialog addMovieWatchDialog = this;
        this.watched$delegate = FragmentArgsKt.arg(addMovieWatchDialog);
        this.time$delegate = FragmentArgsKt.arg(addMovieWatchDialog);
        this.tid$delegate = FragmentArgsKt.arg(addMovieWatchDialog);
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

    private final String getTid() {
        return (String) this.tid$delegate.getValue(this, $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTid(String str) {
        this.tid$delegate.setValue(this, $$delegatedProperties[2], str);
    }

    /* compiled from: AddMovieWatchDialog.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddMovieWatchDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AddMovieWatchDialog;", "watched", "", "time", "", "tid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AddMovieWatchDialog newInstance(int i, long j, String str) {
            AddMovieWatchDialog addMovieWatchDialog = new AddMovieWatchDialog();
            addMovieWatchDialog.setWatched(i);
            addMovieWatchDialog.setTime(j);
            if (str == null) {
                str = "";
            }
            addMovieWatchDialog.setTid(str);
            return addMovieWatchDialog;
        }
    }

    public final void setListener(AddWatchPlanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void changeWatched(int i) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", (Object) 1).param("mid", getTid()).param("watched", Integer.valueOf(i)).asRequest(), this), new AddMovieWatchDialog$changeWatched$1(this), null, new AddMovieWatchDialog$changeWatched$2(this), null, new AddMovieWatchDialog$changeWatched$3(this, i), 10, null);
    }

    private final void markWatched(int i) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_add").param("box_type", (Object) 1).param("mid", getTid()).param("watched", Integer.valueOf(i)).asRequest(), this), new AddMovieWatchDialog$markWatched$1(this), null, new AddMovieWatchDialog$markWatched$2(this), null, new AddMovieWatchDialog$markWatched$3(this, i), 10, null);
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
        AddMovieWatchDialog addMovieWatchDialog = this;
        WatchedLiveData.Companion.get().observeInFragment(addMovieWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddMovieWatchDialog$AVf_Z2XVhHBE-63rGA_nBsagKfc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddMovieWatchDialog.m916initData$lambda0(AddMovieWatchDialog.this, (Boolean) obj);
            }
        });
        WaitingLiveData.Companion.get().observeInFragment(addMovieWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddMovieWatchDialog$Gz2tAwshjaiRVdc7vmUxq4w6JjU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddMovieWatchDialog.m917initData$lambda1(AddMovieWatchDialog.this, (Boolean) obj);
            }
        });
        AddToWaitingLiveData.Companion.get().observeInFragment(addMovieWatchDialog, new Observer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddMovieWatchDialog$SwzNhcODjaIkbOrp3yueVmRYAsE
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddMovieWatchDialog.m918initData$lambda2(AddMovieWatchDialog.this, (Boolean) obj);
            }
        });
        AddTvWatchPlanPopLayoutBinding addTvWatchPlanPopLayoutBinding = this.binding;
        if (addTvWatchPlanPopLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            addTvWatchPlanPopLayoutBinding = null;
        }
        addTvWatchPlanPopLayoutBinding.llWaiting.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddMovieWatchDialog$32Ghv6NkBA9of2Z5DXlbf4FFYnI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieWatchDialog.m919initData$lambda5$lambda3(AddMovieWatchDialog.this, view);
            }
        });
        addTvWatchPlanPopLayoutBinding.llWatched.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$AddMovieWatchDialog$QkbZh2LzMewPygrTY6lMoFi0wek
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieWatchDialog.m920initData$lambda5$lambda4(AddMovieWatchDialog.this, view);
            }
        });
        if (getWatched() == 1) {
            TextView tvWatchedAdded = addTvWatchPlanPopLayoutBinding.tvWatchedAdded;
            Intrinsics.checkNotNullExpressionValue(tvWatchedAdded, "tvWatchedAdded");
            CommonExtKt.visible(tvWatchedAdded);
            TextView tvWatchedTime = addTvWatchPlanPopLayoutBinding.tvWatchedTime;
            Intrinsics.checkNotNullExpressionValue(tvWatchedTime, "tvWatchedTime");
            CommonExtKt.visible(tvWatchedTime);
            addTvWatchPlanPopLayoutBinding.tvWatchedTime.setText(TimeUtils.INSTANCE.formatPlayTime(getTime() * 1000));
        } else if (getWatched() == 0) {
            TextView tvWaitingAdded = addTvWatchPlanPopLayoutBinding.tvWaitingAdded;
            Intrinsics.checkNotNullExpressionValue(tvWaitingAdded, "tvWaitingAdded");
            CommonExtKt.visible(tvWaitingAdded);
            TextView tvWaitingTime = addTvWatchPlanPopLayoutBinding.tvWaitingTime;
            Intrinsics.checkNotNullExpressionValue(tvWaitingTime, "tvWaitingTime");
            CommonExtKt.visible(tvWaitingTime);
            addTvWatchPlanPopLayoutBinding.tvWaitingTime.setText(TimeUtils.INSTANCE.formatPlayTime(getTime() * 1000));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0  reason: not valid java name */
    public static final void m916initData$lambda0(AddMovieWatchDialog this$0, Boolean bool) {
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
    public static final void m917initData$lambda1(AddMovieWatchDialog this$0, Boolean bool) {
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
    public static final void m918initData$lambda2(AddMovieWatchDialog this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getWatched() == -1) {
            this$0.setTime(System.currentTimeMillis() / 1000);
            this$0.markWatched(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-5$lambda-3  reason: not valid java name */
    public static final void m919initData$lambda5$lambda3(AddMovieWatchDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddWatchPlanListener addWatchPlanListener = this$0.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchPlan(0);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-5$lambda-4  reason: not valid java name */
    public static final void m920initData$lambda5$lambda4(AddMovieWatchDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddWatchPlanListener addWatchPlanListener = this$0.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchPlan(1);
        }
        this$0.dismiss();
    }
}
