package com.movieboxpro.android.http;

import com.movieboxpro.android.utils.MLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import retrofit2.Call;
/* loaded from: classes.dex */
public class CallManager {
    private static final String TAG = "CallManager";
    private static List<WeakReference<Call>> list = new ArrayList();
    private static Map<String, List<WeakReference<Call>>> map = new ConcurrentHashMap();

    public static void add(Call call) {
        add(list, call);
    }

    public static void add(String str, Call call) {
        MLog.d(TAG, "add: " + str);
        List<WeakReference<Call>> list2 = map.get(str);
        if (list2 == null) {
            ArrayList arrayList = new ArrayList();
            add(arrayList, call);
            map.put(str, arrayList);
            return;
        }
        add(list2, call);
    }

    private static void add(List<WeakReference<Call>> list2, Call call) {
        Call call2;
        synchronized (TAG) {
            if (call != null) {
                boolean z = false;
                Iterator<WeakReference<Call>> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WeakReference<Call> next = it.next();
                    if (next != null && (call2 = next.get()) != null && call2 == call) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    list2.add(new WeakReference<>(call));
                }
            }
        }
    }

    public static void remove(Call call) {
        remove(list, call);
    }

    public static void remove(String str, Call call) {
        MLog.d(TAG, "remove: " + str);
        List<WeakReference<Call>> list2 = map.get(str);
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        remove(list2, call);
    }

    private static void remove(List<WeakReference<Call>> list2, Call call) {
        Call call2;
        synchronized (TAG) {
            if (call != null) {
                Iterator<WeakReference<Call>> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WeakReference<Call> next = it.next();
                    if (next != null && (call2 = next.get()) != null && call2 == call && !call.isCanceled()) {
                        call.cancel();
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    public static void cancelAll() {
        cancelAll(list);
    }

    public static void cancelAll(String str) {
        MLog.d(TAG, "cancelAll: " + str);
        List<WeakReference<Call>> list2 = map.get(str);
        if (list2 != null) {
            cancelAll(list2);
        }
    }

    private static void cancelAll(List<WeakReference<Call>> list2) {
        synchronized (TAG) {
            if (!list2.isEmpty()) {
                Iterator<WeakReference<Call>> it = list2.iterator();
                while (it.hasNext()) {
                    WeakReference<Call> next = it.next();
                    if (next != null) {
                        Call call = next.get();
                        if (call != null && !call.isCanceled()) {
                            call.cancel();
                        }
                        it.remove();
                    }
                }
            }
        }
    }
}
