package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: AltMovieListActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/AltMovieListActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "enableEventBus", "", "getLayoutResId", "", "initData", "", "initListener", "initView", "onMovieListSelected", "item", "Lcom/movieboxpro/android/event/MovieListSelectedEvent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AltMovieListActivity extends BaseSimpleActivity {
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
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_alt_movie_list;
    }

    /* compiled from: AltMovieListActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/AltMovieListActivity$Companion;", "", "()V", TtmlNode.START, "", "activity", "Landroid/app/Activity;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            activity.startActivity(new Intent(activity, AltMovieListActivity.class));
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltMovieListActivity$8Q6LHiv3Dj8k9Xkj8EG_xfbxMDE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AltMovieListActivity.m427initListener$lambda0(AltMovieListActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m427initListener$lambda0(AltMovieListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMovieListSelected(MovieListSelectedEvent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AltMovieListAllFragment.newInstance(TtmlNode.COMBINE_ALL));
        arrayList.add(AltMovieListMineFragment.newInstance("mine"));
        arrayList.add(AltMovieListMineFragment.newInstance("collect"));
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"All", "Mine", "My Collect"});
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setOffscreenPageLimit(arrayList.size());
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setAdapter(tabLayoutPagerAdapter);
        ((SmartTabLayout) _$_findCachedViewById(R.id.tabLayout)).setViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Choose MovieLists");
        ((TextView) _$_findCachedViewById(R.id.tv_right)).setText("Done");
    }
}
