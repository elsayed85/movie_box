package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.movieboxpro.android.view.widget.TouchHelper.DeviceTouchHelperCallback;
import com.movieboxpro.android.view.widget.TouchHelper.ItemTouchMoveListener;
import com.movieboxpro.android.view.widget.adapter.DeviceAdapter;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public class DeviceActivity extends BaseActivity implements ItemTouchMoveListener {
    public static final String DEVICE_INFO = "DEVICE_INFO";
    private ItemTouchHelper itemTouchHelper;
    private MyLinearLayoutManager layoutManager;
    public List<Device> list = new ArrayList();
    private DeviceAdapter mAdapter;
    @BindView(R.id.text_empty)
    TextView mEmptyText;
    @BindView(R.id.fragment_normal_recycler)
    MyRecyclerView mRecycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static void start(Context context) {
        context.startActivity(new Intent(context, DeviceActivity.class));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.layout_recycler_normal, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitle("History");
        CommonUtils.initSwipeColor(this.swipeRefreshLayout);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            this.mAdapter = new DeviceAdapter(this.list, this.activity);
            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
            this.layoutManager = myLinearLayoutManager;
            this.mRecycler.setLayoutManager(myLinearLayoutManager);
            this.mAdapter.setShowFooter(false);
            this.mRecycler.setEmptyView(this.mEmptyText);
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.setAdapter(this.mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DeviceTouchHelperCallback(this));
            this.itemTouchHelper = itemTouchHelper;
            itemTouchHelper.attachToRecyclerView(this.mRecycler);
        }
        String stringParam = getStringParam(DEVICE_INFO, "");
        String str = this.TAG;
        MLog.d(str, "登录device : " + stringParam);
        setTitle("Device");
        if (Network.isConnected(this.context)) {
            if (!TextUtils.isEmpty(stringParam)) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(stringParam);
                if (jSONObject.getInteger("code").intValue() == 2) {
                    setList(jSONObject.getJSONArray("data"));
                }
            } else {
                setData(true);
            }
        }
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceActivity$bIVfaeKw5soY_kQkDldje-ttKJg
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DeviceActivity.this.lambda$initData$0$DeviceActivity();
            }
        });
    }

    public /* synthetic */ void lambda$initData$0$DeviceActivity() {
        if (App.isLogin() && Network.isConnected(this.context)) {
            this.list.clear();
            setData(true);
            return;
        }
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override // com.movieboxpro.android.base.BaseActivity, com.movieboxpro.android.view.listener.IViewController
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    private void setData(boolean z) {
        if (z) {
            showLoading();
        }
        ((ObservableSubscribeProxy) this.service.Device_list(API.BASE_URL, API.Common.DEVICELIST, App.isLogin() ? App.getUserData().uid_v2 : "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.DeviceActivity.1
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(DeviceActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                String str2 = DeviceActivity.this.TAG;
                MLog.d(str2, "登录login_thirdpart : " + str);
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    DeviceActivity.this.setList(jSONObject.getJSONArray("data"));
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                DeviceActivity.this.endLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                DeviceActivity.this.endLoading();
            }
        });
    }

    private void logout(boolean z, int i, String str, String str2) {
        if (z) {
            showLoading();
        }
        ((ObservableSubscribeProxy) this.service.deviceDelete(API.BASE_URL, API.USER.DEVICE_DELETE, App.getUserData().uid_v2, str, str2).subscribeOn(Schedulers.io()).compose(RxUtils.rxTranslateMsg()).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.DeviceActivity.2
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(DeviceActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str3) {
                String str4 = DeviceActivity.this.TAG;
                MLog.d(str4, "登录login_thirdpart : " + str3);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                DeviceActivity.this.endLoading();
                ToastUtils.showShort("Delete failed:" + th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                DeviceActivity.this.endLoading();
            }
        });
    }

    public void setList(JSONArray jSONArray) {
        this.list.addAll(jSONArray.toJavaList(Device.class));
        this.mAdapter.notifyDataSetChanged();
        endLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endLoading() {
        hideLoading();
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override // com.movieboxpro.android.view.widget.TouchHelper.ItemTouchMoveListener
    public boolean onItemMove(int i, int i2) {
        Collections.swap(this.list, i, i2);
        this.mAdapter.notifyItemMoved(i, i2);
        return true;
    }

    @Override // com.movieboxpro.android.view.widget.TouchHelper.ItemTouchMoveListener
    public boolean onItemRemove(int i) {
        Device model = this.mAdapter.getModel(i);
        this.list.remove(i);
        logout(true, i, model.open_udid, model.id);
        this.mAdapter.notifyItemRemoved(i);
        return false;
    }
}
