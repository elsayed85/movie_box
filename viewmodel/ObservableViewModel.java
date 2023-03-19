package com.movieboxpro.android.viewmodel;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ObservableViewModel.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/viewmodel/ObservableViewModel;", "Landroidx/lifecycle/ViewModel;", "Landroidx/databinding/Observable;", "()V", "callbacks", "Landroidx/databinding/PropertyChangeRegistry;", "getCallbacks", "()Landroidx/databinding/PropertyChangeRegistry;", "callbacks$delegate", "Lkotlin/Lazy;", "addOnPropertyChangedCallback", "", "callback", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "notifyChange", "notifyPropertyChanged", "fieldId", "", "removeOnPropertyChangedCallback", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ObservableViewModel extends ViewModel implements Observable {
    private final Lazy callbacks$delegate = LazyKt.lazy(ObservableViewModel$callbacks$2.INSTANCE);

    private final PropertyChangeRegistry getCallbacks() {
        return (PropertyChangeRegistry) this.callbacks$delegate.getValue();
    }

    @Override // androidx.databinding.Observable
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        getCallbacks().add(callback);
    }

    @Override // androidx.databinding.Observable
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        getCallbacks().remove(callback);
    }

    public final void notifyChange() {
        getCallbacks().notifyCallbacks(this, 0, null);
    }

    public final void notifyPropertyChanged(int i) {
        getCallbacks().notifyCallbacks(this, i, null);
    }
}
