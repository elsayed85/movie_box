package com.movieboxpro.android.view.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SpecialFilterFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J&\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\u0006\u0010\u001f\u001a\u00020\u0013J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0010`\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/SpecialFilterModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "bgList", "", "", "[Ljava/lang/Integer;", "lastSelectPosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment$OnTopFilterClickListener;", "viewList", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "initData", "", "initListener", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "requestData", "resetFilter", "setTopFilterClickListener", "Companion", "OnTopFilterClickListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SpecialFilterFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> adapter;
    private OnTopFilterClickListener listener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<View> viewList = new ArrayList<>();
    private Integer[] bgList = {Integer.valueOf((int) R.drawable.special_filter_bg_one_shape), Integer.valueOf((int) R.drawable.special_filter_bg_two_shape), Integer.valueOf((int) R.drawable.special_filter_bg_three_shape), Integer.valueOf((int) R.drawable.special_filter_bg_four_shape)};
    private int lastSelectPosition = -1;

    /* compiled from: SpecialFilterFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment$OnTopFilterClickListener;", "", "onFilterClicked", "", "selected", "", "id", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface OnTopFilterClickListener {
        void onFilterClicked(boolean z, String str);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_specical_filter, viewGroup, false);
    }

    public final void setTopFilterClickListener(OnTopFilterClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initListener();
        requestData();
    }

    private final void requestData() {
        Bundle arguments = getArguments();
        ((ObservableSubscribeProxy) Http.getService().Top_list(API.BASE_URL, "Top_list", arguments == null ? 1 : arguments.getInt("boxType")).compose(RxUtils.rxTranslate2List(SpecialFilterModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<SpecialFilterModel>>() { // from class: com.movieboxpro.android.view.fragment.home.SpecialFilterFragment$requestData$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<SpecialFilterModel> model) {
                BaseQuickAdapter baseQuickAdapter;
                Integer[] numArr;
                Integer[] numArr2;
                Intrinsics.checkNotNullParameter(model, "model");
                SpecialFilterFragment specialFilterFragment = SpecialFilterFragment.this;
                int i = 0;
                for (Object obj : model) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    numArr = specialFilterFragment.bgList;
                    numArr2 = specialFilterFragment.bgList;
                    ((SpecialFilterModel) obj).setBgId(numArr[i % numArr2.length].intValue());
                    i = i2;
                }
                baseQuickAdapter = SpecialFilterFragment.this.adapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter = null;
                }
                baseQuickAdapter.setNewData(model);
            }
        });
    }

    public final void resetFilter() {
        for (View view : this.viewList) {
            if (view != null) {
                CommonExtKt.gone(view);
            }
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        SpecialFilterModel item = baseQuickAdapter.getItem(this.lastSelectPosition);
        if (item != null) {
            item.setSelect(false);
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
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.home.-$$Lambda$SpecialFilterFragment$3CHi_-YllGx-xG3GLtvzz077vcs
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SpecialFilterFragment.m1248initListener$lambda3(SpecialFilterFragment.this, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1248initListener$lambda3(SpecialFilterFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String id;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        int i2 = this$0.lastSelectPosition;
        String str = "";
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = null;
        if (i2 != i) {
            if (i2 != -1) {
                View view = this$0.viewList.get(i2);
                if (view != null) {
                    CommonExtKt.visible(view);
                }
                BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = this$0.adapter;
                if (baseQuickAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter2 = null;
                }
                SpecialFilterModel item = baseQuickAdapter2.getItem(this$0.lastSelectPosition);
                if (item != null) {
                    item.setSelect(false);
                }
                BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                baseQuickAdapter3.notifyItemChanged(this$0.lastSelectPosition);
            } else {
                for (View view2 : this$0.viewList) {
                    if (view2 != null) {
                        CommonExtKt.visible(view2);
                    }
                }
            }
            View view3 = this$0.viewList.get(i);
            if (view3 != null) {
                CommonExtKt.gone(view3);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter4 = this$0.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter4 = null;
            }
            SpecialFilterModel item2 = baseQuickAdapter4.getItem(i);
            if (item2 != null) {
                item2.setSelect(true);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter5 = this$0.adapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(i);
            this$0.lastSelectPosition = i;
            OnTopFilterClickListener onTopFilterClickListener = this$0.listener;
            if (onTopFilterClickListener == null) {
                return;
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter6 = this$0.adapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter = baseQuickAdapter6;
            }
            SpecialFilterModel item3 = baseQuickAdapter.getItem(i);
            if (item3 != null && (id = item3.getId()) != null) {
                str = id;
            }
            onTopFilterClickListener.onFilterClicked(true, str);
            return;
        }
        for (View view4 : this$0.viewList) {
            if (view4 != null) {
                CommonExtKt.gone(view4);
            }
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter7 = this$0.adapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter7 = null;
        }
        SpecialFilterModel item4 = baseQuickAdapter7.getItem(this$0.lastSelectPosition);
        if (item4 != null) {
            item4.setSelect(false);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter8 = this$0.adapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter8;
        }
        baseQuickAdapter.notifyItemChanged(this$0.lastSelectPosition);
        this$0.lastSelectPosition = -1;
        OnTopFilterClickListener onTopFilterClickListener2 = this$0.listener;
        if (onTopFilterClickListener2 == null) {
            return;
        }
        onTopFilterClickListener2.onFilterClicked(false, "");
    }

    private final void initData() {
        final ArrayList arrayList = new ArrayList();
        this.adapter = new BaseQuickAdapter<SpecialFilterModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.fragment.home.SpecialFilterFragment$initData$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SpecialFilterModel item) {
                ArrayList arrayList2;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.container);
                TextView textView = (TextView) holder.getView(R.id.textView);
                ImageView imageView = (ImageView) holder.getView(R.id.imageView);
                View view = holder.getView(R.id.view);
                arrayList2 = SpecialFilterFragment.this.viewList;
                arrayList2.add(view);
                if (item.isSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.invisible(imageView);
                }
                constraintLayout.setBackgroundResource(item.getBgId());
                textView.setText(item.getDisplay_name());
            }
        };
        Context context = getContext();
        if (context != null) {
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            CommonExtKt.initLinearAndMargin(recyclerView, context, false, 15, false);
        }
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setNestedScrollingEnabled(false);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView2.setAdapter(baseQuickAdapter);
    }

    /* compiled from: SpecialFilterFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/home/SpecialFilterFragment;", "boxType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SpecialFilterFragment newInstance(int i) {
            SpecialFilterFragment specialFilterFragment = new SpecialFilterFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("boxType", i);
            specialFilterFragment.setArguments(bundle);
            return specialFilterFragment;
        }
    }
}
