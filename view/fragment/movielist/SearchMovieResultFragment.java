package com.movieboxpro.android.view.fragment.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import com.movieboxpro.android.view.fragment.movielist.SearchMovieListFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class SearchMovieResultFragment extends Fragment implements SearchMovieListFragment.OnItemCheckedListener {
    private static final String[] TAB_TITLE = {"All", "Movie", "TV Shows"};
    private TabLayoutPagerAdapter adapter;
    private List<Fragment> fragments;
    @BindView(R.id.search_result_pager)
    ViewPager searchResultPager;
    @BindView(R.id.search_result_tab)
    SmartTabLayout searchResultTab;
    Unbinder unbinder;
    private List<NormalFilmModel> checkFilms = new ArrayList();
    private List<NormalFilmModel> selectedVideos = new ArrayList();
    private String[] types = {TtmlNode.COMBINE_ALL, "movie", "tv"};

    private void initListener() {
    }

    public void setCheckFilms(List<NormalFilmModel> list) {
        this.checkFilms = list;
    }

    public static SearchMovieResultFragment newInstance(String str, ArrayList<NormalFilmModel> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", str);
        bundle.putParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS, arrayList);
        SearchMovieResultFragment searchMovieResultFragment = new SearchMovieResultFragment();
        searchMovieResultFragment.setArguments(bundle);
        return searchMovieResultFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_search_movie_result, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    private void initData() {
        this.selectedVideos = getArguments().getParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS);
        String string = getArguments().getString("keyword");
        this.fragments = new ArrayList();
        for (int i = 0; i < TAB_TITLE.length; i++) {
            SearchMovieListFragment newInstance = SearchMovieListFragment.newInstance(this.types[i], string);
            newInstance.setCheckedFilms(this.checkFilms);
            newInstance.setCheckedListener(this);
            this.fragments.add(newInstance);
        }
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), this.fragments, TAB_TITLE);
        this.adapter = tabLayoutPagerAdapter;
        this.searchResultPager.setAdapter(tabLayoutPagerAdapter);
        this.searchResultPager.setOffscreenPageLimit(this.fragments.size());
        this.searchResultTab.setViewPager(this.searchResultPager);
    }

    public void refreshData(String str) {
        for (int i = 0; i < this.adapter.getCount(); i++) {
            ((SearchMovieListFragment) this.adapter.getItem(i)).refreshData(str);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public List<NormalFilmModel> getCheckedFilms() {
        return this.selectedVideos;
    }

    @Override // com.movieboxpro.android.view.fragment.movielist.SearchMovieListFragment.OnItemCheckedListener
    public void onCheck(int i, int i2, NormalFilmModel normalFilmModel) {
        int indexOf = this.selectedVideos.indexOf(normalFilmModel);
        if (normalFilmModel.isChecked()) {
            if (indexOf == -1) {
                this.selectedVideos.add(normalFilmModel);
            }
            if (i == 1) {
                ((SearchMovieListFragment) this.fragments.get(1)).updateCheckStatus(normalFilmModel);
                ((SearchMovieListFragment) this.fragments.get(2)).updateCheckStatus(normalFilmModel);
                return;
            } else if (i == 2) {
                ((SearchMovieListFragment) this.fragments.get(0)).updateCheckStatus(normalFilmModel);
                return;
            } else if (i != 3) {
                return;
            } else {
                ((SearchMovieListFragment) this.fragments.get(0)).updateCheckStatus(normalFilmModel);
                return;
            }
        }
        if (indexOf != -1) {
            this.selectedVideos.remove(indexOf);
        }
        if (i == 1) {
            ((SearchMovieListFragment) this.fragments.get(1)).updateCheckStatus(normalFilmModel);
            ((SearchMovieListFragment) this.fragments.get(2)).updateCheckStatus(normalFilmModel);
        } else if (i == 2) {
            ((SearchMovieListFragment) this.fragments.get(0)).updateCheckStatus(normalFilmModel);
        } else if (i != 3) {
        } else {
            ((SearchMovieListFragment) this.fragments.get(0)).updateCheckStatus(normalFilmModel);
        }
    }
}
