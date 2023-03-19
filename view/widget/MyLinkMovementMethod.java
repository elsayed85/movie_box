package com.movieboxpro.android.view.widget;

import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;
/* loaded from: classes3.dex */
public class MyLinkMovementMethod extends ScrollingMovementMethod {
    private static final int CLICK = 1;
    private static final int DOWN = 3;
    private static Object FROM_BELOW = new NoCopySpan.Concrete();
    private static final int UP = 2;
    private static MyLinkMovementMethod sInstance;

    @Override // android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean canSelectArbitrarily() {
        return true;
    }

    @Override // android.text.method.BaseMovementMethod
    protected boolean handleMovementKey(TextView textView, Spannable spannable, int i, int i2, KeyEvent keyEvent) {
        if ((i == 23 || i == 66) && KeyEvent.metaStateHasNoModifiers(i2) && keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0 && action(1, textView, spannable)) {
            return true;
        }
        return super.handleMovementKey(textView, spannable, i, i2, keyEvent);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean up(TextView textView, Spannable spannable) {
        if (action(2, textView, spannable)) {
            return true;
        }
        return super.up(textView, spannable);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean down(TextView textView, Spannable spannable) {
        if (action(3, textView, spannable)) {
            return true;
        }
        return super.down(textView, spannable);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean left(TextView textView, Spannable spannable) {
        if (action(2, textView, spannable)) {
            return true;
        }
        return super.left(textView, spannable);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean right(TextView textView, Spannable spannable) {
        if (action(3, textView, spannable)) {
            return true;
        }
        return super.right(textView, spannable);
    }

    private boolean action(int i, TextView textView, Spannable spannable) {
        Layout layout = textView.getLayout();
        int totalPaddingTop = textView.getTotalPaddingTop() + textView.getTotalPaddingBottom();
        int scrollY = textView.getScrollY();
        int height = (textView.getHeight() + scrollY) - totalPaddingTop;
        int lineForVertical = layout.getLineForVertical(scrollY);
        int lineForVertical2 = layout.getLineForVertical(height);
        int lineStart = layout.getLineStart(lineForVertical);
        int lineEnd = layout.getLineEnd(lineForVertical2);
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(lineStart, lineEnd, ClickableSpan.class);
        int selectionStart = Selection.getSelectionStart(spannable);
        int selectionEnd = Selection.getSelectionEnd(spannable);
        int min = Math.min(selectionStart, selectionEnd);
        int max = Math.max(selectionStart, selectionEnd);
        if (min < 0 && spannable.getSpanStart(FROM_BELOW) >= 0) {
            min = spannable.length();
            max = min;
        }
        if (min > lineEnd) {
            max = Integer.MAX_VALUE;
            min = Integer.MAX_VALUE;
        }
        int i2 = -1;
        if (max < lineStart) {
            max = -1;
            min = -1;
        }
        if (i != 1) {
            if (i == 2) {
                int i3 = -1;
                for (int i4 = 0; i4 < clickableSpanArr.length; i4++) {
                    int spanEnd = spannable.getSpanEnd(clickableSpanArr[i4]);
                    if ((spanEnd < max || min == max) && spanEnd > i3) {
                        i2 = spannable.getSpanStart(clickableSpanArr[i4]);
                        i3 = spanEnd;
                    }
                }
                if (i2 >= 0) {
                    Selection.setSelection(spannable, i3, i2);
                    return true;
                }
            } else if (i == 3) {
                int i5 = Integer.MAX_VALUE;
                int i6 = Integer.MAX_VALUE;
                for (int i7 = 0; i7 < clickableSpanArr.length; i7++) {
                    int spanStart = spannable.getSpanStart(clickableSpanArr[i7]);
                    if ((spanStart > min || min == max) && spanStart < i6) {
                        i5 = spannable.getSpanEnd(clickableSpanArr[i7]);
                        i6 = spanStart;
                    }
                }
                if (i5 < Integer.MAX_VALUE) {
                    Selection.setSelection(spannable, i6, i5);
                    return true;
                }
            }
        } else if (min == max) {
            return false;
        } else {
            ClickableSpan[] clickableSpanArr2 = (ClickableSpan[]) spannable.getSpans(min, max, ClickableSpan.class);
            if (clickableSpanArr2.length != 1) {
                return false;
            }
            clickableSpanArr2[0].onClick(textView);
        }
        return false;
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 0) {
            int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
            int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
            int scrollX = x + textView.getScrollX();
            int scrollY = y + textView.getScrollY();
            Layout layout = textView.getLayout();
            int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
            Object[] objArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
            if (objArr.length == 0) {
                Selection.removeSelection(spannable);
                return false;
            }
            if (action == 1) {
                objArr[0].onClick(textView);
            } else if (action == 0) {
                Selection.setSelection(spannable, spannable.getSpanStart(objArr[0]), spannable.getSpanEnd(objArr[0]));
            }
            return true;
        }
        return super.onTouchEvent(textView, spannable, motionEvent);
    }

    @Override // android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public void initialize(TextView textView, Spannable spannable) {
        Selection.removeSelection(spannable);
        spannable.removeSpan(FROM_BELOW);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public void onTakeFocus(TextView textView, Spannable spannable, int i) {
        Selection.removeSelection(spannable);
        if ((i & 1) != 0) {
            spannable.setSpan(FROM_BELOW, 0, 0, 34);
        } else {
            spannable.removeSpan(FROM_BELOW);
        }
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new MyLinkMovementMethod();
        }
        return sInstance;
    }
}
