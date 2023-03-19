package com.movieboxpro.android.view.activity.movielist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.view.activity.movielist.PlayListRankActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: PlayListRankActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/PlayListRankActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "getStatusColor", "initData", "", "initListener", "initView", "Companion", "PlayListRankListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlayListRankActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_play_list_rank;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    /* compiled from: PlayListRankActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/PlayListRankActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intent intent = new Intent(context, PlayListRankActivity.class);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$PlayListRankActivity$B1DlwkKDgBygOnAvQFGUg_1MGJA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayListRankActivity.m405initListener$lambda0(PlayListRankActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m405initListener$lambda0(PlayListRankActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PlayListRankListFragment.Companion.newInstance("total"));
        arrayList.add(PlayListRankListFragment.Companion.newInstance("day"));
        arrayList.add(PlayListRankListFragment.Companion.newInstance("month"));
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"All", "TODAY", "MONTH"});
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setOffscreenPageLimit(arrayList.size());
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setAdapter(tabLayoutPagerAdapter);
        ((SmartTabLayout) _$_findCachedViewById(R.id.tabLayout)).setViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Rank");
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
    }

    /* compiled from: PlayListRankActivity.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u00020\rH\u0014J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/PlayListRankActivity$PlayListRankListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "()V", IjkMediaMeta.IJKM_KEY_TYPE, "getBundle", "", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isVerticalLayout", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PlayListRankListFragment extends BaseListFragment<MovieListModel.MovieListItem, String> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private String type = "";

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
            return R.layout.adapter_compilations_list_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isVerticalLayout() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return Http.getService().Playlists_ranking(API.BASE_URL, "Playlists_ranking", this.type, this.mCurrentPage, this.mPageSize);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$PlayListRankActivity$PlayListRankListFragment$kZHimd0Ib6YJCA95xVGgmYRX4ck
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    PlayListRankActivity.PlayListRankListFragment.m406onItemClick$lambda1(PlayListRankActivity.PlayListRankListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-1  reason: not valid java name */
        public static final void m406onItemClick$lambda1(PlayListRankListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            String str;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this$0.mAdapter.getItem(i);
            if (movieListItem == null) {
                return;
            }
            if (movieListItem.getImgArr() == null || movieListItem.getImgArr().size() < 1) {
                str = "";
            } else {
                String str2 = movieListItem.getImgArr().get(0);
                Intrinsics.checkNotNullExpressionValue(str2, "item.imgArr[0]");
                str = str2;
            }
            MovieListDetailActivity.start(this$0.getContext(), movieListItem.getLid(), movieListItem.getUsername(), str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            if (CommonUtils.isTablet()) {
                recyclerView.addItemDecoration(new GridInsetDecoration(16, 0, true));
            } else {
                recyclerView.addItemDecoration(new GridInsetDecoration(16, 0, true));
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return CommonUtils.isTablet() ? 2 : 1;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.type = bundle == null ? null : bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE);
            this.mClass = MovieListModel.MovieListItem.class;
            this.mPageSize = 8;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ImageView imageView = (ImageView) helper.getView(R.id.imageView);
            TextView textView = (TextView) helper.getView(R.id.tvName);
            TextView textView2 = (TextView) helper.getView(R.id.tvNum);
            if (item.getImgArr() != null) {
                List<String> imgArr = item.getImgArr();
                Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
                if (!imgArr.isEmpty()) {
                    GlideUtils.loadWithCorner(getContext(), item.getImgArr().get(0), imageView, DensityUtils.dp2px(App.getContext(), 4.0f));
                }
            }
            String name = item.getName();
            Intrinsics.checkNotNullExpressionValue(name, "item.name");
            CommonExtKt.textShadow$default(textView, name, 10, 0, 4, null);
            CommonExtKt.textShadow$default(textView2, String.valueOf(item.getCount()), 10, 0, 4, null);
            List<String> movieArr = item.getMovieArr();
            int size = movieArr == null ? 0 : movieArr.size();
            if (size == 1) {
                helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
            } else if (size == 2) {
                helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
                helper.setText(R.id.tvTwo, Intrinsics.stringPlus("2.", movieArr.get(1)));
            } else if (size == 3) {
                helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
                helper.setText(R.id.tvTwo, Intrinsics.stringPlus("2.", movieArr.get(1)));
                helper.setText(R.id.tvThree, Intrinsics.stringPlus("3.", movieArr.get(2)));
            } else {
                Unit unit = Unit.INSTANCE;
            }
        }

        /* compiled from: PlayListRankActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/PlayListRankActivity$PlayListRankListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/movielist/PlayListRankActivity$PlayListRankListFragment;", IjkMediaMeta.IJKM_KEY_TYPE, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final PlayListRankListFragment newInstance(String type) {
                Intrinsics.checkNotNullParameter(type, "type");
                PlayListRankListFragment playListRankListFragment = new PlayListRankListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, type);
                playListRankListFragment.setArguments(bundle);
                return playListRankListFragment;
            }
        }
    }
}
