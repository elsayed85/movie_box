package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.fragment.home.SpecialFilterFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SpecialFilterView.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0006\u0010\u001a\u001a\u00020\u0018J\u0014\u0010\u001b\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dJ\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/widget/SpecialFilterView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/SpecialFilterModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "bgList", "", "[Ljava/lang/Integer;", "lastSelectPosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment$OnTopFilterClickListener;", "mContext", "viewList", "Landroid/util/SparseArray;", "Landroid/view/View;", "initListener", "", "initView", "resetFilter", "setFilterData", "list", "", "setTopFilterClickListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SpecialFilterView extends FrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> adapter;
    private Integer[] bgList;
    private int lastSelectPosition;
    private SpecialFilterFragment.OnTopFilterClickListener listener;
    private final Context mContext;
    private SparseArray<View> viewList;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.mContext = context;
        this.viewList = new SparseArray<>();
        this.bgList = new Integer[]{Integer.valueOf((int) R.drawable.special_filter_bg_one_shape), Integer.valueOf((int) R.drawable.special_filter_bg_two_shape), Integer.valueOf((int) R.drawable.special_filter_bg_three_shape), Integer.valueOf((int) R.drawable.special_filter_bg_four_shape)};
        LayoutInflater.from(this.mContext).inflate(R.layout.fragment_specical_filter, this);
        initView();
        initListener();
        this.lastSelectPosition = -1;
    }

    public /* synthetic */ SpecialFilterView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void setTopFilterClickListener(SpecialFilterFragment.OnTopFilterClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView() {
        final ArrayList arrayList = new ArrayList();
        this.adapter = new BaseQuickAdapter<SpecialFilterModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.widget.SpecialFilterView$initView$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SpecialFilterModel item) {
                SparseArray sparseArray;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.container);
                TextView textView = (TextView) holder.getView(R.id.textView);
                ImageView imageView = (ImageView) holder.getView(R.id.imageView);
                View view = holder.getView(R.id.view);
                sparseArray = SpecialFilterView.this.viewList;
                sparseArray.put(holder.getAdapterPosition(), view);
                if (item.isSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.invisible(imageView);
                }
                constraintLayout.setBackgroundResource(item.getBgId());
                textView.setText(item.getDisplay_name());
            }
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Context context = getContext();
        if (context != null) {
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            CommonExtKt.initLinearAndMargin(recyclerView, context, false, 15, false);
        }
        recyclerView.setNestedScrollingEnabled(false);
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
    }

    public final void setFilterData(List<SpecialFilterModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Integer[] numArr = this.bgList;
            ((SpecialFilterModel) obj).setBgId(numArr[i % numArr.length].intValue());
            i = i2;
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setNewData(list);
    }

    public final void resetFilter() {
        int size = this.viewList.size();
        if (size >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                View view = this.viewList.get(i);
                if (view != null) {
                    CommonExtKt.gone(view);
                }
                if (i == size) {
                    break;
                }
                i = i2;
            }
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        SpecialFilterModel itemOrNull = baseQuickAdapter.getItemOrNull(this.lastSelectPosition);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.notifyItemChanged(this.lastSelectPosition);
        this.lastSelectPosition = -1;
    }

    private final void initListener() {
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$SpecialFilterView$h9Um_2plp9iSlIlZYUbA_wkJXzY
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SpecialFilterView.m1432initListener$lambda2(SpecialFilterView.this, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1432initListener$lambda2(SpecialFilterView this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        int i2 = this$0.lastSelectPosition;
        int i3 = 0;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = null;
        if (i2 == i) {
            int size = this$0.viewList.size();
            if (size >= 0) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    View view = this$0.viewList.get(i4);
                    if (view != null) {
                        CommonExtKt.gone(view);
                    }
                    if (i4 == size) {
                        break;
                    }
                    i4 = i5;
                }
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = this$0.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter2 = null;
            }
            SpecialFilterModel itemOrNull = baseQuickAdapter2.getItemOrNull(this$0.lastSelectPosition);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = this$0.adapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter = baseQuickAdapter3;
            }
            baseQuickAdapter.notifyItemChanged(this$0.lastSelectPosition);
            this$0.lastSelectPosition = -1;
            SpecialFilterFragment.OnTopFilterClickListener onTopFilterClickListener = this$0.listener;
            if (onTopFilterClickListener == null) {
                return;
            }
            onTopFilterClickListener.onFilterClicked(false, "");
            return;
        }
        if (i2 != -1) {
            View view2 = this$0.viewList.get(i2);
            if (view2 != null) {
                CommonExtKt.visible(view2);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter4 = this$0.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter4 = null;
            }
            SpecialFilterModel itemOrNull2 = baseQuickAdapter4.getItemOrNull(this$0.lastSelectPosition);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter5 = this$0.adapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(this$0.lastSelectPosition);
        } else {
            int size2 = this$0.viewList.size();
            if (size2 >= 0) {
                while (true) {
                    int i6 = i3 + 1;
                    View view3 = this$0.viewList.get(i3);
                    if (view3 != null) {
                        CommonExtKt.visible(view3);
                    }
                    if (i3 == size2) {
                        break;
                    }
                    i3 = i6;
                }
            }
        }
        View view4 = this$0.viewList.get(i);
        if (view4 != null) {
            CommonExtKt.gone(view4);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter6 = this$0.adapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.getItem(i).setSelect(true);
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter7 = this$0.adapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter7 = null;
        }
        baseQuickAdapter7.notifyItemChanged(i);
        this$0.lastSelectPosition = i;
        SpecialFilterFragment.OnTopFilterClickListener onTopFilterClickListener2 = this$0.listener;
        if (onTopFilterClickListener2 == null) {
            return;
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter8 = this$0.adapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter8;
        }
        String id = baseQuickAdapter.getItem(i).getId();
        onTopFilterClickListener2.onFilterClicked(true, id != null ? id : "");
    }
}
