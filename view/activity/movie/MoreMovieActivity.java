package com.movieboxpro.android.view.activity.movie;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.model.movie.MovieDetailModel;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class MoreMovieActivity extends BaseActivity {
    public static final String MOVIELIST_ORDER = "movielsit_order";
    public static final String MOVIELIST_TITLE = "movielsit_title";
    private MyGridLayoutManager layoutManager;
    private MovieMoreAdapter mAdapter;
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
    private String title;
    private static final String[] SORT_BY = {"RATING", "ADDED", "VIEW", "COLLECT", MainActivity.TAG_DOWNLOAD};
    private static final String[] YEAR = {"ALL", "2018", "2017", "2016", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "OTHERS"};
    private static final List<String> GENER = new ArrayList();
    private static final String[] SORT_BYS = {"rating", FilterVideoDialog.TV_DEFAULT_SORT, "view", "collect", DownloadInfo.DOWNLOAD};
    private static final String[] YEARS = {"0", "2018", "2017", "2016", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "OTHERS"};
    private int sortby = 0;
    private int year = 0;
    private int gener = 0;
    private List<String> GENERS = new ArrayList();
    public List<MovieDetailModel> list = new ArrayList();
    private int page = 1;
    private boolean isLoadingMore = false;
    OnItemClickListener clickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            if (i < 0 || i > MoreMovieActivity.this.mAdapter.getItemCount() - 1) {
                return;
            }
            MovieDetailModel model = MoreMovieActivity.this.mAdapter.getModel(i);
            MoreMovieActivity.this.route(MovieDetailActivity.class, ParamsUtils.newBuilder().addParam(MovieDetailActivity.MOVIE_ID, model.id).addParam("movie_poster", model.poster).build());
        }
    };
    protected RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.2
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i2 > 0) {
                int childCount = MoreMovieActivity.this.layoutManager.getChildCount();
                int itemCount = MoreMovieActivity.this.layoutManager.getItemCount();
                int findFirstVisibleItemPosition = MoreMovieActivity.this.layoutManager.findFirstVisibleItemPosition();
                String str = MoreMovieActivity.this.TAG;
                MLog.d(str, "FAKER" + childCount + "," + itemCount + "," + findFirstVisibleItemPosition + ",");
                if (childCount + findFirstVisibleItemPosition != itemCount || MoreMovieActivity.this.isLoadingMore) {
                    return;
                }
                MoreMovieActivity.access$308(MoreMovieActivity.this);
                MoreMovieActivity.this.isLoadingMore = true;
                MoreMovieActivity.this.setData(false);
            }
        }
    };
    TabLayout.OnTabSelectedListener sortbylistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.6
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            MoreMovieActivity.this.sortby = ((Integer) tab.getTag()).intValue();
            MoreMovieActivity.this.page = 1;
            MoreMovieActivity.this.setData(true);
        }
    };
    TabLayout.OnTabSelectedListener generglistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.7
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            MoreMovieActivity.this.gener = ((Integer) tab.getTag()).intValue();
            MoreMovieActivity.this.page = 1;
            MoreMovieActivity.this.setData(true);
        }
    };
    TabLayout.OnTabSelectedListener yearlistener = new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.8
        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            MoreMovieActivity.this.year = ((Integer) tab.getTag()).intValue();
            MoreMovieActivity.this.page = 1;
            MoreMovieActivity.this.setData(true);
        }
    };

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mCover = (ImageView) Utils.findRequiredViewAsType(view, R.id.movie_item_poster, "field 'mCover'", ImageView.class);
            item1ViewHolder.mDesc = (ImageView) Utils.findRequiredViewAsType(view, R.id.movie_item_desc, "field 'mDesc'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mCover = null;
            item1ViewHolder.mDesc = null;
        }
    }

    static /* synthetic */ int access$308(MoreMovieActivity moreMovieActivity) {
        int i = moreMovieActivity.page;
        moreMovieActivity.page = i + 1;
        return i;
    }

    @OnClick({R.id.recycler_empty_text})
    public void onEmptyClick() {
        if (Network.isConnected(this.context)) {
            getList();
        }
    }

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, MoreMovieActivity.class);
        intent.putExtra("id", str);
        intent.putExtra(MOVIELIST_TITLE, str2);
        context.startActivity(intent);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_movielist, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        String stringParam = getStringParam(MOVIELIST_TITLE, "");
        this.title = stringParam;
        setTitle(stringParam);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            this.mAdapter = new MovieMoreAdapter(this.list);
            MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 15);
            this.layoutManager = myGridLayoutManager;
            this.mRecycler.setLayoutManager(myGridLayoutManager);
            this.layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.3
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (i == MoreMovieActivity.this.list.size()) {
                        return 15;
                    }
                    return MoreMovieActivity.this.isScreenLandscape() ? 3 : 5;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(boolean z) {
        if (z) {
            showLoading();
        }
        APIService aPIService = this.service;
        String str = API.BASE_URL;
        String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
        String str3 = YEARS[this.year];
        int size = this.GENERS.size();
        int i = this.gener;
        String str4 = size > i ? this.GENERS.get(i) : "0";
        String str5 = SORT_BYS[this.sortby];
        aPIService.Movie_list(str, API.Movie.MOVE_LIST, str2, str3, str4, str5, this.page + "", "15", BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.4
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(MoreMovieActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str6) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str6);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    MoreMovieActivity.this.setList(jSONObject.getJSONArray("data"));
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                MoreMovieActivity.this.mAdapter.setNoMoreData(true);
                MoreMovieActivity.this.endLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                MoreMovieActivity.this.endLoading();
            }
        });
    }

    public void setList(JSONArray jSONArray) {
        List javaList = jSONArray.toJavaList(MovieDetailModel.class);
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

    private void getList() {
        if (Network.isConnected(this.context)) {
            this.service.Cat_list(API.BASE_URL, "Cat_list").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.5
                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    String str2 = MoreMovieActivity.this.TAG;
                    MLog.d(str2, "获取列表" + str);
                    JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                    if (jSONObject.getInteger("code").intValue() == 1) {
                        List javaList = jSONObject.getJSONArray("data").toJavaList(Gener.class);
                        for (int i = 0; i < javaList.size(); i++) {
                            MoreMovieActivity.GENER.add(((Gener) javaList.get(i)).getName());
                            List list = MoreMovieActivity.this.GENERS;
                            list.add(i + "");
                        }
                        String stringExtra = MoreMovieActivity.this.getIntent().getStringExtra("id");
                        if (TextUtils.isEmpty(stringExtra)) {
                            return;
                        }
                        for (int i2 = 0; i2 < MoreMovieActivity.this.GENERS.size(); i2++) {
                            if (stringExtra.equals(MoreMovieActivity.this.GENERS.get(i2))) {
                                MoreMovieActivity.this.gener = i2;
                                return;
                            }
                        }
                    }
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    MoreMovieActivity.this.initTabs();
                    MoreMovieActivity.this.setData(true);
                }
            });
        }
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
            if (i4 == this.gener) {
                this.mTabSegment3.addTab(newTab4, i4, true);
            } else {
                this.mTabSegment3.addTab(newTab4, i4);
            }
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }

    /* loaded from: classes3.dex */
    public class MovieMoreAdapter extends BaseAdapter<MovieDetailModel> {
        public MovieMoreAdapter(List<MovieDetailModel> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_movielist_item2, viewGroup, false), onItemClickListener);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x007d, code lost:
            if (r5.equals("4k") != false) goto L13;
         */
        @Override // com.movieboxpro.android.base.BaseAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void setView(com.movieboxpro.android.base.BaseViewHolder r4, int r5) {
            /*
                Method dump skipped, instructions count: 400
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.activity.movie.MoreMovieActivity.MovieMoreAdapter.setView(com.movieboxpro.android.base.BaseViewHolder, int):void");
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            return getModel(i).box_type;
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.movie_item_poster)
        ImageView mCover;
        @BindView(R.id.movie_item_desc)
        ImageView mDesc;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
