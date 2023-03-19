package com.movieboxpro.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.BaseLoadMoreAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.CustomLoadMoreView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public abstract class BaseListActivity<T, P> extends AppCompatActivity {
    private View emptyView;
    private ViewStub emptyViewStub;
    private View errorView;
    private ViewStub errorViewStub;
    private BaseLoadMoreModule loadMoreModule;
    protected BaseQuickAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> mAdapter;
    protected Class<T> mClass;
    protected List<T> mData;
    protected Class<P> mPageClass;
    protected RecyclerView mRecyclerView;
    protected TextView mTvTitle;
    private SwipeRefreshLayout swipeRefreshLayout;
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

    protected void doSomethingWithData(List<T> list) {
    }

    protected boolean enableEventBus() {
        return false;
    }

    protected List<T> getData(P p) {
        return null;
    }

    protected abstract void getIntentData(Intent intent);

    protected abstract Observable<String> getServiceData();

    protected int getStatusColor() {
        return R.color.color_main_back;
    }

    protected int gridLayoutSpan() {
        return 2;
    }

    protected void initEmptyView(TextView textView, ImageView imageView) {
    }

    protected abstract void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t);

    protected abstract int initItemLayout();

    /* JADX INFO: Access modifiers changed from: protected */
    public void initRecyclerView(RecyclerView recyclerView) {
    }

    protected boolean isOpenLoadMore() {
        return true;
    }

    protected boolean isOpenRefresh() {
        return true;
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

    protected void onLoadDataComplete(List list) {
    }

    protected void onRequestLoadMoreComplete(List<T> list) {
    }

    protected void test() {
    }

    protected void viewAttachedToWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    protected void viewDetachedFromWindow(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_base_list);
        initStatusTint();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.emptyViewStub = (ViewStub) findViewById(R.id.emptyViewStub);
        this.errorViewStub = (ViewStub) findViewById(R.id.errorViewStub);
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setEnablePureScrollMode(true);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        findViewById(R.id.ll_back).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseListActivity.this.finish();
            }
        });
        this.mTvTitle = (TextView) findViewById(R.id.tv_title);
        init();
    }

    private void initStatusTint() {
        ImmersionBar.with(this).fitsSystemWindows(true).navigationBarColor(getStatusColor()).statusBarColor(getStatusColor()).init();
    }

    private void init() {
        this.mData = new ArrayList();
        getIntentData(getIntent());
        BaseLoadMoreAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder> baseLoadMoreAdapter = new BaseLoadMoreAdapter<T, com.chad.library.adapter.base.viewholder.BaseViewHolder>(initItemLayout(), this.mData) { // from class: com.movieboxpro.android.base.BaseListActivity.2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            protected void convert(com.chad.library.adapter.base.viewholder.BaseViewHolder baseViewHolder, T t) {
                BaseListActivity.this.initHolder(baseViewHolder, t);
            }
        };
        this.mAdapter = baseLoadMoreAdapter;
        BaseLoadMoreModule loadMoreModule = baseLoadMoreAdapter.getLoadMoreModule();
        this.loadMoreModule = loadMoreModule;
        loadMoreModule.setLoadMoreView(new CustomLoadMoreView());
        this.loadMoreModule.setPreLoadNumber(this.mPreLoadNum);
        if (isVerticalLayout()) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridLayoutSpan()));
        }
        if (addHeaderView(this.mRecyclerView) != null) {
            for (View view : addHeaderView(this.mRecyclerView)) {
                this.mAdapter.addHeaderView(view, 0);
            }
        }
        initRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mAdapter);
        initView();
        if (onItemClick() != null) {
            this.mAdapter.setOnItemClickListener(onItemClick());
        }
        if (onItemChildClick() != null) {
            this.mAdapter.setOnItemChildClickListener(onItemChildClick());
        }
        this.loadMoreModule.setPreLoadNumber(8);
        this.mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        if (isOpenLoadMore()) {
            this.loadMoreModule.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListActivity$-MiE92ueZXgGES_A3hiHRDOWiDU
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    BaseListActivity.this.lambda$init$0$BaseListActivity();
                }
            });
        }
        CommonUtils.initSwipeColor(this.swipeRefreshLayout);
        if (isOpenRefresh()) {
            this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.base.-$$Lambda$BaseListActivity$2pOOx5fPXrdjNqVMQqr4MkQV20g
                @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
                public final void onRefresh() {
                    BaseListActivity.this.lambda$init$1$BaseListActivity();
                }
            });
        } else {
            this.swipeRefreshLayout.setEnabled(false);
        }
        test();
        startRefresh();
    }

    public /* synthetic */ void lambda$init$0$BaseListActivity() {
        if (Network.isConnected(this)) {
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

    public /* synthetic */ void lambda$init$1$BaseListActivity() {
        this.mCurrentPage = 1;
        startRefresh();
    }

    protected void testItems(List<T> list) {
        this.mAdapter.setNewData(list);
    }

    private void enableLoadMore(boolean z) {
        this.loadMoreModule.setEnableLoadMore(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endRefreshView() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void startRefreshView() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    private void loadData() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListActivity.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass3) list);
                if (list == null || list.size() == 0) {
                    BaseListActivity.this.setEmptyView();
                } else {
                    BaseListActivity.this.mAdapter.setNewData(list);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListActivity.this.endRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListActivity.this.endRefreshView();
                BaseListActivity.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadDataWithModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListActivity.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListActivity.this.getData(p);
                if (data == null || data.size() == 0) {
                    BaseListActivity.this.setEmptyView();
                } else {
                    BaseListActivity.this.mAdapter.setNewData(data);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                BaseListActivity.this.endRefreshView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                BaseListActivity.this.endRefreshView();
                BaseListActivity.this.setErrorView(apiException.getMessage());
            }
        });
    }

    private void loadMoreDataPage() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2List(this.mClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<T>>() { // from class: com.movieboxpro.android.base.BaseListActivity.5
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((List) ((List) obj));
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.setLoadMoreViewEnable(false);
                } else {
                    BaseListActivity.this.setRefreshViewEnable(false);
                }
            }

            public void onNext(List<T> list) {
                super.onNext((AnonymousClass5) list);
                if (BaseListActivity.this.mCurrentPage == 1) {
                    if (list == null || list.size() == 0) {
                        BaseListActivity.this.setEmptyView();
                        return;
                    } else {
                        BaseListActivity.this.mAdapter.setNewData(list);
                        return;
                    }
                }
                BaseListActivity.this.mAdapter.addData((Collection) list);
                if (list.size() == 0) {
                    BaseListActivity.this.loadMoreModule.loadMoreEnd();
                } else {
                    BaseListActivity.this.loadMoreModule.loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.endRefreshView();
                }
                BaseListActivity.this.setLoadMoreViewEnable(true);
                BaseListActivity.this.setRefreshViewEnable(true);
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.setErrorView(apiException.getMessage());
                    BaseListActivity.this.endRefreshView();
                } else {
                    ToastUtils.showShort("service error");
                    BaseListActivity.this.loadMoreModule.loadMoreFail();
                    BaseListActivity.this.mCurrentPage--;
                }
                BaseListActivity.this.setLoadMoreViewEnable(true);
                BaseListActivity.this.setRefreshViewEnable(true);
            }
        });
    }

    private void loadMoreDataPageModel() {
        ((ObservableSubscribeProxy) getServiceData().compose(RxUtils.rxTranslate2Bean(this.mPageClass)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<P>() { // from class: com.movieboxpro.android.base.BaseListActivity.6
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(P p) {
                super.onNext(p);
                List<T> data = BaseListActivity.this.getData(p);
                if (BaseListActivity.this.mCurrentPage == 1) {
                    if (data == null || data.size() == 0) {
                        BaseListActivity.this.setEmptyView();
                        return;
                    }
                    BaseListActivity.this.mAdapter.setNewData(data);
                    if (data.size() < BaseListActivity.this.mPageSize) {
                        BaseListActivity.this.loadMoreModule.loadMoreEnd(true);
                        return;
                    }
                    return;
                }
                BaseListActivity.this.mAdapter.addData((Collection) data);
                if (data.size() < BaseListActivity.this.mPageSize) {
                    BaseListActivity.this.loadMoreModule.loadMoreEnd();
                } else {
                    BaseListActivity.this.loadMoreModule.loadMoreComplete();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.endRefreshView();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (BaseListActivity.this.mCurrentPage == 1) {
                    BaseListActivity.this.setErrorView(apiException.getMessage());
                    BaseListActivity.this.endRefreshView();
                    return;
                }
                ToastUtils.showShort("service error");
                BaseListActivity.this.loadMoreModule.loadMoreFail();
                BaseListActivity.this.mCurrentPage--;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorView(String str) {
        if (this.errorView == null) {
            View inflate = this.errorViewStub.inflate();
            this.errorView = inflate;
            ((TextView) inflate.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", str));
            ((TextView) this.errorView.findViewById(R.id.tvTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseListActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseListActivity.this.startRefresh();
                }
            });
        }
        ((TextView) this.errorView.findViewById(R.id.empty_text)).setText(String.format("Load failed:%s", str));
        this.errorView.setVisibility(0);
    }

    protected void startRefresh() {
        if (this.mAdapter == null) {
            return;
        }
        refreshData();
    }

    private void hideErrorView() {
        View view = this.errorView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmptyView() {
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

    protected void refreshData() {
        startRefreshView();
        hideEmptyView();
        hideErrorView();
        if (Network.isConnected(this)) {
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

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
