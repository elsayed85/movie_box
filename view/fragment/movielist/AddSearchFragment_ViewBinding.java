package com.movieboxpro.android.view.fragment.movielist;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class AddSearchFragment_ViewBinding implements Unbinder {
    private AddSearchFragment target;
    private View view7f09030d;
    private View view7f0907e2;

    public AddSearchFragment_ViewBinding(final AddSearchFragment addSearchFragment, View view) {
        this.target = addSearchFragment;
        addSearchFragment.etSearch = (EditText) Utils.findRequiredViewAsType(view, R.id.et_search, "field 'etSearch'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_clear, "field 'ivClear' and method 'onViewClicked'");
        addSearchFragment.ivClear = (ImageView) Utils.castView(findRequiredView, R.id.iv_clear, "field 'ivClear'", ImageView.class);
        this.view7f09030d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_done, "field 'tvDone' and method 'onViewClicked'");
        addSearchFragment.tvDone = (TextView) Utils.castView(findRequiredView2, R.id.tv_done, "field 'tvDone'", TextView.class);
        this.view7f0907e2 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addSearchFragment.onViewClicked(view2);
            }
        });
        addSearchFragment.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frame_layout, "field 'frameLayout'", FrameLayout.class);
        addSearchFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.keywordRecyclerView, "field 'recyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AddSearchFragment addSearchFragment = this.target;
        if (addSearchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addSearchFragment.etSearch = null;
        addSearchFragment.ivClear = null;
        addSearchFragment.tvDone = null;
        addSearchFragment.frameLayout = null;
        addSearchFragment.recyclerView = null;
        this.view7f09030d.setOnClickListener(null);
        this.view7f09030d = null;
        this.view7f0907e2.setOnClickListener(null);
        this.view7f0907e2 = null;
    }
}
