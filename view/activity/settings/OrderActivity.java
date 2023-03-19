package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.model.common.Order;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.movieboxpro.android.view.widget.adapter.OrderAdapter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class OrderActivity extends BaseActivity {
    private MyLinearLayoutManager layoutManager;
    public List<Order> list = new ArrayList();
    private OrderAdapter mAdapter;
    @BindView(R.id.text_empty)
    TextView mEmptyText;
    @BindView(R.id.fragment_normal_recycler)
    MyRecyclerView mRecycler;

    public static void start(Context context) {
        context.startActivity(new Intent(context, OrderActivity.class));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.layout_recycler_normal, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitle("My Orders");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            MLog.d(this.TAG, "SSSS");
            this.mAdapter = new OrderAdapter(this.list);
            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
            this.layoutManager = myLinearLayoutManager;
            this.mRecycler.setLayoutManager(myLinearLayoutManager);
            this.mRecycler.setEmptyView(this.mEmptyText);
            this.mRecycler.setAdapter(this.mAdapter);
        }
        setData(true);
    }

    private void setData(boolean z) {
        if (z) {
            showLoading();
        }
        this.service.Orderlist(API.BASE_URL, API.Common.ORDERLIST, App.isLogin() ? App.getUserData().uid_v2 : "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.settings.OrderActivity.1
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(OrderActivity.this.TAG, disposable);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                JSONObject jSONObject = (JSONObject) JSONObject.parse(str);
                if (jSONObject.getInteger("code").intValue() == 1) {
                    JSONArray jSONArray = jSONObject.getJSONArray("data");
                    String str2 = OrderActivity.this.TAG;
                    MLog.d(str2, "SSSS" + OrderActivity.this.list.size());
                    OrderActivity.this.setList(jSONArray);
                    OrderActivity.this.mAdapter.notifyDataSetChanged();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                OrderActivity.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                OrderActivity.this.hideLoading();
            }
        });
    }

    public void setList(JSONArray jSONArray) {
        jSONArray.toJavaList(Order.class);
        this.list.addAll(jSONArray.toJavaList(Order.class));
        String str = this.TAG;
        MLog.d(str, "SSSS" + this.list.size());
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }
}
