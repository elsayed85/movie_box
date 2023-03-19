package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/* loaded from: classes3.dex */
public class MaxTwoLineTextLabelLayout extends ViewGroup {
    private static final int CHILD_COUNT = 2;
    private static final int MAX_LINE = 2;

    public MaxTwoLineTextLabelLayout(Context context) {
        super(context);
    }

    public MaxTwoLineTextLabelLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() == 2 && (getChildAt(0) instanceof TextView)) {
            int i5 = i3 - i;
            TextView textView = (TextView) getChildAt(0);
            int measuredWidth = textView.getMeasuredWidth();
            int measuredHeight = textView.getMeasuredHeight();
            View childAt = getChildAt(1);
            int measuredWidth2 = childAt.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredHeight2 = childAt.getMeasuredHeight();
            if (measuredWidth + measuredWidth2 + marginLayoutParams.leftMargin > i5) {
                if (textView.getLineCount() == 1) {
                    textView.layout(0, 0, measuredWidth, measuredHeight);
                    int i6 = measuredHeight + marginLayoutParams.topMargin;
                    childAt.layout(0, i6, measuredWidth2, measuredHeight2 + i6);
                    return;
                }
                textView.layout(0, 0, measuredWidth, measuredHeight);
                int lineWidth = getLineWidth(textView, 1);
                if (marginLayoutParams.leftMargin + lineWidth + measuredWidth2 < i5) {
                    int i7 = lineWidth + marginLayoutParams.leftMargin;
                    int i8 = ((measuredHeight + (measuredHeight / 2)) - measuredHeight2) / 2;
                    childAt.layout(i7, i8, measuredWidth2 + i7, measuredHeight2 + i8);
                    return;
                }
                return;
            }
            textView.layout(0, 0, measuredWidth, measuredHeight);
            int i9 = measuredWidth + marginLayoutParams.leftMargin;
            int i10 = (measuredHeight - measuredHeight2) / 2;
            childAt.layout(i9, i10, measuredWidth2 + i9, measuredHeight2 + i10);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (getChildCount() == 2 && (getChildAt(0) instanceof TextView)) {
            TextView textView = (TextView) getChildAt(0);
            measureChild(textView, i, i2);
            int measuredWidth = textView.getMeasuredWidth();
            int measuredHeight = textView.getMeasuredHeight();
            View childAt = getChildAt(1);
            measureChild(childAt, i, i2);
            int measuredWidth2 = childAt.getMeasuredWidth();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredHeight2 = childAt.getMeasuredHeight();
            int i3 = measuredWidth2 + measuredWidth + marginLayoutParams.leftMargin;
            if (i3 <= size) {
                measuredWidth = i3;
            } else if (textView.getLineCount() == 1) {
                measuredHeight = marginLayoutParams.topMargin + measuredHeight + measuredHeight2;
            } else {
                measuredWidth = Math.max(measuredWidth, size);
            }
            setMeasuredDimension(measuredWidth, measuredHeight);
            return;
        }
        setMeasuredDimension(size, size2);
    }

    private int getLineWidth(TextView textView, int i) {
        Layout layout = textView.getLayout();
        int lineCount = textView.getLineCount();
        if (layout == null || i < 0 || i >= lineCount) {
            return 0;
        }
        double lineWidth = layout.getLineWidth(i);
        Double.isNaN(lineWidth);
        return (int) (lineWidth + 0.5d);
    }
}
