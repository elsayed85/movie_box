package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CustomProgressView.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007H\u0014J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010!\u001a\u00020\u001a2\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/view/widget/CustomProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bgPaint", "Landroid/graphics/Paint;", "bgRectF", "Landroid/graphics/RectF;", "complete", "", "favoriteEpisodes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "linePaint", "lineRectF", "lineWidth", "", "perWidth", "viewHeight", "viewWidth", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setComplete", "setEpisodes", "episodes", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomProgressView extends View {
    public Map<Integer, View> _$_findViewCache;
    private Paint bgPaint;
    private RectF bgRectF;
    private boolean complete;
    private ArrayList<Integer> favoriteEpisodes;
    private Paint linePaint;
    private RectF lineRectF;
    private float lineWidth;
    private float perWidth;
    private int viewHeight;
    private int viewWidth;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

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

    public /* synthetic */ CustomProgressView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.favoriteEpisodes = new ArrayList<>();
        this.lineWidth = CommonExtKt.dp2Px(4);
        this.bgPaint = new Paint();
        this.linePaint = new Paint();
        this.bgPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.bgPaint.setAntiAlias(true);
        this.bgPaint.setDither(true);
        this.bgPaint.setStrokeWidth(this.lineWidth);
        this.bgPaint.setStyle(Paint.Style.FILL);
        this.bgPaint.setColor(Color.parseColor("#21FFFFFF"));
        this.linePaint.setStrokeCap(Paint.Cap.SQUARE);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setDither(true);
        this.linePaint.setStrokeWidth(this.lineWidth);
        this.linePaint.setColor(CommonExtKt.colorInt(this, (int) R.color.white_30alpha));
    }

    public final void setEpisodes(ArrayList<Integer> episodes) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        this.favoriteEpisodes.clear();
        this.favoriteEpisodes.addAll(episodes);
        this.perWidth = this.viewWidth / episodes.size();
        invalidate();
    }

    public final void setComplete(boolean z) {
        this.complete = z;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size == 0 || size2 == 0) {
            return;
        }
        this.viewWidth = size;
        this.viewHeight = size2;
        if (!this.favoriteEpisodes.isEmpty()) {
            this.perWidth = this.viewWidth / this.favoriteEpisodes.size();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= 21) {
            float f = this.lineWidth;
            canvas.drawRoundRect(0.0f, f, this.viewWidth, f * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.bgPaint);
        } else {
            RectF rectF = this.bgRectF;
            if (rectF == null) {
                float f2 = this.lineWidth;
                this.bgRectF = new RectF(0.0f, f2, this.viewWidth, 2 * f2);
            } else {
                Intrinsics.checkNotNull(rectF);
                float f3 = this.lineWidth;
                rectF.set(0.0f, f3, this.viewWidth, 2 * f3);
            }
            RectF rectF2 = this.bgRectF;
            Intrinsics.checkNotNull(rectF2);
            canvas.drawRoundRect(rectF2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.bgPaint);
        }
        if (this.complete) {
            RectF rectF3 = this.lineRectF;
            if (rectF3 == null) {
                float f4 = this.lineWidth;
                this.lineRectF = new RectF(0.0f, f4, this.viewWidth, 2 * f4);
            } else {
                Intrinsics.checkNotNull(rectF3);
                float f5 = this.lineWidth;
                rectF3.set(0.0f, f5, this.viewWidth, 2 * f5);
            }
            RectF rectF4 = this.lineRectF;
            Intrinsics.checkNotNull(rectF4);
            canvas.drawRoundRect(rectF4, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
            return;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Object obj : this.favoriteEpisodes) {
            int i4 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((Number) obj).intValue() == 1) {
                int i5 = i2 + 1;
                if (i == this.favoriteEpisodes.size() - 1) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        float f6 = this.perWidth;
                        float f7 = this.lineWidth;
                        canvas.drawRoundRect(i3 * f6, f7, (i3 + i5) * f6, f7 * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                    } else {
                        RectF rectF5 = this.lineRectF;
                        if (rectF5 == null) {
                            float f8 = this.perWidth;
                            float f9 = this.lineWidth;
                            this.lineRectF = new RectF(i3 * f8, f9, (i3 + i5) * f8, 2 * f9);
                        } else {
                            Intrinsics.checkNotNull(rectF5);
                            float f10 = this.perWidth;
                            float f11 = this.lineWidth;
                            rectF5.set(i3 * f10, f11, (i3 + i5) * f10, 2 * f11);
                        }
                        RectF rectF6 = this.lineRectF;
                        Intrinsics.checkNotNull(rectF6);
                        canvas.drawRoundRect(rectF6, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                    }
                }
                i2 = i5;
            } else {
                if (i2 != 0) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        float f12 = this.perWidth;
                        float f13 = this.lineWidth;
                        canvas.drawRoundRect(i3 * f12, f13, (i3 + i2) * f12, f13 * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                    } else {
                        RectF rectF7 = this.lineRectF;
                        if (rectF7 == null) {
                            float f14 = this.perWidth;
                            float f15 = this.lineWidth;
                            this.lineRectF = new RectF(i3 * f14, f15, (i3 + i2) * f14, 2 * f15);
                        } else {
                            Intrinsics.checkNotNull(rectF7);
                            float f16 = this.perWidth;
                            float f17 = this.lineWidth;
                            rectF7.set(i3 * f16, f17, (i3 + i2) * f16, 2 * f17);
                        }
                        RectF rectF8 = this.lineRectF;
                        Intrinsics.checkNotNull(rectF8);
                        canvas.drawRoundRect(rectF8, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                    }
                    i2 = 0;
                }
                i3 = i4;
            }
            i = i4;
        }
    }
}
