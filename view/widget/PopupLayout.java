package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.material.badge.BadgeDrawable;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.LogUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jetty.http.HttpHeaderValues;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.AndroidUtil;
/* compiled from: PopupLayout.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bH\u0002J\u0010\u0010'\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001bH\u0016J\u0010\u0010+\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001bH\u0016J\u0010\u0010,\u001a\u00020#2\u0006\u0010*\u001a\u00020\u001bH\u0016J\u0018\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016J\u000e\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u000eJ\u0016\u00104\u001a\u00020#2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bJ\u000e\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020\u001fJ\u0016\u00107\u001a\u00020#2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bJ\b\u00108\u001a\u00020#H\u0002R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/movieboxpro/android/view/widget/PopupLayout;", "Landroid/widget/RelativeLayout;", "Landroid/view/ScaleGestureDetector$OnScaleGestureListener;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gestureDetector", "Landroidx/core/view/GestureDetectorCompat;", "initialTouchX", "", "initialTouchY", "initialX", "initialY", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "popupHeight", "popupWidth", "scaleFactor", "", "scaleGestureDetector", "Landroid/view/ScaleGestureDetector;", "screenHeight", "screenWidth", "vlcVout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "windowManager", "Landroid/view/WindowManager;", HttpHeaderValues.CLOSE, "", "containInScreen", "width", "height", "init", "onScale", "", "detector", "onScaleBegin", "onScaleEnd", "onTouch", "v", "Landroid/view/View;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "setGestureDetector", "gdc", "setInitViewSize", "setVLCVOut", "vout", "setViewSize", "updateWindowSize", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PopupLayout extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VLC/PopupView";
    public Map<Integer, View> _$_findViewCache;
    private GestureDetectorCompat gestureDetector;
    private float initialTouchX;
    private float initialTouchY;
    private int initialX;
    private int initialY;
    private WindowManager.LayoutParams mLayoutParams;
    private int popupHeight;
    private int popupWidth;
    private double scaleFactor;
    private ScaleGestureDetector scaleGestureDetector;
    private int screenHeight;
    private int screenWidth;
    private IVLCVout vlcVout;
    private WindowManager windowManager;

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

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Intrinsics.checkNotNullParameter(detector, "detector");
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.scaleFactor = 1.0d;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.scaleFactor = 1.0d;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.scaleFactor = 1.0d;
        init(context);
    }

    public final void setVLCVOut(IVLCVout vout) {
        Intrinsics.checkNotNullParameter(vout, "vout");
        this.vlcVout = vout;
        Intrinsics.checkNotNull(vout);
        vout.setWindowSize(this.popupWidth, this.popupHeight);
    }

    public final void close() {
        setKeepScreenOn(false);
        WindowManager windowManager = this.windowManager;
        Intrinsics.checkNotNull(windowManager);
        windowManager.removeView(this);
        this.windowManager = null;
        this.vlcVout = null;
    }

    public final void setGestureDetector(GestureDetectorCompat gdc) {
        Intrinsics.checkNotNullParameter(gdc, "gdc");
        this.gestureDetector = gdc;
    }

    public final void setViewSize(int i, int i2) {
        this.popupWidth = i;
        this.popupHeight = i2;
        if (i > ScreenUtils.getScreenWidth()) {
            int screenWidth = ScreenUtils.getScreenWidth();
            this.popupWidth = screenWidth;
            this.popupHeight = (screenWidth * i2) / i;
        }
        int i3 = this.screenWidth;
        if (i > i3) {
            i2 = (i2 * i3) / i;
            i = i3;
        }
        int i4 = this.screenHeight;
        if (i2 > i4) {
            i = (i * i4) / i2;
            i2 = i4;
        }
        containInScreen(i, i2);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        WindowManager.LayoutParams layoutParams2 = null;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams = null;
        }
        layoutParams.width = i;
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        if (layoutParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams3 = null;
        }
        layoutParams3.height = i2;
        WindowManager windowManager = this.windowManager;
        Intrinsics.checkNotNull(windowManager);
        PopupLayout popupLayout = this;
        WindowManager.LayoutParams layoutParams4 = this.mLayoutParams;
        if (layoutParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
        } else {
            layoutParams2 = layoutParams4;
        }
        windowManager.updateViewLayout(popupLayout, layoutParams2);
        IVLCVout iVLCVout = this.vlcVout;
        if (iVLCVout != null) {
            Intrinsics.checkNotNull(iVLCVout);
            iVLCVout.setWindowSize(this.popupWidth, this.popupHeight);
        }
    }

    public final void setInitViewSize(int i, int i2) {
        int i3 = this.popupWidth;
        this.popupHeight = (i3 * i2) / i;
        if (i3 > ScreenUtils.getScreenWidth()) {
            int screenWidth = ScreenUtils.getScreenWidth();
            this.popupWidth = screenWidth;
            this.popupHeight = (screenWidth * i2) / i;
        }
        int i4 = this.popupWidth;
        int i5 = this.popupHeight;
        int i6 = this.screenWidth;
        if (i4 > i6) {
            i5 = (i5 * i6) / i4;
            i4 = i6;
        }
        int i7 = this.screenHeight;
        if (i5 > i7) {
            i4 = (i4 * i7) / i5;
            i5 = i7;
        }
        containInScreen(i4, i5);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        WindowManager.LayoutParams layoutParams2 = null;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams = null;
        }
        layoutParams.width = i4;
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        if (layoutParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams3 = null;
        }
        layoutParams3.height = i5;
        WindowManager windowManager = this.windowManager;
        if (windowManager != null) {
            Intrinsics.checkNotNull(windowManager);
            PopupLayout popupLayout = this;
            WindowManager.LayoutParams layoutParams4 = this.mLayoutParams;
            if (layoutParams4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            } else {
                layoutParams2 = layoutParams4;
            }
            windowManager.updateViewLayout(popupLayout, layoutParams2);
        }
        IVLCVout iVLCVout = this.vlcVout;
        if (iVLCVout != null) {
            Intrinsics.checkNotNull(iVLCVout);
            iVLCVout.setWindowSize(this.popupWidth, this.popupHeight);
        }
    }

    private final void init(Context context) {
        Object systemService = context.getApplicationContext().getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.windowManager = (WindowManager) systemService;
        this.popupWidth = context.getResources().getDimensionPixelSize(R.dimen.pop_player_width);
        this.popupHeight = context.getResources().getDimensionPixelSize(R.dimen.pop_player_height);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(this.popupWidth, this.popupHeight, AndroidUtil.isOOrLater ? 2038 : 2002, 8, -1);
        layoutParams.gravity = BadgeDrawable.BOTTOM_START;
        layoutParams.x = 50;
        layoutParams.y = 50;
        this.scaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        WindowManager windowManager = this.windowManager;
        Intrinsics.checkNotNull(windowManager);
        windowManager.addView(this, layoutParams);
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        if (layoutParams2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager.LayoutParams");
        }
        this.mLayoutParams = (WindowManager.LayoutParams) layoutParams2;
        updateWindowSize();
    }

    private final void updateWindowSize() {
        Point point = new Point();
        WindowManager windowManager = this.windowManager;
        Intrinsics.checkNotNull(windowManager);
        windowManager.getDefaultDisplay().getSize(point);
        this.screenWidth = point.x;
        this.screenHeight = point.y;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
        if (r7.isInProgress() == false) goto L22;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r7 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r7)
            android.view.WindowManager r7 = r6.windowManager
            r0 = 0
            if (r7 != 0) goto L10
            return r0
        L10:
            android.view.ScaleGestureDetector r7 = r6.scaleGestureDetector
            if (r7 == 0) goto L1a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r7.onTouchEvent(r8)
        L1a:
            androidx.core.view.GestureDetectorCompat r7 = r6.gestureDetector
            r1 = 1
            if (r7 == 0) goto L29
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            boolean r7 = r7.onTouchEvent(r8)
            if (r7 == 0) goto L29
            return r1
        L29:
            int r7 = r8.getAction()
            r2 = 0
            java.lang.String r3 = "mLayoutParams"
            if (r7 == 0) goto L9f
            if (r7 == r1) goto L9e
            r4 = 2
            if (r7 == r4) goto L38
            goto L46
        L38:
            android.view.ScaleGestureDetector r7 = r6.scaleGestureDetector
            if (r7 == 0) goto L47
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            boolean r7 = r7.isInProgress()
            if (r7 != 0) goto L46
            goto L47
        L46:
            return r0
        L47:
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L4f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L4f:
            int r0 = r6.initialX
            float r4 = r8.getRawX()
            float r5 = r6.initialTouchX
            float r4 = r4 - r5
            int r4 = (int) r4
            int r0 = r0 + r4
            r7.x = r0
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L64
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L64:
            int r0 = r6.initialY
            float r8 = r8.getRawY()
            float r4 = r6.initialTouchY
            float r8 = r8 - r4
            int r8 = (int) r8
            int r0 = r0 - r8
            r7.y = r0
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L79
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L79:
            int r7 = r7.width
            android.view.WindowManager$LayoutParams r8 = r6.mLayoutParams
            if (r8 != 0) goto L83
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r2
        L83:
            int r8 = r8.height
            r6.containInScreen(r7, r8)
            android.view.WindowManager r7 = r6.windowManager
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r8 = r6
            android.view.View r8 = (android.view.View) r8
            android.view.WindowManager$LayoutParams r0 = r6.mLayoutParams
            if (r0 != 0) goto L98
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L99
        L98:
            r2 = r0
        L99:
            android.view.ViewGroup$LayoutParams r2 = (android.view.ViewGroup.LayoutParams) r2
            r7.updateViewLayout(r8, r2)
        L9e:
            return r1
        L9f:
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto La7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        La7:
            int r7 = r7.x
            r6.initialX = r7
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto Lb3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto Lb4
        Lb3:
            r2 = r7
        Lb4:
            int r7 = r2.y
            r6.initialY = r7
            float r7 = r8.getRawX()
            r6.initialTouchX = r7
            float r7 = r8.getRawY()
            r6.initialTouchY = r7
            r6.updateWindowSize()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.widget.PopupLayout.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector detector) {
        Intrinsics.checkNotNullParameter(detector, "detector");
        double d = this.scaleFactor;
        double scaleFactor = detector.getScaleFactor();
        Double.isNaN(scaleFactor);
        this.scaleFactor = d * scaleFactor;
        LogUtils.INSTANCE.logD("PopupLayout", "scaleFactor" + this.scaleFactor + " scaleFactor2:" + detector.getScaleFactor());
        this.scaleFactor = Math.max(0.3d, Math.min(this.scaleFactor, 5.0d));
        double width = (double) getWidth();
        double d2 = this.scaleFactor;
        Double.isNaN(width);
        this.popupWidth = (int) (width * d2);
        double height = getHeight();
        double d3 = this.scaleFactor;
        Double.isNaN(height);
        this.popupHeight = (int) (height * d3);
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector detector) {
        Intrinsics.checkNotNullParameter(detector, "detector");
        setViewSize(this.popupWidth, this.popupHeight);
        this.scaleFactor = 1.0d;
    }

    private final void containInScreen(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        WindowManager.LayoutParams layoutParams2 = null;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams = null;
        }
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        if (layoutParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams3 = null;
        }
        layoutParams.x = Math.max(layoutParams3.x, 0);
        WindowManager.LayoutParams layoutParams4 = this.mLayoutParams;
        if (layoutParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams4 = null;
        }
        WindowManager.LayoutParams layoutParams5 = this.mLayoutParams;
        if (layoutParams5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams5 = null;
        }
        layoutParams4.y = Math.max(layoutParams5.y, 0);
        WindowManager.LayoutParams layoutParams6 = this.mLayoutParams;
        if (layoutParams6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams6 = null;
        }
        if (layoutParams6.x + i > this.screenWidth) {
            WindowManager.LayoutParams layoutParams7 = this.mLayoutParams;
            if (layoutParams7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
                layoutParams7 = null;
            }
            layoutParams7.x = this.screenWidth - i;
        }
        WindowManager.LayoutParams layoutParams8 = this.mLayoutParams;
        if (layoutParams8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams8 = null;
        }
        if (layoutParams8.y + i2 > this.screenHeight) {
            WindowManager.LayoutParams layoutParams9 = this.mLayoutParams;
            if (layoutParams9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            } else {
                layoutParams2 = layoutParams9;
            }
            layoutParams2.y = this.screenHeight - i2;
        }
    }

    /* compiled from: PopupLayout.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/widget/PopupLayout$Companion;", "", "()V", "TAG", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
