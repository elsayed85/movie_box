package com.movieboxpro.android.app;

import android.app.Activity;
import com.movieboxpro.android.utils.MLog;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class AppManager {
    private static final String TAG = "AppManager";
    public static Map<String, WeakReference<Activity>> map = new ConcurrentHashMap();

    public static boolean contains(Class<? extends Activity> cls) {
        if (cls != null) {
            return map.containsKey(cls.getSimpleName());
        }
        return false;
    }

    public static void add(Activity activity) {
        if (activity != null) {
            String simpleName = activity.getClass().getSimpleName();
            if (map.containsKey(simpleName)) {
                return;
            }
            MLog.d(TAG, "add activity:" + simpleName);
            map.put(simpleName, new WeakReference<>(activity));
        }
    }

    public static void remove(Activity activity) {
        if (activity != null) {
            String simpleName = activity.getClass().getSimpleName();
            if (map.containsKey(simpleName)) {
                MLog.d(TAG, "remove activity:" + simpleName);
                map.remove(simpleName);
            }
        }
    }

    public static void finish(Class<? extends Activity> cls) {
        if (cls == null) {
            return;
        }
        for (Map.Entry<String, WeakReference<Activity>> entry : map.entrySet()) {
            Activity activity = entry.getValue().get();
            if (activity != null && activity.getClass() == cls) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
                return;
            }
        }
    }

    @SafeVarargs
    public static void finish(Class<? extends Activity>... clsArr) {
        if (clsArr == null || clsArr.length == 0) {
            return;
        }
        for (Map.Entry<String, WeakReference<Activity>> entry : map.entrySet()) {
            Activity activity = entry.getValue().get();
            if (activity != null) {
                Class<?> cls = activity.getClass();
                int length = clsArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (cls == clsArr[i]) {
                        MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                        activity.finish();
                        remove(activity);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public static void finishAllExcept(Class<? extends Activity> cls) {
        for (Map.Entry<String, WeakReference<Activity>> entry : map.entrySet()) {
            Activity activity = entry.getValue().get();
            if (activity != null && activity.getClass() != cls) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
            }
        }
    }

    public static void finishAll() {
        for (Map.Entry<String, WeakReference<Activity>> entry : map.entrySet()) {
            Activity activity = entry.getValue().get();
            if (activity != null) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
            }
        }
    }
}
