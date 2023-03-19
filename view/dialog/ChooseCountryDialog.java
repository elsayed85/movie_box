package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ChooseCountryDialog.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001bB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog;", "Lcom/movieboxpro/android/view/dialog/BaseCenterDialog;", "context", "Landroid/content/Context;", "pos", "", "countryList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/FilterCountry;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;ILjava/util/ArrayList;)V", "country", "", "countryAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "lastCountryPosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog$OnChooseCountryListener;", "selectCountryPosition", "initData", "", "initDialogWidth", "", "initLayoutId", "initListener", "initView", "setListener", "OnChooseCountryListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseCountryDialog extends BaseCenterDialog {
    private String country;
    private CommBaseAdapter<FilterCountry> countryAdapter;
    private final ArrayList<FilterCountry> countryList;
    private int lastCountryPosition;
    private OnChooseCountryListener listener;
    private final int pos;
    private int selectCountryPosition;

    /* compiled from: ChooseCountryDialog.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog$OnChooseCountryListener;", "", "onChooseCountry", "", "pos", "", "country", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnChooseCountryListener {
        void onChooseCountry(int i, String str);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public float initDialogWidth() {
        return 0.9f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public int initLayoutId() {
        return R.layout.dialog_choose_country;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initView() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseCountryDialog(Context context, int i, ArrayList<FilterCountry> countryList) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(countryList, "countryList");
        this.pos = i;
        this.countryList = countryList;
        this.lastCountryPosition = -1;
        this.country = "";
    }

    public final void setListener(OnChooseCountryListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initListener() {
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCountryDialog$qfmsMqs0umLgSDoySEtiSRRBSUU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseCountryDialog.m944initListener$lambda0(ChooseCountryDialog.this, baseQuickAdapter, view, i);
            }
        });
        ((Button) findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCountryDialog$lQRdPEq8-eNLx16blrcJIo_8jds
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseCountryDialog.m945initListener$lambda1(ChooseCountryDialog.this, view);
            }
        });
        ((WaveSideBar) findViewById(R.id.sideBar)).setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCountryDialog$cDPA1jyiLQwppPUQwTChx5E4rbM
            @Override // com.gjiazhe.wavesidebar.WaveSideBar.OnSelectIndexItemListener
            public final void onSelectIndexItem(String str) {
                ChooseCountryDialog.m946initListener$lambda3(ChooseCountryDialog.this, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m944initListener$lambda0(ChooseCountryDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastCountryPosition == i) {
            return;
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
        CommBaseAdapter<FilterCountry> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        String country = commBaseAdapter.getItem(i).getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "countryAdapter.getItem(position).country");
        this$0.country = country;
        this$0.selectCountryPosition = i;
        CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter3 = null;
        }
        commBaseAdapter3.getItem(i).setSelect(true);
        CommBaseAdapter<FilterCountry> commBaseAdapter4 = this$0.countryAdapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter4 = null;
        }
        FilterCountry itemOrNull = commBaseAdapter4.getItemOrNull(this$0.lastCountryPosition);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter5 = this$0.countryAdapter;
        if (commBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter5 = null;
        }
        commBaseAdapter5.notifyItemChanged(i);
        CommBaseAdapter<FilterCountry> commBaseAdapter6 = this$0.countryAdapter;
        if (commBaseAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter6;
        }
        commBaseAdapter2.notifyItemChanged(this$0.lastCountryPosition);
        this$0.lastCountryPosition = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m945initListener$lambda1(ChooseCountryDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        FilterCountry itemOrNull = commBaseAdapter.getItemOrNull(this$0.lastCountryPosition);
        OnChooseCountryListener onChooseCountryListener = this$0.listener;
        if (onChooseCountryListener != null) {
            onChooseCountryListener.onChooseCountry(this$0.lastCountryPosition, itemOrNull != null ? itemOrNull.getCountry() : null);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m946initListener$lambda3(ChooseCountryDialog this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        int i = 0;
        for (Object obj : commBaseAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String country = ((FilterCountry) obj).getCountry();
            Intrinsics.checkNotNullExpressionValue(country, "country.country");
            String valueOf = String.valueOf(StringsKt.first(country));
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String upperCase = valueOf.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            if (Intrinsics.areEqual(upperCase, str)) {
                RecyclerView.LayoutManager layoutManager = ((RecyclerView) this$0.findViewById(R.id.rvCountry)).getLayoutManager();
                if (layoutManager == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                }
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
                return;
            }
            i = i2;
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseCenterDialog
    public void initData() {
        final ArrayList<FilterCountry> arrayList = this.countryList;
        ArrayList<FilterCountry> arrayList2 = arrayList;
        if (arrayList2.size() > 1) {
            CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog$initData$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    String country = ((FilterCountry) t).getCountry();
                    Intrinsics.checkNotNullExpressionValue(country, "it.country");
                    String country2 = ((FilterCountry) t2).getCountry();
                    Intrinsics.checkNotNullExpressionValue(country2, "it.country");
                    return ComparisonsKt.compareValues(Character.valueOf(StringsKt.first(country)), Character.valueOf(StringsKt.first(country2)));
                }
            });
        }
        FilterCountry filterCountry = (FilterCountry) CollectionsKt.getOrNull(arrayList2, this.pos);
        if (filterCountry != null) {
            filterCountry.setSelect(true);
        }
        this.lastCountryPosition = this.pos;
        this.countryAdapter = new CommBaseAdapter<>(R.layout.adapter_filter_country_item, new ChooseCountryDialog$initData$2(this), arrayList2);
        ((RecyclerView) findViewById(R.id.rvCountry)).setLayoutManager(new LinearLayoutManager(getContext()));
        ((RecyclerView) findViewById(R.id.rvCountry)).addItemDecoration(StickyDecoration.Builder.init(new GroupListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseCountryDialog$kEOf3-Q9Ey_hJi5-HG1f9GqdrFE
            @Override // com.gavin.com.library.listener.GroupListener
            public final String getGroupName(int i) {
                String m943initData$lambda5;
                m943initData$lambda5 = ChooseCountryDialog.m943initData$lambda5(arrayList, i);
                return m943initData$lambda5;
            }
        }).setGroupBackground(ContextCompat.getColor(App.getContext(), R.color.color_main_back)).setGroupTextColor(ContextCompat.getColor(App.getContext(), R.color.white_30alpha)).setDivideHeight(0).setGroupHeight(DensityUtils.dp2px(App.getContext(), 30.0f)).setDivideColor(ContextCompat.getColor(App.getContext(), R.color.white_70alpha)).setTextSideMargin(DensityUtils.dp2px(App.getContext(), 20.0f)).build());
        RecyclerView rvCountry = (RecyclerView) findViewById(R.id.rvCountry);
        Intrinsics.checkNotNullExpressionValue(rvCountry, "rvCountry");
        CommonExtKt.disableRefreshAnimation(rvCountry);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvCountry);
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        recyclerView.setAdapter(commBaseAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-5  reason: not valid java name */
    public static final String m943initData$lambda5(ArrayList list, int i) {
        Intrinsics.checkNotNullParameter(list, "$list");
        if (CommonExtKt.checkIndexLegal(i, list)) {
            String country = ((FilterCountry) list.get(i)).getCountry();
            Intrinsics.checkNotNullExpressionValue(country, "list[position].country");
            return String.valueOf(StringsKt.first(country));
        }
        return null;
    }
}
