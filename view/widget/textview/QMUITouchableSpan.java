package com.movieboxpro.android.view.widget.textview;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.view.ViewCompat;
/* loaded from: classes3.dex */
public abstract class QMUITouchableSpan extends ClickableSpan implements ITouchableSpan {
    private static final String TAG = "QMUITouchableSpan";
    private boolean mIsNeedUnderline = false;
    private boolean mIsPressed;
    private int mNormalBackgroundColor;
    private int mNormalBgAttr;
    private int mNormalTextColor;
    private int mNormalTextColorAttr;
    private int mPressedBackgroundColor;
    private int mPressedBgAttr;
    private int mPressedTextColor;
    private int mPressedTextColorAttr;

    public abstract void onSpanClick(View view);

    @Override // android.text.style.ClickableSpan, com.movieboxpro.android.view.widget.textview.ITouchableSpan
    public final void onClick(View view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            onSpanClick(view);
        }
    }

    public QMUITouchableSpan(int i, int i2, int i3, int i4) {
        this.mNormalTextColor = i;
        this.mPressedTextColor = i2;
        this.mNormalBackgroundColor = i3;
        this.mPressedBackgroundColor = i4;
    }

    public QMUITouchableSpan(View view, int i, int i2, int i3, int i4) {
        this.mNormalBgAttr = i3;
        this.mPressedBgAttr = i4;
        this.mNormalTextColorAttr = i;
        this.mPressedTextColorAttr = i2;
    }

    public int getNormalBackgroundColor() {
        return this.mNormalBackgroundColor;
    }

    public void setNormalTextColor(int i) {
        this.mNormalTextColor = i;
    }

    public void setPressedTextColor(int i) {
        this.mPressedTextColor = i;
    }

    public int getNormalTextColor() {
        return this.mNormalTextColor;
    }

    public int getPressedBackgroundColor() {
        return this.mPressedBackgroundColor;
    }

    public int getPressedTextColor() {
        return this.mPressedTextColor;
    }

    @Override // com.movieboxpro.android.view.widget.textview.ITouchableSpan
    public void setPressed(boolean z) {
        this.mIsPressed = z;
    }

    public boolean isPressed() {
        return this.mIsPressed;
    }

    public void setIsNeedUnderline(boolean z) {
        this.mIsNeedUnderline = z;
    }

    public boolean isNeedUnderline() {
        return this.mIsNeedUnderline;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.mIsPressed ? this.mPressedTextColor : this.mNormalTextColor);
        textPaint.bgColor = this.mIsPressed ? this.mPressedBackgroundColor : this.mNormalBackgroundColor;
        textPaint.setUnderlineText(this.mIsNeedUnderline);
    }
}
