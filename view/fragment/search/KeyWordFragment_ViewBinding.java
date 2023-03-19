package com.movieboxpro.android.view.fragment.search;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.widget.MyRecyclerView;
/* loaded from: classes3.dex */
public class KeyWordFragment_ViewBinding implements Unbinder {
    private KeyWordFragment target;

    public KeyWordFragment_ViewBinding(KeyWordFragment keyWordFragment, View view) {
        this.target = keyWordFragment;
        keyWordFragment.mRecycler = (MyRecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_normal_recycler, "field 'mRecycler'", MyRecyclerView.class);
        keyWordFragment.mEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.text_empty, "field 'mEmptyText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyWordFragment keyWordFragment = this.target;
        if (keyWordFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyWordFragment.mRecycler = null;
        keyWordFragment.mEmptyText = null;
    }
}
