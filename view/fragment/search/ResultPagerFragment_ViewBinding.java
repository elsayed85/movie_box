package com.movieboxpro.android.view.fragment.search;

import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
/* loaded from: classes3.dex */
public class ResultPagerFragment_ViewBinding implements Unbinder {
    private ResultPagerFragment target;

    public ResultPagerFragment_ViewBinding(ResultPagerFragment resultPagerFragment, View view) {
        this.target = resultPagerFragment;
        resultPagerFragment.mTabs = (SmartTabLayout) Utils.findRequiredViewAsType(view, R.id.search_result_tab, "field 'mTabs'", SmartTabLayout.class);
        resultPagerFragment.mPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.search_result_pager, "field 'mPager'", ViewPager.class);
        resultPagerFragment.ivFilter = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivFilter, "field 'ivFilter'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ResultPagerFragment resultPagerFragment = this.target;
        if (resultPagerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        resultPagerFragment.mTabs = null;
        resultPagerFragment.mPager = null;
        resultPagerFragment.ivFilter = null;
    }
}
