package com.movieboxpro.android.app;

import com.movieboxpro.android.utils.MLog;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class RxManager {
    private static final String TAG = "RxManager";
    private static Map<String, WeakReference<CompositeDisposable>> map = new ConcurrentHashMap();

    public static void addDisposable(String str, Disposable disposable) {
        CompositeDisposable compositeDisposable;
        MLog.d(TAG, "addDisposable: " + str);
        if (map.containsKey(str)) {
            WeakReference<CompositeDisposable> weakReference = map.get(str);
            if (weakReference == null || (compositeDisposable = weakReference.get()) == null) {
                return;
            }
            compositeDisposable.add(disposable);
            return;
        }
        CompositeDisposable compositeDisposable2 = new CompositeDisposable();
        compositeDisposable2.add(disposable);
        map.put(str, new WeakReference<>(compositeDisposable2));
    }

    public static void remove(String str) {
        CompositeDisposable compositeDisposable;
        MLog.d(TAG, "remove: " + str);
        if (map.containsKey(str)) {
            WeakReference<CompositeDisposable> weakReference = map.get(str);
            if (weakReference != null && (compositeDisposable = weakReference.get()) != null) {
                compositeDisposable.clear();
            }
            map.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAll() {
        MLog.d(TAG, "removeAll");
        for (String str : map.keySet()) {
            MLog.d(TAG, "removeAll: key: " + str);
            remove(str);
        }
        map.clear();
    }
}
