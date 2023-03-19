package com.movieboxpro.android.utils;

import androidx.core.app.NotificationCompat;
import com.flurry.android.FlurryAgent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: EventUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/utils/EventUtils;", "", "()V", NotificationCompat.CATEGORY_EVENT, "", "eventName", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EventUtils {
    public static final EventUtils INSTANCE = new EventUtils();

    private EventUtils() {
    }

    @JvmStatic
    public static final void event(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        FlurryAgent.logEvent(eventName);
    }
}
