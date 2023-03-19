package com.movieboxpro.android.livedata;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NetSpeedLiveData.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/livedata/NetSpeedLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetSpeedLiveData extends MutableLiveData<Long> {
    public static final Companion Companion = new Companion(null);
    private static NetSpeedLiveData sInstance;

    /* compiled from: NetSpeedLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/livedata/NetSpeedLiveData$Companion;", "", "()V", "sInstance", "Lcom/movieboxpro/android/livedata/NetSpeedLiveData;", "get", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NetSpeedLiveData get() {
            NetSpeedLiveData netSpeedLiveData;
            if (NetSpeedLiveData.sInstance != null) {
                netSpeedLiveData = NetSpeedLiveData.sInstance;
                if (netSpeedLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    netSpeedLiveData = null;
                }
            } else {
                netSpeedLiveData = new NetSpeedLiveData();
            }
            NetSpeedLiveData.sInstance = netSpeedLiveData;
            NetSpeedLiveData netSpeedLiveData2 = NetSpeedLiveData.sInstance;
            if (netSpeedLiveData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                return null;
            }
            return netSpeedLiveData2;
        }
    }
}
