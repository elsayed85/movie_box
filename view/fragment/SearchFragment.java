package com.movieboxpro.android.view.fragment;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.model.search.SearchWordList;
import com.movieboxpro.android.utils.CacheDiskUtils;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.search.KeyWordFragment;
import com.movieboxpro.android.view.fragment.search.ResultPagerFragment;
import com.movieboxpro.android.view.listener.OnKeyWordSelectListener;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public class SearchFragment extends BaseFragment implements OnKeyWordSelectListener {
    private static final int DEFAULT_HISTORY_COUNT = 2;
    public static final int KEYWORD_ALL = 0;
    public static final int KEYWORD_BOOK_AUTHOR = 4;
    public static final int KEYWORD_NOVEL_AUTHOR = 3;
    public static final String PARAM_KEY_KEYWORD = "keyword";
    public static final String PARAM_KEY_KEYWORD_TYPE = "keyword_type";
    private String currKeyword;
    private KeyWordFragment keywordFragment;
    private HotSearchAdapter mAdapter;
    @BindView(R.id.activity_search_frame)
    FrameLayout mFrame;
    @BindView(R.id.activity_search_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.search_bar_clear)
    ImageView mSearchClear;
    @BindView(R.id.search_bar_edit)
    EditText mSearchEdit;
    private ResultPagerFragment resultFragment;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    private boolean isKeywordShow = false;
    private boolean isResultShow = false;
    private List<SearchWordList> HotWord = new ArrayList();
    private List<SearchWordList> historyList = new ArrayList();
    private List<SearchWordList> hotList = new ArrayList();

    /* loaded from: classes3.dex */
    interface OnItemClickListener2 extends OnItemClickListener {
        void onBtnClick(int i);

        void onDelete(int i);
    }

    /* loaded from: classes3.dex */
    public class HeaderViewHolder_ViewBinding implements Unbinder {
        private HeaderViewHolder target;

        public HeaderViewHolder_ViewBinding(HeaderViewHolder headerViewHolder, View view) {
            this.target = headerViewHolder;
            headerViewHolder.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.search_header_title, "field 'mTitle'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            HeaderViewHolder headerViewHolder = this.target;
            if (headerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            headerViewHolder.mTitle = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.search_item_title, "field 'mTitle'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mTitle = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item3ViewHolder_ViewBinding implements Unbinder {
        private Item3ViewHolder target;

        public Item3ViewHolder_ViewBinding(Item3ViewHolder item3ViewHolder, View view) {
            this.target = item3ViewHolder;
            item3ViewHolder.mBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.search_item3_btn, "field 'mBtn'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item3ViewHolder item3ViewHolder = this.target;
            if (item3ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item3ViewHolder.mBtn = null;
        }
    }

    /* loaded from: classes3.dex */
    public class ItemAdViewHolder_ViewBinding implements Unbinder {
        private ItemAdViewHolder target;

        public ItemAdViewHolder_ViewBinding(ItemAdViewHolder itemAdViewHolder, View view) {
            this.target = itemAdViewHolder;
            itemAdViewHolder.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frameLayout, "field 'frameLayout'", FrameLayout.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemAdViewHolder itemAdViewHolder = this.target;
            if (itemAdViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemAdViewHolder.frameLayout = null;
        }
    }

    /* loaded from: classes3.dex */
    public class Item2ViewHolder_ViewBinding implements Unbinder {
        private Item2ViewHolder target;

        public Item2ViewHolder_ViewBinding(Item2ViewHolder item2ViewHolder, View view) {
            this.target = item2ViewHolder;
            item2ViewHolder.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.search_item2_title, "field 'mTitle'", TextView.class);
            item2ViewHolder.mDelete = (ImageView) Utils.findRequiredViewAsType(view, R.id.search_item2_delete, "field 'mDelete'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item2ViewHolder item2ViewHolder = this.target;
            if (item2ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item2ViewHolder.mTitle = null;
            item2ViewHolder.mDelete = null;
        }
    }

    @OnClick({R.id.search_bar_clear})
    public void clearKeyWord(View view) {
        this.mSearchEdit.setText("");
        this.mSearchEdit.requestFocus();
        showKeyBoard();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        this.keywordFragment = (KeyWordFragment) getChildFragmentManager().findFragmentByTag(KeyWordFragment.class.getSimpleName());
        this.resultFragment = (ResultPagerFragment) getChildFragmentManager().findFragmentByTag(ResultPagerFragment.class.getSimpleName());
        this.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#464646"));
        this.swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFDD00"));
        this.mSearchEdit.requestFocus();
        this.mSearchEdit.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    SearchFragment.this.mSearchClear.setVisibility(0);
                    if (SearchFragment.this.isResultShow) {
                        return;
                    }
                    SearchFragment.this.showKeyords(editable.toString());
                    return;
                }
                SearchFragment.this.mSearchClear.setVisibility(4);
                if (SearchFragment.this.isKeywordShow) {
                    SearchFragment.this.hideKeywords();
                }
                if (SearchFragment.this.isResultShow) {
                    SearchFragment.this.hideResult();
                }
            }
        });
        this.mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchFragment$R_Gi2reMYoJ_3FYWoA2XAu0OhI8
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return SearchFragment.this.lambda$initView$0$SearchFragment(textView, i, keyEvent);
            }
        });
        this.mSearchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchFragment$ojfH49Ov9QZUE7cEl0beALXKnO8
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchFragment.this.lambda$initView$1$SearchFragment(view, z);
            }
        });
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$SearchFragment$UPq-9I5bDwcXVT3-qiAfG5IYK0M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchFragment.this.lambda$initView$2$SearchFragment(view);
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$zb9AJjhBbN4LysOXE6oQrkFn604
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                SearchFragment.this.loadData();
            }
        });
    }

    public /* synthetic */ boolean lambda$initView$0$SearchFragment(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            String trim = this.mSearchEdit.getText().toString().trim();
            this.currKeyword = trim;
            onKeyWordSelected(trim);
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$initView$1$SearchFragment(View view, boolean z) {
        if (z) {
            showHistory();
            hideResult();
            this.tvCancel.setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$initView$2$SearchFragment(View view) {
        showTrends();
        this.tvCancel.setVisibility(8);
        this.mSearchEdit.clearFocus();
        if (this.mFrame.getVisibility() == 0) {
            this.mFrame.setVisibility(8);
        }
        this.mSearchEdit.setText("");
        this.mSearchClear.setVisibility(8);
        InputMethodUtils.hideSoftInput(this.mSearchEdit);
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
        loadData();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            this.mAdapter = new HotSearchAdapter(this.HotWord);
            if (getContext() != null) {
                if (CommonUtils.isTablet()) {
                    MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 4);
                    this.mRecycler.setLayoutManager(myGridLayoutManager);
                    myGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.2
                        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                        public int getSpanSize(int i) {
                            int itemViewType = SearchFragment.this.mAdapter.getItemViewType(i);
                            return (itemViewType == 0 || itemViewType == 2 || itemViewType == 3 || itemViewType == 6) ? 4 : 1;
                        }
                    });
                    this.mRecycler.setLayoutManager(myGridLayoutManager);
                } else {
                    MyGridLayoutManager myGridLayoutManager2 = new MyGridLayoutManager(this.context, 2);
                    this.mRecycler.setLayoutManager(myGridLayoutManager2);
                    myGridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.3
                        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                        public int getSpanSize(int i) {
                            int itemViewType = SearchFragment.this.mAdapter.getItemViewType(i);
                            return (itemViewType == 0 || itemViewType == 2 || itemViewType == 3 || itemViewType == 6) ? 2 : 1;
                        }
                    });
                    this.mRecycler.setLayoutManager(myGridLayoutManager2);
                }
            }
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(new OnItemClickListener2() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.4
                @Override // com.movieboxpro.android.view.fragment.SearchFragment.OnItemClickListener2
                public void onDelete(int i) {
                    if (i < SearchFragment.this.historyList.size()) {
                        SearchFragment.this.generateData();
                        SearchFragment.this.deleteData(((SearchWordList) SearchFragment.this.historyList.remove(i)).id);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.SearchFragment.OnItemClickListener2
                public void onBtnClick(int i) {
                    if (!SearchFragment.this.mAdapter.isAllShow()) {
                        SearchFragment.this.mAdapter.setAllShow(true);
                        SearchFragment.this.generateData();
                        return;
                    }
                    SearchFragment.this.historyList.clear();
                    SearchFragment.this.mAdapter.setAllShow(false);
                    SearchFragment.this.generateData();
                    SearchFragment.this.deleteData("");
                }

                @Override // com.movieboxpro.android.base.OnItemClickListener
                public void onItemClick(int i) {
                    if (i < 0 || i > SearchFragment.this.mAdapter.getItemCount() - 1) {
                        return;
                    }
                    SearchFragment.this.tvCancel.setVisibility(0);
                    SearchWordList model = SearchFragment.this.mAdapter.getModel(i);
                    if (model.viewType != 0) {
                        SearchFragment.this.handleHistory(model.KeyWord);
                    }
                }
            });
        }
        if (App.getUserData().getIsvip() != 1) {
            initAd();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent loginEvent) {
        if (this.resultFragment != null) {
            getChildFragmentManager().beginTransaction().remove(this.resultFragment).commitAllowingStateLoss();
            ResultPagerFragment resultPagerFragment = new ResultPagerFragment();
            this.resultFragment = resultPagerFragment;
            resultPagerFragment.setName("搜索结果");
            this.resultFragment.setAddIn(true);
            this.resultFragment.setArguments(ParamsUtils.newBuilder().addParam(PARAM_KEY_KEYWORD_TYPE, getIntParam(PARAM_KEY_KEYWORD_TYPE, 0)).build());
            getChildFragmentManager().beginTransaction().add(R.id.activity_search_frame, this.resultFragment, ResultPagerFragment.class.getSimpleName()).commitAllowingStateLoss();
            this.resultFragment.setKeyWord(this.currKeyword);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        if (this.resultFragment != null) {
            getChildFragmentManager().beginTransaction().remove(this.resultFragment).commitAllowingStateLoss();
            ResultPagerFragment resultPagerFragment = new ResultPagerFragment();
            this.resultFragment = resultPagerFragment;
            resultPagerFragment.setName("搜索结果");
            this.resultFragment.setAddIn(true);
            this.resultFragment.setArguments(ParamsUtils.newBuilder().addParam(PARAM_KEY_KEYWORD_TYPE, getIntParam(PARAM_KEY_KEYWORD_TYPE, 0)).build());
            getChildFragmentManager().beginTransaction().add(R.id.activity_search_frame, this.resultFragment, ResultPagerFragment.class.getSimpleName()).commitAllowingStateLoss();
            this.resultFragment.setKeyWord(this.currKeyword);
        }
    }

    private void initAd() {
        getContext();
    }

    private int getStarRatingRes(Double d) {
        if (d.doubleValue() >= 5.0d) {
            return R.mipmap.stars_5;
        }
        if (d.doubleValue() >= 4.5d) {
            return R.mipmap.stars_4_5;
        }
        if (d.doubleValue() >= 4.0d) {
            return R.mipmap.stars_4;
        }
        if (d.doubleValue() >= 3.5d) {
            return R.mipmap.stars_3_5;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteData(String str) {
        ((ObservableSubscribeProxy) this.service.MySearch_Record(API.BASE_URL, API.Search.MY_SEARCH_RECORD, "del", App.isLogin() ? App.getUserData().uid_v2 : "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", str, "1.0").subscribeOn(Schedulers.io()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.5
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    public void loadData() {
        ((ObservableSubscribeProxy) Observable.zip(this.service.MySearch_Record(API.BASE_URL, API.Search.MY_SEARCH_RECORD, "get", App.isLogin() ? App.getUserData().uid_v2 : "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", "", "1.0").compose(RxUtils.rxTranslate2List(SearchWordList.class)).map($$Lambda$SearchFragment$YDVsJpdXtTOje7cxKm0u0iv0s.INSTANCE), this.service.Search_Hot(API.BASE_URL, API.Search.SEARCH_HOT, "movie", "10").doOnNext($$Lambda$SearchFragment$p1yOvQkVNd29IGYG__ZR33yx3AI.INSTANCE).onErrorReturn($$Lambda$SearchFragment$Yai3NicLxEUEMFyidO_RLHFuRw.INSTANCE).compose(RxUtils.rxTranslate2List(String.class)).map($$Lambda$SearchFragment$tUB1tDDP2ekVGf2jX9w0mvPGmw.INSTANCE), $$Lambda$SearchFragment$YAi2EefNbbVrqIEA3BeXY1FkMU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Pair<List<SearchWordList>, List<SearchWordList>>>() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.6
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                SearchFragment.this.swipeRefreshLayout.setRefreshing(true);
            }

            @Override // io.reactivex.Observer
            public void onNext(Pair<List<SearchWordList>, List<SearchWordList>> pair) {
                SearchFragment.this.historyList.clear();
                if (pair.first != null) {
                    SearchFragment.this.historyList.addAll(pair.first);
                }
                SearchFragment.this.hotList.clear();
                if (pair.second != null) {
                    SearchFragment.this.hotList.addAll(pair.second);
                }
                if (SearchFragment.this.tvCancel.getVisibility() == 8) {
                    SearchFragment.this.showTrends();
                } else {
                    SearchFragment.this.showHistory();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                SearchFragment.this.swipeRefreshLayout.setRefreshing(false);
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                SearchFragment.this.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$loadData$3(List list) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SearchWordList searchWordList = (SearchWordList) it.next();
            searchWordList.viewType = 2;
            searchWordList.KeyWord = searchWordList.keyword;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$loadData$5(Throwable th) throws Exception {
        ToastUtils.showShort("Load failed:" + th.getMessage());
        String string = CacheDiskUtils.getInstance().getString("SEARCH_HOT", "");
        if (TextUtils.isEmpty(string)) {
            throw new ApiException(th, -1);
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$loadData$6(List list) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new SearchWordList(1, (String) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Pair lambda$loadData$7(List list, List list2) throws Exception {
        return new Pair(list, list2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTrends() {
        this.HotWord.clear();
        if (!this.hotList.isEmpty()) {
            this.HotWord.add(new SearchWordList(0, "Trends"));
            this.HotWord.addAll(this.hotList);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHistory() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
            return;
        }
        this.HotWord.clear();
        if (this.historyList != null) {
            for (int i = 0; i < this.historyList.size(); i++) {
                this.HotWord.add(i, this.historyList.get(i));
            }
            if (this.historyList.size() > 0) {
                this.HotWord.add(new SearchWordList(3, null));
            }
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateData() {
        this.HotWord.clear();
        if (this.historyList.size() > 2) {
            if (!this.mAdapter.isAllShow()) {
                for (int i = 0; i < 2; i++) {
                    this.HotWord.add(i, this.historyList.get(i));
                }
                this.HotWord.add(2, new SearchWordList(3, null));
            } else {
                this.HotWord.addAll(this.historyList);
                this.HotWord.add(new SearchWordList(3, null));
            }
        } else {
            for (int i2 = 0; i2 < this.historyList.size(); i2++) {
                this.HotWord.add(i2, this.historyList.get(i2));
            }
            this.HotWord.add(new SearchWordList(3, null));
        }
        if (!this.hotList.isEmpty()) {
            this.HotWord.add(new SearchWordList(0, "Trends"));
            this.HotWord.addAll(this.hotList);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private void showResult(String str) {
        this.currKeyword = str;
        hideKeyBoard();
        if (this.resultFragment == null) {
            ResultPagerFragment resultPagerFragment = new ResultPagerFragment();
            this.resultFragment = resultPagerFragment;
            resultPagerFragment.setName("搜索结果");
            this.resultFragment.setAddIn(true);
            this.resultFragment.setArguments(ParamsUtils.newBuilder().addParam(PARAM_KEY_KEYWORD_TYPE, getIntParam(PARAM_KEY_KEYWORD_TYPE, 0)).build());
            getChildFragmentManager().beginTransaction().add(R.id.activity_search_frame, this.resultFragment, ResultPagerFragment.class.getSimpleName()).commit();
        }
        this.resultFragment.setKeyWord(str);
        if (this.isResultShow) {
            return;
        }
        this.mFrame.setVisibility(0);
        getChildFragmentManager().beginTransaction().show(this.resultFragment).commit();
        this.isResultShow = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideResult() {
        if (this.isResultShow) {
            getChildFragmentManager().beginTransaction().hide(this.resultFragment).commit();
            this.mFrame.setVisibility(8);
            this.isResultShow = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKeyords(String str) {
        if (this.keywordFragment == null) {
            KeyWordFragment keyWordFragment = new KeyWordFragment();
            this.keywordFragment = keyWordFragment;
            keyWordFragment.setName("关键词");
            this.keywordFragment.setAddIn(true);
            this.keywordFragment.setListener(this);
            getChildFragmentManager().beginTransaction().add(R.id.activity_search_frame, this.keywordFragment, KeyWordFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
        this.keywordFragment.setKeyword(str);
        if (this.isKeywordShow) {
            return;
        }
        this.mFrame.setVisibility(0);
        getChildFragmentManager().beginTransaction().show(this.keywordFragment).commitAllowingStateLoss();
        this.isKeywordShow = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideKeywords() {
        if (this.isKeywordShow) {
            getChildFragmentManager().beginTransaction().hide(this.keywordFragment).commitAllowingStateLoss();
            this.mFrame.setVisibility(8);
            this.isKeywordShow = false;
        }
    }

    private void showKeyBoard() {
        InputMethodUtils.showSoftInput(this.activity);
    }

    private void hideKeyBoard() {
        InputMethodUtils.hideSoftInput(this.activity);
    }

    @Override // com.movieboxpro.android.base.BaseFragment
    public boolean onBackPressed() {
        if (this.isKeywordShow || this.isResultShow) {
            this.mSearchEdit.setText("");
            this.mSearchEdit.requestFocus();
            showKeyBoard();
            return true;
        }
        return super.onBackPressed();
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.listener.OnKeyWordSelectListener
    public void onKeyWordSelected(String str) {
        handleHistory(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHistory(String str) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.historyList.size()) {
                z = false;
                break;
            } else if (this.historyList.get(i).KeyWord.equals(str)) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            this.historyList.add(0, new SearchWordList(2, str));
        } else {
            this.historyList.add(0, this.historyList.remove(i));
        }
        hideKeywords();
        showResult(str);
        this.mSearchEdit.setText(str);
        this.mSearchEdit.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class HotSearchAdapter extends BaseAdapter<SearchWordList> {
        private boolean isAllShow;

        HotSearchAdapter(List<SearchWordList> list) {
            super(list);
            this.isAllShow = true;
        }

        boolean isAllShow() {
            return this.isAllShow;
        }

        void setAllShow(boolean z) {
            this.isAllShow = true;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            BaseViewHolder headerViewHolder;
            if (i == 0) {
                headerViewHolder = new HeaderViewHolder(layoutInflater.inflate(R.layout.layout_search_header, viewGroup, false), onItemClickListener);
            } else if (i == 1) {
                headerViewHolder = new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_search_item1, viewGroup, false), onItemClickListener);
            } else if (i == 2) {
                headerViewHolder = new Item2ViewHolder(layoutInflater.inflate(R.layout.layout_search_item2, viewGroup, false), onItemClickListener);
            } else if (i == 3) {
                headerViewHolder = new Item3ViewHolder(layoutInflater.inflate(R.layout.layout_search_item3, viewGroup, false), onItemClickListener);
            } else if (i != 6) {
                return null;
            } else {
                headerViewHolder = new ItemAdViewHolder(layoutInflater.inflate(R.layout.framelayout_layout, viewGroup, false), onItemClickListener);
            }
            return headerViewHolder;
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            SearchWordList model = getModel(i);
            int type = getType(i);
            if (type == 0) {
                ((HeaderViewHolder) baseViewHolder).mTitle.setText(model.KeyWord);
            } else if (type == 1) {
                ((Item1ViewHolder) baseViewHolder).mTitle.setText(model.KeyWord);
            } else if (type == 2) {
                ((Item2ViewHolder) baseViewHolder).mTitle.setText(model.KeyWord);
            } else if (type == 3) {
                ((Item3ViewHolder) baseViewHolder).mBtn.setText("Clear Search History");
            } else if (type != 6) {
            } else {
                ((ItemAdViewHolder) baseViewHolder).frameLayout.removeAllViews();
            }
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public int getType(int i) {
            return getModel(i).viewType;
        }
    }

    /* loaded from: classes3.dex */
    static class HeaderViewHolder extends BaseViewHolder {
        @BindView(R.id.search_header_title)
        TextView mTitle;

        HeaderViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.search_item_title)
        TextView mTitle;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mTitle.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.Item1ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (Item1ViewHolder.this.listener == null || (adapterPosition = Item1ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    Item1ViewHolder.this.listener.onItemClick(adapterPosition);
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    static class Item2ViewHolder extends BaseViewHolder {
        @BindView(R.id.search_item2_delete)
        ImageView mDelete;
        @BindView(R.id.search_item2_title)
        TextView mTitle;

        Item2ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.Item2ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item2ViewHolder.this.listener instanceof OnItemClickListener2) || (adapterPosition = Item2ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnItemClickListener2) Item2ViewHolder.this.listener).onDelete(adapterPosition);
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    static class Item3ViewHolder extends BaseViewHolder {
        @BindView(R.id.search_item3_btn)
        TextView mBtn;

        Item3ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            this.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.Item3ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    int adapterPosition;
                    if (!(Item3ViewHolder.this.listener instanceof OnItemClickListener2) || (adapterPosition = Item3ViewHolder.this.getAdapterPosition()) == -1) {
                        return;
                    }
                    ((OnItemClickListener2) Item3ViewHolder.this.listener).onBtnClick(adapterPosition);
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    static class ItemAdViewHolder extends BaseViewHolder {
        @BindView(R.id.frameLayout)
        FrameLayout frameLayout;

        ItemAdViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
