package com.movieboxpro.android.view.activity.review;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BBsResponseModel;
import com.movieboxpro.android.model.ReportReasonModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ReportReviewActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReportReviewActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/ReportReasonModel;", "lastChoose", "", "getLayoutResId", "getReportReason", "", "initData", "initListener", "initView", "sendReport", NotificationCompat.CATEGORY_MESSAGE, "", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReportReviewActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    private CommBaseAdapter<ReportReasonModel> adapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastChoose = -1;

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_report_review;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReportReviewActivity$rr3RomHLauvijqrlEcJJu0eRrpM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReportReviewActivity.m596initListener$lambda0(ReportReviewActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReportReviewActivity$En0UJOfkp4LNOlBgphKuJP94t8c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReportReviewActivity.m597initListener$lambda1(ReportReviewActivity.this, view);
            }
        });
        CommBaseAdapter<ReportReasonModel> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ReportReviewActivity$iHa3EIgjdQCDv6p6Q-JNJgXf1cM
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReportReviewActivity.m598initListener$lambda2(ReportReviewActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m596initListener$lambda0(ReportReviewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m597initListener$lambda1(ReportReviewActivity this$0, View view) {
        Editable text;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommBaseAdapter<ReportReasonModel> commBaseAdapter = this$0.adapter;
        String str = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        ReportReasonModel item = commBaseAdapter.getItem(this$0.lastChoose);
        if (item != null) {
            if (Intrinsics.areEqual(item.getReason(), "Other")) {
                CommBaseAdapter<ReportReasonModel> commBaseAdapter2 = this$0.adapter;
                if (commBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commBaseAdapter2 = null;
                }
                View viewByPosition = commBaseAdapter2.getViewByPosition(this$0.lastChoose, R.id.editText);
                EditText editText = viewByPosition instanceof EditText ? (EditText) viewByPosition : null;
                if (editText != null && (text = editText.getText()) != null) {
                    str = text.toString();
                }
                String str2 = str;
                if (str2 == null || StringsKt.isBlank(str2)) {
                    ToastUtils.showShort("Reason is empty", new Object[0]);
                    return;
                } else {
                    this$0.sendReport(str);
                    return;
                }
            }
            String reason = item.getReason();
            Intrinsics.checkNotNullExpressionValue(reason, "item.reason");
            this$0.sendReport(reason);
            return;
        }
        ToastUtils.showShort("Please choose a reason", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m598initListener$lambda2(ReportReviewActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastChoose == i) {
            return;
        }
        CommBaseAdapter<ReportReasonModel> commBaseAdapter = this$0.adapter;
        CommBaseAdapter<ReportReasonModel> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        ReportReasonModel item = commBaseAdapter.getItem(i);
        if (item != null) {
            item.setChoose(!item.isChoose());
            CommBaseAdapter<ReportReasonModel> commBaseAdapter3 = this$0.adapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter3 = null;
            }
            commBaseAdapter3.notifyItemChanged(i);
        }
        if (this$0.lastChoose != -1) {
            CommBaseAdapter<ReportReasonModel> commBaseAdapter4 = this$0.adapter;
            if (commBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commBaseAdapter4 = null;
            }
            ReportReasonModel item2 = commBaseAdapter4.getItem(this$0.lastChoose);
            if (item2 != null) {
                item2.setChoose(!item2.isChoose());
                CommBaseAdapter<ReportReasonModel> commBaseAdapter5 = this$0.adapter;
                if (commBaseAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commBaseAdapter2 = commBaseAdapter5;
                }
                commBaseAdapter2.notifyItemChanged(this$0.lastChoose);
            }
        }
        this$0.lastChoose = i;
    }

    private final void sendReport(String str) {
        ((ObservableSubscribeProxy) Http.getService().sendReport(API.BBS_URL, "common_report", App.getBBsInfo().getAuth(), App.getBBsInfo().getAuthkey(), App.getBBsInfo().getFormhash(), str, "post", getIntent().getStringExtra("pid"), getIntent().getStringExtra("tid"), getIntent().getStringExtra("fid")).compose(RxUtils.rxTranslate2Bean(BBsResponseModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<BBsResponseModel>() { // from class: com.movieboxpro.android.view.activity.review.ReportReviewActivity$sendReport$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ReportReviewActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BBsResponseModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (model.getMessage() != null) {
                    if (Intrinsics.areEqual(model.getMessage().getMessageval(), "report_succeed")) {
                        ToastUtils.showShort(model.getMessage().getMessagestr(), new Object[0]);
                        ReportReviewActivity.this.finish();
                    } else {
                        ToastUtils.showShort(model.getMessage().getMessagestr(), new Object[0]);
                    }
                }
                ReportReviewActivity.this.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ToastUtils.showShort(Intrinsics.stringPlus("Failed:", e.getMessage()), new Object[0]);
                ReportReviewActivity.this.hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_report_item, ReportReviewActivity$initData$1.INSTANCE, null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<ReportReasonModel> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView.setAdapter(commBaseAdapter);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView2);
        getReportReason();
    }

    private final void getReportReason() {
        if (App.getBBsInfo() != null) {
            ((ObservableSubscribeProxy) Http.getService().getReportReason(API.BBS_URL, "common_report_reasons", App.getBBsInfo().getAuth(), App.getBBsInfo().getAuthkey()).compose(RxUtils.rxTranslate2List(String.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<? extends String>>() { // from class: com.movieboxpro.android.view.activity.review.ReportReviewActivity$getReportReason$1
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public /* bridge */ /* synthetic */ void onSuccess(List<? extends String> list) {
                    onSuccess2((List<String>) list);
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    ReportReviewActivity.this.showLoadingView();
                }

                /* renamed from: onSuccess  reason: avoid collision after fix types in other method */
                public void onSuccess2(List<String> model) {
                    CommBaseAdapter commBaseAdapter;
                    Intrinsics.checkNotNullParameter(model, "model");
                    ReportReviewActivity.this.hideLoadingView();
                    ArrayList arrayList = new ArrayList();
                    for (String str : model) {
                        arrayList.add(new ReportReasonModel(str, false));
                    }
                    commBaseAdapter = ReportReviewActivity.this.adapter;
                    if (commBaseAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commBaseAdapter = null;
                    }
                    commBaseAdapter.setNewData(arrayList);
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    ReportReviewActivity.this.hideLoadingView();
                    ToastUtils.showShort(Intrinsics.stringPlus("Load failed:", e.getMessage()), new Object[0]);
                }
            });
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Report");
        TextView tv_right = (TextView) _$_findCachedViewById(R.id.tv_right);
        Intrinsics.checkNotNullExpressionValue(tv_right, "tv_right");
        CommonExtKt.visible(tv_right);
        ((TextView) _$_findCachedViewById(R.id.tv_right)).setText("Send");
        ((LinearLayout) _$_findCachedViewById(R.id.ll_right)).setClickable(true);
    }

    /* compiled from: ReportReviewActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ReportReviewActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "pid", "", "tid", "fid", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, ReportReviewActivity.class);
            intent.putExtra("pid", str);
            intent.putExtra("tid", str2);
            intent.putExtra("fid", str3);
            context.startActivity(intent);
        }
    }
}
