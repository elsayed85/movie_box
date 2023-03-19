package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.functions.Function;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment2$OYN86Glreq738OtNjx-7ESsmk4Q  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment2$OYN86Glreq738OtNjx7ESsmk4Q implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment2$OYN86Glreq738OtNjx7ESsmk4Q INSTANCE = new $$Lambda$CollectFragment2$OYN86Glreq738OtNjx7ESsmk4Q();

    private /* synthetic */ $$Lambda$CollectFragment2$OYN86Glreq738OtNjx7ESsmk4Q() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        return PrefsUtils.getInstance().putString(Constant.Prefs.FILTER_GENER, (String) obj);
    }
}
