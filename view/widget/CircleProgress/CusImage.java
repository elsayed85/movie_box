package com.movieboxpro.android.view.widget.CircleProgress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
/* loaded from: classes3.dex */
public class CusImage extends View {
    private int flag;
    private Circlelayout m;
    private Paint myFramePaint;
    private Paint myPaint;
    int pix;
    RectF rect;
    private float startAngle;
    float sweepAngle;
    public float temp;
    public TextView value;

    public CusImage(Context context, AttributeSet attributeSet, Circlelayout circlelayout) {
        super(context, attributeSet);
        this.flag = 0;
        this.pix = 0;
        this.m = circlelayout;
        init();
    }

    public CusImage(Context context, Circlelayout circlelayout) {
        super(context);
        this.flag = 0;
        this.pix = 0;
        this.m = circlelayout;
        init();
    }

    private void init() {
        this.myPaint = new Paint();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Double.isNaN(r0);
        this.pix = (int) Math.sqrt(r0 * 0.0217d);
        this.myPaint.setAntiAlias(true);
        this.myPaint.setStyle(Paint.Style.STROKE);
        this.myPaint.setColor(Color.rgb(0, (int) Opcodes.IF_ICMPLT, 234));
        this.myPaint.setStrokeWidth(7.0f);
        Paint paint = new Paint();
        this.myFramePaint = paint;
        paint.setAntiAlias(true);
        this.myFramePaint.setColor(0);
        int i = this.pix;
        double d = i;
        Double.isNaN(d);
        double d2 = i;
        Double.isNaN(d2);
        float f = (float) (d2 * 0.95d);
        double d3 = i;
        Double.isNaN(d3);
        double d4 = i;
        Double.isNaN(d4);
        this.rect = new RectF((float) (d * 0.05d), (float) (d3 * 0.05d), f, (float) (d4 * 0.95d));
    }

    public void setupprogress(int i) {
        double d = i;
        Double.isNaN(d);
        this.sweepAngle = (float) (d * 3.6d);
    }

    public void reset() {
        this.sweepAngle = 0.0f;
        this.startAngle = -90.0f;
        this.flag = 1;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.pix;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
        }
        if (mode2 == 1073741824) {
            i3 = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            i3 = Math.min(i3, size2);
        }
        setMeasuredDimension(size, i3);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(this.rect, this.startAngle, this.sweepAngle, false, this.myPaint);
        this.startAngle = -90.0f;
        if (this.sweepAngle < 360.0f && this.flag == 0) {
            invalidate();
        } else if (this.flag == 1) {
            this.sweepAngle = 0.0f;
            this.startAngle = -90.0f;
            this.flag = 0;
            invalidate();
        } else {
            this.sweepAngle = 0.0f;
            this.startAngle = -90.0f;
            this.m.finalAnimation();
        }
    }
}
