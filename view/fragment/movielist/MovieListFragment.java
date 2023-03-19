package com.movieboxpro.android.view.fragment.movielist;

import android.os.Bundle;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.fragment.MovieListList2Fragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class MovieListFragment extends BaseMvpFragment<MovieListPresenter> {
    private int current = 0;
    private FragmentManager fragmentManager;
    @BindView(R.id.homelist_view_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_movie_list;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @OnClick({R.id.iv_add})
    public void onAddMovieList() {
        if (App.isLogin()) {
            CreateMovieListActivity.start(getContext(), "");
        } else {
            Login2Activity.start(getContext());
        }
    }

    public static MovieListFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("fromAdd", z);
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(bundle);
        return movieListFragment;
    }

    public static MovieListFragment newInstance() {
        Bundle bundle = new Bundle();
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(bundle);
        return movieListFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public MovieListPresenter bindPresenter() {
        return new MovieListPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        boolean z = getArguments() != null ? getArguments().getBoolean("fromAdd", false) : false;
        this.fragmentManager = getChildFragmentManager();
        ArrayList arrayList = new ArrayList(3);
        MovielistListFragment movielistListFragment = (MovielistListFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(0L));
        if (movielistListFragment == null) {
            movielistListFragment = MovielistListFragment.newInstance(TtmlNode.COMBINE_ALL, z);
        }
        MovieListList2Fragment movieListList2Fragment = (MovieListList2Fragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(1L));
        if (movieListList2Fragment == null) {
            movieListList2Fragment = MovieListList2Fragment.newInstance("mine", z);
        }
        MovieListList2Fragment movieListList2Fragment2 = (MovieListList2Fragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(2L));
        if (movieListList2Fragment2 == null) {
            movieListList2Fragment2 = MovieListList2Fragment.newInstance("collect", z);
        }
        arrayList.add(movielistListFragment);
        arrayList.add(movieListList2Fragment);
        arrayList.add(movieListList2Fragment2);
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, new String[]{"Popular", "Mine", "My Collections"});
        this.viewPager.setOffscreenPageLimit(arrayList.size());
        this.viewPager.setAdapter(tabLayoutPagerAdapter);
        this.tabLayout.setViewPager(this.viewPager);
    }

    private static String makeFragmentName(long j) {
        return "android:switcher:2131298397:" + j;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        if (getArguments() == null || !getArguments().getBoolean("fromAdd", false)) {
            return;
        }
        this.rlTitle.setVisibility(8);
    }
}
