package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.movieboxpro.android.R;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class LockPatternView extends ViewGroup {
    private boolean autoLink;
    private GestureCallback callback;
    private boolean enableVibrate;
    private boolean inPause;
    private boolean isGoneFinish;
    private int lineColor;
    private float lineWidth;
    private float nodeAreaExpand;
    private final List<NodeView> nodeList;
    private int nodeOnAnim;
    private Drawable nodeOnSrc;
    private float nodeSize;
    private Drawable nodeSrc;
    private float padding;
    private Paint paint;
    private float spacing;
    private int vibrateTime;
    private Vibrator vibrator;
    private float x;
    private float y;

    /* loaded from: classes3.dex */
    public interface GestureCallback {
        void onGestureFinished(int[] iArr);

        void onNodeConnected(int[] iArr);
    }

    public void setGestureCallback(GestureCallback gestureCallback) {
        this.callback = gestureCallback;
    }

    public LockPatternView(Context context) {
        super(context);
        this.nodeList = new ArrayList();
        this.isGoneFinish = true;
        init(context, null, 0, 0);
    }

    public LockPatternView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.nodeList = new ArrayList();
        this.isGoneFinish = true;
        init(context, attributeSet, 0, 0);
    }

    public LockPatternView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.nodeList = new ArrayList();
        this.isGoneFinish = true;
        init(context, attributeSet, i, 0);
    }

    public LockPatternView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.nodeList = new ArrayList();
        this.isGoneFinish = true;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Lock9View, i, i2);
        this.nodeSrc = obtainStyledAttributes.getDrawable(8);
        this.nodeOnSrc = obtainStyledAttributes.getDrawable(6);
        this.nodeSize = obtainStyledAttributes.getDimension(7, 0.0f);
        this.nodeAreaExpand = obtainStyledAttributes.getDimension(4, 0.0f);
        this.nodeOnAnim = obtainStyledAttributes.getResourceId(5, 0);
        this.lineColor = obtainStyledAttributes.getColor(2, Color.argb(0, 0, 0, 0));
        this.lineWidth = obtainStyledAttributes.getDimension(3, 0.0f);
        this.padding = obtainStyledAttributes.getDimension(9, 0.0f);
        this.spacing = obtainStyledAttributes.getDimension(10, 0.0f);
        this.autoLink = obtainStyledAttributes.getBoolean(0, false);
        this.enableVibrate = obtainStyledAttributes.getBoolean(1, false);
        this.vibrateTime = obtainStyledAttributes.getInt(11, 20);
        obtainStyledAttributes.recycle();
        if (this.enableVibrate && !isInEditMode()) {
            this.vibrator = (Vibrator) context.getSystemService("vibrator");
        }
        Paint paint = new Paint(4);
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.lineWidth);
        this.paint.setColor(this.lineColor);
        this.paint.setAntiAlias(true);
        int i3 = 0;
        while (i3 < 9) {
            i3++;
            addView(new NodeView(getContext(), i3));
        }
        setWillNotDraw(false);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measureSize = measureSize(i);
        setMeasuredDimension(measureSize, measureSize);
    }

    private int measureSize(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return size;
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int i5 = 0;
            if (this.nodeSize > 0.0f) {
                float f = (i3 - i) / 3;
                while (i5 < 9) {
                    float f2 = this.nodeSize;
                    int i6 = (int) (((i5 % 3) * f) + ((f - f2) / 2.0f));
                    int i7 = (int) (((i5 / 3) * f) + ((f - f2) / 2.0f));
                    ((NodeView) getChildAt(i5)).layout(i6, i7, (int) (i6 + f2), (int) (i7 + f2));
                    i5++;
                }
                return;
            }
            float f3 = (((i3 - i) - (this.padding * 2.0f)) - (this.spacing * 2.0f)) / 3.0f;
            while (i5 < 9) {
                float f4 = this.padding;
                float f5 = this.spacing;
                int i8 = (int) (((i5 % 3) * (f3 + f5)) + f4);
                int i9 = (int) (f4 + ((i5 / 3) * (f5 + f3)));
                ((NodeView) getChildAt(i5)).layout(i8, i9, (int) (i8 + f3), (int) (i9 + f3));
                i5++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000a, code lost:
        if (r0 != 2) goto L6;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L2a
            if (r0 == r1) goto Le
            r2 = 2
            if (r0 == r2) goto L2a
            goto L90
        Le:
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r5 = r4.nodeList
            int r5 = r5.size()
            if (r5 <= 0) goto L90
            r4.handleOnGestureFinishedCallback()
            r4.inPause = r1
            boolean r5 = r4.isGoneFinish
            if (r5 == 0) goto L90
            com.movieboxpro.android.view.widget.LockPatternView$1 r5 = new com.movieboxpro.android.view.widget.LockPatternView$1
            r5.<init>()
            r2 = 500(0x1f4, double:2.47E-321)
            r4.postDelayed(r5, r2)
            goto L90
        L2a:
            boolean r0 = r4.inPause
            if (r0 == 0) goto L2f
            goto L90
        L2f:
            float r0 = r5.getX()
            r4.x = r0
            float r5 = r5.getY()
            r4.y = r5
            float r0 = r4.x
            com.movieboxpro.android.view.widget.LockPatternView$NodeView r5 = r4.getNodeAt(r0, r5)
            if (r5 == 0) goto L85
            boolean r0 = r5.isHighLighted()
            if (r0 != 0) goto L85
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r0 = r4.nodeList
            int r0 = r0.size()
            if (r0 <= 0) goto L79
            boolean r0 = r4.autoLink
            if (r0 == 0) goto L79
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r0 = r4.nodeList
            int r2 = r0.size()
            int r2 = r2 - r1
            java.lang.Object r0 = r0.get(r2)
            com.movieboxpro.android.view.widget.LockPatternView$NodeView r0 = (com.movieboxpro.android.view.widget.LockPatternView.NodeView) r0
            com.movieboxpro.android.view.widget.LockPatternView$NodeView r0 = r4.getNodeBetween(r0, r5)
            if (r0 == 0) goto L79
            boolean r2 = r0.isHighLighted()
            if (r2 != 0) goto L79
            r0.setHighLighted(r1, r1)
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r2 = r4.nodeList
            r2.add(r0)
            r4.handleOnNodeConnectedCallback()
        L79:
            r0 = 0
            r5.setHighLighted(r1, r0)
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r0 = r4.nodeList
            r0.add(r5)
            r4.handleOnNodeConnectedCallback()
        L85:
            java.util.List<com.movieboxpro.android.view.widget.LockPatternView$NodeView> r5 = r4.nodeList
            int r5 = r5.size()
            if (r5 <= 0) goto L90
            r4.invalidate()
        L90:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.widget.LockPatternView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void resetNodes() {
        this.nodeList.clear();
        for (int i = 0; i < getChildCount(); i++) {
            ((NodeView) getChildAt(i)).setHighLighted(false, false);
        }
        this.paint.setColor(this.lineColor);
        invalidate();
        this.inPause = false;
    }

    public void setFinishGone(boolean z) {
        this.isGoneFinish = z;
    }

    public void setErrorStatus(Drawable drawable, int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            NodeView nodeView = (NodeView) getChildAt(i2);
            if (nodeView.isHighLighted()) {
                nodeView.setErrorBackground(drawable);
            }
        }
        this.paint.setColor(i);
        invalidate();
    }

    private int[] generateCurrentNumbers() {
        int[] iArr = new int[this.nodeList.size()];
        for (int i = 0; i < this.nodeList.size(); i++) {
            iArr[i] = this.nodeList.get(i).getNumber();
        }
        return iArr;
    }

    private void handleOnNodeConnectedCallback() {
        GestureCallback gestureCallback = this.callback;
        if (gestureCallback != null) {
            gestureCallback.onNodeConnected(generateCurrentNumbers());
        }
    }

    private void handleOnGestureFinishedCallback() {
        GestureCallback gestureCallback = this.callback;
        if (gestureCallback != null) {
            gestureCallback.onGestureFinished(generateCurrentNumbers());
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i = 1; i < this.nodeList.size(); i++) {
            NodeView nodeView = this.nodeList.get(i - 1);
            NodeView nodeView2 = this.nodeList.get(i);
            canvas.drawLine(nodeView.getCenterX(), nodeView.getCenterY(), nodeView2.getCenterX(), nodeView2.getCenterY(), this.paint);
        }
        if (this.nodeList.size() > 0) {
            List<NodeView> list = this.nodeList;
            NodeView nodeView3 = list.get(list.size() - 1);
            canvas.drawLine(nodeView3.getCenterX(), nodeView3.getCenterY(), this.x, this.y, this.paint);
        }
    }

    private NodeView getNodeAt(float f, float f2) {
        for (int i = 0; i < getChildCount(); i++) {
            NodeView nodeView = (NodeView) getChildAt(i);
            if (f >= nodeView.getLeft() - this.nodeAreaExpand && f < nodeView.getRight() + this.nodeAreaExpand && f2 >= nodeView.getTop() - this.nodeAreaExpand && f2 < nodeView.getBottom() + this.nodeAreaExpand) {
                return nodeView;
            }
        }
        return null;
    }

    private NodeView getNodeBetween(NodeView nodeView, NodeView nodeView2) {
        if (nodeView.getNumber() > nodeView2.getNumber()) {
            nodeView2 = nodeView;
            nodeView = nodeView2;
        }
        if (nodeView.getNumber() % 3 == 1 && nodeView2.getNumber() - nodeView.getNumber() == 2) {
            return (NodeView) getChildAt(nodeView.getNumber());
        }
        if (nodeView.getNumber() <= 3 && nodeView2.getNumber() - nodeView.getNumber() == 6) {
            return (NodeView) getChildAt(nodeView.getNumber() + 2);
        }
        if ((nodeView.getNumber() == 1 && nodeView2.getNumber() == 9) || (nodeView.getNumber() == 3 && nodeView2.getNumber() == 7)) {
            return (NodeView) getChildAt(4);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class NodeView extends View {
        private boolean highLighted;
        private int number;

        NodeView(Context context, int i) {
            super(context);
            this.highLighted = false;
            this.number = i;
            setBackgroundDrawable(LockPatternView.this.nodeSrc);
        }

        boolean isHighLighted() {
            return this.highLighted;
        }

        void setErrorBackground(Drawable drawable) {
            setBackground(drawable);
        }

        void setHighLighted(boolean z, boolean z2) {
            if (this.highLighted != z) {
                this.highLighted = z;
                if (LockPatternView.this.nodeOnSrc != null) {
                    LockPatternView lockPatternView = LockPatternView.this;
                    setBackgroundDrawable(z ? lockPatternView.nodeOnSrc : lockPatternView.nodeSrc);
                }
                if (LockPatternView.this.nodeOnAnim != 0) {
                    if (z) {
                        startAnimation(AnimationUtils.loadAnimation(getContext(), LockPatternView.this.nodeOnAnim));
                    } else {
                        clearAnimation();
                    }
                }
                if (LockPatternView.this.enableVibrate && !z2 && z) {
                    LockPatternView.this.vibrator.vibrate(LockPatternView.this.vibrateTime);
                }
            }
        }

        int getCenterX() {
            return (getLeft() + getRight()) / 2;
        }

        int getCenterY() {
            return (getTop() + getBottom()) / 2;
        }

        int getNumber() {
            return this.number;
        }
    }
}
