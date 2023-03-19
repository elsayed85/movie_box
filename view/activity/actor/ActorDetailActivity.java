package com.movieboxpro.android.view.activity.actor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.model.ActorDetailModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LayoutManagerItemDecoration;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.activity.actor.ActorAllTvShowsActivity;
import com.movieboxpro.android.view.activity.actor.ActorDetailContract;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.user.Login2Activity;
import com.movieboxpro.android.view.dialog.ReviewDialogFragment;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ActorDetailActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001!B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0013H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0016H\u0014J\b\u0010\u0018\u001a\u00020\u0016H\u0014J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0014J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0006H\u0017J\u0012\u0010\u001f\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010\u0010H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/ActorDetailActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/actor/ActorDetailPresenter;", "Lcom/movieboxpro/android/view/activity/actor/ActorDetailContract$View;", "()V", "actorModel", "Lcom/movieboxpro/android/model/ActorDetailModel;", "movieAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/ActorDetailModel$ActorMovie;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "tvAdapter", "Lcom/movieboxpro/android/model/ActorDetailModel$ActorTvShow;", "tvShowsList", "", "url", "", "bindPresenter", "getLayoutResId", "", "getStatusColor", "initData", "", "initListener", "initView", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "requestData", "showInfo", "model", "showReviewCount", "num", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActorDetailActivity extends BaseMvpActivity<ActorDetailPresenter> implements ActorDetailContract.View {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ActorDetailModel actorModel;
    private BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder> movieAdapter;
    private BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> tvAdapter;
    private List<? extends ActorDetailModel.ActorTvShow> tvShowsList;
    private String url;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_actor_detail;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.tvFullInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$l2iYJNCq0bapDOSb4qVhxBf-VSM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActorDetailActivity.m311initListener$lambda0(ActorDetailActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvAll)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$MrJvJMJ1oclfXrgL5yxgmHeqoo0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActorDetailActivity.m312initListener$lambda1(ActorDetailActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$vfJGNb9266aFFeWZqUn0YyU7oaw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActorDetailActivity.m313initListener$lambda2(ActorDetailActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llReview)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$GipoUg_bGlhI0tbMdwoLPGHRh4w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActorDetailActivity.m314initListener$lambda4(ActorDetailActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m311initListener$lambda0(ActorDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActorDetailActivity actorDetailActivity = this$0;
        String str = this$0.url;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("url");
            str = null;
        }
        SystemUtils.startBrowser((Activity) actorDetailActivity, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m312initListener$lambda1(ActorDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActorAllTvShowsActivity.Companion companion = ActorAllTvShowsActivity.Companion;
        ActorDetailActivity actorDetailActivity = this$0;
        String obj = ((TextView) this$0._$_findCachedViewById(R.id.tv_title)).getText().toString();
        ArrayList arrayList = this$0.tvShowsList;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        companion.start(actorDetailActivity, obj, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m313initListener$lambda2(ActorDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m314initListener$lambda4(ActorDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!App.isLogin()) {
            Login2Activity.start(this$0);
            return;
        }
        ActorDetailModel actorDetailModel = this$0.actorModel;
        if (actorDetailModel == null) {
            return;
        }
        ReviewDialogFragment.Companion.newInstance(actorDetailModel.getActor().getActor_id(), 4).show(this$0.getSupportFragmentManager(), ReviewDialogFragment.class.getSimpleName());
    }

    @Override // com.movieboxpro.android.view.activity.actor.ActorDetailContract.View
    public void showReviewCount(String str) {
        ((TextView) _$_findCachedViewById(R.id.tvReviewNum)).setText(str);
    }

    @Override // com.movieboxpro.android.view.activity.actor.ActorDetailContract.View
    public void showInfo(ActorDetailModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.actorModel = model;
        this.tvShowsList = model.getTv();
        String imdb_link = model.getActor().getImdb_link();
        Intrinsics.checkNotNullExpressionValue(imdb_link, "model.actor.imdb_link");
        this.url = imdb_link;
        if (model.getTv().isEmpty()) {
            RelativeLayout rlTv = (RelativeLayout) _$_findCachedViewById(R.id.rlTv);
            Intrinsics.checkNotNullExpressionValue(rlTv, "rlTv");
            CommonExtKt.gone(rlTv);
        } else if (model.getTv().size() > 10) {
            TextView tvAll = (TextView) _$_findCachedViewById(R.id.tvAll);
            Intrinsics.checkNotNullExpressionValue(tvAll, "tvAll");
            CommonExtKt.visible(tvAll);
        } else {
            TextView tvAll2 = (TextView) _$_findCachedViewById(R.id.tvAll);
            Intrinsics.checkNotNullExpressionValue(tvAll2, "tvAll");
            CommonExtKt.gone(tvAll2);
        }
        final List<ActorDetailModel.ActorTvShow> tv2 = model.getTv();
        this.tvAdapter = new BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder>(tv2) { // from class: com.movieboxpro.android.view.activity.actor.ActorDetailActivity$showInfo$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, ActorDetailModel.ActorTvShow item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.loadLandGifHolder(getContext(), item.getBanner_mini(), (ImageView) holder.getView(R.id.tv_item_poster));
                TextView textView = (TextView) holder.getView(R.id.tv_item_season);
                if (!TextUtils.isEmpty(item.getSeason_episode())) {
                    textView.setText(item.getSeason_episode());
                    CommonExtKt.visible(textView);
                } else {
                    CommonExtKt.gone(textView);
                }
                CommonExtKt.gone(holder.getView(R.id.slanted_text_view));
                TextView textView2 = (TextView) holder.getView(R.id.tvImdbRating);
                String imdb_rating = item.getImdb_rating();
                if (imdb_rating == null || imdb_rating.length() == 0) {
                    CommonExtKt.textShadow$default(textView2, "-.-", 0, 0, 6, null);
                    return;
                }
                String imdb_rating2 = item.getImdb_rating();
                if (imdb_rating2 == null) {
                    imdb_rating2 = "";
                }
                CommonExtKt.textShadow$default(textView2, imdb_rating2, 0, 0, 6, null);
            }
        };
        ActorDetailActivity actorDetailActivity = this;
        boolean z = false;
        ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setLayoutManager(new LinearLayoutManager(actorDetailActivity, 0, false));
        ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).addItemDecoration(new LayoutManagerItemDecoration(0, 10));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvTvShows);
        BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter = this.tvAdapter;
        BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvAdapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        if (model.getMovie().isEmpty()) {
            TextView tvMovie = (TextView) _$_findCachedViewById(R.id.tvMovie);
            Intrinsics.checkNotNullExpressionValue(tvMovie, "tvMovie");
            CommonExtKt.gone(tvMovie);
        }
        final List<ActorDetailModel.ActorMovie> movie = model.getMovie();
        this.movieAdapter = new BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder>(movie) { // from class: com.movieboxpro.android.view.activity.actor.ActorDetailActivity$showInfo$2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, ActorDetailModel.ActorMovie item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.getQuality_tag_new()), (ImageView) holder.getView(R.id.movie_item_desc));
                GlideUtils.loadPortraitGifHolder(getContext(), item.getPoster(), (ImageView) holder.getView(R.id.movie_item_poster));
                TextView textView = (TextView) holder.getView(R.id.tvImdbRating);
                String imdb_rating = item.getImdb_rating();
                if (imdb_rating == null || imdb_rating.length() == 0) {
                    CommonExtKt.textShadow$default(textView, "-.-", 0, 0, 6, null);
                    return;
                }
                String imdb_rating2 = item.getImdb_rating();
                if (imdb_rating2 == null) {
                    imdb_rating2 = "";
                }
                CommonExtKt.textShadow$default(textView, imdb_rating2, 0, 0, 6, null);
            }
        };
        if (CommonUtils.isTablet()) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).setLayoutManager(new GridLayoutManager(actorDetailActivity, 5));
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).addItemDecoration(new GridSpacingItemDecoration(10, true));
        } else {
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).setLayoutManager(new GridLayoutManager(actorDetailActivity, 3));
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).addItemDecoration(new GridSpacingItemDecoration(10, true));
        }
        ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).setAdapter(this.movieAdapter);
        ((TextView) _$_findCachedViewById(R.id.tvName)).setText(model.getActor().getName());
        String job = model.getActor().getJob();
        Intrinsics.checkNotNullExpressionValue(job, "model.actor.job");
        ((TextView) _$_findCachedViewById(R.id.tvJob)).setText(StringsKt.replace$default(job, ",", "|", false, 4, (Object) null));
        String avatar = model.getActor().getAvatar();
        if ((avatar == null || avatar.length() == 0) ? true : true) {
            CircleImageView ivAvatar = (CircleImageView) _$_findCachedViewById(R.id.ivAvatar);
            Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
            CommonExtKt.gone(ivAvatar);
            AppCompatTextView tvNameFirst = (AppCompatTextView) _$_findCachedViewById(R.id.tvNameFirst);
            Intrinsics.checkNotNullExpressionValue(tvNameFirst, "tvNameFirst");
            CommonExtKt.visible(tvNameFirst);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvNameFirst)).setText(CommonUtils.getNameFirstLetter(model.getActor().getName()));
        } else {
            CircleImageView ivAvatar2 = (CircleImageView) _$_findCachedViewById(R.id.ivAvatar);
            Intrinsics.checkNotNullExpressionValue(ivAvatar2, "ivAvatar");
            CommonExtKt.visible(ivAvatar2);
            AppCompatTextView tvNameFirst2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvNameFirst);
            Intrinsics.checkNotNullExpressionValue(tvNameFirst2, "tvNameFirst");
            CommonExtKt.gone(tvNameFirst2);
            GlideUtils.load((Activity) this, model.getActor().getAvatar(), (ImageView) ((CircleImageView) _$_findCachedViewById(R.id.ivAvatar)), (int) R.drawable.image_loading_placeholer);
        }
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(model.getActor().getName());
        BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter3 = this.tvAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$grQzw82SFMMYQg7FSGpxjsjNmqE
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                ActorDetailActivity.m317showInfo$lambda5(ActorDetailActivity.this, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder> baseQuickAdapter4 = this.movieAdapter;
        if (baseQuickAdapter4 == null) {
            return;
        }
        baseQuickAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorDetailActivity$z1Mw7aE-u1sdMAW-gFbLIzejM3w
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                ActorDetailActivity.m318showInfo$lambda6(ActorDetailActivity.this, baseQuickAdapter5, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showInfo$lambda-5  reason: not valid java name */
    public static final void m317showInfo$lambda5(ActorDetailActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter = this$0.tvAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvAdapter");
            baseQuickAdapter = null;
        }
        ActorDetailModel.ActorTvShow item = baseQuickAdapter.getItem(i);
        TvDetailActivity.start(this$0, item == null ? null : item.getId(), item == null ? null : item.getBanner_mini(), item != null ? item.getPoster() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showInfo$lambda-6  reason: not valid java name */
    public static final void m318showInfo$lambda6(ActorDetailActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder> baseQuickAdapter = this$0.movieAdapter;
        ActorDetailModel.ActorMovie item = baseQuickAdapter == null ? null : baseQuickAdapter.getItem(i);
        MovieDetailActivity.start(this$0, item == null ? null : item.getId(), item != null ? item.getPoster() : null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).setLayoutManager(new GridLayoutManager(this, 5));
            BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder> baseQuickAdapter = this.movieAdapter;
            if (baseQuickAdapter == null) {
                return;
            }
            baseQuickAdapter.notifyDataSetChanged();
        } else if (getResources().getConfiguration().orientation == 1) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvMovies)).setLayoutManager(new GridLayoutManager(this, 3));
            BaseQuickAdapter<ActorDetailModel.ActorMovie, BaseViewHolder> baseQuickAdapter2 = this.movieAdapter;
            if (baseQuickAdapter2 == null) {
                return;
            }
            baseQuickAdapter2.notifyDataSetChanged();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        ActorDetailPresenter actorDetailPresenter = (ActorDetailPresenter) this.mPresenter;
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        actorDetailPresenter.getReviewNum(stringExtra);
        ActorDetailPresenter actorDetailPresenter2 = (ActorDetailPresenter) this.mPresenter;
        String stringExtra2 = getIntent().getStringExtra("id");
        actorDetailPresenter2.requestDetail(stringExtra2 != null ? stringExtra2 : "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ActorDetailPresenter bindPresenter() {
        return new ActorDetailPresenter(this);
    }

    /* compiled from: ActorDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/ActorDetailActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intent intent = new Intent(context, ActorDetailActivity.class);
            intent.putExtra("id", id);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
