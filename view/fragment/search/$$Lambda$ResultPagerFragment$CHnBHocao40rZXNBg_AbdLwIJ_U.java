package com.movieboxpro.android.view.fragment.search;

import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.search.-$$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U implements Function {
    public static final /* synthetic */ $$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U INSTANCE = new $$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U();

    private /* synthetic */ $$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString(Constant.Prefs.FILTER_GENER, (String) obj);
    }
}
