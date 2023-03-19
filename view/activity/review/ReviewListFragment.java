package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.BaseLoadMoreAdapter;
import com.movieboxpro.android.adapter.BaseLoadmoreDelegateMultiAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.widget.PandaForumLoadMoreView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes.dex */
public abstract class ReviewListFragment<T, P> extends BaseLazyFragment {
    private View emptyView;
    private ViewStub emptyViewStub;
    private View errorView;
    private ViewStub errorViewStub;
    private Disposable lastDisposable;
    private BaseLoadMoreModule loadMoreModule;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    protected Class<P> mPageClass;
    protected RecyclerView mRecyclerView;
    private View pandaForumView;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected int mCurrentPage = 1;
    protected int mPageSize = 10;
    protected int mPreLoadNum = 9;

    private void enableRefresh(boolean z) {
    }

    private void initView() {
    }

    protected List<View> addHeaderView(RecyclerView recyclerView) {
        return null;
    }

    protected RecyclerView.ItemDecoration addItemDecoration() {
        return null;
    }

    protected void addOnItemClickViews(BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter) {
    }

    protected void doSomethingWithData(List<T> list) {
    }

    protected boolean enableEventBus() {
        return false;
    }

    protected boolean enableMultiAdapter() {
        return false;
    }

    protected abstract void getBundle(Bundle bundle);

    protected int getCurrPage(T t) {
        return 0;
    }

    protected List<T> getData(P p) {
        return null;
    }

    protected abstract Observable<String> getServiceData();

    protected int gridLayoutSpan() {
        return 2;
    }

    protected void initEmptyView(TextView textView, ImageView imageView) {
    }

    protected abstract void initHolder(BaseViewHolder baseViewHolder, T t);

    protected abstract int initItemLayout();

    protected int initItemType(T t) {
        return 0;
    }

    protected void initRecyclerView(RecyclerView recyclerView) {
    }

    protected boolean isNeedLazyLoad() {
        return true;
    }

    protected boolean isNeedLoadDataAuto() {
        return true;
    }

    protected boolean isNeedLogin() {
        return false;
    }

    protected boolean isNeedTest() {
        return false;
    }

    protected boolean isOpenLoadMore() {
        return true;
    }

    protected boolean isOpenRefresh() {
        return true;
    }

    protected boolean isOpenUpFetch() {
        return true;
    }

    protected boolean isVerticalLayout() {
        return true;
    }

    protected void onFirstLoadComplete() {
    }

    protected OnItemChildClickListener onItemChildClick() {
        return null;
    }

    protected OnItemClickListener onItemClick() {
        return null;
    }

    protected void onLoadComplete() {
    }

    protected void onLoadDataComplete(List list) {
    }

    protected OnItemLongClickListener onLongClick() {
        return null;
    }

    protected void onRequestLoadMoreComplete(List<T> list) {
    }

    protected void registerItemType(BaseMultiTypeDelegate<T> baseMultiTypeDelegate) {
    }

    protected int setEmptyLayoutId() {
        return -1;
    }

    protected void setPageInfo(P p) {
    }

    protected void test() {
    }

    protected void viewAttachedToWindow(BaseViewHolder baseViewHolder) {
    }

