package com.movieboxpro.android.view.fragment.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.SearchEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.SearchAllFilterLiveData;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.DoubleClickHelper;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog;
import com.movieboxpro.android.view.dialog.FilterSearchVideoDialog;
import com.movieboxpro.android.view.fragment.SearchFragment;
import com.movieboxpro.android.view.fragment.SearchResultAllFragment;
import com.movieboxpro.android.view.fragment.SearchResultFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class ResultPagerFragment extends BaseFragment {
    public static final String PARAM_KEY_KEYWORD = "keyword";
    public static final String PARAM_KEY_TAB = "tab";
    private SearchResultAllFragment allFragment;
    private ArrayList<FilterCountry> countries;
    private FilterSearchVideoDialog filterSearchVideoDialog;
    private ArrayList<Gener> geners;
    @BindView(R.id.ivFilter)
    ImageView ivFilter;
    private String keyword;
    @BindView(R.id.search_result_pager)
    ViewPager mPager;
    @BindView(R.id.search_result_tab)
    SmartTabLayout mTabs;
    private SearchResultFragment movieFragment;
    private int selectPos;
    private SearchResultFragment tvFragment;
    private String[] TAB_TITLES = {"All", "Movies", "TV Shows"};
    private String[] TAB_TITLE = {TtmlNode.COMBINE_ALL, "movie", "tv"};
    private int type = 0;

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.fragment_search_result, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        this.type = getIntParam(SearchFragment.PARAM_KEY_KEYWORD_TYPE, 0);
        DoubleClickHelper.click(this.ivFilter, new Runnable() { // from class: com.movieboxpro.android.view.fragment.search.-$$Lambda$ResultPagerFragment$NXocsHf6jjlZYFkz4xBXCamOHds
            @Override // java.lang.Runnable
            public final void run() {
                ResultPagerFragment.this.lambda$initView$0$ResultPagerFragment();
            }
        });
        SearchAllFilterLiveData.Companion.get().observe(this, new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.search.ResultPagerFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$ResultPagerFragment() {
        if (this.geners == null || this.countries == null) {
            getFilterData();
        } else {
            showFilterDialog();
        }
    }

    private void getFilterData() {
        Observable map;
        Observable map2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.FILTER_GENER);
        if (string == null) {
            map = Http.getService().Cat_list(API.BASE_URL, "Cat_list").compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$ResultPagerFragment$CHnBHocao40rZXNBg_AbdLwIJ_U.INSTANCE);
        } else {
            map = Observable.just(string).map($$Lambda$ResultPagerFragment$ej5BywerDMkwIdRJ1SUCJOCnVV8.INSTANCE);
        }
        String string2 = PrefsUtils.getInstance().getString("movie_country_list");
        if (string2 == null) {
            map2 = HttpRequest.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$ResultPagerFragment$Rf4oXSx2MyhDxLdyVnQqjFKFIFw.INSTANCE);
        } else {
            map2 = Observable.just(string2).map($$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE.INSTANCE);
        }
        ((ObservableSubscribeProxy) Observable.zip(map, map2, $$Lambda$ResultPagerFragment$H17IeA263sykJYKtRqI_ajQYXKs.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<ArrayList<Gener>, ArrayList<FilterCountry>>>() { // from class: com.movieboxpro.android.view.fragment.search.ResultPagerFragment.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                ResultPagerFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<ArrayList<Gener>, ArrayList<FilterCountry>> pair) {
                ResultPagerFragment.this.hideLoading();
                ResultPagerFragment.this.geners = pair.first;
                ResultPagerFragment.this.countries = pair.second;
                ResultPagerFragment.this.showFilterDialog();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                ResultPagerFragment.this.hideLoading();
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList lambda$getFilterData$2(String str) throws Exception {
        return new ArrayList(JSONObject.parseArray(str, Gener.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Pair lambda$getFilterData$5(ArrayList arrayList, ArrayList arrayList2) throws Exception {
        return new Pair(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFilterDialog() {
        boolean z = this.selectPos == 1;
        boolean z2 = this.selectPos == 0;
        if (this.filterSearchVideoDialog == null) {
            FilterSearchVideoDialog newInstance = FilterSearchVideoDialog.Companion.newInstance(this.geners);
            this.filterSearchVideoDialog = newInstance;
            newInstance.setType(z, z2);
            this.filterSearchVideoDialog.setFilterListener(this.countries, new FilterSearchVideoDialog.OnFilterListener() { // from class: com.movieboxpro.android.view.fragment.search.ResultPagerFragment.3
                @Override // com.movieboxpro.android.view.dialog.FilterSearchVideoDialog.OnFilterListener
                public void onFilter(String str, String str2, String str3, String str4, String str5, String str6) {
                    boolean haveFilter;
                    HashMap hashMap = new HashMap();
                    hashMap.put("year", str);
                    hashMap.put("genre", str2);
                    hashMap.put("sort", str3);
                    hashMap.put("rating", str4);
                    hashMap.put("quality", str5);
                    hashMap.put("country", str6);
                    String jSONString = JSONObject.toJSONString(hashMap);
                    if (ResultPagerFragment.this.selectPos == 0) {
                        ResultPagerFragment.this.allFragment.setFilter(str, str2, str3, str4, str5, str6);
                        haveFilter = ResultPagerFragment.this.allFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.SEARCH_FILTER_ALL, jSONString);
                    } else if (ResultPagerFragment.this.selectPos == 1) {
                        ResultPagerFragment.this.movieFragment.setFilter(str, str2, str3, str4, str5, str6);
                        haveFilter = ResultPagerFragment.this.movieFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.SEARCH_FILTER_MOVIE, jSONString);
                    } else {
                        ResultPagerFragment.this.tvFragment.setFilter(str, str2, str3, str4, str5, str6);
                        haveFilter = ResultPagerFragment.this.tvFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.SEARCH_FILTER_TV, jSONString);
                    }
                    if (haveFilter) {
                        ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                    } else {
                        ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                    }
                }

                @Override // com.movieboxpro.android.view.dialog.FilterSearchVideoDialog.OnFilterListener
                public void typeSelect(int i) {
                    ResultPagerFragment.this.mPager.setCurrentItem(i, false);
                }
            });
        }
        this.filterSearchVideoDialog.setType(z, z2);
        HashMap<String, String> hashMap = new HashMap<>();
        int i = this.selectPos;
        if (i == 0) {
            hashMap = this.allFragment.getFilterData();
        } else if (i == 1) {
            hashMap = this.movieFragment.getFilterData();
        } else if (i == 2) {
            hashMap = this.tvFragment.getFilterData();
        }
        this.filterSearchVideoDialog.setFilterData(hashMap);
        this.filterSearchVideoDialog.show(getChildFragmentManager(), FilterFavoriteVideoDialog.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseFragment
    public void onVisible() {
        super.onVisible();
        if (this.loaded) {
            return;
        }
        initData();
        this.loaded = true;
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        initTabs();
    }

    private void initTabs() {
        initPages();
    }

    private void initPages() {
        if (App.isLogin() && App.getUserData().getHide_tv_movielist() == 0) {
            this.TAB_TITLES = new String[]{"All", "Movies", "TV Shows"};
            this.TAB_TITLE = new String[]{TtmlNode.COMBINE_ALL, "movie", "tv"};
        } else {
            this.TAB_TITLES = new String[]{"All", "Movies"};
            this.TAB_TITLE = new String[]{TtmlNode.COMBINE_ALL, "movie"};
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.TAB_TITLES.length; i++) {
            ParamsUtils.newBuilder().addParam("keyword", this.keyword).addParam(PARAM_KEY_TAB, this.TAB_TITLE[i]).build();
            SearchResultFragment newInstance = SearchResultFragment.Companion.newInstance(this.keyword, this.TAB_TITLE[i]);
            if (i == 0) {
                SearchResultAllFragment newInstance2 = SearchResultAllFragment.Companion.newInstance(this.keyword);
                this.allFragment = newInstance2;
                arrayList.add(newInstance2);
            } else {
                arrayList.add(newInstance);
                if (i == 1) {
                    this.movieFragment = newInstance;
                } else {
                    this.tvFragment = newInstance;
                }
            }
        }
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, this.TAB_TITLES);
        this.mPager.setOffscreenPageLimit(arrayList.size());
        this.mPager.setAdapter(tabLayoutPagerAdapter);
        this.mTabs.setViewPager(this.mPager);
        this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.fragment.search.ResultPagerFragment.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                ResultPagerFragment.this.type = i2;
                ResultPagerFragment.this.selectPos = i2;
                if (ResultPagerFragment.this.selectPos == 0 ? ResultPagerFragment.this.allFragment.haveFilter() : ResultPagerFragment.this.selectPos == 1 ? ResultPagerFragment.this.movieFragment.haveFilter() : ResultPagerFragment.this.selectPos == 2 ? ResultPagerFragment.this.tvFragment.haveFilter() : false) {
                    ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    ResultPagerFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
            }
        });
    }

    public void setKeyWord(String str) {
        this.keyword = str;
        String str2 = this.TAG;
        MLog.d(str2, "setKeyWord: keyword: " + str);
        if (this.loaded) {
            EventBus.getDefault().post(new SearchEvent(str, this.TAB_TITLE[this.type]));
        }
    }

    public String getKeyword() {
        return this.keyword;
    }
}
