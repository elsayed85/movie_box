package com.movieboxpro.android.viewmodel;

import androidx.databinding.PropertyChangeRegistry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ObservableViewModel.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/databinding/PropertyChangeRegistry;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ObservableViewModel$callbacks$2 extends Lambda implements Function0<PropertyChangeRegistry> {
    public static final ObservableViewModel$callbacks$2 INSTANCE = new ObservableViewModel$callbacks$2();

    ObservableViewModel$callbacks$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final PropertyChangeRegistry invoke() {
        return new PropertyChangeRegistry();
    }
}
