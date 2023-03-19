package com.movieboxpro.android.view.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
/* loaded from: classes3.dex */
public abstract class CustomClickableSpan extends ClickableSpan {
    private int colorInt;
    private boolean underLine;

    public CustomClickableSpan(int i) {
        this.underLine = false;
        this.colorInt = i;
    }

    public CustomClickableSpan(int i, boolean z) {
        this.underLine = false;
        this.underLine = z;
        this.colorInt = i;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.colorInt);
        textPaint.setUnderlineText(this.underLine);
    }
}
