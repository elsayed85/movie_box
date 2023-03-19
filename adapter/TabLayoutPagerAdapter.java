package com.movieboxpro.android.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
/* loaded from: classes3.dex */
public class TabLayoutPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] mTitles;

    public TabLayoutPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, String[] strArr) {
        super(fragmentManager);
        this.list = list;
        this.mTitles = strArr;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.list.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.mTitles[i];
    }
}
