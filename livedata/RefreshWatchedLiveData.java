package com.movieboxpro.android.livedata;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RefreshWatchedLiveData.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/livedata/RefreshWatchedLiveData;", "Lcom/kunminx/architecture/ui/callback/UnPeekLiveData;", "", "()V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RefreshWatchedLiveData extends UnPeekLiveData<Boolean> {
    public static final Companion Companion = new Companion(null);
    private static RefreshWatchedLiveData sInstance;

    /* compiled from: RefreshWatchedLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/livedata/RefreshWatchedLiveData$Companion;", "", "()V", "sInstance", "Lcom/movieboxpro/android/livedata/RefreshWatchedLiveData;", "get", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RefreshWatchedLiveData get() {
            RefreshWatchedLiveData refreshWatchedLiveData;
            if (RefreshWatchedLiveData.sInstance != null) {
                refreshWatchedLiveData = RefreshWatchedLiveData.sInstance;
                if (refreshWatchedLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    refreshWatchedLiveData = null;
                }
            } else {
                refreshWatchedLiveData = new RefreshWatchedLiveData();
            }
            RefreshWatchedLiveData.sInstance = refreshWatchedLiveData;
            RefreshWatchedLiveData refreshWatchedLiveData2 = RefreshWatchedLiveData.sInstance;
            if (refreshWatchedLiveData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                return null;
            }
            return refreshWatchedLiveData2;
        }
    }
}
