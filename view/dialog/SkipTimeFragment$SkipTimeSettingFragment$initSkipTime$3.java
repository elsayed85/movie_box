package com.movieboxpro.android.view.dialog;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.adapter.SkipTimeAdapter;
import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBinding;
import com.movieboxpro.android.model.SkipTimeItem;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ int $count;
    final /* synthetic */ SkipTimeResponse $response;
    final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$3(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment, int i, SkipTimeResponse skipTimeResponse) {
        super(0);
        this.this$0 = skipTimeSettingFragment;
        this.$count = i;
        this.$response = skipTimeResponse;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        SkipTimeAdapter skipTimeAdapter;
        FragmentSkipTimeSettingBinding binding;
        skipTimeAdapter = this.this$0.adapter;
        if (skipTimeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            skipTimeAdapter = null;
        }
        List<SkipTimeItem> data = skipTimeAdapter.getData();
        SkipTimeResponse skipTimeResponse = this.$response;
        Iterator<SkipTimeItem> it = data.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getTime() == skipTimeResponse.getEnd()) {
                break;
            }
            i++;
        }
        if (i != -1) {
            binding = this.this$0.getBinding();
            RecyclerView.LayoutManager layoutManager = binding.rvOpening.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i - (this.$count / 2), 0);
        }
        Object as = Observable.timer(300L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.this$0));
        Intrinsics.checkNotNullExpressionValue(as, "timer(300, TimeUnit.MILL…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, new AnonymousClass1(this.this$0), null, null, null, 29, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SkipTimeFragment.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.view.dialog.SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$3$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ SkipTimeFragment.SkipTimeSettingFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SkipTimeFragment.SkipTimeSettingFragment skipTimeSettingFragment) {
            super(0);
            this.this$0 = skipTimeSettingFragment;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.dataLoaded = true;
        }
    }
}
