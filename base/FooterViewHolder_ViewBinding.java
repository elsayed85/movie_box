package com.movieboxpro.android.base;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class FooterViewHolder_ViewBinding implements Unbinder {
    private FooterViewHolder target;

    public FooterViewHolder_ViewBinding(FooterViewHolder footerViewHolder, View view) {
        this.target = footerViewHolder;
        footerViewHolder.mLoading = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.loading_footer_progress, "field 'mLoading'", ProgressBar.class);
        footerViewHolder.mLoadingText = (TextView) Utils.findRequiredViewAsType(view, R.id.loading_footer_text, "field 'mLoadingText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        FooterViewHolder footerViewHolder = this.target;
        if (footerViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        footerViewHolder.mLoading = null;
        footerViewHolder.mLoadingText = null;
    }
}
