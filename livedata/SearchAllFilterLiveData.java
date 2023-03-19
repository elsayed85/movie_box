package com.movieboxpro.android.livedata;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SearchAllFilterLiveData.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/livedata/SearchAllFilterLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SearchAllFilterLiveData extends MutableLiveData<Boolean> {
    public static final Companion Companion = new Companion(null);
    private static SearchAllFilterLiveData sInstance;

    /* compiled from: SearchAllFilterLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/livedata/SearchAllFilterLiveData$Companion;", "", "()V", "sInstance", "Lcom/movieboxpro/android/livedata/SearchAllFilterLiveData;", "get", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchAllFilterLiveData get() {
            SearchAllFilterLiveData searchAllFilterLiveData;
            if (SearchAllFilterLiveData.sInstance != null) {
                searchAllFilterLiveData = SearchAllFilterLiveData.sInstance;
                if (searchAllFilterLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    searchAllFilterLiveData = null;
                }
            } else {
                searchAllFilterLiveData = new SearchAllFilterLiveData();
            }
            SearchAllFilterLiveData.sInstance = searchAllFilterLiveData;
            SearchAllFilterLiveData searchAllFilterLiveData2 = SearchAllFilterLiveData.sInstance;
            if (searchAllFilterLiveData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                return null;
            }
            return searchAllFilterLiveData2;
        }
    }
}
