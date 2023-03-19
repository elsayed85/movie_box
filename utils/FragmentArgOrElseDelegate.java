package com.movieboxpro.android.utils;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: FragmentArgs.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0096\u0002¢\u0006\u0002\u0010\fJ*\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/utils/FragmentArgOrElseDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/properties/ReadWriteProperty;", "Landroidx/fragment/app/Fragment;", "defaultValue", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class FragmentArgOrElseDelegate<T> implements ReadWriteProperty<Fragment, T> {
    private final Function0<T> defaultValue;

    /* JADX WARN: Multi-variable type inference failed */
    public FragmentArgOrElseDelegate(Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        this.defaultValue = defaultValue;
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return getValue((Fragment) obj, (KProperty<?>) kProperty);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.properties.ReadWriteProperty
    public /* bridge */ /* synthetic */ void setValue(Fragment fragment, KProperty kProperty, Object obj) {
        setValue2(fragment, (KProperty<?>) kProperty, (KProperty) obj);
    }

    public T getValue(Fragment thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        Bundle arguments = thisRef.getArguments();
        T t = arguments == null ? null : (T) arguments.get(property.getName());
        return t == null ? this.defaultValue.invoke() : t;
    }

    /* renamed from: setValue  reason: avoid collision after fix types in other method */
    public void setValue2(Fragment thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(value, "value");
        FragmentArgsKt.putArg(thisRef, property.getName(), value);
    }
}
