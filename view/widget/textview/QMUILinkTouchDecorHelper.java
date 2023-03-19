package com.movieboxpro.android.view.widget.textview;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.view.MotionEvent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public class QMUILinkTouchDecorHelper {
    private WeakReference<ITouchableSpan> mPressedSpanRf;

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() == 0) {
            ITouchableSpan pressedSpan = getPressedSpan(textView, spannable, motionEvent);
            if (pressedSpan != null) {
                pressedSpan.setPressed(true);
                Selection.setSelection(spannable, spannable.getSpanStart(pressedSpan), spannable.getSpanEnd(pressedSpan));
                this.mPressedSpanRf = new WeakReference<>(pressedSpan);
            }
            if (textView instanceof ISpanTouchFix) {
                ((ISpanTouchFix) textView).setTouchSpanHit(pressedSpan != null);
            }
            return pressedSpan != null;
        }
        ITouchableSpan iTouchableSpan = null;
        if (motionEvent.getAction() == 2) {
            ITouchableSpan pressedSpan2 = getPressedSpan(textView, spannable, motionEvent);
            WeakReference<ITouchableSpan> weakReference = this.mPressedSpanRf;
            ITouchableSpan iTouchableSpan2 = weakReference != null ? weakReference.get() : null;
            if (iTouchableSpan2 == null || iTouchableSpan2 == pressedSpan2) {
                iTouchableSpan = iTouchableSpan2;
            } else {
                iTouchableSpan2.setPressed(false);
                this.mPressedSpanRf = null;
                Selection.removeSelection(spannable);
            }
            if (textView instanceof ISpanTouchFix) {
                ((ISpanTouchFix) textView).setTouchSpanHit(iTouchableSpan != null);
            }
            return iTouchableSpan != null;
        } else if (motionEvent.getAction() == 1) {
            WeakReference<ITouchableSpan> weakReference2 = this.mPressedSpanRf;
            ITouchableSpan iTouchableSpan3 = weakReference2 != null ? weakReference2.get() : null;
            if (iTouchableSpan3 != null) {
                iTouchableSpan3.setPressed(false);
                if (motionEvent.getAction() == 1) {
                    iTouchableSpan3.onClick(textView);
                }
            } else {
                z = false;
            }
            this.mPressedSpanRf = null;
            Selection.removeSelection(spannable);
            if (textView instanceof ISpanTouchFix) {
                ((ISpanTouchFix) textView).setTouchSpanHit(z);
            }
            return z;
        } else {
            WeakReference<ITouchableSpan> weakReference3 = this.mPressedSpanRf;
            ITouchableSpan iTouchableSpan4 = weakReference3 != null ? weakReference3.get() : null;
            if (iTouchableSpan4 != null) {
                iTouchableSpan4.setPressed(false);
            }
            if (textView instanceof ISpanTouchFix) {
                ((ISpanTouchFix) textView).setTouchSpanHit(false);
            }
            this.mPressedSpanRf = null;
            Selection.removeSelection(spannable);
            return false;
        }
    }

    public ITouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
        int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
        int scrollX = x + textView.getScrollX();
        Layout layout = textView.getLayout();
        int lineForVertical = layout.getLineForVertical(y + textView.getScrollY());
        float f = scrollX;
        try {
            layout.getOffsetForHorizontal(lineForVertical, f);
            int i = (f < layout.getLineLeft(lineForVertical) || f > layout.getLineRight(lineForVertical)) ? -1 : -1;
            ITouchableSpan[] iTouchableSpanArr = (ITouchableSpan[]) spannable.getSpans(i, i, ITouchableSpan.class);
            if (iTouchableSpanArr.length > 0) {
                return iTouchableSpanArr[0];
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }
}
