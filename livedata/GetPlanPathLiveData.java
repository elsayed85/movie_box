package com.movieboxpro.android.livedata;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GetPlanPathLiveData.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00072$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00050\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/livedata/GetPlanPathLiveData;", "Lcom/kunminx/architecture/ui/callback/UnPeekLiveData;", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "()V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetPlanPathLiveData extends UnPeekLiveData<HashMap<String, Object>> {
    public static final Companion Companion = new Companion(null);
    private static GetPlanPathLiveData sInstance;

    /* compiled from: GetPlanPathLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/livedata/GetPlanPathLiveData$Companion;", "", "()V", "sInstance", "Lcom/movieboxpro/android/livedata/GetPlanPathLiveData;", "get", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GetPlanPathLiveData get() {
            GetPlanPathLiveData getPlanPathLiveData;
            if (GetPlanPathLiveData.sInstance != null) {
                getPlanPathLiveData = GetPlanPathLiveData.sInstance;
                if (getPlanPathLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    getPlanPathLiveData = null;
                }
            } else {
                getPlanPathLiveData = new GetPlanPathLiveData();
            }
            GetPlanPathLiveData.sInstance = getPlanPathLiveData;
            GetPlanPathLiveData getPlanPathLiveData2 = GetPlanPathLiveData.sInstance;
            if (getPlanPathLiveData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                return null;
            }
            return getPlanPathLiveData2;
        }
    }
}