    protected void viewDetachedFromWindow(BaseViewHolder baseViewHolder) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.fragment_forum_recycler_layout, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.emptyViewStub = (ViewStub) inflate.findViewById(R.id.emptyViewStub);
        if (setEmptyLayoutId() != -1) {
            this.emptyViewStub.setLayoutResource(setEmptyLayoutId());
        }
        this.errorViewStub = (ViewStub) inflate.findViewById(R.id.errorViewStub);
        this.swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefreshLayout);
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnablePureScrollMode(true);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter((Context) Objects.requireNonNull(getContext())));
        CommonUtils.initSwipeColor(this.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewListFragment$DaHO2BUb3h8W516CHHWyYxgafRw
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ReviewListFragment.this.lambda$onCreateView$0$ReviewListFragment();
            }
        });
        return inflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$ReviewListFragment() {
        if (isOpenUpFetch()) {
            if (this.mAdapter.getData().size() > 0) {
                if (getCurrPage(this.mAdapter.getData().get(0)) == 1) {
                    swipeRefreshData();
                    return;
                } else {
                    upFetchLoadData();
                    return;
                }
            }
            swipeRefreshData();
            return;
        }
        swipeRefreshData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isNeedLazyLoad()) {
            return;
        }
        init();
        swipeRefreshData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRefreshView() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    private void init() {
        this.mData = new ArrayList();
        getBundle(getArguments());
        if (enableMultiAdapter()) {
            this.mAdapter = new MultiItemAdapter();
        } else {
            this.mAdapter = new BaseLoadMoreAdapter<T, BaseViewHolder>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.1
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                protected void convert(BaseViewHolder baseViewHolder, T t) {
                    ReviewListFragment.this.initHolder(baseViewHolder, t);
                }
            };
        }
        BaseLoadMoreModule loadMoreModule = this.mAdapter.getLoadMoreModule();
        this.loadMoreModule = loadMoreModule;
        loadMoreModule.setLoadMoreView(new PandaForumLoadMoreView());
        this.loadMoreModule.setPreLoadNumber(this.mPreLoadNum);
        if (isVerticalLayout()) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridLayoutSpan()));
        }
        if (addHeaderView(this.mRecyclerView) != null) {
            for (View view : addHeaderView(this.mRecyclerView)) {
                this.mAdapter.addHeaderView(view, 0);
            }
        }
        View inflate = getLayoutInflater().inflate(R.layout.panda_forum_bottom_layout, (ViewGroup) this.mRecyclerView.getParent(), false);
        this.pandaForumView = inflate;
        this.mAdapter.addFooterView(inflate);
        this.pandaForumView.setVisibility(8);
        ((ImageView) this.pandaForumView.findViewById(R.id.ivLink)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewListFragment$AVjRtCgi_3tPqHj-PgktYySf-JE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ReviewListFragment.this.lambda$init$1$ReviewListFragment(view2);
            }
        });
        ((ImageView) this.pandaForumView.findViewById(R.id.ivPanda)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewListFragment$QVeYO9XRcEx_5LTLARm--6lj-Ps
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ReviewListFragment.this.lambda$init$2$ReviewListFragment(view2);
            }
        });
        if (addItemDecoration() != null) {
            this.mRecyclerView.addItemDecoration(addItemDecoration());
        }
        initRecyclerView(this.mRecyclerView);
        RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        addOnItemClickViews(this.mAdapter);
        this.mRecyclerView.setAdapter(this.mAdapter);
        initView();
        if (onItemClick() != null) {
            this.mAdapter.setOnItemClickListener(onItemClick());
        }
        if (onItemChildClick() != null) {
            this.mAdapter.setOnItemChildClickListener(onItemChildClick());
        }
        if (onLongClick() != null) {
            this.mAdapter.setOnItemLongClickListener(onLongClick());
        }
        this.mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        if (isOpenLoadMore()) {
            this.loadMoreModule.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReviewListFragment$7IYPVvELpNYu7_rMdL85VTk8tvQ
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    ReviewListFragment.this.lambda$init$3$ReviewListFragment();
                }
            });
        }
        test();
    }

    public /* synthetic */ void lambda$init$1$ReviewListFragment(View view) {
        if (App.isLogin()) {
            if (App.getBBsInfo() != null) {
                PandaForumAuthorizeActivity.Companion.start(getContext(), true);
                return;
            } else {
                PandaForumAuthorizeActivity.Companion.start(getContext(), false);
                return;
            }
        }
        Login2Activity.start(getContext());
    }

    public /* synthetic */ void lambda$init$2$ReviewListFragment(View view) {
        SystemUtils.startBrowser((Activity) getActivity(), Constant.PANDA_BBS_URL);
    }

    public /* synthetic */ void lambda$init$3$ReviewListFragment() {
        if (Network.isConnected(getContext())) {
            this.mCurrentPage++;
            if (this.mPageClass == null) {
                loadMoreDataPage();
                return;
            } else {
                loadMoreDataPageModel();
                return;
            }
        }
        this.mCurrentPage--;
        this.loadMoreModule.loadMoreFail();
        ToastUtils.showShort("no network");
    }

    private void enableLoadMore(boolean z) {
        this.loadMoreModule.setEnableLoadMore(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endRefreshView() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void loadData() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                ReviewListFragment.this.startRefreshView();
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass2) list);
                if (list == null || list.size() == 0) {
                    ReviewListFragment.this.setEmptyView();
                    return;
                }
                ReviewListFragment.this.doSomethingWithData(list);
                ReviewListFragment.this.mAdapter.setNewData(list);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                ReviewListFragment.this.endRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                ReviewListFragment.this.endRefreshView();
                ReviewListFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadDataWithModel() {
        Disposable disposable = this.lastDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.lastDisposable.dispose();
        }
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                super.onSubscribe(disposable2);
                ReviewListFragment.this.lastDisposable = disposable2;
                ReviewListFragment.this.startRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = ReviewListFragment.this.getData(p);
                if (data == null || data.size() == 0) {
                    ReviewListFragment.this.setEmptyView();
                    return;
                }
                ReviewListFragment.this.doSomethingWithData(data);
                ReviewListFragment.this.mAdapter.setNewData(data);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                ReviewListFragment.this.endRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                ReviewListFragment.this.endRefreshView();
                ReviewListFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.setLoadMoreViewEnable(false);
                } else {
                    ReviewListFragment.this.setRefreshViewEnable(false);
                }
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass4) list);
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        ReviewListFragment.this.mAdapter.setNewData(null);
                        ReviewListFragment.this.setEmptyView();
                        return;
                    }
                    ReviewListFragment.this.doSomethingWithData(list);
                    ReviewListFragment.this.mAdapter.setNewData(list);
                    if (list.size() < ReviewListFragment.this.mPageSize) {
                        ReviewListFragment.this.loadMoreModule.loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                ReviewListFragment.this.doSomethingWithData(list);
                ReviewListFragment.this.mAdapter.addData((Collection) list);
                if (list.size() < ReviewListFragment.this.mPageSize) {
                    ReviewListFragment.this.loadMoreModule.loadMoreEnd();
                } else {
                    ReviewListFragment.this.loadMoreModule.loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.endRefreshView();
                }
                ReviewListFragment.this.setLoadMoreViewEnable(true);
                ReviewListFragment.this.setRefreshViewEnable(true);
                ReviewListFragment.this.pandaForumView.setVisibility(0);
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.setErrorView(apiException.getMessage());
                    ReviewListFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    ReviewListFragment.this.loadMoreModule.loadMoreFail();
                    ReviewListFragment.this.mCurrentPage--;
                }
                ReviewListFragment.this.setLoadMoreViewEnable(true);
                ReviewListFragment.this.setRefreshViewEnable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoadMoreViewEnable(boolean z) {
        this.loadMoreModule.setEnableLoadMore(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewEnable(boolean z) {
        this.swipeRefreshLayout.setEnabled(z);
    }

    private void upFetchLoadData() {
        if (this.mAdapter.getData().size() > 0) {
            this.mCurrentPage = getCurrPage(this.mAdapter.getData().get(0)) - 1;
        }
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.5
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = ReviewListFragment.this.getData(p);
                ReviewListFragment.this.doSomethingWithData(data);
                ReviewListFragment.this.mAdapter.addData(0, (Collection) data);
                ReviewListFragment.this.endRefreshView();
                ((LinearLayoutManager) ReviewListFragment.this.mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(ReviewListFragment.this.mPageSize - 1, 0);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                ReviewListFragment.this.endRefreshView();
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
            }
        });
    }

    private void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.6
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.setLoadMoreViewEnable(false);
                } else {
                    ReviewListFragment.this.setRefreshViewEnable(false);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                ReviewListFragment.this.pandaForumView.setVisibility(0);
                List<T> data = ReviewListFragment.this.getData(p);
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        ReviewListFragment.this.mAdapter.setNewData(null);
                        ReviewListFragment.this.setEmptyView();
                        return;
                    }
                    ReviewListFragment.this.doSomethingWithData(data);
                    ReviewListFragment.this.mAdapter.setNewData(data);
                    if (data.size() < ReviewListFragment.this.mPageSize) {
                        ReviewListFragment.this.loadMoreModule.loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                ReviewListFragment.this.doSomethingWithData(data);
                if (data != null) {
                    ReviewListFragment.this.mAdapter.addData((Collection) data);
                    if (data.size() < ReviewListFragment.this.mPageSize) {
                        ReviewListFragment.this.loadMoreModule.loadMoreEnd();
                        return;
                    } else {
                        ReviewListFragment.this.loadMoreModule.loadMoreComplete();
                        return;
                    }
                }
                ReviewListFragment.this.mAdapter.addData((Collection) new ArrayList());
                ReviewListFragment.this.loadMoreModule.loadMoreEnd();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.endRefreshView();
                }
                ReviewListFragment.this.setLoadMoreViewEnable(true);
                ReviewListFragment.this.setRefreshViewEnable(true);
                ReviewListFragment.this.onFirstLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (ReviewListFragment.this.mCurrentPage == 1) {
                    ReviewListFragment.this.setErrorView(apiException.getMessage());
                    ReviewListFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    ReviewListFragment.this.loadMoreModule.loadMoreFail();
                    ReviewListFragment.this.mCurrentPage--;
                }
                ReviewListFragment.this.setLoadMoreViewEnable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorView(String str) {
        if (this.errorView == null) {
            View inflate = this.errorViewStub.inflate();
            this.errorView = inflate;
            ((TextView) inflate.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", str));
            ((TextView) this.errorView.findViewById(R.id.tvTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ReviewListFragment.this.startRefresh();
                }
            });
        }
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", str));
        this.errorView.setVisibility(0);
    }

    private void hideErrorView() {
        View view = this.errorView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    protected void setEmptyView() {
        if (this.emptyView == null) {
            this.emptyView = this.emptyViewStub.inflate();
        }
        this.emptyView.setVisibility(0);
    }

    private void hideEmptyView() {
        View view = this.emptyView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startRefresh() {
        if (this.mAdapter == null) {
            return;
        }
        swipeRefreshData();
    }

    private void swipeRefreshData() {
        startRefreshView();
        this.mCurrentPage = 1;
        if (isNeedTest()) {
            return;
        }
        refreshData();
    }

    public void refreshData() {
        hideEmptyView();
        hideErrorView();
        if (isNeedLogin()) {
            if (App.isLogin()) {
                startLoad();
                return;
            }
            setErrorView("not login");
            this.mAdapter.setNewData(new ArrayList());
            endRefreshView();
            return;
        }
        startLoad();
    }

    public void refreshPageData(int i) {
        hideEmptyView();
        hideErrorView();
        int size = this.mAdapter.getData().size();
        if (size > 0) {
            int currPage = getCurrPage(this.mAdapter.getData().get(0));
            int currPage2 = getCurrPage(this.mAdapter.getData().get(size - 1));
            if (i >= currPage && i <= currPage2) {
                ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(this.mPageSize * (i - currPage), 0);
                return;
            }
            this.mCurrentPage = i;
            hideEmptyView();
            hideErrorView();
            if (Network.isConnected(getContext())) {
                loadDeterminePageData();
                return;
            }
            endRefreshView();
            setErrorView("no internet");
        }
    }

    public void loadExactPageData(int i) {
        hideEmptyView();
        hideErrorView();
        this.mCurrentPage = i;
        if (Network.isConnected(getContext())) {
            loadDeterminePageData();
            return;
        }
        endRefreshView();
        setErrorView("no internet");
    }

    private void loadDeterminePageData() {
        Disposable disposable = this.lastDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.lastDisposable.dispose();
        }
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.8
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                super.onSubscribe(disposable2);
                ReviewListFragment.this.lastDisposable = disposable2;
                ReviewListFragment.this.startRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = ReviewListFragment.this.getData(p);
                ReviewListFragment.this.doSomethingWithData(data);
                ReviewListFragment.this.setPageInfo(p);
                if (data == null || data.size() == 0) {
                    ReviewListFragment.this.setEmptyView();
                } else {
                    ReviewListFragment.this.doSomethingWithData(data);
                    ReviewListFragment.this.mAdapter.setNewData(data);
                    if (data.size() < ReviewListFragment.this.mPageSize) {
                        ReviewListFragment.this.loadMoreModule.loadMoreEnd(true);
                    }
                }
                ReviewListFragment.this.pandaForumView.setVisibility(0);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                ReviewListFragment.this.endRefreshView();
                ReviewListFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                ReviewListFragment.this.endRefreshView();
                ReviewListFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void startLoad() {
        if (Network.isConnected(getContext())) {
            if (isOpenLoadMore()) {
                this.mCurrentPage = 1;
                if (this.mPageClass == null) {
                    loadMoreDataPage();
                    return;
                } else {
                    loadMoreDataPageModel();
                    return;
                }
            } else if (this.mPageClass == null) {
                loadData();
                return;
            } else {
                loadDataWithModel();
                return;
            }
        }
        endRefreshView();
        setErrorView("no internet");
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentFirstVisible() {
        if (isNeedLazyLoad()) {
            init();
            if (isNeedLoadDataAuto()) {
                swipeRefreshData();
            } else {
                loadDeterminePageData();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class MultiItemAdapter extends BaseLoadmoreDelegateMultiAdapter<T, BaseViewHolder> {
        MultiItemAdapter() {
            super(null);
            setMultiTypeDelegate(new BaseMultiTypeDelegate<T>() { // from class: com.movieboxpro.android.view.activity.review.ReviewListFragment.MultiItemAdapter.1
                @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
                public int getItemType(List<? extends T> list, int i) {
                    return ReviewListFragment.this.initItemType(list.get(i));
                }
            });
            ReviewListFragment.this.registerItemType(getMultiTypeDelegate());
        }

        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            ReviewListFragment.this.initHolder(baseViewHolder, t);
        }
    }
}
