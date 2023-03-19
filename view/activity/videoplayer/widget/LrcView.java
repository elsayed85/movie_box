package com.movieboxpro.android.view.activity.videoplayer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.videocontroller.R;
import com.movieboxpro.android.view.activity.videoplayer.controller.MediaPlayerControl;
import java.util.List;
/* loaded from: classes3.dex */
public class LrcView extends View {
    public static final int KARAOKE = 1;
    private int currentPosition;
    private Paint gPaint;
    private Paint hPaint;
    private int height;
    private int highLineColor;
    private int lastPosition;
    private List<SrtPraseModel> list;
    private int lrcColor;
    private int mode;
    private MediaPlayerControl player;
    private int width;

    public void setHighLineColor(int i) {
        this.highLineColor = i;
    }

    public void setLrcColor(int i) {
        this.lrcColor = i;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setPlayer(MediaPlayerControl mediaPlayerControl) {
        this.player = mediaPlayerControl;
    }

    public void setLrc(List<SrtPraseModel> list) {
        this.list = list;
    }

    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LrcView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.width = 0;
        this.height = 0;
        this.currentPosition = 0;
        this.lastPosition = 0;
        this.mode = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LrcView);
        this.highLineColor = obtainStyledAttributes.getColor(0, getResources().getColor(com.movieboxpro.android.R.color.text_yellow));
        this.lrcColor = obtainStyledAttributes.getColor(2, getResources().getColor(17170432));
        this.mode = obtainStyledAttributes.getInt(6, this.mode);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.gPaint = paint;
        paint.setAntiAlias(true);
        this.gPaint.setColor(this.lrcColor);
        this.gPaint.setTextSize(36.0f);
        this.gPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint2 = new Paint();
        this.hPaint = paint2;
        paint2.setAntiAlias(true);
        this.hPaint.setColor(this.highLineColor);
        this.hPaint.setTextSize(36.0f);
        this.hPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        if (this.width == 0 || this.height == 0) {
            this.width = getMeasuredWidth();
            this.height = getMeasuredHeight();
        }
        List<SrtPraseModel> list = this.list;
        if (list == null || list.size() == 0) {
            canvas.drawText("暂无歌词", this.width / 2, this.height / 2, this.gPaint);
            return;
        }
        getCurrentPosition();
        int currentPosition = (int) this.player.getCurrentPosition();
        drawLrc2(canvas, currentPosition);
        long beginTime = currentPosition - this.list.get(this.currentPosition).getBeginTime();
        if (beginTime > 500) {
            f = this.currentPosition * 80;
        } else {
            int i = this.lastPosition;
            f = ((this.currentPosition - i) * 80 * (((float) beginTime) / 500.0f)) + (i * 80);
        }
        setScrollY((int) f);
        int scrollY = getScrollY();
        int i2 = this.currentPosition;
        if (scrollY == i2 * 80) {
            this.lastPosition = i2;
        }
        postInvalidateDelayed(100L);
    }

    private void drawLrc2(Canvas canvas, int i) {
        SrtPraseModel srtPraseModel;
        int i2 = 0;
        if (this.mode == 0) {
            while (i2 < this.list.size()) {
                if (i2 == this.currentPosition) {
                    canvas.drawText(this.list.get(i2).getSrtBody(), this.width / 2, (this.height / 2) + (i2 * 80), this.hPaint);
                } else {
                    canvas.drawText(this.list.get(i2).getSrtBody(), this.width / 2, (this.height / 2) + (i2 * 80), this.gPaint);
                }
                i2++;
            }
            return;
        }
        while (i2 < this.list.size()) {
            canvas.drawText(this.list.get(i2).getSrtBody(), this.width / 2, (this.height / 2) + (i2 * 80), this.gPaint);
            i2++;
        }
        String srtBody = this.list.get(this.currentPosition).getSrtBody();
        int measureText = (int) this.gPaint.measureText(srtBody);
        int i3 = (this.width - measureText) / 2;
        long beginTime = this.list.get(this.currentPosition).getBeginTime();
        int endTime = (int) (((((float) (i - beginTime)) * 1.0f) / ((float) (srtPraseModel.getEndTime() - beginTime))) * measureText);
        if (endTime > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(endTime, 80, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawText(srtBody, measureText / 2, 80.0f, this.hPaint);
            canvas.drawBitmap(createBitmap, i3, (this.height / 2) + ((this.currentPosition - 1) * 80), (Paint) null);
        }
    }

    public void init() {
        this.currentPosition = 0;
        this.lastPosition = 0;
        setScrollY(0);
        invalidate();
    }

    private void drawLrc1(Canvas canvas) {
        canvas.drawText(this.list.get(this.currentPosition).getSrtBody(), this.width / 2, this.height / 2, this.hPaint);
        for (int i = 1; i < 10; i++) {
            int i2 = this.currentPosition - i;
            if (i2 > -1) {
                canvas.drawText(this.list.get(i2).getSrtBody(), this.width / 2, (this.height / 2) - (i * 80), this.gPaint);
            }
        }
        for (int i3 = 1; i3 < 10; i3++) {
            int i4 = this.currentPosition + i3;
            if (i4 < this.list.size()) {
                canvas.drawText(this.list.get(i4).getSrtBody(), this.width / 2, (this.height / 2) + (i3 * 80), this.gPaint);
            }
        }
    }

    private void getCurrentPosition() {
        try {
            int currentPosition = (int) this.player.getCurrentPosition();
            if (currentPosition < this.list.get(0).getBeginTime()) {
                this.currentPosition = 0;
            } else if (currentPosition > this.list.get(this.list.size() - 1).getBeginTime()) {
                this.currentPosition = this.list.size() - 1;
            } else {
                for (int i = 0; i < this.list.size(); i++) {
                    if (currentPosition >= this.list.get(i).getBeginTime() && currentPosition < this.list.get(i).getEndTime()) {
                        this.currentPosition = i;
                        return;
                    }
                }
            }
        } catch (Exception unused) {
            postInvalidateDelayed(100L);
        }
    }
}
