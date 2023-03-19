package com.movieboxpro.android.view.activity.movie;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.OnFilterCompleteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import com.movieboxpro.android.view.fragment.home.MoviesFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: ActMovieActivity.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0014J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/activity/movie/ActMovieActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "dialog", "Lcom/movieboxpro/android/view/dialog/FilterVideoDialog;", "fragment", "Lcom/movieboxpro/android/view/fragment/home/MoviesFragment;", "geners", "", "Lcom/movieboxpro/android/model/common/Gener;", "enableEventBus", "", "getCats", "", "getLayoutResId", "", "initData", "initListener", "initView", "onDestroy", "onFilterDismiss", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnFilterCompleteEvent;", "onStart", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ActMovieActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FilterVideoDialog dialog;
    private MoviesFragment fragment;
    private List<? extends Gener> geners;

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
        return R.layout.activity_act_movie;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movie.-$$Lambda$ActMovieActivity$ad1Hw4NYjKeQfvy-4RJCiIB9Hrg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActMovieActivity.m385initListener$lambda0(ActMovieActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(getIntent().getStringExtra("name"));
        ImageView iv_right = (ImageView) _$_findCachedViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(iv_right, "iv_right");
        CommonExtKt.visible(iv_right);
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_filter_seleted);
        ((ImageView) _$_findCachedViewById(R.id.iv_right)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movie.-$$Lambda$ActMovieActivity$mvkRpr7TpyyA5SQ9NR_yVCyABL8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActMovieActivity.m386initListener$lambda1(ActMovieActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m385initListener$lambda0(ActMovieActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m386initListener$lambda1(ActMovieActivity this$0, View view) {
        FilterVideoDialog filterVideoDialog;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.geners == null) {
            this$0.getCats();
            return;
        }
        if (this$0.dialog == null) {
            FilterVideoDialog.Companion companion = FilterVideoDialog.Companion;
            List<? extends Gener> list = this$0.geners;
            Intrinsics.checkNotNull(list);
            this$0.dialog = FilterVideoDialog.Companion.newInstance$default(companion, list, true, 0, 4, null);
        }
        FilterVideoDialog filterVideoDialog2 = this$0.dialog;
        boolean z = false;
        if (filterVideoDialog2 != null && !filterVideoDialog2.isAdded()) {
            z = true;
        }
        if (!z || (filterVideoDialog = this$0.dialog) == null) {
            return;
        }
        filterVideoDialog.show(this$0.getSupportFragmentManager(), "FilterVideoDialog");
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String stringExtra = getIntent().getStringExtra("act");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.fragment = MoviesFragment.Companion.newInstance(stringExtra);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MoviesFragment moviesFragment = this.fragment;
        Intrinsics.checkNotNull(moviesFragment);
        FragmentUtils.add(supportFragmentManager, moviesFragment, (int) R.id.container);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onFilterDismiss(OnFilterCompleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.isComplete()) {
            ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_filter_seleted);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.iv_right)).setImageResource(R.mipmap.ic_filter);
        }
    }

    private final void getCats() {
        ((ObservableSubscribeProxy) Http.getService().Cat_list(API.BASE_URL, "Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<? extends Gener>>() { // from class: com.movieboxpro.android.view.activity.movie.ActMovieActivity$getCats$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ActMovieActivity.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends Gener> model) {
                FilterVideoDialog filterVideoDialog;
                FilterVideoDialog filterVideoDialog2;
                List<? extends Gener> list;
                FilterVideoDialog filterVideoDialog3;
                Intrinsics.checkNotNullParameter(model, "model");
                ActMovieActivity.this.hideLoadingView();
                ActMovieActivity.this.geners = model;
                filterVideoDialog = ActMovieActivity.this.dialog;
                if (filterVideoDialog == null) {
                    String stringExtra = ActMovieActivity.this.getIntent().getStringExtra("act");
                    ActMovieActivity actMovieActivity = ActMovieActivity.this;
                    FilterVideoDialog.Companion companion = FilterVideoDialog.Companion;
                    list = ActMovieActivity.this.geners;
                    Intrinsics.checkNotNull(list);
                    if (stringExtra == null) {
                        stringExtra = "0";
                    }
                    actMovieActivity.dialog = companion.newInstance(list, true, Integer.parseInt(stringExtra));
                    filterVideoDialog3 = ActMovieActivity.this.dialog;
                    if (filterVideoDialog3 != null) {
                        ArrayList<FilterCountry> arrayList = new ArrayList<>();
                        final ActMovieActivity actMovieActivity2 = ActMovieActivity.this;
                        filterVideoDialog3.setFilterListener(arrayList, new FilterVideoDialog.OnFilterListener() { // from class: com.movieboxpro.android.view.activity.movie.ActMovieActivity$getCats$1$onSuccess$1
                            @Override // com.movieboxpro.android.view.dialog.FilterVideoDialog.OnFilterListener
                            public void typeSelect(int i) {
                            }

                            @Override // com.movieboxpro.android.view.dialog.FilterVideoDialog.OnFilterListener
                            public void onFilter(String year, ArrayList<Integer> genre, String sort, String rating, String quality, ArrayList<String> country, String imdbRating, String tomatoMeter) {
                                MoviesFragment moviesFragment;
                                Intrinsics.checkNotNullParameter(year, "year");
                                Intrinsics.checkNotNullParameter(genre, "genre");
                                Intrinsics.checkNotNullParameter(sort, "sort");
                                Intrinsics.checkNotNullParameter(rating, "rating");
                                Intrinsics.checkNotNullParameter(quality, "quality");
                                Intrinsics.checkNotNullParameter(country, "country");
                                Intrinsics.checkNotNullParameter(imdbRating, "imdbRating");
                                Intrinsics.checkNotNullParameter(tomatoMeter, "tomatoMeter");
                                moviesFragment = ActMovieActivity.this.fragment;
                                if (moviesFragment == null) {
                                    return;
                                }
                                moviesFragment.setFilterRefresh(year, genre, sort, rating, quality, country, imdbRating, tomatoMeter);
                            }
                        });
                    }
                }
                filterVideoDialog2 = ActMovieActivity.this.dialog;
                if (filterVideoDialog2 == null) {
                    return;
                }
                filterVideoDialog2.show(ActMovieActivity.this.getSupportFragmentManager(), "FilterVideoDialog");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ActMovieActivity.this.hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    /* compiled from: ActMovieActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/movie/ActMovieActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "act", "", "actName", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String act, String actName) {
            Intrinsics.checkNotNullParameter(act, "act");
            Intrinsics.checkNotNullParameter(actName, "actName");
            Intent intent = new Intent(context, ActMovieActivity.class);
            intent.putExtra("act", act);
            intent.putExtra("name", actName);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
