package com.movieboxpro.android.view.fragment.search;

import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.search.-$$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw implements Function {
    public static final /* synthetic */ $$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw INSTANCE = new $$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw();

    private /* synthetic */ $$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString("movie_country_list", (String) obj);
    }
}
