package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.utils.CacheDiskUtils;
import io.reactivex.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI implements Consumer {
    public static final /* synthetic */ $$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI INSTANCE = new $$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI();

    private /* synthetic */ $$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI() {
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Object obj) {
        CacheDiskUtils.getInstance().put("SEARCH_HOT", (String) obj);
    }
}
