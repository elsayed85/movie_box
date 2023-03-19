package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.FilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.dialog.ChooseCountryDialog;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.MyRangeSeekBar;
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
import org.apache.commons.beanutils.PropertyUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: FilterFavoriteVideoDialog2.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 V2\u00020\u0001:\u0002VWB\u0005¢\u0006\u0002\u0010\u0002J\b\u00105\u001a\u000206H\u0003J\b\u00107\u001a\u000206H\u0002J\u0018\u00108\u001a\u0002062\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\"H\u0002J\u0012\u0010<\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J&\u0010?\u001a\u0004\u0018\u0001032\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010D\u001a\u000206H\u0016J\b\u0010E\u001a\u000206H\u0016J\b\u0010F\u001a\u000206H\u0016J\u001a\u0010G\u001a\u0002062\u0006\u0010H\u001a\u0002032\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010I\u001a\u0002062\u0006\u0010H\u001a\u000203H\u0002J\u0010\u0010J\u001a\u0002062\u0006\u0010H\u001a\u000203H\u0002J.\u0010K\u001a\u0002062&\u0010L\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040Mj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`NJ&\u0010O\u001a\u0002062\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010P\u001a\u0002062\u0006\u00100\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010Q\u001a\u0002062\u0006\u0010R\u001a\u00020\u0015H\u0002J\u0010\u0010S\u001a\u0002062\u0006\u0010R\u001a\u00020\u0015H\u0002J\b\u0010T\u001a\u000206H\u0002J\b\u0010U\u001a\u000206H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\"\u00102\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001030\tj\n\u0012\u0006\u0012\u0004\u0018\u000103`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog2;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "country", "", "countryAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/FilterCountry;", "countryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "genre", "genreAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/common/Gener;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "isAll", "", "isGetGenreId", "isMovie", "lastCountryPosition", "", "lastGenrePosition", "lastRatingPosition", "lastResolutionPosition", "lastSortPosition", "lastTypePosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog2$OnFilterListener;", "quality", "rangeSeekBar", "Lcom/movieboxpro/android/view/widget/MyRangeSeekBar;", "rating", "ratingAdapter", "Lcom/movieboxpro/android/adapter/FilterAdapter;", "resolutionAdapter", "selectCountryPosition", "value", "selectGenrePosition", "setSelectGenrePosition", "(I)V", "selectMorePos", "selectRatingPosition", "selectResolutionPosition", "selectSortPosition", "selectTypePosition", "sort", "sortAdapter", IjkMediaMeta.IJKM_KEY_TYPE, "typeAdapter", "viewList", "Landroid/view/View;", "year", "initData", "", "initListener", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "filterAdapter", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onPause", "onResume", "onStart", "onViewCreated", "view", "scaleDown", "scaleUp", "setFilterData", "filterData", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setFilterListener", "setType", "setViewGoneByPosition", "position", "setViewVisibleByPosition", "setViewsGone", "setViewsVisible", "Companion", "OnFilterListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FilterFavoriteVideoDialog2 extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_SORT = "";
    private CommBaseAdapter<FilterCountry> countryAdapter;
    private BaseQuickAdapter<Gener, BaseViewHolder> genreAdapter;
    private boolean isGetGenreId;
    private boolean isMovie;
    private int lastCountryPosition;
    private int lastSortPosition;
    private int lastTypePosition;
    private OnFilterListener listener;
    private MyRangeSeekBar rangeSeekBar;
    private FilterAdapter ratingAdapter;
    private FilterAdapter resolutionAdapter;
    private int selectCountryPosition;
    private int selectSortPosition;
    private int selectTypePosition;
    private FilterAdapter sortAdapter;
    private int type;
    private FilterAdapter typeAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private String year = "0";
    private String genre = "0";
    private String sort = "";
    private String rating = "";
    private String quality = "";
    private String country = "";
    private boolean isAll = true;
    private ArrayList<View> viewList = new ArrayList<>();
    private int selectMorePos = -1;
    private int lastRatingPosition = -1;
    private int selectRatingPosition = -1;
    private int lastResolutionPosition = -1;
    private int selectResolutionPosition = -1;
    private int lastGenrePosition = -1;
    private int selectGenrePosition = -1;

    /* compiled from: FilterFavoriteVideoDialog2.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog2$OnFilterListener;", "", "onFilter", "", "year", "", "genre", "sort", "rating", "quality", "country", "typeSelect", IjkMediaMeta.IJKM_KEY_TYPE, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnFilterListener {
        void onFilter(String str, String str2, String str3, String str4, String str5, String str6);

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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.BottomSheetFullScreenDialog);
    }

    public final void setType(int i, boolean z) {
        this.type = i;
        this.isAll = z;
    }

    public final void setFilterData(HashMap<String, String> filterData) {
        Intrinsics.checkNotNullParameter(filterData, "filterData");
        String str = filterData.get("year");
        if (str == null) {
            str = "0";
        }
        this.year = str;
        String str2 = filterData.get("gener");
        this.genre = str2 != null ? str2 : "0";
        String str3 = filterData.get("sort");
        if (str3 == null) {
            str3 = "";
        }
        this.sort = str3;
        String str4 = filterData.get("rating");
        if (str4 == null) {
            str4 = "";
        }
        this.rating = str4;
        String str5 = filterData.get("quality");
        if (str5 == null) {
            str5 = "";
        }
        this.quality = str5;
        String str6 = filterData.get("country");
        this.country = str6 != null ? str6 : "";
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
        return inflater.inflate(R.layout.fragment_filter_favorite_video, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.rangeSeekBar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.rangeSeekBar)");
        this.rangeSeekBar = (MyRangeSeekBar) findViewById;
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
        ((FrameLayout) _$_findCachedViewById(R.id.flTop)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$vQcmYY1ItXuLuqzTl1rvH5W5YII
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog2.m989initListener$lambda1(FilterFavoriteVideoDialog2.this, view);
            }
        });
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$Wu-4-aG1qOAvyKviBKPUtLn73MA
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog2.m995initListener$lambda2(FilterFavoriteVideoDialog2.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter = this.typeAdapter;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        filterAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$7KkxmW285AJ5WrxMi-K8oKgfcnk
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog2.m996initListener$lambda8(FilterFavoriteVideoDialog2.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter2 = this.sortAdapter;
        if (filterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter2 = null;
        }
        filterAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$GQcjHeI-SdTEf66DbZjcSu8eq3Q
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog2.m997initListener$lambda9(FilterFavoriteVideoDialog2.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter3 = this.ratingAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter3 = null;
        }
        filterAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$l2IzCSDNx1-FfceNuGH802Dya8w
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog2.m990initListener$lambda10(FilterFavoriteVideoDialog2.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter4 = this.resolutionAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter4 = null;
        }
        filterAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$JfsJ3D87POG71FYCA6DCCumvDxQ
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog2.m991initListener$lambda11(FilterFavoriteVideoDialog2.this, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this.genreAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        baseQuickAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$X91WHyL6C2Ui5W0gaG-kK02MsqU
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterFavoriteVideoDialog2.m992initListener$lambda13(FilterFavoriteVideoDialog2.this, baseQuickAdapter3, view, i);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$Q9Zoq1_I9Y18Iw0qYMO-zTppNhA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog2.m993initListener$lambda14(FilterFavoriteVideoDialog2.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog2$k_7HuP1lLfybfkfZLBPvLE8SKtE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog2.m994initListener$lambda16(FilterFavoriteVideoDialog2.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m989initListener$lambda1(FilterFavoriteVideoDialog2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m995initListener$lambda2(final FilterFavoriteVideoDialog2 this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
            this$0.selectCountryPosition = i;
            CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter3 = null;
            }
            commBaseAdapter3.getItem(i).setSelect(true);
            int i2 = this$0.lastCountryPosition;
            CommBaseAdapter<FilterCountry> commBaseAdapter4 = this$0.countryAdapter;
            if (commBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter4 = null;
            }
            if (i2 != commBaseAdapter4.getData().size() - 1) {
                CommBaseAdapter<FilterCountry> commBaseAdapter5 = this$0.countryAdapter;
                if (commBaseAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                    commBaseAdapter5 = null;
                }
                FilterCountry itemOrNull = commBaseAdapter5.getItemOrNull(this$0.lastCountryPosition);
                if (itemOrNull != null) {
                    itemOrNull.setSelect(false);
                }
                CommBaseAdapter<FilterCountry> commBaseAdapter6 = this$0.countryAdapter;
                if (commBaseAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                    commBaseAdapter6 = null;
                }
                commBaseAdapter6.notifyItemChanged(this$0.lastCountryPosition);
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter7 = this$0.countryAdapter;
            if (commBaseAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            } else {
                commBaseAdapter2 = commBaseAdapter7;
            }
            commBaseAdapter2.notifyItemChanged(i);
            this$0.lastCountryPosition = i;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ChooseCountryDialog chooseCountryDialog = new ChooseCountryDialog(requireActivity, this$0.selectMorePos, this$0.countryList);
            chooseCountryDialog.setListener(new ChooseCountryDialog.OnChooseCountryListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2$initListener$2$1
                @Override // com.movieboxpro.android.view.dialog.ChooseCountryDialog.OnChooseCountryListener
                public void onChooseCountry(int i3, String str) {
                    int i4;
                    CommBaseAdapter commBaseAdapter8;
                    CommBaseAdapter commBaseAdapter9;
                    CommBaseAdapter commBaseAdapter10;
                    CommBaseAdapter commBaseAdapter11;
                    CommBaseAdapter commBaseAdapter12;
                    CommBaseAdapter commBaseAdapter13;
                    CommBaseAdapter commBaseAdapter14;
                    int i5;
                    CommBaseAdapter commBaseAdapter15;
                    int i6;
                    FilterFavoriteVideoDialog2.this.selectMorePos = i3;
                    String str2 = str;
                    if (!(str2 == null || StringsKt.isBlank(str2))) {
                        i4 = FilterFavoriteVideoDialog2.this.lastCountryPosition;
                        commBaseAdapter8 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        CommBaseAdapter commBaseAdapter16 = null;
                        if (commBaseAdapter8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter8 = null;
                        }
                        if (i4 != commBaseAdapter8.getData().size() - 1) {
                            commBaseAdapter14 = FilterFavoriteVideoDialog2.this.countryAdapter;
                            if (commBaseAdapter14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                                commBaseAdapter14 = null;
                            }
                            i5 = FilterFavoriteVideoDialog2.this.lastCountryPosition;
                            FilterCountry filterCountry = (FilterCountry) commBaseAdapter14.getItemOrNull(i5);
                            if (filterCountry != null) {
                                filterCountry.setSelect(false);
                            }
                            commBaseAdapter15 = FilterFavoriteVideoDialog2.this.countryAdapter;
                            if (commBaseAdapter15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                                commBaseAdapter15 = null;
                            }
                            i6 = FilterFavoriteVideoDialog2.this.lastCountryPosition;
                            commBaseAdapter15.notifyItemChanged(i6);
                        }
                        commBaseAdapter9 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        if (commBaseAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter9 = null;
                        }
                        commBaseAdapter10 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        if (commBaseAdapter10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter10 = null;
                        }
                        ((FilterCountry) commBaseAdapter9.getItem(commBaseAdapter10.getData().size() - 1)).setCountry("More(" + ((Object) str) + PropertyUtils.MAPPED_DELIM2);
                        commBaseAdapter11 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        if (commBaseAdapter11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter11 = null;
                        }
                        commBaseAdapter12 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        if (commBaseAdapter12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter12 = null;
                        }
                        commBaseAdapter11.notifyItemChanged(commBaseAdapter12.getData().size() - 1);
                        RecyclerView recyclerView = (RecyclerView) FilterFavoriteVideoDialog2.this._$_findCachedViewById(R.id.rvCountry);
                        commBaseAdapter13 = FilterFavoriteVideoDialog2.this.countryAdapter;
                        if (commBaseAdapter13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        } else {
                            commBaseAdapter16 = commBaseAdapter13;
                        }
                        recyclerView.scrollToPosition(commBaseAdapter16.getData().size() - 1);
                    }
                    FilterFavoriteVideoDialog2 filterFavoriteVideoDialog2 = FilterFavoriteVideoDialog2.this;
                    if (str == null) {
                        str = "";
                    }
                    filterFavoriteVideoDialog2.country = str;
                }
            });
            chooseCountryDialog.show();
        } else if (this$0.lastCountryPosition == i) {
        } else {
            CommBaseAdapter<FilterCountry> commBaseAdapter8 = this$0.countryAdapter;
            if (commBaseAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter8 = null;
            }
            String country2 = commBaseAdapter8.getItem(i).getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "countryAdapter.getItem(position).country");
            this$0.country = country2;
            this$0.selectCountryPosition = i;
            CommBaseAdapter<FilterCountry> commBaseAdapter9 = this$0.countryAdapter;
            if (commBaseAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter9 = null;
            }
            commBaseAdapter9.getItem(i).setSelect(true);
            CommBaseAdapter<FilterCountry> commBaseAdapter10 = this$0.countryAdapter;
            if (commBaseAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter10 = null;
            }
            FilterCountry itemOrNull2 = commBaseAdapter10.getItemOrNull(this$0.lastCountryPosition);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter11 = this$0.countryAdapter;
            if (commBaseAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter11 = null;
            }
            commBaseAdapter11.notifyItemChanged(i);
            CommBaseAdapter<FilterCountry> commBaseAdapter12 = this$0.countryAdapter;
            if (commBaseAdapter12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter12 = null;
            }
            commBaseAdapter12.notifyItemChanged(this$0.lastCountryPosition);
            this$0.lastCountryPosition = i;
            CommBaseAdapter<FilterCountry> commBaseAdapter13 = this$0.countryAdapter;
            if (commBaseAdapter13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter13 = null;
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter14 = this$0.countryAdapter;
            if (commBaseAdapter14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            } else {
                commBaseAdapter2 = commBaseAdapter14;
            }
            commBaseAdapter13.notifyItemChanged(commBaseAdapter2.getData().size() - 1);
            this$0.selectMorePos = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x047c  */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m996initListener$lambda8(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2 r10, com.chad.library.adapter.base.BaseQuickAdapter r11, android.view.View r12, int r13) {
        /*
            Method dump skipped, instructions count: 1188
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2.m996initListener$lambda8(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2, com.chad.library.adapter.base.BaseQuickAdapter, android.view.View, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m997initListener$lambda9(FilterFavoriteVideoDialog2 this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    /* renamed from: initListener$lambda-10  reason: not valid java name */
    public static final void m990initListener$lambda10(FilterFavoriteVideoDialog2 this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    /* renamed from: initListener$lambda-11  reason: not valid java name */
    public static final void m991initListener$lambda11(FilterFavoriteVideoDialog2 this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    /* renamed from: initListener$lambda-13  reason: not valid java name */
    public static final void m992initListener$lambda13(FilterFavoriteVideoDialog2 this$0, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        if (this$0.lastGenrePosition == i) {
            this$0.scaleDown(view);
            this$0.lastGenrePosition = -1;
            this$0.setSelectGenrePosition(-1);
            this$0.genre = "0";
            this$0.setViewsGone();
            return;
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = this$0.genreAdapter;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter = null;
        }
        String valueOf = String.valueOf(baseQuickAdapter.getItem(i).getId());
        if (valueOf == null) {
            valueOf = "";
        }
        this$0.genre = valueOf;
        this$0.scaleUp(view);
        boolean z = this$0.selectGenrePosition == -1;
        this$0.setSelectGenrePosition(i);
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = this$0.genreAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.getItem(i).setSelect(true);
        if (this$0.lastGenrePosition != -1) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = this$0.genreAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter4 = null;
            }
            View viewByPosition = baseQuickAdapter4.getViewByPosition(this$0.lastGenrePosition, R.id.container);
            if (viewByPosition != null) {
                this$0.scaleDown(viewByPosition);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = this$0.genreAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter5;
            }
            Gener itemOrNull = baseQuickAdapter2.getItemOrNull(this$0.lastGenrePosition);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
        }
        if (z) {
            this$0.setViewsVisible();
        }
        int i2 = this$0.lastGenrePosition;
        if (i2 != -1) {
            this$0.setViewVisibleByPosition(i2);
        }
        this$0.setViewGoneByPosition(i);
        this$0.lastGenrePosition = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* renamed from: initListener$lambda-14  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m993initListener$lambda14(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2 r11, android.view.View r12) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2.m993initListener$lambda14(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2, android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-16  reason: not valid java name */
    public static final void m994initListener$lambda16(FilterFavoriteVideoDialog2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MyRangeSeekBar myRangeSeekBar = null;
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
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = this$0.genreAdapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter = null;
            }
            Gener itemOrNull4 = baseQuickAdapter.getItemOrNull(this$0.selectGenrePosition);
            if (itemOrNull4 != null) {
                itemOrNull4.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this$0.genreAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter2 = null;
            }
            View viewByPosition = baseQuickAdapter2.getViewByPosition(this$0.selectGenrePosition, R.id.container);
            if (viewByPosition != null) {
                this$0.scaleDown(viewByPosition);
            }
        }
        if (this$0.selectCountryPosition != 0) {
            CommBaseAdapter<FilterCountry> commBaseAdapter = this$0.countryAdapter;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter = null;
            }
            FilterCountry itemOrNull5 = commBaseAdapter.getItemOrNull(0);
            if (itemOrNull5 != null) {
                itemOrNull5.setSelect(true);
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter2 = this$0.countryAdapter;
            if (commBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter2 = null;
            }
            commBaseAdapter2.notifyItemChanged(0);
            CommBaseAdapter<FilterCountry> commBaseAdapter3 = this$0.countryAdapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter3 = null;
            }
            FilterCountry itemOrNull6 = commBaseAdapter3.getItemOrNull(this$0.selectCountryPosition);
            if (itemOrNull6 != null) {
                itemOrNull6.setSelect(false);
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter4 = this$0.countryAdapter;
            if (commBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter4 = null;
            }
            commBaseAdapter4.notifyItemChanged(this$0.selectCountryPosition);
            CommBaseAdapter<FilterCountry> commBaseAdapter5 = this$0.countryAdapter;
            if (commBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter5 = null;
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter6 = this$0.countryAdapter;
            if (commBaseAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter6 = null;
            }
            commBaseAdapter5.getItem(commBaseAdapter6.getData().size() - 1).setCountry("More");
            CommBaseAdapter<FilterCountry> commBaseAdapter7 = this$0.countryAdapter;
            if (commBaseAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter7 = null;
            }
            CommBaseAdapter<FilterCountry> commBaseAdapter8 = this$0.countryAdapter;
            if (commBaseAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                commBaseAdapter8 = null;
            }
            commBaseAdapter7.notifyItemChanged(commBaseAdapter8.getData().size() - 1);
        }
        MyRangeSeekBar myRangeSeekBar2 = this$0.rangeSeekBar;
        if (myRangeSeekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
        } else {
            myRangeSeekBar = myRangeSeekBar2;
        }
        myRangeSeekBar.setProgress(1900.0f, Calendar.getInstance().get(1));
        this$0.year = "0";
        this$0.genre = "0";
        this$0.sort = "";
        this$0.rating = "";
        this$0.quality = "";
        this$0.country = "";
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

    private final void initRecyclerView(RecyclerView recyclerView, FilterAdapter filterAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.addItemDecoration(new LayoutManagerItemDecoration(0, 10));
        recyclerView.setAdapter(filterAdapter);
        CommonExtKt.disableRefreshAnimation(recyclerView);
    }

    private final void initData() {
        ArrayList arrayListOf;
        Gener gener;
        this.viewList.clear();
        ArrayList arrayListOf2 = CollectionsKt.arrayListOf(new FilterModel("WAITING", "WAITING", false), new FilterModel("WATCHED", "WATCHED"), new FilterModel(MainActivity.TAG_FAVORITE, MainActivity.TAG_FAVORITE));
        ((FilterModel) arrayListOf2.get(this.type)).setSelect(true);
        int i = this.type;
        this.selectTypePosition = i;
        this.lastTypePosition = i;
        this.typeAdapter = new FilterAdapter(arrayListOf2);
        RecyclerView rvType = (RecyclerView) _$_findCachedViewById(R.id.rvType);
        Intrinsics.checkNotNullExpressionValue(rvType, "rvType");
        FilterAdapter filterAdapter = this.typeAdapter;
        MyRangeSeekBar myRangeSeekBar = null;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        initRecyclerView(rvType, filterAdapter);
        ArrayList arrayListOf3 = (this.isMovie || this.isAll) ? CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", FilterVideoDialog.DEFAULT_SORT)) : CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", "episode_released"));
        ArrayList arrayList = arrayListOf3;
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it.next()).getServiceText(), this.sort)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            this.selectSortPosition = i2;
            this.lastSortPosition = i2;
        } else {
            this.sort = "";
            this.selectSortPosition = 0;
            this.lastSortPosition = 0;
        }
        if (CommonExtKt.checkIndexLegal(this.selectSortPosition, arrayList)) {
            ((FilterModel) arrayListOf3.get(this.selectSortPosition)).setSelect(true);
        }
        this.sortAdapter = new FilterAdapter(arrayList);
        RecyclerView rvSort = (RecyclerView) _$_findCachedViewById(R.id.rvSort);
        Intrinsics.checkNotNullExpressionValue(rvSort, "rvSort");
        FilterAdapter filterAdapter2 = this.sortAdapter;
        if (filterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter2 = null;
        }
        initRecyclerView(rvSort, filterAdapter2);
        if (this.isMovie) {
            arrayListOf = CollectionsKt.arrayListOf(new FilterModel("G", "G", false), new FilterModel("PG", "PG"), new FilterModel("PG-13", "PG-13"), new FilterModel("R", "R"), new FilterModel("NC-17", "NC-17"));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tvRating)).setText("TVSHOW RATINGS");
            RecyclerView rvResolution = (RecyclerView) _$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
            CommonExtKt.gone(rvResolution);
            TextView tvResolution = (TextView) _$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
            CommonExtKt.gone(tvResolution);
            arrayListOf = CollectionsKt.arrayListOf(new FilterModel("TV-Y", "TV-Y", false), new FilterModel("TV-Y7", "TV-Y7"), new FilterModel("TV-G", "TV-G"), new FilterModel("TV-PG", "TV-PG"), new FilterModel("TV-14", "TV-14"), new FilterModel("TV-MA", "TV-MA"));
        }
        Iterator it2 = arrayListOf.iterator();
        int i3 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i3 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it2.next()).getServiceText(), this.rating)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            this.selectRatingPosition = i3;
            this.lastRatingPosition = i3;
        } else {
            this.selectRatingPosition = -1;
            this.lastRatingPosition = -1;
        }
        if (CommonExtKt.checkIndexLegal(this.selectRatingPosition, arrayListOf)) {
            ((FilterModel) arrayListOf.get(this.selectRatingPosition)).setSelect(true);
        }
        this.ratingAdapter = new FilterAdapter(arrayListOf);
        RecyclerView rvRating = (RecyclerView) _$_findCachedViewById(R.id.rvRating);
        Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
        FilterAdapter filterAdapter3 = this.ratingAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter3 = null;
        }
        initRecyclerView(rvRating, filterAdapter3);
        ArrayList arrayListOf4 = CollectionsKt.arrayListOf(new FilterModel("1080P", "1080p"), new FilterModel("4K", "4k"), new FilterModel("8K", "8k"), new FilterModel("BLU-RAY", "blu-ray"));
        ArrayList arrayList2 = arrayListOf4;
        Iterator it3 = arrayList2.iterator();
        int i4 = 0;
        while (true) {
            if (!it3.hasNext()) {
                i4 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it3.next()).getServiceText(), this.quality)) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 != -1) {
            this.selectResolutionPosition = i4;
            this.lastResolutionPosition = i4;
        } else {
            this.selectResolutionPosition = -1;
            this.lastResolutionPosition = -1;
        }
        if (CommonExtKt.checkIndexLegal(this.selectResolutionPosition, arrayList2)) {
            ((FilterModel) arrayListOf4.get(this.selectResolutionPosition)).setSelect(true);
        }
        this.resolutionAdapter = new FilterAdapter(arrayList2);
        RecyclerView rvResolution2 = (RecyclerView) _$_findCachedViewById(R.id.rvResolution);
        Intrinsics.checkNotNullExpressionValue(rvResolution2, "rvResolution");
        FilterAdapter filterAdapter4 = this.resolutionAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter4 = null;
        }
        initRecyclerView(rvResolution2, filterAdapter4);
        Bundle arguments = getArguments();
        final ArrayList parcelableArrayList = arguments == null ? null : arguments.getParcelableArrayList("data");
        if (parcelableArrayList == null) {
            parcelableArrayList = new ArrayList();
        }
        Iterator it4 = parcelableArrayList.iterator();
        while (true) {
            if (!it4.hasNext()) {
                break;
            }
            Gener gener2 = (Gener) it4.next();
            if (gener2.isSelect()) {
                gener2.setSelect(false);
                break;
            }
        }
        Unit unit = Unit.INSTANCE;
        ArrayList arrayList3 = parcelableArrayList;
        Iterator it5 = arrayList3.iterator();
        int i5 = 0;
        while (true) {
            if (!it5.hasNext()) {
                i5 = -1;
                break;
            } else if (Intrinsics.areEqual(String.valueOf(((Gener) it5.next()).getId()), this.genre)) {
                break;
            } else {
                i5++;
            }
        }
        if (i5 != -1) {
            setSelectGenrePosition(i5);
            this.lastGenrePosition = i5;
        } else {
            setSelectGenrePosition(-1);
            this.lastGenrePosition = -1;
        }
        if (CommonExtKt.checkIndexLegal(this.selectGenrePosition, arrayList3) && (gener = (Gener) parcelableArrayList.get(this.selectGenrePosition)) != null) {
            gener.setSelect(true);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Gener, BaseViewHolder>(parcelableArrayList, this) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2$initData$2
            final /* synthetic */ ArrayList<Gener> $genres;
            final /* synthetic */ FilterFavoriteVideoDialog2 this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_filter_genre_item, parcelableArrayList);
                this.$genres = parcelableArrayList;
                this.this$0 = this;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, Gener item) {
                ArrayList arrayList4;
                int i6;
                int i7;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.loadWithCorner(getContext(), item.getImg(), (ImageView) holder.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 4.0f));
                View view = holder.getView(R.id.view);
                arrayList4 = this.this$0.viewList;
                arrayList4.add(view);
                if (item.isSelect()) {
                    i7 = this.this$0.selectGenrePosition;
                    if (i7 != -1) {
                        CommonExtKt.gone(view);
                    }
                    this.this$0.scaleUp((ConstraintLayout) holder.getView(R.id.container));
                    return;
                }
                i6 = this.this$0.selectGenrePosition;
                if (i6 == -1) {
                    CommonExtKt.gone(view);
                } else {
                    CommonExtKt.visible(view);
                }
            }
        };
        this.genreAdapter = baseQuickAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.addChildClickViewIds(R.id.container);
        if (getContext() != null && CommonUtils.isTablet()) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvGenre)).setLayoutManager(new GridLayoutManager(getContext(), 5));
            ((RecyclerView) _$_findCachedViewById(R.id.rvGenre)).addItemDecoration(new GridSpacingItemDecoration(5, DensityUtils.dp2px(App.getContext(), 15.0f), true));
        } else {
            ((RecyclerView) _$_findCachedViewById(R.id.rvGenre)).setLayoutManager(new GridLayoutManager(getContext(), 3));
            ((RecyclerView) _$_findCachedViewById(R.id.rvGenre)).addItemDecoration(new GridSpacingItemDecoration(3, DensityUtils.dp2px(App.getContext(), 15.0f), true));
        }
        ((RecyclerView) _$_findCachedViewById(R.id.rvGenre)).setNestedScrollingEnabled(false);
        RecyclerView rvGenre = (RecyclerView) _$_findCachedViewById(R.id.rvGenre);
        Intrinsics.checkNotNullExpressionValue(rvGenre, "rvGenre");
        CommonExtKt.disableRefreshAnimation(rvGenre);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvGenre);
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this.genreAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter2 = null;
        }
        recyclerView.setAdapter(baseQuickAdapter2);
        ArrayList<FilterCountry> arrayList4 = new ArrayList();
        arrayList4.add(new FilterCountry("All", R.mipmap.all));
        if (this.countryList.size() >= 12) {
            arrayList4.addAll(this.countryList.subList(0, 10));
        }
        for (FilterCountry filterCountry : arrayList4) {
            filterCountry.setSelect(false);
        }
        if (!(!StringsKt.isBlank(this.country))) {
            this.selectCountryPosition = 0;
            this.lastCountryPosition = 0;
        } else {
            Iterator it6 = arrayList4.iterator();
            int i6 = 0;
            while (true) {
                if (!it6.hasNext()) {
                    i6 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FilterCountry) it6.next()).getCountry(), this.country)) {
                    break;
                } else {
                    i6++;
                }
            }
            if (i6 != -1) {
                this.selectCountryPosition = i6;
                this.lastCountryPosition = i6;
            } else {
                this.selectCountryPosition = 0;
                this.lastCountryPosition = 0;
            }
        }
        if (StringsKt.isBlank(this.country)) {
            arrayList4.add(new FilterCountry("More", -1));
        } else if (this.selectMorePos != -1) {
            arrayList4.add(new FilterCountry("More(" + this.country + PropertyUtils.MAPPED_DELIM2, -1));
        } else {
            arrayList4.add(new FilterCountry("More", -1));
        }
        ArrayList arrayList5 = arrayList4;
        FilterCountry filterCountry2 = (FilterCountry) CollectionsKt.getOrNull(arrayList5, this.lastCountryPosition);
        if (filterCountry2 != null) {
            filterCountry2.setSelect(true);
        }
        this.countryAdapter = new CommBaseAdapter<>(R.layout.adapter_filter_country_item, new FilterFavoriteVideoDialog2$initData$5(this), arrayList5);
        ((RecyclerView) _$_findCachedViewById(R.id.rvCountry)).setLayoutManager(new GridLayoutManager(getContext(), 3));
        ((RecyclerView) _$_findCachedViewById(R.id.rvCountry)).addItemDecoration(new GridSpacingItemDecoration(3, 10, false));
        RecyclerView rvCountry = (RecyclerView) _$_findCachedViewById(R.id.rvCountry);
        Intrinsics.checkNotNullExpressionValue(rvCountry, "rvCountry");
        CommonExtKt.disableRefreshAnimation(rvCountry);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvCountry);
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        recyclerView2.setAdapter(commBaseAdapter);
        float f = 1900.0f;
        float f2 = Calendar.getInstance().get(1);
        if (!Intrinsics.areEqual(this.year, "0") && (!StringsKt.isBlank(this.year))) {
            List split$default = StringsKt.split$default((CharSequence) this.year, new String[]{"-"}, false, 0, 6, (Object) null);
            if (split$default.size() == 2) {
                f = Float.parseFloat((String) split$default.get(0));
                f2 = Float.parseFloat((String) split$default.get(1));
            }
        }
        MyRangeSeekBar myRangeSeekBar2 = this.rangeSeekBar;
        if (myRangeSeekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
            myRangeSeekBar2 = null;
        }
        myRangeSeekBar2.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog2$initData$6
            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
            }

            @Override // com.jaygoo.widget.OnRangeChangedListener
            public void onRangeChanged(RangeSeekBar rangeSeekBar, float f3, float f4, boolean z) {
                String sb;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                int i7 = (int) f3;
                int i8 = (int) f4;
                String format = String.format("%s - %s", Arrays.copyOf(new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) FilterFavoriteVideoDialog2.this._$_findCachedViewById(R.id.tvYear)).setText(format);
                FilterFavoriteVideoDialog2 filterFavoriteVideoDialog2 = FilterFavoriteVideoDialog2.this;
                if (i7 == 1900 && i8 == Calendar.getInstance().get(1)) {
                    sb = "0";
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i7);
                    sb2.append('-');
                    sb2.append(i8);
                    sb = sb2.toString();
                }
                filterFavoriteVideoDialog2.year = sb;
            }
        });
        MyRangeSeekBar myRangeSeekBar3 = this.rangeSeekBar;
        if (myRangeSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
            myRangeSeekBar3 = null;
        }
        myRangeSeekBar3.setRange(1900.0f, Calendar.getInstance().get(1), 0.0f);
        MyRangeSeekBar myRangeSeekBar4 = this.rangeSeekBar;
        if (myRangeSeekBar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
        } else {
            myRangeSeekBar = myRangeSeekBar4;
        }
        myRangeSeekBar.setProgress(f, f2);
        ((TextView) _$_findCachedViewById(R.id.tvYear)).setText(((int) f) + " - " + ((int) f2));
        if (this.isAll) {
            RecyclerView rvRating2 = (RecyclerView) _$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating2, "rvRating");
            CommonExtKt.gone(rvRating2);
            RecyclerView rvResolution3 = (RecyclerView) _$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution3, "rvResolution");
            CommonExtKt.gone(rvResolution3);
            TextView tvRating = (TextView) _$_findCachedViewById(R.id.tvRating);
            Intrinsics.checkNotNullExpressionValue(tvRating, "tvRating");
            CommonExtKt.gone(tvRating);
        }
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
        ViewCompat.animate(view).setDuration(200L).scaleX(1.2f).scaleY(1.2f).start();
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

    /* compiled from: FilterFavoriteVideoDialog2.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog2$Companion;", "", "()V", "DEFAULT_SORT", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog2;", "genre", "", "Lcom/movieboxpro/android/model/common/Gener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FilterFavoriteVideoDialog2 newInstance(List<? extends Gener> genre) {
            Intrinsics.checkNotNullParameter(genre, "genre");
            FilterFavoriteVideoDialog2 filterFavoriteVideoDialog2 = new FilterFavoriteVideoDialog2();
            Bundle bundle = new Bundle();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(genre);
            if (!arrayList.isEmpty()) {
                arrayList.remove(0);
            }
            bundle.putParcelableArrayList("data", arrayList);
            filterFavoriteVideoDialog2.setArguments(bundle);
            return filterFavoriteVideoDialog2;
        }
    }
}
