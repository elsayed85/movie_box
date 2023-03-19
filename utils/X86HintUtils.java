package com.movieboxpro.android.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
/* compiled from: X86HintUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/utils/X86HintUtils;", "", "()V", "checkX86", "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class X86HintUtils {
    public static final X86HintUtils INSTANCE = new X86HintUtils();

    @JvmStatic
    public static final boolean checkX86(Context context) {
        return false;
    }

    private X86HintUtils() {
    }

    /* renamed from: checkX86$lambda-1$lambda-0  reason: not valid java name */
    private static final void m118checkX86$lambda1$lambda0(Context context) {
        ClipboardUtil.copyContent(context, "https://www.movieboxpro.app/apk_x86");
        ToastUtils.showShort("Copy link successfully", new Object[0]);
    }
}
