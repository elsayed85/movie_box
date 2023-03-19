package com.movieboxpro.android.utils.tool;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import com.movieboxpro.android.utils.tool.ThreadUtils;
/* loaded from: classes3.dex */
public final class Utils {
    private static Application sApp;

    /* loaded from: classes3.dex */
    public static class ActivityLifecycleCallbacks {
        public void onActivityCreated(Activity activity) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onLifecycleChanged(Activity activity, Lifecycle.Event event) {
        }
    }

    /* loaded from: classes3.dex */
    public interface Consumer<T> {
        void accept(T t);
    }

    /* loaded from: classes3.dex */
    public interface Func1<Ret, Par> {
        Ret call(Par par);
    }

    /* loaded from: classes3.dex */
    public interface OnAppStatusChangedListener {
        void onBackground(Activity activity);

        void onForeground(Activity activity);
    }

    /* loaded from: classes3.dex */
    public interface Supplier<T> {
        T get();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = sApp;
        if (application2 == null) {
            sApp = application;
            UtilsBridge.init(application);
            UtilsBridge.preLoad();
        } else if (application2.equals(application)) {
        } else {
            UtilsBridge.unInit(sApp);
            sApp = application;
            UtilsBridge.init(application);
        }
    }

    public static Application getApp() {
        Application application = sApp;
        if (application != null) {
            return application;
        }
        init(UtilsBridge.getApplicationByReflect());
        Application application2 = sApp;
        if (application2 != null) {
            return application2;
        }
        throw new NullPointerException("reflect failed.");
    }

    /* loaded from: classes3.dex */
    public static abstract class Task<Result> extends ThreadUtils.SimpleTask<Result> {
        private Consumer<Result> mConsumer;

        public Task(Consumer<Result> consumer) {
            this.mConsumer = consumer;
        }

        @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
        public void onSuccess(Result result) {
            Consumer<Result> consumer = this.mConsumer;
            if (consumer != null) {
                consumer.accept(result);
            }
        }
    }
}
