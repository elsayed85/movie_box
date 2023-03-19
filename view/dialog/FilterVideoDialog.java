package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.FilterAdapter;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.FilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.dialog.ChooseMultiCountryDialog;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: FilterVideoDialog.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 V2\u00020\u0001:\u0002VWB\u0005¢\u0006\u0002\u0010\u0002J\b\u00104\u001a\u000205H\u0003J\b\u00106\u001a\u000205H\u0002J\"\u00107\u001a\u0002052\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020!2\b\b\u0002\u0010;\u001a\u00020\fH\u0002J\u0012\u0010<\u001a\u0002052\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J&\u0010?\u001a\u0004\u0018\u0001022\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010D\u001a\u000205H\u0016J\b\u0010E\u001a\u000205H\u0016J\b\u0010F\u001a\u000205H\u0016J\u001a\u0010G\u001a\u0002052\u0006\u0010H\u001a\u0002022\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010I\u001a\u0002052\u0006\u0010H\u001a\u000202H\u0002J\u0010\u0010J\u001a\u0002052\u0006\u0010H\u001a\u000202H\u0002J&\u0010K\u001a\u0002052\u0016\u0010L\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010M\u001a\u0002052\u0006\u0010N\u001a\u00020\fH\u0002J\u0010\u0010O\u001a\u0002052\u0006\u0010N\u001a\u00020\fH\u0002J\b\u0010P\u001a\u000205H\u0002J\b\u0010Q\u001a\u000205H\u0002J\u0018\u0010R\u001a\u0002052\u0006\u0010S\u001a\u00020T2\b\u0010U\u001a\u0004\u0018\u00010\nR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0007j\b\u0012\u0004\u0012\u00020\f`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0007j\b\u0012\u0004\u0012\u00020\u000f`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\"\u00101\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001020\u0007j\n\u0012\u0006\u0012\u0004\u0018\u000102`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterVideoDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "countryAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/FilterCountry;", "countryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "extraCountry", "", "genre", "", "genreAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/common/Gener;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "genres", "imdbRating", "isGetGenreId", "", "isMovie", "lastCountryPosition", "lastGenrePosition", "lastRatingPosition", "lastResolutionPosition", "lastSortPosition", "lastTypePosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterVideoDialog$OnFilterListener;", "quality", "rating", "ratingAdapter", "Lcom/movieboxpro/android/adapter/FilterAdapter;", "resolutionAdapter", "selectCountryPosition", "value", "selectGenrePosition", "setSelectGenrePosition", "(I)V", "selectMorePos", "selectRatingPosition", "selectResolutionPosition", "selectSortPosition", "selectTypePosition", "sort", "sortAdapter", "tomatoMeter", "typeAdapter", "viewList", "Landroid/view/View;", "year", "initData", "", "initListener", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "filterAdapter", "count", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onPause", "onResume", "onStart", "onViewCreated", "view", "scaleDown", "scaleUp", "setFilterListener", "country", "setViewGoneByPosition", "position", "setViewVisibleByPosition", "setViewsGone", "setViewsVisible", "showAllowingStateLoss", "manager", "Landroidx/fragment/app/FragmentManager;", "tag", "Companion", "OnFilterListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FilterVideoDialog extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_SORT = "released";
    public static final String TV_DEFAULT_SORT = "adder";
    private CommBaseAdapter<FilterCountry> countryAdapter;
    private BaseQuickAdapter<Gener, BaseViewHolder> genreAdapter;
    private boolean isGetGenreId;
    private boolean isMovie;
    private int lastCountryPosition;
    private int lastSortPosition;
    private int lastTypePosition;
    private OnFilterListener listener;
    private FilterAdapter ratingAdapter;
    private FilterAdapter resolutionAdapter;
    private int selectCountryPosition;
    private int selectSortPosition;
    private int selectTypePosition;
    private FilterAdapter sortAdapter;
    private FilterAdapter typeAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private String year = "";
    private ArrayList<Integer> genre = new ArrayList<>();
    private String sort = DEFAULT_SORT;
    private String rating = "";
    private String quality = "";
    private String imdbRating = "";
    private String tomatoMeter = "";
    private ArrayList<Gener> genres = new ArrayList<>();
    private ArrayList<String> extraCountry = new ArrayList<>();
    private ArrayList<View> viewList = new ArrayList<>();
    private int selectMorePos = -1;
    private int lastRatingPosition = -1;
    private int selectRatingPosition = -1;
    private int lastResolutionPosition = -1;
    private int selectResolutionPosition = -1;
    private int lastGenrePosition = -1;
    private int selectGenrePosition = -1;

    /* compiled from: FilterVideoDialog.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001Jh\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\t2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\bH&¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterVideoDialog$OnFilterListener;", "", "onFilter", "", "year", "", "genre", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sort", "rating", "quality", "country", "imdbRating", "tomatoMeter", "typeSelect", IjkMediaMeta.IJKM_KEY_TYPE, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnFilterListener {
        void onFilter(String str, ArrayList<Integer> arrayList, String str2, String str3, String str4, ArrayList<String> arrayList2, String str5, String str6);

        void typeSelect(int i);
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

    public final void showAllowingStateLoss(FragmentManager manager, String str) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        try {
            Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
            Intrinsics.checkNotNullExpressionValue(declaredField, "DialogFragment::class.ja…claredField(\"mDismissed\")");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        try {
            Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
            Intrinsics.checkNotNullExpressionValue(declaredField2, "DialogFragment::class.ja…claredField(\"mShownByMe\")");
            declaredField2.setAccessible(true);
            declaredField2.set(this, true);
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        }
        FragmentTransaction beginTransaction = manager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "manager.beginTransaction()");
        beginTransaction.add(this, str);
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.BottomSheetFullScreenDialog);
    }

    public final void setFilterListener(ArrayList<FilterCountry> country, OnFilterListener listener) {
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.countryList.clear();
        this.countryList.addAll(country);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Resources resources;
        super.onStart();
        Dialog dialog = getDialog();
        DisplayMetrics displayMetrics = null;
        Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            int i = 0;
            window.getDecorView().setPadding(0, 0, 0, 0);
            Context context = getContext();
            window.setBackgroundDrawable(context == null ? null : ContextCompat.getDrawable(context, 17170445));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.0f;
            Context context2 = getContext();
            if (context2 != null && (resources = context2.getResources()) != null) {
                displayMetrics = resources.getDisplayMetrics();
            }
            attributes.width = -1;
            if (displayMetrics != null) {
                double d = displayMetrics.heightPixels;
                Double.isNaN(d);
                i = (int) (d * 0.8d);
            }
            attributes.height = i;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_filter_video, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    private final void setSelectGenrePosition(int i) {
        if (i == -1) {
            setViewsGone();
        }
        this.selectGenrePosition = i;
    }

    private final void initListener() {
        ((FrameLayout) _$_findCachedViewById(R.id.flTop)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$eYW90HVd2VOKPPeB6F4CUmTHwy0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterVideoDialog.m1027initListener$lambda1(FilterVideoDialog.this, view);
            }
        });
        FilterAdapter filterAdapter = this.typeAdapter;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        filterAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$sG4LDOhI1HRZ8VGiZmnykulaDZg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterVideoDialog.m1028initListener$lambda13(FilterVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$3M4Nc-wKn9i2saOoyXEFMne4E2E
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterVideoDialog.m1029initListener$lambda15(FilterVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter2 = this.sortAdapter;
        if (filterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter2 = null;
        }
        filterAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$ADCmW6D25_AgkYa9EMSKOBobXas
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterVideoDialog.m1030initListener$lambda16(FilterVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter3 = this.ratingAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter3 = null;
        }
        filterAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$FT3qGF1g-cwNmp5jxmTRuJI0cEY
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterVideoDialog.m1031initListener$lambda17(FilterVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter4 = this.resolutionAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter4 = null;
        }
        filterAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$ij-fpSvm91hWhHHxuRH9jzw4Ihg
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterVideoDialog.m1032initListener$lambda18(FilterVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this.genreAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        baseQuickAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$6BViN6zRBZRo7XvCbVpCixb6Z78
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterVideoDialog.m1033initListener$lambda19(FilterVideoDialog.this, baseQuickAdapter3, view, i);
            }
        });
        ((RangeSeekBar) _$_findCachedViewById(R.id.sbImdbRating)).setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.dialog.FilterVideoDialog$initListener$8
            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onRangeChanged(RangeSeekBar view, float f, float f2, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                String format = new DecimalFormat("#.#").format(Float.valueOf(f));
                String format2 = new DecimalFormat("#.#").format(Float.valueOf(f2));
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format3 = String.format("%s - %s", Arrays.copyOf(new Object[]{format, format2}, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(format, *args)");
                ((TextView) FilterVideoDialog.this._$_findCachedViewById(R.id.tvImdbRating)).setText(format3);
                if (Intrinsics.areEqual(format, "0") && Intrinsics.areEqual(format2, "10")) {
                    FilterVideoDialog.this.imdbRating = "";
                    return;
                }
                FilterVideoDialog filterVideoDialog = FilterVideoDialog.this;
                StringBuilder sb = new StringBuilder();
                sb.append((Object) format);
                sb.append('-');
                sb.append((Object) format2);
                filterVideoDialog.imdbRating = sb.toString();
            }
        });
        ((RangeSeekBar) _$_findCachedViewById(R.id.sbTomatoMeter)).setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.dialog.FilterVideoDialog$initListener$9
            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onRangeChanged(RangeSeekBar view, float f, float f2, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                int i = (int) f;
                int i2 = (int) f2;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s - %s", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) FilterVideoDialog.this._$_findCachedViewById(R.id.tvTomatoMeter)).setText(format);
                if (i == 0 && i2 == 100) {
                    FilterVideoDialog.this.tomatoMeter = "";
                    return;
                }
                FilterVideoDialog filterVideoDialog = FilterVideoDialog.this;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('-');
                sb.append(i2);
                filterVideoDialog.tomatoMeter = sb.toString();
            }
        });
        ((RangeSeekBar) _$_findCachedViewById(R.id.rangeSeekBar)).setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.dialog.FilterVideoDialog$initListener$10
            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                String sb;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                int i = (int) f;
                int i2 = (int) f2;
                String format = String.format("%s - %s", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) FilterVideoDialog.this._$_findCachedViewById(R.id.tvYear)).setText(format);
                FilterVideoDialog filterVideoDialog = FilterVideoDialog.this;
                if (i == 1900 && i2 == Calendar.getInstance().get(1)) {
                    sb = "";
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i);
                    sb2.append('-');
                    sb2.append(i2);
                    sb = sb2.toString();
                }
                filterVideoDialog.year = sb;
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$fOf1au_-IecGW2Vcau4Im7T7RD0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterVideoDialog.m1034initListener$lambda24(FilterVideoDialog.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterVideoDialog$IIH7GPcBAeF7lESQkoTihulV2gc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterVideoDialog.m1035initListener$lambda28(FilterVideoDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1027initListener$lambda1(FilterVideoDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-13  reason: not valid java name */
    public static final void m1028initListener$lambda13(FilterVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        ArrayList arrayListOf;
        String tVSort;
        SparseIntArray sparseIntArray;
        ArrayList<String> tVCountry;
        CommBaseAdapter<FilterCountry> commBaseAdapter;
        float tVStartYear;
        float tVEndYear;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        FilterAdapter filterAdapter = this$0.typeAdapter;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        filterAdapter.getItem(i);
        if (this$0.lastTypePosition == i) {
            return;
        }
        this$0.selectTypePosition = i;
        FilterAdapter filterAdapter2 = this$0.typeAdapter;
        if (filterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter2 = null;
        }
        filterAdapter2.getItem(i).setSelect(true);
        FilterAdapter filterAdapter3 = this$0.typeAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter3 = null;
        }
        filterAdapter3.getItem(this$0.lastTypePosition).setSelect(false);
        FilterAdapter filterAdapter4 = this$0.typeAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter4 = null;
        }
        filterAdapter4.notifyItemChanged(i);
        FilterAdapter filterAdapter5 = this$0.typeAdapter;
        if (filterAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter5 = null;
        }
        filterAdapter5.notifyItemChanged(this$0.lastTypePosition);
        this$0.lastTypePosition = i;
        OnFilterListener onFilterListener = this$0.listener;
        if (onFilterListener != null) {
            onFilterListener.typeSelect(i);
            Unit unit = Unit.INSTANCE;
        }
        if (i == 0) {
            this$0.isMovie = true;
            ((TextView) this$0._$_findCachedViewById(R.id.tvRating)).setText("MOVIE RATINGS");
            TextView tvRating = (TextView) this$0._$_findCachedViewById(R.id.tvRating);
            Intrinsics.checkNotNullExpressionValue(tvRating, "tvRating");
            CommonExtKt.visible(tvRating);
            TextView tvResolution = (TextView) this$0._$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
            CommonExtKt.visible(tvResolution);
            RecyclerView rvResolution = (RecyclerView) this$0._$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
            CommonExtKt.visible(rvResolution);
            RecyclerView rvRating = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
            CommonExtKt.visible(rvRating);
            ArrayList arrayListOf2 = CollectionsKt.arrayListOf(new FilterModel("G", "G", false), new FilterModel("PG", "PG"), new FilterModel("PG-13", "PG-13"), new FilterModel("R", "R"), new FilterModel("NC-17", "NC-17"), new FilterModel("Not Rated", "empty"));
            String movieRating = SettingManager.INSTANCE.getMovieRating();
            Iterator it = arrayListOf2.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FilterModel) it.next()).getServiceText(), movieRating)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                this$0.selectRatingPosition = i2;
                this$0.lastRatingPosition = i2;
                String serviceText = ((FilterModel) arrayListOf2.get(i2)).getServiceText();
                Intrinsics.checkNotNullExpressionValue(serviceText, "rating[ratingIndex].serviceText");
                this$0.rating = serviceText;
            } else {
                this$0.selectRatingPosition = -1;
                this$0.lastRatingPosition = -1;
            }
            FilterAdapter filterAdapter6 = this$0.ratingAdapter;
            if (filterAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter6 = null;
            }
            filterAdapter6.setList(arrayListOf2);
            ArrayList arrayListOf3 = CollectionsKt.arrayListOf(new FilterModel("1080P", "1080p"), new FilterModel("4K", "4k"), new FilterModel("8K", "8k"), new FilterModel("BLU-RAY", "blu-ray"));
            String movieResolution = SettingManager.INSTANCE.getMovieResolution();
            ArrayList arrayList = arrayListOf3;
            Iterator it2 = arrayList.iterator();
            int i3 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i3 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FilterModel) it2.next()).getServiceText(), movieResolution)) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 != -1) {
                this$0.selectResolutionPosition = i3;
                this$0.lastResolutionPosition = i3;
                String serviceText2 = ((FilterModel) arrayListOf3.get(i3)).getServiceText();
                Intrinsics.checkNotNullExpressionValue(serviceText2, "resolution[resolutionIndex].serviceText");
                this$0.quality = serviceText2;
            }
            if (CommonExtKt.checkIndexLegal(this$0.selectResolutionPosition, arrayList)) {
                ((FilterModel) arrayListOf3.get(this$0.selectResolutionPosition)).setSelect(true);
            }
            FilterAdapter filterAdapter7 = this$0.resolutionAdapter;
            if (filterAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter7 = null;
            }
            filterAdapter7.setList(arrayListOf3);
        } else if (i == 1) {
            this$0.isMovie = false;
            RecyclerView rvRating2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating2, "rvRating");
            CommonExtKt.visible(rvRating2);
            ((TextView) this$0._$_findCachedViewById(R.id.tvRating)).setText("TVSHOW RATINGS");
            RecyclerView rvResolution2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution2, "rvResolution");
            CommonExtKt.gone(rvResolution2);
            TextView tvResolution2 = (TextView) this$0._$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution2, "tvResolution");
            CommonExtKt.gone(tvResolution2);
            ArrayList arrayListOf4 = CollectionsKt.arrayListOf(new FilterModel("TV-Y", "TV-Y", false), new FilterModel("TV-Y7", "TV-Y7"), new FilterModel("TV-G", "TV-G"), new FilterModel("TV-PG", "TV-PG"), new FilterModel("TV-14", "TV-14"), new FilterModel("TV-MA", "TV-MA"), new FilterModel("Not Rated", "empty"));
            String tVRating = SettingManager.INSTANCE.getTVRating();
            Iterator it3 = arrayListOf4.iterator();
            int i4 = 0;
            while (true) {
                if (!it3.hasNext()) {
                    i4 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FilterModel) it3.next()).getServiceText(), tVRating)) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 != -1) {
                this$0.selectRatingPosition = i4;
                this$0.lastRatingPosition = i4;
                String serviceText3 = ((FilterModel) arrayListOf4.get(i4)).getServiceText();
                Intrinsics.checkNotNullExpressionValue(serviceText3, "rating[ratingIndex].serviceText");
                this$0.rating = serviceText3;
            } else {
                this$0.selectRatingPosition = -1;
                this$0.lastRatingPosition = -1;
            }
            FilterAdapter filterAdapter8 = this$0.ratingAdapter;
            if (filterAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter8 = null;
            }
            filterAdapter8.setList(arrayListOf4);
        }
        if (this$0.isMovie) {
            arrayListOf = CollectionsKt.arrayListOf(new FilterModel("Released", DEFAULT_SORT), new FilterModel("Added", TV_DEFAULT_SORT), new FilterModel("IMDB", "rating", false), new FilterModel("RottenTomatoes", "tomato_meter"), new FilterModel("View", "view"));
        } else {
            arrayListOf = CollectionsKt.arrayListOf(new FilterModel("Update", TV_DEFAULT_SORT), new FilterModel("IMDB", "rating", false), new FilterModel("RottenTomatoes", "tomato_meter"), new FilterModel("View", "view"));
        }
        if (this$0.isMovie) {
            tVSort = SettingManager.INSTANCE.getMovieSort();
        } else {
            tVSort = SettingManager.INSTANCE.getTVSort();
        }
        ArrayList arrayList2 = arrayListOf;
        Iterator it4 = arrayList2.iterator();
        int i5 = 0;
        while (true) {
            if (!it4.hasNext()) {
                i5 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it4.next()).getServiceText(), tVSort)) {
                break;
            } else {
                i5++;
            }
        }
        if (i5 != -1) {
            this$0.selectSortPosition = i5;
            this$0.lastSortPosition = i5;
            String serviceText4 = ((FilterModel) arrayListOf.get(i5)).getServiceText();
            Intrinsics.checkNotNullExpressionValue(serviceText4, "sorts[sortIndex].serviceText");
            this$0.sort = serviceText4;
        } else {
            this$0.selectSortPosition = 0;
            this$0.lastSortPosition = 0;
        }
        if (CommonExtKt.checkIndexLegal(this$0.selectSortPosition, arrayList2)) {
            ((FilterModel) arrayListOf.get(this$0.selectSortPosition)).setSelect(true);
        }
        FilterAdapter filterAdapter9 = this$0.sortAdapter;
        if (filterAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter9 = null;
        }
        filterAdapter9.setList(arrayListOf);
        for (Gener gener : this$0.genres) {
            gener.setSelect(false);
        }
        if (this$0.isMovie) {
            sparseIntArray = new SparseIntArray();
            for (Number number : SettingManager.INSTANCE.getMovieGener()) {
                int intValue = number.intValue();
                sparseIntArray.put(intValue, intValue);
            }
        } else {
            sparseIntArray = new SparseIntArray();
            for (Number number2 : SettingManager.INSTANCE.getTVGener()) {
                int intValue2 = number2.intValue();
                sparseIntArray.put(intValue2, intValue2);
            }
        }
        for (Gener gener2 : this$0.genres) {
            if (sparseIntArray.indexOfKey(gener2.getId()) >= 0) {
                gener2.setSelect(true);
            }
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = this$0.genreAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(this$0.genres);
        CommBaseAdapter<FilterCountry> commBaseAdapter2 = this$0.countryAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter2 = null;
        }
        for (FilterCountry filterCountry : commBaseAdapter2.getData()) {
            filterCountry.setSelect(false);
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter3 = null;
        }
        List<FilterCountry> data = commBaseAdapter3.getData();
        if (this$0.isMovie) {
            tVCountry = SettingManager.INSTANCE.getMovieCountry();
        } else {
            tVCountry = SettingManager.INSTANCE.getTVCountry();
        }
        if (tVCountry.isEmpty()) {
            data.get(0).setSelect(true);
        } else {
            HashMap hashMap = new HashMap();
            for (String str : tVCountry) {
                hashMap.put(str, str);
            }
            for (FilterCountry filterCountry2 : data) {
                if (hashMap.containsKey(filterCountry2.getCountry())) {
                    filterCountry2.setSelect(true);
                }
            }
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter4 = this$0.countryAdapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        } else {
            commBaseAdapter = commBaseAdapter4;
        }
        commBaseAdapter.setList(data);
        if (this$0.isMovie) {
            tVStartYear = SettingManager.INSTANCE.getMovieStartYear();
        } else {
            tVStartYear = SettingManager.INSTANCE.getTVStartYear();
        }
        if (this$0.isMovie) {
            tVEndYear = SettingManager.INSTANCE.getMovieEndYear();
        } else {
            tVEndYear = SettingManager.INSTANCE.getTVEndYear();
        }
        StringBuilder sb = new StringBuilder();
        int i6 = (int) tVStartYear;
        sb.append(i6);
        sb.append('-');
        int i7 = (int) tVEndYear;
        sb.append(i7);
        this$0.year = sb.toString();
        ((RangeSeekBar) this$0._$_findCachedViewById(R.id.rangeSeekBar)).setProgress(tVStartYear, tVEndYear);
        ((TextView) this$0._$_findCachedViewById(R.id.tvYear)).setText(i6 + " - " + i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-15  reason: not valid java name */
    public static final void m1029initListener$lambda15(final FilterVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
        CommBaseAdapter<FilterCountry> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        String country = commBaseAdapter.getItem(i).getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "countryAdapter.getItem(position).country");
        if (StringsKt.contains$default((CharSequence) country, (CharSequence) "More", false, 2, (Object) null)) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ChooseMultiCountryDialog chooseMultiCountryDialog = new ChooseMultiCountryDialog(requireActivity, this$0.extraCountry, new ArrayList(this$0.countryList));
            chooseMultiCountryDialog.setListener(new ChooseMultiCountryDialog.OnChooseCountryListener() { // from class: com.movieboxpro.android.view.dialog.FilterVideoDialog$initListener$3$1
                @Override // com.movieboxpro.android.view.dialog.ChooseMultiCountryDialog.OnChooseCountryListener
                public void onChooseCountry(int i2, ArrayList<String> country2) {
                    CommBaseAdapter commBaseAdapter3;
                    ArrayList<String> arrayList;
                    CommBaseAdapter commBaseAdapter4;
                    CommBaseAdapter commBaseAdapter5;
                    CommBaseAdapter commBaseAdapter6;
                    CommBaseAdapter commBaseAdapter7;
                    CommBaseAdapter commBaseAdapter8;
                    Intrinsics.checkNotNullParameter(country2, "country");
                    CommBaseAdapter commBaseAdapter9 = null;
                    if (country2.isEmpty()) {
                        commBaseAdapter6 = FilterVideoDialog.this.countryAdapter;
                        if (commBaseAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter6 = null;
                        }
                        for (FilterCountry filterCountry : commBaseAdapter6.getData()) {
                            filterCountry.setSelect(false);
                        }
                        commBaseAdapter7 = FilterVideoDialog.this.countryAdapter;
                        if (commBaseAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter7 = null;
                        }
                        ((FilterCountry) commBaseAdapter7.getItem(0)).setSelect(true);
                        commBaseAdapter8 = FilterVideoDialog.this.countryAdapter;
                        if (commBaseAdapter8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        } else {
                            commBaseAdapter9 = commBaseAdapter8;
                        }
                        commBaseAdapter9.notifyDataSetChanged();
                        return;
                    }
                    commBaseAdapter3 = FilterVideoDialog.this.countryAdapter;
                    if (commBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        commBaseAdapter3 = null;
                    }
                    for (FilterCountry filterCountry2 : commBaseAdapter3.getData()) {
                        filterCountry2.setSelect(false);
                    }
                    HashMap hashMap = new HashMap();
                    FilterVideoDialog.this.extraCountry = country2;
                    arrayList = FilterVideoDialog.this.extraCountry;
                    for (String str : arrayList) {
                        hashMap.put(str, str);
                    }
                    commBaseAdapter4 = FilterVideoDialog.this.countryAdapter;
                    if (commBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        commBaseAdapter4 = null;
                    }
                    for (FilterCountry filterCountry3 : commBaseAdapter4.getData()) {
                        if (hashMap.containsKey(filterCountry3.getCountry())) {
                            filterCountry3.setSelect(true);
                        }
                    }
                    commBaseAdapter5 = FilterVideoDialog.this.countryAdapter;
                    if (commBaseAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                    } else {
                        commBaseAdapter9 = commBaseAdapter5;
                    }
                    commBaseAdapter9.notifyDataSetChanged();
                }
            });
            chooseMultiCountryDialog.show();
            return;
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter3 = null;
        }
        FilterCountry item = commBaseAdapter3.getItem(i);
        if (Intrinsics.areEqual(item.getCountry(), "All")) {
            CommBaseAdapter<FilterCountry> commBaseAdapter4 = this$0.countryAdapter;
            if (commBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter4 = null;
            }
            for (FilterCountry filterCountry : commBaseAdapter4.getData()) {
                filterCountry.setSelect(false);
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter5 = this$0.countryAdapter;
            if (commBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter5 = null;
            }
            commBaseAdapter5.getItem(0).setSelect(true);
            CommBaseAdapter<FilterCountry> commBaseAdapter6 = this$0.countryAdapter;
            if (commBaseAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            } else {
                commBaseAdapter2 = commBaseAdapter6;
            }
            commBaseAdapter2.notifyDataSetChanged();
            return;
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter7 = this$0.countryAdapter;
        if (commBaseAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter7 = null;
        }
        commBaseAdapter7.getItem(0).setSelect(false);
        item.setSelect(!item.isSelect());
        CommBaseAdapter<FilterCountry> commBaseAdapter8 = this$0.countryAdapter;
        if (commBaseAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter8 = null;
        }
        commBaseAdapter8.notifyItemChanged(i);
        CommBaseAdapter<FilterCountry> commBaseAdapter9 = this$0.countryAdapter;
        if (commBaseAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            commBaseAdapter2 = commBaseAdapter9;
        }
        commBaseAdapter2.notifyItemChanged(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-16  reason: not valid java name */
    public static final void m1030initListener$lambda16(FilterVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String serviceText;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastSortPosition == i) {
            return;
        }
        FilterAdapter filterAdapter = this$0.sortAdapter;
        FilterAdapter filterAdapter2 = null;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter = null;
        }
        FilterModel item = filterAdapter.getItem(i);
        String str = "rating";
        if (item != null && (serviceText = item.getServiceText()) != null) {
            str = serviceText;
        }
        this$0.sort = str;
        this$0.selectSortPosition = i;
        FilterAdapter filterAdapter3 = this$0.sortAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter3 = null;
        }
        FilterModel item2 = filterAdapter3.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        FilterAdapter filterAdapter4 = this$0.sortAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter4 = null;
        }
        FilterModel itemOrNull = filterAdapter4.getItemOrNull(this$0.lastSortPosition);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        FilterAdapter filterAdapter5 = this$0.sortAdapter;
        if (filterAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter5 = null;
        }
        filterAdapter5.notifyItemChanged(i);
        FilterAdapter filterAdapter6 = this$0.sortAdapter;
        if (filterAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
        } else {
            filterAdapter2 = filterAdapter6;
        }
        filterAdapter2.notifyItemChanged(this$0.lastSortPosition);
        this$0.lastSortPosition = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-17  reason: not valid java name */
    public static final void m1031initListener$lambda17(FilterVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String serviceText;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        String str = "";
        FilterAdapter filterAdapter = null;
        if (this$0.lastRatingPosition == i) {
            this$0.rating = "";
            FilterAdapter filterAdapter2 = this$0.ratingAdapter;
            if (filterAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter2 = null;
            }
            FilterModel item = filterAdapter2.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            this$0.selectRatingPosition = -1;
            this$0.lastRatingPosition = -1;
            FilterAdapter filterAdapter3 = this$0.ratingAdapter;
            if (filterAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            } else {
                filterAdapter = filterAdapter3;
            }
            filterAdapter.notifyItemChanged(i);
            return;
        }
        FilterAdapter filterAdapter4 = this$0.ratingAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter4 = null;
        }
        FilterModel item2 = filterAdapter4.getItem(i);
        if (item2 != null && (serviceText = item2.getServiceText()) != null) {
            str = serviceText;
        }
        this$0.rating = str;
        this$0.selectRatingPosition = i;
        FilterAdapter filterAdapter5 = this$0.ratingAdapter;
        if (filterAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter5 = null;
        }
        FilterModel item3 = filterAdapter5.getItem(i);
        if (item3 != null) {
            item3.setSelect(true);
        }
        FilterAdapter filterAdapter6 = this$0.ratingAdapter;
        if (filterAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter6 = null;
        }
        filterAdapter6.notifyItemChanged(i);
        if (this$0.lastRatingPosition != -1) {
            FilterAdapter filterAdapter7 = this$0.ratingAdapter;
            if (filterAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter7 = null;
            }
            FilterModel itemOrNull = filterAdapter7.getItemOrNull(this$0.lastRatingPosition);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            FilterAdapter filterAdapter8 = this$0.ratingAdapter;
            if (filterAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            } else {
                filterAdapter = filterAdapter8;
            }
            filterAdapter.notifyItemChanged(this$0.lastRatingPosition);
        }
        this$0.lastRatingPosition = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-18  reason: not valid java name */
    public static final void m1032initListener$lambda18(FilterVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String serviceText;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        String str = "";
        FilterAdapter filterAdapter = null;
        if (this$0.lastResolutionPosition == i) {
            this$0.quality = "";
            FilterAdapter filterAdapter2 = this$0.resolutionAdapter;
            if (filterAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter2 = null;
            }
            FilterModel item = filterAdapter2.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            this$0.selectResolutionPosition = -1;
            this$0.lastResolutionPosition = -1;
            FilterAdapter filterAdapter3 = this$0.resolutionAdapter;
            if (filterAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            } else {
                filterAdapter = filterAdapter3;
            }
            filterAdapter.notifyItemChanged(i);
            return;
        }
        FilterAdapter filterAdapter4 = this$0.resolutionAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter4 = null;
        }
        FilterModel item2 = filterAdapter4.getItem(i);
        if (item2 != null && (serviceText = item2.getServiceText()) != null) {
            str = serviceText;
        }
        this$0.quality = str;
        this$0.selectResolutionPosition = i;
        FilterAdapter filterAdapter5 = this$0.resolutionAdapter;
        if (filterAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter5 = null;
        }
        FilterModel item3 = filterAdapter5.getItem(i);
        if (item3 != null) {
            item3.setSelect(true);
        }
        FilterAdapter filterAdapter6 = this$0.resolutionAdapter;
        if (filterAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter6 = null;
        }
        filterAdapter6.notifyItemChanged(i);
        if (this$0.lastResolutionPosition != -1) {
            FilterAdapter filterAdapter7 = this$0.resolutionAdapter;
            if (filterAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter7 = null;
            }
            FilterModel itemOrNull = filterAdapter7.getItemOrNull(this$0.lastResolutionPosition);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            FilterAdapter filterAdapter8 = this$0.resolutionAdapter;
            if (filterAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            } else {
                filterAdapter = filterAdapter8;
            }
            filterAdapter.notifyItemChanged(this$0.lastResolutionPosition);
        }
        this$0.lastResolutionPosition = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-19  reason: not valid java name */
    public static final void m1033initListener$lambda19(FilterVideoDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = this$0.genreAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter = null;
        }
        Gener item = baseQuickAdapter.getItem(i);
        if (item.isSelect()) {
            item.setSelect(false);
            this$0.scaleDown(view);
            this$0.setViewVisibleByPosition(i);
            return;
        }
        item.setSelect(true);
        this$0.scaleUp(view);
        this$0.setViewGoneByPosition(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f2  */
    /* renamed from: initListener$lambda-24  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1034initListener$lambda24(com.movieboxpro.android.view.dialog.FilterVideoDialog r14, android.view.View r15) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.dialog.FilterVideoDialog.m1034initListener$lambda24(com.movieboxpro.android.view.dialog.FilterVideoDialog, android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-28  reason: not valid java name */
    public static final void m1035initListener$lambda28(FilterVideoDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (this$0.selectSortPosition != 0) {
            FilterAdapter filterAdapter = this$0.sortAdapter;
            if (filterAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter = null;
            }
            FilterModel item = filterAdapter.getItem(0);
            if (item != null) {
                item.setSelect(true);
            }
            FilterAdapter filterAdapter2 = this$0.sortAdapter;
            if (filterAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter2 = null;
            }
            filterAdapter2.notifyItemChanged(0);
            FilterAdapter filterAdapter3 = this$0.sortAdapter;
            if (filterAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter3 = null;
            }
            FilterModel itemOrNull = filterAdapter3.getItemOrNull(this$0.selectSortPosition);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            FilterAdapter filterAdapter4 = this$0.sortAdapter;
            if (filterAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter4 = null;
            }
            filterAdapter4.notifyItemChanged(this$0.selectSortPosition);
        }
        if (this$0.selectRatingPosition != -1) {
            FilterAdapter filterAdapter5 = this$0.ratingAdapter;
            if (filterAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter5 = null;
            }
            FilterModel itemOrNull2 = filterAdapter5.getItemOrNull(this$0.selectRatingPosition);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            FilterAdapter filterAdapter6 = this$0.ratingAdapter;
            if (filterAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter6 = null;
            }
            filterAdapter6.notifyItemChanged(this$0.selectRatingPosition);
        }
        if (this$0.selectResolutionPosition != -1) {
            FilterAdapter filterAdapter7 = this$0.resolutionAdapter;
            if (filterAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter7 = null;
            }
            FilterModel itemOrNull3 = filterAdapter7.getItemOrNull(this$0.selectResolutionPosition);
            if (itemOrNull3 != null) {
                itemOrNull3.setSelect(false);
            }
            FilterAdapter filterAdapter8 = this$0.resolutionAdapter;
            if (filterAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter8 = null;
            }
            filterAdapter8.notifyItemChanged(this$0.selectResolutionPosition);
        }
        if (this$0.selectGenrePosition != -1) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this$0.genreAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter2 = null;
            }
            Gener itemOrNull4 = baseQuickAdapter2.getItemOrNull(this$0.selectGenrePosition);
            if (itemOrNull4 != null) {
                itemOrNull4.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = this$0.genreAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter3 = null;
            }
            View viewByPosition = baseQuickAdapter3.getViewByPosition(this$0.selectGenrePosition, R.id.container);
            if (viewByPosition != null) {
                this$0.scaleDown(viewByPosition);
            }
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        for (FilterCountry filterCountry : commBaseAdapter.getData()) {
            filterCountry.setSelect(false);
        }
        CommBaseAdapter<FilterCountry> commBaseAdapter2 = this$0.countryAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter2 = null;
        }
        commBaseAdapter2.getItem(0).setSelect(true);
        CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter3 = null;
        }
        commBaseAdapter3.notifyDataSetChanged();
        ((RangeSeekBar) this$0._$_findCachedViewById(R.id.rangeSeekBar)).setProgress(1900.0f, Calendar.getInstance().get(1));
        ((RangeSeekBar) this$0._$_findCachedViewById(R.id.sbImdbRating)).setProgress(0.0f, 10.0f);
        ((RangeSeekBar) this$0._$_findCachedViewById(R.id.sbTomatoMeter)).setProgress(0.0f, 100.0f);
        this$0.imdbRating = "";
        this$0.tomatoMeter = "";
        this$0.year = "";
        this$0.genre = new ArrayList<>();
        for (Gener gener : this$0.genres) {
            gener.setSelect(false);
        }
        this$0.viewList.clear();
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = this$0.genreAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        baseQuickAdapter.setList(this$0.genres);
        this$0.sort = this$0.isMovie ? DEFAULT_SORT : TV_DEFAULT_SORT;
        this$0.rating = "";
        this$0.quality = "";
        this$0.extraCountry.clear();
        this$0.lastSortPosition = 0;
        this$0.selectSortPosition = 0;
        this$0.lastRatingPosition = -1;
        this$0.selectRatingPosition = -1;
        this$0.lastResolutionPosition = -1;
        this$0.selectResolutionPosition = -1;
        this$0.lastGenrePosition = -1;
        this$0.setSelectGenrePosition(-1);
        this$0.lastCountryPosition = 0;
        this$0.selectCountryPosition = 0;
        this$0.selectMorePos = -1;
    }

    static /* synthetic */ void initRecyclerView$default(FilterVideoDialog filterVideoDialog, RecyclerView recyclerView, FilterAdapter filterAdapter, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 5;
        }
        filterVideoDialog.initRecyclerView(recyclerView, filterAdapter, i);
    }

    private final void initRecyclerView(RecyclerView recyclerView, FilterAdapter filterAdapter, int i) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(i, 10, false));
        recyclerView.setAdapter(filterAdapter);
        CommonExtKt.disableRefreshAnimation(recyclerView);
    }

    /* JADX WARN: Removed duplicated region for block: B:205:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x073f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0753  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x07e2  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x07e9  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x07f3  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x07fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void initData() {
        /*
            Method dump skipped, instructions count: 2150
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.dialog.FilterVideoDialog.initData():void");
    }

    private final void setViewsGone() {
        for (View view : this.viewList) {
            if (view != null) {
                CommonExtKt.gone(view);
            }
        }
    }

    private final void setViewGoneByPosition(int i) {
        View view = this.viewList.get(i);
        if (view == null) {
            return;
        }
        CommonExtKt.gone(view);
    }

    private final void setViewVisibleByPosition(int i) {
        View view = this.viewList.get(i);
        if (view == null) {
            return;
        }
        CommonExtKt.visible(view);
    }

    private final void setViewsVisible() {
        for (View view : this.viewList) {
            if (view != null) {
                CommonExtKt.visible(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scaleUp(View view) {
        ViewCompat.animate(view).setDuration(200L).scaleX(1.05f).scaleY(1.05f).start();
    }

    private final void scaleDown(View view) {
        ViewCompat.animate(view).setDuration(200L).scaleX(1.0f).scaleY(1.0f).start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Context context = getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "MoreFilter");
        }
        EventUtils.event("更多的筛选");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* compiled from: FilterVideoDialog.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterVideoDialog$Companion;", "", "()V", "DEFAULT_SORT", "", "TV_DEFAULT_SORT", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterVideoDialog;", "genre", "", "Lcom/movieboxpro/android/model/common/Gener;", "isMovie", "", "genreId", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FilterVideoDialog newInstance(List<? extends Gener> genre, boolean z) {
            Intrinsics.checkNotNullParameter(genre, "genre");
            return newInstance$default(this, genre, z, 0, 4, null);
        }

        private Companion() {
        }

        public static /* synthetic */ FilterVideoDialog newInstance$default(Companion companion, List list, boolean z, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 0;
            }
            return companion.newInstance(list, z, i);
        }

        public final FilterVideoDialog newInstance(List<? extends Gener> genre, boolean z, int i) {
            Intrinsics.checkNotNullParameter(genre, "genre");
            FilterVideoDialog filterVideoDialog = new FilterVideoDialog();
            Bundle bundle = new Bundle();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(genre);
            if (!arrayList.isEmpty()) {
                arrayList.remove(0);
            }
            bundle.putParcelableArrayList("data", arrayList);
            bundle.putBoolean("isMovie", z);
            bundle.putInt("genreId", i);
            filterVideoDialog.setArguments(bundle);
            return filterVideoDialog;
        }
    }
}
