package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU INSTANCE = new $$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU();

    private /* synthetic */ $$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString("movie_country_list", (String) obj);
    }
}
