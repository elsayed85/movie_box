package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SavedState;
/* loaded from: classes3.dex */
public class MyRangeSeekBar extends RangeSeekBar {
    public MyRangeSeekBar(Context context) {
        super(context);
    }

    public MyRangeSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.jaygoo.widget.RangeSeekBar, android.view.View
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState());
    }
}
