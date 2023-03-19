package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.VipPlanModel;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VipPlanActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/VipPlanActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/VipPlanModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "id", "", "kProgressHUD", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "getLayoutResId", "", "initData", "", "initListener", "initView", "setAgreementStatus", NotificationCompat.CATEGORY_STATUS, "reason", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VipPlanActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<VipPlanModel, BaseViewHolder> adapter;
    private KProgressHUD kProgressHUD;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id = "";

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
        return R.layout.activity_vip_plan;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        CommonExtKt.initImmersionBar$default(this, 0, 1, null);
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("VIP Plan");
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$VipPlanActivity$7yXgNXApa---PbT1YIO-7TXBAW8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipPlanActivity.m834initView$lambda0(VipPlanActivity.this, view);
            }
        });
        SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvPayPal));
        Intrinsics.checkNotNullExpressionValue(with, "with(tvPayPal)");
        CommonExtKt.addText(with, "How to cancel automatic payment on Paypal", 12, R.color.color_main_blue).setUnderline().create();
        ((TextView) _$_findCachedViewById(R.id.tvPayPal)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$VipPlanActivity$Nnr4bIrs6d6xXv8ACPpCbwmuubQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipPlanActivity.m835initView$lambda1(VipPlanActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m834initView$lambda0(VipPlanActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m835initView$lambda1(VipPlanActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SystemUtils.startBrowser((Activity) this$0, "https://www.movieboxpro.app/index/article?id=26");
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        final UserAgreementModel userAgreementModel = (UserAgreementModel) getIntent().getParcelableExtra("data");
        if (userAgreementModel == null) {
            return;
        }
        String agreement_id = userAgreementModel.getAgreement_id();
        Intrinsics.checkNotNullExpressionValue(agreement_id, "it.agreement_id");
        this.id = agreement_id;
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new VipPlanModel(UploadErrorInfoService.ID, userAgreementModel.getAgreement_id()));
        arrayList.add(new VipPlanModel(userAgreementModel.getFrequency(), userAgreementModel.getFrequency_interval()));
        arrayList.add(new VipPlanModel("Amount", userAgreementModel.getAmount()));
        arrayList.add(new VipPlanModel("Start Date", userAgreementModel.getStart_date()));
        arrayList.add(new VipPlanModel("Settlement Date", userAgreementModel.getNext_billing_date()));
        arrayList.add(new VipPlanModel("Transaction", "", true, false));
        arrayList.add(new VipPlanModel("Automatic Payment", userAgreementModel.getStatus(), false, true));
        this.adapter = new BaseQuickAdapter<VipPlanModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.activity.settings.VipPlanActivity$initData$1$1
            final /* synthetic */ ArrayList<VipPlanModel> $list;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_vip_plan_item, arrayList);
                this.$list = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, VipPlanModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) holder.getView(R.id.tvValue);
                ImageView imageView = (ImageView) holder.getView(R.id.ivArrow);
                holder.setText(R.id.tvTitle, item.getTitle());
                if (item.isTransaction()) {
                    CommonExtKt.gone(textView);
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.visible(textView);
                    CommonExtKt.gone(imageView);
                    textView.setText(item.getValue());
                    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_30alpha));
                }
                if (item.isAutoPayment()) {
                    CommonExtKt.visible(textView);
                    CommonExtKt.gone(imageView);
                    SpanUtils with = SpanUtils.with(textView);
                    Intrinsics.checkNotNullExpressionValue(with, "with(value)");
                    String value = item.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "item.value");
                    CommonExtKt.addText(with, value, 14, R.color.white_30alpha).create();
                }
            }
        };
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).addItemDecoration(new LinearItemDecoration(1, true));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<VipPlanModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<VipPlanModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        BaseQuickAdapter<VipPlanModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$VipPlanActivity$he1WG4J31exdVMSZAYoRHOm4pZg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                VipPlanActivity.m833initData$lambda4$lambda3(VipPlanActivity.this, userAgreementModel, baseQuickAdapter4, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-4$lambda-3  reason: not valid java name */
    public static final void m833initData$lambda4$lambda3(VipPlanActivity this$0, UserAgreementModel userAgreementModel, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<VipPlanModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        if (baseQuickAdapter.getItem(i).isTransaction()) {
            String agreement_id = userAgreementModel.getAgreement_id();
            Intrinsics.checkNotNullExpressionValue(agreement_id, "model.agreement_id");
            TransactionListActivity.Companion.start(this$0, agreement_id);
        }
    }

    private final void setAgreementStatus(final String str, String str2) {
        ((ObservableSubscribeProxy) Http.getService().Agreement_status(API.BASE_URL, "Agreement_status", App.getUserData().uid_v2, str, this.id, str2).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.VipPlanActivity$setAgreementStatus$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                VipPlanActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                VipPlanActivity.this.hideLoadingView();
                Intrinsics.areEqual(str, "stop");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                VipPlanActivity.this.hideLoadingView();
            }
        });
    }

    /* compiled from: VipPlanActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/VipPlanActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "model", "Lcom/movieboxpro/android/model/UserAgreementModel;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, UserAgreementModel userAgreementModel) {
            Intent intent = new Intent(context, VipPlanActivity.class);
            intent.putExtra("data", userAgreementModel);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
