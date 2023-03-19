package com.movieboxpro.android.view.fragment.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.model.tv.TvDetailModel;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class TvMoreFragment extends BaseFragment {
    private MyGridLayoutManager layoutManager;
    private TvMoreAdapter mAdapter;
    @BindView(R.id.barlayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.recycler_empty_text)
    TextView mEmptyText;
    @BindView(R.id.recycler_empty)
    ConstraintLayout mEmptyView;
    @BindView(R.id.contentViewPager)
    MyRecyclerView mRecycler;
    @BindView(R.id.tabSegment)
    TabLayout mTabSegment;
    @BindView(R.id.tabSegment1)
    TabLayout mTabSegment1;
    @BindView(R.id.tabSegment2)
    TabLayout mTabSegment2;
    @BindView(R.id.tabSegment3)
    TabLayout mTabSegment3;
    private static final String[] SORT_BY = {"UPDATE", "ADDED", "VIEW", "COLLECT", MainActivity.TAG_DOWNLOAD};
    private static final String[] YEAR = {"ALL", "2019", "2018", "2017", "2016", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "OTHERS"};
    private static final List<String> GENER = new ArrayList();
    private static final String[] SORT_BYS = {"update", "add", "collect", "view", DownloadInfo.DOWNLOAD};
    private static final String[] YEARS = {"0", "2019", "2018", "2017", "2016", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "OTHERS"};
    private static final List<String> GENERS = new ArrayList();
    private int sortby = 0;
    private int year = 0;
    private int gener = 0;
    public List<TvDetailModel> list = new ArrayList();
    private int page = 1;
    private boolean isLoadingMore = false;
    OnItemClickListener clickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            if (i < 0 || i > TvMoreFragment.this.mAdapter.getItemCount() - 1) {
                return;
            }
            TvDetailModel model = TvMoreFragment.this.mAdapter.getModel(i);
            TvMoreFragment.this.route(TvDetailActivity.class, ParamsUtils.newBuilder().addParam(TvDetailActivity.TV_TID, model.id).addParam("tv_poster", model.poster).addParam(TvDetailActivity.TV_BANNER, model.banner_mini).build(), ((Item1ViewHolder) TvMoreFragment.this.mRecycler.findViewHolderForPosition(i)).getImageView(), "tv_poster");
        }
    };
    protected RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.2
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i2 > 0) {
                int childCount = TvMoreFragment.this.layoutManager.getChildCount();
                int itemCount = TvMoreFragment.this.layoutManager.getItemCount();
                int findFirstVisibleItemPosition = TvMoreFragment.this.layoutManager.findFirstVisibleItemPosition();
                String str = TvMoreFragment.this.TAG;
                MLog.d(str, "FAKER" + childCount + "," + itemCount + "," + findFirstVisibleItemPosition + ",");
                if (childCount + findFirstVisibleItemPosition != itemCount || TvMoreFragment.this.isLoadingMore) {
                    return;
                }
                TvMoreFragment.access$508(TvMoreFragment.this);
                TvMoreFragment.this.isLoadingMore = true;
                TvMoreFragment.this.setData(false);
            }
        }
    };
    TabLayout.OnTabSelectedListener sortbylistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.6
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            TvMoreFragment.this.sortby = ((Integer) tab.getTag()).intValue();
            TvMoreFragment.this.page = 1;
            TvMoreFragment.this.setData(true);
        }
    };
    TabLayout.OnTabSelectedListener generglistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.7
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            TvMoreFragment.this.gener = ((Integer) tab.getTag()).intValue();
            TvMoreFragment.this.page = 1;
            TvMoreFragment.this.setData(true);
        }
    };
    TabLayout.OnTabSelectedListener yearlistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.8
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            TvMoreFragment.this.year = ((Integer) tab.getTag()).intValue();
            TvMoreFragment.this.page = 1;
            TvMoreFragment.this.setData(true);
        }
    };

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_item_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mSeason = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_item_season, "field 'mSeason'", TextView.class);
            item1ViewHolder.mDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_item_desc, "field 'mDesc'", TextView.class);
            item1ViewHolder.mGroup = (Group) Utils.findRequiredViewAsType(view, R.id.group, "field 'mGroup'", Group.class);
            item1ViewHolder.mTri = (SlantedTextView) Utils.findRequiredViewAsType(view, R.id.slv_right_text, "field 'mTri'", SlantedTextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
            item1ViewHolder.mSeason = null;
            item1ViewHolder.mDesc = null;
            item1ViewHolder.mGroup = null;
            item1ViewHolder.mTri = null;
        }
    }

    static /* synthetic */ int access$508(TvMoreFragment tvMoreFragment) {
        int i = tvMoreFragment.page;
        tvMoreFragment.page = i + 1;
        return i;
    }

    @OnClick({R.id.recycler_empty_text})
    public void onEmptyClick() {
        if (Network.isConnected(this.context)) {
            setData(true);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_movielist, viewGroup, false);
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
        if (this.mAdapter == null) {
            this.mAdapter = new TvMoreAdapter(this.list);
            MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 8);
            this.layoutManager = myGridLayoutManager;
            this.mRecycler.setLayoutManager(myGridLayoutManager);
            this.layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.3
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (i == TvMoreFragment.this.list.size()) {
                        return 8;
                    }
                    return TvMoreFragment.this.isScreenLandscape() ? 2 : 4;
                }
            });
            this.mAdapter.setShowFooter(true);
            this.mRecycler.setEmptyView(this.mEmptyView);
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mRecycler.addOnScrollListener(this.scrollListener);
            this.mAdapter.setListener(this.clickListener);
        }
        getList();
    }

    private void getList() {
        if (Network.isConnected(this.context)) {
            this.service.Cat_list(API.BASE_URL, "Cat_list").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.4
                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    String str2 = TvMoreFragment.this.TAG;
                    MLog.d(str2, "获取列表" + str);
                    JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                    if (jSONObject.getInteger("code").intValue() == 1) {
                        List javaList = jSONObject.getJSONArray("data").toJavaList(Gener.class);
                        for (int i = 0; i < javaList.size(); i++) {
                            TvMoreFragment.GENER.add(((Gener) javaList.get(i)).getName());
                            List list = TvMoreFragment.GENERS;
                            list.add(i + "");
                        }
                    }
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    TvMoreFragment.this.initTabs();
                }
            });
            setData(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(boolean z) {
        if (z) {
            showLoading();
        }
        APIService aPIService = this.service;
        String str = API.BASE_URL;
        String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
        String str3 = YEARS[this.year];
        int size = GENERS.size();
        int i = this.gener;
        String str4 = size > i ? GENERS.get(i) : "0";
        String str5 = SORT_BYS[this.sortby];
        aPIService.Movie_list(str, API.Tv.TV_LIST, str2, str3, str4, str5, this.page + "", "15", BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.home.TvMoreFragment.5
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(TvMoreFragment.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str6) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str6);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    TvMoreFragment.this.setList(jSONObject.getJSONArray("data"));
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                TvMoreFragment.this.mAdapter.setNoMoreData(true);
                TvMoreFragment.this.endLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                TvMoreFragment.this.endLoading();
            }
        });
    }

    public void setList(JSONArray jSONArray) {
        List javaList = jSONArray.toJavaList(TvDetailModel.class);
        if (!this.isLoadingMore || this.page == 1) {
            this.list.clear();
        }
        this.list.addAll(javaList);
        if (this.mAdapter.isShowFooter()) {
            if (javaList.size() < 15) {
                this.mAdapter.setNoMoreData(true);
            } else {
                this.mAdapter.setNoMoreData(false);
            }
        }
        this.mAdapter.notifyDataSetChanged();
        this.isLoadingMore = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endLoading() {
        hideLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTabs() {
        for (int i = 0; i < SORT_BY.length; i++) {
            TabLayout.Tab newTab = this.mTabSegment.newTab();
            newTab.setText(SORT_BY[i]);
            newTab.setTag(Integer.valueOf(i));
            this.mTabSegment.addTab(newTab, i);
        }
        for (int i2 = 0; i2 < SORT_BY.length; i2++) {
            TabLayout.Tab newTab2 = this.mTabSegment1.newTab();
            newTab2.setText(SORT_BY[i2]);
            newTab2.setTag(Integer.valueOf(i2));
            this.mTabSegment1.addTab(newTab2, i2);
        }
        for (int i3 = 0; i3 < YEAR.length; i3++) {
            TabLayout.Tab newTab3 = this.mTabSegment2.newTab();
            newTab3.setText(YEAR[i3]);
            newTab3.setTag(Integer.valueOf(i3));
            this.mTabSegment2.addTab(newTab3, i3);
        }
        for (int i4 = 0; i4 < GENER.size(); i4++) {
            TabLayout.Tab newTab4 = this.mTabSegment3.newTab();
            newTab4.setText(GENER.get(i4));
            newTab4.setTag(Integer.valueOf(i4));
            this.mTabSegment3.addTab(newTab4, i4);
        }
        this.mTabSegment.setTabMode(0);
        this.mTabSegment1.setTabMode(0);
        this.mTabSegment2.setTabMode(0);
        this.mTabSegment3.setTabMode(0);
        this.mTabSegment.addOnTabSelectedListener(this.sortbylistener);
        this.mTabSegment1.addOnTabSelectedListener(this.sortbylistener);
        this.mTabSegment2.addOnTabSelectedListener(this.yearlistener);
        this.mTabSegment3.addOnTabSelectedListener(this.generglistener);
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroyView();
    }

    /* loaded from: classes3.dex */
    public class TvMoreAdapter extends BaseAdapter<TvDetailModel> {
        public TvMoreAdapter(List<TvDetailModel> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_tvlist_item_2, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            TvDetailModel model = getModel(i);
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
            if (TvMoreFragment.this.activity != null && !TvMoreFragment.this.activity.isFinishing()) {
                GlideApp.with(TvMoreFragment.this.activity).load(model.poster).placeholder((int) R.drawable.ic_default).into(item1ViewHolder.mCover);
            }
            item1ViewHolder.mSeason.setVisibility(TextUtils.isEmpty(model.season_episode) ? 8 : 0);
            item1ViewHolder.mSeason.setText(model.season_episode);
            if (model.update_title != null && !TextUtils.isEmpty(model.update_title)) {
                item1ViewHolder.mGroup.setVisibility(0);
                item1ViewHolder.mTri.setText(model.update_title);
                return;
            }
            item1ViewHolder.mGroup.setVisibility(8);
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_item_poster)
        ImageView mCover;
        @BindView(R.id.tv_item_desc)
        TextView mDesc;
        @BindView(R.id.group)
        Group mGroup;
        @BindView(R.id.tv_item_season)
        TextView mSeason;
        @BindView(R.id.slv_right_text)
        SlantedTextView mTri;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }

        public ImageView getImageView() {
            return this.mCover;
        }
    }
}
