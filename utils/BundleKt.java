package com.movieboxpro.android.utils;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Bundle.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a%\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0002\u0010\t\u001a(\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0002\b\u00030\u000bj\u0006\u0012\u0002\b\u0003`\fH\u0002\u001a \u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0002¨\u0006\u000f"}, d2 = {"put", "", "Landroid/os/Bundle;", "key", "", "value", "", "putArray", "", "(Landroid/os/Bundle;Ljava/lang/String;[Ljava/lang/Object;)V", "putArrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "putSparseArrayOfParcelable", "Landroid/util/SparseArray;", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BundleKt {
    public static final void put(Bundle bundle, String key, Object obj) {
        Intrinsics.checkNotNullParameter(bundle, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj instanceof String) {
            bundle.putString(key, (String) obj);
        } else if (obj instanceof int[]) {
            bundle.putIntArray(key, (int[]) obj);
        } else if (obj instanceof short[]) {
            bundle.putShortArray(key, (short[]) obj);
        } else if (obj instanceof long[]) {
            bundle.putLongArray(key, (long[]) obj);
        } else if (obj instanceof byte[]) {
            bundle.putByteArray(key, (byte[]) obj);
        } else if (obj instanceof float[]) {
            bundle.putFloatArray(key, (float[]) obj);
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(key, (double[]) obj);
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(key, (boolean[]) obj);
        } else if (obj instanceof char[]) {
            bundle.putCharArray(key, (char[]) obj);
        } else if (obj instanceof CharSequence) {
            bundle.putCharSequence(key, (CharSequence) obj);
        } else if (obj instanceof Bundle) {
            bundle.putBundle(key, (Bundle) obj);
        } else if (obj instanceof Object[]) {
            putArray(bundle, key, (Object[]) obj);
        } else if (obj instanceof ArrayList) {
            putArrayList(bundle, key, (ArrayList) obj);
        } else if (obj instanceof SparseArray) {
            putSparseArrayOfParcelable(bundle, key, (SparseArray) obj);
        } else if (obj instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) obj);
        } else if (obj instanceof Serializable) {
            bundle.putSerializable(key, (Serializable) obj);
        } else if (obj == null) {
            bundle.putString(key, (String) obj);
        } else {
            throw new UnsupportedOperationException("Type " + ((Object) obj.getClass().getCanonicalName()) + " is not supported");
        }
    }

    private static final void putArray(Bundle bundle, String str, Object[] objArr) {
        if (objArr instanceof CharSequence[]) {
            bundle.putCharSequenceArray(str, (CharSequence[]) objArr);
        } else if (objArr instanceof String[]) {
            bundle.putStringArray(str, (String[]) objArr);
        } else if (objArr instanceof Parcelable[]) {
            bundle.putParcelableArray(str, (Parcelable[]) objArr);
        } else {
            throw new UnsupportedOperationException("Array type " + ((Object) objArr.getClass().getCanonicalName()) + " is not supported");
        }
    }

    private static final void putArrayList(Bundle bundle, String str, ArrayList<?> arrayList) {
        ArrayList<?> arrayList2 = arrayList;
        Object firstOrNull = CollectionsKt.firstOrNull((List<? extends Object>) arrayList2);
        if (firstOrNull instanceof CharSequence) {
            bundle.putCharSequenceArrayList(str, arrayList);
        } else if (firstOrNull instanceof String) {
            bundle.putStringArrayList(str, arrayList);
        } else if (firstOrNull instanceof Parcelable) {
            bundle.putParcelableArrayList(str, arrayList);
        } else {
            boolean z = true;
            if (!(firstOrNull instanceof Integer) && firstOrNull != null) {
                z = false;
            }
            if (z) {
                bundle.putIntegerArrayList(str, arrayList);
                return;
            }
            throw new UnsupportedOperationException("Type " + ((Object) CollectionsKt.first((List<? extends Object>) arrayList2).getClass().getCanonicalName()) + " in ArrayList is not supported");
        }
    }

    private static final void putSparseArrayOfParcelable(Bundle bundle, String str, SparseArray<?> sparseArray) {
        bundle.putSparseParcelableArray(str, sparseArray);
    }
}
