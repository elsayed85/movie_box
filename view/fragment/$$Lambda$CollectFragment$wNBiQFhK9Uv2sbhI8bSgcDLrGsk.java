package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk INSTANCE = new $$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk();

    private /* synthetic */ $$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString(Constant.Prefs.FILTER_GENER, (String) obj);
    }
}
