package com.movieboxpro.android.view.widget.textview;

import android.content.Context;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatTextView;
/* loaded from: classes3.dex */
public class QMUISpanTouchFixTextView extends AppCompatTextView implements ISpanTouchFix {
    private boolean mIsPressedRecord;
    private boolean mNeedForceEventToParent;
    private boolean mTouchSpanHit;

    public QMUISpanTouchFixTextView(Context context) {
        this(context, null);
    }

    public QMUISpanTouchFixTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QMUISpanTouchFixTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsPressedRecord = false;
        this.mNeedForceEventToParent = false;
        setHighlightColor(0);
    }

    public void setNeedForceEventToParent(boolean z) {
        this.mNeedForceEventToParent = z;
        setFocusable(!z);
        setClickable(!z);
        setLongClickable(!z);
    }

    public void setMovementMethodDefault() {
        setMovementMethodCompat(QMUILinkTouchMovementMethod.getInstance());
    }

    public void setMovementMethodCompat(MovementMethod movementMethod) {
        setMovementMethod(movementMethod);
        if (this.mNeedForceEventToParent) {
            setNeedForceEventToParent(true);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(getText() instanceof Spannable)) {
            return super.onTouchEvent(motionEvent);
        }
        this.mTouchSpanHit = true;
        return this.mNeedForceEventToParent ? this.mTouchSpanHit : super.onTouchEvent(motionEvent);
    }

    @Override // com.movieboxpro.android.view.widget.textview.ISpanTouchFix
    public void setTouchSpanHit(boolean z) {
        if (this.mTouchSpanHit != z) {
            this.mTouchSpanHit = z;
            setPressed(this.mIsPressedRecord);
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.mTouchSpanHit || this.mNeedForceEventToParent) {
            return false;
        }
        return super.performClick();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean performLongClick() {
        if (this.mTouchSpanHit || this.mNeedForceEventToParent) {
            return false;
        }
        return super.performLongClick();
    }

    @Override // android.view.View
    public final void setPressed(boolean z) {
        this.mIsPressedRecord = z;
        if (this.mTouchSpanHit) {
            return;
        }
        onSetPressed(z);
    }

    protected void onSetPressed(boolean z) {
        super.setPressed(z);
    }
}
