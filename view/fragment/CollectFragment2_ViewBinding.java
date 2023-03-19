package com.movieboxpro.android.view.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class CollectFragment2_ViewBinding implements Unbinder {
    private CollectFragment2 target;
    private View view7f09020d;
    private View view7f09020e;

    public CollectFragment2_ViewBinding(final CollectFragment2 collectFragment2, View view) {
        this.target = collectFragment2;
        collectFragment2.mEdit = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_add, "field 'mEdit'", ImageView.class);
        collectFragment2.mCollectTab = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.fragmen_collect_tabs, "field 'mCollectTab'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fragmen_collect_select, "field 'mSelect' and method 'onViewClicked'");
        collectFragment2.mSelect = (TextView) Utils.castView(findRequiredView, R.id.fragmen_collect_select, "field 'mSelect'", TextView.class);
        this.view7f09020e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                collectFragment2.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.fragmen_collect_delete, "field 'mDelete' and method 'onViewClicked'");
        collectFragment2.mDelete = (TextView) Utils.castView(findRequiredView2, R.id.fragmen_collect_delete, "field 'mDelete'", TextView.class);
        this.view7f09020d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                collectFragment2.onViewClicked(view2);
            }
        });
        collectFragment2.llEdit = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llEdit, "field 'llEdit'", LinearLayout.class);
        collectFragment2.flDragHint = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flDragHint, "field 'flDragHint'", FrameLayout.class);
        collectFragment2.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
        collectFragment2.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewPager, "field 'viewPager'", ViewPager.class);
        collectFragment2.ivFilter = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivFilter, "field 'ivFilter'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CollectFragment2 collectFragment2 = this.target;
        if (collectFragment2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        collectFragment2.mEdit = null;
        collectFragment2.mCollectTab = null;
        collectFragment2.mSelect = null;
        collectFragment2.mDelete = null;
        collectFragment2.llEdit = null;
        collectFragment2.flDragHint = null;
        collectFragment2.tabLayout = null;
        collectFragment2.viewPager = null;
        collectFragment2.ivFilter = null;
        this.view7f09020e.setOnClickListener(null);
        this.view7f09020e = null;
        this.view7f09020d.setOnClickListener(null);
        this.view7f09020d = null;
    }
}
