package com.movieboxpro.android.utils.databinding;

import android.view.LayoutInflater;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ReflectExt.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u0007\u001a\u001e\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"BIND_NAME", "", "INFLATE_NAME", "bindMethod", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/Class;", "inflateMethod", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReflectExtKt {
    public static final String BIND_NAME = "bind";
    public static final String INFLATE_NAME = "inflate";

    public static final <T> Method inflateMethod(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return cls.getMethod(INFLATE_NAME, LayoutInflater.class);
    }

    public static final <T> Method bindMethod(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return cls.getMethod(BIND_NAME, View.class);
    }
}
