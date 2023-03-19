package com.movieboxpro.android.view.activity.review;

import android.content.Context;
import android.util.AttributeSet;
import com.rex.editor.view.RichEditor;
/* loaded from: classes3.dex */
public class MyRichEditor extends RichEditor {
    private void initData() {
    }

    public MyRichEditor(Context context) {
        super(context);
    }

    public MyRichEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyRichEditor(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initData();
    }

    public void insertHtml(String str) {
        exec("javascript:RE.prepareInsert();");
        exec("javascript:RE.insertHTML('" + str + "');");
    }
}
