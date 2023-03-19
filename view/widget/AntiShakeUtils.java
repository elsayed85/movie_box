package com.movieboxpro.android.view.widget;

import android.view.View;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class AntiShakeUtils {
    private static final long INTERNAL_TIME = 1000;

    public static boolean isInvalidClick(View view) {
        return isInvalidClick(view, 1000L);
    }

    public static boolean isInvalidClick(View view, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        Object tag = view.getTag(R.id.last_click_time);
        if (tag == null) {
            view.setTag(R.id.last_click_time, Long.valueOf(currentTimeMillis));
            return false;
        }
        boolean z = currentTimeMillis - ((Long) tag).longValue() < j;
        if (!z) {
            view.setTag(R.id.last_click_time, Long.valueOf(currentTimeMillis));
        }
        return z;
    }
}
