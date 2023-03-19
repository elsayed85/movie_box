package com.movieboxpro.android.base;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class FooterViewHolder extends BaseViewHolder {
    @BindView(R.id.loading_footer_progress)
    ProgressBar mLoading;
    @BindView(R.id.loading_footer_text)
    TextView mLoadingText;

    public FooterViewHolder(View view, OnItemClickListener onItemClickListener) {
        super(view, onItemClickListener);
    }
}
