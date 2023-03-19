package com.movieboxpro.android.utils;

import android.net.TrafficStats;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.listener.TrafficSpeedListener;
import com.movieboxpro.android.livedata.NetSpeedLiveData;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
/* compiled from: TestNetSpeedUtils.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010\u0016\u001a\u00020\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/utils/TestNetSpeedUtils;", "", "()V", "lastTotal", "", "speedList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "startTime", "startTraffic", "stopped", "", "testCount", "", "testDisposable", "Lio/reactivex/disposables/Disposable;", "totalSpeed10s", "getNetSpeed", "startTestSpeed", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "stopTestSpeed", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/TrafficSpeedListener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestNetSpeedUtils {
    private static long lastTotal;
    private static long startTime;
    private static long startTraffic;
    private static boolean stopped;
    private static int testCount;
    private static Disposable testDisposable;
    private static long totalSpeed10s;
    public static final TestNetSpeedUtils INSTANCE = new TestNetSpeedUtils();
    private static ArrayList<Long> speedList = new ArrayList<>();

    @JvmStatic
    public static final void stopTestSpeed() {
        stopTestSpeed$default(null, 1, null);
    }

    private TestNetSpeedUtils() {
    }

    private final long getNetSpeed() {
        if (TrafficStats.getTotalRxBytes() == -1) {
            return 0L;
        }
        return TrafficStats.getTotalRxBytes() / 1024;
    }

    public final void startTestSpeed(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        startTime = System.currentTimeMillis();
        startTraffic = getNetSpeed();
        speedList.clear();
        Disposable disposable = testDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        testCount = 0;
        totalSpeed10s = 0L;
        stopped = false;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = System.currentTimeMillis();
        final Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = getNetSpeed();
        Object as = Observable.interval(0L, 300L, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.movieboxpro.android.utils.-$$Lambda$TestNetSpeedUtils$zCzol-lq77SV3IoKX0Clxenr1E8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Unit m117startTestSpeed$lambda0;
                m117startTestSpeed$lambda0 = TestNetSpeedUtils.m117startTestSpeed$lambda0(Ref.LongRef.this, longRef2, (Long) obj);
                return m117startTestSpeed$lambda0;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "interval(0, 300, TimeUni…cleOwner(lifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, TestNetSpeedUtils$startTestSpeed$2.INSTANCE, TestNetSpeedUtils$startTestSpeed$3.INSTANCE, null, TestNetSpeedUtils$startTestSpeed$4.INSTANCE, 9, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTestSpeed$lambda-0  reason: not valid java name */
    public static final Unit m117startTestSpeed$lambda0(Ref.LongRef lastTime, Ref.LongRef lastTraffic, Long it) {
        Intrinsics.checkNotNullParameter(lastTime, "$lastTime");
        Intrinsics.checkNotNullParameter(lastTraffic, "$lastTraffic");
        Intrinsics.checkNotNullParameter(it, "it");
        long netSpeed = ((INSTANCE.getNetSpeed() - lastTraffic.element) * 1000) / (System.currentTimeMillis() - lastTime.element);
        Log.d("startTestSpeed", String.valueOf(netSpeed));
        if (netSpeed != 0) {
            speedList.add(Long.valueOf(netSpeed));
        }
        lastTime.element = System.currentTimeMillis();
        lastTraffic.element = INSTANCE.getNetSpeed();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void stopTestSpeed$default(TrafficSpeedListener trafficSpeedListener, int i, Object obj) {
        if ((i & 1) != 0) {
            trafficSpeedListener = null;
        }
        stopTestSpeed(trafficSpeedListener);
    }

    @JvmStatic
    public static final void stopTestSpeed(TrafficSpeedListener trafficSpeedListener) {
        if (stopped) {
            return;
        }
        stopped = true;
        long netSpeed = ((INSTANCE.getNetSpeed() - startTraffic) * 1000) / (System.currentTimeMillis() - startTime);
        if (netSpeed != 0) {
            speedList.add(Long.valueOf(netSpeed));
        }
        if (speedList.size() > 0) {
            Iterator<T> it = speedList.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            long longValue = ((Number) it.next()).longValue();
            while (it.hasNext()) {
                long longValue2 = ((Number) it.next()).longValue();
                if (longValue < longValue2) {
                    longValue = longValue2;
                }
            }
            NetSpeedLiveData.Companion.get().setValue(Long.valueOf(longValue));
            if (trafficSpeedListener != null) {
                trafficSpeedListener.onSpeed(longValue);
            }
        }
        speedList.clear();
        Disposable disposable = testDisposable;
        if (disposable == null) {
            return;
        }
        disposable.dispose();
    }
}
