package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class RadioView extends View {
    private int mDefaultIndicatorHeight;
    private int mDefaultIndicatorWidth;
    private int mDefaultTempHeight;
    private int mDefaultTextSize;
    Paint mPaint;
    Paint paint;
    Path path;
    private RectF rectProgressBg;
    private LinearGradient shader;
    Paint textPaint;
    private int textSpace;

    public RadioView(Context context) {
        super(context);
        this.mDefaultIndicatorWidth = dipToPx(10);
        this.mDefaultIndicatorHeight = dipToPx(8);
        this.mDefaultTempHeight = dipToPx(20);
        this.mDefaultTextSize = 30;
        this.textSpace = dipToPx(5);
        init();
    }

    public RadioView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDefaultIndicatorWidth = dipToPx(10);
        this.mDefaultIndicatorHeight = dipToPx(8);
        this.mDefaultTempHeight = dipToPx(20);
        this.mDefaultTextSize = 30;
        this.textSpace = dipToPx(5);
        init();
    }

    public RadioView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultIndicatorWidth = dipToPx(10);
        this.mDefaultIndicatorHeight = dipToPx(8);
        this.mDefaultTempHeight = dipToPx(20);
        this.mDefaultTextSize = 30;
        this.textSpace = dipToPx(5);
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setTextAlign(Paint.Align.LEFT);
        this.textPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        this.textPaint.setTextSize(80.0f);
        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.textPaint.setFlags(1);
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        paint2.setAntiAlias(true);
        this.path = new Path();
        Paint paint3 = new Paint();
        this.paint = paint3;
        paint3.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setShader(this.shader);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            size = 0;
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            size2 = this.mDefaultTextSize + this.mDefaultTempHeight + this.mDefaultIndicatorHeight + this.textSpace;
        }
        setMeasuredDimension(size, size2);
    }

    private int dipToPx(int i) {
        return (int) ((i * getContext().getResources().getDisplayMetrics().density) + ((i >= 0 ? 1 : -1) * 0.5f));
    }
}
