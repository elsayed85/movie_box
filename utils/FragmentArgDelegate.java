package com.movieboxpro.android.utils;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FragmentArgs.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0096\u0002J%\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u000b\u001a\u00020\u0003H\u0096\u0002¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/utils/FragmentArgDelegate;", "Lkotlin/properties/ReadWriteProperty;", "Landroidx/fragment/app/Fragment;", "", "()V", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "setValue", "", "value", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FragmentArgDelegate implements ReadWriteProperty<Fragment, Object> {
    public static final FragmentArgDelegate INSTANCE = new FragmentArgDelegate();

    private FragmentArgDelegate() {
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return getValue((Fragment) obj, (KProperty<?>) kProperty);
    }

    @Override // kotlin.properties.ReadWriteProperty
    public /* bridge */ /* synthetic */ void setValue(Fragment fragment, KProperty kProperty, Object obj) {
        setValue2(fragment, (KProperty<?>) kProperty, obj);
    }

    public Object getValue(Fragment thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        String name = property.getName();
        Bundle arguments = thisRef.getArguments();
        if (arguments == null) {
            throw new IllegalStateException(("Cannot read property " + name + " if no arguments have been set").toString());
        }
        Object obj = arguments.get(name);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException(("Property " + name + " could not be read").toString());
    }

    /* renamed from: setValue  reason: avoid collision after fix types in other method */
    public void setValue2(Fragment thisRef, KProperty<?> property, Object value) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(value, "value");
        FragmentArgsKt.putArg(thisRef, property.getName(), value);
    }
}
