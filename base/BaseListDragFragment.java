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
import com.movieboxpro.android.adapter.BaseItemDraggableAdapter;
import com.movieboxpro.android.adapter.BaseLoadmoreDelegateMultiAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes.dex */
public abstract class BaseListDragFragment<T, P> extends BaseLazyFragment {
    private View emptyView;
    private ViewStub emptyViewStub;
    private View errorView;
    private ViewStub errorViewStub;
    private Disposable lastDisposable;
    private BaseLoadMoreModule loadMoreModule;
    protected BaseItemDraggableAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    protected Class<P> mPageClass;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected int mCurrentPage = 1;
    protected int mPageSize = 15;
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

    protected void doSomethingWithData(List<T> list) {
    }

    protected boolean enableEventBus() {
        return false;
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

    protected void initEmptyView(TextView textView, ImageView imageView) {
    }

    protected abstract void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t);

    protected abstract int initItemLayout();

    protected int initItemType(T t) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initRecyclerView(RecyclerView recyclerView) {
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

    protected boolean isUseDiffUtil() {
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

    protected void registerItemType(BaseMultiTypeDelegate<T> baseMultiTypeDelegate) {
    }

    protected void test() {
    }

    protected void viewAttachedToWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    protected void viewDetachedFromWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.fragment_recycler_layout2, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.emptyViewStub = (ViewStub) inflate.findViewById(R.id.emptyViewStub);
        this.errorViewStub = (ViewStub) inflate.findViewById(R.id.errorViewStub);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout = swipeRefreshLayout;
        CommonUtils.initSwipeColor(swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListDragFragment$tRsFOm_ebc0QL8asOxiildveAm0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                BaseListDragFragment.this.swipeRefreshData();
            }
        });
        return inflate;
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
        if (!enableMultiAdapter()) {
            this.mAdapter = new BaseItemDraggableAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.base.BaseListDragFragment.1
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t) {
                    BaseListDragFragment.this.initHolder(baseViewHolder, t);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                public void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t, List<?> list) {
                    super.convert(baseViewHolder, t, list);
                }
            };
        }
        BaseLoadMoreModule loadMoreModule = this.mAdapter.getLoadMoreModule();
        this.loadMoreModule = loadMoreModule;
        loadMoreModule.setPreLoadNumber(this.mPreLoadNum);
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
            this.loadMoreModule.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListDragFragment$Ef8rYGtNmFtXBX-Pdt58ol_qKFc
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    BaseListDragFragment.this.lambda$init$0$BaseListDragFragment();
                }
            });
        }
        test();
    }

    public /* synthetic */ void lambda$init$0$BaseListDragFragment() {
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
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListDragFragment.2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                BaseListDragFragment.this.startRefreshView();
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass2) list);
                if (list == null || list.size() == 0) {
                    BaseListDragFragment.this.setEmptyView();
                    return;
                }
                BaseListDragFragment.this.doSomethingWithData(list);
                BaseListDragFragment.this.mAdapter.setNewData(list);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListDragFragment.this.endRefreshView();
                BaseListDragFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListDragFragment.this.endRefreshView();
                BaseListDragFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadDataWithModel() {
        Disposable disposable = this.lastDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.lastDisposable.dispose();
        }
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListDragFragment.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                super.onSubscribe(disposable2);
                BaseListDragFragment.this.lastDisposable = disposable2;
                BaseListDragFragment.this.startRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListDragFragment.this.getData(p);
                if (data == null || data.size() == 0) {
                    BaseListDragFragment.this.setEmptyView();
                    return;
                }
                BaseListDragFragment.this.doSomethingWithData(data);
                if (BaseListDragFragment.this.isUseDiffUtil()) {
                    BaseListDragFragment.this.mAdapter.setDiffNewData(data);
                } else {
                    BaseListDragFragment.this.mAdapter.setNewData(data);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListDragFragment.this.endRefreshView();
                BaseListDragFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListDragFragment.this.endRefreshView();
                BaseListDragFragment.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListDragFragment.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (BaseListDragFragment.this.mCurrentPage != 1) {
                    BaseListDragFragment.this.setRefreshViewEnable(false);
                } else {
                    BaseListDragFragment.this.setLoadMoreViewEnable(false);
                }
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass4) list);
                BaseListDragFragment.this.doSomethingWithData(list);
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        if (BaseListDragFragment.this.mAdapter != null) {
                            BaseListDragFragment.this.mAdapter.setDiffNewData(null);
                        }
                        BaseListDragFragment.this.setEmptyView();
                        return;
                    } else if (BaseListDragFragment.this.isUseDiffUtil()) {
                        if (BaseListDragFragment.this.mAdapter != null) {
                            BaseListDragFragment.this.mAdapter.setDiffNewData(list);
                            return;
                        }
                        return;
                    } else if (BaseListDragFragment.this.mAdapter != null) {
                        BaseListDragFragment.this.mAdapter.setNewData(list);
                        return;
                    } else {
                        return;
                    }
                }
                BaseListDragFragment.this.doSomethingWithData(list);
                if (BaseListDragFragment.this.mAdapter != null) {
                    BaseListDragFragment.this.mAdapter.addData((Collection) list);
                }
                if (list.size() == 0) {
                    BaseListDragFragment.this.loadMoreModule.loadMoreEnd();
                } else {
                    BaseListDragFragment.this.loadMoreModule.loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    BaseListDragFragment.this.endRefreshView();
                }
                BaseListDragFragment.this.setLoadMoreViewEnable(true);
                BaseListDragFragment.this.setRefreshViewEnable(true);
                BaseListDragFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    BaseListDragFragment.this.setErrorView(apiException.getMessage());
                    BaseListDragFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    BaseListDragFragment.this.loadMoreModule.loadMoreFail();
                    BaseListDragFragment.this.mCurrentPage--;
                }
                BaseListDragFragment.this.setLoadMoreViewEnable(true);
                BaseListDragFragment.this.setRefreshViewEnable(true);
            }
        });
    }

    protected void setLoadMoreViewEnable(boolean z) {
        BaseLoadMoreModule baseLoadMoreModule = this.loadMoreModule;
        if (baseLoadMoreModule != null) {
            baseLoadMoreModule.setEnableLoadMore(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshViewEnable(boolean z) {
        this.swipeRefreshLayout.setEnabled(z);
    }

    private void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListDragFragment.5
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (BaseListDragFragment.this.mCurrentPage != 1) {
                    BaseListDragFragment.this.setRefreshViewEnable(false);
                } else {
                    BaseListDragFragment.this.setLoadMoreViewEnable(false);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListDragFragment.this.getData(p);
                BaseListDragFragment.this.doSomethingWithData(data);
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        BaseListDragFragment.this.mAdapter.setList(null);
                        BaseListDragFragment.this.setEmptyView();
                        return;
                    }
                    if (BaseListDragFragment.this.isUseDiffUtil()) {
                        BaseListDragFragment.this.mAdapter.setDiffNewData(data);
                    } else {
                        BaseListDragFragment.this.mAdapter.setList(data);
                    }
                    if (data.size() < BaseListDragFragment.this.mPageSize) {
                        BaseListDragFragment.this.loadMoreModule.loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListDragFragment.this.doSomethingWithData(data);
                if (data != null) {
                    BaseListDragFragment.this.mAdapter.addData((Collection) data);
                    if (data.size() < BaseListDragFragment.this.mPageSize) {
                        BaseListDragFragment.this.loadMoreModule.loadMoreEnd();
                        return;
                    } else {
                        BaseListDragFragment.this.loadMoreModule.loadMoreComplete();
                        return;
                    }
                }
                BaseListDragFragment.this.mAdapter.addData((Collection) new ArrayList());
                BaseListDragFragment.this.loadMoreModule.loadMoreEnd();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    BaseListDragFragment.this.endRefreshView();
                }
                BaseListDragFragment.this.setLoadMoreViewEnable(true);
                BaseListDragFragment.this.setRefreshViewEnable(true);
                BaseListDragFragment.this.onLoadComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListDragFragment.this.mCurrentPage == 1) {
                    BaseListDragFragment.this.setErrorView(apiException.getMessage());
                    BaseListDragFragment.this.endRefreshView();
                } else {
                    ToastUtils.showShort("Load failed");
                    BaseListDragFragment.this.loadMoreModule.loadMoreFail();
                    BaseListDragFragment.this.mCurrentPage--;
                }
                BaseListDragFragment.this.setLoadMoreViewEnable(true);
                BaseListDragFragment.this.setRefreshViewEnable(true);
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
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListDragFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseListDragFragment.this.startRefresh();
            }
        });
        textView.setText("Try again");
        ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.drawable.ic_load_error);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void swipeRefreshData() {
        onSwipeRefresh();
        startRefreshView();
        this.mCurrentPage = 1;
        if (isNeedTest()) {
            return;
        }
        refreshData();
    }

    private void setNotLoginError() {
        if (this.errorView == null) {
            this.errorView = this.errorViewStub.inflate();
        }
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText("Please login first.");
        TextView textView = (TextView) this.errorView.findViewById(R.id.tvTryAgain);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListDragFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Login2Activity.start(BaseListDragFragment.this.getContext());
            }
        });
        ((ImageView) this.errorView.findViewById(R.id.empty_image)).setImageResource(R.mipmap.ic_no_login);
        textView.setText("Login");
        this.errorView.setVisibility(0);
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
            this.mAdapter.setList(new ArrayList());
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

    /* loaded from: classes3.dex */
    class MultiItemAdapter extends BaseLoadmoreDelegateMultiAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> {
        MultiItemAdapter() {
            super(null);
        }

        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t) {
            BaseListDragFragment.this.initHolder(baseViewHolder, t);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t, List<?> list) {
            super.convert(baseViewHolder, t, list);
        }
    }
}
