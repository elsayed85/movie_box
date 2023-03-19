package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.SystemUtils;
/* loaded from: classes3.dex */
public class SpannableFoldTextView extends AppCompatTextView implements View.OnClickListener {
    private static final String ELLIPSIZE_END = "...";
    private static final int END = 0;
    private static final String EXPAND_TIP_TEXT = "UP ";
    private static final String FOLD_TIP_TEXT = "All ";
    private static final int MAX_LINE = 2;
    private static final String TAG = "SpannableFoldTextView";
    private static final int TIP_COLOR = -1;
    private boolean flag;
    private HttpSpan httpSpan;
    private boolean isExpand;
    private boolean isExpandSpanClick;
    private boolean isParentClick;
    private boolean isShowTipAfterExpand;
    private View.OnClickListener listener;
    private String mExpandText;
    private String mFoldText;
    private String mHttpSpan;
    private CharSequence mOriginalText;
    private int mShowMaxLine;
    private ExpandSpan mSpan;
    private boolean mTipClickable;
    private int mTipColor;
    private int mTipGravity;

    public SpannableFoldTextView(Context context) {
        this(context, null);
    }

    public SpannableFoldTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
    }

    public SpannableFoldTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowMaxLine = 2;
        this.mSpan = new ExpandSpan();
        this.httpSpan = new HttpSpan();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FoldTextView);
            this.mShowMaxLine = obtainStyledAttributes.getInt(3, 2);
            this.mTipGravity = obtainStyledAttributes.getInt(7, 0);
            this.mTipColor = obtainStyledAttributes.getColor(6, -1);
            this.mTipClickable = obtainStyledAttributes.getBoolean(5, false);
            this.mFoldText = obtainStyledAttributes.getString(1);
            this.mExpandText = obtainStyledAttributes.getString(0);
            this.isShowTipAfterExpand = obtainStyledAttributes.getBoolean(4, false);
            this.isParentClick = obtainStyledAttributes.getBoolean(2, false);
            obtainStyledAttributes.recycle();
        }
        if (TextUtils.isEmpty(this.mExpandText)) {
            this.mExpandText = EXPAND_TIP_TEXT;
        }
        if (TextUtils.isEmpty(this.mFoldText)) {
            this.mFoldText = FOLD_TIP_TEXT;
        }
    }

    @Override // android.widget.TextView
    public void setText(final CharSequence charSequence, final TextView.BufferType bufferType) {
        if (TextUtils.isEmpty(charSequence) || this.mShowMaxLine == 0) {
            super.setText(charSequence, bufferType);
        } else if (this.isExpand) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mOriginalText);
            if (this.mHttpSpan != null) {
                spannableStringBuilder.append("\n\ns");
                Drawable drawable = getResources().getDrawable(R.drawable.ic_imdb);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new myImageSpain(drawable, 1), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
                spannableStringBuilder.append((CharSequence) this.mHttpSpan);
                spannableStringBuilder.setSpan(this.httpSpan, spannableStringBuilder.length() - this.mHttpSpan.length(), spannableStringBuilder.length(), 17);
            }
            addTip(spannableStringBuilder, bufferType);
        } else if (!this.flag) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.movieboxpro.android.view.widget.SpannableFoldTextView.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    SpannableFoldTextView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    SpannableFoldTextView.this.flag = true;
                    SpannableFoldTextView.this.formatText(charSequence, bufferType);
                    return true;
                }
            });
        } else {
            formatText(charSequence, bufferType);
        }
    }

    private void addTip(SpannableStringBuilder spannableStringBuilder, TextView.BufferType bufferType) {
        if (!this.isExpand || this.isShowTipAfterExpand) {
            if (this.mTipGravity == 0) {
                spannableStringBuilder.append("  ");
            } else {
                spannableStringBuilder.append(" ");
            }
            if (this.isExpand) {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_expandtext_up);
                drawable.setBounds(0, 0, getLayout().getWidth(), drawable.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new myImageSpain(drawable, 1), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
            } else {
                Drawable drawable2 = getResources().getDrawable(R.drawable.ic_expandtext);
                drawable2.setBounds(0, 0, getLayout().getWidth(), drawable2.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new myImageSpain(drawable2, 1), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
            }
            if (this.mTipClickable) {
                spannableStringBuilder.setSpan(this.mSpan, spannableStringBuilder.length() - 0, spannableStringBuilder.length(), 17);
                if (this.isParentClick) {
                    setMovementMethod(MyLinkMovementMethod.getInstance());
                    setClickable(false);
                    setFocusable(false);
                    setLongClickable(false);
                } else {
                    setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.mTipColor), spannableStringBuilder.length() - 0, spannableStringBuilder.length(), 17);
        }
        super.setText(spannableStringBuilder, bufferType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatText(CharSequence charSequence, final TextView.BufferType bufferType) {
        this.mOriginalText = charSequence;
        Layout layout = getLayout();
        if (layout == null || !layout.getText().equals(this.mOriginalText)) {
            super.setText(this.mOriginalText, bufferType);
            layout = getLayout();
        }
        if (layout == null) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.widget.SpannableFoldTextView.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    SpannableFoldTextView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    SpannableFoldTextView spannableFoldTextView = SpannableFoldTextView.this;
                    spannableFoldTextView.translateText(spannableFoldTextView.getLayout(), bufferType);
                }
            });
        } else {
            translateText(layout, bufferType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateText(Layout layout, TextView.BufferType bufferType) {
        if (layout.getLineCount() > this.mShowMaxLine) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int lineStart = layout.getLineStart(this.mShowMaxLine - 1);
            int lineVisibleEnd = layout.getLineVisibleEnd(this.mShowMaxLine - 1);
            TextPaint paint = getPaint();
            StringBuilder sb = new StringBuilder(ELLIPSIZE_END);
            if (this.mTipGravity == 0) {
                sb.append("  ");
                sb.append(this.mFoldText);
            }
            spannableStringBuilder.append(this.mOriginalText.subSequence(0, lineVisibleEnd - (paint.breakText(this.mOriginalText, lineStart, lineVisibleEnd, false, paint.measureText(sb.toString()), null) + 1)));
            spannableStringBuilder.append(ELLIPSIZE_END);
            addTip(spannableStringBuilder, bufferType);
        }
    }

    public void setShowMaxLine(int i) {
        this.mShowMaxLine = i;
    }

    public void setFoldText(String str) {
        this.mFoldText = str;
    }

    public void setExpandText(String str) {
        this.mExpandText = str;
    }

    public void setTipGravity(int i) {
        this.mTipGravity = i;
    }

    public void setTipColor(int i) {
        this.mTipColor = i;
    }

    public void setTipClickable(boolean z) {
        this.mTipClickable = z;
    }

    public void setHttpSpan(String str) {
        this.mHttpSpan = str;
    }

    public void setShowTipAfterExpand(boolean z) {
        this.isShowTipAfterExpand = z;
    }

    public void setParentClick(boolean z) {
        this.isParentClick = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.isExpandSpanClick) {
            this.isExpandSpanClick = false;
            return;
        }
        this.isExpand = !this.isExpand;
        Log.d("emmm", "onClick: parentclick click");
        setText(this.mOriginalText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class ExpandSpan extends ClickableSpan {
        private ExpandSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            if (SpannableFoldTextView.this.mTipClickable) {
                SpannableFoldTextView spannableFoldTextView = SpannableFoldTextView.this;
                spannableFoldTextView.isExpand = !spannableFoldTextView.isExpand;
                SpannableFoldTextView.this.isExpandSpanClick = true;
                Log.d("emmm", "onClick: span click");
                SpannableFoldTextView spannableFoldTextView2 = SpannableFoldTextView.this;
                spannableFoldTextView2.setText(spannableFoldTextView2.mOriginalText);
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(SpannableFoldTextView.this.mTipColor);
            textPaint.setUnderlineText(false);
        }
    }

    /* loaded from: classes3.dex */
    private class HttpSpan extends ClickableSpan {
        private HttpSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            if (SpannableFoldTextView.this.mTipClickable) {
                SpannableFoldTextView.this.isExpandSpanClick = true;
                Log.d("emmm", "onClick: span click");
                if (SpannableFoldTextView.this.mHttpSpan != null) {
                    SystemUtils.startBrowser(SpannableFoldTextView.this.getContext(), SpannableFoldTextView.this.mHttpSpan);
                }
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(SpannableFoldTextView.this.mTipColor);
            textPaint.setUnderlineText(false);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.listener = onClickListener;
        super.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class myImageSpain extends ImageSpan {
        public myImageSpain(Bitmap bitmap) {
            super(bitmap);
        }

        public myImageSpain(Drawable drawable, int i) {
            super(drawable, i);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            canvas.save();
            canvas.translate(f, ((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2));
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
