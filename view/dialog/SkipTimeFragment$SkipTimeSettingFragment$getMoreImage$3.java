package com.movieboxpro.android.view.dialog;

import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.adapter.SkipTimeAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.AdapterMoreImageBinding;
import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBinding;
import com.movieboxpro.android.model.SkipEnd;
import com.movieboxpro.android.model.SkipStart;
import com.movieboxpro.android.model.SkipTimeItem;
import com.movieboxpro.android.model.VideoThumb;
import com.movieboxpro.android.model.VideoThumbResponse;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lcom/movieboxpro/android/model/VideoThumbResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$3 extends Lambda implements Function1<VideoThumbResponse, Unit> {
    final /* synthetic */ ArrayList<SkipEnd> $endTimes;
    final /* synthetic */ ArrayList<SkipStart> $times;
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment, ArrayList<SkipStart> arrayList, ArrayList<SkipEnd> arrayList2) {
        super(1);
        this.this$0 = skipTimeSettingFragment;
        this.$times = arrayList;
        this.$endTimes = arrayList2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(VideoThumbResponse videoThumbResponse) {
        invoke2(videoThumbResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(VideoThumbResponse videoThumbResponse) {
        AdapterMoreImageBinding adapterMoreImageBinding;
        AdapterMoreImageBinding adapterMoreImageBinding2;
        boolean startTimeType;
        SkipTimeAdapter skipTimeAdapter;
        int i;
        SkipTimeAdapter skipTimeAdapter2;
        int i2;
        CommBaseAdapter commBaseAdapter;
        Object obj;
        boolean z;
        SkipTimeAdapter skipTimeAdapter3;
        SkipTimeAdapter skipTimeAdapter4;
        SkipTimeAdapter skipTimeAdapter5;
        int i3;
        SkipTimeAdapter skipTimeAdapter6;
        SkipTimeAdapter skipTimeAdapter7;
        int i4;
        CommBaseAdapter commBaseAdapter2;
        Object obj2;
        boolean z2;
        SkipTimeAdapter skipTimeAdapter8;
        SkipTimeAdapter skipTimeAdapter9;
        adapterMoreImageBinding = this.this$0.moreBinding;
        CommBaseAdapter commBaseAdapter3 = null;
        if (adapterMoreImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
            adapterMoreImageBinding = null;
        }
        adapterMoreImageBinding.loadingView.hideLoading();
        adapterMoreImageBinding2 = this.this$0.moreBinding;
        if (adapterMoreImageBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreBinding");
            adapterMoreImageBinding2 = null;
        }
        FrameLayout frameLayout = adapterMoreImageBinding2.frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "moreBinding.frameLayout");
        CommonExtKt.gone(frameLayout);
        ArrayList arrayList = new ArrayList();
        startTimeType = this.this$0.getStartTimeType();
        if (startTimeType) {
            skipTimeAdapter5 = this.this$0.adapter;
            if (skipTimeAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                skipTimeAdapter5 = null;
            }
            int size = skipTimeAdapter5.getData().size() - 1;
            if (size >= 0) {
                while (true) {
                    int i5 = size - 1;
                    skipTimeAdapter8 = this.this$0.adapter;
                    if (skipTimeAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        skipTimeAdapter8 = null;
                    }
                    if (skipTimeAdapter8.getItem(size).getTime() != -1) {
                        skipTimeAdapter9 = this.this$0.adapter;
                        if (skipTimeAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            skipTimeAdapter9 = null;
                        }
                        i3 = skipTimeAdapter9.getItem(size).getTime();
                    } else if (i5 < 0) {
                        break;
                    } else {
                        size = i5;
                    }
                }
            }
            i3 = 0;
            ArrayList<VideoThumb> thumbs = videoThumbResponse.getThumbs();
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(2, (thumbs == null ? 0 : thumbs.size()) * 2, 2);
            if (2 <= progressionLastElement) {
                int i6 = 2;
                while (true) {
                    int i7 = i6 + 2;
                    arrayList.add(new SkipTimeItem(i6 + i3, 0));
                    if (i6 == progressionLastElement) {
                        break;
                    }
                    i6 = i7;
                }
            }
            ArrayList<SkipStart> arrayList2 = this.$times;
            if (arrayList2 != null) {
                for (SkipStart skipStart : arrayList2) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        if (((SkipTimeItem) obj2).getTime() == skipStart.getStart()) {
                            z2 = true;
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                        if (z2) {
                            break;
                        }
                    }
                    SkipTimeItem skipTimeItem = (SkipTimeItem) obj2;
                    if (skipTimeItem != null) {
                        skipTimeItem.setTotal(skipStart.getTotal());
                    }
                }
            }
            skipTimeAdapter6 = this.this$0.adapter;
            if (skipTimeAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                skipTimeAdapter6 = null;
            }
            skipTimeAdapter7 = this.this$0.adapter;
            if (skipTimeAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                skipTimeAdapter7 = null;
            }
            int size2 = skipTimeAdapter7.getData().size();
            i4 = this.this$0.count;
            skipTimeAdapter6.addData(size2 - (i4 / 2), (Collection) arrayList);
            commBaseAdapter2 = this.this$0.imageAdapter;
            if (commBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
            } else {
                commBaseAdapter3 = commBaseAdapter2;
            }
            ArrayList<VideoThumb> thumbs2 = videoThumbResponse.getThumbs();
            if (thumbs2 == null) {
                thumbs2 = new ArrayList<>();
            }
            commBaseAdapter3.addData((Collection) thumbs2);
            return;
        }
        skipTimeAdapter = this.this$0.adapter;
        if (skipTimeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            skipTimeAdapter = null;
        }
        int size3 = skipTimeAdapter.getData().size();
        int i8 = 0;
        while (true) {
            if (i8 >= size3) {
                i = 0;
                break;
            }
            int i9 = i8 + 1;
            skipTimeAdapter3 = this.this$0.adapter;
            if (skipTimeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                skipTimeAdapter3 = null;
            }
            if (skipTimeAdapter3.getItem(i8).getTime() != -1) {
                skipTimeAdapter4 = this.this$0.adapter;
                if (skipTimeAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    skipTimeAdapter4 = null;
                }
                i = skipTimeAdapter4.getItem(i8).getTime();
            } else {
                i8 = i9;
            }
        }
        ArrayList<VideoThumb> thumbs3 = videoThumbResponse.getThumbs();
        int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(2, (thumbs3 == null ? 0 : thumbs3.size()) * 2, 2);
        if (2 <= progressionLastElement2) {
            int i10 = 2;
            while (true) {
                int i11 = i10 + 2;
                arrayList.add(new SkipTimeItem(i10 + i, 0));
                if (i10 == progressionLastElement2) {
                    break;
                }
                i10 = i11;
            }
        }
        ArrayList<SkipEnd> arrayList3 = this.$endTimes;
        if (arrayList3 != null) {
            for (SkipEnd skipEnd : arrayList3) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    if (((SkipTimeItem) obj).getTime() == skipEnd.getEnd()) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                SkipTimeItem skipTimeItem2 = (SkipTimeItem) obj;
                if (skipTimeItem2 != null) {
                    skipTimeItem2.setTotal(skipEnd.getTotal());
                }
            }
        }
        skipTimeAdapter2 = this.this$0.adapter;
        if (skipTimeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            skipTimeAdapter2 = null;
        }
        i2 = this.this$0.count;
        skipTimeAdapter2.addData(i2 / 2, (Collection) CollectionsKt.reversed(arrayList));
        commBaseAdapter = this.this$0.imageAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
        } else {
            commBaseAdapter3 = commBaseAdapter;
        }
        ArrayList<VideoThumb> thumbs4 = videoThumbResponse.getThumbs();
        if (thumbs4 == null) {
            thumbs4 = new ArrayList<>();
        }
        commBaseAdapter3.addData(0, (Collection) thumbs4);
        Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.this$0));
        Intrinsics.checkNotNullExpressionValue(as, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, new AnonymousClass3(this.this$0, videoThumbResponse, arrayList), null, null, null, 29, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SkipTimeFragment.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$3$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3 extends Lambda implements Function0<Unit> {
        final /* synthetic */ ArrayList<SkipTimeItem> $moreTimes;
        final /* synthetic */ VideoThumbResponse $response;
        final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment, VideoThumbResponse videoThumbResponse, ArrayList<SkipTimeItem> arrayList) {
            super(0);
            this.this$0 = skipTimeSettingFragment;
            this.$response = videoThumbResponse;
            this.$moreTimes = arrayList;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            FragmentSkipTimeSettingBinding binding;
            int i;
            FragmentSkipTimeSettingBinding binding2;
            binding = this.this$0.getBinding();
            RecyclerView.LayoutManager layoutManager = binding.rvImage.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            ArrayList<VideoThumb> thumbs = this.$response.getThumbs();
            int size = thumbs == null ? 0 : thumbs.size();
            i = this.this$0.imageItemWidth;
            linearLayoutManager.scrollToPositionWithOffset(size + 1, (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i / 2));
            binding2 = this.this$0.getBinding();
            RecyclerView.LayoutManager layoutManager2 = binding2.rvOpening.getLayoutManager();
            if (layoutManager2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            ((LinearLayoutManager) layoutManager2).scrollToPositionWithOffset(this.$moreTimes.size(), 0);
        }
    }
}
