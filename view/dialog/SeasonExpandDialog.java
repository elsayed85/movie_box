package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SeasonEpisodeAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.databinding.DialogSeasonExpandBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: SeasonExpandDialog.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 22\u00020\u0001:\u000223B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0016JJ\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0002J$\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u000e\u00100\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u00101\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SeasonExpandDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/SeasonEpisodeAdapter;", "binding", "Lcom/movieboxpro/android/databinding/DialogSeasonExpandBinding;", "favoriteItem", "Lcom/movieboxpro/android/model/FavoriteItem;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/SeasonExpandDialog$OnMarkListener;", "<set-?>", "", "tid", "getTid", "()Ljava/lang/String;", "setTid", "(Ljava/lang/String;)V", "tid$delegate", "Lkotlin/properties/ReadWriteProperty;", "checkShowProgress", "", "list", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "initData", "", "initListener", "initView", "markTv", "id", "season", "episode", DownloadInfo.DOWNLOAD_OVER, "position", "favoriteSeasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "favoriteEpisodeItem", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setData", "setListener", "Companion", "OnMarkListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SeasonExpandDialog extends BaseBindingCenterDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SeasonExpandDialog.class, "tid", "getTid()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    private SeasonEpisodeAdapter adapter;
    private DialogSeasonExpandBinding binding;
    private FavoriteItem favoriteItem;
    private OnMarkListener listener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ReadWriteProperty tid$delegate = FragmentArgsKt.arg(this);

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
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

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    private final String getTid() {
        return (String) this.tid$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTid(String str) {
        this.tid$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    public final void setListener(OnMarkListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* compiled from: SeasonExpandDialog.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JJ\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SeasonExpandDialog$OnMarkListener;", "", "onMark", "", "id", "", "season", "episode", DownloadInfo.DOWNLOAD_OVER, "", "position", "favoriteSeasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "favoriteEpisodeItem", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "onRefresh", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnMarkListener {
        void onMark(String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem);

        void onRefresh();

        /* compiled from: SeasonExpandDialog.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void onMark$default(OnMarkListener onMarkListener, String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem, int i3, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onMark");
                }
                onMarkListener.onMark(str, str2, str3, i, i2, (i3 & 32) != 0 ? null : favoriteSeasonItem, (i3 & 64) != 0 ? null : favoriteEpisodeItem);
            }
        }
    }

    /* compiled from: SeasonExpandDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SeasonExpandDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/SeasonExpandDialog;", "tid", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SeasonExpandDialog newInstance(String str) {
            SeasonExpandDialog seasonExpandDialog = new SeasonExpandDialog();
            if (str == null) {
                str = "";
            }
            seasonExpandDialog.setTid(str);
            return seasonExpandDialog;
        }
    }

    public final void setData(FavoriteItem favoriteItem) {
        Intrinsics.checkNotNullParameter(favoriteItem, "favoriteItem");
        this.favoriteItem = favoriteItem;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_season_expand, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…expand, container, false)");
        DialogSeasonExpandBinding dialogSeasonExpandBinding = (DialogSeasonExpandBinding) inflate;
        this.binding = dialogSeasonExpandBinding;
        if (dialogSeasonExpandBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSeasonExpandBinding = null;
        }
        View root = dialogSeasonExpandBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    static /* synthetic */ void markTv$default(SeasonExpandDialog seasonExpandDialog, String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem, int i3, Object obj) {
        seasonExpandDialog.markTv(str, str2, str3, i, i2, (i3 & 32) != 0 ? null : favoriteSeasonItem, (i3 & 64) != 0 ? null : favoriteEpisodeItem);
    }

    private final void markTv(String str, String str2, String str3, int i, int i2, FavoriteSeasonItem favoriteSeasonItem, FavoriteEpisodeItem favoriteEpisodeItem) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this), new SeasonExpandDialog$markTv$1(this), null, new SeasonExpandDialog$markTv$2(this), null, new SeasonExpandDialog$markTv$3(this, str, str2, str3, i, i2, favoriteSeasonItem, favoriteEpisodeItem), 10, null);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        DialogSeasonExpandBinding dialogSeasonExpandBinding = this.binding;
        SeasonEpisodeAdapter seasonEpisodeAdapter = null;
        if (dialogSeasonExpandBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSeasonExpandBinding = null;
        }
        dialogSeasonExpandBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new SeasonEpisodeAdapter(this, this, new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SeasonExpandDialog$pEGJ5a2AHbOS-AGEPoW5FbAIoiE
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                SeasonExpandDialog.m1106initData$lambda0(SeasonExpandDialog.this);
            }
        });
        DialogSeasonExpandBinding dialogSeasonExpandBinding2 = this.binding;
        if (dialogSeasonExpandBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSeasonExpandBinding2 = null;
        }
        RecyclerView recyclerView = dialogSeasonExpandBinding2.recyclerView;
        SeasonEpisodeAdapter seasonEpisodeAdapter2 = this.adapter;
        if (seasonEpisodeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter2 = null;
        }
        recyclerView.setAdapter(seasonEpisodeAdapter2);
        FavoriteItem favoriteItem = this.favoriteItem;
        if (favoriteItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
            favoriteItem = null;
        }
        favoriteItem.setExpanded(true);
        SeasonEpisodeAdapter seasonEpisodeAdapter3 = this.adapter;
        if (seasonEpisodeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter3 = null;
        }
        FavoriteItem[] favoriteItemArr = new FavoriteItem[1];
        FavoriteItem favoriteItem2 = this.favoriteItem;
        if (favoriteItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
            favoriteItem2 = null;
        }
        favoriteItemArr[0] = favoriteItem2;
        seasonEpisodeAdapter3.setList(CollectionsKt.arrayListOf(favoriteItemArr));
        SeasonEpisodeAdapter seasonEpisodeAdapter4 = this.adapter;
        if (seasonEpisodeAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter4 = null;
        }
        seasonEpisodeAdapter4.addChildClickViewIds(R.id.ivExpand, R.id.ivMark);
        SeasonEpisodeAdapter seasonEpisodeAdapter5 = this.adapter;
        if (seasonEpisodeAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter5 = null;
        }
        seasonEpisodeAdapter5.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SeasonExpandDialog$iOylxgwSr_8HJWoZFBQhK6JQwzQ
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SeasonExpandDialog.m1107initData$lambda1(SeasonExpandDialog.this, baseQuickAdapter, view, i);
            }
        });
        SeasonEpisodeAdapter seasonEpisodeAdapter6 = this.adapter;
        if (seasonEpisodeAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            seasonEpisodeAdapter = seasonEpisodeAdapter6;
        }
        seasonEpisodeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SeasonExpandDialog$74G6oThYTQh4anBmjp4X2MkcRnQ
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SeasonExpandDialog.m1108initData$lambda2(SeasonExpandDialog.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0  reason: not valid java name */
    public static final void m1106initData$lambda0(SeasonExpandDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnMarkListener onMarkListener = this$0.listener;
        if (onMarkListener == null) {
            return;
        }
        onMarkListener.onRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m1107initData$lambda1(SeasonExpandDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        SeasonEpisodeAdapter seasonEpisodeAdapter = this$0.adapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter2 = null;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        BaseNode item = seasonEpisodeAdapter.getItem(i);
        int id = view.getId();
        if (id != R.id.ivExpand) {
            if (id != R.id.ivMark) {
                return;
            }
            if (item instanceof FavoriteSeasonItem) {
                FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
                markTv$default(this$0, this$0.getTid(), String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, favoriteSeasonItem, null, 64, null);
                return;
            } else if (item instanceof FavoriteEpisodeItem) {
                FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
                markTv$default(this$0, this$0.getTid(), String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, null, favoriteEpisodeItem, 32, null);
                return;
            } else {
                return;
            }
        }
        SeasonEpisodeAdapter seasonEpisodeAdapter3 = this$0.adapter;
        if (seasonEpisodeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter3 = null;
        }
        seasonEpisodeAdapter3.expandOrCollapse(i, true, true, 110);
        SeasonEpisodeAdapter seasonEpisodeAdapter4 = this$0.adapter;
        if (seasonEpisodeAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            seasonEpisodeAdapter2 = seasonEpisodeAdapter4;
        }
        seasonEpisodeAdapter2.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-2  reason: not valid java name */
    public static final void m1108initData$lambda2(SeasonExpandDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        SeasonEpisodeAdapter seasonEpisodeAdapter = this$0.adapter;
        SeasonEpisodeAdapter seasonEpisodeAdapter2 = null;
        if (seasonEpisodeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            seasonEpisodeAdapter = null;
        }
        BaseNode item = seasonEpisodeAdapter.getItem(i);
        if (item instanceof FavoriteItem) {
            SeasonEpisodeAdapter seasonEpisodeAdapter3 = this$0.adapter;
            if (seasonEpisodeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                seasonEpisodeAdapter3 = null;
            }
            seasonEpisodeAdapter3.expandOrCollapse(i, true, true, 110);
            SeasonEpisodeAdapter seasonEpisodeAdapter4 = this$0.adapter;
            if (seasonEpisodeAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                seasonEpisodeAdapter2 = seasonEpisodeAdapter4;
            }
            seasonEpisodeAdapter2.notifyItemChanged(i);
        } else if (item instanceof FavoriteSeasonItem) {
            FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) item;
            markTv$default(this$0, this$0.getTid(), String.valueOf(favoriteSeasonItem.getSeason()), "", favoriteSeasonItem.getWatchedCount() == favoriteSeasonItem.getEpisode_list().size() ? 0 : 1, i, favoriteSeasonItem, null, 64, null);
        } else if (item instanceof FavoriteEpisodeItem) {
            FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) item;
            markTv$default(this$0, this$0.getTid(), String.valueOf(favoriteEpisodeItem.getSeason()), String.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver() == 1 ? 0 : 1, i, null, favoriteEpisodeItem, 32, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkShowProgress(ArrayList<Integer> arrayList) {
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int intValue = ((Number) obj).intValue();
            if (i == 0 && intValue == 0) {
                return false;
            }
            Integer num = (Integer) CollectionsKt.getOrNull(arrayList, i - 1);
            if (num != null && intValue == 1 && num.intValue() == 0) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
