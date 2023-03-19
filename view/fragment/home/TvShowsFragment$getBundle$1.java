package com.movieboxpro.android.view.fragment.home;

import com.movieboxpro.android.event.OnFilterCompleteEvent;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.greenrobot.eventbus.EventBus;
/* compiled from: TvShowsFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TvShowsFragment$getBundle$1 extends Lambda implements Function1<Long, Unit> {
    final /* synthetic */ TvShowsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvShowsFragment$getBundle$1(TvShowsFragment tvShowsFragment) {
        super(1);
        this.this$0 = tvShowsFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
        invoke2(l);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Long l) {
        String str;
        ArrayList arrayList;
        String str2;
        String str3;
        ArrayList arrayList2;
        String str4;
        String str5;
        str = this.this$0.year;
        if (str.length() == 0) {
            arrayList = this.this$0.genre;
            if (arrayList.isEmpty()) {
                str2 = this.this$0.sort;
                if (Intrinsics.areEqual(str2, FilterVideoDialog.TV_DEFAULT_SORT)) {
                    str3 = this.this$0.rating;
                    if (str3.length() == 0) {
                        arrayList2 = this.this$0.country;
                        if (arrayList2.isEmpty()) {
                            str4 = this.this$0.imdbRating;
                            if (Intrinsics.areEqual(str4, "")) {
                                str5 = this.this$0.tomatoMeter;
                                if (Intrinsics.areEqual(str5, "")) {
                                    EventBus.getDefault().post(new OnFilterCompleteEvent(false, false));
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        EventBus.getDefault().post(new OnFilterCompleteEvent(true, false));
    }
}
