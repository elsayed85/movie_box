package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment$yBLSx-uHGrzHSbs_a1DWH63Uj78  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment$yBLSxuHGrzHSbs_a1DWH63Uj78 implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment$yBLSxuHGrzHSbs_a1DWH63Uj78 INSTANCE = new $$Lambda$CollectFragment$yBLSxuHGrzHSbs_a1DWH63Uj78();

    private /* synthetic */ $$Lambda$CollectFragment$yBLSxuHGrzHSbs_a1DWH63Uj78() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString("movie_country_list", (String) obj);
    }
}
