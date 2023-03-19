package com.movieboxpro.android.view.dialog;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.ScreenMember;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration2;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ScreenManageDialog.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/ScreenMember;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenManageDialog$initData$2 extends Lambda implements Function2<BaseViewHolder, ScreenMember, Unit> {
    final /* synthetic */ ScreenManageDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenManageDialog$initData$2(ScreenManageDialog screenManageDialog) {
        super(2);
        this.this$0 = screenManageDialog;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ScreenMember screenMember) {
        invoke2(baseViewHolder, screenMember);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, ScreenMember item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvName);
        TextView textView2 = (TextView) helper.getView(R.id.tvOnlineNum);
        if (Intrinsics.areEqual(item.getUid(), App.getUserData().getUid())) {
            SpanUtils with = SpanUtils.with(textView);
            Intrinsics.checkNotNullExpressionValue(with, "with(tvName)");
            String username = item.getUsername();
            Intrinsics.checkNotNullExpressionValue(username, "item.username");
            CommonExtKt.addText(CommonExtKt.addText(with, username, 16, R.color.white_30alpha), " (me)", 16, R.color.white_70alpha).create();
        } else {
            SpanUtils with2 = SpanUtils.with(textView);
            Intrinsics.checkNotNullExpressionValue(with2, "with(tvName)");
            String username2 = item.getUsername();
            Intrinsics.checkNotNullExpressionValue(username2, "item.username");
            CommonExtKt.addText(with2, username2, 16, R.color.white_30alpha).create();
        }
        if (item.getDevices().size() <= 1) {
            textView2.setText(item.getDevices().size() + " Screen Playing");
        } else {
            textView2.setText(item.getDevices().size() + " Screens Playing");
        }
        if (App.getUserData().getFamily() == 0) {
            CommonExtKt.gone(textView);
            CommonExtKt.gone(textView2);
        } else {
            CommonExtKt.visible(textView);
            CommonExtKt.visible(textView2);
        }
        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
        final CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_online_screen, ScreenManageDialog$initData$2$adapter$1.INSTANCE, item.getDevices());
        commBaseAdapter.addChildClickViewIds(R.id.tvStop);
        if (ScreenUtils.isLandscape(this.this$0.getContext())) {
            recyclerView.setLayoutManager(new GridLayoutManager(this.this$0.getContext(), 2));
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new GridSpacingItemDecoration2(2, DensityUtils.dp2px(App.getContext(), 15.0f), false));
                recyclerView.setTag("added");
            }
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this.this$0.getContext()));
        }
        CommonExtKt.disableRefreshAnimation(recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(commBaseAdapter);
        final ScreenManageDialog screenManageDialog = this.this$0;
        commBaseAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ScreenManageDialog$initData$2$UuAsKu2rc-ZA_5VMrfHF94otb08
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ScreenManageDialog$initData$2.m1104invoke$lambda0(CommBaseAdapter.this, screenManageDialog, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m1104invoke$lambda0(CommBaseAdapter adapter, ScreenManageDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        DeviceModelResponse.DeviceModel item3 = (DeviceModelResponse.DeviceModel) adapter.getItem(i);
        Intrinsics.checkNotNullExpressionValue(item3, "item3");
        this$0.deleteDevice(item3);
    }
}
