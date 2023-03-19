package com.movieboxpro.android.view.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.TimeLineModel;
import com.movieboxpro.android.model.TvShowsUpdateModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.fragment.home.TvGuideContract;
import com.movieboxpro.android.view.fragment.home.TvGuideFragment;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: TvGuideFragment.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002$%B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\bH\u0014J\b\u0010\r\u001a\u00020\u0002H\u0014J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002J\u001c\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001cH\u0014J\b\u0010\u001e\u001a\u00020\u001cH\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001cH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/fragment/home/TvGuidePresenter;", "Lcom/movieboxpro/android/view/fragment/home/TvGuideContract$View;", "()V", "fragment", "Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment$TvGuideListFragment;", "lastSelectedPosition", "", "timeLineAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/TimeLineModel;", "bindLayout", "bindPresenter", "findDates", "", "Ljava/util/Date;", "dBegin", "dEnd", "getLastTimeInterval", "Lkotlin/Pair;", "date", "getTimeInterval", "getTimeNextWeek", "getWeekDayLetter", "", "week", "initData", "", "initListener", "initView", "isToday", "", "calendar", "Ljava/util/Calendar;", "loadData", "Companion", "TvGuideListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvGuideFragment extends BaseMvpFragment<TvGuidePresenter> implements TvGuideContract.View {
    public static final Companion Companion = new Companion(null);
    private TvGuideListFragment fragment;
    private CommBaseAdapter<TimeLineModel> timeLineAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int lastSelectedPosition = -1;

    private final String getWeekDayLetter(int i) {
        switch (i) {
            case 1:
            case 7:
                return ExifInterface.LATITUDE_SOUTH;
            case 2:
                return "M";
            case 3:
            case 5:
                return ExifInterface.GPS_DIRECTION_TRUE;
            case 4:
                return ExifInterface.LONGITUDE_WEST;
            case 6:
                return "F";
            default:
                return "";
        }
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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_tv_guide;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        TimeLineModel timeLineModel;
        Pair<Date, Date> lastTimeInterval = getLastTimeInterval(new Date());
        List<Date> findDates = findDates(lastTimeInterval.getFirst(), lastTimeInterval.getSecond());
        Pair<Date, Date> timeInterval = getTimeInterval(new Date());
        findDates.addAll(findDates(timeInterval.getFirst(), timeInterval.getSecond()));
        Pair<Date, Date> timeNextWeek = getTimeNextWeek(new Date());
        findDates.addAll(findDates(timeNextWeek.getFirst(), timeNextWeek.getSecond()));
        final Ref.IntRef intRef = new Ref.IntRef();
        List<Date> list = findDates;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        String str = "";
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime((Date) obj);
            Intrinsics.checkNotNullExpressionValue(instance, "instance");
            if (isToday(instance)) {
                intRef.element = i;
                str = TimeUtils.INSTANCE.formatTime2(instance.getTimeInMillis());
                timeLineModel = new TimeLineModel(instance.get(5), getWeekDayLetter(instance.get(7)), TimeUtils.INSTANCE.formatTime2(instance.getTimeInMillis()), true);
            } else {
                timeLineModel = new TimeLineModel(instance.get(5), getWeekDayLetter(instance.get(7)), TimeUtils.INSTANCE.formatTime2(instance.getTimeInMillis()));
            }
            arrayList.add(timeLineModel);
            i = i2;
        }
        final ArrayList arrayList2 = arrayList;
        this.timeLineAdapter = new CommBaseAdapter<>(R.layout.adapter_time_line_item, TvGuideFragment$initData$1.INSTANCE, new ArrayList(arrayList2));
        RecyclerView rvTimeLine = (RecyclerView) _$_findCachedViewById(R.id.rvTimeLine);
        Intrinsics.checkNotNullExpressionValue(rvTimeLine, "rvTimeLine");
        CommonExtKt.initLinearAndMargin(rvTimeLine, getContext(), false, 30, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvTimeLine);
        CommBaseAdapter<TimeLineModel> commBaseAdapter = this.timeLineAdapter;
        TvGuideListFragment tvGuideListFragment = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            commBaseAdapter = null;
        }
        recyclerView.setAdapter(commBaseAdapter);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvTimeLine);
        if (recyclerView2 != null) {
            recyclerView2.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.fragment.home.-$$Lambda$TvGuideFragment$4QF0ZstCq_ZORzvrZcYHbyixdTs
                @Override // java.lang.Runnable
                public final void run() {
                    TvGuideFragment.m1250initData$lambda1(Ref.IntRef.this, arrayList2, this);
                }
            }, 500L);
        }
        this.fragment = TvGuideListFragment.Companion.newInstance(str);
        FragmentManager childFragmentManager = getChildFragmentManager();
        TvGuideListFragment tvGuideListFragment2 = this.fragment;
        if (tvGuideListFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            tvGuideListFragment = tvGuideListFragment2;
        }
        FragmentUtils.add(childFragmentManager, tvGuideListFragment, (int) R.id.frameLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m1250initData$lambda1(Ref.IntRef todayIndex, List timeLines, TvGuideFragment this$0) {
        Intrinsics.checkNotNullParameter(todayIndex, "$todayIndex");
        Intrinsics.checkNotNullParameter(timeLines, "$timeLines");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (CommonExtKt.checkIndexLegal(todayIndex.element + 3, timeLines)) {
            RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.rvTimeLine);
            if (recyclerView == null) {
                return;
            }
            recyclerView.scrollToPosition(todayIndex.element + 3);
            return;
        }
        RecyclerView recyclerView2 = (RecyclerView) this$0._$_findCachedViewById(R.id.rvTimeLine);
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.scrollToPosition(todayIndex.element);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        CommBaseAdapter<TimeLineModel> commBaseAdapter = this.timeLineAdapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.home.-$$Lambda$TvGuideFragment$Tdf_XFMlCNcSIgfUjijZmbYsM-o
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvGuideFragment.m1251initListener$lambda2(TvGuideFragment.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1251initListener$lambda2(TvGuideFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String day;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        int i2 = this$0.lastSelectedPosition;
        if (i2 == i) {
            return;
        }
        CommBaseAdapter<TimeLineModel> commBaseAdapter = null;
        if (i2 != -1) {
            CommBaseAdapter<TimeLineModel> commBaseAdapter2 = this$0.timeLineAdapter;
            if (commBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                commBaseAdapter2 = null;
            }
            TimeLineModel item = commBaseAdapter2.getItem(this$0.lastSelectedPosition);
            if (item != null) {
                item.setSelect(false);
            }
            CommBaseAdapter<TimeLineModel> commBaseAdapter3 = this$0.timeLineAdapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                commBaseAdapter3 = null;
            }
            commBaseAdapter3.notifyItemChanged(this$0.lastSelectedPosition);
        }
        CommBaseAdapter<TimeLineModel> commBaseAdapter4 = this$0.timeLineAdapter;
        if (commBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            commBaseAdapter4 = null;
        }
        TimeLineModel item2 = commBaseAdapter4.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        CommBaseAdapter<TimeLineModel> commBaseAdapter5 = this$0.timeLineAdapter;
        if (commBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            commBaseAdapter5 = null;
        }
        commBaseAdapter5.notifyItemChanged(i);
        this$0.lastSelectedPosition = i;
        TvGuideListFragment tvGuideListFragment = this$0.fragment;
        if (tvGuideListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            tvGuideListFragment = null;
        }
        CommBaseAdapter<TimeLineModel> commBaseAdapter6 = this$0.timeLineAdapter;
        if (commBaseAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
        } else {
            commBaseAdapter = commBaseAdapter6;
        }
        TimeLineModel item3 = commBaseAdapter.getItem(i);
        String str = "";
        if (item3 != null && (day = item3.getDay()) != null) {
            str = day;
        }
        tvGuideListFragment.refreshData(str);
    }

    private final boolean isToday(Calendar calendar) {
        return Calendar.getInstance().get(6) == calendar.get(6);
    }

    private final Pair<Date, Date> getLastTimeInterval(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date);
        int i = calendar.get(7) - 1;
        if (i <= 0) {
            i = 7;
        }
        calendar.add(5, (1 - i) - 7);
        calendar2.add(5, (7 - i) - 7);
        return new Pair<>(calendar.getTime(), calendar2.getTime());
    }

    private final Pair<Date, Date> getTimeInterval(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (1 == calendar.get(7)) {
            calendar.add(5, -1);
        }
        calendar.setFirstDayOfWeek(2);
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        Date time = calendar.getTime();
        calendar.add(5, 6);
        return new Pair<>(time, calendar.getTime());
    }

    private final Pair<Date, Date> getTimeNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date);
        int i = calendar.get(7) - 1;
        int i2 = i > 0 ? i : 7;
        calendar.add(5, 8 - i2);
        calendar2.add(5, 14 - i2);
        return new Pair<>(calendar.getTime(), calendar2.getTime());
    }

    private final List<Date> findDates(Date date, Date date2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar.getInstance().setTime(date2);
        while (date2.after(calendar.getTime())) {
            calendar.add(5, 1);
            arrayList.add(calendar.getTime());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public TvGuidePresenter bindPresenter() {
        return new TvGuidePresenter(this);
    }

    /* compiled from: TvGuideFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TvGuideFragment newInstance() {
            TvGuideFragment tvGuideFragment = new TvGuideFragment();
            tvGuideFragment.setArguments(new Bundle());
            return tvGuideFragment;
        }
    }

    /* compiled from: TvGuideFragment.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0014J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment$TvGuideListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/TvShowsUpdateModel;", "", "()V", "day", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "refreshData", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class TvGuideListFragment extends BaseListFragment<TvShowsUpdateModel, String> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private String day = "";

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

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_tv_shows_update_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return Http.getService().TV_guide(API.BASE_URL, "TV_guide", this.day, this.mPageSize, this.mCurrentPage);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            String string;
            String str = "";
            if (bundle != null && (string = bundle.getString("day")) != null) {
                str = string;
            }
            this.day = str;
            this.mClass = TvShowsUpdateModel.class;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.home.-$$Lambda$TvGuideFragment$TvGuideListFragment$nNkGK7EUhP3A6vIerKNgDYCKzOk
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    TvGuideFragment.TvGuideListFragment.m1253onItemClick$lambda1(TvGuideFragment.TvGuideListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-1  reason: not valid java name */
        public static final void m1253onItemClick$lambda1(TvGuideListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            TvShowsUpdateModel tvShowsUpdateModel = (TvShowsUpdateModel) this$0.mAdapter.getItem(i);
            if (tvShowsUpdateModel == null) {
                return;
            }
            TvDetailActivity.start(this$0.getContext(), tvShowsUpdateModel.getId(), tvShowsUpdateModel.getBanner_mini(), tvShowsUpdateModel.getPoster());
        }

        public final void refreshData(String day) {
            Intrinsics.checkNotNullParameter(day, "day");
            this.day = day;
            startRefresh();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, TvShowsUpdateModel item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.tvName, item.getTitle());
            helper.setText(R.id.tvType, item.getCats());
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s Updated", Arrays.copyOf(new Object[]{item.getSeason_episode()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            helper.setText(R.id.tvSeasonInfo, format);
            GlideUtils.loadCornerPortraitGifHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivPoster), 8);
        }

        /* compiled from: TvGuideFragment.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment$TvGuideListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/home/TvGuideFragment$TvGuideListFragment;", "day", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TvGuideListFragment newInstance(String day) {
                Intrinsics.checkNotNullParameter(day, "day");
                TvGuideListFragment tvGuideListFragment = new TvGuideListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("day", day);
                tvGuideListFragment.setArguments(bundle);
                return tvGuideListFragment;
            }
        }
    }
}
