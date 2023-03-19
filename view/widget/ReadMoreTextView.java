package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class ReadMoreTextView extends AppCompatTextView {
    private static final boolean DEFAULT_SHOW_TRIM_EXPANDED_TEXT = true;
    private static final int DEFAULT_TRIM_LENGTH = 240;
    private static final int DEFAULT_TRIM_LINES = 2;
    private static final String ELLIPSIZE = "... ";
    private static final int INVALID_END_INDEX = -1;
    private static final int TRIM_MODE_LENGTH = 1;
    private static final int TRIM_MODE_LINES = 0;
    private TextView.BufferType bufferType;
    private int colorClickableText;
    private int lineEndIndex;
    private boolean readMore;
    private boolean showTrimExpandedText;
    private CharSequence text;
    private CharSequence trimCollapsedText;
    private CharSequence trimExpandedText;
    private int trimLength;
    private int trimLines;
    private int trimMode;
    private ReadMoreClickableSpan viewMoreSpan;

    public ReadMoreTextView(Context context) {
        this(context, null);
    }

    public ReadMoreTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.readMore = true;
        this.trimCollapsedText = "More";
        this.trimExpandedText = "Less";
        this.colorClickableText = ContextCompat.getColor(App.getContext(), R.color.color_main_blue);
        this.trimMode = 0;
        this.viewMoreSpan = new ReadMoreClickableSpan();
        onGlobalLayoutLineEndIndex();
        setText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setText() {
        super.setText(getDisplayableText(), this.bufferType);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(0);
    }

    private CharSequence getDisplayableText() {
        return getTrimmedText(this.text);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.text = charSequence;
        this.bufferType = bufferType;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence charSequence) {
        if (this.trimMode == 1 && charSequence != null && charSequence.length() > this.trimLength) {
            if (this.readMore) {
                return updateCollapsedText();
            }
            return updateExpandedText();
        } else if (this.trimMode != 0 || charSequence == null || this.lineEndIndex <= 0) {
            return charSequence;
        } else {
            if (this.readMore) {
                return getLayout().getLineCount() > this.trimLines ? updateCollapsedText() : charSequence;
            }
            return updateExpandedText();
        }
    }

    private CharSequence updateCollapsedText() {
        int i;
        int length = this.text.length();
        int i2 = this.trimMode;
        if (i2 == 0) {
            length = this.lineEndIndex - ((4 + this.trimCollapsedText.length()) + 1);
            if (length < 0) {
                i = this.trimLength;
                length = i + 1;
            }
        } else if (i2 == 1) {
            i = this.trimLength;
            length = i + 1;
        }
        return addClickableSpan(new SpannableStringBuilder(this.text, 0, length).append((CharSequence) ELLIPSIZE).append(this.trimCollapsedText), this.trimCollapsedText);
    }

    private CharSequence updateExpandedText() {
        if (this.showTrimExpandedText) {
            CharSequence charSequence = this.text;
            return addClickableSpan(new SpannableStringBuilder(charSequence, 0, charSequence.length()).append(this.trimExpandedText), this.trimExpandedText);
        }
        return this.text;
    }

    private CharSequence addClickableSpan(SpannableStringBuilder spannableStringBuilder, CharSequence charSequence) {
        spannableStringBuilder.setSpan(this.viewMoreSpan, spannableStringBuilder.length() - charSequence.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public void setTrimLength(int i) {
        this.trimLength = i;
        setText();
    }

    public void setColorClickableText(int i) {
        this.colorClickableText = i;
    }

    public void setTrimCollapsedText(CharSequence charSequence) {
        this.trimCollapsedText = charSequence;
    }

    public void setTrimExpandedText(CharSequence charSequence) {
        this.trimExpandedText = charSequence;
    }

    public void setTrimMode(int i) {
        this.trimMode = i;
    }

    public void setTrimLines(int i) {
        this.trimLines = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class ReadMoreClickableSpan extends ClickableSpan {
        private ReadMoreClickableSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            ReadMoreTextView readMoreTextView = ReadMoreTextView.this;
            readMoreTextView.readMore = !readMoreTextView.readMore;
            ReadMoreTextView.this.setText();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(ReadMoreTextView.this.colorClickableText);
        }
    }

    private void onGlobalLayoutLineEndIndex() {
        if (this.trimMode == 0) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.widget.ReadMoreTextView.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ReadMoreTextView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ReadMoreTextView.this.refreshLineEndIndex();
                    ReadMoreTextView.this.setText();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLineEndIndex() {
        try {
            if (this.trimLines == 0) {
                this.lineEndIndex = getLayout().getLineEnd(0);
            } else if (this.trimLines > 0 && getLineCount() >= this.trimLines) {
                this.lineEndIndex = getLayout().getLineEnd(this.trimLines - 1);
            } else {
                this.lineEndIndex = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
