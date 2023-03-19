package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class Language_ViewBinding implements Unbinder {
    private Language target;

    public Language_ViewBinding(Language language) {
        this(language, language.getWindow().getDecorView());
    }

    public Language_ViewBinding(Language language, View view) {
        this.target = language;
        language.mRecycler = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.myRecycler, "field 'mRecycler'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        Language language = this.target;
        if (language == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        language.mRecycler = null;
    }
}
