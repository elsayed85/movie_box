package com.movieboxpro.android.view.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.AdapterMoreImageBinding;
import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBinding;
import com.movieboxpro.android.model.SkipEnd;
import com.movieboxpro.android.model.SkipStart;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.model.VideoThumb;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.movieboxpro.android.view.widget.LoadingStatusView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "thumbs", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/VideoThumb;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3 extends Lambda implements Function1<ArrayList<VideoThumb>, Unit> {
    final /* synthetic */ ArrayList<SkipEnd> $endTimes;
    final /* synthetic */ Ref.BooleanRef $moreData;
    final /* synthetic */ SkipTimeResponse $skipTime;
    final /* synthetic */ ArrayList<SkipStart> $times;
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment, Ref.BooleanRef booleanRef, SkipTimeResponse skipTimeResponse, ArrayList<SkipStart> arrayList, ArrayList<SkipEnd> arrayList2) {
        super(1);
        this.this$0 = skipTimeSettingFragment;
        this.$moreData = booleanRef;
        this.$skipTime = skipTimeResponse;
        this.$times = arrayList;
        this.$endTimes = arrayList2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ArrayList<VideoThumb> arrayList) {
        invoke2(arrayList);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ArrayList<VideoThumb> thumbs) {
        FragmentSkipTimeSettingBinding binding;
        CommBaseAdapter commBaseAdapter;
        FragmentSkipTimeSettingBinding binding2;
        AdapterMoreImageBinding adapterMoreImageBinding;
        AdapterMoreImageBinding adapterMoreImageBinding2;
        boolean startTimeType;
        CommBaseAdapter commBaseAdapter2;
        AdapterMoreImageBinding adapterMoreImageBinding3;
        CommBaseAdapter commBaseAdapter3;
        AdapterMoreImageBinding adapterMoreImageBinding4;
        binding = this.this$0.getBinding();
        binding.loadingView.hideLoading();
        commBaseAdapter = this.this$0.imageAdapter;
        AdapterMoreImageBinding adapterMoreImageBinding5 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setList(thumbs);
        if (!this.$moreData.element) {
            SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment = this.this$0;
            LayoutInflater from = LayoutInflater.from(skipTimeSettingFragment.getContext());
            binding2 = this.this$0.getBinding();
            ViewParent parent = binding2.rvImage.getParent();
            if (parent != null) {
                AdapterMoreImageBinding inflate = AdapterMoreImageBinding.inflate(from, (ViewGroup) parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …                        )");
                skipTimeSettingFragment.moreBinding = inflate;
                adapterMoreImageBinding = this.this$0.moreBinding;
                if (adapterMoreImageBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
                    adapterMoreImageBinding = null;
                }
                TextView textView = adapterMoreImageBinding.tvMore;
                final SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment2 = this.this$0;
                final ArrayList<SkipStart> arrayList = this.$times;
                final ArrayList<SkipEnd> arrayList2 = this.$endTimes;
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3$iWCriyOsRVaNPgfxC-i9N1fh5mc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3.m1124invoke$lambda0(SkipTimeFragment.SkipTimeSettingFragment.this, arrayList, arrayList2, view);
                    }
                });
                adapterMoreImageBinding2 = this.this$0.moreBinding;
                if (adapterMoreImageBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
                    adapterMoreImageBinding2 = null;
                }
                LoadingStatusView loadingStatusView = adapterMoreImageBinding2.loadingView;
                final SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment3 = this.this$0;
                final ArrayList<SkipStart> arrayList3 = this.$times;
                final ArrayList<SkipEnd> arrayList4 = this.$endTimes;
                loadingStatusView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3$-0tW-PA8jYg3g8tPWUm4sscwnZs
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3.m1125invoke$lambda1(SkipTimeFragment.SkipTimeSettingFragment.this, arrayList3, arrayList4);
                    }
                });
                startTimeType = this.this$0.getStartTimeType();
                if (startTimeType) {
                    commBaseAdapter3 = this.this$0.imageAdapter;
                    if (commBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                        commBaseAdapter3 = null;
                    }
                    CommBaseAdapter commBaseAdapter4 = commBaseAdapter3;
                    adapterMoreImageBinding4 = this.this$0.moreBinding;
                    if (adapterMoreImageBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
                    } else {
                        adapterMoreImageBinding5 = adapterMoreImageBinding4;
                    }
                    View root = adapterMoreImageBinding5.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root, "moreBinding.root");
                    BaseQuickAdapter.addFooterView$default(commBaseAdapter4, root, 0, 0, 2, null);
                } else {
                    commBaseAdapter2 = this.this$0.imageAdapter;
                    if (commBaseAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                        commBaseAdapter2 = null;
                    }
                    CommBaseAdapter commBaseAdapter5 = commBaseAdapter2;
                    adapterMoreImageBinding3 = this.this$0.moreBinding;
                    if (adapterMoreImageBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
                    } else {
                        adapterMoreImageBinding5 = adapterMoreImageBinding3;
                    }
                    View root2 = adapterMoreImageBinding5.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root2, "moreBinding.root");
                    BaseQuickAdapter.addHeaderView$default(commBaseAdapter5, root2, 0, 0, 2, null);
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
        }
        Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.this$0));
        Intrinsics.checkNotNullExpressionValue(as, "timer(200,TimeUnit.MILLI…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, new AnonymousClass3(this.this$0, this.$skipTime, thumbs), null, null, null, 29, null);
        SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment4 = this.this$0;
        SkipTimeResponse skipTimeResponse = this.$skipTime;
        Intrinsics.checkNotNullExpressionValue(thumbs, "thumbs");
        skipTimeSettingFragment4.initSkipTime(skipTimeResponse, thumbs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m1124invoke$lambda0(SkipTimeFragment.SkipTimeSettingFragment this$0, ArrayList arrayList, ArrayList arrayList2, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMoreImage(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m1125invoke$lambda1(SkipTimeFragment.SkipTimeSettingFragment this$0, ArrayList arrayList, ArrayList arrayList2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMoreImage(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SkipTimeFragment.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3 extends Lambda implements Function0<Unit> {
        final /* synthetic */ SkipTimeResponse $skipTime;
        final /* synthetic */ ArrayList<VideoThumb> $thumbs;
        final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment, SkipTimeResponse skipTimeResponse, ArrayList<VideoThumb> arrayList) {
            super(0);
            this.this$0 = skipTimeSettingFragment;
            this.$skipTime = skipTimeResponse;
            this.$thumbs = arrayList;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            boolean startTimeType;
            CommBaseAdapter commBaseAdapter;
            FragmentSkipTimeSettingBinding binding;
            int i;
            FragmentSkipTimeSettingBinding binding2;
            int i2;
            FragmentSkipTimeSettingBinding binding3;
            FragmentSkipTimeSettingBinding binding4;
            int i3;
            startTimeType = this.this$0.getStartTimeType();
            if (startTimeType) {
                binding4 = this.this$0.getBinding();
                RecyclerView.LayoutManager layoutManager = binding4.rvImage.getLayoutManager();
                if (layoutManager != null) {
                    i3 = this.this$0.imageItemWidth;
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(this.$skipTime.getStart() / 2, (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i3 / 2));
                    if (this.$skipTime.getStart() == -1 || !Intrinsics.areEqual("other", this.$skipTime.getStart_type())) {
                        return;
                    }
                    this.this$0.dataLoaded = true;
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            if (this.$skipTime.getEnd() != -1 && Intrinsics.areEqual("other", this.$skipTime.getEnd_type())) {
                this.this$0.dataLoaded = true;
            }
            if (this.$skipTime.getEnd() == -1) {
                binding3 = this.this$0.getBinding();
                binding3.rvImage.scrollToPosition(this.$thumbs.size());
                return;
            }
            commBaseAdapter = this.this$0.imageAdapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                commBaseAdapter = null;
            }
            if (commBaseAdapter.hasHeaderLayout()) {
                binding2 = this.this$0.getBinding();
                RecyclerView.LayoutManager layoutManager2 = binding2.rvImage.getLayoutManager();
                if (layoutManager2 != null) {
                    i2 = this.this$0.imageItemWidth;
                    ((LinearLayoutManager) layoutManager2).scrollToPositionWithOffset(this.$thumbs.size() - (this.$skipTime.getEnd() / 2), (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i2 / 2));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            binding = this.this$0.getBinding();
            RecyclerView.LayoutManager layoutManager3 = binding.rvImage.getLayoutManager();
            if (layoutManager3 != null) {
                i = this.this$0.imageItemWidth;
                ((LinearLayoutManager) layoutManager3).scrollToPositionWithOffset((this.$thumbs.size() - (this.$skipTime.getEnd() / 2)) - 1, (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i / 2));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
    }
}
