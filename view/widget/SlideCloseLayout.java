package com.movieboxpro.android.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SlideCloseLayout.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/widget/SlideCloseLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "previousX", "", "previousY", "scrollListener", "Lcom/movieboxpro/android/view/widget/LayoutScrollListener;", "layoutExitAnim", "", "layoutRecoverAnim", "onInterceptTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "onTouchEvent", "setLayoutScrollListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SlideCloseLayout extends FrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private float previousX;
    private float previousY;
    private LayoutScrollListener scrollListener;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlideCloseLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        Drawable background = getBackground();
        if (background == null) {
            return;
        }
        background.setAlpha(255);
    }

    public /* synthetic */ SlideCloseLayout(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getPointerCount() <= 1) {
            float rawY = motionEvent.getRawY();
            float rawX = motionEvent.getRawX();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.previousX = rawX;
                this.previousY = rawY;
            } else if (action == 2) {
                if (Math.abs(rawX - this.previousX) + 50 < Math.abs(rawY - this.previousY)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null) {
            float rawY = motionEvent.getRawY();
            float rawX = motionEvent.getRawX();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.previousX = rawX;
                this.previousY = rawY;
            } else if (action != 1) {
                if (action == 2) {
                    setTranslationY(rawY - this.previousY);
                    setAlpha(1.0f - (Math.abs(rawY - this.previousY) / getHeight()));
                } else {
                    return super.onTouchEvent(motionEvent);
                }
            } else if (Math.abs(getTranslationY()) > getHeight() / 4) {
                layoutExitAnim();
            } else {
                layoutRecoverAnim();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setLayoutScrollListener(LayoutScrollListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.scrollListener = listener;
    }

    private final void layoutRecoverAnim() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationY", getTranslationY(), 0.0f);
        ofFloat.setDuration(100L);
        ofFloat.start();
        setAlpha(1.0f);
    }

    private final void layoutExitAnim() {
        ObjectAnimator ofFloat;
        if (getTranslationY() >= 0.0f) {
            ofFloat = ObjectAnimator.ofFloat(this, "translationY", getTranslationY(), getHeight());
            Intrinsics.checkNotNullExpressionValue(ofFloat, "{\n            ObjectAnim…ight.toFloat())\n        }");
        } else {
            ofFloat = ObjectAnimator.ofFloat(this, "translationY", getTranslationY(), -getHeight());
            Intrinsics.checkNotNullExpressionValue(ofFloat, "{\n            ObjectAnim…ight.toFloat())\n        }");
        }
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.movieboxpro.android.view.widget.SlideCloseLayout$layoutExitAnim$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LayoutScrollListener layoutScrollListener;
                SlideCloseLayout.this.setAlpha(0.0f);
                layoutScrollListener = SlideCloseLayout.this.scrollListener;
                if (layoutScrollListener == null) {
                    return;
                }
                layoutScrollListener.onLayoutClosed();
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$SlideCloseLayout$bqsSIDCO3BuEG95we58mOZJSPmc
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SlideCloseLayout.m1431layoutExitAnim$lambda2(SlideCloseLayout.this, valueAnimator);
            }
        });
        ofFloat.setDuration(200L);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: layoutExitAnim$lambda-2  reason: not valid java name */
    public static final void m1431layoutExitAnim$lambda2(SlideCloseLayout this$0, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setAlpha(1 - (this$0.getTranslationY() / this$0.getHeight()));
    }
}
