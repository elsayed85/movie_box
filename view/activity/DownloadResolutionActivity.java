package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.CommBaseDragAdapter;
import com.movieboxpro.android.databinding.ActivityDownloadResolutionBinding;
import com.movieboxpro.android.livedata.DownloadRuleChangedLiveData;
import com.movieboxpro.android.model.DownloadQualityRule;
import com.movieboxpro.android.utils.SettingManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
/* compiled from: DownloadResolutionActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadResolutionActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityDownloadResolutionBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityDownloadResolutionBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "initData", "", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DownloadResolutionActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(DownloadResolutionActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityDownloadResolutionBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityDownloadResolutionBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
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

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    private final ActivityDownloadResolutionBinding getBinding() {
        return (ActivityDownloadResolutionBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        ArrayList arrayList = new ArrayList();
        for (String str : StringsKt.split$default((CharSequence) SettingManager.INSTANCE.getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null)) {
            arrayList.add(new DownloadQualityRule(str, str));
        }
        final CommBaseDragAdapter commBaseDragAdapter = new CommBaseDragAdapter(R.layout.adapter_download_rule_item, DownloadResolutionActivity$initData$adapter$1.INSTANCE, arrayList);
        commBaseDragAdapter.getDraggableModule().setSwipeEnabled(false);
        commBaseDragAdapter.getDraggableModule().setDragEnabled(true);
        commBaseDragAdapter.getDraggableModule().setDragOnLongPressEnabled(false);
        commBaseDragAdapter.getDraggableModule().getItemTouchHelperCallback().setSwipeMoveFlags(16);
        commBaseDragAdapter.getDraggableModule().setToggleViewId(R.id.ivMove);
        commBaseDragAdapter.getDraggableModule().setOnItemDragListener(new OnItemDragListener() { // from class: com.movieboxpro.android.view.activity.DownloadResolutionActivity$initData$2
            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2) {
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {
                StringBuilder sb = new StringBuilder();
                CommBaseDragAdapter<DownloadQualityRule> commBaseDragAdapter2 = commBaseDragAdapter;
                int i2 = 0;
                for (Object obj : commBaseDragAdapter.getData()) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    DownloadQualityRule downloadQualityRule = (DownloadQualityRule) obj;
                    if (i2 != commBaseDragAdapter2.getData().size() - 1) {
                        sb.append(downloadQualityRule.getQuality());
                        sb.append(",");
                    } else {
                        sb.append(downloadQualityRule.getQuality());
                    }
                    i2 = i3;
                }
                SettingManager settingManager = SettingManager.INSTANCE;
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "rules.toString()");
                settingManager.saveDownloadRule(sb2);
                DownloadRuleChangedLiveData.Companion.get().setValue(sb.toString());
            }
        });
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setAdapter(commBaseDragAdapter);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Resolution Priority");
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$DownloadResolutionActivity$3aI2_chSVIbsaAZgrP-XpzCbjcA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadResolutionActivity.m150initView$lambda1(DownloadResolutionActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m150initView$lambda1(DownloadResolutionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
