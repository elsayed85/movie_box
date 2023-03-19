package com.movieboxpro.android.view.widget.loadcallback;

import com.kingja.loadsir.callback.Callback;
import com.movieboxpro.android.R;
/* loaded from: classes.dex */
public class LoadingCallbackView extends Callback {
    @Override // com.kingja.loadsir.callback.Callback
    protected int onCreateView() {
        return R.layout.loading_view_layout;
    }
}
