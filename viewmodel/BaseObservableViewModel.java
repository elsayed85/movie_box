package com.movieboxpro.android.viewmodel;

import com.movieboxpro.android.base.LoadView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseObservableViewModel.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/viewmodel/BaseObservableViewModel;", "Lcom/movieboxpro/android/viewmodel/ObservableViewModel;", "()V", "loadView", "Lcom/movieboxpro/android/base/LoadView;", "getLoadView", "()Lcom/movieboxpro/android/base/LoadView;", "setLoadView", "(Lcom/movieboxpro/android/base/LoadView;)V", "setLoadingView", "", "loadingView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BaseObservableViewModel extends ObservableViewModel {
    protected LoadView loadView;

    /* JADX INFO: Access modifiers changed from: protected */
    public final LoadView getLoadView() {
        LoadView loadView = this.loadView;
        if (loadView != null) {
            return loadView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("loadView");
        return null;
    }

    protected final void setLoadView(LoadView loadView) {
        Intrinsics.checkNotNullParameter(loadView, "<set-?>");
        this.loadView = loadView;
    }

    public final void setLoadingView(LoadView loadingView) {
        Intrinsics.checkNotNullParameter(loadingView, "loadingView");
        setLoadView(loadingView);
    }
}
