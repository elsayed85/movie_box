package com.movieboxpro.android.view.fragment.home;

import com.movieboxpro.android.event.OpenFilterEvent;
import com.movieboxpro.android.model.CountryResponse;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoviesFragment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/CountryResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoviesFragment$getCountry$4 extends Lambda implements Function1<CountryResponse, Unit> {
    final /* synthetic */ OpenFilterEvent $event;
    final /* synthetic */ MoviesFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoviesFragment$getCountry$4(MoviesFragment moviesFragment, OpenFilterEvent openFilterEvent) {
        super(1);
        this.this$0 = moviesFragment;
        this.$event = openFilterEvent;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CountryResponse countryResponse) {
        invoke2(countryResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(CountryResponse countryResponse) {
        ArrayList arrayList;
        this.this$0.hideLoadingView();
        this.this$0.countryList = countryResponse.getCountry_list();
        MoviesFragment moviesFragment = this.this$0;
        OpenFilterEvent openFilterEvent = this.$event;
        arrayList = moviesFragment.countryList;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        moviesFragment.openFilter(openFilterEvent, arrayList);
    }
}
