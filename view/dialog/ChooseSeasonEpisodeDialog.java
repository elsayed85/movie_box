package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.SeasonEpisodeSelectableModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChooseSeasonEpisodeDialog.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0011H\u0016J\u001a\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "chooseSeason", "", "chooseYear", "lastSeasonPosition", "", "lastYearPosition", "onSeasonYearChooseListener", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog$OnSeasonYearChooseListener;", "seasonAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/SeasonEpisodeSelectableModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "yearAdapter", "initData", "", "initListener", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onStart", "onViewCreated", "view", "Companion", "OnSeasonYearChooseListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseSeasonEpisodeDialog extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private OnSeasonYearChooseListener onSeasonYearChooseListener;
    private BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> seasonAdapter;
    private BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> yearAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastSeasonPosition = -1;
    private int lastYearPosition = -1;
    private String chooseSeason = "";
    private String chooseYear = "";

    /* compiled from: ChooseSeasonEpisodeDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog$OnSeasonYearChooseListener;", "", "onSeasonYearChoose", "", "season", "", "year", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnSeasonYearChooseListener {
        void onSeasonYearChoose(String str, String str2);
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof OnSeasonYearChooseListener) {
            this.onSeasonYearChooseListener = (OnSeasonYearChooseListener) context;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.RightSheetDialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Resources resources;
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            Context context = getContext();
            DisplayMetrics displayMetrics = (context == null || (resources = context.getResources()) == null) ? null : resources.getDisplayMetrics();
            int i = 0;
            window.getDecorView().setPadding(0, 0, 0, 0);
            Context context2 = getContext();
            window.setBackgroundDrawable(context2 != null ? ContextCompat.getDrawable(context2, 17170445) : null);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 5;
            if (displayMetrics != null) {
                double d = displayMetrics.widthPixels;
                Double.isNaN(d);
                i = (int) (d * 0.75d);
            }
            attributes.width = i;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_choose_season_episode, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    private final void initListener() {
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter = this.seasonAdapter;
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseSeasonEpisodeDialog$sIEr0mkVntoAAk41o9ngFBL8BWY
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                ChooseSeasonEpisodeDialog.m962initListener$lambda1(ChooseSeasonEpisodeDialog.this, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter3 = this.yearAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ChooseSeasonEpisodeDialog$CgAZC4CiRHw4HU3PIWVMb31ARgk
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                ChooseSeasonEpisodeDialog.m963initListener$lambda2(ChooseSeasonEpisodeDialog.this, baseQuickAdapter4, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m962initListener$lambda1(ChooseSeasonEpisodeDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String text;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastSeasonPosition == i) {
            return;
        }
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter = this$0.seasonAdapter;
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
            baseQuickAdapter = null;
        }
        SeasonEpisodeSelectableModel item = baseQuickAdapter.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter3 = this$0.seasonAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.notifyItemChanged(i);
        if (this$0.lastSeasonPosition != -1) {
            BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter4 = this$0.seasonAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
                baseQuickAdapter4 = null;
            }
            SeasonEpisodeSelectableModel item2 = baseQuickAdapter4.getItem(this$0.lastSeasonPosition);
            if (item2 != null) {
                item2.setSelect(false);
            }
            BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter5 = this$0.seasonAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(this$0.lastSeasonPosition);
        }
        this$0.lastSeasonPosition = i;
        this$0.dismiss();
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter6 = this$0.seasonAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter6;
        }
        SeasonEpisodeSelectableModel item3 = baseQuickAdapter2.getItem(i);
        String str = "";
        if (item3 != null && (text = item3.getText()) != null) {
            str = text;
        }
        this$0.chooseSeason = str;
        OnSeasonYearChooseListener onSeasonYearChooseListener = this$0.onSeasonYearChooseListener;
        if (onSeasonYearChooseListener == null) {
            return;
        }
        onSeasonYearChooseListener.onSeasonYearChoose(str, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m963initListener$lambda2(ChooseSeasonEpisodeDialog this$0, BaseQuickAdapter adapter, View view, int i) {
        String text;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        if (this$0.lastYearPosition == i) {
            return;
        }
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter = this$0.yearAdapter;
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter = null;
        }
        SeasonEpisodeSelectableModel item = baseQuickAdapter.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter3 = this$0.yearAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.notifyItemChanged(i);
        if (this$0.lastYearPosition != -1) {
            BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter4 = this$0.yearAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter4 = null;
            }
            SeasonEpisodeSelectableModel item2 = baseQuickAdapter4.getItem(this$0.lastYearPosition);
            if (item2 != null) {
                item2.setSelect(false);
            }
            BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter5 = this$0.yearAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(this$0.lastYearPosition);
        }
        this$0.lastYearPosition = i;
        this$0.dismiss();
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter6 = this$0.yearAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter6;
        }
        SeasonEpisodeSelectableModel item3 = baseQuickAdapter2.getItem(i);
        String str = "";
        if (item3 != null && (text = item3.getText()) != null) {
            str = text;
        }
        this$0.chooseYear = str;
        OnSeasonYearChooseListener onSeasonYearChooseListener = this$0.onSeasonYearChooseListener;
        if (onSeasonYearChooseListener == null) {
            return;
        }
        onSeasonYearChooseListener.onSeasonYearChoose("0", str);
    }

    private final void initData() {
        String string;
        String string2;
        Bundle arguments = getArguments();
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter = null;
        String[] stringArray = arguments == null ? null : arguments.getStringArray("seasons");
        Bundle arguments2 = getArguments();
        String str = "";
        if (arguments2 == null || (string = arguments2.getString("currentSeason")) == null) {
            string = "";
        }
        Bundle arguments3 = getArguments();
        if (arguments3 != null && (string2 = arguments3.getString("currentYear")) != null) {
            str = string2;
        }
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        if (stringArray != null) {
            int length = stringArray.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                String s = stringArray[i2];
                i2++;
                int i4 = i3 + 1;
                if (Intrinsics.areEqual(string, s)) {
                    this.lastSeasonPosition = i3;
                    Intrinsics.checkNotNullExpressionValue(s, "s");
                    this.chooseSeason = s;
                    arrayList.add(new SeasonEpisodeSelectableModel(s, true));
                } else {
                    arrayList.add(new SeasonEpisodeSelectableModel(s));
                }
                i3 = i4;
            }
        }
        Bundle arguments4 = getArguments();
        String[] stringArray2 = arguments4 == null ? null : arguments4.getStringArray("years");
        final ArrayList arrayList2 = new ArrayList();
        if (stringArray2 != null) {
            int length2 = stringArray2.length;
            int i5 = 0;
            while (i < length2) {
                String s2 = stringArray2[i];
                i++;
                int i6 = i5 + 1;
                if (Intrinsics.areEqual(str, s2)) {
                    this.lastYearPosition = i5;
                    Intrinsics.checkNotNullExpressionValue(s2, "s");
                    this.chooseYear = s2;
                    arrayList2.add(new SeasonEpisodeSelectableModel(s2, true));
                } else {
                    arrayList2.add(new SeasonEpisodeSelectableModel(s2));
                }
                i5 = i6;
            }
        }
        this.seasonAdapter = new BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonEpisodeDialog$initData$3
            final /* synthetic */ ArrayList<SeasonEpisodeSelectableModel> $seasonsModel;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_season_episode_item, arrayList);
                this.$seasonsModel = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SeasonEpisodeSelectableModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) holder.getView(R.id.textView);
                textView.setText(item.getText());
                textView.setSelected(item.isSelect());
            }
        };
        ((RecyclerView) _$_findCachedViewById(R.id.rvSeason)).setLayoutManager(new GridLayoutManager(getContext(), 4));
        ((RecyclerView) _$_findCachedViewById(R.id.rvSeason)).addItemDecoration(new GridSpacingItemDecoration(4, DensityUtils.dp2px(App.getContext(), 10.0f), true));
        RecyclerView rvSeason = (RecyclerView) _$_findCachedViewById(R.id.rvSeason);
        Intrinsics.checkNotNullExpressionValue(rvSeason, "rvSeason");
        CommonExtKt.disableRefreshAnimation(rvSeason);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvSeason);
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter2 = this.seasonAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seasonAdapter");
            baseQuickAdapter2 = null;
        }
        recyclerView.setAdapter(baseQuickAdapter2);
        this.yearAdapter = new BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder>(arrayList2) { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonEpisodeDialog$initData$4
            final /* synthetic */ ArrayList<SeasonEpisodeSelectableModel> $yearsModel;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_season_episode_item, arrayList2);
                this.$yearsModel = arrayList2;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SeasonEpisodeSelectableModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) holder.getView(R.id.textView);
                if (Intrinsics.areEqual(item.getText(), "-1")) {
                    textView.setText("Unreleased");
                    textView.setTextSize(8.0f);
                } else {
                    textView.setText(item.getText());
                    textView.setTextSize(16.0f);
                }
                textView.setSelected(item.isSelect());
            }
        };
        ((RecyclerView) _$_findCachedViewById(R.id.rvYear)).setLayoutManager(new GridLayoutManager(getContext(), 4));
        ((RecyclerView) _$_findCachedViewById(R.id.rvYear)).addItemDecoration(new GridSpacingItemDecoration(4, DensityUtils.dp2px(App.getContext(), 10.0f), true));
        RecyclerView rvYear = (RecyclerView) _$_findCachedViewById(R.id.rvYear);
        Intrinsics.checkNotNullExpressionValue(rvYear, "rvYear");
        CommonExtKt.disableRefreshAnimation(rvYear);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvYear);
        BaseQuickAdapter<SeasonEpisodeSelectableModel, BaseViewHolder> baseQuickAdapter3 = this.yearAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter3;
        }
        recyclerView2.setAdapter(baseQuickAdapter);
    }

    /* compiled from: ChooseSeasonEpisodeDialog.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog;", "seasons", "", "", "years", "currentSeason", "currentYear", "([Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/movieboxpro/android/view/dialog/ChooseSeasonEpisodeDialog;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseSeasonEpisodeDialog newInstance(String[] seasons, String[] years, String currentSeason, String currentYear) {
            Intrinsics.checkNotNullParameter(seasons, "seasons");
            Intrinsics.checkNotNullParameter(years, "years");
            Intrinsics.checkNotNullParameter(currentSeason, "currentSeason");
            Intrinsics.checkNotNullParameter(currentYear, "currentYear");
            ChooseSeasonEpisodeDialog chooseSeasonEpisodeDialog = new ChooseSeasonEpisodeDialog();
            Bundle bundle = new Bundle();
            bundle.putStringArray("seasons", seasons);
            bundle.putStringArray("years", years);
            bundle.putString("currentSeason", currentSeason);
            bundle.putString("currentYear", currentYear);
            chooseSeasonEpisodeDialog.setArguments(bundle);
            return chooseSeasonEpisodeDialog;
        }
    }
}
