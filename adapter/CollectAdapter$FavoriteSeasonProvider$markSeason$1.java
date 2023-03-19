package com.movieboxpro.android.adapter;

import com.movieboxpro.android.adapter.CollectAdapter;
import com.movieboxpro.android.base.LoadView;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.ToastUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CollectAdapter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/http/ApiException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CollectAdapter$FavoriteSeasonProvider$markSeason$1 extends Lambda implements Function1<ApiException, Unit> {
    final /* synthetic */ CollectAdapter.FavoriteSeasonProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectAdapter$FavoriteSeasonProvider$markSeason$1(CollectAdapter.FavoriteSeasonProvider favoriteSeasonProvider) {
        super(1);
        this.this$0 = favoriteSeasonProvider;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
        invoke2(apiException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(ApiException it) {
        LoadView loadView;
        Intrinsics.checkNotNullParameter(it, "it");
        loadView = this.this$0.loadView;
        loadView.hideLoadingView();
        ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", it.getMessage()), new Object[0]);
    }
}
