package com.movieboxpro.android.utils;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
/* compiled from: FragmentArgs.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u00020\u0002\u001a-\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u00020\u00022\u0006\u0010\u0006\u001a\u0002H\u0003¢\u0006\u0002\u0010\u0007\u001a.\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\t\u001a\u001e\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u0002\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0000¨\u0006\u0010"}, d2 = {"arg", "Lkotlin/properties/ReadWriteProperty;", "Landroidx/fragment/app/Fragment;", ExifInterface.GPS_DIRECTION_TRUE, "", "argOrDefault", "defaultValue", "(Landroidx/fragment/app/Fragment;Ljava/lang/Object;)Lkotlin/properties/ReadWriteProperty;", "argOrElse", "Lkotlin/Function0;", "argOrNull", "putArg", "", "key", "", "value", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FragmentArgsKt {
    public static final <T> ReadWriteProperty<Fragment, T> arg(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return FragmentArgDelegate.INSTANCE;
    }

    public static final <T> ReadWriteProperty<Fragment, T> argOrNull(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return FragmentArgOrNullDelegate.INSTANCE;
    }

    public static final <T> ReadWriteProperty<Fragment, T> argOrDefault(Fragment fragment, T defaultValue) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return new FragmentArgOrDefaultDelegate(defaultValue);
    }

    public static final <T> ReadWriteProperty<Fragment, T> argOrElse(Fragment fragment, Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return new FragmentArgOrElseDelegate(defaultValue);
    }

    public static final void putArg(Fragment fragment, String key, Object obj) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        BundleKt.put(arguments, key, obj);
    }
}
