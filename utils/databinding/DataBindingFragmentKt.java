package com.movieboxpro.android.utils.databinding;

import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DataBindingFragment.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"binding", "Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/Fragment;", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DataBindingFragmentKt {
    public static final /* synthetic */ <T extends ViewDataBinding> FragmentBindingDelegate<T> binding(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new FragmentBindingDelegate<>(ViewDataBinding.class, fragment);
    }
}
