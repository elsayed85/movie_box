package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.TransactionModel;
import com.movieboxpro.android.utils.LinearItemDecoration;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TransactionListActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00172\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0014J\u001a\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TransactionListActivity;", "Lcom/movieboxpro/android/base/BaseListActivity;", "Lcom/movieboxpro/android/model/TransactionModel;", "", "()V", "id", "getIntentData", "", "intent", "Landroid/content/Intent;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isOpenLoadMore", "", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TransactionListActivity extends BaseListActivity<TransactionModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id = "";

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int initItemLayout() {
        return R.layout.adapter_transaction_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isOpenLoadMore() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        return Http.getService().Agreement_transactions(API.BASE_URL, "Agreement_transactions", App.getUserData().uid_v2, this.id);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        this.id = intent == null ? null : intent.getStringExtra("id");
        this.mTvTitle.setText("Transaction");
        this.mClass = TransactionModel.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.addItemDecoration(new LinearItemDecoration(10, true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder helper, TransactionModel transactionModel) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        if (transactionModel == null) {
            return;
        }
        helper.setText(R.id.tvId, transactionModel.getTransaction_id());
        helper.setText(R.id.tvTime, transactionModel.getTime_stamp());
        helper.setText(R.id.tvAmount, transactionModel.getAmount());
    }

    /* compiled from: TransactionListActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/TransactionListActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(id, "id");
            Intent intent = new Intent(context, TransactionListActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
