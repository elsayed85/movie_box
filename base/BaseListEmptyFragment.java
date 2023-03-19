package com.movieboxpro.android.base;

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
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.BaseLoadMoreAdapter;
import com.movieboxpro.android.adapter.BaseLoadmoreDelegateMultiAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public abstract class BaseListEmptyFragment<T, P> extends BaseLazyFragment {
    private View emptyView;
    private ViewStub emptyViewStub;
    private View errorView;
    private ViewStub errorViewStub;
    private Disposable lastDisposable;
    private BaseLoadMoreModule loadMoreModule;
    protected BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    protected Class<P> mPageClass;
    protected RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected int mCurrentPage = 1;
    protected int mPageSize = 15;
    protected int mPreLoadNum = 9;
    private boolean isNeedRefreshData = true;

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

    protected void addOnItemClickViews(BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> baseQuickAdapter) {
    }

    protected void doSomethingWithData(List<T> list) {
    }

    protected void doUpFetchLoading() {
    }

    protected boolean enableEventBus() {
        return true;
    }

    protected boolean enableMultiAdapter() {
        return false;
    }

    protected abstract void getBundle(Bundle bundle);

    protected List<T> getData(P p) {
        return null;
    }

    protected abstract Observable<String> getServiceData();

    protected int gridLayoutSpan() {
        return 2;
    }

    protected void hideEmptyView() {
    }

    protected void initEmptyView(TextView textView, ImageView imageView) {
    }

    protected abstract void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t);

    protected abstract int initItemLayout();

    protected int initItemType(T t) {
        return 0;
    }

    protected void initRecyclerView(RecyclerView recyclerView) {
    }

    protected boolean isNeedLazyLoad() {
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

    protected boolean isUpFetchEnable() {
        return false;
    }

    protected boolean isVerticalLayout() {
        return true;
    }

    protected OnItemChildClickListener onItemChildClick() {
        return null;
    }

    protected com.chad.library.adapter.base.listener.OnItemClickListener onItemClick() {
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

    protected void onSwipeRefresh() {
    }

    protected boolean openLowMemoryEmpty() {
        return false;
    }

    protected void registerItemType(BaseMultiTypeDelegate<T> baseMultiTypeDelegate) {
    }

    protected void setEmptyView() {
    }

    protected void test() {
    }

    protected boolean upFetchLoading() {
        return false;
    }

    protected void viewAttachedToWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    protected void viewDetachedFromWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.fragment_recycler_empty_layout, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.emptyViewStub = (ViewStub) inflate.findViewById(R.id.emptyViewStub);
        this.errorViewStub = (ViewStub) inflate.findViewById(R.id.errorViewStub);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout = swipeRefreshLayout;
        CommonUtils.initSwipeColor(swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListEmptyFragment$DMhAVemDb-ZqKIOjU2kTwRjyKwA
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                BaseListEmptyFragment.this.lambda$onCreateView$0$BaseListEmptyFragment();
            }
        });
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnablePureScrollMode(true);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        if (getContext() != null) {
            smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        }
        return inflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$BaseListEmptyFragment() {
        if (isUpFetchEnable()) {
            doUpFetchLoading();
        } else {
            swipeRefreshData();
        }
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

    protected void startRefreshView() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    private void init() {
        this.mData = new ArrayList();
        getBundle(getArguments());
        if (enableMultiAdapter()) {
            this.mAdapter = new MultiItemAdapter();
        } else {
            this.mAdapter = new BaseLoadMoreAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.1
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t) {
                    BaseListEmptyFragment.this.initHolder(baseViewHolder, t);
                }
            };
        }
        this.loadMoreModule = this.mAdapter.getLoadMoreModule();
        this.mAdapter.getUpFetchModule();
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
            this.loadMoreModule.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListEmptyFragment$gcDwfZGu5JLS-Rw_YTqGaPXhQJw
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    BaseListEmptyFragment.this.lambda$init$1$BaseListEmptyFragment();
                }
            });
        }
        test();
    }

    public /* synthetic */ void lambda$init$1$BaseListEmptyFragment() {
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

    protected void endRefreshView() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void loadData() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                BaseListEmptyFragment.this.startRefreshView();
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass2) list);
                if (list == null || list.size() == 0) {
                    BaseListEmptyFragment.this.setEmptyView();
                    return;
                }
                BaseListEmptyFragment.this.doSomethingWithData(list);
                BaseListEmptyFragment.this.mAdapter.setNewData(list);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListEmptyFragment.this.endRefreshView();
                BaseListEmptyFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListEmptyFragment.this.endRefreshView();
                BaseListEmptyFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadDataWithModel() {
        Disposable disposable = this.lastDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.lastDisposable.dispose();
        }
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                super.onSubscribe(disposable2);
                BaseListEmptyFragment.this.lastDisposable = disposable2;
                BaseListEmptyFragment.this.startRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListEmptyFragment.this.getData(p);
                if (data == null || data.size() == 0) {
                    BaseListEmptyFragment.this.setEmptyView();
                    return;
                }
                BaseListEmptyFragment.this.doSomethingWithData(data);
                BaseListEmptyFragment.this.mAdapter.setNewData(data);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListEmptyFragment.this.endRefreshView();
                BaseListEmptyFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListEmptyFragment.this.endRefreshView();
                BaseListEmptyFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.setLoadMoreViewEnable(false);
                } else {
                    BaseListEmptyFragment.this.setRefreshViewEnable(false);
                }
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass4) list);
                BaseListEmptyFragment.this.doSomethingWithData(list);
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        BaseListEmptyFragment.this.mAdapter.setList(null);
                        BaseListEmptyFragment.this.setEmptyView();
                        return;
                    }
                    BaseListEmptyFragment.this.mAdapter.setList(list);
                    return;
                }
                BaseListEmptyFragment.this.doSomethingWithData(list);
                BaseListEmptyFragment.this.mAdapter.addData((Collection) list);
                if (list.size() == 0) {
                    BaseListEmptyFragment.this.loadMoreModule.loadMoreEnd();
                } else {
                    BaseListEmptyFragment.this.loadMoreModule.loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.endRefreshView();
                }
                BaseListEmptyFragment.this.setLoadMoreViewEnable(true);
                BaseListEmptyFragment.this.setRefreshViewEnable(true);
                BaseListEmptyFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.setErrorView(apiException.getMessage());
                    BaseListEmptyFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    BaseListEmptyFragment.this.loadMoreModule.loadMoreFail();
                    BaseListEmptyFragment.this.mCurrentPage--;
                }
                BaseListEmptyFragment.this.setLoadMoreViewEnable(true);
                BaseListEmptyFragment.this.setRefreshViewEnable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoadMoreViewEnable(boolean z) {
        this.loadMoreModule.setEnableLoadMore(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewEnable(boolean z) {
        if (isOpenRefresh()) {
            this.swipeRefreshLayout.setEnabled(z);
        }
    }

    private void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.5
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.setLoadMoreViewEnable(false);
                } else {
                    BaseListEmptyFragment.this.setRefreshViewEnable(false);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListEmptyFragment.this.getData(p);
                BaseListEmptyFragment.this.doSomethingWithData(data);
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        BaseListEmptyFragment.this.mAdapter.setNewData(null);
                        BaseListEmptyFragment.this.setEmptyView();
                        return;
                    }
                    BaseListEmptyFragment.this.mAdapter.setNewData(data);
                    if (data.size() < BaseListEmptyFragment.this.mPageSize) {
                        BaseListEmptyFragment.this.loadMoreModule.loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListEmptyFragment.this.doSomethingWithData(data);
                if (data != null) {
                    BaseListEmptyFragment.this.mAdapter.addData((Collection) data);
                    if (data.size() < BaseListEmptyFragment.this.mPageSize) {
                        BaseListEmptyFragment.this.loadMoreModule.loadMoreEnd();
                        return;
                    } else {
                        BaseListEmptyFragment.this.loadMoreModule.loadMoreComplete();
                        return;
                    }
                }
                BaseListEmptyFragment.this.mAdapter.addData((Collection) new ArrayList());
                BaseListEmptyFragment.this.loadMoreModule.loadMoreEnd();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.endRefreshView();
                }
                BaseListEmptyFragment.this.setLoadMoreViewEnable(true);
                BaseListEmptyFragment.this.setRefreshViewEnable(true);
                BaseListEmptyFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListEmptyFragment.this.mCurrentPage == 1) {
                    BaseListEmptyFragment.this.setErrorView(apiException.getMessage());
                    BaseListEmptyFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    BaseListEmptyFragment.this.loadMoreModule.loadMoreFail();
                    BaseListEmptyFragment.this.mCurrentPage--;
                }
                BaseListEmptyFragment.this.setLoadMoreViewEnable(true);
                BaseListEmptyFragment.this.setRefreshViewEnable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorView(String str) {
        if (this.errorView == null) {
            this.errorView = this.errorViewStub.inflate();
        }
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", str));
        TextView textView = (TextView) this.errorView.findViewById(R.id.tvTryAgain);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseListEmptyFragment.this.startRefresh();
            }
        });
        textView.setText("Try again");
        ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.drawable.ic_load_error);
        this.errorView.setVisibility(0);
    }

    private void setNotLoginError() {
        View view = this.errorView;
        if (view == null) {
            View inflate = this.errorViewStub.inflate();
            this.errorView = inflate;
            TextView textView = (TextView) inflate.findViewById(R.id.tvTryAgain);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Login2Activity.start(BaseListEmptyFragment.this.getContext());
                }
            });
            ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.mipmap.ic_no_login);
            ((TextView) this.errorView.findViewById(R.id.empty_text)).setText("Please login first.");
            textView.setText("Login");
        } else {
            ((TextView) view.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", "No login"));
            TextView textView2 = (TextView) this.errorView.findViewById(R.id.tvTryAgain);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Login2Activity.start(BaseListEmptyFragment.this.getContext());
                }
            });
            ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.mipmap.ic_no_login);
            ((TextView) this.errorView.findViewById(R.id.empty_text)).setText("Please login first.");
            textView2.setText("Login");
        }
        this.errorView.setVisibility(0);
    }

    private void hideErrorView() {
        View view = this.errorView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void startRefresh() {
        if (this.mAdapter == null) {
            return;
        }
        swipeRefreshData();
    }

    private void swipeRefreshData() {
        onSwipeRefresh();
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
            setNotLoginError();
            this.mAdapter.setNewData(new ArrayList());
            endRefreshView();
            return;
        }
        startLoad();
    }

    public void refreshPageData(int i) {
        this.mCurrentPage = i;
        hideEmptyView();
        hideErrorView();
        if (Network.isConnected(getContext())) {
            if (this.mPageClass == null) {
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
            swipeRefreshData();
        }
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResumeNoFirst() {
        BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> baseQuickAdapter;
        super.onFragmentResumeNoFirst();
        if (!openLowMemoryEmpty() || (baseQuickAdapter = this.mAdapter) == null || baseQuickAdapter.getData().size() != 0 || this.swipeRefreshLayout.isRefreshing()) {
            return;
        }
        startRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLowMemory(LowMemoryEvent lowMemoryEvent) {
        BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> baseQuickAdapter;
        if (!openLowMemoryEmpty() || this.isVisibleToUser || (baseQuickAdapter = this.mAdapter) == null) {
            return;
        }
        baseQuickAdapter.setNewData(new ArrayList());
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
    public class MultiItemAdapter extends BaseLoadmoreDelegateMultiAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
        MultiItemAdapter() {
            super(null);
            BaseMultiTypeDelegate<T> baseMultiTypeDelegate = new BaseMultiTypeDelegate<T>() { // from class: com.movieboxpro.android.base.BaseListEmptyFragment.MultiItemAdapter.1
                @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
                public int getItemType(List<? extends T> list, int i) {
                    return BaseListEmptyFragment.this.initItemType(list.get(i));
                }
            };
            setMultiTypeDelegate(baseMultiTypeDelegate);
            BaseListEmptyFragment.this.registerItemType(baseMultiTypeDelegate);
        }

        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t) {
            BaseListEmptyFragment.this.initHolder(baseViewHolder, t);
        }
    }
}
