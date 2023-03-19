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
import com.alibaba.fastjson.JSONObject;
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
import com.movieboxpro.android.app.Constant;
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
import com.movieboxpro.android.utils.PrefsUtils;
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
/* compiled from: FilterFavoriteVideoDialog.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 U2\u00020\u0001:\u0002UVB\u0005¢\u0006\u0002\u0010\u0002J\b\u00104\u001a\u000205H\u0003J\b\u00106\u001a\u000205H\u0002J\u0018\u00107\u001a\u0002052\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\"H\u0002J\u0012\u0010;\u001a\u0002052\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J&\u0010>\u001a\u0004\u0018\u0001022\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010C\u001a\u000205H\u0016J\b\u0010D\u001a\u000205H\u0016J\b\u0010E\u001a\u000205H\u0016J\u001a\u0010F\u001a\u0002052\u0006\u0010G\u001a\u0002022\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0010\u0010H\u001a\u0002052\u0006\u0010G\u001a\u000202H\u0002J\u0010\u0010I\u001a\u0002052\u0006\u0010G\u001a\u000202H\u0002J.\u0010J\u001a\u0002052&\u0010K\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040Lj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`MJ&\u0010N\u001a\u0002052\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010O\u001a\u0002052\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010P\u001a\u0002052\u0006\u0010Q\u001a\u00020\u0015H\u0002J\u0010\u0010R\u001a\u0002052\u0006\u0010Q\u001a\u00020\u0015H\u0002J\b\u0010S\u001a\u000205H\u0002J\b\u0010T\u001a\u000205H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\"\u00101\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001020\tj\n\u0012\u0006\u0012\u0004\u0018\u000102`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "country", "", "countryAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/FilterCountry;", "countryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "genre", "genreAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/common/Gener;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "isAll", "", "isGetGenreId", "isMovie", "lastCountryPosition", "", "lastGenrePosition", "lastRatingPosition", "lastResolutionPosition", "lastSortPosition", "lastTypePosition", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog$OnFilterListener;", "quality", "rangeSeekBar", "Lcom/movieboxpro/android/view/widget/MyRangeSeekBar;", "rating", "ratingAdapter", "Lcom/movieboxpro/android/adapter/FilterAdapter;", "resolutionAdapter", "selectCountryPosition", "value", "selectGenrePosition", "setSelectGenrePosition", "(I)V", "selectMorePos", "selectRatingPosition", "selectResolutionPosition", "selectSortPosition", "selectTypePosition", "sort", "sortAdapter", "typeAdapter", "viewList", "Landroid/view/View;", "year", "initData", "", "initListener", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "filterAdapter", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onPause", "onResume", "onStart", "onViewCreated", "view", "scaleDown", "scaleUp", "setFilterData", "filterData", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setFilterListener", "setType", "setViewGoneByPosition", "position", "setViewVisibleByPosition", "setViewsGone", "setViewsVisible", "Companion", "OnFilterListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FilterFavoriteVideoDialog extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_SORT = "";
    private CommBaseAdapter<FilterCountry> countryAdapter;
    private BaseQuickAdapter<Gener, BaseViewHolder> genreAdapter;
    private boolean isAll;
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
    private FilterAdapter typeAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private String year = "0";
    private String genre = "0";
    private String sort = "";
    private String rating = "";
    private String quality = "";
    private String country = "";
    private ArrayList<View> viewList = new ArrayList<>();
    private int selectMorePos = -1;
    private int lastRatingPosition = -1;
    private int selectRatingPosition = -1;
    private int lastResolutionPosition = -1;
    private int selectResolutionPosition = -1;
    private int lastGenrePosition = -1;
    private int selectGenrePosition = -1;

    /* compiled from: FilterFavoriteVideoDialog.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog$OnFilterListener;", "", "onFilter", "", "year", "", "genre", "sort", "rating", "quality", "country", "typeSelect", IjkMediaMeta.IJKM_KEY_TYPE, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    public final void setType(boolean z, boolean z2) {
        this.isMovie = z;
        this.isAll = z2;
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
        ((FrameLayout) _$_findCachedViewById(R.id.flTop)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$mIVFnJ9Zm4N0Y0fI0xrn_58eB68
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog.m976initListener$lambda1(FilterFavoriteVideoDialog.this, view);
            }
        });
        CommBaseAdapter<FilterCountry> commBaseAdapter = this.countryAdapter;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$S_p4Q_APYCnHRYeCqi2fNrVao00
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog.m982initListener$lambda2(FilterFavoriteVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter = this.typeAdapter;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        filterAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$J20FLf-puDmMMO_m-5yVEU3PViU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog.m983initListener$lambda8(FilterFavoriteVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter2 = this.sortAdapter;
        if (filterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter2 = null;
        }
        filterAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$BWu-eRRxgWln9POcOCfcT3z_Xx4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog.m984initListener$lambda9(FilterFavoriteVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter3 = this.ratingAdapter;
        if (filterAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter3 = null;
        }
        filterAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$ppcU-WkQCLNJSW5uRJ-i7jxg8fk
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog.m977initListener$lambda10(FilterFavoriteVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        FilterAdapter filterAdapter4 = this.resolutionAdapter;
        if (filterAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter4 = null;
        }
        filterAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$Hp326BIWd_qiE404c4FDmK0GlC0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterFavoriteVideoDialog.m978initListener$lambda11(FilterFavoriteVideoDialog.this, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this.genreAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        baseQuickAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$P-8Q5-W2rohKtYWqyeKvCBdYi8I
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterFavoriteVideoDialog.m979initListener$lambda13(FilterFavoriteVideoDialog.this, baseQuickAdapter3, view, i);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$WANmefXuLbmkvFpJZ2Ilcvknovo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog.m980initListener$lambda14(FilterFavoriteVideoDialog.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$FilterFavoriteVideoDialog$GCZR3HT_RD9ahQRWoa8KX4imPTU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterFavoriteVideoDialog.m981initListener$lambda16(FilterFavoriteVideoDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m976initListener$lambda1(FilterFavoriteVideoDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m982initListener$lambda2(final FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
            chooseCountryDialog.setListener(new ChooseCountryDialog.OnChooseCountryListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog$initListener$2$1
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
                    FilterFavoriteVideoDialog.this.selectMorePos = i3;
                    String str2 = str;
                    if (!(str2 == null || StringsKt.isBlank(str2))) {
                        i4 = FilterFavoriteVideoDialog.this.lastCountryPosition;
                        commBaseAdapter8 = FilterFavoriteVideoDialog.this.countryAdapter;
                        CommBaseAdapter commBaseAdapter16 = null;
                        if (commBaseAdapter8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter8 = null;
                        }
                        if (i4 != commBaseAdapter8.getData().size() - 1) {
                            commBaseAdapter14 = FilterFavoriteVideoDialog.this.countryAdapter;
                            if (commBaseAdapter14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                                commBaseAdapter14 = null;
                            }
                            i5 = FilterFavoriteVideoDialog.this.lastCountryPosition;
                            FilterCountry filterCountry = (FilterCountry) commBaseAdapter14.getItemOrNull(i5);
                            if (filterCountry != null) {
                                filterCountry.setSelect(false);
                            }
                            commBaseAdapter15 = FilterFavoriteVideoDialog.this.countryAdapter;
                            if (commBaseAdapter15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                                commBaseAdapter15 = null;
                            }
                            i6 = FilterFavoriteVideoDialog.this.lastCountryPosition;
                            commBaseAdapter15.notifyItemChanged(i6);
                        }
                        commBaseAdapter9 = FilterFavoriteVideoDialog.this.countryAdapter;
                        if (commBaseAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter9 = null;
                        }
                        commBaseAdapter10 = FilterFavoriteVideoDialog.this.countryAdapter;
                        if (commBaseAdapter10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter10 = null;
                        }
                        ((FilterCountry) commBaseAdapter9.getItem(commBaseAdapter10.getData().size() - 1)).setCountry("More(" + ((Object) str) + PropertyUtils.MAPPED_DELIM2);
                        commBaseAdapter11 = FilterFavoriteVideoDialog.this.countryAdapter;
                        if (commBaseAdapter11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter11 = null;
                        }
                        commBaseAdapter12 = FilterFavoriteVideoDialog.this.countryAdapter;
                        if (commBaseAdapter12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            commBaseAdapter12 = null;
                        }
                        commBaseAdapter11.notifyItemChanged(commBaseAdapter12.getData().size() - 1);
                        RecyclerView recyclerView = (RecyclerView) FilterFavoriteVideoDialog.this._$_findCachedViewById(R.id.rvCountry);
                        commBaseAdapter13 = FilterFavoriteVideoDialog.this.countryAdapter;
                        if (commBaseAdapter13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        } else {
                            commBaseAdapter16 = commBaseAdapter13;
                        }
                        recyclerView.scrollToPosition(commBaseAdapter16.getData().size() - 1);
                    }
                    FilterFavoriteVideoDialog filterFavoriteVideoDialog = FilterFavoriteVideoDialog.this;
                    if (str == null) {
                        str = "";
                    }
                    filterFavoriteVideoDialog.country = str;
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
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v28, types: [com.movieboxpro.android.view.widget.MyRangeSeekBar] */
    /* JADX WARN: Type inference failed for: r4v30, types: [com.movieboxpro.android.view.widget.MyRangeSeekBar] */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m983initListener$lambda8(FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        FilterAdapter filterAdapter;
        HashMap<String, String> hashMap;
        HashMap<String, String> hashMap2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
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
            this$0.isAll = true;
            RecyclerView rvRating = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
            CommonExtKt.gone(rvRating);
            RecyclerView rvResolution = (RecyclerView) this$0._$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
            CommonExtKt.gone(rvResolution);
            TextView tvRating = (TextView) this$0._$_findCachedViewById(R.id.tvRating);
            Intrinsics.checkNotNullExpressionValue(tvRating, "tvRating");
            CommonExtKt.gone(tvRating);
            TextView tvResolution = (TextView) this$0._$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
            CommonExtKt.gone(tvResolution);
        } else if (i == 1) {
            this$0.isMovie = true;
            ((TextView) this$0._$_findCachedViewById(R.id.tvRating)).setText("MOVIE RATINGS");
            TextView tvRating2 = (TextView) this$0._$_findCachedViewById(R.id.tvRating);
            Intrinsics.checkNotNullExpressionValue(tvRating2, "tvRating");
            CommonExtKt.visible(tvRating2);
            TextView tvResolution2 = (TextView) this$0._$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution2, "tvResolution");
            CommonExtKt.visible(tvResolution2);
            RecyclerView rvResolution2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution2, "rvResolution");
            CommonExtKt.visible(rvResolution2);
            RecyclerView rvRating2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating2, "rvRating");
            CommonExtKt.visible(rvRating2);
            ArrayList arrayListOf = CollectionsKt.arrayListOf(new FilterModel("G", "G", false), new FilterModel("PG", "PG"), new FilterModel("PG-13", "PG-13"), new FilterModel("R", "R"), new FilterModel("NC-17", "NC-17"));
            FilterAdapter filterAdapter6 = this$0.ratingAdapter;
            if (filterAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter6 = null;
            }
            filterAdapter6.setList(arrayListOf);
            ArrayList arrayListOf2 = (this$0.isMovie || this$0.isAll) ? CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", FilterVideoDialog.DEFAULT_SORT)) : CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", "episode_released"));
            FilterModel filterModel = (FilterModel) CollectionsKt.getOrNull(arrayListOf2, this$0.lastSortPosition);
            if (filterModel != null) {
                filterModel.setSelect(true);
            }
            FilterAdapter filterAdapter7 = this$0.sortAdapter;
            if (filterAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter7 = null;
            }
            filterAdapter7.setList(arrayListOf2);
            this$0.selectRatingPosition = -1;
            this$0.lastRatingPosition = -1;
        } else if (i == 2) {
            this$0.isAll = false;
            this$0.isMovie = false;
            RecyclerView rvRating3 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRating);
            Intrinsics.checkNotNullExpressionValue(rvRating3, "rvRating");
            CommonExtKt.visible(rvRating3);
            ((TextView) this$0._$_findCachedViewById(R.id.tvRating)).setText("TVSHOW RATINGS");
            RecyclerView rvResolution3 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvResolution);
            Intrinsics.checkNotNullExpressionValue(rvResolution3, "rvResolution");
            CommonExtKt.gone(rvResolution3);
            TextView tvResolution3 = (TextView) this$0._$_findCachedViewById(R.id.tvResolution);
            Intrinsics.checkNotNullExpressionValue(tvResolution3, "tvResolution");
            CommonExtKt.gone(tvResolution3);
            ArrayList arrayListOf3 = CollectionsKt.arrayListOf(new FilterModel("TV-Y", "TV-Y", false), new FilterModel("TV-Y7", "TV-Y7"), new FilterModel("TV-G", "TV-G"), new FilterModel("TV-PG", "TV-PG"), new FilterModel("TV-14", "TV-14"), new FilterModel("TV-MA", "TV-MA"));
            FilterAdapter filterAdapter8 = this$0.ratingAdapter;
            if (filterAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter8 = null;
            }
            filterAdapter8.setList(arrayListOf3);
            ArrayList arrayListOf4 = (this$0.isMovie || this$0.isAll) ? CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", FilterVideoDialog.DEFAULT_SORT)) : CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", "episode_released"));
            FilterModel filterModel2 = (FilterModel) CollectionsKt.getOrNull(arrayListOf4, this$0.lastSortPosition);
            if (filterModel2 != null) {
                filterModel2.setSelect(true);
            }
            FilterAdapter filterAdapter9 = this$0.sortAdapter;
            if (filterAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter9 = null;
            }
            filterAdapter9.setList(arrayListOf4);
            this$0.selectRatingPosition = -1;
            this$0.lastRatingPosition = -1;
        }
        if (i == 0) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_ALL);
            String str = string;
            if (!(str == null || StringsKt.isBlank(str))) {
                Object parseObject = JSONObject.parseObject(string, HashMap.class);
                if (parseObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String?>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.String?> }");
                }
                hashMap2 = (HashMap) parseObject;
                hashMap = hashMap2;
                filterAdapter = null;
            } else {
                filterAdapter = null;
                hashMap = null;
            }
        } else if (i == 1) {
            String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_MOVIE);
            String str2 = string2;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                Object parseObject2 = JSONObject.parseObject(string2, HashMap.class);
                if (parseObject2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String?>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.String?> }");
                }
                hashMap2 = (HashMap) parseObject2;
                hashMap = hashMap2;
                filterAdapter = null;
            } else {
                filterAdapter = null;
                hashMap = null;
            }
        } else if (i != 2) {
            filterAdapter = null;
            hashMap = null;
        } else {
            String string3 = PrefsUtils.getInstance().getString(Constant.Prefs.FAVORITE_FILTER_TV);
            String str3 = string3;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                Object parseObject3 = JSONObject.parseObject(string3, HashMap.class);
                if (parseObject3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String?>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.String?> }");
                }
                hashMap2 = (HashMap) parseObject3;
                hashMap = hashMap2;
                filterAdapter = null;
            } else {
                filterAdapter = null;
                hashMap = null;
            }
        }
        if (hashMap != null) {
            this$0.setFilterData(hashMap);
        } else {
            this$0.setFilterData(new HashMap<>());
        }
        FilterAdapter filterAdapter10 = this$0.sortAdapter;
        if (filterAdapter10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter10 = filterAdapter;
        }
        FilterModel itemOrNull = filterAdapter10.getItemOrNull(this$0.lastSortPosition);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        FilterAdapter filterAdapter11 = this$0.sortAdapter;
        if (filterAdapter11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter11 = filterAdapter;
        }
        filterAdapter11.notifyItemChanged(this$0.lastSortPosition);
        FilterAdapter filterAdapter12 = this$0.sortAdapter;
        if (filterAdapter12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            filterAdapter12 = filterAdapter;
        }
        Iterator<FilterModel> it = filterAdapter12.getData().iterator();
        int i8 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                i8 = -1;
                break;
            } else if (Intrinsics.areEqual(it.next().getServiceText(), this$0.sort)) {
                i2 = -1;
                break;
            } else {
                i8++;
            }
        }
        if (i8 != i2) {
            this$0.selectSortPosition = i8;
            this$0.lastSortPosition = i8;
            FilterAdapter filterAdapter13 = this$0.sortAdapter;
            if (filterAdapter13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter13 = filterAdapter;
            }
            filterAdapter13.getItem(i8).setSelect(true);
            FilterAdapter filterAdapter14 = this$0.sortAdapter;
            if (filterAdapter14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter14 = filterAdapter;
            }
            filterAdapter14.notifyItemChanged(i8);
        } else {
            this$0.sort = "";
            this$0.selectSortPosition = 0;
            this$0.lastSortPosition = 0;
            FilterAdapter filterAdapter15 = this$0.sortAdapter;
            if (filterAdapter15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter15 = filterAdapter;
            }
            filterAdapter15.getItem(this$0.selectSortPosition).setSelect(true);
            FilterAdapter filterAdapter16 = this$0.sortAdapter;
            if (filterAdapter16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                filterAdapter16 = filterAdapter;
            }
            filterAdapter16.notifyItemChanged(this$0.selectSortPosition);
        }
        FilterAdapter filterAdapter17 = this$0.ratingAdapter;
        if (filterAdapter17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter17 = filterAdapter;
        }
        FilterModel itemOrNull2 = filterAdapter17.getItemOrNull(this$0.lastRatingPosition);
        if (itemOrNull2 != null) {
            itemOrNull2.setSelect(false);
        }
        FilterAdapter filterAdapter18 = this$0.ratingAdapter;
        if (filterAdapter18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter18 = filterAdapter;
        }
        filterAdapter18.notifyItemChanged(this$0.lastRatingPosition);
        FilterAdapter filterAdapter19 = this$0.ratingAdapter;
        if (filterAdapter19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            filterAdapter19 = filterAdapter;
        }
        Iterator<FilterModel> it2 = filterAdapter19.getData().iterator();
        int i9 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i3 = -1;
                i9 = -1;
                break;
            } else if (Intrinsics.areEqual(it2.next().getServiceText(), this$0.rating)) {
                i3 = -1;
                break;
            } else {
                i9++;
            }
        }
        if (i9 != i3) {
            this$0.selectRatingPosition = i9;
            this$0.lastRatingPosition = i9;
            FilterAdapter filterAdapter20 = this$0.ratingAdapter;
            if (filterAdapter20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter20 = filterAdapter;
            }
            filterAdapter20.getItem(i9).setSelect(true);
            FilterAdapter filterAdapter21 = this$0.ratingAdapter;
            if (filterAdapter21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                filterAdapter21 = filterAdapter;
            }
            filterAdapter21.notifyItemChanged(i9);
        } else {
            this$0.selectRatingPosition = i3;
            this$0.lastRatingPosition = i3;
        }
        FilterAdapter filterAdapter22 = this$0.resolutionAdapter;
        if (filterAdapter22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter22 = filterAdapter;
        }
        FilterModel itemOrNull3 = filterAdapter22.getItemOrNull(this$0.lastResolutionPosition);
        if (itemOrNull3 != null) {
            itemOrNull3.setSelect(false);
        }
        FilterAdapter filterAdapter23 = this$0.resolutionAdapter;
        if (filterAdapter23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter23 = filterAdapter;
        }
        filterAdapter23.notifyItemChanged(this$0.lastResolutionPosition);
        FilterAdapter filterAdapter24 = this$0.resolutionAdapter;
        if (filterAdapter24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            filterAdapter24 = filterAdapter;
        }
        Iterator<FilterModel> it3 = filterAdapter24.getData().iterator();
        int i10 = 0;
        while (true) {
            if (!it3.hasNext()) {
                i4 = -1;
                i10 = -1;
                break;
            } else if (Intrinsics.areEqual(it3.next().getServiceText(), this$0.quality)) {
                i4 = -1;
                break;
            } else {
                i10++;
            }
        }
        if (i10 != i4) {
            this$0.selectResolutionPosition = i10;
            this$0.lastResolutionPosition = i10;
            FilterAdapter filterAdapter25 = this$0.resolutionAdapter;
            if (filterAdapter25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter25 = filterAdapter;
            }
            filterAdapter25.getItem(i10).setSelect(true);
            FilterAdapter filterAdapter26 = this$0.resolutionAdapter;
            if (filterAdapter26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                filterAdapter26 = filterAdapter;
            }
            filterAdapter26.notifyItemChanged(i10);
        } else {
            this$0.selectResolutionPosition = i4;
            this$0.lastResolutionPosition = i4;
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = this$0.genreAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter = filterAdapter;
        }
        Gener itemOrNull4 = baseQuickAdapter.getItemOrNull(this$0.lastGenrePosition);
        if (itemOrNull4 != null) {
            itemOrNull4.setSelect(false);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = this$0.genreAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter2 = filterAdapter;
        }
        baseQuickAdapter2.notifyItemChanged(this$0.lastGenrePosition);
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = this$0.genreAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
            baseQuickAdapter3 = filterAdapter;
        }
        Iterator<Gener> it4 = baseQuickAdapter3.getData().iterator();
        int i11 = 0;
        while (true) {
            if (!it4.hasNext()) {
                i5 = -1;
                i11 = -1;
                break;
            } else if (Intrinsics.areEqual(String.valueOf(it4.next().getId()), this$0.genre)) {
                i5 = -1;
                break;
            } else {
                i11++;
            }
        }
        if (i11 != i5) {
            this$0.setSelectGenrePosition(i11);
            this$0.lastGenrePosition = i11;
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = this$0.genreAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter4 = filterAdapter;
            }
            baseQuickAdapter4.getItem(i11).setSelect(true);
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = this$0.genreAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("genreAdapter");
                baseQuickAdapter5 = filterAdapter;
            }
            baseQuickAdapter5.notifyItemChanged(i11);
        } else {
            this$0.setSelectGenrePosition(i5);
            this$0.lastGenrePosition = i5;
        }
        BaseQuickAdapter baseQuickAdapter6 = this$0.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter6 = filterAdapter;
        }
        FilterCountry filterCountry = (FilterCountry) baseQuickAdapter6.getItemOrNull(this$0.lastCountryPosition);
        if (filterCountry != null) {
            filterCountry.setSelect(false);
        }
        RecyclerView.Adapter adapter = this$0.countryAdapter;
        if (adapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            adapter = filterAdapter;
        }
        adapter.notifyItemChanged(this$0.lastCountryPosition);
        BaseQuickAdapter baseQuickAdapter7 = this$0.countryAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter7 = filterAdapter;
        }
        Iterator it5 = baseQuickAdapter7.getData().iterator();
        int i12 = 0;
        while (true) {
            if (!it5.hasNext()) {
                i6 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterCountry) it5.next()).getCountry(), this$0.country)) {
                i6 = i12;
                break;
            } else {
                i12++;
            }
        }
        if (i6 != -1) {
            this$0.selectCountryPosition = i6;
            this$0.lastCountryPosition = i6;
            BaseQuickAdapter baseQuickAdapter8 = this$0.countryAdapter;
            if (baseQuickAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                baseQuickAdapter8 = filterAdapter;
            }
            ((FilterCountry) baseQuickAdapter8.getItem(i6)).setSelect(true);
            RecyclerView.Adapter adapter2 = this$0.countryAdapter;
            if (adapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                adapter2 = filterAdapter;
            }
            adapter2.notifyItemChanged(i6);
            i7 = 1;
        } else {
            this$0.selectCountryPosition = 0;
            this$0.lastCountryPosition = 0;
            BaseQuickAdapter baseQuickAdapter9 = this$0.countryAdapter;
            if (baseQuickAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                baseQuickAdapter9 = filterAdapter;
            }
            i7 = 1;
            ((FilterCountry) baseQuickAdapter9.getItem(this$0.selectCountryPosition)).setSelect(true);
            RecyclerView.Adapter adapter3 = this$0.countryAdapter;
            if (adapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                adapter3 = filterAdapter;
            }
            adapter3.notifyItemChanged(this$0.selectCountryPosition);
        }
        float f = 1900.0f;
        float f2 = Calendar.getInstance().get(i7);
        if (!Intrinsics.areEqual(this$0.year, "0") && ((StringsKt.isBlank(this$0.year) ? 1 : 0) ^ i7) != 0) {
            List split$default = StringsKt.split$default((CharSequence) this$0.year, new String[]{"-"}, false, 0, 6, (Object) null);
            if (split$default.size() == 2) {
                f = Float.parseFloat((String) split$default.get(0));
                f2 = Float.parseFloat((String) split$default.get(1));
            }
        }
        MyRangeSeekBar myRangeSeekBar = this$0.rangeSeekBar;
        MyRangeSeekBar myRangeSeekBar2 = myRangeSeekBar;
        if (myRangeSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
            myRangeSeekBar2 = filterAdapter;
        }
        myRangeSeekBar2.setRange(1900.0f, Calendar.getInstance().get(1), 0.0f);
        MyRangeSeekBar myRangeSeekBar3 = this$0.rangeSeekBar;
        MyRangeSeekBar myRangeSeekBar4 = myRangeSeekBar3;
        if (myRangeSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rangeSeekBar");
            myRangeSeekBar4 = filterAdapter;
        }
        myRangeSeekBar4.setProgress(f, f2);
        ((TextView) this$0._$_findCachedViewById(R.id.tvYear)).setText(((int) f) + " - " + ((int) f2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-9  reason: not valid java name */
    public static final void m984initListener$lambda9(FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    public static final void m977initListener$lambda10(FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    public static final void m978initListener$lambda11(FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
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
    public static final void m979initListener$lambda13(FilterFavoriteVideoDialog this$0, BaseQuickAdapter noName_0, View view, int i) {
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
    public static final void m980initListener$lambda14(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog r11, android.view.View r12) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog.m980initListener$lambda14(com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog, android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-16  reason: not valid java name */
    public static final void m981initListener$lambda16(FilterFavoriteVideoDialog this$0, View view) {
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
        MyRangeSeekBar myRangeSeekBar;
        Gener gener;
        this.viewList.clear();
        ArrayList arrayListOf2 = CollectionsKt.arrayListOf(new FilterModel("   All   ", "All", false), new FilterModel("Movies", "Movie"), new FilterModel("TV Shows", "TV Shows"));
        if (this.isAll) {
            ((FilterModel) arrayListOf2.get(0)).setSelect(true);
            this.selectTypePosition = 0;
            this.lastTypePosition = 0;
        } else if (this.isMovie) {
            ((FilterModel) arrayListOf2.get(1)).setSelect(true);
            this.selectTypePosition = 1;
            this.lastTypePosition = 1;
        } else {
            ((FilterModel) arrayListOf2.get(2)).setSelect(true);
            this.selectTypePosition = 2;
            this.lastTypePosition = 2;
        }
        this.typeAdapter = new FilterAdapter(arrayListOf2);
        RecyclerView rvType = (RecyclerView) _$_findCachedViewById(R.id.rvType);
        Intrinsics.checkNotNullExpressionValue(rvType, "rvType");
        FilterAdapter filterAdapter = this.typeAdapter;
        if (filterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeAdapter");
            filterAdapter = null;
        }
        initRecyclerView(rvType, filterAdapter);
        ArrayList arrayListOf3 = (this.isMovie || this.isAll) ? CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", FilterVideoDialog.DEFAULT_SORT)) : CollectionsKt.arrayListOf(new FilterModel("Default", ""), new FilterModel("Added", "add_time"), new FilterModel("Name", "name", false), new FilterModel("Released", "episode_released"));
        ArrayList arrayList = arrayListOf3;
        Iterator it = arrayList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it.next()).getServiceText(), this.sort)) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            this.selectSortPosition = i;
            this.lastSortPosition = i;
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
        int i2 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i2 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it2.next()).getServiceText(), this.rating)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            this.selectRatingPosition = i2;
            this.lastRatingPosition = i2;
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
        int i3 = 0;
        while (true) {
            if (!it3.hasNext()) {
                i3 = -1;
                break;
            } else if (Intrinsics.areEqual(((FilterModel) it3.next()).getServiceText(), this.quality)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            this.selectResolutionPosition = i3;
            this.lastResolutionPosition = i3;
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
        int i4 = 0;
        while (true) {
            if (!it5.hasNext()) {
                i4 = -1;
                break;
            } else if (Intrinsics.areEqual(String.valueOf(((Gener) it5.next()).getId()), this.genre)) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 != -1) {
            setSelectGenrePosition(i4);
            this.lastGenrePosition = i4;
        } else {
            setSelectGenrePosition(-1);
            this.lastGenrePosition = -1;
        }
        if (CommonExtKt.checkIndexLegal(this.selectGenrePosition, arrayList3) && (gener = (Gener) parcelableArrayList.get(this.selectGenrePosition)) != null) {
            gener.setSelect(true);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Gener, BaseViewHolder>(parcelableArrayList, this) { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog$initData$2
            final /* synthetic */ ArrayList<Gener> $genres;
            final /* synthetic */ FilterFavoriteVideoDialog this$0;

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
                int i5;
                int i6;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.loadWithCorner(getContext(), item.getImg(), (ImageView) holder.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 4.0f));
                View view = holder.getView(R.id.view);
                arrayList4 = this.this$0.viewList;
                arrayList4.add(view);
                if (item.isSelect()) {
                    i6 = this.this$0.selectGenrePosition;
                    if (i6 != -1) {
                        CommonExtKt.gone(view);
                    }
                    this.this$0.scaleUp((ConstraintLayout) holder.getView(R.id.container));
                    return;
                }
                i5 = this.this$0.selectGenrePosition;
                if (i5 == -1) {
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
            int i5 = 0;
            while (true) {
                if (!it6.hasNext()) {
                    i5 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FilterCountry) it6.next()).getCountry(), this.country)) {
                    break;
                } else {
                    i5++;
                }
            }
            if (i5 != -1) {
                this.selectCountryPosition = i5;
                this.lastCountryPosition = i5;
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
        this.countryAdapter = new CommBaseAdapter<>(R.layout.adapter_filter_country_item, new FilterFavoriteVideoDialog$initData$5(this), arrayList5);
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
        myRangeSeekBar2.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog$initData$6
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
                int i6 = (int) f3;
                int i7 = (int) f4;
                String format = String.format("%s - %s", Arrays.copyOf(new Object[]{Integer.valueOf(i6), Integer.valueOf(i7)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((TextView) FilterFavoriteVideoDialog.this._$_findCachedViewById(R.id.tvYear)).setText(format);
                FilterFavoriteVideoDialog filterFavoriteVideoDialog = FilterFavoriteVideoDialog.this;
                if (i6 == 1900 && i7 == Calendar.getInstance().get(1)) {
                    sb = "0";
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i6);
                    sb2.append('-');
                    sb2.append(i7);
                    sb = sb2.toString();
                }
                filterFavoriteVideoDialog.year = sb;
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
            myRangeSeekBar = null;
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

    /* compiled from: FilterFavoriteVideoDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog$Companion;", "", "()V", "DEFAULT_SORT", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterFavoriteVideoDialog;", "genre", "", "Lcom/movieboxpro/android/model/common/Gener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FilterFavoriteVideoDialog newInstance(List<? extends Gener> genre) {
            Intrinsics.checkNotNullParameter(genre, "genre");
            FilterFavoriteVideoDialog filterFavoriteVideoDialog = new FilterFavoriteVideoDialog();
            Bundle bundle = new Bundle();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(genre);
            if (!arrayList.isEmpty()) {
                arrayList.remove(0);
            }
            bundle.putParcelableArrayList("data", arrayList);
            filterFavoriteVideoDialog.setArguments(bundle);
            return filterFavoriteVideoDialog;
        }
    }
}
