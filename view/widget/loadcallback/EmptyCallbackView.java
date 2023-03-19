package com.movieboxpro.android.view.widget.loadcallback;

import com.kingja.loadsir.callback.Callback;
import com.movieboxpro.android.R;
/* loaded from: classes.dex */
public class EmptyCallbackView extends Callback {
    @Override // com.kingja.loadsir.callback.Callback
    protected int onCreateView() {
        return R.layout.empty_view_layout;
    }
}
