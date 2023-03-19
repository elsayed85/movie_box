package com.movieboxpro.android.utils;

import android.view.View;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public class DoubleClickHelper {
    private static final int windowDuration = 1;

    public static void click(View view, Runnable runnable) {
        click(view, runnable, 1);
    }

    public static void click(View view, final Runnable runnable, int i) {
        if (view == null || runnable == null) {
            return;
        }
        Observable.create(new ClickObservable(view)).throttleFirst(i, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$DoubleClickHelper$ILkrK-W0_jJ2YYMpZS0moJpMt_8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                View view2 = (View) obj;
                runnable.run();
            }
        });
    }

    public static void click(View view, View.OnClickListener onClickListener) {
        click(view, onClickListener, 1);
    }

    public static void click(final View view, final View.OnClickListener onClickListener, int i) {
        if (view == null || onClickListener == null) {
            return;
        }
        Observable.create(new ClickObservable(view)).throttleFirst(i, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.movieboxpro.android.utils.-$$Lambda$DoubleClickHelper$SERH_1cYIrvGrVXZf5w6RZjfBSU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                View view2 = (View) obj;
                onClickListener.onClick(view);
            }
        });
    }

    private DoubleClickHelper() {
    }
}
